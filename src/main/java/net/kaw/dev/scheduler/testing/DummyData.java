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

import java.util.ArrayList;
import java.util.List;
import net.kaw.dev.scheduler.csv.CsvFactory;
import net.kaw.dev.scheduler.data.hexable.Classroom;
import net.kaw.dev.scheduler.data.separable.Career;
import net.kaw.dev.scheduler.data.separable.Schedule;
import net.kaw.dev.scheduler.data.separable.ScheduleType;
import net.kaw.dev.scheduler.data.separable.Subject;
import net.kaw.dev.scheduler.data.separable.Teacher;
import net.kaw.dev.scheduler.interfaces.IHexable;
import net.kaw.dev.scheduler.interfaces.ISeparable;

public class DummyData {

    protected static List<ISeparable> getDummyTeachers() {
        List<ISeparable> teachers = new ArrayList<>(11);

        String[] values = {
            "P,176,Rossana,Walsh",
            "P,195,Carrol,Bogisich",
            "P,216,Karyn,Keebler",
            "P,224,Walter,Wiza",
            "P,175,Shon,Cormier",
            "P,219,Odis,Lemke",
            "P,136,Stacy,Graham",
            "P,165,Mckinley,Cummings",
            "P,227,Rosalia,Spinka",
            "P,131,Roy,Kshlerin"};

        for (String value : values) {
            Teacher teacher = (Teacher) CsvFactory.build(CsvFactory.SeparableType.TEACHER, value);

            if (teacher != null) {
                teachers.add(teacher);
            }
        }
        return teachers;
    }

    protected static List<ISeparable> getDummySubjects() {
        List<ISeparable> subjects = new ArrayList<>(100);

        String[] values = {
            "EA01,AV,00000C00h,SIST CONTROL I C/LAB",
            "EA02,AV,00000C00h,SIST CONTROL II C/LAB",
            "EA03,AV,00000C00h,INST Y SERVO SIST C/LAB",
            "EA04,AV,00000C00h,INSTRUM ELECT I C/LAB",
            "EA07,AV,00000C00h,ELECTROMETRIA C/LAB",
            "EA08,AV,00000C00h,INSTR Y CONTROL",
            "EA09,L4,00000400h,MEDICIONES ELECTRICAS",
            "EAL1,LC,0000001Fh,LAB DE SIST CONTROL I",
            "EAL2,L5,0000001Fh,LAB DE SIST CONTROL II",
            "EAL3,L3,000003E0h,LAB DE INST Y SERVO SIST C/LAB",
            "EAL4,L3,000003E0h,LAB INSTRUM ELECT I",
            "EAL7,L6,000003E0h,LAB ELECTROMETRIA",
            "EC01,AV,00000C00h,ELECT. Y MAG. C/LAB",
            "EC02,AV,00000C00h,TEORIA ELECTROMAG I",
            "EC03,AV,00000C00h,T ELECTROMAG II C/LAB",
            "EC06,AV,00000C00h,T COMUNIC I C/LAB",
            "EC07,AV,00000C00h,T COMUNIC II C/LAB",
            "EC08,AV,00000C00h,SIST DE COMS I",
            "EC09,AV,00000C00h,SIST DE COMS II C/LAB",
            "ECL1,L1,0000001Fh,LAB ELECT. Y MAGNET.",
            "ECL3,L1,0000001Fh,LAB T ELECTROMAG II",
            "ECL6,L1,000003E0h,LAB T COMUNIC I",
            "ECL7,L5,000003E0h,LAB T COMUNIC II",
            "ECL9,LC,000003E0h,SIST DE COMS II C/LAB",
            "ED01,AC,00000C00h,COMPUTACION I (ING)",
            "ED02,AC,00000C00h,COMPUTACION II",
            "ED03,AV,00000400h,SIST DIGIT I C/LAB",
            "ED04,AV,00000C00h,SISTEMAS DIGITALES II",
            "ED05,AV,00000C00h,SIST DIGITALES III C/L",
            "ED06,AC,00000C00h,COMPUTACION III C/LAB",
            "ED07,AC,00003000h,INTR A LA COMP E INF",
            "ED08,AC,00000C00h,ALGORITMOS COMPUTAC",
            "ED09,AC,00000C00h,REDES Y SERV DE INF",
            "ED12,AV,00000C00h,SIS OPERATIVOS ABIERTO",
            "ED13,AV,00000C00h,OPTATIVA II(TEC EMERG)",
            "EDL3,L3,000003E0h,LAB SIST DIGITALES I",
            "EDL5,L5,000003E0h,LAB SIST DIGITALES III",
            "EDL6,LC,000003E0h,LAB COMPUTACION III",
            "EE01,AV,00000C00h,FISICA DEL EDO. SOLIDO",
            "EE02,AV,00000C00h,TEC MATER ELECTR C/LAB",
            "EE03,AV,00000C00h,ELECT POTENCIA I C/L",
            "EE04,AV,00000C00h,ELECT POTENCIA II C/L",
            "EE05,AV,00004000h,DISP ELECTRON I C/L",
            "EE06,AV,00000C00h,DISP ELECTRON II C/LAB",
            "EE86,AV,00000C00h,ELECTRONICA C/LAB",
            "EEL2,L6,0000001Fh,LAB TECNO MATER ELECT",
            "EEL3,L4,000003E0h,LAB ELECT POTENCIA I",
            "EEL4,L3,000003E0h,LAB ELECT POTENCIA II",
            "EEL5,L3,000003E0h,LAB DISP ELECTRON I",
            "EEL6,L3,000003E0h,LAB DISP ELECTRON II",
            "EELC,L4,000003E0h,LAB DE ELECTRONICA",
            "EF01,AV,00000C00h,INTROD A LA INGENIERIA",
            "EF02,AV,00000C00h,PROYECTO I",
            "EF03,AV,00000C00h,PROYECTO II",
            "EF04,AV,00000C00h,PROYECTO IV",
            "EI02,AV,00000C00h,INSTALA ELECTRICAS I C/LAB",
            "EI03,AV,00000C00h,INSTALA ELECTRICAS II",
            "EI04,AV,00000C00h,INSTALA ELECTRICAS III",
            "EI05,AV,00000C00h,SIST DE DISTRIBUCION",
            "EIL2,L6,0000001Fh,LAB DE INSTALA ELECTRICAS I",
            "EM01,AV,00000C00h,CONVER ENERGIA I C/LAB",
            "EM02,AV,00000C00h,CONVER ENERGIA II C/LAB",
            "EM03,AV,00000C00h,CONV ENERGIA III C/LAB",
            "EM04,AV,00000C00h,CONV ENERGIA IV C/LAB",
            "EM05,AV,00000C00h,MAQUINAS ELECTS C/LAB",
            "EM85,AV,00000C00h,MAQUINAS DE C.D. C/LAB",
            "EML1,L6,0000001Fh,LAB CONV ENERGIA I",
            "EML2,L6,0000001Fh,LAB CONV ENERGIA II",
            "EML3,L6,0000001Fh,LAB CONV ENERGIA III",
            "EML4,L6,0000001Fh,LAB CONV ENERGIA IV",
            "EML5,L6,000003E0h,LAB DE MAQUINAS ELECTS",
            "EMLF,L6,000003E0h,LAB MAQUINAS DE C.D.",
            "EO01,AV,00000C00h,OPTATIVA I(CTRL DIGIT)",
            "EO02,AV,00000C00h,OPTATIVA II(TOPICOS CONT)",
            "EO03,AV,00000C00h,OPTATIVA III",
            "EO04,AV,00000C00h,OPTATIVA I (CONTROL II)",
            "EO05,AV,00000C00h,OPTATIVA II(TELEFONIA)",
            "EO06,AV,00000C00h,OPTATIVA III",
            "EO07,AV,00000C00h,OPTATIVA IV",
            "EO09,AV,00000C00h,OPTATIVA II(SIS DIGIT)",
            "EP03,AV,00000C00h,SIST POTENCIA II C/LAB",
            "EP04,AV,00000C00h,CENTRALES ELECTRICAS",
            "EP05,AV,00000C00h,PROTECC ELECTRICAS C/L",
            "EPL3,L5,0000001Fh,LAB SIST POTENCIA II",
            "EPL5,L6,00000C00h,LAN PROTEC ELECTRICAS",
            "ES04,LC,00000C00h,ORG REDES Y SERV COMP",
            "ES08,AV,00000C00h,ACTUALIZA E INNOVA TEC",
            "ET01,AV,00004000h,CIRCUITOS ELECTR I C/L",
            "ET02,AV,00004000h,CIRC ELECTRICOS II C/L",
            "ET03,AV,00000C00h,ING ELECTRICA C/LAB",
            "ET04,AV,00000C00h,ELECTRICIDAD BASICA C/LAB",
            "ET85,AV,00000C00h,CIRCUITOS ELECT. C/LAB",
            "ET87,L2,00000C00h,ING. ELECTRICA II C/L",
            "ETL1,L4,0000001Fh,LAB CIRC ELECTRICOS I",
            "ETL2,LC,0000001Fh,LAB CIRC ELECTRICOS II",
            "ETL3,L2,0000001Fh,LAB DE ING ELECTRICA",
            "ETL4,L2,0000001Fh,LAB DE ELECT BASICA C/LAB",
            "ETLE,L2,000003E0h,LAB DE CIRCUITOS ELEC"};

        for (String value : values) {
            Subject subject = (Subject) CsvFactory.build(CsvFactory.SeparableType.SUBJECT, value);

            if (subject != null) {
                subjects.add(subject);
            }
        }
        return subjects;
    }

    protected static List<ISeparable> getDummyScheduleTypes() {
        List<ISeparable> scheduleTypes = new ArrayList<>(16);

        String[] values = {
            "Lu(2.0),01110111h,000001010101h",
            "Ma(2.0),01110111h,000002020202h",
            "Mi(2.0),01110111h,000004040404h",
            "Ju(2.0),01110111h,000008080808h",
            "Vi(2.0),01110111h,000010101010h",
            "Lu(3.0),00410041h,010101010101h",
            "Ma(3,0),00410041h,020202020202h",
            "Mi(3.0),00410041h,040404040404h",
            "Ju(3.0),00410041h,080808080808h",
            "Vi(3.0),00410041h,101010101010h",
            "MaJu(1.5),024A024Ah,0000000A0A0Ah",
            "LuMiVi(1.0),05550555h,000000001515h",
            "LuMi(2.0),01110111h,000005050505h",
            "MaJu(2.0),01110111h,00000A0A0A0Ah",
            "LuMaMiJuVi(1.0),05550555h,000000001F1Fh"};

        for (String value : values) {
            ScheduleType scheduleType = (ScheduleType) CsvFactory.build(CsvFactory.SeparableType.SCHEDULE_TYPE, value);

            if (scheduleType != null) {
                scheduleTypes.add(scheduleType);
            }
        }
        return scheduleTypes;
    }

    protected static List<ISeparable> getDummySchedules() {
        List<ISeparable> schedules = new ArrayList<>(100);

        String[] values = {
            "0,0,000001010101h,Lu(2.0)",
            "0,4,000001010101h,Lu(2.0)",
            "0,8,000001010101h,Lu(2.0)",
            "0,16,000001010101h,Lu(2.0)",
            "0,20,000001010101h,Lu(2.0)",
            "0,24,000001010101h,Lu(2.0)",
            "1,0,000002020202h,Ma(2.0)",
            "1,4,000002020202h,Ma(2.0)",
            "1,8,000002020202h,Ma(2.0)",
            "1,16,000002020202h,Ma(2.0)",
            "1,20,000002020202h,Ma(2.0)",
            "1,24,000002020202h,Ma(2.0)",
            "2,0,000004040404h,Mi(2.0)",
            "2,4,000004040404h,Mi(2.0)",
            "2,8,000004040404h,Mi(2.0)",
            "2,16,000004040404h,Mi(2.0)",
            "2,20,000004040404h,Mi(2.0)",
            "2,24,000004040404h,Mi(2.0)",
            "3,0,000008080808h,Ju(2.0)",
            "3,4,000008080808h,Ju(2.0)",
            "3,8,000008080808h,Ju(2.0)",
            "3,16,000008080808h,Ju(2.0)",
            "3,20,000008080808h,Ju(2.0)",
            "3,24,000008080808h,Ju(2.0)",
            "4,0,000010101010h,Vi(2.0)",
            "4,4,000010101010h,Vi(2.0)",
            "4,8,000010101010h,Vi(2.0)",
            "4,16,000010101010h,Vi(2.0)",
            "4,20,000010101010h,Vi(2.0)",
            "4,24,000010101010h,Vi(2.0)",
            "5,0,010101010101h,Lu(3.0)",
            "5,6,010101010101h,Lu(3.0)",
            "5,16,010101010101h,Lu(3.0)",
            "5,22,010101010101h,Lu(3.0)",
            "6,0,020202020202h,Ma(3.0)",
            "6,6,020202020202h,Ma(3.0)",
            "6,16,020202020202h,Ma(3.0)",
            "6,22,020202020202h,Ma(3.0)",
            "7,0,040404040404h,Mi(3.0)",
            "7,6,040404040404h,Mi(3.0)",
            "7,16,040404040404h,Mi(3.0)",
            "7,22,040404040404h,Mi(3.0)",
            "8,0,080808080808h,Ju(3.0)",
            "8,6,080808080808h,Ju(3.0)",
            "8,16,080808080808h,Ju(3.0)",
            "8,22,080808080808h,Ju(3.0)",
            "9,0,101010101010h,Vi(3.0)",
            "9,6,101010101010h,Vi(3.0)",
            "9,16,101010101010h,Vi(3.0)",
            "9,22,101010101010h,Vi(3.0)",
            "10,0,0000000A0A0Ah,MaJu(1.5)",
            "10,3,0000000A0A0Ah,MaJu(1.5)",
            "10,6,0000000A0A0Ah,MaJu(1.5)",
            "10,9,0000000A0A0Ah,MaJu(1.5)",
            "10,16,0000000A0A0Ah,MaJu(1.5)",
            "10,19,0000000A0A0Ah,MaJu(1.5)",
            "10,22,0000000A0A0Ah,MaJu(1.5)",
            "10,25,0000000A0A0Ah,MaJu(1.5)",
            "11,0,000000001515h,LuMiVi(1.0)",
            "11,2,000000001515h,LuMiVi(1.0)",
            "11,4,000000001515h,LuMiVi(1.0)",
            "11,6,000000001515h,LuMiVi(1.0)",
            "11,8,000000001515h,LuMiVi(1.0)",
            "11,10,000000001515h,LuMiVi(1.0)",
            "11,16,000000001515h,LuMiVi(1.0)",
            "11,18,000000001515h,LuMiVi(1.0)",
            "11,20,000000001515h,LuMiVi(1.0)",
            "11,22,000000001515h,LuMiVi(1.0)",
            "11,24,000000001515h,LuMiVi(1.0)",
            "11,26,000000001515h,LuMiVi(1.0)",
            "12,0,000005050505h,LuMi(2.0)",
            "12,4,000005050505h,LuMi(2.0)",
            "12,8,000005050505h,LuMi(2.0)",
            "12,16,000005050505h,LuMi(2.0)",
            "12,20,000005050505h,LuMi(2.0)",
            "12,24,000005050505h,LuMi(2.0)",
            "13,0,00000A0A0A0Ah,MaJu(2.0)",
            "13,4,00000A0A0A0Ah,MaJu(2.0)",
            "13,8,00000A0A0A0Ah,MaJu(2.0)",
            "13,16,00000A0A0A0Ah,MaJu(2.0)",
            "13,20,00000A0A0A0Ah,MaJu(2.0)",
            "13,24,00000A0A0A0Ah,MaJu(2.0)",
            "14,0,000000001F1Fh,LuMaMiJuVi(1.0)",
            "14,2,000000001F1Fh,LuMaMiJuVi(1.0)",
            "14,4,000000001F1Fh,LuMaMiJuVi(1.0)",
            "14,6,000000001F1Fh,LuMaMiJuVi(1.0)",
            "14,8,000000001F1Fh,LuMaMiJuVi(1.0)",
            "14,10,000000001F1Fh,LuMaMiJuVi(1.0)",
            "14,16,000000001F1Fh,LuMaMiJuVi(1.0)",
            "14,18,000000001F1Fh,LuMaMiJuVi(1.0)",
            "14,20,000000001F1Fh,LuMaMiJuVi(1.0)",
            "14,22,000000001F1Fh,LuMaMiJuVi(1.0)",
            "14,24,000000001F1Fh,LuMaMiJuVi(1.0)",
            "14,26,000000001F1Fh,LuMaMiJuVi(1.0)"};

        for (String value : values) {
            Schedule schedule = (Schedule) CsvFactory.build(CsvFactory.SeparableType.SCHEDULE, value);

            if (schedule != null) {
                schedules.add(schedule);
            }
        }
        return schedules;
    }

    protected static List<ISeparable> getDummyCareers() {
        List<ISeparable> careers = new ArrayList<>(65);

        String[] values = {
            "IE3,????,1,2",
            "IE3,ECL1,2,3",
            "IE3,EF01,3,4",
            "IE3,EA09,4,-1",
            "IE5,ED03,1,2",
            "IE5,EDL3,2,3",
            "IE5,ET02,3,4",
            "IE5,ETL2,4,5",
            "IE5,EC03,5,6",
            "IE5,ECL3,6,-1",
            "IEC7,ED05,1,2",
            "IEC7,EDL5,2,3",
            "IEC7,EF02,3,4",
            "IEC7,EO04,4,5",
            "IEC7,EC05,5,6",
            "IEC7,ECL5,6,7",
            "IEC7,EC06,7,8",
            "IEC7,ECL6,8,-1",
            "IEC9,EO06,1,2",
            "IEC9,EO07,2,3",
            "IEC9,EC08,3,4",
            "IEC9,EC09,4,5",
            "IEC9,ECL9,5,6",
            "IEC9,EF04,6,-1",
            "IEIC7,ED05,1,2",
            "IEIC7,EDL5,2,3",
            "IEIC7,EF02,3,4",
            "IEIC7,EO04,4,5",
            "IEIC7,EAL2,5,6",
            "IEIC7,EM05,6,7",
            "IEIC7,EML5,7,8",
            "IEIC7,EC06,8,9",
            "IEIC7,ECL6,9,-1",
            "IEIC9,EO03,1,2",
            "IEIC9,EF04,2,3",
            "IEIC9,EAL6,3,4",
            "IEIC9,EO02,4,5",
            "IEIC9,EA06,5,6",
            "IEIC9,EA05,6,7",
            "IEIC9,EAL5,7,-1",
            "IEL3,EC01,1,2",
            "IEL3,ECL1,2,3",
            "IEL3,EF01,3,4",
            "IEL3,EA09,4,5",
            "IEL3,EI02,5,6",
            "IEL3,EIL2,6,-1",
            "IEL5,EM02,1,2",
            "IEL5,EML2,2,3",
            "IEL5,ET02,3,4",
            "IEL5,ETL2,4,5",
            "IEL5,EC02,5,6",
            "IEL5,EE03,6,7",
            "IEL5,EEL3,7,8",
            "IEL5,EI04,8,-1",
            "IEL7,EM04,1,2",
            "IEL7,EML4,2,3",
            "IEL7,EA03,3,4",
            "IEL7,EAL3,4,5",
            "IEL7,EP02,5,6",
            "IEL7,EO08,6,7",
            "IEL7,EP01,7,-1"};

        for (String value : values) {
            Career career = (Career) CsvFactory.build(CsvFactory.SeparableType.CAREER, value);

            if (career != null) {
                careers.add(career);
            }
        }
        return careers;
    }

    protected static List<IHexable> getDummyClassrooms() {
        List<IHexable> classrooms = new ArrayList<>();

        Classroom cr1 = new Classroom("L6", 1);
        Classroom cr2 = new Classroom("L4", 1);
        Classroom cr3 = new Classroom("L2", 1);
        Classroom cr4 = new Classroom("AV", 30);
        Classroom cr5 = new Classroom("AC", 3);
        Classroom cr6 = new Classroom("LC", 2);
        Classroom cr7 = new Classroom("L1", 1);
        Classroom cr8 = new Classroom("L3", 1);
        Classroom cr9 = new Classroom("L5", 1);

        classrooms.add(cr1);
        classrooms.add(cr2);
        classrooms.add(cr3);
        classrooms.add(cr4);
        classrooms.add(cr5);
        classrooms.add(cr6);
        classrooms.add(cr7);
        classrooms.add(cr8);
        classrooms.add(cr9);

        return classrooms;
    }

    protected static List<IHexable> getDummyTeachersHex() {
        List<IHexable> teachers = new ArrayList<>();

        List<ISeparable> _teachers = getDummyTeachers();

        for (ISeparable _teacher : _teachers) {
            teachers.add((IHexable) _teacher);
        }

        return teachers;
    }

}
