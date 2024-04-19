package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.TreeMap;

public class Parser {
    public static TreeMap<String, Object> getDataParse(File file) throws Exception {
        String fileExtension = FilenameUtils.getExtension(file.getName());
        ObjectMapper mapper = getMapper(fileExtension);
        return mapper.readValue(file, new TypeReference<>() { });
    }

    private static ObjectMapper getMapper(String fileExtension) throws Exception {
        return switch (fileExtension.toUpperCase()) {
            case "JSON" -> new ObjectMapper();
            case "YML" -> new YAMLMapper();
            default -> throw new Exception("no such file format " + fileExtension);
        };
    }
}
