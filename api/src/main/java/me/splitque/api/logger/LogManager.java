package me.splitque.api.logger;

import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogManager {
    private static LogFile STANDART_LOG_FILE;
    private static LoggerSettings LOGGER_SETTINGS;
    private static SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
    private static boolean INITIALIZED = false;

    /* CONSTRUCTOR */
    public static void init(Path logsDirectory, LoggerSettings loggerSettings) {
        STANDART_LOG_FILE = new LogFile(logsDirectory);
        LOGGER_SETTINGS = loggerSettings;

        StandartConsoleStream standartConsoleStream = new StandartConsoleStream();
        ErrorConsoleStream errorConsoleStream = new ErrorConsoleStream();
        System.setOut(new PrintStream(standartConsoleStream, true));
        System.setErr(new PrintStream(errorConsoleStream, true));

        INITIALIZED = true;
    }

    /* METHODS */
    public static Logger getLogger(String name, LogFile logFile) {
        checkInit();
        return new Logger(logFile) {
            @Override
            public void message(String message) {
                sendLog(name, message, this.logFile);
            }

            @Override
            public void messagef(String message, boolean showMessage) {
                if (showMessage) sendLog(name, message, this.logFile);
            }

            @Override
            public void info(String message) {
                sendLog(name, Format.COLOR_BRIGHT_WHITE + "INFO: " + message, this.logFile);
            }

            @Override
            public void infof(String message, boolean showMessage) {
                if (showMessage) sendLog(name, Format.COLOR_BRIGHT_WHITE + "INFO: " + message, this.logFile);
            }

            @Override
            public void debug(String message) {
                sendLog(name, Format.COLOR_DARK_GRAY + "DEBUG: " + message, this.logFile);
            }

            @Override
            public void debugf(String message, boolean showMessage) {
                if (showMessage) sendLog(name, Format.COLOR_DARK_GRAY + "DEBUG: " + message, this.logFile);
            }

            @Override
            public void warn(String message) {
                sendLog(name, Format.COLOR_YELLOW + "WARN: " + message, this.logFile);
            }

            @Override
            public void warnf(String message, boolean showMessage) {
                if (showMessage) sendLog(name, Format.COLOR_YELLOW + "WARN: " + message, this.logFile);
            }

            @Override
            public void error(String message) {
                sendLog(name, Format.COLOR_RED + "ERROR: " + message, this.logFile);
            }

            @Override
            public void errorf(String message, boolean showMessage) {
                if (showMessage) sendLog(name, Format.COLOR_RED + "ERROR: " + message, this.logFile);
            }
        };
    }
    private static void sendLog(String name, String message, LogFile logFile) {
        checkInit();
        String toConsole;
        String toFile;
        if (name != null) {
            toConsole = LOGGER_SETTINGS.getTimeFormat() + "[" + TIME_FORMAT.format(new Date()) + "]" + LOGGER_SETTINGS.getNameFormat() + " (" + name + ") " + Format.COLOR_WHITE + message + Format.COLOR_WHITE;
            toFile = "[" + TIME_FORMAT.format(new Date()) + "] (" + name + ") " + message;
        } else {
            toConsole = LOGGER_SETTINGS.getTimeFormat() + "[" + TIME_FORMAT.format(new Date()) + "] " + Format.COLOR_WHITE + message + Format.COLOR_WHITE;
            toFile = "[" + TIME_FORMAT.format(new Date()) + "] " + message;
        }
        for (Format format : Format.values()) {
            toFile = toFile.replace(format.value, "");
        }
        System.console().writer().print(toConsole);
        System.console().writer().flush();
        logFile.saveLog(toFile);
    }
    private static void checkInit() {
        if (!INITIALIZED) throw new LoggerException("LogManager is not initialized!");
    }

    /* GETTER */
    public static LogFile getStandartLogFile() {
        return STANDART_LOG_FILE;
    }

    /* STANDART I/O */
    private static class StandartConsoleStream extends OutputStream {
        @Override
        public void write(int b) {
            sendLog(null, String.valueOf((char) b), getStandartLogFile());
        }

        @Override
        public void write(byte[] b, int off, int len) {
            sendLog(null, new String(b, off, len), getStandartLogFile());
        }
    }
    private static class ErrorConsoleStream extends OutputStream {
        @Override
        public void write(int b) {
            sendLog(null, Format.COLOR_RED + String.valueOf((char) b), getStandartLogFile());
        }

        @Override
        public void write(byte[] b, int off, int len) {
            sendLog(null, Format.COLOR_RED + new String(b, off, len), getStandartLogFile());
        }
    }
}
