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
package net.kaw.dev.scheduler.testing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.kaw.dev.scheduler.data.Career;
import net.kaw.dev.scheduler.data.Classroom;
import net.kaw.dev.scheduler.data.Group;
import net.kaw.dev.scheduler.data.HexableFactory.HexableType;
import net.kaw.dev.scheduler.data.MappableFactory;
import net.kaw.dev.scheduler.data.MappableFactory.MappableType;
import net.kaw.dev.scheduler.data.Schedule;
import net.kaw.dev.scheduler.data.ScheduleType;
import net.kaw.dev.scheduler.data.SeparableFactory.SeparableType;
import net.kaw.dev.scheduler.data.Subject;
import net.kaw.dev.scheduler.data.Teacher;
import net.kaw.dev.scheduler.data.University;
import net.kaw.dev.scheduler.data.interfaces.IHexable;
import net.kaw.dev.scheduler.data.interfaces.ISeparable;
import net.kaw.dev.scheduler.io.CsvManager;
import net.kaw.dev.scheduler.io.HexManager;
import net.kaw.dev.scheduler.utils.JSONUtils;

public class RunTests {

    /**
     * text files
     */
    private final String MAESTROS = "./output/maestros.txt";
    private final String DEF_HORS = "./output/defHors.txt";
    private final String TAB_GRUPOS = "./output/tabGrupos.txt";
    private final String TAB_MATERIAS = "./output/tabMaterias.txt";
    private final String TAB_SEMCARR = "./output/tabSemCarr.txt";
    private final String TIPO_HORS = "./output/tipoHors.txt";

    /**
     * .dat files
     */
    private final String TAB_AULAS = "./output/tabAulas.dat";
    private final String TAB_MAES = "./output/tabMaes.dat";

    /**
     * Dummy data
     */
    private final List<ISeparable> dummyTeachers = DummyData.getDummyTeachers();
    private final List<ISeparable> dummyScheduleTypes = DummyData.getDummyScheduleTypes();
    private final List<ISeparable> dummySchedules = DummyData.getDummySchedules();
    private final List<ISeparable> dummySubjects = DummyData.getDummySubjects();
    private final List<ISeparable> dummyCareers = DummyData.getDummyCareers();
    private final List<ISeparable> dummyGroups = DummyData.getDummyGroups();
    private final List<IHexable> dummyClassrooms = DummyData.getDummyClassrooms();
    private final List<IHexable> dummyTeachersHex = DummyData.getDummyTeachersHex();

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        new RunTests().run();
    }

    public void run() {
//        createTestFilesCsv();
//        createTestFilesDat();
//        System.out.println(readTestFilesCsv());
//        System.out.println(readTestFilesDat());
        //System.out.println(JSONUtils.jsonToMap(JSONUtils.mapToJSON(testCreateUniversity())));
//        System.out.println(testGetTeacherFromMap());
        System.out.println(testGetCareerFromMap());
    }

    private void createTestFilesCsv() {
        CsvManager.write(dummyTeachers, MAESTROS);
        CsvManager.write(dummyScheduleTypes, DEF_HORS);
        CsvManager.write(dummySchedules, TIPO_HORS);
        CsvManager.write(dummySubjects, TAB_MATERIAS);
        CsvManager.write(dummyCareers, TAB_SEMCARR);
        CsvManager.write(dummyGroups, TAB_GRUPOS);
    }

    private void createTestFilesDat() {
        HexManager.write(dummyClassrooms, TAB_AULAS);
        HexManager.write(dummyTeachersHex, TAB_MAES);
    }

    private String readTestFilesCsv() {
        StringBuilder sb = new StringBuilder();

        CsvManager.read(MAESTROS, SeparableType.TEACHER).forEach((item) -> sb.append(item).append("\n"));
        sb.append("\n");

        CsvManager.read(TIPO_HORS, SeparableType.SCHEDULE).forEach((item) -> sb.append(item).append("\n"));
        sb.append("\n");

        CsvManager.read(DEF_HORS, SeparableType.SCHEDULE_TYPE).forEach((item) -> sb.append(item).append("\n"));
        sb.append("\n");

        CsvManager.read(TAB_MATERIAS, SeparableType.SUBJECT).forEach((item) -> sb.append(item).append("\n"));
        sb.append("\n");

        CsvManager.read(TAB_SEMCARR, SeparableType.CAREER).forEach((item) -> sb.append(item).append("\n"));
        sb.append("\n");

        CsvManager.read(TAB_GRUPOS, SeparableType.GROUP).forEach((item) -> sb.append(item).append("\n"));
        sb.append("\n");

        return sb.toString();
    }

    private String readTestFilesDat() {
        StringBuilder sb = new StringBuilder();

        HexManager.read(TAB_AULAS, HexableType.CLASSROOM).forEach((item) -> sb.append(item).append("\n"));
        sb.append("\n");

        HexManager.read(TAB_MAES, HexableType.TEACHER).forEach((item) -> sb.append(item).append("\n"));

        return sb.toString();
    }

    private Map<String, Object> testCreateUniversity() {
        List<Career> careers = new ArrayList<>();

        for (ISeparable dummyCareer : dummyCareers) {
            Career career = (Career) dummyCareer;
            careers.add(career);
        }

        List<Group> groups = new ArrayList<>();

        for (ISeparable dummyGroup : dummyGroups) {
            Group group = (Group) dummyGroup;
            groups.add(group);
        }

        List<Schedule> schedules = new ArrayList<>();

        for (ISeparable dummySchedule : dummySchedules) {
            Schedule schedule = (Schedule) dummySchedule;
            schedules.add(schedule);
        }

        List<ScheduleType> scheduleTypes = new ArrayList<>();

        for (ISeparable dummyScheduleType : dummyScheduleTypes) {
            ScheduleType scheduleType = (ScheduleType) dummyScheduleType;
            scheduleTypes.add(scheduleType);
        }

        List<Subject> subjects = new ArrayList<>();

        for (ISeparable dummySubject : dummySubjects) {
            Subject subject = (Subject) dummySubject;
            subjects.add(subject);
        }

        List<Classroom> classrooms = new ArrayList<>();

        for (IHexable dummyClassroom : dummyClassrooms) {
            Classroom classroom = (Classroom) dummyClassroom;
            classrooms.add(classroom);
        }

        List<Teacher> teachers = new ArrayList<>();

        for (ISeparable dummyTeacher : dummyTeachers) {
            Teacher teacher = (Teacher) dummyTeacher;
            teachers.add(teacher);
        }

        University university = new University("university", careers, groups, schedules, scheduleTypes, subjects, classrooms, teachers);

        return university.toMap();
    }

    @SuppressWarnings("unchecked")
    private Career testGetCareerFromMap() {
        Career career = null;

        Map<String, Object> university = JSONUtils.jsonToMap(JSONUtils.mapToJSON(testCreateUniversity()));

        @SuppressWarnings("unchecked")
        Map<String, Object> careers = (Map<String, Object>) university.get("careers");

        for (Entry<String, Object> entry : careers.entrySet()) {
//            System.out.println(entry.getValue());
            career = (Career) MappableFactory.build(MappableType.CAREER, (Map<String, Object>) entry.getValue());
            break;
        }

        return career;
    }

    @SuppressWarnings("unchecked")
    private Teacher testGetTeacherFromMap() {
        Teacher teacher = null;

        Map<String, Object> university = JSONUtils.jsonToMap(JSONUtils.mapToJSON(testCreateUniversity()));

        @SuppressWarnings("unchecked")
        Map<String, Object> teachers = (Map<String, Object>) university.get("teachers");

        for (Entry<String, Object> entry : teachers.entrySet()) {
            //System.out.println(entry.getValue());
            teacher = (Teacher) MappableFactory.build(MappableType.TEACHER, (Map<String, Object>) entry.getValue());
            break;
        }

        return teacher;
    }

}
