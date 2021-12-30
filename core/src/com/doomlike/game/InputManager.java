package com.doomlike.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputManager implements InputProcessor {
	private Player player;
	private Tilemap tilemap;
	
	public InputManager(Player player, Tilemap tilemap) {
		this.player = player;
		this.tilemap = tilemap;
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
		}
		
		return false;
	}
	
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		System.out.printf("Mouse: %dx%d\n", screenX, screenY);
		player.setMouseX(screenX);
		player.setMouseY(screenY);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		tilemap.toggleWall(screenX, screenY);
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}
}
