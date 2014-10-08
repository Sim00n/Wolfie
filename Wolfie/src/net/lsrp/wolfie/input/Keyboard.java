package net.lsrp.wolfie.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	public static boolean[] keys = new boolean[65563];
	public static boolean up, down, left, right, space, r;
	
	public void tick(double delta) {
		up = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
		space = keys[KeyEvent.VK_SPACE];
		r = keys[KeyEvent.VK_R];
		
		if(keys[KeyEvent.VK_ESCAPE])
			System.exit(0);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
