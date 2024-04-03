package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        File file1 = getFile(filePath1);
        File file2 = getFile(filePath2);

        ObjectMapper mapper = new ObjectMapper();
        final TreeMap<String, String> map1 = mapper.readValue(file1, new TypeReference<>() { });
        final TreeMap<String, String> map2 = mapper.readValue(file2, new TypeReference<>() { });
        TreeMap<String, String> mapUnion = new TreeMap<>(map2);
        mapUnion.putAll(map1);
        //System.out.println("contain map all =" + mapUnion);
        var diff = mapUnion.entrySet().stream()
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
            .collect(Collectors.joining("\n", "{\n", "\n}"));

      /*  try (JsonParser parser = mapper.createParser(file1)) {
            JsonToken t = parser.nextToken(); // Should be JsonToken.START_OBJECT
          //  while (t.)
            t = parser.nextToken(); // JsonToken.FIELD_NAME
            System.out.println("msg 1=" + t);
            if ((t != JsonToken.FIELD_NAME) || !"message".equals(parser.getCurrentName())) {
                //throw new RuntimeException("error parse handle json");
                System.out.println("handle=" + parser.getCurrentName());
            }
            t = parser.nextToken();
            System.out.println("msg 2=" + t);
            if (t != JsonToken.VALUE_STRING) {
                System.out.println("value=" + parser.getCurrentName());
               // throw new RuntimeException("error parse value json");
            }
            String msg = parser.getCurrentName() + ":" + parser.getText();
            System.out.printf("My message to you is: %s!\n", msg);
            t = parser.nextToken();
            System.out.println("end=" + parser.getCurrentName());
        }*/
        return diff;
    }

    private static String getLineMessage(String sign, String key, String value) {
        return sign.concat(" ").concat(key).concat(": ").concat(value);
    }

    private static File getFile(String pathFile) throws Exception {
        File file1 = new File(pathFile);
        if (!file1.exists() || file1.isDirectory()) {
            throw new Exception("no such file " + pathFile);
        }
        return file1;
    }
}
