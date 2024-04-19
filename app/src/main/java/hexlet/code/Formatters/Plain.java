package hexlet.code.Formatters;

import hexlet.code.Compare;
import java.util.List;
import java.util.Map;

public class Plain {

    static final String COMPLEX_VALUE = "[complex value]";
    public static String setPlainFormat(Map<String, Object> map) {
        return switch (map.get("status").toString()) {
            case Compare.STATUS_ADDED,
                 Compare.STATUS_UNCHANGED -> getLineMessage(map.get("status").toString(),
                                                map.get("key"), map.get("value"), null);
            case Compare.STATUS_REMOVED   -> getLineMessage(map.get("status").toString(),
                                                map.get("key"), null, map.get("valueRemoved"));
            case Compare.STATUS_UPDATED   -> getLineMessage(map.get("status").toString(),
                                                map.get("key"), map.get("value"), map.get("valueRemoved"));
            default -> throw new RuntimeException("Error parse unknown diff status");
        };
    }

    private static String getLineMessage(String sign, Object key, Object value, Object valueRemoved) {
        String keyValue = String.valueOf(key);
        if (sign.equals(Compare.STATUS_UNCHANGED)) {
            return "";
        }
        StringBuilder sb = new StringBuilder("Property '".concat(keyValue)
                                            .concat("' was ").concat(sign));
        if (sign.equals(Compare.STATUS_REMOVED)) {
            return sb.toString();
        }
        if (sign.equals(Compare.STATUS_ADDED)) {
            sb.append(" with value: ");
            sb.append(getComplexValue(value));
            return sb.toString();
        }
        sb.append(". From ");
        sb.append(getComplexValue(valueRemoved));
        sb.append(" to ");
        sb.append(getComplexValue(value));

        return sb.toString();
    }

    private static String getComplexValue(Object o1) {
        if (o1 instanceof String) {
            if (!"null".equals(String.valueOf(o1))) {
                return "'".concat(String.valueOf(o1)).concat("'");
            }
        }
        if ((o1 instanceof List<?>) || (o1 instanceof Map<?, ?>)) {
            return COMPLEX_VALUE;
        }

        return String.valueOf(o1);
    }
}
