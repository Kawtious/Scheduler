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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Waffle#4265 on Discord
 */
public class ScheduleMap<T> {

    /*
     * A sequence that corresponds to the mapping matrix of half hours in the schedule,
     * the matrix is 5 rows and 28 columns, the first data corresponds to the first hour of Monday,
     * second, third until reaching 27, then continues the first hour on Tuesday, the second,....,
     * etc, then Wednesday and so on until Friday.
     */
    private final List<List<T>> map;

    private final int DAYS = 5, HALFHOURS = 28;

    public ScheduleMap(T initValue) {
        this.map = new ArrayList<>(DAYS);

        for (int i = 0; i < DAYS; i++) {
            map.add(new ArrayList<>(HALFHOURS));
        }

        setAllMapValues(initValue);
    }

    public final void setAllMapValues(T value) {
        for (List<T> day : map) {
            day.clear();

            for (int i = 0; i < HALFHOURS; i++) {
                day.add(value);
            }
        }
    }

    public void setMapValue(int day, int halfhour, T value) {
        map.get(day).set(halfhour, value);
    }

    public T getMapValue(int day, int halfhour) {
        return map.get(day).get(halfhour);
    }

    @Override
    public String toString() {
        return "ScheduleMap{" + "map=" + map + '}';
    }

}
