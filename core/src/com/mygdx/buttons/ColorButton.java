package com.mygdx.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.image_editor.EditWindow;

public class ColorButton extends Button{

	public ColorButton(Vector2 scale, Vector2 position, Color recColor) {
		super(scale, position, recColor);
		// TODO Auto-generated constructor stub
	}
	public void onClickUp(Vector2 mousePosition) {
		super.onClickUp(mousePosition);
		System.out.println("I've been clicked");
	}
	public void onClickDown(Vector2 mousePosition) {
		super.onClickDown(mousePosition);
		EditWindow.Instance.DrawColor = _startColor;
		EditWindow.Instance.DoodleMap.setColor(_startColor);
	}
	

}
