/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.kaw.dev.scheduler.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.kaw.dev.scheduler.data.interfaces.ISeparable;

public class CsvManager {

    public static void createCSV(List<ISeparable> items, String filepath) {
        StringBuilder sb = new StringBuilder();
        for (ISeparable item : items) {
            sb.append(item.toCSV()).append("\n");
        }

        try {
            File file = new File(filepath);
            if (!file.exists()) {
                file.createNewFile();
            }

            Files.writeString(Paths.get(filepath), sb.toString(), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(CsvManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<String> asList(String filepath) throws FileNotFoundException {
        List<String> list = new ArrayList<>();

        try (final BufferedReader reader = new BufferedReader(new FileReader(new File(filepath)))) {
            String line = reader.readLine();

            while (line != null) {
                if (!line.isEmpty()) {
                    list.add(line);
                }
                // read next line
                line = reader.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(CsvManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

}
