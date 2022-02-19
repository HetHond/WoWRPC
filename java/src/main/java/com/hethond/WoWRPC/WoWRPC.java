package com.hethond.WoWRPC;

import com.hethond.WoWRPC.data.Player;
import com.hethond.WoWRPC.util.PixelDecoder;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

import java.awt.*;
import java.util.HexFormat;

public class WoWRPC {
    private Robot robot;
    private Player player;

    private PixelDecoder decoder;

    boolean running = true;

    private boolean checkSafeArea() {
        boolean TopLeft = (robot.getPixelColor(100, 0).getRGB() & 0xFFFFFF) == 0x126745;
        boolean TopRight = (robot.getPixelColor(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth(), 0).getRGB() == 0x126745);

        String hex = "0x" + HexFormat.of().toHexDigits(robot.getPixelColor(100, 0).getRGB());
        System.out.println(hex);
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
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!checkSafeArea()) continue;
            System.out.println(System.nanoTime());

            this.decoder.readPixels();

            updatePresence();
        }
        destroyPresence();
    }

    public WoWRPC() {
        try {
            this.robot = new Robot();
            this.decoder = new PixelDecoder(robot);
            start();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
