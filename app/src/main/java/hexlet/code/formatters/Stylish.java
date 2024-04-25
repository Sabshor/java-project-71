package hexlet.code.formatters;

import hexlet.code.Compare;
import java.util.Map;

public class Stylish {
    public static String setStylishFormat(Map<String, Object> map) {
        return switch (map.get("status").toString()) {
            case Compare.STATUS_ADDED     -> getLineMessage("+", map.get("key"), map.get("value"));
            case Compare.STATUS_REMOVED   -> getLineMessage("-", map.get("key"), map.get("valueRemoved"));
            case Compare.STATUS_UNCHANGED -> getLineMessage(" ", map.get("key"), map.get("value"));
            case Compare.STATUS_UPDATED   -> getLineMessage("-", map.get("key"), map.get("valueRemoved"))
                                .concat("\n")
                                .concat(getLineMessage("+", map.get("key"), map.get("value")));
            default -> throw new RuntimeException("Error parse unknown diff status");
        };
    }

    private static String getLineMessage(String sign, Object key, Object value) {
        return "  ".concat(sign).concat(" ")
                   .concat(String.valueOf(key)).concat(": ")
                   .concat(String.valueOf(value));
    }
}
