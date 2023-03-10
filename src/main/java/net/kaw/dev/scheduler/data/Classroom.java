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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.kaw.dev.scheduler.data.interfaces.IHexable;
import net.kaw.dev.scheduler.data.interfaces.IMappable;
import net.kaw.dev.scheduler.utils.HexUtils;

/**
 * Contains information about the types of classrooms that exist and their schedule availability.
 */
public class Classroom implements IMappable, IHexable {

    public static final String ID_KEY = "id";

    public static final String TYPE_KEY = "type";

    public static final String SCHEDULE_MAP_KEY = "scheduleMap";

    private String id;

    /*
     * This represents the classroom type key for identification
     */
    private String type;

    /*
     * A sequence of integers that correspond to the mapping matrix of half hours in the schedule,
     * the matrix is 5 rows and 28 columns, the first data corresponds to the first hour of Monday,
     * second, third until reaching 27, then continues the first hour on Tuesday, the second,...., etc,
     * then Wednesday and so on until Friday. Each integer represents the number of available classrooms
     * of that type in that ???half hour???.
     */
    private final ScheduleMap scheduleMap;

    public Classroom(String type) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.scheduleMap = new ScheduleMap(new HalfHour(0));
    }

    public Classroom(String type, ScheduleMap scheduleMap) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.scheduleMap = scheduleMap;
    }

    public Classroom(String type, Integer valueToMap) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.scheduleMap = new ScheduleMap(new HalfHour(valueToMap));
    }

    public Classroom(String id, String type) {
        this.id = id;
        this.type = type;
        this.scheduleMap = new ScheduleMap(new HalfHour(0));
    }

    public Classroom(String id, String type, ScheduleMap scheduleMap) {
        this.id = id;
        this.type = type;
        this.scheduleMap = scheduleMap;
    }

    public Classroom(String id, String type, Integer valueToMap) {
        this.id = id;
        this.type = type;
        this.scheduleMap = new ScheduleMap(new HalfHour(valueToMap));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ScheduleMap getScheduleMap() {
        return scheduleMap;
    }

    @Override
    public String toHex() {
        StringBuilder sb = new StringBuilder();
        sb.append(HexUtils.stringToHex(type, 8));

        for (int day = 0; day < ScheduleMap.DAYS; day++) {
            for (int halfhour = 0; halfhour < ScheduleMap.HALFHOURS; halfhour++) {
                sb.append(HexUtils.intToHex(scheduleMap.getMapValue(day, halfhour).getAvailable(), 8));
            }
        }
        return sb.toString();
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>(4);

        map.put(ID_KEY, id);
        map.put(TYPE_KEY, type);
        map.put(SCHEDULE_MAP_KEY, scheduleMap.toMap());

        return map;
    }

    @Override
    public String toString() {
        return "Classroom{" + "id=" + id + ", type=" + type + ", scheduleMap=" + scheduleMap + '}';
    }

}
