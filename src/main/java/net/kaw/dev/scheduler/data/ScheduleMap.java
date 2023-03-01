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
package net.kaw.dev.scheduler.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.kaw.dev.scheduler.data.interfaces.IMappable;

/**
 *
 * @author Waffle#4265 on Discord
 */
public class ScheduleMap implements IMappable {

    private Cycle cycle;

    /*
     * A sequence that corresponds to the mapping matrix of half hours in the schedule,
     * the matrix is 5 rows and 28 columns, the first data corresponds to the first hour of Monday,
     * second, third until reaching 27, then continues the first hour on Tuesday, the second,....,
     * etc, then Wednesday and so on until Friday.
     */
    private final List<List<HalfHour>> scheduleMap;

    public static final int DAYS = 5, HALFHOURS = 28;

    protected ScheduleMap(HalfHour initValue) {
        this.scheduleMap = new ArrayList<>(DAYS);

        for (int i = 0; i < DAYS; i++) {
            scheduleMap.add(new ArrayList<>(HALFHOURS));
        }

        setAllMapValues(initValue);
    }

    protected ScheduleMap(List<List<HalfHour>> map) {
        this.scheduleMap = map;
    }

    private void setAllMapValues(HalfHour value) {
        for (List<HalfHour> day : scheduleMap) {
            day.clear();

            for (int i = 0; i < HALFHOURS; i++) {
                day.add(value);
            }
        }
    }

    public void setMapValue(int day, int halfhour, HalfHour value) {
        scheduleMap.get(day).set(halfhour, value);
    }

    public HalfHour getMapValue(int day, int halfhour) {
        return scheduleMap.get(day).get(halfhour);
    }

    public Cycle getCycle() {
        return cycle;
    }

    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }

    private Map<String, Object> toMapDay(int day) {
        Map<String, Object> map = new HashMap<>(HALFHOURS);

        map.put("0700", getMapValue(day, 0).toMap());
        map.put("0730", getMapValue(day, 1).toMap());
        map.put("0800", getMapValue(day, 2).toMap());
        map.put("0830", getMapValue(day, 3).toMap());
        map.put("0900", getMapValue(day, 4).toMap());
        map.put("0930", getMapValue(day, 5).toMap());
        map.put("1000", getMapValue(day, 6).toMap());
        map.put("1030", getMapValue(day, 7).toMap());
        map.put("1100", getMapValue(day, 8).toMap());
        map.put("1130", getMapValue(day, 9).toMap());
        map.put("1200", getMapValue(day, 10).toMap());
        map.put("1230", getMapValue(day, 11).toMap());
        map.put("1300", getMapValue(day, 12).toMap());
        map.put("1330", getMapValue(day, 13).toMap());
        map.put("1400", getMapValue(day, 14).toMap());
        map.put("1430", getMapValue(day, 15).toMap());
        map.put("1500", getMapValue(day, 16).toMap());
        map.put("1530", getMapValue(day, 17).toMap());
        map.put("1600", getMapValue(day, 18).toMap());
        map.put("1630", getMapValue(day, 19).toMap());
        map.put("1700", getMapValue(day, 20).toMap());
        map.put("1730", getMapValue(day, 21).toMap());
        map.put("1800", getMapValue(day, 22).toMap());
        map.put("1830", getMapValue(day, 23).toMap());
        map.put("1900", getMapValue(day, 24).toMap());
        map.put("1930", getMapValue(day, 25).toMap());
        map.put("2000", getMapValue(day, 26).toMap());
        map.put("2030", getMapValue(day, 27).toMap());

        return map;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>(DAYS);

        if (cycle != null) {
            map.put("cycle", cycle.toMap());
        }

        map.put("monday", toMapDay(0));
        map.put("tuesday", toMapDay(1));
        map.put("wednesday", toMapDay(2));
        map.put("thursday", toMapDay(3));
        map.put("friday", toMapDay(4));

        return map;
    }

    @Override
    public String toString() {
        return "ScheduleMap{" + "cycle=" + cycle + ", scheduleMap=" + scheduleMap + '}';
    }

}
