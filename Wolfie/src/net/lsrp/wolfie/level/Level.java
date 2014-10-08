package net.lsrp.wolfie.level;

import java.util.Random;

import net.lsrp.wolfie.Game;
import net.lsrp.wolfie.graphics.Screen;
import net.lsrp.wolfie.graphics.Sprite;
import net.lsrp.wolfie.input.Keyboard;


public class Level {

	private Game game;
	public int width, height;
	public Platform[] platforms;
	public Platform.TYPE platform_type = Platform.TYPE.STONE;;
	public int platform_update = 5;
	public int spacing;
	private double deltaStack = 0.0;
	public boolean started = false;
	public int longheight = 0;
	public int platform = 0;
	
	public static Level level;
	
	public Random rand = new Random();
	
	public Level(Game game, int width, int height) {
		this.game = game;
		this.width = width;
		this.height = height;
		spacing = 0;
		
		platforms = new Platform[20];
		generatePlatforms();
	}
	
	public void generatePlatforms() {		
		platforms[0] = new Platform(1 << 4, Game.HEIGHT - (2 << 4), (Game.WIDTH >> 4) - 2, 1, Platform.TYPE.STONE);
		
		for(int i = 1; i < platforms.length; i++) {
			int l = rand.nextInt(10) + 5;
			int block_size = rand.nextInt(25) + 1;
			platforms[i] = new Platform(rand.nextInt(block_size)+1 << 4, Game.HEIGHT - (i * 50), l, 1, Platform.TYPE.STONE);
		}
	}
	
	public void render(Screen screen) {
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				screen.renderSprite(x << 4, y << 4, Sprite.background);
			}
			
			screen.renderSprite(0, y << 4, Sprite.border);
			screen.renderSprite((width << 4) - 16, y << 4, Sprite.border_i);
		}
		
		for(Platform p : platforms) {
			if(p != null) {
				p.render(screen);
			}
		}
	}
	
	public void tick(double delta) {
		if(Keyboard.r) {
			game.restart();
		}
		
		deltaStack += delta;
		
		if(started) {
			if(deltaStack > platform_update) {
				for(Platform p : platforms) {
					p.y++;;
				}
				deltaStack = 0.0;
			}
			
			if(game.wolfie.y < 25) {
				for(Platform p : platforms) {
					p.y += 5;
				}
			} else if(game.wolfie.y < 50) {
				for(Platform p : platforms) {
					p.y += 3;
				}
			} else if(game.wolfie.y < 100) {
				for(Platform p : platforms) {
					p.y++;
				}
			}
		}
		
		for(int i = 0; i < platforms.length; i++) {
			if(platforms[i].width >= 30) {
				longheight = platforms[i].y;
			}
			if(platforms[i].y > Game.HEIGHT + 20) {
				int tmpy = 0;
				int k = 0;
				for(int j = 0; j < platforms.length; j++) {
					if(i != j) {
						if(platforms[j].y < tmpy) {
							tmpy = platforms[j].y;
							k = j;
						}
					}
				}
				platforms[i].y = platforms[k].y - 50;
								
				if(platforms[i].width < 30) {
					int block_size = rand.nextInt(25) + 1;
					platforms[i].x = rand.nextInt(block_size)+1 << 4;
					platforms[i].width = rand.nextInt(10) + 5;
				} else {
					
					if(platform_type == Platform.TYPE.STONE)
						platform_type = Platform.TYPE.WOOD;
					else if(platform_type == Platform.TYPE.WOOD)
						platform_type = Platform.TYPE.ICE;
					else if(platform_type == Platform.TYPE.ICE)
						platform_type = Platform.TYPE.LEAF;
					else if(platform_type == Platform.TYPE.LEAF)
						platform_type = Platform.TYPE.JELLY;
					else if(platform_type == Platform.TYPE.JELLY)
						platform_type = Platform.TYPE.BONE;
					else if(platform_type == Platform.TYPE.BONE)
						platform_type = Platform.TYPE.RAINBOW;
					else
						platform_type = Platform.TYPE.STONE;
					
					if(platform_update > 0) {
						platform_update--;
					}
					platform++;
				}
				
				platforms[i].type = platform_type;
			}
		}
	}
	
	public void death() {
		game.death();
	}
}