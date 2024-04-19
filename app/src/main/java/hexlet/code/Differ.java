package hexlet.code;

import java.io.File;

public class Differ {
    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        String format = formatName.isEmpty() ? "stylish" : formatName.toLowerCase();

        File file1 = getFile(filePath1);
        File file2 = getFile(filePath2);

        final var map1 = Parser.getDataParse(file1);
        final var map2 = Parser.getDataParse(file2);

        return Formatter.checkFormat(Compare.getDifferenceMap(map1, map2), format);
    }

    private static File getFile(String pathFile) throws Exception {
        File file1 = new File(pathFile);
        if (!file1.exists() || file1.isDirectory()) {
            throw new Exception("no such file " + pathFile);
        }
        return file1;
    }
}
