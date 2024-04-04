package com.mygdx.image_editor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class ImageEditor extends ApplicationAdapter {
	SpriteBatch batch;
	Rec2D button1;
	Rec2D button2;
	Rec2D button3;
	Rec2D button4;
	Rec2D button5;

	Array<Rec2D> Rectangles = new Array<Rec2D>();

	public Vector2 ScreenSize;

	public static ImageEditor Instance;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		ScreenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Instance = this;

		InputManager inputManager = new InputManager();
		Gdx.input.setInputProcessor(inputManager);

		CollisionManager collisionManager = new CollisionManager();

		Vector2 rectangleScale = new Vector2(100, 100);
		button1 = new Button(
				rectangleScale,
				new Vector2(ScreenSize.x / 4f - rectangleScale.x /2f , ScreenSize.y * 0.75f - rectangleScale.y / 2f),
				Color.ORANGE
		);
		button2 = new Button(
				rectangleScale,
				new Vector2(ScreenSize.x / 4f - rectangleScale.x /2f, ScreenSize.y / 4f - rectangleScale.y / 2f),
				Color.GREEN);
		button3 = new Button(
				rectangleScale,
				new Vector2(ScreenSize.x / 2f - rectangleScale.x / 2f, ScreenSize.y / 2f - rectangleScale.y / 2f),
				Color.WHITE);
		button4 = new Button(
				rectangleScale,
				new Vector2(ScreenSize.x * 0.75f - rectangleScale.x /2f, ScreenSize.y *0.75f - rectangleScale.y / 2f),
				Color.BLUE);
		button5 = new Button(
				rectangleScale,
				new Vector2(ScreenSize.x *0.75f - rectangleScale.x /2f, ScreenSize.y /4f - rectangleScale.y / 2f),
				Color.RED);


	}

	@Override
	public void render () {
		ScreenUtils.clear(0f, 0f, 0f, 1);
		batch.begin();

		Rec2D rec;
		for(int i = 0; i < Rectangles.size; i++) {
			rec = Rectangles.get(i);
			batch.draw(rec.RecTexture, rec.Position.x, rec.Position.y, rec.Scale.x, rec.Scale.y);
		}

		
		batch.draw(button1.RecTexture, button1.Position.x, button1.Position.y);
		batch.draw(button2.RecTexture, button2.Position.x, button2.Position.y);
		batch.draw(button3.RecTexture, button3.Position.x, button3.Position.y);
		batch.draw(button4.RecTexture, button4.Position.x, button4.Position.y);
		batch.draw(button5.RecTexture, button5.Position.x, button5.Position.y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
