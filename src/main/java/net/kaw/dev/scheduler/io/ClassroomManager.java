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
package net.kaw.dev.scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.kaw.dev.scheduler.data.hexable.Classroom;
import net.kaw.dev.scheduler.testing.RunTests;
import net.kaw.dev.scheduler.utils.HexUtils;

public class ClassroomManager {

    public static void create(List<Classroom> classrooms, String filepath) {
        try {
            StringBuilder sb = new StringBuilder();

            // define amount of classes
            int classroomCount = 9;
            sb.append(HexUtils.intToHex(classroomCount, 8));

            for (Classroom classroom : classrooms) {
                sb.append(classroom.toHex());
            }

            writeToFile(filepath, sb.toString());
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
     * @see HexUtils#bytesToHex(byte[])
     */
    private String readFile(String filepath) throws IOException {
        File file = new File(filepath);
        byte[] file_bytes = new byte[(int) file.length()];
        try (final FileInputStream fis = new FileInputStream(file)) {
            int bytes_read;
            do {
                bytes_read = fis.read(file_bytes);
            } while (bytes_read != -1);

            return new String(HexUtils.bytesToHex(file_bytes));
        }
    }

    /**
     * Saves the content of a string to a file.
     *
     * @param filepath The file path to writeToFile to
     * @throws FileNotFoundException If file cannot be written due to unexpected condition.
     */
    public static void writeToFile(String filepath, String hexString) throws IOException, FileNotFoundException {
        byte[] file_hex_bytes = HexUtils.hexToBytes(hexString.toCharArray());
        FileOutputStream file_save = new FileOutputStream(new File(filepath));
        file_save.write(file_hex_bytes);
    }

}
