package com.snake.entities;

public class Player {
	
	private Body head;
	private int score = 0;
	
	public Player() {

		head = new Body(300, 0);
	}
	
	public Body getHead() {
		
		return head;
	}
	
	public void setScore(int value) {
		
		score = value;
	}
	
	public int getScore() {
		
		return score;
	}
	
	public int getX() {
		
		return head.getX();
	}
	
	public int getY() {
		
		return head.getY();
	}
	
	public void moveX(int x) {
		
		head.setLastPos(head.getX(), head.getY());
		
		head.setX(head.getX() + x);
	}
	
	public void moveY(int y) {
		
		head.setLastPos(head.getX(), head.getY());
		
		head.setY(head.getY() + y);
	}
	
	public void addSegment() {
		
		Body newSeg = head;
		
		while (newSeg.getNextSeg() != null) {

			newSeg = newSeg.getNextSeg();
		}
			
		newSeg.setNextSeg(new Body(newSeg.getLastX(), newSeg.getLastY()));
	}
	
	public void updateSegments() {
		
		Body newSeg = head;
		
		while (newSeg.getNextSeg() != null) {
			
			int lastX = newSeg.getLastX();
			int lastY = newSeg.getLastY();

			newSeg = newSeg.getNextSeg();
			
			newSeg.setLastPos(newSeg.getX(), newSeg.getY());
			newSeg.setPos(lastX, lastY);
		}
	}
}
