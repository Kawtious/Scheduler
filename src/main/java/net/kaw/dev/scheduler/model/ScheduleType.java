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

public class ScheduleType implements ISeparatable {

    private String description;

    private String availableHours;

    private String mask;

    public ScheduleType(String description, String availableHours, String mask) {
        this.description = description;
        this.availableHours = availableHours;
        this.mask = mask;
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

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public static List<ISeparatable> getDummyScheduleTypes() {
        List<ISeparatable> list = new ArrayList<>(16);

        list.add(new ScheduleType("Lu(2.0)", "01110111h", "000001010101h"));
        list.add(new ScheduleType("Ma(2.0)", "01110111h", "000002020202h"));
        list.add(new ScheduleType("Mi(2.0)", "01110111h", "000004040404h"));
        list.add(new ScheduleType("Ju(2.0)", "01110111h", "000008080808h"));
        list.add(new ScheduleType("Vi(2.0)", "01110111h", "000010101010h"));
        list.add(new ScheduleType("Lu(3.0)", "00410041h", "010101010101h"));
        list.add(new ScheduleType("Ma(3.0)", "00410041h", "020202020202h"));
        list.add(new ScheduleType("Mi(3.0)", "00410041h", "040404040404h"));
        list.add(new ScheduleType("Ju(3.0)", "00410041h", "080808080808h"));
        list.add(new ScheduleType("Vi(3.0)", "00410041h", "101010101010h"));
        list.add(new ScheduleType("MaJu(1.5)", "024A024Ah", "0000000A0A0Ah"));
        list.add(new ScheduleType("LuMiVi(1.0)", "05550555h", "000000001515h"));
        list.add(new ScheduleType("LuMi(2.0)", "01110111h", "000005050505h"));
        list.add(new ScheduleType("MaJu(2.0)", "01110111h", "00000A0A0A0Ah"));
        list.add(new ScheduleType("LuMaMiJuVi(1.0)", "05550555h", "000000001F1Fh"));
        return list;
    }

    public static ScheduleType fromCSV(String csv) {
        if (csv.isEmpty()) {
            return null;
        }

        String[] values = csv.split(",");

        int fields = 3;

        if (values.length != fields) {
            return null;
        }

        String description = values[0];
        String availableHours = values[1];
        String mask = values[2];

        ScheduleType scheduleType = new ScheduleType(description, availableHours, mask);

        if (validate(scheduleType)) {
            return scheduleType;
        }

        return null;
    }

    public static boolean validate(ScheduleType scheduleType) {
        return !scheduleType.getDescription().isEmpty() && !scheduleType.getAvailableHours().isEmpty()
                && !scheduleType.getMask().isEmpty();
    }

    @Override
    public String toCSV() {
        return description + "," + availableHours + "," + mask;
    }

    @Override
    public String toString() {
        return "ScheduleType{" + "description=" + description + ", availableHours=" + availableHours + ", mask=" + mask + '}';
    }

}
