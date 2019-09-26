package net.sf.extjwnl;

import java.util.Scanner;

class BruteForceSimulator {
	static Scanner userInput = new Scanner(System.in);
	public static void main(String[] args) throws JWNLException {
		// TODO Auto-generated method stub
		System.out.println("Enter a ciphertext");
		String ciphertext = userInput.nextLine();
		for (char l : ciphertext.toCharArray()) {
			for (char m : ciphertext.toCharArray()) {
				System.out.println(l + ", " + m);
				if (FrequencyAnalysisSimulator.isWord(ciphertext.replace(l, m))) {
					
					ciphertext = ciphertext.replace(l, m);
				}
			}
			
		}
	}

}
