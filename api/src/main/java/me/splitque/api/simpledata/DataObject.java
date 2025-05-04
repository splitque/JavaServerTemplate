package me.splitque.api.simpledata;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DataObject {
    private final List<DataLine> DATA_LINES;
    private final String NAME;
    private final Path PATH;

    public DataObject(String name, Path path) {
        DATA_LINES = new ArrayList<>();
        NAME = name;
        PATH = path;
    }

    public void loadData() throws IOException {
        if (Files.exists(PATH)) {
            List<String> lines = Files.readAllLines(PATH);
            for (String line : lines) {
                DATA_LINES.add(DataLine.loadFromFile(line));
            }
        }
    }
    public void saveData() throws IOException {
        if (Files.exists(PATH)) Files.delete(PATH);
        if (!Files.exists(PATH.getParent())) Files.createDirectories(PATH.getParent());
        Files.createFile(PATH);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH.toFile()))) {
            for (DataLine dataLine : DATA_LINES) {
                writer.write(dataLine.toString());
                writer.newLine();
            }
            writer.flush();
        }
    }

    private DataLine getDataLine(String key) {
        for (DataLine dataLine : DATA_LINES) {
            if (dataLine.getKey().equals(key)) return dataLine;
        }
        return null;
    }
    private DataLine createDataLine(String key) {
        DataLine dataLine = new DataLine(key);
        DATA_LINES.add(dataLine);
        return dataLine;
    }

    public void addValue(String key, String id, Object value) {
        if (getDataLine(key) != null) {
            getDataLine(key).addValue(id, value);
        } else {
            createDataLine(key).addValue(id, value);
        }
    }
    public void setValue(String key, String id, Object value) {
        if (getDataLine(key) != null) {
            getDataLine(key).setValue(id, value);
        } else {
            createDataLine(key).setValue(id, value);
        }
    }
    public Object getValue(String key, String id) {
        if (getDataLine(key) == null) throw new NullPointerException("DataLine with this key=" + key + " not found!");
        return getDataLine(key).getValue(id);
    }

    public boolean isKeyExists(String key) {
        return getDataLine(key) != null;
    }
}
