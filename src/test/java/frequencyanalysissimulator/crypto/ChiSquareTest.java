package frequencyanalysissimulator.crypto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ChiSquareTest {
	@Test
	public void testChiSquare() {
		Caesar cipher = new Caesar("WWBQCUOBSW");
		char output = cipher.getKeyByChiSquare();
		assertEquals('O', output);
	}

	@Test
	public void testChiSquare1() {
		Caesar cipher = new Caesar("NIBFOPDVWTZ");
		char output = cipher.getKeyByChiSquare();
		System.out.println(output);
		assertEquals('B', output);
	}

	@Test
	public void testChiSquare3() {
		Caesar cipher = new Caesar("AERFJGJGPR");
		char output = cipher.getKeyByChiSquare();
		System.out.println(output);
		assertEquals('Y', output);
	}

}
