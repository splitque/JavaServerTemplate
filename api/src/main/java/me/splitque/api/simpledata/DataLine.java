package me.splitque.api.simpledata;

import java.util.HashMap;
import java.util.Map;

public class DataLine {
    private final String KEY;
    private Map<String, Object> VALUES;

    public static DataLine loadFromFile(String line) {
        DataLine dataLine = null;
        String[] partsLine = line.split(":");
        for (String part : partsLine) {
            if (partsLine[0].equals(part)) dataLine = new DataLine(part);
            else {
                String[] valueParts = part.split("=");
                dataLine.setValue(valueParts[0], valueParts[1]);
            }
        }
        return dataLine;
    }
    public DataLine(String key) {
        this.KEY = key;
        VALUES = new HashMap<>();
    }

    public String getKey() {
        return KEY;
    }

    public void addValue(String id, Object value) {
        if (!VALUES.containsKey(id)) VALUES.put(id, value);
    }
    public void setValue(String id, Object value) {
        if (VALUES.containsKey(id)) VALUES.replace(id, value);
        else VALUES.put(id, value);
    }
    public Object getValue(String id) {
        return VALUES.get(id);
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder();
        for (String key : VALUES.keySet()) {
            line.append(key + "=" + VALUES.get(key));
            line.append(":");
        }
        return KEY + ":" + line;
    }
}
