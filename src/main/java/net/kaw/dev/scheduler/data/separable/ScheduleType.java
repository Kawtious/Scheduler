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
package net.kaw.dev.scheduler.data.separable;

import net.kaw.dev.scheduler.interfaces.ISeparable;

public class ScheduleType implements ISeparable {

    /*
     * Represents the name to describe the type of schedule
     */
    private String description;

    /*
     * Indicates in hexadecimal all the possible hours at which
     * you can start that session (and therefore the number of variants
     * that this type of schedule has)
     */
    private String availableHours;

    /*
     * The “half-hour” sessionMask that occupies this type of schedule
     */
    private String sessionMask;

    public ScheduleType(String description, String availableHours, String sessionMask) {
        this.description = description;
        this.availableHours = availableHours;
        this.sessionMask = sessionMask;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvailableHours() {
        return availableHours;
    }

    public void setAvailableHours(String availableHours) {
        this.availableHours = availableHours;
    }

    public String getSessionMask() {
        return sessionMask;
    }

    public void setSessionMask(String sessionMask) {
        this.sessionMask = sessionMask;
    }

    @Override
    public String toCSV() {
        return description + "," + availableHours + "," + sessionMask;
    }

    @Override
    public String toString() {
        return "ScheduleType{" + "description=" + description + ", availableHours=" + availableHours + ", sessionMask=" + sessionMask + '}';
    }

}
