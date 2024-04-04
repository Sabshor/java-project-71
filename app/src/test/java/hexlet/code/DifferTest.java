package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    static TreeMap<String, String> map1 = new TreeMap<>();
    static TreeMap<String, String> map2 = new TreeMap<>();
    @BeforeAll
    public static void beforeAll() {
        map1.putAll(Map.of("host", "hexlet.io",
                           "timeout", "50",
                           "proxy", "123.234.53.22",
                           "follow", "false"));
        map2.putAll(Map.of("timeout", "20",
                           "verbose", "true",
                           "host", "hexlet.io"));
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
}
