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
package net.kaw.dev.scheduler.model;

import java.util.ArrayList;
import java.util.List;

public class Subject implements ISeparatable {

    private String subjectKey;

    private String classKey;

    private String schedule;

    private String description;

    public Subject(String subjectKey, String classKey, String schedule, String description) {
        this.subjectKey = subjectKey;
        this.classKey = classKey;
        this.schedule = schedule;
        this.description = description;
    }

    public String getSubjectKey() {
        return subjectKey;
    }

    public void setSubjectKey(String subjectKey) {
        this.subjectKey = subjectKey;
    }

    public String getClassKey() {
        return classKey;
    }

    public void setClassKey(String classKey) {
        this.classKey = classKey;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static List<ISeparatable> getDummySubjects() {
        List<ISeparatable> list = new ArrayList<>(100);

        list.add(new Subject("EA01", "AV", "00000C00h", "SIST CONTROL I C/LAB"));
        list.add(new Subject("EA02", "AV", "00000C00h", "SIST CONTROL II C/LAB"));
        list.add(new Subject("EA03", "AV", "00000C00h", "INST Y SERVO SIST C/LAB"));
        list.add(new Subject("EA04", "AV", "00000C00h", "INSTRUM ELECT I C/LAB"));
        list.add(new Subject("EA07", "AV", "00000C00h", "ELECTROMETRIA C/LAB"));
        list.add(new Subject("EA08", "AV", "00000C00h", "INSTR Y CONTROL"));
        list.add(new Subject("EA09", "L4", "00000400h", "MEDICIONES ELECTRICAS"));
        list.add(new Subject("EAL1", "LC", "0000001Fh", "LAB DE SIST CONTROL I"));
        list.add(new Subject("EAL2", "L5", "0000001Fh", "LAB DE SIST CONTROL II"));
        list.add(new Subject("EAL3", "L3", "000003E0h", "LAB DE INST Y SERVO SIST C/LAB"));
        list.add(new Subject("EAL4", "L3", "000003E0h", "LAB INSTRUM ELECT I"));
        list.add(new Subject("EAL7", "L6", "000003E0h", "LAB ELECTROMETRIA"));
        list.add(new Subject("EC01", "AV", "00000C00h", "ELECT. Y MAG. C/LAB"));
        list.add(new Subject("EC02", "AV", "00000C00h", "TEORIA ELECTROMAG I"));
        list.add(new Subject("EC03", "AV", "00000C00h", "T ELECTROMAG II C/LAB"));
        list.add(new Subject("EC06", "AV", "00000C00h", "T COMUNIC I C/LAB"));
        list.add(new Subject("EC07", "AV", "00000C00h", "T COMUNIC II C/LAB"));
        list.add(new Subject("EC08", "AV", "00000C00h", "SIST DE COMS I"));
        list.add(new Subject("EC09", "AV", "00000C00h", "SIST DE COMS II C/LAB"));
        list.add(new Subject("ECL1", "L1", "0000001Fh", "LAB ELECT. Y MAGNET."));
        list.add(new Subject("ECL3", "L1", "0000001Fh", "LAB T ELECTROMAG II"));
        list.add(new Subject("ECL6", "L1", "000003E0h", "LAB T COMUNIC I"));
        list.add(new Subject("ECL7", "L5", "000003E0h", "LAB T COMUNIC II"));
        list.add(new Subject("ECL9", "LC", "000003E0h", "SIST DE COMS II C/LAB"));
        list.add(new Subject("ED01", "AC", "00000C00h", "COMPUTACION I (ING)"));
        list.add(new Subject("ED02", "AC", "00000C00h", "COMPUTACION II"));
        list.add(new Subject("ED03", "AV", "00000400h", "SIST DIGIT I C/LAB"));
        list.add(new Subject("ED04", "AV", "00000C00h", "SISTEMAS DIGITALES II"));
        list.add(new Subject("ED05", "AV", "00000C00h", "SIST DIGITALES III C/L"));
        list.add(new Subject("ED06", "AC", "00000C00h", "COMPUTACION III C/LAB"));
        list.add(new Subject("ED07", "AC", "00003000h", "INTR A LA COMP E INF"));
        list.add(new Subject("ED08", "AC", "00000C00h", "ALGORITMOS COMPUTAC"));
        list.add(new Subject("ED09", "AC", "00000C00h", "REDES Y SERV DE INF"));
        list.add(new Subject("ED12", "AV", "00000C00h", "SIS OPERATIVOS ABIERTO"));
        list.add(new Subject("ED13", "AV", "00000C00h", "OPTATIVA II(TEC EMERG)"));
        list.add(new Subject("EDL3", "L3", "000003E0h", "LAB SIST DIGITALES I"));
        list.add(new Subject("EDL5", "L5", "000003E0h", "LAB SIST DIGITALES III"));
        list.add(new Subject("EDL6", "LC", "000003E0h", "LAB COMPUTACION III"));
        list.add(new Subject("EE01", "AV", "00000C00h", "FISICA DEL EDO. SOLIDO"));
        list.add(new Subject("EE02", "AV", "00000C00h", "TEC MATER ELECTR C/LAB"));
        list.add(new Subject("EE03", "AV", "00000C00h", "ELECT POTENCIA I C/L"));
        list.add(new Subject("EE04", "AV", "00000C00h", "ELECT POTENCIA II C/L"));
        list.add(new Subject("EE05", "AV", "00004000h", "DISP ELECTRON I C/L"));
        list.add(new Subject("EE06", "AV", "00000C00h", "DISP ELECTRON II C/LAB"));
        list.add(new Subject("EE86", "AV", "00000C00h", "ELECTRONICA C/LAB"));
        list.add(new Subject("EEL2", "L6", "0000001Fh", "LAB TECNO MATER ELECT"));
        list.add(new Subject("EEL3", "L4", "000003E0h", "LAB ELECT POTENCIA I"));
        list.add(new Subject("EEL4", "L3", "000003E0h", "LAB ELECT POTENCIA II"));
        list.add(new Subject("EEL5", "L3", "000003E0h", "LAB DISP ELECTRON I"));
        list.add(new Subject("EEL6", "L3", "000003E0h", "LAB DISP ELECTRON II"));
        list.add(new Subject("EELC", "L4", "000003E0h", "LAB DE ELECTRONICA"));
        list.add(new Subject("EF01", "AV", "00000C00h", "INTROD A LA INGENIERIA"));
        list.add(new Subject("EF02", "AV", "00000C00h", "PROYECTO I"));
        list.add(new Subject("EF03", "AV", "00000C00h", "PROYECTO II"));
        list.add(new Subject("EF04", "AV", "00000C00h", "PROYECTO IV"));
        list.add(new Subject("EI02", "AV", "00000C00h", "INSTALA ELECTRICAS I C/LAB"));
        list.add(new Subject("EI03", "AV", "00000C00h", "INSTALA ELECTRICAS II"));
        list.add(new Subject("EI04", "AV", "00000C00h", "INSTALA ELECTRICAS III"));
        list.add(new Subject("EI05", "AV", "00000C00h", "SIST DE DISTRIBUCION"));
        list.add(new Subject("EIL2", "L6", "0000001Fh", "LAB DE INSTALA ELECTRICAS I"));
        list.add(new Subject("EM01", "AV", "00000C00h", "CONVER ENERGIA I C/LAB"));
        list.add(new Subject("EM02", "AV", "00000C00h", "CONVER ENERGIA II C/LAB"));
        list.add(new Subject("EM03", "AV", "00000C00h", "CONV ENERGIA III C/LAB"));
        list.add(new Subject("EM04", "AV", "00000C00h", "CONV ENERGIA IV C/LAB"));
        list.add(new Subject("EM05", "AV", "00000C00h", "MAQUINAS ELECTS C/LAB"));
        list.add(new Subject("EM85", "AV", "00000C00h", "MAQUINAS DE C.D. C/LAB"));
        list.add(new Subject("EML1", "L6", "0000001Fh", "LAB CONV ENERGIA I"));
        list.add(new Subject("EML2", "L6", "0000001Fh", "LAB CONV ENERGIA II"));
        list.add(new Subject("EML3", "L6", "0000001Fh", "LAB CONV ENERGIA III"));
        list.add(new Subject("EML4", "L6", "0000001Fh", "LAB CONV ENERGIA IV"));
        list.add(new Subject("EML5", "L6", "000003E0h", "LAB DE MAQUINAS ELECTS"));
        list.add(new Subject("EMLF", "L6", "000003E0h", "LAB MAQUINAS DE C.D."));
        list.add(new Subject("EO01", "AV", "00000C00h", "OPTATIVA I(CTRL DIGIT)"));
        list.add(new Subject("EO02", "AV", "00000C00h", "OPTATIVA II(TOPICOS CONT)"));
        list.add(new Subject("EO03", "AV", "00000C00h", "OPTATIVA III"));
        list.add(new Subject("EO04", "AV", "00000C00h", "OPTATIVA I (CONTROL II)"));
        list.add(new Subject("EO05", "AV", "00000C00h", "OPTATIVA II(TELEFONIA)"));
        list.add(new Subject("EO06", "AV", "00000C00h", "OPTATIVA III"));
        list.add(new Subject("EO07", "AV", "00000C00h", "OPTATIVA IV"));
        list.add(new Subject("EO09", "AV", "00000C00h", "OPTATIVA II(SIS DIGIT)"));
        list.add(new Subject("EP03", "AV", "00000C00h", "SIST POTENCIA II C/LAB"));
        list.add(new Subject("EP04", "AV", "00000C00h", "CENTRALES ELECTRICAS"));
        list.add(new Subject("EP05", "AV", "00000C00h", "PROTECC ELECTRICAS C/L"));
        list.add(new Subject("EPL3", "L5", "0000001Fh", "LAB SIST POTENCIA II"));
        list.add(new Subject("EPL5", "L6", "00000C00h", "LAN PROTEC ELECTRICAS"));
        list.add(new Subject("ES04", "LC", "00000C00h", "ORG REDES Y SERV COMP"));
        list.add(new Subject("ES08", "AV", "00000C00h", "ACTUALIZA E INNOVA TEC"));
        list.add(new Subject("ET01", "AV", "00004000h", "CIRCUITOS ELECTR I C/L"));
        list.add(new Subject("ET02", "AV", "00004000h", "CIRC ELECTRICOS II C/L"));
        list.add(new Subject("ET03", "AV", "00000C00h", "ING ELECTRICA C/LAB"));
        list.add(new Subject("ET04", "AV", "00000C00h", "ELECTRICIDAD BASICA C/LAB"));
        list.add(new Subject("ET85", "AV", "00000C00h", "CIRCUITOS ELECT. C/LAB"));
        list.add(new Subject("ET87", "L2", "00000C00h", "ING. ELECTRICA II C/L"));
        list.add(new Subject("ETL1", "L4", "0000001Fh", "LAB CIRC ELECTRICOS I"));
        list.add(new Subject("ETL2", "LC", "0000001Fh", "LAB CIRC ELECTRICOS II"));
        list.add(new Subject("ETL3", "L2", "0000001Fh", "LAB DE ING ELECTRICA"));
        list.add(new Subject("ETL4", "L2", "0000001Fh", "LAB DE ELECT BASICA C/LAB"));
        list.add(new Subject("ETLE", "L2", "000003E0h", "LAB DE CIRCUITOS ELEC"));
        return list;
    }

    public static Subject fromCSV(String csv) {
        if (csv.isEmpty()) {
            return null;
        }

        String[] values = csv.split(",");

        int fields = 4;

        if (values.length != fields) {
            return null;
        }

        String subjectKey = values[0];
        String classKey = values[1];
        String schedule = values[2];
        String description = values[3];

        Subject subject = new Subject(subjectKey, classKey, schedule, description);

        if (validate(subject)) {
            return subject;
        }

        return null;
    }

    public static boolean validate(Subject subject) {
        return !subject.getSubjectKey().isEmpty() && !subject.getClassKey().isEmpty()
                && !subject.getSchedule().isEmpty() && !subject.getDescription().isEmpty();
    }

    @Override
    public String toCSV() {
        return subjectKey + "," + classKey + "," + schedule + "," + description;
    }

    @Override
    public String toString() {
        return "Subject{" + "subjectKey=" + subjectKey + ", classKey=" + classKey + ", schedule=" + schedule + ", description=" + description + '}';
    }

}
