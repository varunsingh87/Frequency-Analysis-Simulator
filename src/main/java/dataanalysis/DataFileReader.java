package dataanalysis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataFileReader {
    /**
     * Reads from data/inputs/arg[0].txt
     * 
     * @param args
     *            arg[0] = trial id
     */
    public static void main(String... args) {
        try {
            String expectedText = Files.readString(Path.of(String.format("data/inputs/%s.txt", args[0])));
            expectedText = expectedText.replace(System.getProperty("line.separator"), " ");
            DataCollector.main(new String[] { expectedText, args[0] });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
