package frequencyanalysissimulator.business;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VigenereTest {

	@Test
	public void testChiSquare() {
		Vigenere cipher = new Vigenere(4, "WWBQCUOBSW");
		char output = cipher.decryptCosetByChiSquare((byte) 1);
		System.out.println(output);
		assertEquals('O', output);
	}

	@Test
	public void testChiSquare1() {
		Vigenere cipher = new Vigenere(4, "NIBFOPDVWTZ");
		char output = cipher.decryptCosetByChiSquare((byte) 1);
		System.out.println(output);
		assertEquals('B', output);
	}

	@Test
	public void testChiSquare3() {
		Vigenere cipher = new Vigenere(4, "AERFJGJGPR");
		char output = cipher.decryptCosetByChiSquare((byte) 1);
		System.out.println(output);
		assertEquals('Y', output);
	}

}
