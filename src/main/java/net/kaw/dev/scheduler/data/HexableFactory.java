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
package net.kaw.dev.scheduler.data;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.kaw.dev.scheduler.data.interfaces.IHexable;
import net.kaw.dev.scheduler.utils.HexUtils;

/**
 *
 * @author Waffle#4265 on Discord
 */
public class HexableFactory {

    public enum HexableType {
        CLASSROOM, TEACHER
    }

    public static List<IHexable> build(HexableType hexableType, String hexString) {
        if (hexString.isEmpty()) {
            return null;
        }

        switch (hexableType) {
            case CLASSROOM:
                return buildClassrooms(hexString);
            case TEACHER:
                return buildTeachers(hexString);
            default:
                return null;
        }
    }

    private static List<IHexable> buildClassrooms(String classroomsHexString) {
        int listSize = HexUtils.hexToInt(classroomsHexString.substring(0, 8));

        List<IHexable> classrooms = new ArrayList<>(listSize);

        try {
            /*
             * AAAAAAARRRGHHH MAAAAAAATH NOOOOOOOOOOOOO
             *
             * 4C 36 00 00 | 00 00 00 01 | 00 00 00 01 | 00 00 00 01  L6..............
             * 00 00 00 01 | 00 00 00 01 | 00 00 00 01 | 00 00 00 01  ................
             */
            String data = classroomsHexString.substring(8, classroomsHexString.length());

            int dataChunkSize = (ScheduleMap.DAYS * ScheduleMap.HALFHOURS) * 8;

            for (int i = 0, previousIndexEnd = 0; i < listSize; i++) {
                int currentIndexStart = previousIndexEnd;
                int currentIndexTypeStart = currentIndexStart;
                int currentIndexTypeEnd = currentIndexTypeStart + 8;
                int currentIndexClassMapStart = currentIndexTypeEnd;
                int currentIndexClassMapEnd = currentIndexClassMapStart + dataChunkSize;

                previousIndexEnd = currentIndexClassMapEnd;

                String type = HexUtils.hexToString(data.substring(currentIndexTypeStart, currentIndexTypeEnd));

                Classroom classroom = new Classroom(type);

                /*
                 * 00 00 00 01 | 00 00 00 01 | 00 00 00 01 | 00 00 00 01  ................
                 * 00 00 00 01 | 00 00 00 01 | 00 00 00 01 | 00 00 00 01  ................
                 */
                String[] mapValues = data.substring(currentIndexClassMapStart, currentIndexClassMapEnd).split("(?<=\\G.{8})");

                for (int day = 0; day < ScheduleMap.DAYS; day++) {
                    for (int halfhour = 0; halfhour < ScheduleMap.HALFHOURS; halfhour++) {
                        String mapValue = mapValues[halfhour + (day * ScheduleMap.HALFHOURS)];
                        classroom.getScheduleMap().setMapValue(day, halfhour, HexUtils.hexToInt(mapValue));
                    }
                }

                classrooms.add(classroom);
            }
        } catch (StringIndexOutOfBoundsException ex) {
            Logger.getLogger(HexableFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return classrooms;
    }

    private static List<IHexable> buildTeachers(String teachersHexString) {
        int listSize = HexUtils.hexToInt(teachersHexString.substring(0, 8));

        List<IHexable> teachers = new ArrayList<>(listSize);

        try {
            /*
             * 00 00 00 0A | 50 00 00 00 | 00 00 00 B0 | 52 6F 73 73  ....P......?Ross
             * 61 6E 61 00 | 57 61 6C 73 | 68 00 00 00 | 00 01 00 00  ana.Walsh.......
             */
            String data = teachersHexString.substring(8, teachersHexString.length());

            int dataChunkSize = (ScheduleMap.DAYS * ScheduleMap.HALFHOURS) * 8;

            for (int index = 0, previousIndexEnd = 0; index < listSize; index++) {
                int currentIndexStart = previousIndexEnd;

                int currentIndexTypeStart = currentIndexStart;
                int currentIndexTypeEnd = currentIndexTypeStart + 8;

                int currentIndexControlNumberStart = currentIndexTypeEnd;
                int currentIndexControlNumberEnd = currentIndexControlNumberStart + 8;

                int currentIndexFirstNameStart = currentIndexControlNumberEnd;
                int currentIndexFirstNameEnd = currentIndexFirstNameStart + 2;

                String[] hexByteFirstName = data.substring(currentIndexFirstNameStart, data.length()).split("(?<=\\G.{2})");

                for (int j = 0; j < hexByteFirstName.length; j++) {
                    if (hexByteFirstName[j].equals("00")) {
                        currentIndexFirstNameEnd += (j * 2);
                        break;
                    }
                }

                int currentIndexLastNameStart = currentIndexFirstNameEnd;
                int currentIndexLastNameEnd = currentIndexLastNameStart + 2;

                String[] hexByteLastName = data.substring(currentIndexLastNameStart, data.length()).split("(?<=\\G.{2})");

                for (int j = 0; j < hexByteLastName.length; j++) {
                    if (hexByteLastName[j].equals("00")) {
                        currentIndexLastNameEnd += (j * 2);
                        break;
                    }
                }

                int currentIndexClassMapStart = currentIndexLastNameEnd;
                int currentIndexClassMapEnd = currentIndexClassMapStart + dataChunkSize;

                previousIndexEnd = currentIndexClassMapEnd;

                char type = HexUtils.hexToString(data.substring(currentIndexTypeStart, currentIndexTypeEnd)).charAt(0);
                int controlNumber = HexUtils.hexToInt(data.substring(currentIndexControlNumberStart, currentIndexControlNumberEnd));
                String firstName = HexUtils.hexToString(data.substring(currentIndexFirstNameStart, currentIndexFirstNameEnd));
                String lastName = HexUtils.hexToString(data.substring(currentIndexLastNameStart, currentIndexLastNameEnd));

                Teacher teacher = new Teacher(type, controlNumber, firstName, lastName);

                /*
                 * 00 00 00 01 | 00 00 00 01 | 00 00 00 01 | 00 00 00 01  ................
                 * 00 00 00 01 | 00 00 00 01 | 00 00 00 01 | 00 00 00 01  ................
                 */
                String[] mapValues = data.substring(currentIndexClassMapStart, currentIndexClassMapEnd).split("(?<=\\G.{8})");

                for (int day = 0; day < ScheduleMap.DAYS; day++) {
                    for (int halfhour = 0; halfhour < ScheduleMap.HALFHOURS; halfhour++) {
                        String mapValue = mapValues[halfhour + (day * ScheduleMap.HALFHOURS)];
                        teacher.getScheduleMap().setMapValue(day, halfhour, HexUtils.hexToBoolean(mapValue));
                    }
                }

                teachers.add(teacher);
            }
        } catch (StringIndexOutOfBoundsException ex) {
            Logger.getLogger(HexableFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return teachers;
    }

}
