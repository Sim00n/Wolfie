package net.lsrp.wolfie.entity;

import net.lsrp.wolfie.Game;
import net.lsrp.wolfie.graphics.Screen;
import net.lsrp.wolfie.graphics.Sprite;
import net.lsrp.wolfie.input.Keyboard;
import net.lsrp.wolfie.level.Level;
import net.lsrp.wolfie.level.Platform;

public class Wolfie extends Entity {

	private Level level;
	public Sprite[] sprite;
	double deltaStack = 0;
	private boolean jump = false;
	private int score = 0;
	
	public Wolfie(int x, int y, Level level) {
		this.x = x;
		this.y = y;
		sprite = new Sprite[4];
		this.level = level;
		
		sprite[0] = Sprite.wolfie_tl;
		sprite[1] = Sprite.wolfie_tr;
		sprite[2] = Sprite.wolfie_bl;
		sprite[3] = Sprite.wolfie_br;
	}
	
	@Override
	public void tick(double delta) {
		if(!level.started) {
			if(Keyboard.space || Keyboard.up) {
				level.started = true;
			}
		}
		
		if((Keyboard.space || Keyboard.up) && (collision() || wallcollision()) && y > 10 && !jump) {
			jump = true;
		}
		
		//// Accelerating
		if(Keyboard.left) {
			if(dx > -4)
				dx--;
		}
		if(Keyboard.right) {
			if(dx < 4)
				dx++;
		}
		
		//// Bouncing off the walls with higher speed
		// Left wall
		if(x < 16) {
			x = 16;
			dx = -2*dx;
		}
		// Right wall
		if(x + 32 > Game.WIDTH - 16) {
			x = Game.WIDTH - 32 - 16;
			dx = -2*dx;
			dy -= 10;
		}
		
		x += dx;
		y += dy;
		if(dy < 0) {
			score += -dy;
		}
			
		//// Death
		if(y > Game.HEIGHT - 12) {
			die();
		}
		
		//// Jumping timer
		deltaStack += delta;
		if(deltaStack > 12) {
			jump = false;
			deltaStack = 0;
			if(dx > 0) dx--;
			if(dx < 0) dx++; 
		}
		
				
		if(jump) {
			if(wallcollision())
				dy = -10;
			else
				dy = -5;
			
		} else {	
			if(!collision() && !wallcollision()) {
				if(dy < 4)
					dy += 1;
			}
		}
	}
	
	@Override
	public void render(Screen screen) {
		screen.renderSprite(x, y-16, sprite[0]);
		screen.renderSprite(x + 16, y - 16, sprite[1]);
		screen.renderSprite(x, y, sprite[2]);
		screen.renderSprite(x + 16, y, sprite[3]);
	}
	
	
	private boolean collision() {
		for(Platform p : level.platforms) {
			if(p != null) {
				
				int left = x + 11;
				int right = x + 21;
				
				if(right >= p.x && left <= p.x + p.getPixelWidth()) {
					
					if(y >= p.y-3 && y <= p.y + p.getPixelHeight()) {
						y = p.y;
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private boolean wallcollision() {
		if(x <= 16 + 5 || x > Game.WIDTH - 32 - 16 - 5) {
			return true;
		}
		return false;
	}
	
	private void die() {
		level.death();
	}
	
	public int score() {
		return score;
	}
	
	public void restartScore() {
		score = 0;
	}
}
