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

import net.kaw.dev.scheduler.data.interfaces.ISeparable;

public class Group implements ISeparable {

    /*
     * The subject's key
     */
    private String subjectKey;

    /*
     * The purpose of this value is currently unknown
     */
    private int int_1;

    /*
     * The purpose of this value is currently unknown
     */
    private int int_2;

    protected Group(String subjectKey, int int_1, int int_2) {
        this.subjectKey = subjectKey;
        this.int_1 = int_1;
        this.int_2 = int_2;
    }

    public String getSubjectKey() {
        return subjectKey;
    }

    public void setSubjectKey(String subjectKey) {
        this.subjectKey = subjectKey;
    }

    public int getInt_1() {
        return int_1;
    }

    public void setInt_1(int int_1) {
        this.int_1 = int_1;
    }

    public int getInt_2() {
        return int_2;
    }

    public void setInt_2(int int_2) {
        this.int_2 = int_2;
    }

    @Override
    public String toCSV() {
        return subjectKey + "," + int_1 + "," + int_2;
    }

    @Override
    public boolean validate() {
        return !subjectKey.isEmpty() && int_1 >= 0 && int_2 >= 0;
    }

    @Override
    public String toString() {
        return "Group{" + "subjectKey=" + subjectKey + ", int_1=" + int_1 + ", int_2=" + int_2 + '}';
    }

}
