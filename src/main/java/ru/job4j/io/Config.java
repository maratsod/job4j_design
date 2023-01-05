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
                    .filter(e -> !e.startsWith("#") && e.contains("="))
                    .map(e -> e.split("="))
                    .forEach(e -> values.put(e[0], e.length > 2
                            ? e[1].concat("=" + e[2]) : e[1]));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String value(String key) {
        if (values.get(key).isEmpty() || values.containsKey("")) {
            throw new IllegalArgumentException();
        }
        return values.get(key);
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