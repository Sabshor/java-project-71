package hexlet.code.Formatter;

import java.util.HashMap;

public class Stylish {

    public static String setStylishFormat(HashMap<String, Object> map) {
        return switch (map.get("status").toString()) {
            case "added"     -> getLineMessage("+", map.get("key"), map.get("value"));
            case "removed"   -> getLineMessage("-", map.get("key"), map.get("valueRemoved"));
            case "unchanged" -> getLineMessage(" ", map.get("key"), map.get("value"));
            case "updated"   -> getLineMessage("-", map.get("key"), map.get("valueRemoved"))
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
