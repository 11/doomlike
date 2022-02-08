package com.doomlike.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.ScreenUtils;

public class DoomLike extends ApplicationAdapter {
	final static int TOP_DOWN_MODE = 1;
	final static int FIRST_PERSON_MODE = 2;
	int renderMode = TOP_DOWN_MODE;

	OrthographicCamera camera;
	ShapeRenderer shapeRenderer;
	Player player;
	Tilemap tilemap;

	@Override
	public void create() {
		player = new Player();

		camera = new OrthographicCamera();
		camera.setToOrtho(true);

		tilemap = new Tilemap();

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);

		Gdx.input.setInputProcessor(new InputManager(player, tilemap, this));
	}

	@Override
	public void render() {
		ScreenUtils.clear(0, 0, 0, 1);
		camera.update();

		switch (renderMode) {
			case TOP_DOWN_MODE: {
				renderTopDown();
				break;
			}
			case FIRST_PERSON_MODE: {
				renderFirstPerson();
				break;
			}
		}
	}

	private void renderTopDown() {
		shapeRenderer.setProjectionMatrix(camera.combined);
		// tilemap.raycast(player.getX(), player.getY(), player.getMouseX(), player.getMouseY());

		shapeRenderer.begin(ShapeType.Line);
		tilemap.renderLines(shapeRenderer);
		shapeRenderer.end();

		shapeRenderer.begin(ShapeType.Filled);
		tilemap.render(shapeRenderer);
		player.render(shapeRenderer);
		shapeRenderer.end();
	}

	private void renderFirstPerson() {
		// TODO:
	}

	@Override
	public void dispose() {
		shapeRenderer.dispose();
	}

	public void setRenderMode(int renderMode) {
		this.renderMode = renderMode;
	}
}
