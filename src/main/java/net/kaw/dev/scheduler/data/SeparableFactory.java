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

import java.util.logging.Level;
import java.util.logging.Logger;
import net.kaw.dev.scheduler.data.interfaces.ISeparable;

public class SeparableFactory {

    public enum SeparableType {
        SCHEDULE, SCHEDULE_TYPE, SUBJECT, TEACHER, CAREER, GROUP
    }

    public static ISeparable build(SeparableType separableType, String csvString) {
        if (csvString.isEmpty()) {
            return null;
        }

        /*
         * Split all values in the string using commas
         */
        String[] values = csvString.split(",");

        if (values.length == 0) {
            return null;
        }

        /*
         * Check what type of data must be built, and use the corresponding build method
         */
        switch (separableType) {
            case SCHEDULE:
                return buildSchedule(values);
            case SCHEDULE_TYPE:
                return buildScheduleType(values);
            case SUBJECT:
                return buildSubject(values);
            case TEACHER:
                return buildTeacher(values);
            case CAREER:
                return buildCareer(values);
            case GROUP:
                return buildGroup(values);
            default:
                return null;
        }
    }

    private static Career buildCareer(String[] careerValues) {
        try {
            String id = careerValues[0];
            String subjectKey = careerValues[1];
            int trajectoryStart = Integer.parseInt(careerValues[2]);
            int trajectoryEnd = Integer.parseInt(careerValues[3]);

            Career career = new Career(id, subjectKey, trajectoryStart, trajectoryEnd);

            if (career.validate()) {
                return career;
            }
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            Logger.getLogger(SeparableFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private static Teacher buildTeacher(String[] teacherValues) {
        try {
            char type = teacherValues[0].charAt(0);
            int controlNumber = Integer.parseInt(teacherValues[1]);
            String firstName = teacherValues[2];
            String lastName = teacherValues[3];

            Teacher teacher = new Teacher(type, controlNumber, firstName, lastName);

            if (teacher.validate()) {
                return teacher;
            }
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            Logger.getLogger(SeparableFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private static Subject buildSubject(String[] subjectValues) {
        try {
            String subjectKey = subjectValues[0];
            String classKey = subjectValues[1];
            String schedule = subjectValues[2];
            String description = subjectValues[3];

            Subject subject = new Subject(subjectKey, classKey, schedule, description);

            if (subject.validate()) {
                return subject;
            }
        } catch (IndexOutOfBoundsException ex) {
            Logger.getLogger(SeparableFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private static Schedule buildSchedule(String[] scheduleValues) {
        try {
            int type = Integer.parseInt(scheduleValues[0]);
            int offset = Integer.parseInt(scheduleValues[1]);
            String sessionMask = scheduleValues[2];
            String description = scheduleValues[3];

            Schedule schedule = new Schedule(type, offset, sessionMask, description);

            if (schedule.validate()) {
                return schedule;
            }
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            Logger.getLogger(SeparableFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private static ScheduleType buildScheduleType(String[] scheduleTypeValues) {
        try {
            String description = scheduleTypeValues[0];
            String availableHours = scheduleTypeValues[1];
            String sessionMask = scheduleTypeValues[2];

            ScheduleType scheduleType = new ScheduleType(description, availableHours, sessionMask);

            if (scheduleType.validate()) {
                return scheduleType;
            }
        } catch (IndexOutOfBoundsException ex) {
            Logger.getLogger(SeparableFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private static Group buildGroup(String[] groupValues) {
        try {
            String subjectKey = groupValues[0];
            int int_1 = Integer.parseInt(groupValues[1]);
            int int_2 = Integer.parseInt(groupValues[2]);

            Group group = new Group(subjectKey, int_1, int_2);

            if (group.validate()) {
                return group;
            }
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            Logger.getLogger(SeparableFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
