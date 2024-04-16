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
        byte[] fileSize = {bytes[2], bytes[3], bytes[4], bytes[5]};
        System.out.println("The size of the file is " +
        fileSize[0] + " " + fileSize[1] + " " + fileSize[2] + " " + fileSize[3]);
        //System.out.println("Loading file of size " + bytes.length);
    }
}
    
