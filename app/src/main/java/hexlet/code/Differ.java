package hexlet.code;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        File file1 = getFile(filePath1);
        File file2 = getFile(filePath2);

        final TreeMap<String, String> map1 = Parser.getDataParse(file1);
        final TreeMap<String, String> map2 = Parser.getDataParse(file2);

        return compare(map1, map2);
    }

    public static String compare(Map<String, String> map1, Map<String, String> map2) {
        TreeMap<String, String> mapUnion = new TreeMap<>(map2);
        mapUnion.putAll(map1);
        return mapUnion.entrySet().stream()
                .map((e) -> {
                    if (map2.containsKey(e.getKey())) {
                        String resultCompare;
                        var compareValue2 = map2.get(e.getKey());
                        if (map1.containsKey(e.getKey())) {
                            var compareValue1 = map1.get(e.getKey());
                            if (compareValue1.equals(compareValue2)) {
                                resultCompare = getLineMessage(" ", e.getKey(), e.getValue());
                            } else {
                                resultCompare = getLineMessage("-", e.getKey(), compareValue1)
                                        .concat("\n")
                                        .concat(getLineMessage("+", e.getKey(), compareValue2));
                            }
                        } else {
                            resultCompare = getLineMessage("+", e.getKey(), compareValue2);
                        }
                        return resultCompare;
                    } else {
                        return getLineMessage("-", e.getKey(), e.getValue());
                    }
                })
                .collect(Collectors.joining("\n", "{\n", "\n}\n"));
    }

    private static String getLineMessage(String sign, String key, String value) {
        return "  ".concat(sign).concat(" ").concat(key).concat(": ").concat(value);
    }

    private static File getFile(String pathFile) throws Exception {
        File file1 = new File(pathFile);
        if (!file1.exists() || file1.isDirectory()) {
            throw new Exception("no such file " + pathFile);
        }
        return file1;
    }
}
