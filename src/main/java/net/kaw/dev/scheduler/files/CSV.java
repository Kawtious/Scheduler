/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.kaw.dev.scheduler.files;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.kaw.dev.scheduler.model.ISeparatable;

public class CSV {

    public static void createCSV(List<ISeparatable> items, Path path) {
        StringBuilder sb = new StringBuilder();
        for (ISeparatable item : items) {
            sb.append(item.toCSV()).append("\n");
        }

        try {
            Files.writeString(path, sb.toString(), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(CSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<String> asList(Path path) throws FileNotFoundException {
        List<String> list = new ArrayList<>();

        try (final BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            String line = reader.readLine();

            while (line != null) {
                if (!line.isEmpty()) {
                    list.add(line);
                }
                // read next line
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(CSV.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

}
