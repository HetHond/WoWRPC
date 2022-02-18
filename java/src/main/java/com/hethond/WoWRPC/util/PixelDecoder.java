package com.hethond.WoWRPC.util;

import java.awt.*;

public class PixelDecoder {
    private Robot robot;

    public PixelDecoder(Robot robot) { this.robot = robot; }

    public byte nextPixel() {

    }

    public int curByteIndex = 0;
    public void readPixels() {
        this.curByteIndex = 0;
    }

    public String readString(byte[] bytes) {
    }
}
