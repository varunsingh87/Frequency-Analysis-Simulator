
public class FrequencyAnalysisSimulator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ciphertext = determineCiphertext();
		if (determineCipherType().equals("monoalphabetic")) {
			System.out.println(decipherMonoalphabetic(ciphertext));
		} else if (determineCipherType().equals("Vigenere")) {
			System.out.println(decipherVigenere(ciphertext));
		}
	}
	
	public static String determineCipherType() {
		return "monoalphabetic";
	}
	
	public static String determineCiphertext() {
		return "CKPKH GVGCK UGZQA GCKUG CLGPQ FJZIG PQQAF QQLHG FJZEF QGKEF CCQAG LOULJ QFRGM OGPQA FUGZO SJBQA GLOTS MFOKS JZKOQ VKIGE KOGFJ ZKJGI XKJGT OGMQP LCGJQ CXQKO GPQYD";
	}
	
	public static String decipherMonoalphabetic(String ciphertext) {
		// TODO Implement decipherMonoalphabetic(String ciphertext) method
		String plaintext = ciphertext;
		return plaintext; 
	}
	
	public static String decipherVigenere(String ciphertext) {
		// TODO Implement decipherVigenere(String ciphertext) method
		String plaintext = decipherMonoalphabetic(ciphertext);
		return plaintext; 
	}

}
