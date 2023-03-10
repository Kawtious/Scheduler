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
 * Contains the variants of the schedule types that exist
 */
public class ScheduleType implements IMappable, ISeparable {

    public static final String ID_KEY = "id";

    public static final String DESCRIPTION_KEY = "description";

    public static final String AVAILABLE_HOURS_KEY = "availableHours";

    public static final String SESSION_MASK_KEY = "sessionMask";

    private String id;

    /*
     * Represents the name to describe the type of schedule
     */
    private String description;

    /*
     * Indicates in hexadecimal all the possible hours at which
     * you can start that session (and therefore the number of variants
     * that this type of schedule has)
     */
    private String availableHours;

    /*
     * The ???half-hour??? sessionMask that occupies this type of schedule
     */
    private String sessionMask;

    public ScheduleType(String description, String availableHours, String sessionMask) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.availableHours = availableHours;
        this.sessionMask = sessionMask;
    }

    public ScheduleType(String id, String description, String availableHours, String sessionMask) {
        this.id = id;
        this.description = description;
        this.availableHours = availableHours;
        this.sessionMask = sessionMask;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvailableHours() {
        return availableHours;
    }

    public void setAvailableHours(String availableHours) {
        this.availableHours = availableHours;
    }

    public String getSessionMask() {
        return sessionMask;
    }

    public void setSessionMask(String sessionMask) {
        this.sessionMask = sessionMask;
    }

    @Override
    public String toCsv() {
        return description + "," + availableHours + "," + sessionMask;
    }

    @Override
    public boolean validate() {
        return !description.isEmpty() && !availableHours.isEmpty() && !sessionMask.isEmpty();
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>(3);

        map.put(ID_KEY, id);
        map.put(DESCRIPTION_KEY, description);
        map.put(AVAILABLE_HOURS_KEY, availableHours);
        map.put(SESSION_MASK_KEY, sessionMask);

        return map;
    }

    @Override
    public String toString() {
        return "ScheduleType{" + "id=" + id + ", description=" + description + ", availableHours=" + availableHours + ", sessionMask=" + sessionMask + '}';
    }

}
