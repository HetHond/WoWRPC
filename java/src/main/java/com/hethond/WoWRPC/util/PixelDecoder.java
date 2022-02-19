package com.hethond.WoWRPC.util;

import java.awt.*;

public class PixelDecoder {
    private Robot robot;

    public PixelDecoder(Robot robot) { this.robot = robot; }

    public int pixelIndex;
    private Color curPixel;
    private byte bLeftInPixel;
    public byte nextPixel() {
        if (bLeftInPixel <= 0) {
            pixelIndex++;
            curPixel = robot.getPixelColor(pixelIndex, 0);
        }

        return (byte) ((curPixel.getRGB() & 0xFF) >> (8 * (bLeftInPixel-1)));
    }
    public void readPixels() {
        this.pixelIndex = 0;
        System.out.println(nextPixel());
    }
}
