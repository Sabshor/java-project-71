package hexlet.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Compare {
    public static List<HashMap<String, Object>> getDifferenceMap(Map<String, Object> map1, Map<String, Object> map2) {
        TreeMap<String, Object> mapUnion = new TreeMap<>(map2);
        mapUnion.putAll(map1);
        return mapUnion.entrySet().stream()
                .map((e) -> {
                    var lineInfo = new HashMap<String, Object>();
                    lineInfo.put("key", e.getKey());
                    if (map2.containsKey(e.getKey())) {
                        var compareValue2 = map2.get(e.getKey()) == null ? "null" : map2.get(e.getKey());
                        if (map1.containsKey(e.getKey())) {
                            var compareValue1 = map1.get(e.getKey()) == null ? "null" : map1.get(e.getKey());
                            if (compareValue1.equals(compareValue2)) {
                                lineInfo.put("value", compareValue1);
                                lineInfo.put("valueRemoved", "");
                                lineInfo.put("status", "unchanged");
                            } else {
                                lineInfo.put("value", compareValue2);
                                lineInfo.put("valueRemoved", compareValue1);
                                lineInfo.put("status", "updated");
                            }
                        } else {
                            lineInfo.put("value", compareValue2);
                            lineInfo.put("valueRemoved", "");
                            lineInfo.put("status", "added");
                        }
                    } else {
                        lineInfo.put("value", "");
                        lineInfo.put("valueRemoved", e.getValue());
                        lineInfo.put("status", "removed");
                    }
                    return lineInfo;
                })
                .toList();
    }
}
