package com.mygdx.image_editor;

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

}
