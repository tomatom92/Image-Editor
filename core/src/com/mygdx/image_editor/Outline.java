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
		OutlineTex = new Texture(map);
		//UNFINISHED, i typed out all the loops and put the code he put in but its not drawing the outlines
		//Top
		for(int x = 0; x < map.getWidth(); x++) {
			for(int y = 0; y < thickness; y++) {
				map.drawPixel(x, y);
			}
		}
		//Bottom
			for(int x = 0; x < map.getWidth(); x++) {
				for(int y = 0; y < thickness; y++) {
					map.drawPixel(x, y);
				}
			}
		//Right
		for(int x = 0; x < map.getWidth(); x++) {
			for(int y = 0; y < thickness; y++) {
				map.drawPixel(x, y);
			}
		}
		//Left
			for(int x = 0; x < map.getWidth(); x++) {
				for(int y = 0; y < thickness; y++) {
					map.drawPixel(x, y);
				}
			}	
	}
	
}
