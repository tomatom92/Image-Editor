package com.mygdx.utility;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.image_editor.ImageEditor;

import java.io.IOException;

public class InputManager implements InputProcessor {
    public Array<IClickable> clickables = new Array<IClickable>();
    public Array<IHoverable> hoverables = new Array<IHoverable>();
    public static InputManager Instance;
    private IHoverable _currentlyHovered;
    private IClickable _currentlyClicked;
    private boolean _controlPressed;



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
        if(collision != _currentlyHovered) _currentlyClicked = null;
        _currentlyHovered = collision;

        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(ImageInputOutput.Instance.imageFolderLocation == null){return false;};
        if(_controlPressed && keycode == Keys.S)
            try {
            	System.out.println();
                ImageInputOutput.Instance.saveImage(ImageInputOutput.Instance.imageFolderLocation + "\\output.bmp");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        if(keycode == Keys.CONTROL_LEFT) _controlPressed = true;
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Keys.CONTROL_LEFT) _controlPressed = false;
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
