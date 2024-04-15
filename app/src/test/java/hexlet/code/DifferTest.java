package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
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

    @Test
    public void compareJsonTest() throws Exception {
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
        String path = "src/test/resources/fixtures/json";

        File file1 = new File(path.concat("/file1.json"));
        String absolutePath = file1.getAbsolutePath();
        System.out.println(absolutePath);
        assertEquals(Differ.generate(path.concat("/file1.json"), path.concat("/file2.json")), actual);
    }

    @Test
    public void compareYmlTest() throws Exception {
        String actual = """
                {
                    cache: maven
                    distribution: temurin
                  - fail-fast: false
                    java_opts: -XX:+TieredCompilation -XX:TieredStopAtLevel=1
                  - java_version: 19
                  + java_version: 20
                  - name: Set up JDK
                  + name: Set up JRE
                  + os: ubuntu-20.04
                    uses: actions/checkout@v4
                }
                """;
        String path = "src/test/resources/fixtures/yml";

        File file1 = new File(path.concat("/file1.yml"));
        String absolutePath = file1.getAbsolutePath();
        System.out.println(absolutePath);
        assertEquals(Differ.generate(path.concat("/file1.yml"), path.concat("/file2.yml")), actual);
    }
}
