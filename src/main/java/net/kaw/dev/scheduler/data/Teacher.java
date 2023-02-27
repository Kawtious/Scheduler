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

import java.util.HashMap;
import java.util.Map;
import net.kaw.dev.scheduler.data.interfaces.IHexable;
import net.kaw.dev.scheduler.data.interfaces.IMappable;
import net.kaw.dev.scheduler.data.interfaces.ISeparable;
import net.kaw.dev.scheduler.utils.HexUtils;

/**
 * Contains the identification information of the teacher and their schedule availability and is used as data entry for each teacher.
 * <p>
 * It is used to avoid having to type this data into the program that captures teacher schedules.
 */
public class Teacher implements IMappable, ISeparable, IHexable {

    /*
     * This indicates the type of teacher
     */
    private char type;

    /*
     * The teacher's control number
     */
    private int controlNumber;

    /*
     * The teacher's name
     */
    private String firstName;

    /*
     * The teacher's last name
     */
    private String lastName;

    /*
     * A sequence of integers that correspond to the mapping matrix of half hours in the schedule,
     * the matrix is 5 rows and 28 columns, the first data corresponds to the first hour of Monday,
     * second, third until reaching 27, then continues the first hour on Tuesday, the second,....,
     * etc, then Wednesday and so on until Friday. "Zero" means schedule unavailability and "one" availability.
     */
    private final ScheduleMap scheduleMap;

    protected Teacher(char type, int controlNumber, String firstName, String lastName) {
        this.type = type;
        this.controlNumber = controlNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.scheduleMap = new ScheduleMap(0);
    }

    protected Teacher(char type, int controlNumber, String firstName, String lastName, ScheduleMap scheduleMap) {
        this.type = type;
        this.controlNumber = controlNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.scheduleMap = scheduleMap;
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

    public ScheduleMap getScheduleMap() {
        return scheduleMap;
    }

    @Override
    public String toCsv() {
        return type + "," + controlNumber + "," + firstName + "," + lastName;
    }

    @Override
    public String toHex() {
        StringBuilder sb = new StringBuilder();

        sb.append(HexUtils.stringToHex(Character.toString(type), 8));

        sb.append(HexUtils.intToHex(controlNumber, 8));

        sb.append(HexUtils.stringToHex(firstName));

        sb.append(HexUtils.stringToHex(lastName));

        for (int day = 0; day < ScheduleMap.DAYS; day++) {
            for (int halfhour = 0; halfhour < ScheduleMap.HALFHOURS; halfhour++) {
                sb.append(HexUtils.intToHex(scheduleMap.getMapValue(day, halfhour), 8));
            }
        }

        return sb.toString();
    }

    @Override
    public boolean validate() {
        return controlNumber >= 0 && !firstName.isEmpty() && !lastName.isEmpty();
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>(4);

        map.put("type", type);
        map.put("controlNumber", controlNumber);
        map.put("firstName", firstName);
        map.put("lastName", lastName);
        map.put("scheduleMap", scheduleMap.toMap());

        return map;
    }

    @Override
    public String toString() {
        return "Teacher{" + "type=" + type + ", controlNumber=" + controlNumber + ", firstName=" + firstName + ", lastName=" + lastName + ", scheduleMap=" + scheduleMap + '}';
    }

}
