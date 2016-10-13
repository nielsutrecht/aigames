package com.nibado.project.aigames.shared.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private final String className;
    private Logger(String className) {
        this.className = className;
    }

    public void error(String format, Object... arguments) {
        Out.log(prefix('E') + format);
    }

    public void warn(String format, Object... arguments) {
        Out.log(prefix('W') + format);
    }

    public void info(String format, Object... arguments) {
        Out.log(prefix('I') + format);
    }

    private String prefix(char level) {
        return LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME) + " " + level + " [" + className + "] ";
    }

    public static Logger logger(Class<?> clazz) {
        return new Logger(clazz.getSimpleName());
    }
}
