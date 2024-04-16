package com.mygdx.image_editor;

import com.badlogic.gdx.Gdx;

public class ImageInputOutput {
    public static ImageInputOutput Instance;
    public ImageInputOutput() {
        Instance = this;
    }



    public void loadImage(String filePath) {
        byte[] bytes = Gdx.files.internal(filePath).readBytes();
        if(bytes[0] != 'B' || bytes[1] != 'M') {
            System.out.println(filePath + " is NOT a bitmap image");
            return;
        }
        //System.out.println("Loading file of size " + bytes.length);
    }
}
    
