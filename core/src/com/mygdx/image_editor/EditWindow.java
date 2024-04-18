package com.mygdx.image_editor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class EditWindow extends Rec2D implements IClickable {
    private Vector2 _previousPaintPosition;
    public Texture DoodleTexture;
    public Pixmap DoodleMap;
    public static EditWindow Instance;
    public EditWindow(Vector2 scale, Vector2 position, Texture imageTex) {
        super(scale, position, Color.GRAY);
        RecTexture = imageTex;
        DoodleMap = new Pixmap((int) scale.x, (int) scale.y, Pixmap.Format.RGBA8888);
        DoodleMap.setColor(Color.ORANGE);
        DoodleTexture = new Texture(DoodleMap);
        InputManager.Instance.clickables.add(this);
        Instance = this;
    }

    public void onClickDown(Vector2 mousePosition) {
        if(_previousPaintPosition == null)
        _previousPaintPosition = new Vector2((mousePosition.x - this.Position.x), (mousePosition.y - (ImageEditor.Instance.ScreenSize.y - ImageEditor.Instance.editWindow.Scale.y)));
        paintAtPosition(mousePosition);
    }

    @Override
    public void onClickUp(Vector2 mousePosition) {
        _previousPaintPosition = null;
        
    }

    @Override
    public void onClickDragged(Vector2 mousePosition) {
       paintAtPosition(mousePosition);
    }

    private void paintAtPosition(Vector2 mousePosition) {
        Vector2 paintPosition = new Vector2((int) (mousePosition.x - this.Position.x),(int) (mousePosition.y - (ImageEditor.Instance.ScreenSize.y - ImageEditor.Instance.editWindow.Scale.y)));
       //_doodleMap.drawLine((int)_previousPaintPosition.x,  (int) _previousPaintPosition.y, (int)paintPosition.x, (int)paintPosition.y);
       int startX = (int) _previousPaintPosition.x;
       int startY = (int)_previousPaintPosition.y;
       int endX = (int) paintPosition.x;
       int endY = (int) paintPosition.y;
       DoodleMap.drawLine(startX, startY, endX, endY);
       DoodleMap.drawLine(startX + 1, startY, endX + 1, endY);
       DoodleMap.drawLine(startX - 1, startY, endX - 1, endY);
       DoodleMap.drawLine(startX + 1, startY + 1, endX, endY + 1);
       DoodleMap.drawLine(startX - 1, startY, endX - 1, endY - 1);

        //_doodleMap.drawPixel((int) (mousePosition.x - this.Position.x),(int) (mousePosition.y - (ImageEditor.Instance.ScreenSize.y - ImageEditor.Instance.editWindow.Scale.y)));
        DoodleTexture = new Texture(DoodleMap);
        _previousPaintPosition = paintPosition;
    }

}
