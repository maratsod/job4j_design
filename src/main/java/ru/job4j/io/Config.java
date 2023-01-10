package ru.job4j.io;

import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
             in.lines()
                     .filter(e -> !e.startsWith("#") && !e.isEmpty())
                     .filter(this::validate)
                     .map(e -> e.split("=", 2))
                     .forEach(e -> values.put(e[0], e[1]));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String value(String key) {
        return values.getOrDefault(key, null);
    }

    private boolean validate(String line) {
        if (line.startsWith("=") || line.endsWith("=")) {
            throw new IllegalArgumentException();
        } if (!line.contains("=") || line.length() == 1) {
            throw new IllegalArgumentException();
        } return true;
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}