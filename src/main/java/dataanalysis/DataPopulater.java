package dataanalysis;

import java.io.File;

import frequencyanalysissimulator.crypto.KeyLengthMethod;

/**
 * Runs DataAnalysis without arguments, taking contents of every input file and feeding this to
 * DataFileWriter, which in return feeds the content to DataAnalysis
 * Used for populating all data, takes ~20 seconds to run and collects 5000 data points
 */
public class DataPopulater {
    DataPopulater(String method) {
        File inputFolder = new File("data/inputs");
        File[] allInputs = inputFolder.listFiles();

        for (int i = 0; i < allInputs.length; i++) {
            System.out.printf("Collecting data on %s...%-20s", allInputs[i].getName(), ' ');

            String fileNameWithoutExtension = allInputs[i].getName().substring(0,
                    allInputs[i].getName().lastIndexOf("."));
            DataFileReader.main(fileNameWithoutExtension, method);

            System.out.print("\rData collection complete. Moving to next file...\n");
        }
    }

    /**
     * 
     * @param args
     *            args[0]: key length calculation algorithm
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        if (args[0] == null || args[0].equals("all")) {
            for (KeyLengthMethod method : KeyLengthMethod.values()) {
                new DataPopulater(method.name());
            }
        } else
            new DataPopulater(args[0]);

        System.out.print("\rData population complete");
    }
}
