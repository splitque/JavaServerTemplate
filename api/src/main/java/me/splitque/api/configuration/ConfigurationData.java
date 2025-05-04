package me.splitque.api.configuration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigurationData {
    private List<String> lines;
    private Map<String, Object> config;

    public ConfigurationData() {
        lines = new ArrayList<>();
        config = new HashMap<>();
    }

    public void setConfigData(BufferedReader fileReader, BufferedReader resourceReader) throws IOException {
        Map<String, Object> fileConfig = new HashMap<>();
        for (String line : fileReader.lines().toArray(String[]::new)) {
            if (line.startsWith("#") || line.isBlank()) return;
            String[] split = line.split("=");
            fileConfig.put(split[0], split[1]);
        }
        for (String line : resourceReader.lines().toArray(String[]::new)) {
            if (line.startsWith("#") || line.isBlank()) lines.add(line);
            String[] split = line.split("=");
            if (fileConfig.containsKey(split[0])) config.put(split[0], fileConfig.get(split[0]));
            else config.put(split[0], split[1]);
            lines.add(line);
        }

        fileReader.close();
        resourceReader.close();
    }
    public void setConfigData(BufferedReader resourceReader) throws IOException {
        for (String line : resourceReader.lines().toArray(String[]::new)) {
            if (line.startsWith("#") || line.isBlank()) lines.add(line);
            String[] split = line.split("=");
            config.put(split[0], split[1]);
            lines.add(line);

            resourceReader.close();
        }
    }
    public void saveConfigData(Path configPath) {
        try {
            if (Files.exists(configPath)) Files.delete(configPath);
            Files.createDirectories(configPath.getParent());
            Files.createFile(configPath);
            BufferedWriter writer = Files.newBufferedWriter(configPath);
            for (String line : lines) {
                writer.write(line + "\n");
                writer.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Object getOption(String key) {
        return config.get(key);
    }
    public void setOption(String key, Object value) {
        if (config.containsKey(key)) config.replace(key, value);
        else config.put(key, value);
    }

    public List<String> getAllLines() {
        return lines;
    }
}
