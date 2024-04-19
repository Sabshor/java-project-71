package hexlet.code;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Compare {
    public static final String STATUS_REMOVED = "removed";
    public static final String STATUS_ADDED = "added";
    public static final String STATUS_UPDATED = "updated";
    public static final String STATUS_UNCHANGED = "unchanged";

    public static List<LinkedHashMap<String, Object>> getDifferenceMap(Map<String, Object> map1,
                                                                       Map<String, Object> map2) {
        LinkedHashMap<String, Object> mapUnion = new LinkedHashMap<>(map2);
        mapUnion.putAll(map1);
        return mapUnion.entrySet().stream()
                .map((e) -> {
                    var lineInfo = new LinkedHashMap<String, Object>();
                    String key = e.getKey();
                    lineInfo.put("key", key);
                    if (map2.containsKey(key)) {
                        var compareValue2 = map2.get(key) == null ? "null" : map2.get(key);
                        if (map1.containsKey(key)) {
                            var compareValue1 = map1.get(key) == null ? "null" : map1.get(key);
                            if (compareValue1.equals(compareValue2)) {
                                lineInfo.put("value", map1.get(key));
                                lineInfo.put("valueRemoved", "");
                                lineInfo.put("status", STATUS_UNCHANGED);
                            } else {
                                lineInfo.put("value", map2.get(key));
                                lineInfo.put("valueRemoved", map1.get(key));
                                lineInfo.put("status", STATUS_UPDATED);
                            }
                        } else {
                            lineInfo.put("value", map2.get(key));
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
                .sorted(Comparator.comparing(v -> String.valueOf(v.get("key"))))
                .toList();
    }
}
