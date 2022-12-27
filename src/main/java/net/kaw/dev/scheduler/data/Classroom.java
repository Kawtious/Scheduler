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
package net.kaw.dev.scheduler.data;

import net.kaw.dev.scheduler.data.interfaces.IHexable;
import net.kaw.dev.scheduler.utils.HexUtils;

/**
 * Contains information about the types of classrooms that exist and their schedule availability.
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
    private final ScheduleMap<Integer> scheduleMap;

    public Classroom(String type) {
        this.type = type;
        this.scheduleMap = new ScheduleMap<>(0);
    }

    public Classroom(String type, int valueToMap) {
        this.type = type;
        this.scheduleMap = new ScheduleMap<>(valueToMap);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ScheduleMap<Integer> getScheduleMap() {
        return scheduleMap;
    }

    @Override
    public String toHex() {
        StringBuilder sb = new StringBuilder();
        sb.append(HexUtils.stringToHex(type, 8));

        for (int day = 0; day < ScheduleMap.DAYS; day++) {
            for (int halfhour = 0; halfhour < ScheduleMap.HALFHOURS; halfhour++) {
                sb.append(HexUtils.intToHex(scheduleMap.getMapValue(day, halfhour), 8));
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Classroom{" + "type=" + type + ", scheduleMap=" + scheduleMap + '}';
    }

}
