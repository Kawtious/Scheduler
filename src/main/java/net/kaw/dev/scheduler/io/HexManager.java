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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.kaw.dev.scheduler.data.HexableFactory;
import net.kaw.dev.scheduler.data.HexableFactory.HexableType;
import net.kaw.dev.scheduler.data.interfaces.IHexable;
import net.kaw.dev.scheduler.utils.HexUtils;

public class HexManager {

    public static void write(List<IHexable> items, String filepath) {
        try {
            StringBuilder sb = new StringBuilder();

            // Store amount of items from the list
            int itemsCount = items.size();
            sb.append(HexUtils.intToHex(itemsCount, 8));

            for (IHexable item : items) {
                sb.append(item.toHex());
            }

            HexManager.writeToFile(filepath, sb.toString());
        } catch (IOException ex) {
            Logger.getLogger(HexManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<IHexable> read(String filepath, HexableType hexableType) {
        List<IHexable> objects = new ArrayList<>();

        try {
            String hexString = HexManager.readFile(filepath);
            objects = HexableFactory.build(hexableType, hexString);
        } catch (IOException ex) {
            Logger.getLogger(HexManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return objects;
    }

    /**
     * Get a hexadecimal string from a file
     *
     * @param string filepath
     * @return a char[] containing files binary data in hexadecimal
     * @throws IOException If file, given by filepath, cannot be read.
     * @see HexUtils#bytesToHex(byte[])
     */
    private static String readFile(String filepath) throws IOException {
        File file = new File(filepath);
        byte[] bytes = new byte[(int) file.length()];

        try (final FileInputStream fis = new FileInputStream(file)) {
            int bytesRead;

            do {
                bytesRead = fis.read(bytes);
            } while (bytesRead != -1);

            return new String(HexUtils.bytesToHex(bytes));
        }
    }

    /**
     * Saves the content of a string to a file.
     *
     * @param filepath The file path to write to
     * @throws FileNotFoundException If file cannot be written due to unexpected condition.
     */
    private static void writeToFile(String filepath, String hexString) throws IOException, FileNotFoundException {
        byte[] bytes = HexUtils.hexToBytes(hexString.toCharArray());

        try (final FileOutputStream fos = new FileOutputStream(new File(filepath))) {
            fos.write(bytes);
        }
    }

}
