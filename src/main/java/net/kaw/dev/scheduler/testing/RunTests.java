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

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import net.kaw.dev.scheduler.data.Career;
import net.kaw.dev.scheduler.data.factories.HexableFactory.HexableType;
import net.kaw.dev.scheduler.data.factories.MappableFactory;
import net.kaw.dev.scheduler.data.factories.MappableFactory.MappableType;
import net.kaw.dev.scheduler.data.factories.SeparableFactory.SeparableType;
import net.kaw.dev.scheduler.data.interfaces.IHexable;
import net.kaw.dev.scheduler.data.interfaces.ISeparable;
import net.kaw.dev.scheduler.firebase.FirebaseControl;
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

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        new RunTests().run();
    }

    public void run() throws InterruptedException, ExecutionException, TimeoutException {
        //testIO();
        testRest();
    }

    private void testRest() throws InterruptedException, ExecutionException, TimeoutException {
        String jsonString = "{"
                + "\"trajectoryStart\":0,"
                + "\"id\":\"123456\","
                + "\"subjectKey\":\"EAC2\","
                + "\"key\":\"IEC1\","
                + "\"trajectoryEnd\":1"
                + "}";

        System.out.println("JSON String received from external source: " + jsonString);

        Map<String, Object> map = JSONUtils.jsonToMap(jsonString);
        String elementId = (String) map.get(Career.ID_KEY);

        System.out.println("Convert JSON string to map to retrieve id: " + elementId);

        System.out.println("Element exists in database? " + !testRestGetCareer(elementId).equals("null"));

        System.out.print("Updating element in database... ");

        testRestUpdateCareer(jsonString);

        String careerJson = testRestGetCareer(elementId);

        System.out.println("Element exists in database? " + !testRestGetCareer(elementId).equals("null"));

        Career _career = (Career) MappableFactory.build(MappableType.CAREER, JSONUtils.jsonToMap(careerJson));

        System.out.println("Convert JSON string to object: " + _career);

        //System.out.println("Convert object to csv: " + _career.toCsv());
        System.out.print("Removing element from database... ");

        testRestRemoveCareer(elementId);

        System.out.println("Element exists in database? " + !testRestGetCareer(elementId).equals("null"));
    }

    private void testRestUpdateCareer(String jsonString) throws InterruptedException, ExecutionException, TimeoutException {
        FirebaseControl.Career.update(JSONUtils.jsonToMap(jsonString));
    }

    private String testRestGetCareer(String id) throws InterruptedException, ExecutionException, TimeoutException {
        return JSONUtils.mapToJSON(FirebaseControl.Career.get(id));
    }

    private void testRestRemoveCareer(String id) throws InterruptedException, ExecutionException, TimeoutException {
        FirebaseControl.Career.remove(id);
    }

    private void testIO() {
        testCsvCreateFiles();
        testDatCreateFiles();
        System.out.println(testCsvReadFiles());
        System.out.println(testDatReadFiles());
    }

    private void testCsvCreateFiles() {
        CsvManager.write(dummyTeachers, MAESTROS);
        CsvManager.write(dummyScheduleTypes, DEF_HORS);
        CsvManager.write(dummySchedules, TIPO_HORS);
        CsvManager.write(dummySubjects, TAB_MATERIAS);
        CsvManager.write(dummyCareers, TAB_SEMCARR);
        CsvManager.write(dummyGroups, TAB_GRUPOS);
    }

    private void testDatCreateFiles() {
        HexManager.write(dummyClassrooms, TAB_AULAS);
        HexManager.write(dummyTeachersHex, TAB_MAES);
    }

    private String testCsvReadFiles() {
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

    private String testDatReadFiles() {
        StringBuilder sb = new StringBuilder();

        HexManager.read(TAB_AULAS, HexableType.CLASSROOM).forEach((item) -> sb.append(item).append("\n"));
        sb.append("\n");

        HexManager.read(TAB_MAES, HexableType.TEACHER).forEach((item) -> sb.append(item).append("\n"));

        return sb.toString();
    }

}
