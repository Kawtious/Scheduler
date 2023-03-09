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

public class Career implements IMappable, ISeparable {

    public static final String ID_KEY = "id";

    public static final String KEY_KEY = "key";

    public static final String SUBJECT_KEY_KEY = "subjectKey";

    public static final String TRAJECTORY_START_KEY = "trajectoryStart";

    public static final String TRAJECTORY_END_KEY = "trajectoryEnd";

    private String id;

    /*
     * Career semester identifier
     */
    private String key;

    /*
     * The subject's key
     */
    private String subjectKey;

    private int trajectoryStart;

    private int trajectoryEnd;

    public Career(String key, String subjectKey, int trajectoryStart, int trajectoryEnd) {
        this.id = UUID.randomUUID().toString();
        this.key = key;
        this.subjectKey = subjectKey;
        this.trajectoryStart = trajectoryStart;
        this.trajectoryEnd = trajectoryEnd;
    }

    public Career(String key, Subject subject, int trajectoryStart, int trajectoryEnd) {
        this.id = UUID.randomUUID().toString();
        this.key = key;
        this.subjectKey = subject.getSubjectKey();
        this.trajectoryStart = trajectoryStart;
        this.trajectoryEnd = trajectoryEnd;
    }

    public Career(String id, String key, String subjectKey, int trajectoryStart, int trajectoryEnd) {
        this.id = id;
        this.key = key;
        this.subjectKey = subjectKey;
        this.trajectoryStart = trajectoryStart;
        this.trajectoryEnd = trajectoryEnd;
    }

    public Career(String id, String key, Subject subject, int trajectoryStart, int trajectoryEnd) {
        this.id = id;
        this.key = key;
        this.subjectKey = subject.getSubjectKey();
        this.trajectoryStart = trajectoryStart;
        this.trajectoryEnd = trajectoryEnd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSubjectKey() {
        return subjectKey;
    }

    public void setSubjectKey(String subjectKey) {
        this.subjectKey = subjectKey;
    }

    public int getTrajectoryStart() {
        return trajectoryStart;
    }

    public void setTrajectoryStart(int trajectoryStart) {
        this.trajectoryStart = trajectoryStart;
    }

    public int getTrajectoryEnd() {
        return trajectoryEnd;
    }

    public void setTrajectoryEnd(int trajectoryEnd) {
        this.trajectoryEnd = trajectoryEnd;
    }

    @Override
    public String toCsv() {
        return key + "," + subjectKey + "," + trajectoryStart + "," + trajectoryEnd;
    }

    @Override
    public boolean validate() {
        return !key.isEmpty() && !subjectKey.isEmpty() && trajectoryStart >= 0 && trajectoryEnd >= -1;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>(4);

        map.put(ID_KEY, id);
        map.put(KEY_KEY, key);
        map.put(SUBJECT_KEY_KEY, subjectKey);
        map.put(TRAJECTORY_START_KEY, trajectoryStart);
        map.put(TRAJECTORY_END_KEY, trajectoryEnd);

        return map;
    }

    @Override
    public String toString() {
        return "Career{" + "id=" + id + ", key=" + key + ", subjectKey=" + subjectKey + ", trajectoryStart=" + trajectoryStart + ", trajectoryEnd=" + trajectoryEnd + '}';
    }

}
