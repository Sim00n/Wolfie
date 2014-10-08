package net.lsrp.wolfie.graphics;

import net.lsrp.wolfie.Game;

public class Screen {

	public int width, height;
	public int[] pixels;
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
		
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = 0xFFFFAAFF;
		}
	}
	
	public void clear() {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void renderSprite(int xp, int yp, Sprite sprite) {
			
		for(int y = 0; y < sprite.SIZE; y++) {
			int ya = y + yp;
			for(int x = 0; x < sprite.SIZE; x++) {
				int xa = x + xp;
				
				if(xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				
				int col = sprite.pixels[x + y * sprite.SIZE];
				if(col != 0xFFFF00FF)
					pixels[xa + ya * width] = col;
			}
		}
	}
	
	public void renderPlatform(int xp, int yp, int wp, Sprite sprite, Sprite sprite_l, Sprite sprite_r) {
		
		for(int y = 0; y < sprite_l.SIZE; y++) {
			int ya = y + yp;
			ya+=10;
			for(int x = 0; x < sprite_l.SIZE; x++) {
				int xa = x + xp;
				
				if(xa < -sprite_l.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				
				int col = sprite_l.pixels[x + y * sprite_l.SIZE];
				if(col != 0xFFFF00FF)
					pixels[xa + ya * width] = col;
			}
		}
		
		for(int w = 1; w < wp - 1; w++) {
			for(int y = 0; y < sprite.SIZE; y++) {
				int ya = y + yp;
				ya+=10;
				for(int x = 0; x < sprite.SIZE; x++) {
					int xa = x + xp + (w << 4);
					
					if(xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
					if(xa < 0) xa = 0;
					
					int col = sprite.pixels[x + y * sprite.SIZE];
					if(col != 0xFFFF00FF)
						pixels[xa + ya * width] = col;
				}
			}
		}
		
		for(int y = 0; y < sprite_r.SIZE; y++) {
			int ya = y + yp;
			ya+=10;
			for(int x = 0; x < sprite_r.SIZE; x++) {
				int xa = x + xp + (wp-1 << 4);
				
				if(xa < -sprite_r.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				
				int col = sprite_r.pixels[x + y * sprite_r.SIZE];
				if(col != 0xFFFF00FF)
					pixels[xa + ya * width] = col;
			}
		}
	}

	public void renderPlatformScore(int yp, Sprite sprite) {
		for(int y = 0; y < sprite.SIZE; y++) {
			int ya = y + yp;
			for(int x = 0; x < sprite.SIZE; x++) {
				int xa = x + (Game.WIDTH/2);
				
				if(xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				
				int col = sprite.pixels[x + y * sprite.SIZE];
				if(col != 0xFFFF00FF)
					pixels[xa + ya * width] = col;
			}
		}
	}
	
}
