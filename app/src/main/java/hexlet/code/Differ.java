package hexlet.code;

import hexlet.code.Formatter.Stylish;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Differ {

    public static String generate(String filePath1, String filePath2, String fmt) throws Exception {
        File file1 = getFile(filePath1);
        File file2 = getFile(filePath2);

        final TreeMap<String, Object> map1 = Parser.getDataParse(file1);
        final TreeMap<String, Object> map2 = Parser.getDataParse(file2);

        return compare(map1, map2);
    }

    public static String compare(Map<String, Object> map1, Map<String, Object> map2) {
        return Compare.getDifferenceMap(map1, map2).stream()
               .map(Stylish::setStylishFormat)
               .collect(Collectors.joining("\n", "{\n", "\n}\n"));
    }

    private static File getFile(String pathFile) throws Exception {
        File file1 = new File(pathFile);
        if (!file1.exists() || file1.isDirectory()) {
            throw new Exception("no such file " + pathFile);
        }
        return file1;
    }
}
