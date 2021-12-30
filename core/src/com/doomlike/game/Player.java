package com.doomlike.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player {
	private float x;
	private float y;
	private float mouseX;
	private float mouseY;
	private float radius;
	private float dir;	
	private boolean isMovingRight;
	private boolean isMovingLeft;
	private boolean isMovingDown;
	private boolean isMovingUp;
	
	public Player(float x, float y, float radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.dir = 0;
		this.isMovingRight = false;
		this.isMovingLeft = false;
		this.isMovingDown = false;
		this.isMovingUp = false;
	}

	public void render(ShapeRenderer shapeRenderer) {
		System.out.printf("Player: %dx%d\n", (int)this.x, (int)this.y);

		if (this.isMovingDown) {
			this.y += 2;
		} 
		if (this.isMovingUp) {
			this.y -= 2;
		}
		if (this.isMovingLeft) {
			this.x -= 2;
		}
		if (this.isMovingRight) {
			this.x += 2;
		}
		
		shapeRenderer.setColor(Color.CYAN);
		shapeRenderer.circle(this.x, this.y, this.radius, 50);
	}
	
	public void renderDirection(ShapeRenderer shapeRenderer) {
		shapeRenderer.line(this.x, this.y, this.mouseX, this.mouseY, Color.WHITE, Color.WHITE);
	}
	
	public void setIsMovingUp(boolean val) {
		this.isMovingUp = val;
	}
	
	public void setIsMovingDown(boolean val) {
		this.isMovingDown = val;
	}
	
	public void setIsMovingRight(boolean val) {
		this.isMovingRight = val;
	}
	
	public void setIsMovingLeft(boolean val) {
		this.isMovingLeft = val;
	}
	
	public void setMouseX(float mouseX) {
		this.mouseX = mouseX;
	}

	public void setMouseY(float mouseY) {
		this.mouseY = mouseY;
	}
	
	public float getMouseX() {
		return this.mouseX;
	}
	
	public float getMouseY() {
		return this.mouseY;
	}
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
}
