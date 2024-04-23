package com.mygdx.image_editor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.buttons.Button;
import com.mygdx.buttons.ClearDoodleButton;
import com.mygdx.buttons.ColorButton;
import com.mygdx.buttons.ExitButton;
import com.mygdx.buttons.SaveButton;
import com.mygdx.utility.CollisionManager;
import com.mygdx.utility.ImageInputOutput;
import com.mygdx.utility.InputManager;

import java.io.IOException;

public class ImageEditor extends ApplicationAdapter {
	SpriteBatch batch;

	Array<Rec2D> Rectangles = new Array<Rec2D>();
	EditWindow editWindow;

	public Vector2 ScreenSize;

	public static ImageEditor Instance;
	
	@Override
	public void create () {
		Instance = this;
		initializeUtilityClasses();
		createGraphicalElements();
		

	}
	private BitmapFont _font;
	
	private void initializeUtilityClasses() {
			new CollisionManager();
			new ImageInputOutput();
			InputManager inputManager = new InputManager();
			Gdx.input.setInputProcessor(inputManager);
			_font = new BitmapFont();
			
		}
	
	private void createGraphicalElements() {
		ScreenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Vector2 editWindowSize = new Vector2(500, ScreenSize.y - 25);
		//Buttons that change the color of drawing
		new ColorButton(new Vector2(42, 42), Vector2.Zero, Color.RED);
		new ColorButton(new Vector2(42, 42), new Vector2(42, 0), Color.ORANGE);
		new ColorButton(new Vector2(42, 42), new Vector2(42, 42), Color.YELLOW);
		new ColorButton(new Vector2(42, 42), new Vector2(0,42), Color.GREEN);
		new ColorButton(new Vector2(42, 42), new Vector2(42,84), Color.BLUE);
		new ColorButton(new Vector2(42, 42), new Vector2(0, 84), Color.PURPLE);
		new ColorButton(new Vector2(42, 42), new Vector2(0,126), Color.VIOLET);
		new ColorButton(new Vector2(42, 42), new Vector2(42, 126), Color.WHITE);
		new ColorButton(new Vector2(42, 42), new Vector2(0,168), Color.BLACK);
		new ColorButton(new Vector2(42, 42), new Vector2(42,168), Color.PINK);
		new ColorButton(new Vector2(42, 42), new Vector2(0,210), Color.CYAN);
		new ColorButton(new Vector2(42, 42), new Vector2(42,210), Color.GRAY);
		
		//save button
		new SaveButton(new Vector2(75,25), new Vector2(0, ScreenSize.y - 25), Color.GRAY);
		//exit button
		new ExitButton(new Vector2(75,25), new Vector2(0 + 77, ScreenSize.y - 25), Color.GRAY);
		//clear doodle button
		new ClearDoodleButton(new Vector2(75,25), new Vector2(0 + 77 + 77, ScreenSize.y - 25), Color.GRAY);
		
		batch = new SpriteBatch();
		editWindow = new EditWindow(editWindowSize, new Vector2(ScreenSize.x - editWindowSize.x, 0));
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
		for(int i = 0; i < Rectangles.size; i++) {
			rec = Rectangles.get(i);
			batch.draw(rec.Outline.OutlineTex, rec.Position.x, rec.Position.y, rec.Scale.x, rec.Scale.y);
			}
		batch.draw(editWindow.DoodleTexture, editWindow.Position.x, editWindow.Position.y, editWindow.Scale.x,
				editWindow.Scale.y);
		for(int i = 0; i < Rectangles.size; i++) {
			rec = Rectangles.get(i);
			if(rec instanceof Button) {
				Button button = (Button) rec;
				if(button.ButtonText == null) continue;
				_font.draw(batch, button.ButtonText, button.Position.x, button.Position.y + button.Scale.y * 0.75f,
				button.Scale.x, Align.center, false);
			}
		}
		batch.end();
	}

	public void filesImported (String[] filePaths) {
		Pixmap map = ImageInputOutput.Instance.loadImage(filePaths[0]);
		if (map == null) return;
		editWindow.RecTexture = new Texture(map);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
