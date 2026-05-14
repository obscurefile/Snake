package com.snake.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardController implements KeyListener {
	
	public String key = "S";
	
	public String getKey() {
		
		return key;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		key = KeyEvent.getKeyText(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}