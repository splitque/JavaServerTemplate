package me.splitque.api.logger;

public class LoggerSettings {
    private Format timeFormat;
    private Format nameFormat;

    private LoggerSettings() { }

    public Format getTimeFormat() {
        return timeFormat;
    }
    public Format getNameFormat() {
        return nameFormat;
    }

    public static Builder newBuilder() {
        return new LoggerSettings().new Builder();
    }

    public class Builder {
        private Builder() { }

        public Builder setTimeFormat(Format timeFormat) {
            LoggerSettings.this.timeFormat = timeFormat;
            return this;
        }
        public Builder setNameFormat(Format nameFormat) {
            LoggerSettings.this.nameFormat = nameFormat;
            return this;
        }
        public LoggerSettings build() {
            LoggerSettings loggerSettings = new LoggerSettings();
            loggerSettings.timeFormat = LoggerSettings.this.timeFormat;
            loggerSettings.nameFormat = LoggerSettings.this.nameFormat;
            return loggerSettings;
        }
    }
}
