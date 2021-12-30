package com.doomlike.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Tilemap {
	public static final int TILE_UNIT = 32;

	private int[][] tilemap = null;

	public Tilemap() {
		this.tilemap = this.createTilemap();
	}

	private int[][] createTilemap() {
		final int UNIT_WIDTH = Gdx.graphics.getWidth() / TILE_UNIT;
		final int UNIT_HEIGHT = Gdx.graphics.getHeight() / TILE_UNIT;

		int[][] tilemap = new int[UNIT_HEIGHT][UNIT_WIDTH];
		for (int i = 0; i < UNIT_HEIGHT; i++) {
			for (int j = 0; j < UNIT_WIDTH; j++) {
				tilemap[i][j] = 0;
			}
		}
		
		return tilemap;
	}
	
	public void renderLines(ShapeRenderer shapeRenderer) {
		final int UNIT_WIDTH = Gdx.graphics.getWidth() / TILE_UNIT;
		final int UNIT_HEIGHT = Gdx.graphics.getHeight() / TILE_UNIT;
		
		for (int i = 0; i < UNIT_WIDTH; i++) {
			final float xPos = i * TILE_UNIT;
			shapeRenderer.line(xPos, 0, xPos, Gdx.graphics.getHeight(), Color.WHITE, Color.WHITE);
		}

		for (int i = 0; i < UNIT_HEIGHT; i++) {
			final float yPos = i * TILE_UNIT;
			shapeRenderer.line(0, yPos, Gdx.graphics.getWidth(), yPos, Color.WHITE, Color.WHITE);
		}
	}

	public void render(ShapeRenderer shapeRenderer) {
		final int UNIT_WIDTH = Gdx.graphics.getWidth() / TILE_UNIT;
		final int UNIT_HEIGHT = Gdx.graphics.getHeight() / TILE_UNIT;
		
		for (int i = 0; i < UNIT_HEIGHT; i++) {
			for (int j = 0; j < UNIT_WIDTH; j++) {
				int tile = tilemap[i][j];
				if (tile == 1) {
					shapeRenderer.rect(j * TILE_UNIT+1, i*TILE_UNIT+1, TILE_UNIT-2, TILE_UNIT-2, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE);
				} 
			}
		}
	}

	public void toggleWall(int screenX, int screenY) {
		final int x = screenX / Tilemap.TILE_UNIT;
		final int y = screenY / Tilemap.TILE_UNIT;
		
		int tile = tilemap[y][x];
		if (tile == 1) {
			tilemap[y][x] = 0;
		} else if (tile == 0) {
			tilemap[y][x] = 1;
		}
	}

	public void raycast(float playerX, float playerY) {

	}
}
