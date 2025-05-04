package me.splitque.api.configuration;

import me.splitque.api.utils.IOUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Configuration {
    private ConfigurationData configurationData;
    private String configName;
    private Path configPath;

    public Configuration(String configName, Path configPath) {
        configurationData = new ConfigurationData();
        this.configName = configName;
        this.configPath = Paths.get(configPath + "/" + this.configName);
    }

    public void load(Class clazz) {
        try {
            if (!Files.exists(configPath)) {
                BufferedReader resourceReader = IOUtils.getResourceFile(clazz, configName);
                configurationData.setConfigData(resourceReader);
            } else {
                BufferedReader resourceReader = IOUtils.getResourceFile(clazz, configName);
                BufferedReader fileReader = new BufferedReader(new FileReader(configPath.toFile()));
                configurationData.setConfigData(fileReader, resourceReader);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void save() {
        configurationData.saveConfigData(configPath);
    }

    public String getString(String key) {
        return configurationData.getOption(key).toString();
    }
    public Integer getInteger(String key) {
        return Integer.parseInt(configurationData.getOption(key).toString());
    }
    public Double getDouble(String key) {
        return Double.parseDouble(configurationData.getOption(key).toString());
    }
    public Boolean getBoolean(String key) {
        return Boolean.parseBoolean(configurationData.getOption(key).toString());
    }
    public Long getLong(String key) {
        return Long.parseLong(configurationData.getOption(key).toString());
    }
}