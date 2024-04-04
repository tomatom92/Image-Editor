package com.mygdx.image_editor;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class InputManager implements InputProcessor {
    public Array<Button> Buttons = new Array<Button>();
    private Button _hoveredButton;
    public static InputManager Instance;

    public InputManager () {
        Instance = this;
    }
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Button collision = CollisionManager.Instance.getCollision(
                new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
        if (collision != null) {
            collision.onClickDown();
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Button collision = CollisionManager.Instance.getCollision(
            new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
        if (collision != null) {
            collision.onClickUp();
        }
    return true;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        
        mouseMoved(screenX, screenY);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Button collision = CollisionManager.Instance.getCollision(
            new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
            
        if (collision != _hoveredButton && _hoveredButton != null) _hoveredButton.onHoverExit();
        if(collision != null) collision.onHovered();

        _hoveredButton = collision;

        return true;


        
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
