package com.mygdx.image_editor;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;

public class Util {

    public static int bytesToInt(int[] bytes) {
        int result = 0;
        for(int i = 0; i < bytes.length; i++) {
            result += bytes[i] << (8 * i);
        }
        return result;
    }

    public static int[] unsignBytes(byte[] bytes) {
        int[] ints = new int[bytes.length];
        for(int i = 0; i < bytes.length; i++) {

            byte tempByte = bytes[i];
            if (tempByte >= 0) {
                ints[i] = tempByte;
            } else {
                int distance = bytes[i] + 129;
                ints[i] = distance + 127;
            }

        }
        return ints;
    }

    public static byte[] intToSignedBytes(int value) {
        byte[] result = new byte[4];
        for (int i = 0; i < 4; i++) {
            result[i] = (byte) (value >> (24 - 8 * i));
        }
        return result;
    }

    public static Pixmap scalePixmap(Pixmap map, Vector2 desiredSize) {
        Pixmap newMap = new Pixmap((int) desiredSize.x, (int) desiredSize.y, Pixmap.Format.RGBA8888);
        for(int targetX = 0; targetX < newMap.getWidth(); targetX ++) {
            for(int targetY = 0; targetY < newMap.getHeight(); targetY ++) {
                int sourceX = (int) (targetX / desiredSize.x * map.getWidth());
                int sourceY = (int) (targetY / desiredSize.y * map.getHeight());

                newMap.setColor(map.getPixel(sourceX, sourceY));
                newMap.drawPixel(targetX, targetY);
            }
        }

        return newMap;
    }
}
