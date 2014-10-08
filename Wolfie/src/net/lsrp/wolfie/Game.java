package net.lsrp.wolfie;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import net.lsrp.wolfie.entity.Wolfie;
import net.lsrp.wolfie.graphics.Screen;
import net.lsrp.wolfie.input.Keyboard;
import net.lsrp.wolfie.level.Level;
import net.lsrp.wolfie.level.Platform;


public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	// Constants
	public static final String NAME = "Wolfie";
	public static final int WIDTH = 512;
	public static final int HEIGHT = WIDTH / 16 * 9;
	public static final int SCALE = 2;
	public static final int TICKS = 60;
		
	// Components
	protected JFrame frame;
	protected Thread thread;
	protected Screen screen;
	protected Level level;
	protected Keyboard keyboard;
	public Wolfie wolfie;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	// Runtime
	protected static boolean running = false;
	protected static int frames = 0;
	protected static int ticks = 0;	
	protected boolean death = false;
	
	public Game() {
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size);
		
		frame = new JFrame(NAME);
		screen = new Screen(WIDTH, HEIGHT);
		level = new Level(this, WIDTH >> 4, (HEIGHT >> 4)*2);
		keyboard = new Keyboard();
		wolfie = new Wolfie(Game.WIDTH/2, Game.HEIGHT - (2<<4), level);
		
		addKeyListener(keyboard);
	}
	
	protected synchronized void start() {
		thread = new Thread(this, "Thread");
		thread.start();
		running = true;
		
		frame.setBackground(new Color(0xFF00FF));
		frame.setForeground(Color.BLACK);
	}
	
	protected synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / TICKS;
		double delta = 0;
		int _frames = 0;
		int _ticks = 0;
		
		requestFocus();
		
		while(running) {
			
			long now = System.nanoTime();
			delta += (now-lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1) {
				tick(delta);
				_ticks++;
				delta--;
			}
			
			render();
			_frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = _frames;
				ticks = _ticks;
				_frames = 0;
				_ticks = 0;
			}
		}
	}

	private void tick(double delta) {
		keyboard.tick(delta);
		level.tick(delta);
		if(!death) {
			wolfie.tick(delta);
		}
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			bs = getBufferStrategy();
		}
		
		screen.clear();
		
		level.render(screen);
		wolfie.render(screen);
		
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
				
		g.setFont(new Font("Helvetica", Font.BOLD, 20));
		g.setColor(new Color(255, 255, 255, 255));
		g.drawString("Score: " + wolfie.score(), 40, 25);
		
		g.setFont(new Font("Arial", Font.BOLD, 16));
		g.drawString("Platform: " + level.platform, 40, 45);
		
		g.setColor(new Color(0, 0, 0, 255));
		g.setFont(new Font("Tahoma", Font.BOLD, 10));
		if(level.platform < 10) {
			g.drawString("" + level.platform, WIDTH*SCALE/2 + 14, level.longheight * 2 + 61);
		} else {
			g.drawString("" + level.platform, WIDTH*SCALE/2 + 10, level.longheight * 2 + 61);
		}

		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 12));
		g.drawString("X: " + wolfie.x, 40, 80);
		g.drawString("Y: " + wolfie.y, 40, 100);
		
		if(death) {
			g.setFont(new Font("Impact", Font.BOLD, 96));
			g.setColor(new Color(255, 255, 255, 255));
			g.drawString("GAME OVER!", Game.WIDTH/2, Game.HEIGHT);
			
			g.setFont(new Font("Arial", Font.BOLD, 28));
			g.setColor(new Color(0x7F, 0, 0, 255));
			g.drawString("Created by a Stony Brook University student.", Game.WIDTH/2 - 50, Game.HEIGHT + 60);
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Impact", Font.BOLD, 20));
			g.drawString("Score: " + wolfie.score(), 460, 385);
		}
		
		bs.show();		
	}
	
	public void death() {
		death = true;
		wolfie.x = 380;
		wolfie.y = 128;
	}
	
	public void restart() {
		wolfie.x = Game.WIDTH/2;
		wolfie.y = Game.HEIGHT - (2<<4);
		level.generatePlatforms();
		level.started = false;
		level.platform_update = 5;
		level.platform_type = Platform.TYPE.STONE;
		level.platform = 0;
		wolfie.restartScore();
		death = false;
	}
	
	public static void main(String[] args) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				super.run();
			}
		});
		
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(NAME);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setLocationRelativeTo(null);
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setVisible(true);
		game.start();
	}
}
