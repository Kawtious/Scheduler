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
package net.kaw.dev.scheduler.testing;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.kaw.dev.scheduler.files.CSV;
import net.kaw.dev.scheduler.model.ClassroomManager;
import net.kaw.dev.scheduler.model.ISeparatable;
import net.kaw.dev.scheduler.model.Schedule;
import net.kaw.dev.scheduler.model.ScheduleType;
import net.kaw.dev.scheduler.model.Subject;
import net.kaw.dev.scheduler.model.Teacher;

public class RunTests {

    /**
     * text files
     */
    private final Path MAESTROS = Paths.get("./maestros.txt");
    private final Path DEF_HORS = Paths.get("./defHors.txt");
    private final Path TAB_GRUPOS = Paths.get("./tabGrupos.txt");
    private final Path TAB_MATERIAS = Paths.get("./tabMaterias.txt");
    private final Path TAB_SEMCARR = Paths.get("./tabSemCarr.txt");
    private final Path TIPO_HORS = Paths.get("./tipoHors.txt");

    /**
     * .dat files
     */
    private final Path TAB_AULAS = Paths.get("./tabTest.dat");
    private final Path TAB_MAES = Paths.get("./tabMaes.dat");

    /**
     * Test lists
     */
    private final List<ISeparatable> teachersList = Teacher.getDummyTeachers();
    private final List<ISeparatable> scheduleTypesList = ScheduleType.getDummyScheduleTypes();
    private final List<ISeparatable> schedulesList = Schedule.getDummySchedules();
    private final List<ISeparatable> subjectsList = Subject.getDummySubjects();

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        new RunTests().run();
    }

    public void run() {
        createTestFiles();
        readTestFiles();
        ClassroomManager.create(TAB_AULAS, ClassroomManager.getDummyClassrooms());
    }

    private void readTestFiles() {
        try {
            StringBuilder sb = new StringBuilder();

            readCSV(MAESTROS, Teacher.class).forEach((item) -> sb.append(item).append("\n"));
            sb.append("\n");

            readCSV(TIPO_HORS, Schedule.class).forEach((item) -> sb.append(item).append("\n"));
            sb.append("\n");

            readCSV(DEF_HORS, ScheduleType.class).forEach((item) -> sb.append(item).append("\n"));
            sb.append("\n");

            readCSV(TAB_MATERIAS, Subject.class).forEach((item) -> sb.append(item).append("\n"));

            System.out.print(sb.toString());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RunTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createTestFiles() {
        CSV.createCSV(teachersList, MAESTROS);
        CSV.createCSV(scheduleTypesList, DEF_HORS);
        CSV.createCSV(schedulesList, TIPO_HORS);
        CSV.createCSV(subjectsList, TAB_MATERIAS);
    }

    private List<ISeparatable> readCSV(Path filepath, Class<?> type) throws FileNotFoundException {
        List<ISeparatable> objects = new ArrayList<>();

        if (type != Teacher.class && type != Schedule.class && type != ScheduleType.class && type != Subject.class) {
            return objects;
        }

        List<String> list = CSV.asList(filepath);

        for (String string : list) {
            ISeparatable item;

            if (type == Teacher.class) {
                item = Teacher.fromCSV(string);
            } else if (type == Schedule.class) {
                item = Schedule.fromCSV(string);
            } else if (type == ScheduleType.class) {
                item = ScheduleType.fromCSV(string);
            } else if (type == Subject.class) {
                item = Subject.fromCSV(string);
            } else {
                continue;
            }

            if (item != null) {
                objects.add(item);
            }
        }

        return objects;
    }

}
