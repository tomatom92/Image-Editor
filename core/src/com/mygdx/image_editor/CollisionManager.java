package com.mygdx.image_editor;

import com.badlogic.gdx.math.Vector2;

public class CollisionManager {
    public static CollisionManager Instance;

    public CollisionManager () {
        Instance = this;
    }

    public IHoverable getHovered (Vector2 coordinates) {
        Rec2D itrHoverable;
        for(int i = 0; i < InputManager.Instance.hoverables.size; i++) {
            itrHoverable = (Rec2D) InputManager.Instance.hoverables.get(i);
            if(coordinates.x > itrHoverable.Position.x && coordinates.x < itrHoverable.Position.x + itrHoverable.Scale.x) {
                if (coordinates.y > itrHoverable.Position.y && coordinates.y < itrHoverable.Position.y + itrHoverable.Scale.y) {
                    return (IHoverable) itrHoverable;
                }
            }
        }
        return null;
    }

    public IClickable getClicked (Vector2 coordinates) {
        Rec2D itrClickable;
        for(int i = 0; i < InputManager.Instance.clickables.size; i++) {
            itrClickable = (Rec2D) InputManager.Instance.clickables.get(i);
            if(coordinates.x > itrClickable.Position.x && coordinates.x < itrClickable.Position.x + itrClickable.Scale.x) {
                if (coordinates.y > itrClickable.Position.y && coordinates.y < itrClickable.Position.y + itrClickable.Scale.y) {
                    return (IClickable) itrClickable;
                }
            }
        }
        return null;
    }
}
