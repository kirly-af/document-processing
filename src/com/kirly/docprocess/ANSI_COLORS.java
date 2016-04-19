package com.kirly.docprocess;

/**
 * Created by kirly on 19-Apr-16.
 */
public enum ANSI_COLORS {
    RESET   ("\u001B[0m"),
    BLACK   ("\u001B[30m"),
    RED     ("\u001B[31m"),
    GREEN   ("\u001B[32m"),
    YELLOW  ("\u001B[33m"),
    BLUE    ("\u001B[34m"),
    PURPLE  ("\u001B[35m"),
    CYAN    ("\u001B[36m"),
    WHITE   ("\u001B[37m");

    final String val;
    ANSI_COLORS(String color) {
        this.val = color;
    }

    @Override
    public String toString() {
        return this.val;
    }
}
