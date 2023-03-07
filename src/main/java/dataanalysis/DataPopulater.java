package dataanalysis;

import java.io.File;
import java.io.IOException;

import frequencyanalysissimulator.crypto.CaesarDecryptionMethod;
import frequencyanalysissimulator.crypto.KeyLengthMethod;

/**
 * Runs DataAnalysis without arguments, taking contents of every input file and feeding this to
 * DataFileWriter, which in return feeds the content to DataAnalysis
 * Used for populating all data, takes ~10 seconds to run and collects 5000 data points
 */
public class DataPopulater {
    DataPopulater(String keyLenAlg, String caesarAlg) {
        File inputFolder = new File("data/inputs");
        File[] allInputs = inputFolder.listFiles();

        for (int i = 0; i < allInputs.length; i++) {
            System.out.printf("Collecting data on %s...%-20s", allInputs[i].getName(), ' ');

            try {
                String fileNameWithoutExtension = allInputs[i].getName().substring(0,
                        allInputs[i].getName().lastIndexOf("."));
                String inputText = DataFileReader.readInput(fileNameWithoutExtension);
                DataCollector.main(new String[] { inputText, fileNameWithoutExtension, keyLenAlg, caesarAlg });
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.print("\rData collection complete. Moving to next file...\n");
        }
    }

    /**
     * 
     * @param args
     *            args[0]: key length calculation algorithm
     *            args[1]: Caesar decryption algorithm
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        if (args[0] == null || args[0].equals("all")) {
            for (KeyLengthMethod keyLenAlg : KeyLengthMethod.values()) {
                for (CaesarDecryptionMethod keyAlg : CaesarDecryptionMethod.values()) {
                    new DataPopulater(keyLenAlg.name(), keyAlg.name());
                }
            }
        } else
            new DataPopulater(args[0], args[1]);

        System.out.print("\rData population complete");
    }
}
