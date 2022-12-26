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
package net.kaw.dev.scheduler.csv;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.kaw.dev.scheduler.data.separable.Career;
import net.kaw.dev.scheduler.data.separable.Schedule;
import net.kaw.dev.scheduler.data.separable.ScheduleType;
import net.kaw.dev.scheduler.data.separable.Subject;
import net.kaw.dev.scheduler.data.separable.Teacher;
import net.kaw.dev.scheduler.interfaces.ISeparable;

public class CsvFactory {

    public enum SeparableType {
        SCHEDULE, SCHEDULE_TYPE, SUBJECT, TEACHER, CAREER
    }

    public static ISeparable build(SeparableType separableType, String csvString) {
        if (csvString.isEmpty()) {
            return null;
        }

        String[] values = csvString.split(",");

        if (values.length == 0) {
            return null;
        }

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
            default:
                return null;
        }
    }

    private static Career buildCareer(String[] careerValues) {
        try {
            String id = careerValues[0];
            String subjectKey = careerValues[1];
            int trajectoryStart;
            int trajectoryEnd;

            try {
                trajectoryStart = Integer.parseInt(careerValues[2]);
                trajectoryEnd = Integer.parseInt(careerValues[3]);
            } catch (NumberFormatException e) {
                return null;
            }

            Career career = new Career(id, subjectKey, trajectoryStart, trajectoryEnd);

            if (validate(career)) {
                return career;
            }
        } catch (IndexOutOfBoundsException ex) {
            Logger.getLogger(CsvFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private static Teacher buildTeacher(String[] teacherValues) {
        try {
            char type = teacherValues[0].charAt(0);
            int controlNumber;
            String firstName = teacherValues[2];
            String lastName = teacherValues[3];

            try {
                controlNumber = Integer.parseInt(teacherValues[1]);
            } catch (NumberFormatException e) {
                return null;
            }

            Teacher teacher = new Teacher(type, controlNumber, firstName, lastName);

            if (validate(teacher)) {
                return teacher;
            }
        } catch (IndexOutOfBoundsException ex) {
            Logger.getLogger(CsvFactory.class.getName()).log(Level.SEVERE, null, ex);
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

            if (validate(subject)) {
                return subject;
            }
        } catch (IndexOutOfBoundsException ex) {
            Logger.getLogger(CsvFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private static Schedule buildSchedule(String[] scheduleValues) {
        try {
            int type;
            int offset;

            try {
                type = Integer.parseInt(scheduleValues[0]);
                offset = Integer.parseInt(scheduleValues[1]);
            } catch (NumberFormatException e) {
                return null;
            }

            String mask = scheduleValues[2];
            String description = scheduleValues[3];

            Schedule schedule = new Schedule(type, offset, mask, description);

            if (validate(schedule)) {
                return schedule;
            }
        } catch (IndexOutOfBoundsException ex) {
            Logger.getLogger(CsvFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private static ScheduleType buildScheduleType(String[] scheduleTypeValues) {
        try {
            String description = scheduleTypeValues[0];
            String availableHours = scheduleTypeValues[1];
            String mask = scheduleTypeValues[2];

            ScheduleType scheduleType = new ScheduleType(description, availableHours, mask);

            if (validate(scheduleType)) {
                return scheduleType;
            }
        } catch (IndexOutOfBoundsException ex) {
            Logger.getLogger(CsvFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private static boolean validate(Schedule schedule) {
        return schedule.getType() >= 0 && schedule.getOffset() >= 0
                && !schedule.getMask().isEmpty() && !schedule.getDescription().isEmpty();
    }

    private static boolean validate(Subject subject) {
        return !subject.getSubjectKey().isEmpty() && !subject.getClassKey().isEmpty()
                && !subject.getSchedule().isEmpty() && !subject.getDescription().isEmpty();
    }

    private static boolean validate(Teacher teacher) {
        return teacher.getId() >= 0
                && !teacher.getFirstName().isEmpty() && !teacher.getLastName().isEmpty();
    }

    private static boolean validate(Career career) {
        return !career.getId().isEmpty() && !career.getSubjectKey().isEmpty()
                && career.getTrajectoryStart() >= 0 && career.getTrajectoryEnd() >= -1;
    }

    private static boolean validate(ScheduleType scheduleType) {
        return !scheduleType.getDescription().isEmpty() && !scheduleType.getAvailableHours().isEmpty()
                && !scheduleType.getMask().isEmpty();
    }

}
