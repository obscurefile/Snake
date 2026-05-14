package com.snake.core;

import com.snake.entities.Player;
import com.snake.entities.Apple;
import com.snake.entities.Body;
import com.snake.controller.KeyboardController;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Game implements Runnable {
	
	final static int WIDTH = 600;
	final static int HEIGHT = 600;
	final static int TILE_SIZE = 30;
	
	private int delay = 125;
	
	private Player player = new Player();
	private Apple apple = new Apple(WIDTH/2, HEIGHT/2);
	
	private String direction = "Down";
	
	private JFrame window = new JFrame();
	private GameScreen screen = new GameScreen();
	
	private Thread gameThread;
	
	private KeyboardController keyboard = new KeyboardController();
	
	private SoundManager audioPlayer = new SoundManager();
	
	public void initializeWindow() {
		
		screen.setGame(this);
		screen.addKeyListener(keyboard);
		
		window.setTitle("Snake");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.black);
		window.setSize(WIDTH, HEIGHT);
		window.setResizable(false);
		window.setFocusable(true);
		
		File img = new File("res/images/icon/snake_icon.png"); 
		String cardicon = img.getAbsolutePath(); 
		ImageIcon gameicon = new ImageIcon(cardicon); 
		
		window.setIconImage(gameicon.getImage()); 
		window.add(screen);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
	
	public Player getPlayer() {
		
		return player;
	}
	
	public Apple getApple() {
		
		return apple;
	}
	
	boolean playerInBounds() {
		
		return player.getX() >= 0 && player.getY() >= 0 && player.getX() < WIDTH && player.getY() < HEIGHT;
	}

	boolean checkCollision() {
		
		Body body = player.getHead();
		
		boolean collision = false;
		
		while (body.getNextSeg() != null) {
			
			body = body.getNextSeg();
			
			if (player.getX() == body.getX() && player.getY() == body.getY()) {
				
				collision = true;
			}
		}
		
		return collision;
	}
	
	void repositionApple() {
		
		int x = new Random().nextInt(0, Game.WIDTH/TILE_SIZE);
		int y = new Random().nextInt(0, Game.HEIGHT/TILE_SIZE);
		
		apple.setPos(x * TILE_SIZE, y * TILE_SIZE);
		
		Body body = player.getHead();
		
		while (body.getNextSeg() != null) {
			
			body = body.getNextSeg();
			
			if (apple.getX() == body.getX() && apple.getY() == body.getY()) {

				apple.setPos(new Random().nextInt(0, Game.WIDTH/TILE_SIZE) * TILE_SIZE, new Random().nextInt(0, Game.HEIGHT/TILE_SIZE) * TILE_SIZE);
			}
		}
	}
	
	void changeDirection() {
		
		if (keyboard.getKey().equals("W") && !direction.equals("Down")) {
			
			direction = "Up";
		} else if (keyboard.getKey().equals("S") && !direction.equals("Up")) {
			
			direction = "Down";
		} else if (keyboard.getKey().equals("A") && !direction.equals("Right")) {
			
			direction = "Left";
		} else if (keyboard.getKey().equals("D") && !direction.equals("Left")) {
			
			direction = "Right";
		}
	}
	
	void moveSnake() {
		
		switch (direction) {
		
		case "Up":
			
			player.moveY(-TILE_SIZE);
			break;
		case "Down":
			
			player.moveY(TILE_SIZE);
			break;
		case "Left":
			
			player.moveX(-TILE_SIZE);
			break;
		case "Right":
			
			player.moveX(TILE_SIZE);
			break;		
		}
	}
	
	void appleAte() {
		
		if (player.getX() == apple.getX() && player.getY() == apple.getY()) {
			
			playAppleAte();
			
			player.setScore(player.getScore() + 100);
			player.addSegment();
			
			repositionApple();
			
			if (delay > 60) {
				delay -= 5;
			}
		}
	}
	
	void playAppleAte() {
		
		try {
			audioPlayer.playAudio("res/sounds/effects/sfx_apple_ate.wav");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	void playGameEndAudio() {

		try {
			audioPlayer.playAudio("res/sounds/effects/sfx_end.wav");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		while (playerInBounds() && !checkCollision()) {
				
			update();
				
			try {
				
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
				
			screen.repaint();
		}
		
		playGameEndAudio();
	}
	
	public void startGame() {
		
		gameThread = new Thread(this);
		
		gameThread.run();
	}
	
	public void update() {
		
		changeDirection();
		
		moveSnake();
		
		player.updateSegments();
		
		appleAte();
	}
}