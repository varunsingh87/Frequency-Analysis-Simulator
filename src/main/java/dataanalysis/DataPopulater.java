package dataanalysis;

import java.io.File;

/**
 * Runs DataAnalysis without arguments, taking contents of every input file and feeding this to
 * DataFileWriter, which in return feeds the content to DataAnalysis
 * Used for populating all data, takes ~20 seconds to run and collects 5000 data points
 */
public class DataPopulater {
    public static void main(String[] args) throws InterruptedException {
        File inputFolder = new File("data/inputs");
        File[] allInputs = inputFolder.listFiles();

        for (int i = 0; i < allInputs.length; i++) {
            System.out.printf("Collecting data on %s...%-20s", allInputs[i].getName(), ' ');

            String fileNameWithoutExtension = allInputs[i].getName().substring(0,
                    allInputs[i].getName().lastIndexOf("."));
            DataFileReader.main(fileNameWithoutExtension);

            System.out.print("\rData collection complete. Moving to next file...\n");
        }
        System.out.print("\rData population complete");
    }
}
