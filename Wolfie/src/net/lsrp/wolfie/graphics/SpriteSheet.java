package net.lsrp.wolfie.graphics;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	public static SpriteSheet sheet = new SpriteSheet("/textures/sheets/spritesheet.png", 320);

	private String path;
	public final int SIZE;
	public int[] pixels;
	
	public SpriteSheet(String path, int size) {
		this.path = path;
		this.SIZE = size;
		pixels = new int[SIZE * SIZE];
		load();
	}
	
	private void load() {
		try {
			BufferedImage image = ImageIO.read(this.getClass().getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
