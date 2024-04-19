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
    static String formatPlainActual;
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
        formatPlainActual = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'
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
        assertEquals(Formatter.checkFormat(Compare.getDifferenceMap(map1, map2), "stylish"), actual);
    }

    @Test
    public void compareJsonTestStylish() throws Exception {
        String path = "src/test/resources/fixtures/json";
        /*File file1 = new File(path.concat("/file1.json"));
        String absolutePath = file1.getAbsolutePath();
        System.out.println(absolutePath);*/
        assertEquals(Differ.generate(path.concat("/file1.json"),
                path.concat("/file2.json"), ""), formatStylishActual);
    }

    @Test
    public void compareYmlTestStylish() throws Exception {
        String path = "src/test/resources/fixtures/yml";
        assertEquals(Differ.generate(path.concat("/file1.yml"),
                path.concat("/file2.yml"), ""), formatStylishActual);
    }

    @Test
    public void compareJsonTestPlain() throws Exception {
        String path = "src/test/resources/fixtures/json";
        assertEquals(Differ.generate(path.concat("/file1.json"),
                path.concat("/file2.json"), "Plain"), formatPlainActual);
    }

    @Test
    public void compareYmlTestPlain() throws Exception {
        String path = "src/test/resources/fixtures/yml";
        assertEquals(Differ.generate(path.concat("/file1.yml"),
                path.concat("/file2.yml"), "plain"), formatPlainActual);
    }
}
