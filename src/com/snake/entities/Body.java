package com.snake.entities;

public class Body {
	
	private int xPos;
	private int yPos;
	private int lastX;
	private int lastY;
	private Body nextSeg;
	
	public Body(int x, int y) {
		
		this.xPos = x;
		this.yPos = y;
		this.nextSeg = null;
	}
	
	void setX(int value){
		
		xPos = value;
	}
	
	public int getX(){
		
		return xPos;
	}
	
	void setY(int value){
		
		yPos = value;
	}
	
	public int getY(){
		
		return yPos;
	}
	
	int getLastX() {
		
		return lastX;
	}
	
	int getLastY() {
		
		return lastY;
	}
	
	void setLastPos(int x, int y) {
		
		lastX = x;
		lastY = y;
	}
	
	void setPos(int x, int y) {
		
		xPos = x;
		yPos = y;
	}
	
	void setNextSeg(Body seg) {
		
		nextSeg = seg;
	}
	
	public Body getNextSeg() {
		
		return nextSeg;
	}
}