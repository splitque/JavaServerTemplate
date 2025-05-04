package me.splitque.api.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

public class IOUtils {
    public static String getJarDir(Class clazz) {
        try {
            return new File(clazz.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    public static BufferedReader getResourceFile(Class clazz, String fileName) {
        return new BufferedReader(new InputStreamReader(clazz.getResourceAsStream(fileName)));
    }
}
