package me.splitque.api.logger;

public enum Format {
    BOLD("\033[1m"),
    STANDART("\033[2m"),
    ITALIC("\033[3m"),
    UNDERLINE("\033[4m"),
    BOLD_UNDERLINE("\033[21m"),
    STRIKETHROUGH("\033[9m"),
    CUBE("\033[52m"),
    COLOR_BRIGHT_WHITE("\033[97m"),
    COLOR_BRIGHT_CYAN("\033[96m"),
    COLOR_BRIGHT_GREEN("\033[92m"),
    COLOR_BRIGHT_RED("\033[91m"),
    COLOR_LIGHT_GRAY("\033[37m"),
    COLOR_WHITE("\033[39m"),
    COLOR_BLACK("\u001B[30m"),
    COLOR_RED("\033[31m"),
    COLOR_GREEN("\u001B[32m"),
    COLOR_BLUE("\u001B[34m"),
    COLOR_PURPLE("\u001B[35m"),
    COLOR_CYAN("\u001B[36m"),
    COLOR_PINK("\033[95m"),
    COLOR_YELLOW("\033[93m"),
    COLOR_DARK_YELLOW("\033[33m"),
    COLOR_DARK_GRAY("\033[90m"),
    BACKGROUND_BRIGHT_RED("\033[101m"),
    BACKGROUND_BRIGHT_GREEN("\033[102m"),
    BACKGROUND_BRIGHT_BLUE("\033[104m"),
    BACKGROUND_BRIGHT_CYAN("\033[106m"),
    BACKGROUND_LIGHT_GRAY("\033[7m"),
    BACKGROUND_WHITE("\033[107m"),
    BACKGROUND_BLACK("\033[40m"),
    BACKGROUND_GRAY("\033[47m"),
    BACKGROUND_RED("\033[41m"),
    BACKGROUND_BLUE("\033[44m"),
    BACKGROUND_PURPLE("\033[45m"),
    BACKGROUND_YELLOW("\033[103m"),
    BACKGROUND_PINK("\033[105m"),
    BACKGROUND_DARK_CYAN("\033[46m"),
    BACKGROUND_DARK_YELLOW("\033[43m"),
    BACKGROUND_DARK_GRAY("\033[100m"),
    BACKGROUND_DARK_GREEN("\033[42m");

    public final String value;

    Format(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
