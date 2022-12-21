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

import java.util.ArrayList;
import java.util.List;

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

    public static List<ISeparatable> getDummyCareers() {
        List<ISeparatable> list = new ArrayList<>(65);

        String[] values = {
            "IE3,????,1,2",
            "IE3,ECL1,2,3",
            "IE3,EF01,3,4",
            "IE3,EA09,4,-1",
            "IE5,ED03,1,2",
            "IE5,EDL3,2,3",
            "IE5,ET02,3,4",
            "IE5,ETL2,4,5",
            "IE5,EC03,5,6",
            "IE5,ECL3,6,-1",
            "IEC7,ED05,1,2",
            "IEC7,EDL5,2,3",
            "IEC7,EF02,3,4",
            "IEC7,EO04,4,5",
            "IEC7,EC05,5,6",
            "IEC7,ECL5,6,7",
            "IEC7,EC06,7,8",
            "IEC7,ECL6,8,-1",
            "IEC9,EO06,1,2",
            "IEC9,EO07,2,3",
            "IEC9,EC08,3,4",
            "IEC9,EC09,4,5",
            "IEC9,ECL9,5,6",
            "IEC9,EF04,6,-1",
            "IEIC7,ED05,1,2",
            "IEIC7,EDL5,2,3",
            "IEIC7,EF02,3,4",
            "IEIC7,EO04,4,5",
            "IEIC7,EAL2,5,6",
            "IEIC7,EM05,6,7",
            "IEIC7,EML5,7,8",
            "IEIC7,EC06,8,9",
            "IEIC7,ECL6,9,-1",
            "IEIC9,EO03,1,2",
            "IEIC9,EF04,2,3",
            "IEIC9,EAL6,3,4",
            "IEIC9,EO02,4,5",
            "IEIC9,EA06,5,6",
            "IEIC9,EA05,6,7",
            "IEIC9,EAL5,7,-1",
            "IEL3,EC01,1,2",
            "IEL3,ECL1,2,3",
            "IEL3,EF01,3,4",
            "IEL3,EA09,4,5",
            "IEL3,EI02,5,6",
            "IEL3,EIL2,6,-1",
            "IEL5,EM02,1,2",
            "IEL5,EML2,2,3",
            "IEL5,ET02,3,4",
            "IEL5,ETL2,4,5",
            "IEL5,EC02,5,6",
            "IEL5,EE03,6,7",
            "IEL5,EEL3,7,8",
            "IEL5,EI04,8,-1",
            "IEL7,EM04,1,2",
            "IEL7,EML4,2,3",
            "IEL7,EA03,3,4",
            "IEL7,EAL3,4,5",
            "IEL7,EP02,5,6",
            "IEL7,EO08,6,7",
            "IEL7,EP01,7,-1"};

        for (String value : values) {
            Career career = Career.fromCSV(value);

            if (career != null) {
                list.add(career);
            }
        }
        return list;
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
        int trajectory_2 = -2;

        try {
            trajectory_1 = Integer.parseInt(values[2]);
        } catch (NumberFormatException e) {
        }

        try {
            trajectory_2 = Integer.parseInt(values[3]);
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
