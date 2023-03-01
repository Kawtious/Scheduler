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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.kaw.dev.scheduler.data.interfaces.IMappable;

public class University implements IMappable {

    private final List<Career> careers;

    private final List<Group> groups;

    private final List<Schedule> schedules;

    private final List<ScheduleType> scheduleTypes;

    private final List<Subject> subjects;

    private final List<Classroom> classrooms;

    private final List<Teacher> teachers;

    public University(List<Career> careers, List<Group> groups, List<Schedule> schedules, List<ScheduleType> scheduleTypes, List<Subject> subjects, List<Classroom> classrooms, List<Teacher> teachers) {
        this.careers = careers;
        this.groups = groups;
        this.schedules = schedules;
        this.scheduleTypes = scheduleTypes;
        this.subjects = subjects;
        this.classrooms = classrooms;
        this.teachers = teachers;
    }

    public List<Career> getCareers() {
        return Collections.unmodifiableList(careers);
    }

    public List<Group> getGroups() {
        return Collections.unmodifiableList(groups);
    }

    public List<Schedule> getSchedules() {
        return Collections.unmodifiableList(schedules);
    }

    public List<ScheduleType> getScheduleTypes() {
        return Collections.unmodifiableList(scheduleTypes);
    }

    public List<Subject> getSubjects() {
        return Collections.unmodifiableList(subjects);
    }

    public List<Classroom> getClassrooms() {
        return Collections.unmodifiableList(classrooms);
    }

    public List<Teacher> getTeachers() {
        return Collections.unmodifiableList(teachers);
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();

        Map<String, Object> careersMap = new HashMap<>(careers.size());
        Map<String, Object> groupsMap = new HashMap<>(groups.size());
        Map<String, Object> schedulesMap = new HashMap<>(schedules.size());
        Map<String, Object> scheduleTypesMap = new HashMap<>(scheduleTypes.size());
        Map<String, Object> subjectsMap = new HashMap<>(subjects.size());
        Map<String, Object> classroomsMap = new HashMap<>(classrooms.size());
        Map<String, Object> teachersMap = new HashMap<>(teachers.size());

        for (Career career : careers) {
            careersMap.put(career.getId(), career.toMap());
        }

        for (Group group : groups) {
            groupsMap.put(group.getId(), group.toMap());
        }

        for (Schedule schedule : schedules) {
            schedulesMap.put(schedule.getId(), schedule.toMap());
        }

        for (ScheduleType scheduleType : scheduleTypes) {
            scheduleTypesMap.put(scheduleType.getId(), scheduleType.toMap());
        }

        for (Subject subject : subjects) {
            subjectsMap.put(subject.getId(), subject.toMap());
        }

        for (Classroom classroom : classrooms) {
            classroomsMap.put(classroom.getId(), classroom.toMap());
        }

        for (Teacher teacher : teachers) {
            teachersMap.put(teacher.getId(), teacher.toMap());
        }

        map.put("careers", careersMap);
        map.put("groups", groupsMap);
        map.put("schedules", schedulesMap);
        map.put("scheduleTypes", scheduleTypesMap);
        map.put("subjects", subjectsMap);
        map.put("classrooms", classroomsMap);
        map.put("teachers", teachersMap);

        return map;
    }

    @Override
    public String toString() {
        return "University{" + "careers=" + careers + ", groups=" + groups + ", schedules=" + schedules + ", scheduleTypes=" + scheduleTypes + ", subjects=" + subjects + ", classrooms=" + classrooms + ", teachers=" + teachers + '}';
    }

}
