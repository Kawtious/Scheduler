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
package net.kaw.dev.scheduler.data.hexable;

import net.kaw.dev.scheduler.interfaces.IHexable;
import net.kaw.dev.scheduler.utils.HexUtils;

/**
 * Data file that contains information about the types of classrooms that exist and their schedule availability.
 * <p>
 * The file structure is:
 * <blockquote><pre>
 * An integer (int) that indicates the number of classroom types in the file, and then they appear as many times as that integer indicated by the following data structure:
 * Sequence of indefinite byte size ending in "0" that corresponds to the String that represents the key of a type of classroom to identify them;
 * sequence of indefinite byte size ending in "0" that represents the string that describes this type of classroom,
 * sequence of integers (int) that correspond to the mapping matrix of half hours in the schedule, the matrix is 5 lines long and 28 columns,
 * the first data corresponds to the first hour of Monday, second, third until reaching 27, then follows the first hour of Tuesday, second,...., etc,
 * then Wednesday and so on until Friday. Each integer represents the number of available classrooms of that type in that “half hour”.
 * </pre></blockquote>
 */
public class Classroom implements IHexable {

    /*
     * This represents the classroom type key for identification
     */
    private String type;

    /*
     * A sequence of integers that correspond to the mapping matrix of half hours in the schedule,
     * the matrix is 5 rows and 28 columns, the first data corresponds to the first hour of Monday,
     * second, third until reaching 27, then continues the first hour on Tuesday, the second,...., etc,
     * then Wednesday and so on until Friday. Each integer represents the number of available classrooms
     * of that type in that “half hour”.
     */
    private final int[][] halfHourMap = new int[5][28];

    public Classroom(String type) {
        this.type = type;
        setAllValuesInMap(0);
    }

    public Classroom(String type, int valueToMap) {
        this.type = type;
        setAllValuesInMap(valueToMap);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public final void setAllValuesInMap(int value) {
        for (int day = 0; day < 5; day++) {
            for (int halfhour = 0; halfhour < 28; halfhour++) {
                setValueInMap(day, halfhour, value);
            }
        }
    }

    public void setValueInMap(int day, int halfhour, int value) {
        halfHourMap[day][halfhour] = value;
    }

    @Override
    public String toHex() {
        StringBuilder sb = new StringBuilder();
        sb.append(HexUtils.stringToHex(type, 8));

        for (int day = 0; day < 5; day++) {
            for (int halfhour = 0; halfhour < 28; halfhour++) {
                sb.append(HexUtils.intToHex(halfHourMap[day][halfhour], 8));
            }
        }
        return sb.toString();
    }

}
