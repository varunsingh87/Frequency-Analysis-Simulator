package dataanalysis;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import frequencyanalysissimulator.business.Vigenere;

public class DataAnalysis {
    public static void main(String[] args) {
        String key = "DONQUIXOTECOYOTEWILLMACAVOY";
        String expectedText = "SO MAY THE OUTWARD SHOWS BE LEAST THEMSELVES: THE WORLD IS STILL DECEIVED WITH ORNAMENT. IN LAW, WHAT PLEA SO TAINTED AND CORRUPT, BUT, BEING SEASONED WITH A GRACIOUS VOICE, OBSCURES THE SHOW OF EVIL? IN RELIGION, WHAT DAMNED ERROR, BUT SOME SOBER BROW WILL BLESS IT AND APPROVE IT WITH A TEXT, HIDING THE GROSSNESS WITH FAIR ORNAMENT? THERE IS NO VICE SO SIMPLE BUT ASSUMES SOME MARK OF VIRTUE ON HIS OUTWARD PARTS: HOW MANY COWARDS, WHOSE HEARTS ARE ALL AS FALSE AS STAIRS OF SAND, WEAR YET UPON THEIR CHINS THE BEARDS OF HERCULES AND FROWNING MARS; WHO, INWARD SEARCH'D, HAVE LIVERS WHITE AS MILK; AND THESE ASSUME BUT VALOUR'S EXCREMENT TO RENDER THEM REDOUBTED! LOOK ON BEAUTY, AND YOU SHALL SEE 'TIS PURCHASED BY THE WEIGHT; WHICH THEREIN WORKS A MIRACLE IN NATURE, MAKING THEM LIGHTEST THAT WEAR MOST OF IT: SO ARE THOSE CRISPED SNAKY GOLDEN LOCKS WHICH MAKE SUCH WANTON GAMBOLS WITH THE WIND, UPON SUPPOSED FAIRNESS, OFTEN KNOWN TO BE THE DOWRY OF A SECOND HEAD, THE SKULL THAT BRED THEM IN THE SEPULCHRE. THUS ORNAMENT IS BUT THE GUILED SHORE TO A MOST DANGEROUS SEA; THE BEAUTEOUS SCARF VEILING AN INDIAN BEAUTY; IN A WORD, THE SEEMING TRUTH WHICH CUNNING TIMES PUT ON TO ENTRAP THE WISEST. THEREFORE, THOU GAUDY GOLD, HARD FOOD FOR MIDAS, I WILL NONE OF THEE; NOR NONE OF THEE, THOU PALE AND COMMON DRUDGE 'TWEEN MAN AND MAN: BUT THOU, THOU MEAGRE LEAD, WHICH RATHER THREATENEST THAN DOST PROMISE AUGHT, THY PALENESS MOVES ME MORE THAN ELOQUENCE; AND HERE CHOOSE I; JOY BE THE CONSEQUENCE!"
                .toUpperCase();
        String output = "Len,1-100,101-200,201-300,301-400,401-500,501-600,601-700,701-800,801-900,901-1000,1001-1100,1101-1200,1201-1300,1301-1400,1401-1500\n";

        for (int i = 3; i <= 20; i++) { // O(17) = O(C)
            output += i + ",";
            for (int cipherlen = 90; cipherlen <= 1500; cipherlen += 100) { // O(15) = O(C)
                String input = expectedText.substring(0, cipherlen);
                String subKey = key.substring(0, i);
                String ciphertext = Vigenere.encrypt(input, subKey);
                Vigenere v = new Vigenere(ciphertext);
                String decryptedText = v.decrypt();
                int accuracy = Math.round(percentageSimilarity(decryptedText, input));

                output += accuracy + ",";
            }

            output += "\n";
        }

        try (FileWriter writer = new FileWriter(new File("data/trial1.csv"))) {
            writer.append(output);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Preconditions:
     * (1) input1 is the same exact size as input2 (input1.length() == input2.length())
     * (2) input1 and input2 have all the non-letters in the same exact places
     * 
     * @return Ratio of exact matches to input size as a percentage
     * @throws IndexOutOfBoundsException
     *             If input1.length() !== input2.length()
     */
    static float percentageSimilarity(String input1, String input2) {
        float count = 0;
        for (int i = 0; i < input1.length(); i++) {
            if (input1.charAt(i) == input2.charAt(i)) {
                count += 1.0 / input1.length();
            }
        }

        return count * 100;
    }
}
