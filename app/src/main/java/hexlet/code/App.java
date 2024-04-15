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
            //var diff = Differ.generate("/home/lso/java-projects/filesTo71/file1.json"
            //                           , "/home/lso/java-projects/filesTo71/file2.json");
           // var diff = Differ.generate("../../filesTo71/file1.json", "../../filesTo71/file2.json");
            var diff2 = Differ.generate("../../filesTo71/file_yaml1.yml", "../../filesTo71/file_yaml2.yml");
            //var diff = Differ.generate(filePath1, filePath2);
           // System.out.println(diff);
            System.out.println(diff2);
        } catch (Exception e) {
            System.err.println("Внимание! Ошибка! " + e);
        }
        //System.out.printf("f1=%s,  f2=%s", filePath1, filePath2);
        //System.out.println();
        //return 123; // exit code
        return 0;
    }
    public static void main(String[] args) {
        System.exit(new picocli.CommandLine(new App()).execute(args));
    }
}
