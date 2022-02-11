package com.doomlike.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class Tilemap {
	public static final int TILE_UNIT = 32;
	public static final int TILE_MAP_WIDTH = Gdx.graphics.getWidth() / TILE_UNIT;
	public static final int TILE_MAP_HEIGHT = Gdx.graphics.getHeight() / TILE_UNIT;
	public static final Vector2 TILE_UNIT_VECTOR = new Vector2(Tilemap.TILE_UNIT, Tilemap.TILE_UNIT);

	private int[][] tilemap = null;

	public Tilemap() {
		this.tilemap = this.createTilemap();
	}

	private int[][] createTilemap() {
		int[][] tilemap = new int[TILE_MAP_HEIGHT][TILE_MAP_WIDTH];
		for (int i = 0; i < TILE_MAP_HEIGHT; i++) {
			for (int j = 0; j < TILE_MAP_WIDTH; j++) {
				tilemap[i][j] = 0;
			}
		}
		
		return tilemap;
	}
	
	public void renderLines(ShapeRenderer shapeRenderer) {
		for (int i = 0; i < TILE_MAP_WIDTH; i++) {
			final float xPos = i * TILE_UNIT;
			shapeRenderer.line(xPos, 0, xPos, Gdx.graphics.getHeight(), Color.WHITE, Color.WHITE);
		}

		for (int i = 0; i < TILE_MAP_HEIGHT; i++) {
			final float yPos = i * TILE_UNIT;
			shapeRenderer.line(0, yPos, Gdx.graphics.getWidth(), yPos, Color.WHITE, Color.WHITE);
		}
	}

	public void render(ShapeRenderer shapeRenderer) {
		for (int i = 0; i < TILE_MAP_HEIGHT; i++) {
			for (int j = 0; j < TILE_MAP_WIDTH; j++) {
				int tile = tilemap[i][j];
				if (tile == 1) {
					shapeRenderer.rect(j * TILE_UNIT+1, i*TILE_UNIT+1, TILE_UNIT-2, TILE_UNIT-2, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE);
				} 
			}
		}
	}

	public int getTile(int screenX, int screenY) {
		if (this.isOutOfBounds(screenX, screenY)) {
			return -1;
		}

		final int x = screenX / Tilemap.TILE_UNIT;
		final int y = screenY / Tilemap.TILE_UNIT;
		return this.tilemap[y][x];
	}

	public boolean setTile(int screenX, int screenY, int value) {
		if (this.isOutOfBounds(screenX, screenY)) {
			return false;
		}

		final int x = screenX / Tilemap.TILE_UNIT;
		final int y = screenY / Tilemap.TILE_UNIT;
		this.tilemap[y][x] = value;

		return true;
	}

	public boolean toggleTile(int screenX, int screenY) {
		int tile = this.getTile(screenX, screenY);
		switch (tile) {
			case -1: {
				return false;
			}
			case 0: {
				return this.setTile(screenX, screenY, 1);
			}
			case 1: {
				return this.setTile(screenX, screenY, 0);
			}
		}

		return false;
	}

	public boolean isCollision(int screenX, int screenY) {
		if (this.isOutOfBounds(screenX, screenY)) {
			return false;
		}

		int tile = this.getTile(screenX, screenY);
		return tile != 0;
	}

	public boolean isOutOfBounds(int screenX, int screenY) {
		final int tileX = screenX / Tilemap.TILE_UNIT;
		final int tileY = screenY / Tilemap.TILE_UNIT;
		return tileX < 0
			|| tileY < 0
			|| tileX > TILE_MAP_WIDTH
			|| tileY > TILE_MAP_HEIGHT;
	}

	public void raycast(Vector2 origin, Vector2 dest, ShapeRenderer shapeRenderer) {
		final float diffX = Math.abs(origin.x - dest.x);
		final float diffY = Math.abs(origin.y - dest.y);

		final int stepDirX = (dest.x < origin.x) ? -1 : 1;
		final int stepDirY = (dest.y < origin.y) ? -1 : 1;

		final Vector2 rayDir = new Vector2(diffX, diffY).nor().scl(TILE_UNIT);
		final Vector2 stepDir = new Vector2(stepDirX, stepDirY);
		Vector2 steps = new Vector2(1,1);

		boolean hit = false;

		int count = 0;
		int maxLength = 7;
		while (!hit && count < maxLength) {
			float dx = rayDir.x * steps.x * stepDir.x;
			float dy = rayDir.y * steps.y * stepDir.y;
			if (dx < dy || dx == dy) {
				steps.x += 1f;
			} else {
				steps.y += 1f;
			}

			float screenX = origin.x + dx;
			float screenY = origin.y + dy;

			if (this.isOutOfBounds((int) screenX, (int) screenY)) {
				break;
			}

			if (this.isCollision((int) screenX, (int) screenY)) {
				System.out.println("made it here");
				shapeRenderer.begin(ShapeType.Filled);
				shapeRenderer.setColor(Color.ORANGE);
				shapeRenderer.circle(screenX, screenY, 4);
				shapeRenderer.end();
				hit = true;
			}


			count++;
		}
	}
}
