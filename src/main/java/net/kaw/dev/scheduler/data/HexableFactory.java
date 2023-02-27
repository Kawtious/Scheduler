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
        /*
         * Make sure hexString is a valid hexadecimal string
         */
        if (!hexString.matches("^[0-9a-fA-F]+$")) {
            return null;
        }

        List<IHexable> hexable = null;

        /*
         * Check what type of data must be built, and use the corresponding build method
         */
        switch (hexableType) {
            case CLASSROOM:
                hexable = buildClassrooms(hexString);
                break;
            case TEACHER:
                hexable = buildTeachers(hexString);
                break;
        }

        return hexable;
    }

    private static List<IHexable> buildClassrooms(String classroomsHexString) {
        List<IHexable> classrooms = null;

        try {
            /*
             * Classroom data is structured in a funny way.
             * The first 8 bytes represent the size of the list of classrooms in the data,
             * which thankfully lets us save memory by setting a fixed size on the ArrayList.
             * 
             * This reads the first 8 bytes and creates the new ArrayList
             */
            int listSize = HexUtils.hexToInt(classroomsHexString.substring(0, 8));
            classrooms = new ArrayList<>(listSize);

            /*
             * AAAAAAARRRGHHH MAAAAAAATH NOOOOOOOOOOOOO
             *
             * We now have the ArrayList ready to be filled with classrooms.
             * To make our job easier, we remove the first 8 bytes from the string
             * since we no longer need them; now we can focus on the rest of the data.
             * 
             * This allows us to "reset" the index, and access the data at index 0
             * without having to do any funny math.
             *
             * 4C 36 00 00 | 00 00 00 01 | 00 00 00 01 | 00 00 00 01  L6..............
             * 00 00 00 01 | 00 00 00 01 | 00 00 00 01 | 00 00 00 01  ................
             */
            String data = classroomsHexString.substring(8, classroomsHexString.length());

            /*
             * There are 5 days and 28 half hours for each day, making it 5 * 28
             * different values that are stored in the string. Each one of these
             * uses 8 bytes.
             *
             * 00 00 00 01 | 00 00 00 01 | 00 00 00 01 | 00 00 00 01  ................
             *
             * This value simply sets how many characters the schedule map covers
             * in the data
             */
            int dataChunkSize = (ScheduleMap.DAYS * ScheduleMap.HALFHOURS) * 8;

            /*
             * Here's a funny for loop
             *
             * We trust that listSize is correct and we proceed to go over the
             * data, scraping every value and turning it into classrooms
             */
            for (int i = 0, previousIndexEnd = 0; i < listSize; i++) {
                /*
                 * I used to do the math for this manually, which was truly a nightmare.
                 *
                 * This defines the ranges of bytes in the data that we must access when
                 * extracting certain values from it
                 */
                int currentIndexStart = previousIndexEnd;
                int currentIndexTypeStart = currentIndexStart;
                int currentIndexTypeEnd = currentIndexTypeStart + 8;
                int currentIndexClassMapStart = currentIndexTypeEnd;
                int currentIndexClassMapEnd = currentIndexClassMapStart + dataChunkSize;

                /*
                 * We save this value for the next loop.
                 * This lets us know where the data of this current loop ends, so we can
                 * start a new loop right at the end of the previous loop
                 */
                previousIndexEnd = currentIndexClassMapEnd;

                /*
                 * Now that we have defined all indexes, we now start extracting the data to create
                 * a Classroom object
                 */
                String type = HexUtils.hexToString(data.substring(currentIndexTypeStart, currentIndexTypeEnd));

                Classroom classroom = new Classroom(type);

                /*
                 * We remove the "type" bytes to "reset" the index again,
                 * so it now looks like this:
                 *
                 * 00 00 00 01 | 00 00 00 01 | 00 00 00 01 | 00 00 00 01  ................
                 * 00 00 00 01 | 00 00 00 01 | 00 00 00 01 | 00 00 00 01  ................
                 *
                 * This saves us from having to do some more funny math to build
                 * the schedule map of the classroom
                 */
                String[] mapValues = data.substring(currentIndexClassMapStart, currentIndexClassMapEnd).split("(?<=\\G.{8})");

                for (int day = 0; day < ScheduleMap.DAYS; day++) {
                    int dayIndex = day * ScheduleMap.HALFHOURS;

                    for (int halfhour = 0; halfhour < ScheduleMap.HALFHOURS; halfhour++) {
                        String mapValue = mapValues[halfhour + (dayIndex)];
                        classroom.getScheduleMap().setMapValue(day, halfhour, HexUtils.hexToInt(mapValue));
                    }
                }

                classrooms.add(classroom);
            }
        } catch (StringIndexOutOfBoundsException ex) {
            /*
             * If the data is invalid, this exception might occur
             */
            Logger.getLogger(HexableFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return classrooms;
    }

    private static List<IHexable> buildTeachers(String teachersHexString) {
        List<IHexable> teachers = null;

        try {
            /*
             * I'll just copy comments from buildClassrooms to save time.
             *
             * The first 8 bytes represent the size of the list of teachers in the data,
             * which thankfully lets us save memory by setting a fixed size on the ArrayList.
             * 
             * This reads the first 8 bytes and creates the new ArrayList
             */
            int listSize = HexUtils.hexToInt(teachersHexString.substring(0, 8));
            teachers = new ArrayList<>(listSize);

            /*
             * Data here is formatted in a slightly more complex way compared to Classroom.
             * We make use of null terminators to determine when we should stop reading
             * a string and proceed to the next values.
             *
             * 00 00 00 0A | 50 00 00 00 | 00 00 00 B0 | 52 6F 73 73  ....P......?Ross
             * 61 6E 61 00 | 57 61 6C 73 | 68 00 00 00 | 00 01 00 00  ana.Walsh.......
             *          ^^ Null terminator    ^^ Null terminator
             */
            String data = teachersHexString.substring(8, teachersHexString.length());

            /*
             * There are 5 days and 28 half hours for each day, making it 5 * 28
             * different values that are stored in the string. Each one of these
             * uses 8 bytes.
             *
             * 00 00 00 01 | 00 00 00 01 | 00 00 00 01 | 00 00 00 01  ................
             *
             * This value simply sets how many characters the schedule map covers
             * in the data
             */
            int dataChunkSize = (ScheduleMap.DAYS * ScheduleMap.HALFHOURS) * 8;

            /*
             * We trust that listSize is correct and we proceed to go over the
             * data, scraping every value and turning it into classrooms
             */
            for (int index = 0, previousIndexEnd = 0; index < listSize; index++) {
                int currentIndexStart = previousIndexEnd;

                int currentIndexTypeStart = currentIndexStart;
                int currentIndexTypeEnd = currentIndexTypeStart + 8;

                int currentIndexControlNumberStart = currentIndexTypeEnd;
                int currentIndexControlNumberEnd = currentIndexControlNumberStart + 8;

                int currentIndexFirstNameStart = currentIndexControlNumberEnd;
                int currentIndexFirstNameEnd = currentIndexFirstNameStart + 2; // This +2 takes into account the null terminator

                /*
                 * This code is interesting. We split every byte (every XX value) from the data and look for a null terminator.
                 * If no null terminator is found then a "buffer overflow" occurs, which should never happen
                 * as long as the data fed to the method is not invalid.
                 */
                String[] hexByteFirstName = data.substring(currentIndexFirstNameStart, data.length()).split("(?<=\\G.{2})");

                for (int j = 0; j < hexByteFirstName.length; j++) {
                    if (hexByteFirstName[j].equals("00")) {
                        currentIndexFirstNameEnd += (j * 2);
                        break;
                    }
                }

                int currentIndexLastNameStart = currentIndexFirstNameEnd;
                int currentIndexLastNameEnd = currentIndexLastNameStart + 2;

                /*
                 * We do the same thing we did for the first name of the teacher, but this time we do it for the last name
                 */
                String[] hexByteLastName = data.substring(currentIndexLastNameStart, data.length()).split("(?<=\\G.{2})");

                for (int j = 0; j < hexByteLastName.length; j++) {
                    if (hexByteLastName[j].equals("00")) {
                        currentIndexLastNameEnd += (j * 2);
                        break;
                    }
                }

                int currentIndexClassMapStart = currentIndexLastNameEnd;
                int currentIndexClassMapEnd = currentIndexClassMapStart + dataChunkSize;

                /*
                 * We save this value for the next loop.
                 * This lets us know where the data of this current loop ends, so we can
                 * start a new loop right at the end of the previous loop
                 */
                previousIndexEnd = currentIndexClassMapEnd;

                /*
                 * Now that we have defined all indexes, we now start extracting the data to create
                 * a Teacher object
                 */
                char type = HexUtils.hexToString(data.substring(currentIndexTypeStart, currentIndexTypeEnd)).charAt(0);
                int controlNumber = HexUtils.hexToInt(data.substring(currentIndexControlNumberStart, currentIndexControlNumberEnd));
                String firstName = HexUtils.hexToString(data.substring(currentIndexFirstNameStart, currentIndexFirstNameEnd));
                String lastName = HexUtils.hexToString(data.substring(currentIndexLastNameStart, currentIndexLastNameEnd));

                Teacher teacher = new Teacher(type, controlNumber, firstName, lastName);

                /*
                 * We remove any bytes not related to the schedule map to "reset" the index again,
                 * so it now looks like this:
                 *
                 * 00 00 00 01 | 00 00 00 01 | 00 00 00 01 | 00 00 00 01  ................
                 * 00 00 00 01 | 00 00 00 01 | 00 00 00 01 | 00 00 00 01  ................
                 *
                 * This saves us from having to do some more funny math to build
                 * the schedule map of the classroom
                 */
                String[] mapValues = data.substring(currentIndexClassMapStart, currentIndexClassMapEnd).split("(?<=\\G.{8})");

                for (int day = 0; day < ScheduleMap.DAYS; day++) {
                    int dayIndex = day * ScheduleMap.HALFHOURS;

                    for (int halfhour = 0; halfhour < ScheduleMap.HALFHOURS; halfhour++) {
                        String mapValue = mapValues[halfhour + (dayIndex)];
                        teacher.getScheduleMap().setMapValue(day, halfhour, HexUtils.hexToInt(mapValue));
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
