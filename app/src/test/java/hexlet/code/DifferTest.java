package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    static TreeMap<String, Object> map1 = new TreeMap<>();
    static TreeMap<String, Object> map2 = new TreeMap<>();
    static String formatStylishActual;
    @BeforeAll
    public static void beforeAll() {
        map1.putAll(Map.of("host", "hexlet.io",
                           "timeout", "50",
                           "proxy", "123.234.53.22",
                           "follow", "false"));
        map2.putAll(Map.of("timeout", "20",
                           "verbose", "true",
                           "host", "hexlet.io"));
        formatStylishActual = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }
                """;
    }


    @Test
    public void getLineMessageTest() {
        String actual = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }
                """;
        assertEquals(Differ.compare(map1, map2), actual);
    }

    @Test
    public void compareJsonTest() throws Exception {
        String path = "src/test/resources/fixtures/json";
        /*File file1 = new File(path.concat("/file1.json"));
        String absolutePath = file1.getAbsolutePath();
        System.out.println(absolutePath);*/
        assertEquals(Differ.generate(path.concat("/file1.json"), path.concat("/file2.json"), ""), formatStylishActual);
    }

    @Test
    public void compareYmlTest() throws Exception {
        String path = "src/test/resources/fixtures/yml";
        assertEquals(Differ.generate(path.concat("/file1.yml"), path.concat("/file2.yml"), ""), formatStylishActual);
    }
}
