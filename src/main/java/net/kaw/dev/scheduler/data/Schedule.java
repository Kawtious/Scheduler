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
import net.kaw.dev.scheduler.data.interfaces.IMappable;
import net.kaw.dev.scheduler.data.interfaces.ISeparable;

/**
 * It is used to define the types of schedules that are available as options
 */
public class Schedule implements IMappable, ISeparable {

    public static final String ID_KEY = "id";

    public static final String TYPE_KEY = "type";

    public static final String OFFSET_KEY = "offset";

    public static final String SESSION_MASK_KEY = "sessionMask";

    public static final String DESCRIPTION_KEY = "description";

    private String id;

    /*
     * Schedule type number
     */
    private int type;

    /*
     * Offset that is given to this schedule in half hours from the
     * lowest possible hour where the schedule can be placed (7:00AM)
     */
    private int offset;

    /*
     * The schedule's session sessionMask
     */
    private String sessionMask;

    /*
     * The schedule's description
     */
    private String description;

    public Schedule(int type, int offset, String sessionMask, String description) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.offset = offset;
        this.sessionMask = sessionMask;
        this.description = description;
    }

    public Schedule(int type, int offset, ScheduleType scheduleType) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.offset = offset;
        this.sessionMask = scheduleType.getSessionMask();
        this.description = scheduleType.getDescription();
    }

    public Schedule(String id, int type, int offset, String sessionMask, String description) {
        this.id = id;
        this.type = type;
        this.offset = offset;
        this.sessionMask = sessionMask;
        this.description = description;
    }

    public Schedule(String id, int type, int offset, ScheduleType scheduleType) {
        this.id = id;
        this.type = type;
        this.offset = offset;
        this.sessionMask = scheduleType.getSessionMask();
        this.description = scheduleType.getDescription();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getSessionMask() {
        return sessionMask;
    }

    public void setSessionMask(String sessionMask) {
        this.sessionMask = sessionMask;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toCsv() {
        return type + "," + offset + "," + sessionMask + "," + description;
    }

    @Override
    public boolean validate() {
        return type >= 0 && offset >= 0 && !sessionMask.isEmpty() && !description.isEmpty();
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>(4);

        map.put(ID_KEY, id);
        map.put(TYPE_KEY, type);
        map.put(OFFSET_KEY, offset);
        map.put(SESSION_MASK_KEY, sessionMask);
        map.put(DESCRIPTION_KEY, description);

        return map;
    }

    @Override
    public String toString() {
        return "Schedule{" + "id=" + id + ", type=" + type + ", offset=" + offset + ", sessionMask=" + sessionMask + ", description=" + description + '}';
    }

}
