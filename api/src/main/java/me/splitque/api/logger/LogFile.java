package me.splitque.api.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogFile {
    private static Path LOGS_DIR;
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private Path logPath;

    /* CONSTRUCTORS */
    private void init(String fileName, Path logsDir) {
       if (LOGS_DIR == null) LOGS_DIR = logsDir;
       for (int i = 0; true; i++) {
            this.logPath = Path.of(LOGS_DIR + "/" + fileName + "-" + i + ".log");
            if (!Files.exists(this.logPath)) {
                try {
                    if (!Files.exists(this.logPath.getParent())) Files.createDirectories(this.logPath.getParent());
                    Files.createFile(this.logPath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }
    /* FOR LOGMANAGER */
    LogFile(Path logsDir) {
        init(DATE_FORMAT.format(new Date()), logsDir);
    }
    /* FOR ALL */
    public LogFile(String fileName) {
        init(fileName, LOGS_DIR);
    }

    /* METHODS */
    public void saveLog(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logPath.toFile(), true))) {
            writer.write(message);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}