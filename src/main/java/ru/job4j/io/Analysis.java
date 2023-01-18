package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;

public class Analysis {
    public void unavailable(String source, String target) {
        ArrayList<String> rsl = new ArrayList<>() ;
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new FileOutputStream(target))) {
            in.lines().forEach(rsl::add);
            boolean check = false;
            String[] array;
            for (String str : rsl) {
                if (str.startsWith("400") || str.startsWith("500")) {
                    if (check) {
                        continue;
                    }
                    array = str.split(" ", 2);
                    writer.printf("%s", array[1]);
                    writer.printf(";");
                    check = true;
                } else if (check) {
                    array = str.split(" " , 2);
                    writer.printf("%s", array[1]);
                    writer.printf("%s%n", ";");
                    check = false;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}