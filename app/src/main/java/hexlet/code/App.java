package hexlet.code;

import picocli.CommandLine;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "1.0",
                    description = "Compares two configuration files and shows a difference.")
class App implements Callable<Integer> {
    @CommandLine.Parameters(paramLabel = "filepath1", description = "path to first file")
    private String filePath1;
    @CommandLine.Parameters(paramLabel = "filepath2", description = "path to second file")
    private String filePath2;
    @CommandLine.Option(names = {"-f", "--format"}, paramLabel = "format",
            defaultValue = "stylish", description = "output format [default: ${DEFAULT-VALUE}]")
    private String format;

    @Override
    public Integer call() {
        try {
            //System.out.println(Differ.generate("../../filesTo71/file1.json", "../../filesTo71/file2.json", "Plain"));
            //System.out.println(Differ.generate("../../filesTo71/file11.json","../../filesTo71/file22.json", "plain"));
            //System.out.println(Differ.generate("../../filesTo71/file1.yml", "../../filesTo71/file2.yml", "json"));

            System.out.println(Differ.generate(filePath1, filePath2, format));
        } catch (Exception e) {
            System.err.println("Внимание! Ошибка! " + e);
        }
        return 0;
    }
    public static void main(String[] args) {
        System.exit(new picocli.CommandLine(new App()).execute(args));
    }
}
