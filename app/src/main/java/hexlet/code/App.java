package hexlet.code;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "1.0",
        description = "Compares two configuration files and shows a difference.")
class App implements Callable<Integer> {
    //@CommandLine.Option(names = "-x") int x;
    @Override
    public Integer call() { // business logic
        var diff = Differ.generate("", "");
        //System.out.printf("x=%s%n", x);
        //return 123; // exit code
        return 0;
    }
    public static void main(String[] args) {
        System.exit(new picocli.CommandLine(new App()).execute(args));
    }
}
