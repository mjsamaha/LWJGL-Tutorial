package com.mjsamaha.lwjgltutorial;

/**
 * Very simple logger utility supporting SLF4J-style "{}" placeholders.
 */
public class Logger {

    private Logger() {
        // Utility class
    }

    public static void info(String msg, Object... args) {
        log("INFO", msg, args);
    }

    public static void warn(String msg, Object... args) {
        log("WARN", msg, args);
    }

    public static void error(String msg, Object... args) {
        log("ERROR", msg, args);
    }

    public static void debug(String msg, Object... args) {
        log("DEBUG", msg, args);
    }

    private static void log(String level, String msg, Object... args) {
        StringBuilder sb = new StringBuilder();
        int argIndex = 0;
        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            if (c == '{' && i + 1 < msg.length() && msg.charAt(i + 1) == '}') {
                if (argIndex < args.length) {
                    Object arg = args[argIndex++];
                    if (arg instanceof Throwable) {
                        sb.append(arg.toString());
                    } else {
                        sb.append(arg);
                    }
                } else {
                    sb.append("{}");
                }
                i++;
            } else {
                sb.append(c);
            }
        }

        String formatted = "[" + level + "] " + sb;
        if (level.equals("ERROR")) {
            System.err.println(formatted);
        } else {
            System.out.println(formatted);
        }

        // If the last argument is a Throwable, print its stack trace.
        if (args.length > 0 && args[args.length - 1] instanceof Throwable) {
            ((Throwable) args[args.length - 1]).printStackTrace();
        }
    }
}
