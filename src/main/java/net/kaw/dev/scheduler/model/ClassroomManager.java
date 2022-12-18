/*
 * MIT License
 * 
 * Copyright (c) 2022 Kawtious
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
package net.kaw.dev.scheduler.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.kaw.dev.scheduler.files.Hex;
import net.kaw.dev.scheduler.testing.RunTests;

public class ClassroomManager {

    public static void create(Path filepath, List<Classroom> classrooms) {
        try {
            StringBuilder sb = new StringBuilder();

            // define amount of classes
            int classroomCount = 9;
            sb.append(Hex.intToHex(classroomCount, 8));

            for (Classroom classroom : classrooms) {
                sb.append(classroom.toHex());
            }

            save(filepath, sb.toString());
        } catch (IOException ex) {
            Logger.getLogger(RunTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get a hexadecimal string from a file
     *
     * @param string filepath
     * @return a char[] containing files binary data in hexadecimal
     * @throws IOException If file, given by filepath, cannot be read.
     * @see Hex#bytesToHex( byte[] )
     */
    private String open(Path filepath) throws IOException {
        File file = filepath.toFile();
        byte[] file_bytes = new byte[(int) file.length()];
        try (final FileInputStream fis = new FileInputStream(file)) {
            int bytes_read;
            do {
                bytes_read = fis.read(file_bytes);
            } while (bytes_read != -1);

            return new String(Hex.bytesToHex(file_bytes));
        }
    }

    /**
     * Saves the content of a string to a file.
     *
     * @param filepath The file path to save to
     * @throws FileNotFoundException If file cannot be written due to unexpected condition.
     */
    public static void save(Path filepath, String hexString) throws IOException, FileNotFoundException {
        byte[] file_hex_bytes = Hex.hexToBytes(hexString.toCharArray());
        FileOutputStream file_save = new FileOutputStream(filepath.toFile());
        file_save.write(file_hex_bytes);
    }

    public static List<Classroom> getDummyClassrooms() {
        List<Classroom> classrooms = new ArrayList<>();

        Classroom cr1 = new Classroom("L6");
        Classroom cr2 = new Classroom("L4");
        Classroom cr3 = new Classroom("L2");
        Classroom cr4 = new Classroom("AV");
        Classroom cr5 = new Classroom("AC");
        Classroom cr6 = new Classroom("LC");
        Classroom cr7 = new Classroom("L1");
        Classroom cr8 = new Classroom("L3");
        Classroom cr9 = new Classroom("L5");

        cr1.setAll(1);
        cr2.setAll(1);
        cr3.setAll(1);
        cr4.setAll(30);
        cr5.setAll(3);
        cr6.setAll(2);
        cr7.setAll(1);
        cr8.setAll(1);
        cr9.setAll(1);

        classrooms.add(cr1);
        classrooms.add(cr2);
        classrooms.add(cr3);
        classrooms.add(cr4);
        classrooms.add(cr5);
        classrooms.add(cr6);
        classrooms.add(cr7);
        classrooms.add(cr8);
        classrooms.add(cr9);

        return classrooms;
    }

}
