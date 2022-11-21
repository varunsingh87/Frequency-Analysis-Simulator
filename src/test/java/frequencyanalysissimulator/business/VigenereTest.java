package frequencyanalysissimulator.business;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VigenereTest {
    @Test
    public void testGetKeyOfLength8() {
        Vigenere cipher = new Vigenere(8,
                "VVQGY TVVVK ALURW FHQAC MMVLE HUCAT WFHHI PLXHV UWSCI GINCM UHNHQ RMSUI MHWZO DXTNA EKVVQ GYTVV QPHXI NWCAB ASYYM TKSZR CXWRP RFWYH XYGFI PSBWK QAMZY BXJQQ ABJEM TCHQS NAEKV VQGYT VVPCA QPBSL URQUC VMVPQ UTMML VHWDH NFIKJ CPXMY EIOCD TXBJW KQGAN");
        CharSequence key = cipher.getKey();

        assertEquals("COMPUTER", key);
    }

    @Test
    public void testGetKeyOfLength7() {
        Vigenere cipher = new Vigenere(7,
                "WQXYM REOBP VWHTH QYEQV EDEXR BGSIZ SILGR TAJFZ OAMAV VXGRF QGKCP IOZIJ BCBLU WYRWS TUGVQ PSUDI UWOES FMTBT ANCYZ TKTYB VFDKD ERSIB JECAQ DWPDE RIEKG PRAQF BGTHQ KVVGR AXAVT HARQE ELUEC GVVBJ EBXIJ AKNGE SWTKB EDXPB QOUDW VTXES MRUWW RPAWK MTITK HFWTD AURRV FESFE STKSH FLZAE ONEXZ BWTIA RWWTT HQYEQ VEDEX RBGSO REDMT ICM");
        CharSequence key = cipher.getKey();

        assertEquals("AMERICA", key);
    }

    @Test
    public void testGetKeyOfLength6() {
        Vigenere cipher = new Vigenere(6,
                "TYWUR USHPO SLJNQ AYJLI FTMJY YZFPV EUZTS GAHTU WNSFW EEEVA MYFFD CZTMJ WSQEJ VWXTU QNANT MTIAW AOOJS HPPIN TYDDM VKQUF LGMLB XIXJU BQWXJ YQZJZ YMMZH DMFNQ VIAYE FLVZI ZQCSS AEEXV SFRDS DLBQT YDTFQ NIVKU ZPJFJ HUSLK LUBQV JULAB XYWCD IEOWH FTMXZ MMZHC AATFX YWGMF XYWZU QVPYF AIAFJ GEQCV KNATE MWGKX SMWNA NIUSH PFSRJ CEQEE VJXGG BLBQI MEYMR DSDHU UZXVV VGFXV JZXUI JLIRM RKZYY ASETY MYWWJ IYTMJ KFQQT ZFAQK IJFIP FSYAG QXZVK UZPHF ZCYOS LJNQE MVK");
        CharSequence key = cipher.getKey();

        assertEquals("SUMMER", key);
    }

    @Test
    public void testGetIncorrectKey() {
        Vigenere cipher = new Vigenere(4, "YITZU GRFFE TZZOC GSITS XUEAH EIKUT P");
        CharSequence key = cipher.getKey();

        assertEquals("UAPS", key);
    }

    @Test
    public void testGetKeyOfLength3() {
        Vigenere cipher = new Vigenere(3, "NWAIWEBB RFQFOCJPUGDOJ VBGWSPTWRZ");
        CharSequence key = cipher.getKey();

        assertEquals("BOY", key);
    }
}
