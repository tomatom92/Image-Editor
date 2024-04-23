package com.mygdx.image_editor;

import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Outline{
	public Texture OutlineTex;
	public Outline(Vector2 recSize, Color black, int thickness) {
		Pixmap map = new Pixmap((int) recSize.x, (int) recSize.y, Format.RGBA8888);
		map.setColor(Color.BLACK);
		for(int x = 0; x < map.getWidth(); x++) {
			for(int y = 0; y < thickness; y++) {
				map.drawPixel(x, y);
			}
		}
		//right
		for(int x = map.getWidth()-thickness; x < map.getWidth(); x++) {
			for(int y = 0; y < map.getHeight(); y++) {
				map.drawPixel(x, y);
			}
		}
		//bottom
		for(int x = 0; x < map.getWidth(); x++) {
			for(int y = map.getHeight()-thickness; y < map.getHeight(); y++) {
				map.drawPixel(x, y);
			}
		}
		//left
		for(int x = 0; x < thickness; x++) {
			for(int y = 0; y < map.getHeight(); y++) {
				map.drawPixel(x, y);
			}
		}
		OutlineTex = new Texture(map);
	}
	
}
