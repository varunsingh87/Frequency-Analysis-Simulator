/*
OF MIT 34 BTAKL LOFET MIT K.D.L. MOMAFOE CAL ROLEGXTKTR GF MIT LTAYSGGK LGWMI GY FTCYGWFRSAFR, OM IAL ZTEGDT MIT CGKSR'L DGLM YADGWL LIOHCKTEQ -- A KWLMOFU IWSQ ALLAOSTR ZB IWFRKTRL GY TVHSGKTKL AFR DGXOTDAQTKL, LASXGKL AFR MGWKOLML, LEOTFMOLML AFR YTRTKAS CAMEIRGUL. ASS AUKTT MIAM MIT GFET-UKAFR LIOH OL KAHORSB YASSOFU AHAKM. KTLMOFU GF MIT OEB FGKMI AMSAFMOE LTAZTR DGKT MIAF MCG DOSTL RGCF, WHKOUIM ZWM LHSOM OF MCG, MIT YKAUOST DALL OL LSGCSB LWEEWDZOFU MG KWLM, EGKKGLOXT LASML, DOEKGZTL AFR EGSGFOTL GY RTTH-LTA EKTAMWKTL.
*/

/*
In the 34 years since the R.M.S. Titanic was discovered on the seafloor south of Newfoundland, it has become the world’s most famous shipwreck — a rusting hulk assailed by hundreds of explorers and moviemakers, salvors and tourists, scientists and federal watchdogs. All agree that the once-grand ship is rapidly falling apart. Resting on the icy North Atlantic seabed more than two miles down, upright but split in two, the fragile mass is slowly succumbing to rust, corrosive salts, microbes and colonies of deep-sea creatures.
*/

package net.sf.extjwnl; // Uses the extjwnl package to determine if strings are sentences

import java.util.Scanner;

// Import secretwriting needed classes
import secretwriting.CaesarShiftCipher;
import secretwriting.MonoalphabeticCipher;
import secretwriting.VigenereCipher;

/**
 * Frequency Analysis Simulator
 * By Varun Singh
 */

/**
 * <h1>Frequency Analysis Simulator</h1>
 * <p>Frequency Analysis Simulator is a Java program that simulates frequency analysis in which the user inputs cipher text into the console and the System outputs as close to the corresponding plain text as possible. If the type of cipher has been identified, the process may be sped up after the user inputs the type of cipher (monoalphabetic or Vigenere) on prompt. Furthermore, Frequency Analysis Simulator can decipher the caesar shift cipher, a cipher that does not involve the use of frequency analysis for decipherment. As another added bonus, the application is able to encrypt messages.</p>
 * @author Varun Singh
 * @inspiration The Code Book by Simon Singh
 */
public class FrequencyAnalysisSimulator {
	
	static Scanner userInput = new Scanner (System.in);
	static enum ACTION {
		ENCRYPT,
		DECRYPT,
		MAGIC
	}

	static Exception InvalidInputException = new Exception("You did not enter valid input. Please rerun the program and try again");
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws JWNLException {
		long startTime = System.currentTimeMillis();
		ACTION action = ACTION.MAGIC;
		if (action.equals(ACTION.DECRYPT))
			handleDecrypt();
		else if (action.equals(ACTION.ENCRYPT))
			handleEncrypt();
		else if (action.equals(ACTION.MAGIC)) {
			MonoalphabeticCipher c = new MonoalphabeticCipher("IGQQ CRGXJ FGEH LM JEKMMQ IMB HTYJ, VKTEK CRGXJ CMBR EGBJ GXY FZJRJ MX LKR BMGYJ. YBTARBJ GQJM XRRY LM VGLEK MZL IMB TXEBRGJRY ORYRJLBTGX LBGIITE TX LKR CMBXTXN GXY GILRBXMMX GJ EKTQYBRX VGQH LM GXY IBMC JEKMMQ GXY LKRTB XRTNKFMBKMMY FZJ JLMOJ. LKR ITBJL BGTX TX G IRV VRRHJ EGX FR OGBLTEZQGBQD YGXNRBMZJ, GJ VGLRB OMMQJ MX LMO MI YZJL GXY MTQ LKGL KGARX'L KGY G EKGXER LM VGJK GVGD GXY CGHRJ LKR OGARCRXL RWLBRCRQD JQTOORBD. IGQQ IMQTGNR TJ ERBLGTXQD FRGZLTIZQ, FZL GJ QRGARJ FRNTX LM IGQQ, LKRD QTLLRB LKR BMGYJ, CGHTXN JLBRRLJ JQTEH VKTQR MFJEZBTXN LBGIITE QTXRJ GXY MLKRB OGARCRXL CGBHTXNJ. LKRD GQJM KTYR OMLKMQRJ GXY MLKRB BMGY KGPGBYJ. GXY VKRX TL BGTXJ, TL EGX CGHR LKMJR VRL QRGARJ MX LKR BMGYVGD GJ YGXNRBMZJ GJ TER. GXY VKRBR LKRBR GBR LZBXTXN QRGARJ, LKRBR GBR QRGI ORRORBJ. LKRJR QRGI-ORROTXN YBTARBJ LRXY LM EBGVQ GQMXN LKR BMGYJ GXY CGHR ZXOBRYTELGFQR JLMOJ LM GYCTBR LKR EKGXNTXN IMQTGNR. TI DMZ'BR YBTATXN FRKTXY G EGB VTLK MZL-MI-JLGLR OQGLRJ, NTAR LKRC G QTLLQR RWLBG JOGER UZJL TX EGJR LKRD JLMO JKMBL IMB G OKMLM. EMQY IGQQ CMBXTXNJ MILRX QRGY LM IMN, VKTEK EGX NBRGLQD QTCTL DMZB YBTATXN ATJTFTQTLD GXY ORBEROLTMX MI YTJLGXER. IMN LRXYJ LM MEEZB TX QMV OQGERJ MB GBRGJ JZBBMZXYRY FD KTQQJ, VGLRB, CMZXLGTXJ, GXY LBRRJ. MXR EMCCMX CTJLGHR YBTARBJ CGHR YZBTXN IMNND EMXYTLTMXJ TJ OZLLTXN MX LKRTB KTNK FRGCJ TXJLRGY MI JLGDTXN VTLK LKRTB QMV FRGCJ. LKTJ MXQD CGHRJ ATJTFTQTLD VMBJR FREGZJR DMZB KTNK FRGCJ VTQQ FMZXER MII LKR IMN GXY EBRGLR NQGBR. VKRX YBTATXN LKBMZNK IMN, JQMV YMVX GXY JLGD VRQQ FRKTXY LKR EGB TX IBMXL MI DMZ JM DMZ'QQ KGAR GYRSZGLR LTCR LM JLMO TI DMZ XRRY LM. YZBTXN LKR IGQQ, LRCORBGLZBRJ LRXY LM YBMO YBGCGLTEGQQD YZBTXN LKR XTNKL, VKTEK EGX QRGY LM CMBXTXN IBMJL GXY TED JOMLJ MX LKR BMGY. LKTJ TJ RJORETGQQD EMCCMX MX FBTYNRJ, MARBOGJJRJ, GXY JKGYRY GBRGJ MI LKR BMGY. IGQQ TJ GQJM G FGY LTCR IMB JZX NQGBR MX LKR BMGYJ. JZX NQGBR EGX TCOGEL DMZB JTNKL IMB JREMXYJ GILRB RWOMJZBR, CGHTXN TL KGBY LM JRR ORYRJLBTGXJ, MXEMCTXN LBGIITE, MB LKR EGB TX IBMXL MI DMZ. GEEMBYTXN LM LKR XGLTMXGQ KTNKVGD LBGIITE JGIRLD GYCTXTJLBGLTMX, YBTARBJ YRJEBTFR FRTXN \"FQTXYRY\" GILRB RWOMJZBR, GXY LKTJ JMCRLTCRJ QRGYJ LM GEETYRXLJ MB XRGB CTJJRJ. JZX NQGBR EGX GQJM EGZJR OBMFQRCJ VKRX LKR JZX JRLJ FRKTXY YBTARBJ. TX LKTJ EGJR, JZXQTNKL EGX FMZXER MII DMZB BRGBATRV CTBBMB MB BRIQREL MII LBGIITE QTNKLJ ZO GKRGY, GXY LKTJ EGX FQTXY DMZ IMB G JOQTL JREMXY VKTQR DMZB RDRJ GYUZJL. TL EGX GQJM CGHR TL KGBY (MB TCOMJJTFQR) LM JRR LBGIITE QTNKLJ, VKTEK EGX OBRARXL DMZ IBMC HXMVTXN TI DMZ'BR JZOOMJRY LM JLMO MB NM. LKR IGQQ JRGJMX FBTXNJ GX TXEBRGJR TX YRRB GELTATLD FREGZJR TL'J LKRTB LTCR IMB CGLTXN GXY CTNBGLTXN. TI DMZ QTAR TX G YRRB-KRGAD GBRG, VGLEK IMB YGBLTXN YRRB, RJORETGQQD VKRX YBTATXN GL XTNKL.");
			MonoalphabeticCipher c2 = new MonoalphabeticCipher("NC JUP 34 OPTGB BNCVP JUP G.F.B. JNJTCNV XTB ZNBVAIPGPZ AC JUP BPTMSAAG BADJU AM CPXMADCZSTCZ, NJ UTB LPVAFP JUP XAGSZ'B FABJ MTFADB BUNQXGPVE -- T GDBJNCR UDSE TBBTNSPZ LO UDCZGPZB AM PYQSAGPGB TCZ FAINPFTEPGB, BTSIAGB TCZ JADGNBJB, BVNPCJNBJB TCZ MPZPGTS XTJVUZARB. TSS TRGPP JUTJ JUP ACVP-RGTCZ BUNQ NB GTQNZSO MTSSNCR TQTGJ. GPBJNCR AC JUP NVO CAGJU TJSTCJNV BPTLPZ FAGP JUTC JXA FNSPB ZAXC, DQGNRUJ LDJ BQSNJ NC JXA, JUP MGTRNSP FTBB NB BSAXSO BDVVDFLNCR JA GDBJ, VAGGABNIP BTSJB, FNVGALPB TCZ VASACNPB AM ZPPQ-BPT VGPTJDGPB.");
			MonoalphabeticCipher c3 = new MonoalphabeticCipher("CKNQ OBEJ E SQJOPNI ETOQN UO VEA APJM YI EJ USQYQNG UJ OBQ JKNOB EOFEJOUS, OBQ OUOEJUS VEA BUO EGEUJ -- OBUA OUCQ YI E ACEFF APYCENUJQ. OBQ OVK-RQNAKJ APYCQNAUYFQ HQBUSFQ SNEABQD UJOK OBQ ABURVNQSM AUW CKJOBA EGK VBUFQ UO VEA SKJDPSOUJG NQAQENSB EJD TUFCUJG E DKSPCQJOENI, ESSKNDUJG OK JQV DKSPCQJOA TUFQD UJ E DQSEDQA-FKJG TQDQNEF SKPNO YEOOFQ UJ HUNGUJUE. DPNUJG E AQNUQA KT DUHQA YQOVQQJ ZPFI 29 EJD EPG. 4, 2019, OBQ APY VEA YNUQTFI KHQNVBQFCQD YI \"UJOQJAQ EJD BUGBFI PJRNQDUSOEYFQ SPNNQJOA,\" EJD \"ESSUDQJOEF SKJOESO VEA KSSEAUKJEFFI CEDQ VUOB OBQ AQETFKKN EJD KJ KJQ KSSEAUKJ OBQ VNQSM,\" ESSKNDUJG OK E NQRKNO YI QIKA QWRQDUOUKJA, VBUSB QWQSPOQD OBQ DUHQA. OBQ APY VEA \"JQPONEFFI YPKIEJO (VQUGBOFQAA)\" EO OBQ OUCQ EJD DUD JKO BEHQ EJI \"EYJKNCEF RBIAUSEF AUGJA KT DECEGQ,\" QIKA AEUD, YPO UO UA PJSFQEN VBEO DECEGQ, UT EJI, OBQ OUOEJUS APAOEUJQD.");
			MonoalphabeticCipher c4 = new MonoalphabeticCipher("PVY PDGIP QFZQTUXICM UXIC NZFMXY PTLLCPI IDXI IDC NGZM ATPIXOL AXQD 1 ZCEFEXU SC'EC RCCO DGVFOL NGZ AXY NFOXUUY RC OCXZ. IDFP E8-VGSCZCM ATUC XVVCXZP IG RC XO FMCXU NFI RCISCCO IDC QTZZCOI LI XOM PDCURY LI350, PUGIIFOL FOIG IDC PVXQC QTZZCOIUY GQQTVFCM RY IDC UFAFICM-CMFIFGO RTUUFII, SDFQD FP PUXICM IG LG XSXY XNICZ IDFP YCXZ. ZTAGZP GN X ZCEFEXU GN IDC NGZM ATPIXOL AXQD 1 OXACVUXIC DFI X NCECZ VFIQD FO IDC AGOIDP UCXMFOL TV IG IDC TOECFUFOL GN IDC ATPIXOL AXQD-C QZGPPGECZ. SFID IDC FMCOIFIY GN NGZM'P CUCQIZFQ ATPIXOL QZGPPGECZ QCACOICM, XOIFQFVXIFGO GN X OCS AXQD 1 PCCACM IG MFV. IGMXY, SC AXY JTPI DXEC QXTPC NGZ AGZC CHQFICACOI. VCZDXVP FI'P RCPI IG CUFAFOXIC IDC VGPPFRFUFIFCP SC QXO CXPFUY ZTUC GTI. FI'P TOUFBCUY SC'ZC PCCFOL TVMXICP IG IDC ZTO-GN-IDC-AFUU LI, SDFQD SXP JTPI CHICOPFECUY TVMXICM NGZ IDC 2018 AGMCU YCXZ. IDC LI350 XUPG ZCQCFECM PCECZXU TVMXICP FO XMEXOQC GN IDC FOIZGMTQIFGO GN FIP RFLLCZ RZGIDCZ, IDC LI500, SDFQD FP RZXOM-OCS. SFID IDGPC VGPPFRFUFIFCP CUFAFOXICM, FI'P ZCXPGOXRUC IG XPPTAC IDXI IDC QXAGTNUXLC GO IDFP MCECUGVACOI QXZ FP QGECZFOL TV IDC BCY CUCACOIP GN X OCS LI MCZFEXIFEC, XOM SFID IDC RTUUFII CHFIFOL IDC UFOCTV XNICZ 2020, IDCZC'P VUCOIY GN ZGGA NGZ X OCS, SXZACM-TV EXZFXOI RXPCM GO IDC PIXOMXZM 5.0U LI.");
			MonoalphabeticCipher c5 = new MonoalphabeticCipher("OG XFB TXUFG YKVUFJ UITBOALIBFG, NXVIBV OVURXFO VBTKPQUAOF ZBFOGXV MBNN NQOWB GXQY VBTXVGBVZ LB GLXKJLG GLBVB DXKQY PB “OG QBOZG” 35 VBTKPQUAOF ZBFOGXVZ DLX DXKQY EXGB GX AXFEUAG GVKIT UN GLB EXGB DBVB TVUEOGB. DB YXF’G WFXD UN GLOG DOZ GVKB, OFY UG XPEUXKZQC YUYF’G PBOV XKG UF O TKPQUA EXGB. PKG NQOWB JXG OG GLB NKFYOIBFGOQ YCFOIUA DUGLUF GLB VBTKPQUAOF TOVGC, DLUAL UZ IOFC QODIOWBVZ TVUEOGBQC YUZOJVBB DUGL GLB TVBZUYBFG XF TXQUAC, TXQUGUAZ OFY ALOVOAGBV, PKG LOEB YBAUYBY GLBUV TXQUGUAOQ NKGKVBZ VBZG XF ZGOFYUFJ PC GVKIT. TOVGC QXCOQGC UZ FXG OPFXVIOQ TXQUGUAZ, PKG GLB YBJVBB GX DLUAL VBTKPQUAOF QODIOWBVZ LOEB YBNBFYBY GLB TVBZUYBFG UZ. GVKIT LOZ AVBOGBY OF BFEUVXFIBFG DLBVB GLBVB UZ FX VXXI NXV YBEUOGUXF NVXI LUI BEBF (XV TBVLOTZ BZTBAUOQQC) XF ZXIBGLUFJ OZ ZBVUXKZ OZ GLB OQQBJOGUXFZ NOAUFJ LUI XF KWVOUFB.");
		//	System.out.println(c4.length());
		//	System.out.println(c4.decrypt());
		//	System.out.println(c.length());
		//	System.out.println(c.decrypt());
			System.out.println(c2.length());
			System.out.println(c2.decrypt());
		//	System.out.println(c3.length());
		//	System.out.println(c3.decrypt());
		//	System.out.println(c5.length());
		//	System.out.println(c5.decrypt());
		}
	    long endTime = System.currentTimeMillis();
	    System.out.println("\n\nIt took " + (endTime - startTime) + " milliseconds");
	}

	/**
	 * @return whether to encrypt or decipher
	 */
	public static ACTION determineAction() {
		System.out.println("Would you like to encrypt or decrypt?");
		String myString = userInput.nextLine();
		ACTION determinedAction;
		try {
			determinedAction = ACTION.valueOf(myString.toUpperCase());
		} catch (IllegalArgumentException e) {
			System.out.println("That is not a valid response. You must enter encrypt, decrypt, or magic.");
			determinedAction = ACTION.ENCRYPT;
			System.exit(1);
		}
		
		return determinedAction;
	}
	
	/** Obtain the cipher type from the user
	 * @param action the action that the user chose to take
	 * @return the type of substitution cipher
	 */
	public static String determineCipherType(ACTION action) {
		// DONE Implement determineCipherType()
		if (action.equals(ACTION.DECRYPT)) {
			System.out.println("If you know the type of cipher this ciphertext is, enter it now. Otherwise, enter a new line.");
		} else if (action.equals(ACTION.ENCRYPT)) {
			System.out.println("What type of cipher would you like to use to encrypt the message? (Caesar shift, monoalphabetic, Vigenere");
		}
		
		String cipherType = userInput.nextLine();
		return cipherType;
	}
	
	/** 
	 * @return the cipher text that the user inputs as a string
	 */
	public static String determineCiphertext() {
		// DONE Implement determineCiphertext() method 
		System.out.println("Enter a ciphertext: ");
		String ciphertext = userInput.nextLine();
		return ciphertext;
		
	}
	
	/** Obtain the plain text message from the user 
	 * @return
	 */
	public static String determinePlaintext() {
		// TODO Implement determinePlaintext() method
		System.out.print("Enter a plaintext: ");
		String plaintext = userInput.nextLine();
		return plaintext;
	}
	
	/** Handles all the methods, tasks, and processes if the user chooses to decipher a message
	 * @throws JWNLException
	 */
	protected static void handleDecrypt() throws JWNLException {
		String ciphertext = determineCiphertext();
		String ciphertype = determineCipherType(ACTION.DECRYPT);
		if (ciphertype.equalsIgnoreCase("Caesar shift")) {
			System.out.println(new CaesarShiftCipher(ciphertext).decrypt());
		} else if (ciphertype.equals("monoalphabetic")) {
			System.out.println(new MonoalphabeticCipher(ciphertext).decrypt());
		} else if (ciphertype.equals("Vigenere")) {
			System.out.println(new VigenereCipher(ciphertext).decrypt());
		}
	}
	
	/** Handles all the methods, tasks, and processes if the user chooses to encrypt a message
	 * 
	 */
	protected static void handleEncrypt() {
		String plaintext = determinePlaintext();
		String ciphertype = determineCipherType(ACTION.ENCRYPT);
		if (ciphertype.equalsIgnoreCase("Caesar shift")) {
			System.out.println(new CaesarShiftCipher(plaintext).encrypt());
		} else if (ciphertype.equalsIgnoreCase("monoalphabetic")) {
			System.out.println(new MonoalphabeticCipher(plaintext).encrypt());
		} else if (ciphertype.equalsIgnoreCase("Vigenere")) {
			System.out.println(new VigenereCipher(plaintext).encrypt());
		}
	}
}	
