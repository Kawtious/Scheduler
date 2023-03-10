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
import java.util.UUID;
import net.kaw.dev.scheduler.data.interfaces.IMappable;
import net.kaw.dev.scheduler.data.interfaces.ISeparable;

public class Group implements IMappable, ISeparable {

    public static final String ID_KEY = "id";

    public static final String SUBJECT_KEY_KEY = "subjectKey";

    public static final String INT_1_KEY = "int_1";

    public static final String INT_2_KEY = "int_2";

    private String id;

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

    public Group(String subjectKey, int int_1, int int_2) {
        this.id = UUID.randomUUID().toString();
        this.subjectKey = subjectKey;
        this.int_1 = int_1;
        this.int_2 = int_2;
    }

    public Group(Subject subject, int int_1, int int_2) {
        this.id = UUID.randomUUID().toString();
        this.subjectKey = subject.getSubjectKey();
        this.int_1 = int_1;
        this.int_2 = int_2;
    }

    public Group(String id, String subjectKey, int int_1, int int_2) {
        this.id = id;
        this.subjectKey = subjectKey;
        this.int_1 = int_1;
        this.int_2 = int_2;
    }

    public Group(String id, Subject subject, int int_1, int int_2) {
        this.id = id;
        this.subjectKey = subject.getSubjectKey();
        this.int_1 = int_1;
        this.int_2 = int_2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    public String toCsv() {
        return subjectKey + "," + int_1 + "," + int_2;
    }

    @Override
    public boolean validate() {
        return !subjectKey.isEmpty() && int_1 >= 0 && int_2 >= 0;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>(3);

        map.put(ID_KEY, id);
        map.put(SUBJECT_KEY_KEY, subjectKey);
        map.put(INT_1_KEY, int_1);
        map.put(INT_2_KEY, int_2);

        return map;
    }

    @Override
    public String toString() {
        return "Group{" + "id=" + id + ", subjectKey=" + subjectKey + ", int_1=" + int_1 + ", int_2=" + int_2 + '}';
    }

}
