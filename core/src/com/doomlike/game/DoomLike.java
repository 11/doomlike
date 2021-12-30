package com.doomlike.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.ScreenUtils;

public class DoomLike extends ApplicationAdapter {
	OrthographicCamera camera;
	ShapeRenderer shapeRenderer;
	Player player;
	Tilemap tilemap;

	@Override
	public void create() {
		final float playerX = Gdx.graphics.getWidth() / 2f;
		final float playerY = Gdx.graphics.getHeight() / 2f;
		final float radius = Tilemap.TILE_UNIT / 2f;
		player = new Player(playerX, playerY, radius);

		camera = new OrthographicCamera();
		camera.setToOrtho(true);
		
		tilemap = new Tilemap();

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);

		Gdx.input.setInputProcessor(new InputManager(player, tilemap));
	}

	@Override
	public void render() {
		ScreenUtils.clear(0, 0, 0, 1);
		camera.update();
		shapeRenderer.setProjectionMatrix(camera.combined);

		shapeRenderer.begin(ShapeType.Line);
		tilemap.renderLines(shapeRenderer);
		player.renderDirection(shapeRenderer);
		shapeRenderer.end();
		
		shapeRenderer.begin(ShapeType.Filled);
		tilemap.render(shapeRenderer);
		player.render(shapeRenderer);
		shapeRenderer.end();
	}

	@Override
	public void dispose() {
		shapeRenderer.dispose();
	}
}
