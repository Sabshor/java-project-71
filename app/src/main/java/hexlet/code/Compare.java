package hexlet.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Compare {
    public static final String STATUS_REMOVED = "removed";
    public static final String STATUS_ADDED = "added";
    public static final String STATUS_UPDATED = "updated";
    public static final String STATUS_UNCHANGED = "unchanged";

    public static List<HashMap<String, Object>> getDifferenceMap(Map<String, Object> map1, Map<String, Object> map2) {
        TreeMap<String, Object> mapUnion = new TreeMap<>(map2);
        mapUnion.putAll(map1);
        return mapUnion.entrySet().stream()
                .map((e) -> {
                    var lineInfo = new HashMap<String, Object>();
                    String key = e.getKey();
                    lineInfo.put("key", key);
                    if (map2.containsKey(key)) {
                        var compareValue2 = map2.get(key) == null ? "null" : map2.get(key);
                        if (map1.containsKey(key)) {
                            var compareValue1 = map1.get(key) == null ? "null" : map1.get(key);
                            if (compareValue1.equals(compareValue2)) {
                                lineInfo.put("value", compareValue1);
                                lineInfo.put("valueRemoved", "");
                                lineInfo.put("status", STATUS_UNCHANGED);
                            } else {
                                lineInfo.put("value", compareValue2);
                                lineInfo.put("valueRemoved", compareValue1);
                                lineInfo.put("status", STATUS_UPDATED);
                            }
                        } else {
                            lineInfo.put("value", compareValue2);
                            lineInfo.put("valueRemoved", "");
                            lineInfo.put("status", STATUS_ADDED);
                        }
                    } else {
                        lineInfo.put("value", "");
                        lineInfo.put("valueRemoved", e.getValue());
                        lineInfo.put("status", STATUS_REMOVED);
                    }
                    return lineInfo;
                })
                .toList();
    }
}
