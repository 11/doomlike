package com.doomlike.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Player {
	final private float radius = Tilemap.TILE_UNIT / 2f;

	private Vector2 pos;
	private Vector2 dir;
	private Vector2 mouse;
	private Vector2 cameraPlane;
	private Vector2 focalPoint;

	private boolean isMovingRight;
	private boolean isMovingLeft;
	private boolean isMovingDown;
	private boolean isMovingUp;
	private boolean isRotatingLeft;
	private boolean isRotatingRight;

	public Player() {
		this.pos = new Vector2(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
		this.dir = new Vector2(1,1);
		this.mouse = new Vector2(0,0);
		this.cameraPlane = new Vector2(-this.dir.y, this.dir.x);
		this.focalPoint = new Vector2(this.pos.x + (this.dir.x * Tilemap.TILE_UNIT), this.pos.y + (this.dir.y * Tilemap.TILE_UNIT));

		this.isMovingRight = false;
		this.isMovingLeft = false;
		this.isMovingDown = false;
		this.isMovingUp = false;
		this.isRotatingLeft = false;
		this.isRotatingRight = false;
	}

	private void updateCamera() {
		this.cameraPlane.set(-this.dir.y, this.dir.x);
		this.focalPoint.set(this.pos.x + (this.dir.x * Tilemap.TILE_UNIT), this.pos.y + (this.dir.y * Tilemap.TILE_UNIT));
	}

	private void updateInputs() {
		if (this.isMovingDown) {
			this.pos.sub(this.dir.x * 2f, this.dir.y * 2f);
		}
		if (this.isMovingUp) {
			this.pos.add(this.dir.x * 2f, this.dir.y * 2f);
		}
		if (this.isMovingLeft) {
			this.pos.add(this.dir.y * 2f, -this.dir.x * 2f);
		}
		if (this.isMovingRight) {
			this.pos.add(-this.dir.y * 2f, this.dir.x * 2f);
		}
		if (this.isRotatingRight) {
			this.dir.rotateDeg(-2f);
		}
		if (this.isRotatingLeft) {
			this.dir.rotateDeg(2f);
		}
	}

	public void render(ShapeRenderer shapeRenderer) {
		this.updateCamera();
		this.updateInputs();

		shapeRenderer.setColor(Color.GREEN);
		shapeRenderer.circle(this.pos.x, this.pos.y, this.radius, 50);

		// render line from player to mouse
		shapeRenderer.setColor(Color.WHITE);
		shapeRenderer.rectLine(this.pos, this.mouse, 2);

		// render direction vector
		shapeRenderer.setColor(Color.PURPLE);
		shapeRenderer.rectLine(
				this.pos.x,
				this.pos.y,
				this.focalPoint.x,
				this.focalPoint.y,
				2);

		// render camera plane
		shapeRenderer.setColor(Color.BLUE);
		shapeRenderer.rectLine(
				this.focalPoint.x,
				this.focalPoint.y,
				this.focalPoint.x - (this.cameraPlane.x * Tilemap.TILE_UNIT),
				this.focalPoint.y - (this.cameraPlane.y * Tilemap.TILE_UNIT),
				2);
		shapeRenderer.rectLine(
				this.focalPoint.x,
				this.focalPoint.y,
				this.focalPoint.x + (this.cameraPlane.x * Tilemap.TILE_UNIT),
				this.focalPoint.y + (this.cameraPlane.y * Tilemap.TILE_UNIT),
				2);

		// render FOV guides
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.rectLine(
				this.pos.x,
				this.pos.y,
				this.focalPoint.x - (this.cameraPlane.x * Tilemap.TILE_UNIT),
				this.focalPoint.y - (this.cameraPlane.y * Tilemap.TILE_UNIT),
				2);
		shapeRenderer.rectLine(
				this.pos.x,
				this.pos.y,
				this.focalPoint.x + (this.cameraPlane.x * Tilemap.TILE_UNIT),
				this.focalPoint.y + (this.cameraPlane.y * Tilemap.TILE_UNIT),
		 		2);
	}

	public Vector2 getMouseVector() { return this.mouse; }

	public Vector2 getDirectionVector() { return this.dir; }

	public Vector2 getPositionVector() { return this.pos; }

	public void setIsMovingUp(boolean val) { this.isMovingUp = val; }
	
	public void setIsMovingDown(boolean val) { this.isMovingDown = val; }
	
	public void setIsMovingRight(boolean val) {
		this.isMovingRight = val;
	}
	
	public void setIsMovingLeft(boolean val) { this.isMovingLeft = val; }

	public void setIsRotatingRight(boolean val) { this.isRotatingRight = val; }

	public void setIsRotatingLeft(boolean val) { this.isRotatingLeft = val; }

	public void setMouse(float x, float y) { this.mouse.set(x, y); }

	public void setPosition(float x, float y) { this.pos.set(x, y); }

	public void setDirection(float x, float y) { this.dir.set(x, y); }

	public String toString() {
		return "Position: " + this.pos
				+ "\nDirection: " + this.dir
				+ "\nMouse: " + this.mouse;
	}
}
