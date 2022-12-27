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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.kaw.dev.scheduler.data.HexableFactory;
import net.kaw.dev.scheduler.data.HexableFactory.HexableType;
import net.kaw.dev.scheduler.data.SeparableFactory;
import net.kaw.dev.scheduler.data.SeparableFactory.SeparableType;
import net.kaw.dev.scheduler.data.interfaces.IHexable;
import net.kaw.dev.scheduler.data.interfaces.ISeparable;
import net.kaw.dev.scheduler.io.CsvManager;
import net.kaw.dev.scheduler.io.HexManager;

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
        createTestFiles();
        readTestFilesCsv();
        readTestFilesDat();
    }

    private void createTestFiles() {
        CsvManager.createCSV(dummyTeachers, MAESTROS);
        CsvManager.createCSV(dummyScheduleTypes, DEF_HORS);
        CsvManager.createCSV(dummySchedules, TIPO_HORS);
        CsvManager.createCSV(dummySubjects, TAB_MATERIAS);
        CsvManager.createCSV(dummyCareers, TAB_SEMCARR);
        CsvManager.createCSV(dummyGroups, TAB_GRUPOS);

        HexManager.create(dummyClassrooms, TAB_AULAS);
        HexManager.create(dummyTeachersHex, TAB_MAES);
    }

    private void readTestFilesCsv() {
        try {
            StringBuilder sb = new StringBuilder();

            readCsv(MAESTROS, SeparableType.TEACHER).forEach((item) -> sb.append(item).append("\n"));
            sb.append("\n");

            readCsv(TIPO_HORS, SeparableType.SCHEDULE).forEach((item) -> sb.append(item).append("\n"));
            sb.append("\n");

            readCsv(DEF_HORS, SeparableType.SCHEDULE_TYPE).forEach((item) -> sb.append(item).append("\n"));
            sb.append("\n");

            readCsv(TAB_MATERIAS, SeparableType.SUBJECT).forEach((item) -> sb.append(item).append("\n"));
            sb.append("\n");

            readCsv(TAB_SEMCARR, SeparableType.CAREER).forEach((item) -> sb.append(item).append("\n"));
            sb.append("\n");

            readCsv(TAB_GRUPOS, SeparableType.GROUP).forEach((item) -> sb.append(item).append("\n"));
            sb.append("\n");

            System.out.print(sb.toString());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RunTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void readTestFilesDat() {
        try {
            StringBuilder sb = new StringBuilder();

            readHex(TAB_AULAS, HexableType.CLASSROOM).forEach((item) -> sb.append(item).append("\n"));

            System.out.print(sb.toString());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RunTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<ISeparable> readCsv(String filepath, SeparableType separableType) throws FileNotFoundException {
        List<ISeparable> objects = new ArrayList<>();

        List<String> list = CsvManager.asList(filepath);

        for (String string : list) {
            ISeparable item;

            item = SeparableFactory.build(separableType, string);

            if (item != null) {
                objects.add(item);
            }
        }

        return objects;
    }

    private List<IHexable> readHex(String filepath, HexableType hexableType) throws FileNotFoundException {
        List<IHexable> objects = new ArrayList<>();

        try {
            String hexString = HexManager.readFile(filepath);
            objects = HexableFactory.build(hexableType, hexString);
        } catch (IOException ex) {
            Logger.getLogger(RunTests.class.getName()).log(Level.SEVERE, null, ex);
        }

        return objects;
    }

}
