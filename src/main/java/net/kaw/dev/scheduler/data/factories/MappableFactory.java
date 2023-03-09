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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.kaw.dev.scheduler.data.Career;
import net.kaw.dev.scheduler.data.Classroom;
import net.kaw.dev.scheduler.data.Comment;
import net.kaw.dev.scheduler.data.Cycle;
import net.kaw.dev.scheduler.data.Group;
import net.kaw.dev.scheduler.data.HalfHour;
import net.kaw.dev.scheduler.data.Schedule;
import net.kaw.dev.scheduler.data.ScheduleMap;
import net.kaw.dev.scheduler.data.ScheduleType;
import net.kaw.dev.scheduler.data.Subject;
import net.kaw.dev.scheduler.data.Teacher;
import net.kaw.dev.scheduler.data.interfaces.IMappable;

public class MappableFactory {

    public enum MappableType {
        SCHEDULE, SCHEDULE_TYPE, SUBJECT, TEACHER, CAREER, GROUP, CLASSROOM
    }

    public static IMappable build(MappableType separableType, Map<String, Object> map) {
        if (map == null) {
            return null;
        }

        IMappable _mappable;

        /*
         * Check what type of data must be built, and use the corresponding build method
         */
        switch (separableType) {
            case SCHEDULE:
                _mappable = buildSchedule(map);
                break;
            case SCHEDULE_TYPE:
                _mappable = buildScheduleType(map);
                break;
            case SUBJECT:
                _mappable = buildSubject(map);
                break;
            case TEACHER:
                _mappable = buildTeacher(map);
                break;
            case CAREER:
                _mappable = buildCareer(map);
                break;
            case GROUP:
                _mappable = buildGroup(map);
                break;
            case CLASSROOM:
                _mappable = buildClassroom(map);
                break;
            default:
                return null;
        }

        return _mappable;
    }

    private static Career buildCareer(Map<String, Object> map) {
        String _id;
        String _key;
        String _subjectKey;
        int _trajectoryStart;
        int _trajectoryEnd;

        if (!map.containsKey(Career.ID_KEY) || !map.containsKey(Career.KEY_KEY) || !map.containsKey(Career.SUBJECT_KEY_KEY) || !map.containsKey(Career.TRAJECTORY_START_KEY) || !map.containsKey(Career.TRAJECTORY_END_KEY)) {
            return null;
        }

        _id = (String) map.get(Career.ID_KEY);
        _key = (String) map.get(Career.KEY_KEY);
        _subjectKey = (String) map.get(Career.SUBJECT_KEY_KEY);

        if (map.get(Career.TRAJECTORY_START_KEY) instanceof Number) {
            Number __trajectoryStart = (Number) map.get(Career.TRAJECTORY_START_KEY);
            _trajectoryStart = __trajectoryStart.intValue();
        } else {
            _trajectoryStart = (Integer) map.get(Career.TRAJECTORY_START_KEY);
        }

        if (map.get(Career.TRAJECTORY_END_KEY) instanceof Number) {
            Number __trajectoryEnd = (Number) map.get(Career.TRAJECTORY_END_KEY);
            _trajectoryEnd = __trajectoryEnd.intValue();
        } else {
            _trajectoryEnd = (Integer) map.get(Career.TRAJECTORY_END_KEY);
        }

        return new Career(_id, _key, _subjectKey, _trajectoryStart, _trajectoryEnd);
    }

    @SuppressWarnings("unchecked")
    private static Classroom buildClassroom(Map<String, Object> map) {
        String _id;
        String _type;
        ScheduleMap _scheduleMap;

        if (!map.containsKey(Classroom.ID_KEY) || !map.containsKey(Classroom.TYPE_KEY) || !map.containsKey(Classroom.SCHEDULE_MAP_KEY)) {
            return null;
        }

        _id = (String) map.get(Classroom.ID_KEY);
        _type = (String) map.get(Classroom.TYPE_KEY);
        _scheduleMap = buildScheduleMap((Map<String, Object>) map.get(Classroom.SCHEDULE_MAP_KEY));

        return new Classroom(_id, _type, _scheduleMap);
    }

    @SuppressWarnings("unchecked")
    private static Teacher buildTeacher(Map<String, Object> map) {
        String _id;
        String _type;
        int _controlNumber;
        String _firstName;
        String _lastName;
        ScheduleMap _scheduleMap;

        if (!map.containsKey(Teacher.ID_KEY) || !map.containsKey(Teacher.TYPE_KEY) || !map.containsKey(Teacher.CONTROL_NUMBER_KEY)
                || !map.containsKey(Teacher.FIRST_NAME_KEY) || !map.containsKey(Teacher.LAST_NAME_KEY) || !map.containsKey(Teacher.SCHEDULE_MAP_KEY)) {
            return null;
        }

        _id = (String) map.get(Teacher.ID_KEY);
        _type = (String) map.get(Teacher.TYPE_KEY);

        if (map.get(Teacher.CONTROL_NUMBER_KEY) instanceof Number) {
            Number __controlNumber = (Number) map.get(Teacher.CONTROL_NUMBER_KEY);
            _controlNumber = __controlNumber.intValue();
        } else {
            _controlNumber = (Integer) map.get(Teacher.CONTROL_NUMBER_KEY);
        }

        _firstName = (String) map.get(Teacher.FIRST_NAME_KEY);
        _lastName = (String) map.get(Teacher.LAST_NAME_KEY);
        _scheduleMap = buildScheduleMap((Map<String, Object>) map.get(Teacher.SCHEDULE_MAP_KEY));

        return new Teacher(_id, _type, _controlNumber, _firstName, _lastName, _scheduleMap);
    }

    private static Subject buildSubject(Map<String, Object> map) {
        String _id;
        String _subjectKey;
        String _classKey;
        String _schedule;
        String _description;

        if (!map.containsKey(Subject.ID_KEY) || !map.containsKey(Subject.SUBJECT_KEY_KEY) || !map.containsKey("classKey") || !map.containsKey("schedule") || !map.containsKey("description")) {
            return null;
        }

        _id = (String) map.get(Subject.ID_KEY);
        _subjectKey = (String) map.get(Subject.SUBJECT_KEY_KEY);
        _classKey = (String) map.get(Subject.CLASS_KEY_KEY);
        _schedule = (String) map.get(Subject.SCHEDULE_KEY);
        _description = (String) map.get(Subject.DESCRIPTION_KEY);

        return new Subject(_id, _subjectKey, _classKey, _schedule, _description);
    }

    private static Schedule buildSchedule(Map<String, Object> map) {
        String _id;
        int _type;
        int _offset;
        String _sessionMask;
        String _description;

        if (!map.containsKey(Schedule.ID_KEY) || !map.containsKey(Schedule.TYPE_KEY) || !map.containsKey(Schedule.OFFSET_KEY) || !map.containsKey(Schedule.SESSION_MASK_KEY) || !map.containsKey(Schedule.DESCRIPTION_KEY)) {
            return null;
        }

        _id = (String) map.get(Schedule.ID_KEY);
        _type = (Integer) map.get(Schedule.TYPE_KEY);

        if (map.get(Schedule.OFFSET_KEY) instanceof Number) {
            Number __offset = (Number) map.get(Schedule.OFFSET_KEY);
            _offset = __offset.intValue();
        } else {
            _offset = (Integer) map.get(Schedule.OFFSET_KEY);
        }

        _sessionMask = (String) map.get(Schedule.SESSION_MASK_KEY);
        _description = (String) map.get(Schedule.DESCRIPTION_KEY);

        return new Schedule(_id, _type, _offset, _sessionMask, _description);
    }

    private static ScheduleType buildScheduleType(Map<String, Object> map) {
        String _id;
        String _description;
        String _availableHours;
        String _sessionMask;

        if (!map.containsKey(ScheduleType.ID_KEY) || !map.containsKey(ScheduleType.DESCRIPTION_KEY) || !map.containsKey(ScheduleType.AVAILABLE_HOURS_KEY) || !map.containsKey(ScheduleType.SESSION_MASK_KEY)) {
            return null;
        }

        _id = (String) map.get(ScheduleType.ID_KEY);
        _description = (String) map.get(ScheduleType.DESCRIPTION_KEY);
        _availableHours = (String) map.get(ScheduleType.AVAILABLE_HOURS_KEY);
        _sessionMask = (String) map.get(ScheduleType.SESSION_MASK_KEY);

        return new ScheduleType(_id, _description, _availableHours, _sessionMask);
    }

    @SuppressWarnings("unchecked")
    private static ScheduleMap buildScheduleMap(Map<String, Object> map) {
        ScheduleMap _scheduleMap = new ScheduleMap(new HalfHour(0));

        String[] days = {
            "monday",
            "tuesday",
            "wednesday",
            "thursday",
            "friday"
        };

        String[] hours = {
            "0700",
            "0730",
            "0800",
            "0830",
            "0900",
            "0930",
            "1000",
            "1030",
            "1100",
            "1130",
            "1200",
            "1230",
            "1300",
            "1330",
            "1400",
            "1430",
            "1500",
            "1530",
            "1600",
            "1630",
            "1700",
            "1730",
            "1800",
            "1830",
            "1900",
            "1930",
            "2000",
            "2030"
        };

        int i = 0;

        for (String day : days) {
            if (!map.containsKey(day)) {
                return null;
            }

            int j = 0;

            Map<String, Object> dayMap = ((Map<String, Object>) map.get(day));

            for (String hour : hours) {
                if (!dayMap.containsKey(hour)) {
                    return null;
                }

                _scheduleMap.setMapValue(i, j, buildHalfHour((Map<String, Object>) ((Map<String, Object>) map.get(days[i])).get(hours[j])));
                j++;
            }

            i++;
        }

        if (map.containsKey(ScheduleMap.CYCLE_KEY)) {
            _scheduleMap.setCycle(buildCycle((Map<String, Object>) map.get(ScheduleMap.CYCLE_KEY)));
        }

        if (map.containsKey(ScheduleMap.COMMENTS_KEY)) {
            Map<String, Object> comments = (Map<String, Object>) map.get(ScheduleMap.COMMENTS_KEY);

            for (Entry<String, Object> entry : comments.entrySet()) {
                _scheduleMap.addComment(buildComment((Map<String, Object>) entry));
            }
        }

        return _scheduleMap;
    }

    private static Group buildGroup(Map<String, Object> map) {
        String _id;
        String _subjectKey;
        int _int_1;
        int _int_2;

        if (!map.containsKey(Group.ID_KEY) || !map.containsKey(Group.SUBJECT_KEY_KEY) || !map.containsKey(Group.INT_1_KEY) || !map.containsKey(Group.INT_2_KEY)) {
            return null;
        }

        _id = (String) map.get(Group.ID_KEY);
        _subjectKey = (String) map.get(Group.SUBJECT_KEY_KEY);

        if (map.get(Group.INT_1_KEY) instanceof Number) {
            Number __int_1 = (Number) map.get(Group.INT_1_KEY);
            _int_1 = __int_1.intValue();
        } else {
            _int_1 = (Integer) map.get(Group.INT_1_KEY);
        }

        if (map.get(Group.INT_2_KEY) instanceof Number) {
            Number __int_2 = (Number) map.get(Group.INT_2_KEY);
            _int_2 = __int_2.intValue();
        } else {
            _int_2 = (Integer) map.get(Group.INT_2_KEY);
        }

        return new Group(_id, _subjectKey, _int_1, _int_2);
    }

    @SuppressWarnings("unchecked")
    private static HalfHour buildHalfHour(Map<String, Object> map) {
        String _id;
        Integer _available;

        if (!map.containsKey(HalfHour.ID_KEY) || !map.containsKey(HalfHour.AVAILABLE_KEY)) {
            return null;
        }

        _id = (String) map.get(HalfHour.ID_KEY);

        if (map.get(HalfHour.AVAILABLE_KEY) instanceof Number) {
            Number __available = (Number) map.get(HalfHour.AVAILABLE_KEY);
            _available = __available.intValue();
        } else {
            _available = (Integer) map.get(HalfHour.AVAILABLE_KEY);
        }

        return new HalfHour(_id, _available);
    }

    @SuppressWarnings("unchecked")
    private static Comment buildComment(Map<String, Object> map) {
        String _id;
        String _content;
        List<HalfHour> _halfHours;

        if (!map.containsKey(Comment.ID_KEY) || !map.containsKey(Comment.CONTENT_KEY)) {
            return null;
        }

        _id = (String) map.get(Comment.ID_KEY);
        _content = (String) map.get(Comment.CONTENT_KEY);

        if (map.containsKey(Comment.HALF_HOURS_KEY)) {
            _halfHours = new ArrayList<>();

            Map<String, Object> halfHoursMap = (Map<String, Object>) map.get(Comment.HALF_HOURS_KEY);

            for (Entry<String, Object> entry : halfHoursMap.entrySet()) {
                _halfHours.add(buildHalfHour((Map<String, Object>) entry.getValue()));
            }

            return new Comment(_id, _content, _halfHours);
        }

        return new Comment(_id, _content);
    }

    private static Cycle buildCycle(Map<String, Object> map) {
        String _id;
        String _title;
        Date _start;
        Date _end;

        if (!map.containsKey(Cycle.ID_KEY) || !map.containsKey(Cycle.TITLE_KEY) || !map.containsKey(Cycle.START_KEY) || !map.containsKey(Cycle.END_KEY)) {
            return null;
        }

        _id = (String) map.get(Cycle.ID_KEY);
        _title = (String) map.get(Cycle.TITLE_KEY);
        _start = (Date) map.get(Cycle.START_KEY);
        _end = (Date) map.get(Cycle.END_KEY);

        return new Cycle(_id, _title, _start, _end);
    }

}
