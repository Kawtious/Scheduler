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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.kaw.dev.scheduler.data.interfaces.IMappable;

public class Comment implements IMappable {

    public static final String ID_KEY = "id";

    public static final String CONTENT_KEY = "content";

    public static final String HALF_HOURS_KEY = "halfHours";

    private String id;

    private String content;

    private List<HalfHour> halfHours;

    public Comment(String content) {
        this.id = UUID.randomUUID().toString();
        this.content = content;
    }

    public Comment(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public Comment(String content, List<HalfHour> halfHours) {
        this.id = UUID.randomUUID().toString();
        this.content = content;
    }

    public Comment(String id, String content, List<HalfHour> halfHours) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<HalfHour> getHalfHours() {
        return Collections.unmodifiableList(halfHours);
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();

        map.put(ID_KEY, id);
        map.put(CONTENT_KEY, content);

        Map<String, Object> halfHoursMap = new HashMap<>();

        for (HalfHour halfHour : halfHours) {
            halfHoursMap.put(halfHour.getId(), halfHour.toMap());
        }

        map.put(HALF_HOURS_KEY, halfHoursMap);

        return map;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", content=" + content + '}';
    }

}
