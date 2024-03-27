package com.mygdx.image_editor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Rec2D {
    public Vector2 Scale;
    public Vector2 Position;
    public Texture RecTexture;
    public Vector2 Velocity;
    private Pixmap _pixelMap;
    private Color _recColor;

    public Rec2D(Vector2 scale, Vector2 position, Vector2 velocity, Color _recColor) {
        Scale = scale;
        Position = position;
        Velocity = velocity;
        this._recColor = _recColor;
        generateTexture();
    }

    public void generateTexture () {
        _pixelMap = new Pixmap(200, 100, Pixmap.Format.RGBA8888);
        _pixelMap.setColor(_recColor);
        for (int x = 0; x < _pixelMap.getWidth(); x++) {
            for (int y = 0; y < _pixelMap.getHeight(); y++) {
                _pixelMap.drawPixel(x, y);
            }
        }
        RecTexture = new Texture(_pixelMap);
    }

    public void changeColor (Color newColor) {
        _recColor = newColor;
        generateTexture();
    }

    public int getWidth() {
        return _pixelMap.getWidth();
    }

    public int getHeight() {
        return _pixelMap.getHeight();
    }
}
