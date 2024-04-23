package com.mygdx.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.image_editor.Rec2D;
import com.mygdx.utility.IClickable;
import com.mygdx.utility.IHoverable;
import com.mygdx.utility.InputManager;



public class Button extends Rec2D implements IClickable, IHoverable{
    protected Color _startColor;
    public String ButtonText;
    
    public enum ButtonState {Clicked, Hovered, None}
    private ButtonState _currentState;


    public Button(Vector2 scale, Vector2 position, Color recColor) {
        super(scale, position, recColor);
        _startColor = recColor;
        InputManager.Instance.hoverables.add(this);
        InputManager.Instance.clickables.add(this);
        _currentState = ButtonState.None;
    }

   
    public void onHovered() {
        if(_currentState == ButtonState.Clicked) return;
        _recColor = new Color(_startColor.r / 2f, _startColor.g / 2f , _startColor.b / 2f, 1);
        generateTexture();
        _currentState = ButtonState.Hovered;
    }
    public void onHoverExit() {
        _currentState = ButtonState.None;
        _recColor = _startColor;
        generateTexture();
    }   
    public void onClickUp(Vector2 mousePosition){
        _currentState = ButtonState.Hovered;
        _recColor = new Color(_startColor.r / 2f, _startColor.g / 2f , _startColor.b / 2f, 1);
        generateTexture();
    }
    public void onClickDown(Vector2 mousePosition){
        _currentState = ButtonState.Clicked;
        _recColor = new Color(_startColor.r / 4f, _startColor.g / 4f , _startColor.b / 4f, 1);
        generateTexture();
    }
    @Override
    public void onClickDragged(Vector2 mousePosition) {

    }

}
