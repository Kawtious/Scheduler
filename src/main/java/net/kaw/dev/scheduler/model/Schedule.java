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

public class Schedule implements ISeparatable {

    private int type;

    private int offset;

    private String mask;

    private String description;

    public Schedule(int type, int offset, String mask, String description) {
        this.type = type;
        this.offset = offset;
        this.mask = mask;
        this.description = description;
    }

    public Schedule(int type, int offset, ScheduleType scheduleType) {
        this.type = type;
        this.offset = offset;
        this.mask = scheduleType.getMask();
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

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static List<ISeparatable> getDummySchedules() {
        List<ISeparatable> list = new ArrayList<>(100);

        List<ISeparatable> types = ScheduleType.getDummyScheduleTypes();

        ScheduleType lu2 = (ScheduleType) types.get(0);
        ScheduleType ma2 = (ScheduleType) types.get(1);
        ScheduleType mi2 = (ScheduleType) types.get(2);
        ScheduleType ju2 = (ScheduleType) types.get(3);
        ScheduleType vi2 = (ScheduleType) types.get(4);
        ScheduleType lu3 = (ScheduleType) types.get(5);
        ScheduleType ma3 = (ScheduleType) types.get(6);
        ScheduleType mi3 = (ScheduleType) types.get(7);
        ScheduleType ju3 = (ScheduleType) types.get(8);
        ScheduleType vi3 = (ScheduleType) types.get(9);
        ScheduleType maju15 = (ScheduleType) types.get(10);
        ScheduleType lumivi1 = (ScheduleType) types.get(11);
        ScheduleType lumi2 = (ScheduleType) types.get(12);
        ScheduleType maju2 = (ScheduleType) types.get(13);
        ScheduleType lumamijuvi1 = (ScheduleType) types.get(14);

        list.add(new Schedule(0, 0, lu2));
        list.add(new Schedule(0, 4, lu2));
        list.add(new Schedule(0, 8, lu2));
        list.add(new Schedule(0, 16, lu2));
        list.add(new Schedule(0, 20, lu2));
        list.add(new Schedule(0, 24, lu2));
        list.add(new Schedule(1, 0, ma2));
        list.add(new Schedule(1, 4, ma2));
        list.add(new Schedule(1, 8, ma2));
        list.add(new Schedule(1, 16, ma2));
        list.add(new Schedule(1, 20, ma2));
        list.add(new Schedule(1, 24, ma2));
        list.add(new Schedule(2, 0, mi2));
        list.add(new Schedule(2, 4, mi2));
        list.add(new Schedule(2, 8, mi2));
        list.add(new Schedule(2, 16, mi2));
        list.add(new Schedule(2, 20, mi2));
        list.add(new Schedule(2, 24, mi2));
        list.add(new Schedule(3, 0, ju2));
        list.add(new Schedule(3, 4, ju2));
        list.add(new Schedule(3, 8, ju2));
        list.add(new Schedule(3, 16, ju2));
        list.add(new Schedule(3, 20, ju2));
        list.add(new Schedule(3, 24, ju2));
        list.add(new Schedule(4, 0, vi2));
        list.add(new Schedule(4, 4, vi2));
        list.add(new Schedule(4, 8, vi2));
        list.add(new Schedule(4, 16, vi2));
        list.add(new Schedule(4, 20, vi2));
        list.add(new Schedule(4, 24, vi2));
        list.add(new Schedule(5, 0, lu3));
        list.add(new Schedule(5, 6, lu3));
        list.add(new Schedule(5, 16, lu3));
        list.add(new Schedule(5, 22, lu3));
        list.add(new Schedule(6, 0, ma3));
        list.add(new Schedule(6, 6, ma3));
        list.add(new Schedule(6, 16, ma3));
        list.add(new Schedule(6, 22, ma3));
        list.add(new Schedule(7, 0, mi3));
        list.add(new Schedule(7, 6, mi3));
        list.add(new Schedule(7, 16, mi3));
        list.add(new Schedule(7, 22, mi3));
        list.add(new Schedule(8, 0, ju3));
        list.add(new Schedule(8, 6, ju3));
        list.add(new Schedule(8, 16, ju3));
        list.add(new Schedule(8, 22, ju3));
        list.add(new Schedule(9, 0, vi3));
        list.add(new Schedule(9, 6, vi3));
        list.add(new Schedule(9, 16, vi3));
        list.add(new Schedule(9, 22, vi3));
        list.add(new Schedule(10, 0, maju15));
        list.add(new Schedule(10, 3, maju15));
        list.add(new Schedule(10, 6, maju15));
        list.add(new Schedule(10, 9, maju15));
        list.add(new Schedule(10, 16, maju15));
        list.add(new Schedule(10, 19, maju15));
        list.add(new Schedule(10, 22, maju15));
        list.add(new Schedule(10, 25, maju15));
        list.add(new Schedule(11, 0, lumivi1));
        list.add(new Schedule(11, 2, lumivi1));
        list.add(new Schedule(11, 4, lumivi1));
        list.add(new Schedule(11, 6, lumivi1));
        list.add(new Schedule(11, 8, lumivi1));
        list.add(new Schedule(11, 10, lumivi1));
        list.add(new Schedule(11, 16, lumivi1));
        list.add(new Schedule(11, 18, lumivi1));
        list.add(new Schedule(11, 20, lumivi1));
        list.add(new Schedule(11, 22, lumivi1));
        list.add(new Schedule(11, 24, lumivi1));
        list.add(new Schedule(11, 26, lumivi1));
        list.add(new Schedule(12, 0, lumi2));
        list.add(new Schedule(12, 4, lumi2));
        list.add(new Schedule(12, 8, lumi2));
        list.add(new Schedule(12, 16, lumi2));
        list.add(new Schedule(12, 20, lumi2));
        list.add(new Schedule(12, 24, lumi2));
        list.add(new Schedule(13, 0, maju2));
        list.add(new Schedule(13, 4, maju2));
        list.add(new Schedule(13, 8, maju2));
        list.add(new Schedule(13, 16, maju2));
        list.add(new Schedule(13, 20, maju2));
        list.add(new Schedule(13, 24, maju2));
        list.add(new Schedule(14, 0, lumamijuvi1));
        list.add(new Schedule(14, 2, lumamijuvi1));
        list.add(new Schedule(14, 4, lumamijuvi1));
        list.add(new Schedule(14, 6, lumamijuvi1));
        list.add(new Schedule(14, 8, lumamijuvi1));
        list.add(new Schedule(14, 10, lumamijuvi1));
        list.add(new Schedule(14, 16, lumamijuvi1));
        list.add(new Schedule(14, 18, lumamijuvi1));
        list.add(new Schedule(14, 20, lumamijuvi1));
        list.add(new Schedule(14, 22, lumamijuvi1));
        list.add(new Schedule(14, 24, lumamijuvi1));
        list.add(new Schedule(14, 26, lumamijuvi1));
        return list;
    }

    public static Schedule fromCSV(String csv) {
        if (csv.isEmpty()) {
            return null;
        }

        String[] values = csv.split(",");

        int fields = 4;

        if (values.length != fields) {
            return null;
        }

        int type = -1;
        int offset = -1;

        try {
            type = Integer.parseInt(values[0]);
        } catch (NumberFormatException e) {
        }

        try {
            offset = Integer.parseInt(values[1]);
        } catch (NumberFormatException e) {
        }

        String mask = values[2];
        String description = values[3];

        Schedule schedule = new Schedule(type, offset, mask, description);

        if (validate(schedule)) {
            return schedule;
        }

        return null;
    }

    public static boolean validate(Schedule schedule) {
        return schedule.getType() >= 0 && schedule.getOffset() >= 0
                && !schedule.getMask().isEmpty() && !schedule.getDescription().isEmpty();
    }

    @Override
    public String toCSV() {
        return type + "," + offset + "," + mask + "," + description;
    }

    @Override
    public String toString() {
        return "Schedule{" + "type=" + type + ", offset=" + offset + ", mask=" + mask + ", description=" + description + '}';
    }

}
