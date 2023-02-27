/*
 * MIT License
 * 
 * Copyright (c) 2023 Kawtious
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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
import net.kaw.dev.scheduler.data.SeparableFactory;
import net.kaw.dev.scheduler.data.SeparableFactory.SeparableType;
import net.kaw.dev.scheduler.data.interfaces.ISeparable;

public class CsvManager {

    public static void write(List<ISeparable> items, String filepath) {
        try {
            String csv = convertToCsv(items);

            File file = new File(filepath);
            if (!file.exists()) {
                file.createNewFile();
            }

            Files.writeString(Paths.get(filepath), csv, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(CsvManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<ISeparable> read(String filepath, SeparableType separableType) {
        List<ISeparable> objects = new ArrayList<>();

        try {
            List<String> lines = CsvManager.getFileLines(filepath);
            convertToSeparables(lines, separableType);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CsvManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return objects;
    }

    private static List<String> getFileLines(String filepath) throws FileNotFoundException {
        List<String> lines = new ArrayList<>();

        try (final BufferedReader reader = new BufferedReader(new FileReader(new File(filepath)))) {
            String line = reader.readLine();

            while (line != null) {
                if (!line.isEmpty()) {
                    lines.add(line);
                }
                // read next line
                line = reader.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(CsvManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lines;
    }

    private static List<ISeparable> convertToSeparables(List<String> list, SeparableType separableType) {
        List<ISeparable> objects = new ArrayList<>();

        for (String string : list) {
            ISeparable item = SeparableFactory.build(separableType, string);

            if (item != null) {
                objects.add(item);
            }
        }

        return objects;
    }

    private static String convertToCsv(List<ISeparable> items) {
        StringBuilder sb = new StringBuilder();

        for (ISeparable item : items) {
            sb.append(item.toCsv()).append("\n");
        }

        return sb.toString();
    }

}
