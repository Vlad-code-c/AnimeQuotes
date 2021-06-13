package com.example.animequotes;

import java.util.Random;

public class PageProperties {
    private static final String[] darkColors = new String[] {
      "#55286f", "#384e6b", "#435772", "#1e503e", "#58782b", "#1a3a3d", "#214a4f", "#58355e", "#315263", "#1e7175", "#38686a"
    };

    private static final String[] lightColors = new String[] {
        "#c8b6ff", "#ffa69e", "#84dcc6", "#cdb4db", "#a2d2ff", "#ffd6a5", "#fdffb6", "#caffbf", "#a0c4ff"
    };

    public static String getDarkColor() {
        return getRandomColor(darkColors);
    }

    public static String getLightColor() {
        return getRandomColor(lightColors);
    }

    private static String getRandomColor(String[] collors) {
        Random rand = new Random();
        return collors[rand.nextInt(collors.length)];
    }

}
