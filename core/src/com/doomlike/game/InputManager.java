package com.doomlike.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputManager implements InputProcessor {
	private Player player;
	private Tilemap tilemap;
	private DoomLike renderer;

	private int previousTileX;
	private int previousTileY;
	
	public InputManager(Player player, Tilemap tilemap, DoomLike renderer) {
		this.player = player;
		this.tilemap = tilemap;
		this.renderer = renderer;

		this.previousTileX = -1;
		this.previousTileY = -1;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
			case Input.Keys.W: {
				player.setIsMovingUp(true);
				break;
			} 
			case Input.Keys.A: {
				player.setIsMovingLeft(true);
				break;
			}
			case Input.Keys.S: {
				player.setIsMovingDown(true);
				break;
			}
			case Input.Keys.D: {
				player.setIsMovingRight(true);
				break;
			}
			case Input.Keys.RIGHT: {
				player.setIsRotatingLeft(true);
				break;
			}
			case Input.Keys.LEFT: {
				player.setIsRotatingRight(true);
				break;
			}
		}
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
			case Input.Keys.W: {
				player.setIsMovingUp(false);
				break;
			} 
			case Input.Keys.A: {
				player.setIsMovingLeft(false);
				break;
			}
			case Input.Keys.S: {
				player.setIsMovingDown(false);
				break;
			}
			case Input.Keys.D: {
				player.setIsMovingRight(false);
				break;
			}
			case Input.Keys.RIGHT: {
				player.setIsRotatingLeft(false);
				break;
			}
			case Input.Keys.LEFT: {
				player.setIsRotatingRight(false);
				break;
			}
			case Input.Keys.NUM_1: {
				renderer.setRenderMode(DoomLike.TOP_DOWN_MODE);
				break;
			}
			case Input.Keys.NUM_2: {
				renderer.setRenderMode(DoomLike.FIRST_PERSON_MODE);
				break;
			}
		}
		
		return false;
	}
	
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		player.setMouse(screenX, screenY);
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return tilemap.toggleTile(screenX, screenY);
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		player.setMouse(screenX, screenY);

		final int tileX = screenX / Tilemap.TILE_UNIT;
		final int tileY = screenY / Tilemap.TILE_UNIT;
		if (tileX == this.previousTileX && tileY == this.previousTileY) {
			return false;
		}

		this.previousTileX = tileX;
		this.previousTileY = tileY;
		return tilemap.toggleTile(screenX, screenY);
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}
}
