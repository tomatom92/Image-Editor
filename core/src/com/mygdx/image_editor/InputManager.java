package com.mygdx.image_editor;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class InputManager implements InputProcessor {
    public Array<IClickable> clickables = new Array<IClickable>();
    public Array<IHoverable> hoverables = new Array<IHoverable>();
    private IHoverable _currentlyHovered;
    private IClickable _currentlyClicked;
    public static InputManager Instance;

    public InputManager () {
        Instance = this;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        _currentlyClicked = CollisionManager.Instance.getClicked(
                new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
        if (_currentlyClicked != null) {
            _currentlyClicked.onClickDown(new Vector2(screenX, screenY));
        }
        return true;
    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        _currentlyClicked = CollisionManager.Instance.getClicked(
                new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
        if (_currentlyClicked != null) {
            _currentlyClicked.onClickUp(new Vector2(screenX, screenY));
        }
        return true;
    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        _currentlyClicked = CollisionManager.Instance.getClicked(
                new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
        if (_currentlyClicked != null) {
            _currentlyClicked.onClickDragged(new Vector2(screenX, screenY));
        }
        mouseMoved(screenX, screenY);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        IHoverable collision = CollisionManager.Instance.getHovered(
                new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));

        if (collision != _currentlyHovered && _currentlyHovered != null) _currentlyHovered.onHoverExit();
        if(collision != null) collision.onHovered();

        _currentlyHovered = collision;

        return true;
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
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
