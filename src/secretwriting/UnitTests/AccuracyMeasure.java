package secretwriting.UnitTests;

public class AccuracyMeasure {
	public static void main(String[] args) {
		String original = "In the 34 years since the R.M.S. Titanic was discovered on the seafloor south of Newfoundland, it has become the world’s most famous shipwreck -- a rusting hulk assailed by hundreds of explorers and moviemakers, salvors and tourists, scientists and federal watchdogs. All agree that the once-grand ship is rapidly falling apart. Resting on the icy North Atlantic seabed more than two miles down, upright but split in two, the fragile mass is slowly succumbing to rust, corrosive salts, microbes and colonies of deep-sea creatures.".toLowerCase();
		String decipherment = "in the 34 yeaGs since the G.m.s. titanic was discoIeGed on the searlooG south or newroundland, it has become the woGld's most ramous shipwGeck -- a GustinR hulk assailed by hundGeds or eYploGeGs and moIiemakeGs, salIoGs and touGists, scientists and redeGal watchdoRs. all aRGee that the once-RGand ship is Gapidly rallinR apaGt. GestinR on the icy noGth atlantic seabed moGe than two miles down, upGiRht but split in two, the rGaRile mass is slowly succumbinR to Gust, coGGosiIe salts, micGobes and colonies or deep-sea cGeatuGes.";
		int counter = 0;
		for (int i = 0; i < original.length(); i++) {
			if (original.charAt(i) != decipherment.charAt(i)) {
				counter++;
			}
		}
		System.out.println(((float)(original.length() - counter) / original.length()) * 100 + "%");
	}
}
