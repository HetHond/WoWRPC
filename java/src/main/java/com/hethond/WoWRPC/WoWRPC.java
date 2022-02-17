package com.hethond.WoWRPC;

import com.hethond.WoWRPC.data.Player;

import java.awt.*;

public class WoWRPC {
    private Robot robot;
    private Player player;

    boolean running = true;

    private boolean checkSafeArea() {
        boolean BottomRight = robot.getPixelColor(50, 50).getRGB() == 0x126745;
        boolean BottomLeft = robot.getPixelColor(0, 50).getRGB() == 0x126745;
        boolean TopRight = robot.getPixelColor(50, 0).getRGB() == 0x126745;
        return BottomRight && BottomLeft && TopRight;
    }

    private void start() {
        while (running) {
            if (!checkSafeArea()) continue;

            Color lenPixel = robot.getPixelColor(0, 0);
            int pixels = lenPixel.getRGB() & 0x0000ff;
            int bytesLeft = (lenPixel.getRGB() >> 8) & 0xffff00;


            int curX = 1, curY = 0, maxX = 45;

            int byteIndex = 0;
            byte[] bytes = new byte[bytesLeft];
            for (int i = 0; i < pixels; i++) {
                Color pixel = robot.getPixelColor(curX, curY);
                if (bytesLeft-- > 0) bytes[byteIndex++] = (byte) pixel.getRed();
                if (bytesLeft-- > 0) bytes[byteIndex++] = (byte) pixel.getGreen();
                if (bytesLeft-- > 0) bytes[byteIndex++] = (byte) pixel.getBlue();
            }
        }
    }

    public WoWRPC() {

    }
}
