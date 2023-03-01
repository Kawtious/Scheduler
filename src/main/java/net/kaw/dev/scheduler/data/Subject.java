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
 * Contains information about a subject
 */
public class Subject implements IMappable, ISeparable {

    private String id;

    /*
     * The subject's key
     */
    private String subjectKey;

    /*
     * Represents the key of the type of classroom in which this subject is taught
     */
    private String classKey;

    /*
     * Represents a number in hexadecimal in which each bit represents a type of
     * schedule to which the subject can be assigned
     */
    private String schedule;

    /*
     * Description of the schedule
     */
    private String description;

    public Subject(String subjectKey, String classKey, String schedule, String description) {
        this.id = UUID.randomUUID().toString();
        this.subjectKey = subjectKey;
        this.classKey = classKey;
        this.schedule = schedule;
        this.description = description;
    }

    public Subject(String id, String subjectKey, String classKey, String schedule, String description) {
        this.id = id;
        this.subjectKey = subjectKey;
        this.classKey = classKey;
        this.schedule = schedule;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubjectKey() {
        return subjectKey;
    }

    public void setSubjectKey(String subjectKey) {
        this.subjectKey = subjectKey;
    }

    public String getClassKey() {
        return classKey;
    }

    public void setClassKey(String classKey) {
        this.classKey = classKey;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toCsv() {
        return subjectKey + "," + classKey + "," + schedule + "," + description;
    }

    @Override
    public boolean validate() {
        return !subjectKey.isEmpty() && !classKey.isEmpty() && !schedule.isEmpty() && !description.isEmpty();
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>(3);

        map.put("id", id);
        map.put("subjectKey", subjectKey);
        map.put("classKey", classKey);
        map.put("schedule", schedule);
        map.put("description", description);

        return map;
    }

    @Override
    public String toString() {
        return "Subject{" + "id=" + id + ", subjectKey=" + subjectKey + ", classKey=" + classKey + ", schedule=" + schedule + ", description=" + description + '}';
    }

}
