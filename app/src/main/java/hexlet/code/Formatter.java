package hexlet.code;

import hexlet.code.Formatters.Json;
import hexlet.code.Formatters.Plain;
import hexlet.code.Formatters.Stylish;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Formatter {
    public static String checkFormat(List<LinkedHashMap<String, Object>> diff, String format) {
        return switch (format) {
            case "stylish" -> diff.stream()
                    .map(Stylish::setStylishFormat)
                    .collect(Collectors.joining("\n", "{\n", "\n}"));
            case "plain" -> diff.stream()
                    .map(Plain::setPlainFormat)
                    .filter(Predicate.not(String::isEmpty))
                    .collect(Collectors.joining("\n", "", ""));
            case "json" -> diff.stream()
                    .map(Json::setJsonFormat)
                    .collect(Collectors.joining(",\n", "[\n", "\n]"));
            default -> throw new RuntimeException("Unknown style format");
        };
    }
}
