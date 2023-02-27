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

import java.util.Map;
import static net.kaw.dev.scheduler.data.ScheduleMap.DAYS;
import static net.kaw.dev.scheduler.data.ScheduleMap.HALFHOURS;
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
        String _subjectKey;
        int _trajectoryStart;
        int _trajectoryEnd;

        if (!map.containsKey("id") || !map.containsKey("subjectKey") || !map.containsKey("trajectoryStart") || !map.containsKey("trajectoryEnd")) {
            return null;
        }

        try {
            _id = (String) map.get("id");
            _subjectKey = (String) map.get("subjectKey");
            _trajectoryStart = Integer.parseInt((String) map.get("trajectoryStart"));
            _trajectoryEnd = Integer.parseInt((String) map.get("trajectoryEnd"));
        } catch (NumberFormatException ex) {
            return null;
        }

        return new Career(_id, _subjectKey, _trajectoryStart, _trajectoryEnd);
    }

    @SuppressWarnings("unchecked")
    private static Classroom buildClassroom(Map<String, Object> map) {
        String _type;
        ScheduleMap _scheduleMap;

        if (!map.containsKey("type") || !map.containsKey("scheduleMap")) {
            return null;
        }

        _type = (String) map.get("type");
        _scheduleMap = buildScheduleMap((Map<String, Object>) map.get("scheduleMap"));

        return new Classroom(_type, _scheduleMap);
    }

    @SuppressWarnings("unchecked")
    private static Teacher buildTeacher(Map<String, Object> map) {
        char _type;
        int _controlNumber;
        String _firstName;
        String _lastName;
        ScheduleMap _scheduleMap;

        if (!map.containsKey("type") || !map.containsKey("controlNumber")
                || !map.containsKey("firstName") || !map.containsKey("lastName") || !map.containsKey("scheduleMap")) {
            return null;
        }

        try {
            _type = (char) map.get("type");
            _controlNumber = Integer.parseInt((String) map.get("controlNumber"));
            _firstName = (String) map.get("firstName");
            _lastName = (String) map.get("lastName");
            _scheduleMap = buildScheduleMap((Map<String, Object>) map.get("scheduleMap"));
        } catch (NumberFormatException ex) {
            return null;
        }

        return new Teacher(_type, _controlNumber, _firstName, _lastName, _scheduleMap);
    }

    private static Subject buildSubject(Map<String, Object> map) {
        String _subjectKey;
        String _classKey;
        String _schedule;
        String _description;

        if (!map.containsKey("subjectKey") || !map.containsKey("classKey") || !map.containsKey("schedule") || !map.containsKey("description")) {
            return null;
        }

        _subjectKey = (String) map.get("subjectKey");
        _classKey = (String) map.get("classKey");
        _schedule = (String) map.get("schedule");
        _description = (String) map.get("description");

        return new Subject(_subjectKey, _classKey, _schedule, _description);
    }

    private static Schedule buildSchedule(Map<String, Object> map) {
        int _type;
        int _offset;
        String _sessionMask;
        String _description;

        if (!map.containsKey("type") || !map.containsKey("offset") || !map.containsKey("sessionMask") || !map.containsKey("description")) {
            return null;
        }

        try {
            _type = Integer.parseInt((String) map.get("type"));
            _offset = Integer.parseInt((String) map.get("offset"));
            _sessionMask = (String) map.get("sessionMask");
            _description = (String) map.get("description");
        } catch (NumberFormatException ex) {
            return null;
        }

        return new Schedule(_type, _offset, _sessionMask, _description);
    }

    private static ScheduleType buildScheduleType(Map<String, Object> map) {
        String _description;
        String _availableHours;
        String _sessionMask;

        if (!map.containsKey("description") || !map.containsKey("availableHours") || !map.containsKey("sessionMask")) {
            return null;
        }

        _description = (String) map.get("description");
        _availableHours = (String) map.get("availableHours");
        _sessionMask = (String) map.get("sessionMask");

        return new ScheduleType(_description, _availableHours, _sessionMask);
    }

    @SuppressWarnings("unchecked")
    private static ScheduleMap buildScheduleMap(Map<String, Object> map) {
        ScheduleMap _scheduleMap = new ScheduleMap(0);

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
            "2000"
        };

        for (int i = 0; i < DAYS; i++) {
            for (int j = 0; j < HALFHOURS; j++) {
                _scheduleMap.setMapValue(i, j, ((Map<String, Integer>) map.get(days[i])).get(hours[j]));
            }
        }

        return _scheduleMap;
    }

    private static Group buildGroup(Map<String, Object> map) {
        String _subjectKey;
        int _int_1;
        int _int_2;

        if (!map.containsKey("subjectKey") || !map.containsKey("int_1") || !map.containsKey("int_2")) {
            return null;
        }

        try {
            _subjectKey = (String) map.get("subjectKey");
            _int_1 = Integer.parseInt((String) map.get("int_1"));
            _int_2 = Integer.parseInt((String) map.get("int_2"));
        } catch (NumberFormatException ex) {
            return null;
        }

        return new Group(_subjectKey, _int_1, _int_2);
    }
}
