package com.linseb9.game.util;

public class TerminalFormatting {
    // ANSI escape codes
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public String getReset() {
        return RESET;
    }

    public String getBlack() {
        return BLACK;
    }

    public String getRed() {
        return RED;
    }

    public String getGreen() {
        return GREEN;
    }

    public String getYellow() {
        return YELLOW;
    }

    public String getBlue() {
        return BLUE;
    }

    public String getPurple() {
        return PURPLE;
    }

    public String getCyan() {
        return CYAN;
    }

    public String getWhite() {
        return WHITE;
    }

    public static void main(String[] args) {
        System.out.println(RED + "This text is red!" + RESET);
        System.out.println(GREEN + "This text is green!" + RESET);
        System.out.println(YELLOW + "This text is yellow!" + RESET);
    }
}