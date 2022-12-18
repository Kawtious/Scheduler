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

public class Teacher implements ISeparatable {

    private char type;

    private int controlNumber;

    private String firstName;

    private String lastName;

    public Teacher(char type, int controlNumber, String firstName, String lastName) {
        this.type = type;
        this.controlNumber = controlNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public int getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(int controlNumber) {
        this.controlNumber = controlNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static List<ISeparatable> getDummyTeachers() {
        List<ISeparatable> list = new ArrayList<>(11);

        list.add(new Teacher('P', 176, "Rossana", "Walsh"));
        list.add(new Teacher('P', 195, "Carrol", "Bogisich"));
        list.add(new Teacher('P', 216, "Karyn", "Keebler"));
        list.add(new Teacher('P', 224, "Walter", "Wiza"));
        list.add(new Teacher('P', 175, "Shon", "Cormier"));
        list.add(new Teacher('P', 219, "Odis", "Lemke"));
        list.add(new Teacher('P', 136, "Stacy", "Graham"));
        list.add(new Teacher('P', 165, "Mckinley", "Cummings"));
        list.add(new Teacher('P', 227, "Rosalia", "Spinka"));
        list.add(new Teacher('P', 131, "Roy", "Kshlerin"));
        return list;
    }

    public static Teacher fromCSV(String csv) {
        if (csv.isEmpty()) {
            return null;
        }

        String[] values = csv.split(",");

        int fields = 4;

        if (values.length != fields) {
            return null;
        }

        char type = values[0].charAt(0);
        int controlNumber = -1;
        String firstName = values[2];
        String lastName = values[3];

        try {
            controlNumber = Integer.parseInt(values[1]);
        } catch (NumberFormatException e) {
        }

        Teacher teacher = new Teacher(type, controlNumber, firstName, lastName);

        if (validate(teacher)) {
            return teacher;
        }

        return null;
    }

    public static boolean validate(Teacher teacher) {
        return teacher.getControlNumber() >= 0
                && !teacher.getFirstName().isEmpty() && !teacher.getLastName().isEmpty();
    }

    @Override
    public String toCSV() {
        return type + "," + controlNumber + "," + firstName + "," + lastName;
    }

    @Override
    public String toString() {
        return "Teacher{" + "type=" + type + ", controlNumber=" + controlNumber + ", firstName=" + firstName + ", lastName=" + lastName + '}';
    }

}
