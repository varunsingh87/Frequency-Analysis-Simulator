package dataanalysis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataFileReader {
    static String readInput(String id) throws IOException {
        String expectedText = Files.readString(Path.of(String.format("data/inputs/%s.txt", id)));
        return expectedText.replace(System.getProperty("line.separator"), " ");
    }
}
