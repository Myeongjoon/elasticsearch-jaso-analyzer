package org.elasticsearch.analysis;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class OffsetMap {
    public OffsetMap() {
        this.map = new HashMap<>();
    }

    public boolean containsKey(String key) {
        return map.containsKey(key);
    }

    public Queue<BaseTokenizer.Offset> get(String key) {
        return map.get(key);
    }

    HashMap<String, Queue<BaseTokenizer.Offset>> map;

    public void addOffsetMap(String keyword, int start, int end) {
        BaseTokenizer.Offset offset = new BaseTokenizer.Offset();
        offset.start = start;
        offset.end = end;
        if (map.containsKey(keyword)) {
            Queue<BaseTokenizer.Offset> target = map.get(keyword);
            target.add(offset);
        } else {
            Queue<BaseTokenizer.Offset> queue = new LinkedList<>();
            queue.add(offset);
            map.put(keyword, queue);
        }
    }
}
