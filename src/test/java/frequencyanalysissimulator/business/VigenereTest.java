package frequencyanalysissimulator.business;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class VigenereTest {
    @ParameterizedTest
    @MethodSource
    public void testPerformFriedmanTest(String text, int keylength) {
        Vigenere cipher = new Vigenere(text);

        int keyLength = cipher.calculateKeyLengthByFriedmanTest();

        assertEquals(5, keyLength);
    }

    private static Stream<Arguments> testPerformFriedmanTest() {
        return Stream.of(
                Arguments.of(
                        "QPWK ALVRXC QZIKGRB PFAEOMFL JMSD ZVDHXC XJ YEBIMTRQW NMEAI ZRVKC VKVLXN EIC FZPZCZZH KM LVZV ZIZR RQWDKECH OS NYZZL SPMYKV QXJT DCIOMEE XDQV SRXLRLKZH OV",
                        5));
    }

    @ParameterizedTest
    @MethodSource
    public void testCalculateIndexOfCoincidence(String text, double expectedIOC) {
        Vigenere cipher = new Vigenere(text);

        float indexOfCoincidence = cipher.calculateIndexOfCoincidence();

        assertEquals(expectedIOC, indexOfCoincidence, 0.0001);
    }

    private static Stream<Arguments> testCalculateIndexOfCoincidence() {
        return Stream.of(
                Arguments.of(
                        "QPWK ALVRXC QZIKGRB PFAEOMFL JMSD ZVDHXC XJ YEBIMTRQW NMEAI ZRVKC VKVLXN EIC FZPZCZZH KM LVZV ZIZR RQWDKECH OS NYZZL SPMYKV QXJT DCIOMEE XDQV SRXLRLKZH OV",
                        0.04389));
    }

    @Test
    public void testGetKeyOfLength12() {
        Vigenere cipher = new Vigenere(12,
                "QRBAI UWYOK ILBRZ XTUWL EGXSN VDXWR XMHXY FCGMW WWSME LSXUZ MKMFS BNZIF YEIEG RFZRX WKUFA XQEDX DTTHY NTBRJ LHTAI KOCZX QHBND ZIGZG PXARJ EDYSJ NUMKI FLBTN HWISW NVLFM EGXAI AAWSL FMHXR SGRIG HEQTU MLGLV BRSIL AEZSG XCMHT OWHFM LWMRK HPRFB ELWGF RUGPB HNBEM KBNVW HHUEA KILBN BMLHK XUGML YQKHP RFBEL EJYNV WSIJB GAXGO TPMXR TXFKI WUALB RGWIE GHWHG AMEWW LTAEL NUMRE UWTBL SDPRL YVRET LEEDF ROBEQ UXTHX ZYOZB XLKAC KSOHN VWXKS MAEPH IYQMM FSECH RFYPB BSQTX TPIWH GPXQD FWTAI KNNBX SIYKE TXTLV BTMQA LAGHG OTPMX RTXTH XSFYG WMVKH LOIVU ALMLD LTSYV WYNVW MQVXP XRVYA BLXDL XSMLW SUIOI IMELI SOYEB HPHNR WTVUI AKEYG WIETG WWBVM VDUMA EPAUA KXWHK MAUPA MUKHQ PWKCX EFXGW WSDDE OMLWL NKMWD FWTAM FAFEA MFZBN WIHYA LXRWK MAMIK GNGHJ UAZHM HGUAL YSULA ELYHJ BZMSI LAILH WWYIK EWAHN PMLBN NBVPJ XLBEF WRWGX KWIRH XWWGQ HRRXW IOMFY CZHZL VXNVI OYZCM YDDEY IPWXT MMSHS VHHXZ YEWNV OAOEL SMLSW KXXFX STRVI HZLEF JXDAS FIE");
        CharSequence key = cipher.getKey();

        assertEquals("UNITEDSTATES", key);
    }

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
    public void testGetKeyIsCaseInsensitive() {
        Vigenere cipher = new Vigenere(5,
                "lxfopv fdsrow twyw ef rnhr mbq elufgj qubheie oseid fnth fvr twxoao");
        Vigenere capitalizedCipher = new Vigenere(5,
                "LXFOPV FDSROW TWYW EF RNHR MBQ ELUFGJ QUBHEIE OSEID FNTH FVR TWXOAO");

        assertEquals(capitalizedCipher.getKey(), cipher.getKey());
    }

    @Test
    public void testGetIncorrectKey() {
        Vigenere cipher = new Vigenere(4, "YITZU GRFFE TZZOC GSITS XUEAH EIKUT P");
        CharSequence key = cipher.getKey();

        assertEquals("UAPS", key);
    }

    @Test
    public void testGetKeyFromMultilineCipher() {
        Vigenere cipher = new Vigenere(12, """
                QRBAI UWYOK ILBRZ XTUWL EGXSN VDXWR XMHXY FCGMW WWSME LSXUZ
                MKMFS BNZIF YEIEG RFZRX WKUFA XQEDX DTTHY NTBRJ LHTAI KOCZX
                QHBND ZIGZG PXARJ EDYSJ NUMKI FLBTN HWISW NVLFM EGXAI AAWSL
                FMHXR SGRIG HEQTU MLGLV BRSIL AEZSG XCMHT OWHFM LWMRK HPRFB
                ELWGF RUGPB HNBEM KBNVW HHUEA KILBN BMLHK XUGML YQKHP RFBEL
                EJYNV WSIJB GAXGO TPMXR TXFKI WUALB RGWIE GHWHG AMEWW LTAEL
                NUMRE UWTBL SDPRL YVRET LEEDF ROBEQ UXTHX ZYOZB XLKAC KSOHN
                VWXKS MAEPH IYQMM FSECH RFYPB BSQTX TPIWH GPXQD FWTAI KNNBX
                SIYKE TXTLV BTMQA LAGHG OTPMX RTXTH XSFYG WMVKH LOIVU ALMLD
                LTSYV WYNVW MQVXP XRVYA BLXDL XSMLW SUIOI IMELI SOYEB HPHNR
                WTVUI AKEYG WIETG WWBVM VDUMA EPAUA KXWHK MAUPA MUKHQ PWKCX
                EFXGW WSDDE OMLWL NKMWD FWTAM FAFEA MFZBN WIHYA LXRWK MAMIK
                GNGHJ UAZHM HGUAL YSULA ELYHJ BZMSI LAILH WWYIK EWAHN PMLBN
                NBVPJ XLBEF WRWGX KWIRH XWWGQ HRRXW IOMFY CZHZL VXNVI OYZCM
                YDDEY IPWXT MMSHS VHHXZ YEWNV OAOEL SMLSW KXXFX STRVI HZLEF
                JXDAS FIE""");

        assertEquals("UNITEDSTATES", cipher.getKey());
    }

    @Test
    public void testGetKeyOfLength3() {
        Vigenere cipher = new Vigenere(3, "NWAIWEBB RFQFOCJPUGDOJ VBGWSPTWRZ");
        CharSequence key = cipher.getKey();

        assertEquals("BOY", key);
    }

    @Test
    public void testDecrypt() {
        Vigenere cipher = new Vigenere(12, """
                QRBAI UWYOK ILBRZ XTUWL EGXSN VDXWR XMHXY FCGMW WWSME LSXUZ
                    MKMFS BNZIF YEIEG RFZRX WKUFA XQEDX DTTHY NTBRJ LHTAI KOCZX
                    QHBND ZIGZG PXARJ EDYSJ NUMKI FLBTN HWISW NVLFM EGXAI AAWSL
                    FMHXR SGRIG HEQTU MLGLV BRSIL AEZSG XCMHT OWHFM LWMRK HPRFB
                    ELWGF RUGPB HNBEM KBNVW HHUEA KILBN BMLHK XUGML YQKHP RFBEL
                    EJYNV WSIJB GAXGO TPMXR TXFKI WUALB RGWIE GHWHG AMEWW LTAEL
                    NUMRE UWTBL SDPRL YVRET LEEDF ROBEQ UXTHX ZYOZB XLKAC KSOHN
                    VWXKS MAEPH IYQMM FSECH RFYPB BSQTX TPIWH GPXQD FWTAI KNNBX
                    SIYKE TXTLV BTMQA LAGHG OTPMX RTXTH XSFYG WMVKH LOIVU ALMLD
                    LTSYV WYNVW MQVXP XRVYA BLXDL XSMLW SUIOI IMELI SOYEB HPHNR
                    WTVUI AKEYG WIETG WWBVM VDUMA EPAUA KXWHK MAUPA MUKHQ PWKCX
                    EFXGW WSDDE OMLWL NKMWD FWTAM FAFEA MFZBN WIHYA LXRWK MAMIK
                    GNGHJ UAZHM HGUAL YSULA ELYHJ BZMSI LAILH WWYIK EWAHN PMLBN
                    NBVPJ XLBEF WRWGX KWIRH XWWGQ HRRXW IOMFY CZHZL VXNVI OYZCM
                    YDDEY IPWXT MMSHS VHHXZ YEWNV OAOEL SMLSW KXXFX STRVI HZLEF
                    JXDAS FIE""");
        String expectedPlaintext = "WETHEREFORETHEREPRESENTATIVESOFTHEUNITEDSTATESOFAMERICAINGENERALCONGRESSASSEMBLEDAPPEALINGTOTHESUPREMEJUDGEOFTHEWORLDFORTHERECTITUDEOFOURINTENTIONSDOINTHENAMEANDBYAUTHORITYOFTHEGOODPEOPLEOFTHESECOLONIESSOLEMNLYPUBLISHANDDECLARETHATTHESEUNITEDCOLONIESAREANDOFRIGHTOUGHTTOBEFREEANDINDEPENDENTSTATESTHATTHEYAREABSOLVEDFROMALLALLEGIANCETOTHEBRITISHCROWNANDTHATALLPOLITICALCONNECTIONBETWEENTHEMANDTHESTATEOFGREATBRITAINISANDOUGHTTOBETOTALLYDISSOLVEDANDTHATASFREEANDINDEPENDENTSTATESTHEYHAVEFULLPOWERTOLEVYWARCONCLUDEPEACECONTRACTALLIANCESESTABLISHCOMMERCEANDTODOALLOTHERACTSANDTHINGSWHICHINDEPENDENTSTATESMAYOFRIGHTDOANDFORTHESUPPORTOFTHISDECLARATIONWITHAFIRMRELIANCEONTHEPROTECTIONOFDIVINEPROVIDENCEWEMUTUALLYPLEDGETOEACHOTHEROURLIVESOURFORTUNESANDOURSACREDHONOR";

        assertEquals(expectedPlaintext, cipher.decrypt());
    }

    @Test
    public void testEncrypt() {
        String actualCiphertext = Vigenere.encrypt("""
                Friends, Romans, countrymen, lend me your ears;
                I come to bury Caesar, not to praise him.
                The evil that men do lives after them;
                The good is oft interred with their bones;
                So let it be with Caesar. The noble Brutus
                Hath told you Caesar was ambitious:
                If it were so, it was a grievous fault,
                And grievously hath Caesar answer'd it.
                Here, under leave of Brutus and the rest--
                For Brutus is an honourable man;
                So are they all, all honourable men--
                Come I to speak in Caesar's funeral""", "CRYPTII");

        String expectedCiphertext = """
                HIGTGLATFKPGAKQLLIKGUGEJTGLUGPMJKMITJGRHUMVFZJKGKCVQPKVWVKMEKIQUVFXFBPGVTXEBPCKKTGLWNZTTLINVVPIAMUVYCVHWLKJMUMQVVVPGXLEKKFIAMQTSMCXAAQCCIBBJGNGIAKIGJYGMPMPFZAXJZWKSHAIBJKMAWGWWTYTLIZYRQPFJQVZMJLQNKKUTKMAQZRLTAIIIGTOWCUWYJEBIPUEGBMDQLQARPIVYAPXAITRLHPMZFZRWXZMWEBTKTMCMCDYJZWKSHTVLVYCGXABHFPQKCBWJGHTVPQEMJKIJNVKPGAWCICIAMGCCJPETPQEMJKIJNVKTGKWOVGIHAXGRIXGKIGJYGLNCPVPPE""";

        assertEquals(expectedCiphertext, actualCiphertext);
    }
}
