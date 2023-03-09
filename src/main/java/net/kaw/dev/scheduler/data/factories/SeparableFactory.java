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
package net.kaw.dev.scheduler.data.factories;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.kaw.dev.scheduler.data.Career;
import net.kaw.dev.scheduler.data.Group;
import net.kaw.dev.scheduler.data.Schedule;
import net.kaw.dev.scheduler.data.ScheduleType;
import net.kaw.dev.scheduler.data.Subject;
import net.kaw.dev.scheduler.data.Teacher;
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

        ISeparable separable;

        /*
         * Check what type of data must be built, and use the corresponding build method
         */
        switch (separableType) {
            case SCHEDULE:
                separable = buildSchedule(values);
                break;
            case SCHEDULE_TYPE:
                separable = buildScheduleType(values);
                break;
            case SUBJECT:
                separable = buildSubject(values);
                break;
            case TEACHER:
                separable = buildTeacher(values);
                break;
            case CAREER:
                separable = buildCareer(values);
                break;
            case GROUP:
                separable = buildGroup(values);
                break;
            default:
                return null;
        }

        if (separable != null && separable.validate()) {
            return separable;
        }

        return null;
    }

    private static Career buildCareer(String[] careerValues) {
        try {
            String id = careerValues[0];
            String subjectKey = careerValues[1];
            int trajectoryStart = Integer.parseInt(careerValues[2]);
            int trajectoryEnd = Integer.parseInt(careerValues[3]);

            return new Career(id, subjectKey, trajectoryStart, trajectoryEnd);
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            Logger.getLogger(SeparableFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private static Teacher buildTeacher(String[] teacherValues) {
        try {
            String type = teacherValues[0];
            int controlNumber = Integer.parseInt(teacherValues[1]);
            String firstName = teacherValues[2];
            String lastName = teacherValues[3];

            return new Teacher(type, controlNumber, firstName, lastName);
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

            return new Subject(subjectKey, classKey, schedule, description);
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

            return new Schedule(type, offset, sessionMask, description);
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

            return new ScheduleType(description, availableHours, sessionMask);
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

            return new Group(subjectKey, int_1, int_2);
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            Logger.getLogger(SeparableFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
