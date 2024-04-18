package com.mygdx.image_editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageInputOutput {
    public static ImageInputOutput Instance;
    public ImageInputOutput() {
        Instance = this;
    }
    private byte[] _fileHeader;
    private Pixmap _pixels;

    public Pixmap loadImage(String filePath) {
        byte[] ogBytes = Gdx.files.internal(filePath).readBytes();
        int[] bytes = Util.unsignBytes(ogBytes);
        if(bytes[0] != 'B' || bytes[1] != 'M') {
            System.out.println(filePath + " is NOT a bitmap image");
            return null;
        }
        int[] fileSize = {bytes[2], bytes[3], bytes[4], bytes[5]};

        int[] start = {bytes[10], bytes[11], bytes[12], bytes[13]};
        int[] widthBytes = {bytes[18], bytes[19], bytes[20], bytes[21]};
        int[] heightBytes = {bytes[22], bytes[23], bytes[24], bytes[25]};
        int[] bitsPerPixel = {bytes[28], bytes[29]};
        int startPoint = Util.bytesToInt(start);
        _fileHeader = new byte[startPoint]; // store file header for saving later
        for (int i = 0; i < startPoint; i++) {
            _fileHeader[i] = ogBytes[i];
        }
        int width = Util.bytesToInt(widthBytes);
        int height = Util.bytesToInt(heightBytes);
        int bytesPerPixel = Util.bytesToInt(bitsPerPixel) / 8;
        if(bytesPerPixel != 3) {System.out.println("Unsupported image pixel format. Incorrect bits per pixel"); return null;}

        Pixmap pixels = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        int r,g,b;
        int x = 0, y = height;
        for(int i = startPoint; i < bytes.length - 3; i += 3) {
            b = bytes[i];
            g = bytes[i+1];
            r = bytes[i+2];
            pixels.setColor(((float) (r))/256, ((float) (g))/256, ((float) (b))/256, 1f);
            pixels.drawPixel(x, y);
            x += 1;
            if (x >= width) {
                x = 0;
                y -= 1;
            }
        }
        _pixels = pixels;
        return pixels;
    }

    public void saveImage(String filePath) throws IOException {
        FileOutputStream output = new FileOutputStream(filePath);
        byte[] color;
        byte[] colorData = new byte[_pixels.getWidth() * _pixels.getHeight() * 3];
        int colorIndex = 0;

        for(int y = _pixels.getHeight() - 1; y >= 0; y--) {
            for(int x = 0; x < _pixels.getWidth(); x++) {
                color = Util.intToSignedBytes(_pixels.getPixel(x, y));
                colorData[colorIndex] = color[2];
                colorData[colorIndex + 1] = color[1];
                colorData[colorIndex + 2] = color[0];
                colorIndex += 3;
            }
        }

        Pixmap doodle = EditWindow.Instance.DoodleMap;
        colorIndex = 0;
        for(int y = doodle.getHeight() - 1; y >= 0; y--) {
            for(int x = 0; x < doodle.getWidth(); x++) {
                color = Util.intToSignedBytes(doodle.getPixel(x, y));
                if(color[3] != -1) {colorIndex += 3; continue; }
                colorData[colorIndex] = color[2];
                colorData[colorIndex + 1] = color[1];
                colorData[colorIndex + 2] = color[0];
                colorIndex += 3;
            }
        }

        output.write(_fileHeader);
        output.write(colorData);

        output.close();
    }
}
    
