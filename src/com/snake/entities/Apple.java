package com.snake.entities;

public class Apple {
	
	private int xPos;
	private int yPos;
	
	public Apple(int x, int y) {
		
		this.xPos = x;
		this.yPos = y;
	}
	
	public int getX(){
		
		return xPos;
	}
	
	public int getY(){
		
		return yPos;
	}
	
	public void setPos(int x, int y) {
		
		xPos = x;
		yPos = y;
	}
}