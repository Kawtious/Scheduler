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
package net.kaw.dev.scheduler.model;

/**
 *
 * @author Waffle#4265 on Discord
 */
public class Career implements ISeparatable {

    private String id;

    private String subjectKey;

    private int trajectory_1;

    private int trajectory_2;

    public Career(String id, String subjectKey, int trajectory_1, int trajectory_2) {
        this.id = id;
        this.subjectKey = subjectKey;
        this.trajectory_1 = trajectory_1;
        this.trajectory_2 = trajectory_2;
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

    public int getTrajectory_1() {
        return trajectory_1;
    }

    public void setTrajectory_1(int trajectory_1) {
        this.trajectory_1 = trajectory_1;
    }

    public int getTrajectory_2() {
        return trajectory_2;
    }

    public void setTrajectory_2(int trajectory_2) {
        this.trajectory_2 = trajectory_2;
    }

    public static Career fromCSV(String csv) {
        if (csv.isEmpty()) {
            return null;
        }

        String[] values = csv.split(",");

        int fields = 4;

        if (values.length != fields) {
            return null;
        }

        String id = values[0];
        String subjectKey = values[1];
        int trajectory_1 = -1;
        int trajectory_2 = -1;

        try {
            trajectory_1 = Integer.parseInt(values[0]);
        } catch (NumberFormatException e) {
        }

        try {
            trajectory_2 = Integer.parseInt(values[1]);
        } catch (NumberFormatException e) {
        }

        Career career = new Career(id, subjectKey, trajectory_1, trajectory_2);

        if (validate(career)) {
            return career;
        }

        return null;
    }

    public static boolean validate(Career career) {
        return !career.getId().isEmpty() && !career.getSubjectKey().isEmpty()
                && career.getTrajectory_1() >= 0 && career.getTrajectory_2() >= -1;
    }

    @Override
    public String toCSV() {
        return id + "," + subjectKey + "," + trajectory_1 + "," + trajectory_2;
    }

    @Override
    public String toString() {
        return "Career{" + "id=" + id + ", subjectKey=" + subjectKey + ", trajectory_1=" + trajectory_1 + ", trajectory_2=" + trajectory_2 + '}';
    }

}
