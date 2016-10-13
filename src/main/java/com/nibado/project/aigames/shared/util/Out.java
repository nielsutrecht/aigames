package com.nibado.project.aigames.shared.util;

public class Out {
    public static void write(String format, Object... params) {
        System.out.println(String.format(format, params));
    }

    public static void log(String format, Object... params) {
        System.err.println(String.format(format, params));
    }
}
