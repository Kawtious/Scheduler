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

import net.kaw.dev.scheduler.data.interfaces.ISeparable;

public class Career implements ISeparable {

    /*
     * Career semester identifier
     */
    private String id;

    /*
     * The subject's key
     */
    private String subjectKey;

    private int trajectoryStart;

    private int trajectoryEnd;

    protected Career(String id, String subjectKey, int trajectoryStart, int trajectoryEnd) {
        this.id = id;
        this.subjectKey = subjectKey;
        this.trajectoryStart = trajectoryStart;
        this.trajectoryEnd = trajectoryEnd;
    }

    protected Career(String id, Subject subject, int trajectoryStart, int trajectoryEnd) {
        this.id = id;
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
        return id + "," + subjectKey + "," + trajectoryStart + "," + trajectoryEnd;
    }

    @Override
    public boolean validate() {
        return !id.isEmpty() && !subjectKey.isEmpty() && trajectoryStart >= 0 && trajectoryEnd >= -1;
    }

    @Override
    public String toString() {
        return "Career{" + "id=" + id + ", subjectKey=" + subjectKey + ", trajectoryStart=" + trajectoryStart + ", trajectoryEnd=" + trajectoryEnd + '}';
    }

}
