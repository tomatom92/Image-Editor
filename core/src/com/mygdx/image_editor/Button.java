package com.mygdx.image_editor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;



public class Button extends Rec2D{
    private Color _startColor;

    public enum ButtonState {Clicked, Hovered, None}
    private ButtonState _currentState;

    private static int _buttonCount;
    private int _buttonNumber;


    public Button(Vector2 scale, Vector2 position, Color recColor) {
        super(scale, position, recColor);
        _startColor = recColor;
        _buttonCount += 1;
        _buttonNumber = _buttonCount;
        InputManager.Instance.Buttons.add(this);
        _currentState = ButtonState.None;
    }

   
    public void onHovered() {
        if(_currentState == ButtonState.Clicked) return;
        _recColor = new Color(_startColor.r / 2f, _startColor.g / 2f , _startColor.b / 2f, 1);
        generateTexture();
        System.out.println("You're hovering over " + _buttonNumber);
        _currentState = ButtonState.Hovered;
    }
    public void onHoverExit() {
        _currentState = ButtonState.None;
        _recColor = _startColor;
        generateTexture();
    }   
    public void onClickUp(){
        _currentState = ButtonState.Hovered;
        _recColor = new Color(_startColor.r / 2f, _startColor.g / 2f , _startColor.b / 2f, 1);
        generateTexture();
    }
    public void onClickDown(){
        _currentState = ButtonState.Clicked;
        _recColor = new Color(_startColor.r / 4f, _startColor.g / 4f , _startColor.b / 4f, 1);
        generateTexture();
    }
    
}
