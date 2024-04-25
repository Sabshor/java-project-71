package hexlet.code;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class Differ {
    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        String format = formatName.isEmpty() ? "stylish" : formatName.toLowerCase();

        File file1 = getFile(filePath1);
        File file2 = getFile(filePath2);

        final var map1 = Parser.getDataParse(file1);
        final var map2 = Parser.getDataParse(file2);

        return Formatter.checkFormat(Compare.getDifferenceMap(map1, map2), format);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    private static File getFile(String pathFile) throws Exception {
        Path normalizeAbsolutePath = Path.of(pathFile).normalize().toAbsolutePath();
        if (!Files.exists(normalizeAbsolutePath) || Files.isDirectory(normalizeAbsolutePath)) {
            throw new Exception("no such file " + pathFile);
        }
        return normalizeAbsolutePath.toFile();
    }
}
