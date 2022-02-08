package com.doomlike.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Tilemap {
	public static final int TILE_UNIT = 32;
	public static final Vector2 TILE_UNIT_VECTOR = new Vector2(Tilemap.TILE_UNIT, Tilemap.TILE_UNIT);

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

	public void raycast(Vector2 origin, Vector2 dest) {
		// final float yDiff = originX - destX;
		// final float xDiff = originY - destY;

		// final float xDir = xDiff < 0 ? -1 : 1;
		// final float yDir = yDiff < 0 ? -1 : 1;

		// final float RAY_LENGTH = (float) Math.sqrt((yDiff * yDiff) + (xDiff * xDiff));
		// final float xStepLength = (float) Math.sqrt(1 + ((yDiff/xDiff) * (yDiff/xDiff)));
		// final float yStepLength = (float) Math.sqrt(1 + ((xDiff/yDiff) * (xDiff/yDiff)));

		// int tileX = -1;
		// int tileY = -1;

		// int xRaySteps = 0;
		// int yRaySteps = 0;
		// while (Math.abs(xRaySteps) < RAY_LENGTH && Math.abs(yRaySteps) < RAY_LENGTH) {
		// 	float xStep = xStepLength * xRaySteps;
		// 	float yStep = yStepLength * yRaySteps;
		// 	if (xStep <= yStep) {
		// 		xRaySteps += 1;
		// 	} else {
		// 		yRaySteps += 1;
		// 	}

		// 	tileY = (int) (originY + yStep) / TILE_UNIT;
		// 	tileX = (int) (originX + xStep) / TILE_UNIT;
		// 	// if (tilemap[tileY][tileX] == 1) {
		// 	// 	System.out.println("made it here");
		// 	// }
		// }

		// System.out.printf("%d %d\n", tileX, tileY);
	}
}
