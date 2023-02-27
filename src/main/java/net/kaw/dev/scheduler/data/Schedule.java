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
import net.kaw.dev.scheduler.data.interfaces.IMappable;
import net.kaw.dev.scheduler.data.interfaces.ISeparable;

/**
 * It is used to define the types of schedules that are available as options
 */
public class Schedule implements IMappable, ISeparable {

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

    protected Schedule(int type, int offset, String sessionMask, String description) {
        this.type = type;
        this.offset = offset;
        this.sessionMask = sessionMask;
        this.description = description;
    }

    protected Schedule(int type, int offset, ScheduleType scheduleType) {
        this.type = type;
        this.offset = offset;
        this.sessionMask = scheduleType.getSessionMask();
        this.description = scheduleType.getDescription();
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

        map.put("type", type);
        map.put("offset", offset);
        map.put("sessionMask", sessionMask);
        map.put("description", description);

        return map;
    }

    @Override
    public String toString() {
        return "Schedule{" + "type=" + type + ", offset=" + offset + ", sessionMask=" + sessionMask + ", description=" + description + '}';
    }

}
