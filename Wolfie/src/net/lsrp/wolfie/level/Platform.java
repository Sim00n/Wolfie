package net.lsrp.wolfie.level;

import net.lsrp.wolfie.entity.Entity;
import net.lsrp.wolfie.graphics.Screen;
import net.lsrp.wolfie.graphics.Sprite;

public class Platform extends Entity {

	public int width, height;
	protected Sprite sprite;
	public enum TYPE { ICE, BONE, LEAF, JELLY, STONE, WOOD, RAINBOW };
	protected TYPE type;
	
	public Platform(int x, int y, int width, int height, TYPE type) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
	}
	
	@Override
	public void tick(double delta) {
		
	}
	
	public int getPixelWidth() {
		return width << 4;
	}
	
	public int getPixelHeight() {
		return height << 4;
	}
	
	@Override
	public void render(Screen screen) {
		switch(type) {
			case ICE: {
				screen.renderPlatform(x, y, width, Sprite.platform_ice, Sprite.platform_ice_l, Sprite.platform_ice_r);
				if(width >= 30)
					screen.renderPlatformScore(y + 20, Sprite.platform_ice_s);
				break;
			}
			case BONE: {
				screen.renderPlatform(x, y, width, Sprite.platform_bone, Sprite.platform_bone_l, Sprite.platform_bone_r);
				if(width >= 30)
					screen.renderPlatformScore(y + 20, Sprite.platform_bone_s);
				break;
			}
			case LEAF: {
				screen.renderPlatform(x, y, width, Sprite.platform_leaf, Sprite.platform_leaf_l, Sprite.platform_leaf_r);
				if(width >= 30)
					screen.renderPlatformScore(y + 20, Sprite.platform_leaf_s);
				break;
			}
			case JELLY: {
				screen.renderPlatform(x, y, width, Sprite.platform_jelly, Sprite.platform_jelly_l, Sprite.platform_jelly_r);
				if(width >= 30)
					screen.renderPlatformScore(y + 20, Sprite.platform_jelly_s);
				break;
			}
			case STONE: {
				screen.renderPlatform(x, y, width, Sprite.platform_stone, Sprite.platform_stone_l, Sprite.platform_stone_r);
				if(width >= 30)
					screen.renderPlatformScore(y + 20, Sprite.platform_stone_s);			
				break;
			}
			case WOOD: {
				screen.renderPlatform(x, y, width, Sprite.platform_wood, Sprite.platform_wood_l, Sprite.platform_wood_r);
				if(width >= 30)
					screen.renderPlatformScore(y + 20, Sprite.platform_wood_s);
				break;
			}
			case RAINBOW: {
				screen.renderPlatform(x, y, width, Sprite.platform_rainbow, Sprite.platform_rainbow_l, Sprite.platform_rainbow_r);
				if(width >= 30)
					screen.renderPlatformScore(y + 20, Sprite.platform_rainbow_s);
				break;
			}
		}
	}
	
	
}
