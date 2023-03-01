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

public class HalfHour implements IMappable {

    private String id;

    private Integer available;

    private Comment comment;

    public HalfHour(Integer available) {
        this.id = UUID.randomUUID().toString();
        this.available = available;
    }

    public HalfHour(Integer available, Comment comment) {
        this.id = UUID.randomUUID().toString();
        this.available = available;
        this.comment = comment;
    }

    public HalfHour(String id, Integer available) {
        this.id = id;
        this.available = available;
    }

    public HalfHour(String id, Integer available, Comment comment) {
        this.id = id;
        this.available = available;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();

        map.put("id", id);
        map.put("available", available);

        if (comment != null) {
            map.put("comment", comment.toMap());
        }

        return map;
    }

    @Override
    public String toString() {
        return "HalfHour{" + "id=" + id + ", available=" + available + ", comment=" + comment + '}';
    }

}
