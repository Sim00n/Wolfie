package net.lsrp.wolfie.entity;

import java.util.Random;

import net.lsrp.wolfie.graphics.Screen;
import net.lsrp.wolfie.level.Level;


public class Entity {

	public int x, y;
	protected double dx, dy;
	private boolean removed = false;
	protected Level level;
	
	public static Random rand = new Random();
	
	public void tick(double delta) {
	
	}
	
	public void render(Screen screen) {
		
	}
	
	public void remove() {
		removed = true;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public void init(Level level) {
		this.level = level;
	}
	
}
