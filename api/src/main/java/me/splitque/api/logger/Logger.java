package me.splitque.api.logger;

public abstract class Logger {
    protected LogFile logFile;

    public Logger(LogFile logFile) {
        this.logFile = logFile;
    }

    public abstract void message(String message);
    public abstract void messagef(String message, boolean showMessage);
    public abstract void info(String message);
    public abstract void infof(String message, boolean showMessage);
    public abstract void debug(String message);
    public abstract void debugf(String message, boolean showMessage);
    public abstract void warn(String message);
    public abstract void warnf(String message, boolean showMessage);
    public abstract void error(String message);
    public abstract void errorf(String message, boolean showMessage);
}
