package com.snake.core;

import javax.swing.JPanel;
import com.snake.entities.Body;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GameScreen extends JPanel {

	private Game game;
	
	public GameScreen() {

		this.setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
		this.setBackground(new Color(86 , 130, 3));
		this.setDoubleBuffered(true);
		this.setLayout(null);
		this.setFocusable(true);
	}
	
	public void setGame(Game game) {
		
		this.game = game;
	}

	void drawSnake(Graphics g) {
		
		Body snake = game.getPlayer().getHead();
		
		g.setColor(new Color(44 , 195, 52));
		g.fillRect(snake.getX(), snake.getY(), Game.TILE_SIZE, Game.TILE_SIZE);
		
		while (snake.getNextSeg() != null) {
			
			snake = snake.getNextSeg();
			
			g.fillRect(snake.getX(), snake.getY(), Game.TILE_SIZE, Game.TILE_SIZE);
		}
	}
	
	void drawApple(Graphics g) {
		
		g.setColor(new Color(255 , 0, 56));
		g.fillRect(game.getApple().getX(), game.getApple().getY(), Game.TILE_SIZE, Game.TILE_SIZE);
	}
	
	void drawEnd(Graphics g) {

		g.setColor(new Color(0, 100, 0));
		g.setFont(new Font ("Comic Sans", 1, 50));
		g.drawString("GAME OVER", Game.WIDTH/2 - 150, Game.HEIGHT/2);
	}
	
	void drawScore(Graphics g, int score) {

		g.setColor(new Color(0, 100, 0));
		g.setFont(new Font ("Comic Sans", 1, 35));
		g.drawString(String.valueOf(score), Game.WIDTH/60, Game.HEIGHT-Game.HEIGHT/20);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		requestFocus(true);
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		drawApple(g2);
		drawSnake(g2);
		drawScore(g2, game.getPlayer().getScore());
		
		if (!game.playerInBounds() || game.checkCollision()) {
			
			drawEnd(g2);
		}
	}
}