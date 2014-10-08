package net.lsrp.wolfie.graphics;

public class Sprite {

	public static Sprite background = new Sprite(16, 0, 0, SpriteSheet.sheet);
	//public static Sprite background = new Sprite(16, 1, 0, SpriteSheet.sheet);
	public static Sprite border = new Sprite(16, 0, 1, SpriteSheet.sheet);
	public static Sprite border_i = new Sprite(16, 0, 1, SpriteSheet.sheet, true);
	
	public static Sprite platform_bone_l = new Sprite(16, 0, 3, SpriteSheet.sheet);
	public static Sprite platform_bone = new Sprite(16, 1, 3, SpriteSheet.sheet);
	public static Sprite platform_bone_r = new Sprite(16, 2, 3, SpriteSheet.sheet);
	public static Sprite platform_bone_s = new Sprite(16, 3, 3, SpriteSheet.sheet);

	public static Sprite platform_ice_l = new Sprite(16, 0, 2, SpriteSheet.sheet);
	public static Sprite platform_ice = new Sprite(16, 1, 2, SpriteSheet.sheet);
	public static Sprite platform_ice_r = new Sprite(16, 2, 2, SpriteSheet.sheet);
	public static Sprite platform_ice_s = new Sprite(16, 3, 2, SpriteSheet.sheet);
	
	public static Sprite platform_leaf_l = new Sprite(16, 0, 4, SpriteSheet.sheet);
	public static Sprite platform_leaf = new Sprite(16, 1, 4, SpriteSheet.sheet);
	public static Sprite platform_leaf_r = new Sprite(16, 2, 4, SpriteSheet.sheet);
	public static Sprite platform_leaf_s = new Sprite(16, 3, 4, SpriteSheet.sheet);
	
	public static Sprite platform_jelly_l = new Sprite(16, 0, 5, SpriteSheet.sheet);
	public static Sprite platform_jelly = new Sprite(16, 1, 5, SpriteSheet.sheet);
	public static Sprite platform_jelly_r = new Sprite(16, 2, 5, SpriteSheet.sheet);
	public static Sprite platform_jelly_s = new Sprite(16, 3, 5, SpriteSheet.sheet);
	
	public static Sprite platform_stone_l = new Sprite(16, 0, 6, SpriteSheet.sheet);
	public static Sprite platform_stone = new Sprite(16, 1, 6, SpriteSheet.sheet);
	public static Sprite platform_stone_r = new Sprite(16, 2, 6, SpriteSheet.sheet);
	public static Sprite platform_stone_s = new Sprite(16, 3, 6, SpriteSheet.sheet);
	
	public static Sprite platform_wood_l = new Sprite(16, 0, 7, SpriteSheet.sheet);
	public static Sprite platform_wood = new Sprite(16, 1, 7, SpriteSheet.sheet);
	public static Sprite platform_wood_r = new Sprite(16, 2, 7, SpriteSheet.sheet);
	public static Sprite platform_wood_s = new Sprite(16, 3, 7, SpriteSheet.sheet);
	
	public static Sprite platform_rainbow_l = new Sprite(16, 0, 8, SpriteSheet.sheet);
	public static Sprite platform_rainbow = new Sprite(16, 1, 8, SpriteSheet.sheet);
	public static Sprite platform_rainbow_r = new Sprite(16, 2, 8, SpriteSheet.sheet);
	public static Sprite platform_rainbow_s = new Sprite(16, 3, 8, SpriteSheet.sheet);
	
	public static Sprite wolfie_tl = new Sprite(16, 2, 0, SpriteSheet.sheet);
	public static Sprite wolfie_tr = new Sprite(16, 2, 1, SpriteSheet.sheet);
	public static Sprite wolfie_bl = new Sprite(16, 3, 0, SpriteSheet.sheet);
	public static Sprite wolfie_br = new Sprite(16, 3, 1, SpriteSheet.sheet);
	
	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;
	
	public Sprite(int size, int y, int x, SpriteSheet sheet) {
		this.SIZE = size;
		this.y = y * size;
		this.x = x * size;
		this.sheet = sheet;
		pixels = new int[SIZE * SIZE];
		
		load(false);
	}
	
	public Sprite(int size, int y, int x, SpriteSheet sheet, boolean inverse) {
		this.SIZE = size;
		this.y = y * size;
		this.x = x * size;
		this.sheet = sheet;
		pixels = new int[SIZE * SIZE];
		
		load(inverse);
	}
	
	public Sprite(int size, int color) {
		this.SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}
	
	public void setColor(int color) {
		for(int y = 0; y < SIZE; y++) {
			for(int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = color;
			}
		}
	}
	
	private void load(boolean inverse) {
		if(inverse) {
			for(int y = 0; y < SIZE; y++) {
				for(int x = 0; x < SIZE; x++) {
					pixels[x + y * SIZE] = sheet.pixels[(this.x - x + 15) + (y+this.y) * sheet.SIZE];
				}
			}
		} else {
			for(int y = 0; y < SIZE; y++) {
				for(int x = 0; x < SIZE; x++) {
					pixels[x + y * SIZE] = sheet.pixels[(x+this.x) + (y+this.y) * sheet.SIZE];
				}
			}
		}
	}	
}
