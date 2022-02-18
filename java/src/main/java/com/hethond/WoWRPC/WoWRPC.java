package com.hethond.WoWRPC;

import com.hethond.WoWRPC.data.Player;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

import java.awt.*;

public class WoWRPC {
    private Robot robot;
    private Player player;

    boolean running = true;

    private boolean checkSafeArea() {
        boolean TopLeft = robot.getPixelColor(0, 0).getRGB() == 0x126745;
        boolean TopRight = robot.getPixelColor(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth(), 0).getRGB() == 0x126745;
        return TopLeft && TopRight;
    }

    private void initializePresence() {
        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler((user) -> {
            System.out.println("Welcome " + user.username + "#" + user.discriminator + "!");
        }).build();
        DiscordRPC.discordInitialize("943996046504243202", handlers, true);
    }

    private void destroyPresence() {
        DiscordRPC.discordShutdown();
    }

    private void updatePresence() {
        DiscordRPC.discordRunCallbacks();
    }

    private void start() {
        initializePresence();
        while (running) {
            if (!checkSafeArea()) continue;
            System.out.println(System.nanoTime());

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

            updatePresence();
        }
        destroyPresence();
    }

    public WoWRPC() {
        try {
            this.robot = new Robot();
            start();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
