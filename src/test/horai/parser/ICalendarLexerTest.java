package horai.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Token;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class ICalendarLexerTest {

    /**
     * Helper method to get exactly 1 token from `source`. It will fail when
     * there are less, or more than a single token in `source`.
     *
     * @param source
     *         the input source to tokenize.
     *
     * @return a token created from `source`.
     */
    private Token getToken(String source) {

        List<? extends Token> tokens = getTokens(source);

        if (tokens.size() != 1) {
            fail("expected exactly 1 token for input: '" + source + "', encountered: " + tokens);
        }

        return tokens.get(0);
    }

    /**
     * Creates a list of tokens given some input `source`.
     *
     * @param source
     *         the input to tokenize.
     *
     * @return a list of tokens given some input `source`.
     */
    private List<? extends Token> getTokens(String source) {

        ICalendarLexer lexer = new ICalendarLexer(new ANTLRInputStream(source));
        return lexer.getAllTokens();
    }

    @Test
    public void ACCEPTED_Test() {
        assertThat(getToken("ACCEPTED").getType(), is(ICalendarLexer.ACCEPTED));
        assertThat(getToken("accEPTEd").getType(), is(ICalendarLexer.ACCEPTED));
    }

    @Test
    public void ACTION_Test() {
        assertThat(getToken("ACTION").getType(), is(ICalendarLexer.ACTION));
        assertThat(getToken("aCTiON").getType(), is(ICalendarLexer.ACTION));
    }

    @Test
    public void ADDRESS_Test() {
        assertThat(getToken("ADDRESS").getType(), is(ICalendarLexer.ADDRESS));
        assertThat(getToken("AdDReSS").getType(), is(ICalendarLexer.ADDRESS));
    }

    @Test
    public void ALTREP_Test() {
        assertThat(getToken("ALTREP").getType(), is(ICalendarLexer.ALTREP));
        assertThat(getToken("aLTRep").getType(), is(ICalendarLexer.ALTREP));
    }

    @Test
    public void ATTACH_Test() {
        assertThat(getToken("ATTACH").getType(), is(ICalendarLexer.ATTACH));
        assertThat(getToken("AttACH").getType(), is(ICalendarLexer.ATTACH));
    }

    @Test
    public void ATTENDEE_Test() {
        assertThat(getToken("ATTENDEE").getType(), is(ICalendarLexer.ATTENDEE));
        assertThat(getToken("ATTeNDee").getType(), is(ICalendarLexer.ATTENDEE));
    }

    @Test
    public void AUDIO_Test() {
        assertThat(getToken("AUDIO").getType(), is(ICalendarLexer.AUDIO));
        assertThat(getToken("AUDio").getType(), is(ICalendarLexer.AUDIO));
    }

    @Test
    public void BASE_Test() {
        assertThat(getToken("BASE").getType(), is(ICalendarLexer.BASE));
        assertThat(getToken("BasE").getType(), is(ICalendarLexer.BASE));
    }

    @Test
    public void BEGIN_Test() {
        assertThat(getToken("BEGIN").getType(), is(ICalendarLexer.BEGIN));
        assertThat(getToken("BEGiN").getType(), is(ICalendarLexer.BEGIN));
    }

    @Test
    public void BINARY_Test() {
        assertThat(getToken("BINARY").getType(), is(ICalendarLexer.BINARY));
        assertThat(getToken("BiNARY").getType(), is(ICalendarLexer.BINARY));
    }

    @Test
    public void BIT_Test() {
        assertThat(getToken("BIT").getType(), is(ICalendarLexer.BIT));
        assertThat(getToken("BIt").getType(), is(ICalendarLexer.BIT));
    }

    @Test
    public void BOOLEAN_Test() {
        assertThat(getToken("BOOLEAN").getType(), is(ICalendarLexer.BOOLEAN));
        assertThat(getToken("BooLEAN").getType(), is(ICalendarLexer.BOOLEAN));
    }

    @Test
    public void BUSY_Test() {
        assertThat(getToken("BUSY").getType(), is(ICalendarLexer.BUSY));
        assertThat(getToken("BuSY").getType(), is(ICalendarLexer.BUSY));
    }

    @Test
    public void BUSY_UNAVAILABLE_Test() {
        assertThat(getToken("BUSY-UNAVAILABLE").getType(), is(ICalendarLexer.BUSY_UNAVAILABLE));
        assertThat(getToken("BuSY-uNAVAILABLE").getType(), is(ICalendarLexer.BUSY_UNAVAILABLE));
    }

    @Test
    public void BUSY_TENTATIVE_Test() {
        assertThat(getToken("BUSY-TENTATIVE").getType(), is(ICalendarLexer.BUSY_TENTATIVE));
        assertThat(getToken("BUSY-teNTATIVE").getType(), is(ICalendarLexer.BUSY_TENTATIVE));
    }

    @Test
    public void BYDAY_Test() {
        assertThat(getToken("BYDAY").getType(), is(ICalendarLexer.BYDAY));
        assertThat(getToken("ByDAy").getType(), is(ICalendarLexer.BYDAY));
    }

    @Test
    public void BYHOUR_Test() {
        assertThat(getToken("BYHOUR").getType(), is(ICalendarLexer.BYHOUR));
        assertThat(getToken("ByHOUR").getType(), is(ICalendarLexer.BYHOUR));
    }

    @Test
    public void BYMINUTE_Test() {
        assertThat(getToken("BYMINUTE").getType(), is(ICalendarLexer.BYMINUTE));
        assertThat(getToken("ByMINUTE").getType(), is(ICalendarLexer.BYMINUTE));
    }

    @Test
    public void BYMONTH_Test() {
        assertThat(getToken("BYMONTH").getType(), is(ICalendarLexer.BYMONTH));
        assertThat(getToken("byMONTH").getType(), is(ICalendarLexer.BYMONTH));
    }

    @Test
    public void BYMONTHDAY_Test() {
        assertThat(getToken("BYMONTHDAY").getType(), is(ICalendarLexer.BYMONTHDAY));
        assertThat(getToken("byMONTHDAY").getType(), is(ICalendarLexer.BYMONTHDAY));
    }

    @Test
    public void BYSECOND_Test() {
        assertThat(getToken("BYSECOND").getType(), is(ICalendarLexer.BYSECOND));
        assertThat(getToken("bySECOND").getType(), is(ICalendarLexer.BYSECOND));
    }

    @Test
    public void BYSETPOS_Test() {
        assertThat(getToken("BYSETPOS").getType(), is(ICalendarLexer.BYSETPOS));
        assertThat(getToken("BYSETPOS").getType(), is(ICalendarLexer.BYSETPOS));
    }

    @Test
    public void BYWEEKNO_Test() {
        assertThat(getToken("BYWEEKNO").getType(), is(ICalendarLexer.BYWEEKNO));
        assertThat(getToken("byweekno").getType(), is(ICalendarLexer.BYWEEKNO));
    }

    @Test
    public void BYYEARDAY_Test() {
        assertThat(getToken("BYYEARDAY").getType(), is(ICalendarLexer.BYYEARDAY));
        assertThat(getToken("ByyEARDAY").getType(), is(ICalendarLexer.BYYEARDAY));
    }

    @Test
    public void CAL_ADDRESS_Test() {
        assertThat(getToken("CAL-ADDRESS").getType(), is(ICalendarLexer.CAL_ADDRESS));
        assertThat(getToken("CAl-ADDrEss").getType(), is(ICalendarLexer.CAL_ADDRESS));
    }

    @Test
    public void CALSCALE_Test() {
        assertThat(getToken("CALSCALE").getType(), is(ICalendarLexer.CALSCALE));
        assertThat(getToken("CALSCaLE").getType(), is(ICalendarLexer.CALSCALE));
    }

    @Test
    public void CANCELLED_Test() {
        assertThat(getToken("CANCELLED").getType(), is(ICalendarLexer.CANCELLED));
        assertThat(getToken("CANCeLLeD").getType(), is(ICalendarLexer.CANCELLED));
    }

    @Test
    public void CATEGORIES_Test() {
        assertThat(getToken("CATEGORIES").getType(), is(ICalendarLexer.CATEGORIES));
        assertThat(getToken("cATeGorIeS").getType(), is(ICalendarLexer.CATEGORIES));
    }

    @Test
    public void CHAIR_Test() {
        assertThat(getToken("CHAIR").getType(), is(ICalendarLexer.CHAIR));
        assertThat(getToken("CHaIR").getType(), is(ICalendarLexer.CHAIR));
    }

    @Test
    public void CHILD_Test() {
        assertThat(getToken("CHILD").getType(), is(ICalendarLexer.CHILD));
        assertThat(getToken("CHiLD").getType(), is(ICalendarLexer.CHILD));
    }

    @Test
    public void CLASS_Test() {
        assertThat(getToken("CLASS").getType(), is(ICalendarLexer.CLASS));
        assertThat(getToken("CLaSS").getType(), is(ICalendarLexer.CLASS));
    }

    @Test
    public void CN_Test() {
        assertThat(getToken("CN").getType(), is(ICalendarLexer.CN));
        assertThat(getToken("Cn").getType(), is(ICalendarLexer.CN));
    }

    @Test
    public void COMMENT_Test() {
        assertThat(getToken("COMMENT").getType(), is(ICalendarLexer.COMMENT));
        assertThat(getToken("COmmENT").getType(), is(ICalendarLexer.COMMENT));
    }

    @Test
    public void COMPLETED_Test() {
        assertThat(getToken("COMPLETED").getType(), is(ICalendarLexer.COMPLETED));
        assertThat(getToken("COMPlETED").getType(), is(ICalendarLexer.COMPLETED));
    }

    @Test
    public void CONFIDENTIAL_Test() {
        assertThat(getToken("CONFIDENTIAL").getType(), is(ICalendarLexer.CONFIDENTIAL));
        assertThat(getToken("CONFiDENTIAL").getType(), is(ICalendarLexer.CONFIDENTIAL));
    }

    @Test
    public void CONFIRMED_Test() {
        assertThat(getToken("CONFIRMED").getType(), is(ICalendarLexer.CONFIRMED));
        assertThat(getToken("CONFiRMED").getType(), is(ICalendarLexer.CONFIRMED));
    }

    @Test
    public void CONTACT_Test() {
        assertThat(getToken("CONTACT").getType(), is(ICalendarLexer.CONTACT));
        assertThat(getToken("CONTAcT").getType(), is(ICalendarLexer.CONTACT));
    }

    @Test
    public void COUNT_Test() {
        assertThat(getToken("COUNT").getType(), is(ICalendarLexer.COUNT));
        assertThat(getToken("COUNt").getType(), is(ICalendarLexer.COUNT));
    }

    @Test
    public void CREATED_Test() {
        assertThat(getToken("CREATED").getType(), is(ICalendarLexer.CREATED));
        assertThat(getToken("CREATEd").getType(), is(ICalendarLexer.CREATED));
    }

    @Test
    public void CUTYPE_Test() {
        assertThat(getToken("CUTYPE").getType(), is(ICalendarLexer.CUTYPE));
        assertThat(getToken("cUTYPE").getType(), is(ICalendarLexer.CUTYPE));
    }

    @Test
    public void DAILY_Test() {
        assertThat(getToken("DAILY").getType(), is(ICalendarLexer.DAILY));
        assertThat(getToken("DAILY").getType(), is(ICalendarLexer.DAILY));
    }

    @Test
    public void DATE_Test() {
        assertThat(getToken("DATE").getType(), is(ICalendarLexer.DATE));
        assertThat(getToken("dATE").getType(), is(ICalendarLexer.DATE));
    }

    @Test
    public void DATE_TIME_Test() {
        assertThat(getToken("DATE-TIME").getType(), is(ICalendarLexer.DATE_TIME));
        assertThat(getToken("DATE-time").getType(), is(ICalendarLexer.DATE_TIME));
    }

    @Test
    public void DAYLIGHT_Test() {
        assertThat(getToken("DAYLIGHT").getType(), is(ICalendarLexer.DAYLIGHT));
        assertThat(getToken("DaYLIGHT").getType(), is(ICalendarLexer.DAYLIGHT));
    }

    @Test
    public void DECLINED_Test() {
        assertThat(getToken("DECLINED").getType(), is(ICalendarLexer.DECLINED));
        assertThat(getToken("DeCLINED").getType(), is(ICalendarLexer.DECLINED));
    }

    @Test
    public void DELEGATED_Test() {
        assertThat(getToken("DELEGATED").getType(), is(ICalendarLexer.DELEGATED));
        assertThat(getToken("DeLEGATED").getType(), is(ICalendarLexer.DELEGATED));
    }

    @Test
    public void DELEGATED_FROM_Test() {
        assertThat(getToken("DELEGATED-FROM").getType(), is(ICalendarLexer.DELEGATED_FROM));
        assertThat(getToken("dELEGATED-FROM").getType(), is(ICalendarLexer.DELEGATED_FROM));
    }

    @Test
    public void DELEGATED_TO_Test() {
        assertThat(getToken("DELEGATED-TO").getType(), is(ICalendarLexer.DELEGATED_TO));
        assertThat(getToken("DeLEGATED-TO").getType(), is(ICalendarLexer.DELEGATED_TO));
    }

    @Test
    public void DESCRIPTION_Test() {
        assertThat(getToken("DESCRIPTION").getType(), is(ICalendarLexer.DESCRIPTION));
        assertThat(getToken("deSCRIPtION").getType(), is(ICalendarLexer.DESCRIPTION));
    }

    @Test
    public void DIR_Test() {
        assertThat(getToken("DIR").getType(), is(ICalendarLexer.DIR));
        assertThat(getToken("dIR").getType(), is(ICalendarLexer.DIR));
    }

    @Test
    public void DISPLAY_Test() {
        assertThat(getToken("DISPLAY").getType(), is(ICalendarLexer.DISPLAY));
        assertThat(getToken("dISPLaY").getType(), is(ICalendarLexer.DISPLAY));
    }

    @Test
    public void DRAFT_Test() {
        assertThat(getToken("DRAFT").getType(), is(ICalendarLexer.DRAFT));
        assertThat(getToken("dRaft").getType(), is(ICalendarLexer.DRAFT));
    }

    @Test
    public void DTEND_Test() {
        assertThat(getToken("DTEND").getType(), is(ICalendarLexer.DTEND));
        assertThat(getToken("DteNd").getType(), is(ICalendarLexer.DTEND));
    }

    @Test
    public void DTSTAMP_Test() {
        assertThat(getToken("DTSTAMP").getType(), is(ICalendarLexer.DTSTAMP));
        assertThat(getToken("dTStamP").getType(), is(ICalendarLexer.DTSTAMP));
    }

    @Test
    public void DTSTART_Test() {
        assertThat(getToken("DTSTART").getType(), is(ICalendarLexer.DTSTART));
        assertThat(getToken("dTSTaRt").getType(), is(ICalendarLexer.DTSTART));
    }

    @Test
    public void DUE_Test() {
        assertThat(getToken("DUE").getType(), is(ICalendarLexer.DUE));
        assertThat(getToken("due").getType(), is(ICalendarLexer.DUE));
    }

    @Test
    public void DURATION_Test() {
        assertThat(getToken("DURATION").getType(), is(ICalendarLexer.DURATION));
        assertThat(getToken("duRatION").getType(), is(ICalendarLexer.DURATION));
    }

    @Test
    public void EMAIL_Test() {
        assertThat(getToken("EMAIL").getType(), is(ICalendarLexer.EMAIL));
        assertThat(getToken("emaIL").getType(), is(ICalendarLexer.EMAIL));
    }

    @Test
    public void ENCODING_Test() {
        assertThat(getToken("ENCODING").getType(), is(ICalendarLexer.ENCODING));
        assertThat(getToken("eNCOdING").getType(), is(ICalendarLexer.ENCODING));
    }

    @Test
    public void END_Test() {
        assertThat(getToken("END").getType(), is(ICalendarLexer.END));
        assertThat(getToken("eNd").getType(), is(ICalendarLexer.END));
    }

    @Test
    public void EXDATE_Test() {
        assertThat(getToken("EXDATE").getType(), is(ICalendarLexer.EXDATE));
        assertThat(getToken("eXdate").getType(), is(ICalendarLexer.EXDATE));
    }

    @Test
    public void FALSE_Test() {
        assertThat(getToken("FALSE").getType(), is(ICalendarLexer.FALSE));
        assertThat(getToken("faLSe").getType(), is(ICalendarLexer.FALSE));
    }

    @Test
    public void FBTYPE_Test() {
        assertThat(getToken("FBTYPE").getType(), is(ICalendarLexer.FBTYPE));
        assertThat(getToken("fBtYPe").getType(), is(ICalendarLexer.FBTYPE));
    }

    @Test
    public void FINAL_Test() {
        assertThat(getToken("FINAL").getType(), is(ICalendarLexer.FINAL));
        assertThat(getToken("fINaL").getType(), is(ICalendarLexer.FINAL));
    }

    @Test
    public void FLOAT_Test() {
        assertThat(getToken("FLOAT").getType(), is(ICalendarLexer.FLOAT));
        assertThat(getToken("fLOat").getType(), is(ICalendarLexer.FLOAT));
    }

    @Test
    public void FMTTYPE_Test() {
        assertThat(getToken("FMTTYPE").getType(), is(ICalendarLexer.FMTTYPE));
        assertThat(getToken("fmTtYPe").getType(), is(ICalendarLexer.FMTTYPE));
    }

    @Test
    public void FR_Test() {
        assertThat(getToken("FR").getType(), is(ICalendarLexer.FR));
        assertThat(getToken("fR").getType(), is(ICalendarLexer.FR));
    }

    @Test
    public void FREE_Test() {
        assertThat(getToken("FREE").getType(), is(ICalendarLexer.FREE));
        assertThat(getToken("fRee").getType(), is(ICalendarLexer.FREE));
    }

    @Test
    public void FREEBUSY_Test() {
        assertThat(getToken("FREEBUSY").getType(), is(ICalendarLexer.FREEBUSY));
        assertThat(getToken("fReeBuSY").getType(), is(ICalendarLexer.FREEBUSY));
    }

    @Test
    public void FREQ_Test() {
        assertThat(getToken("FREQ").getType(), is(ICalendarLexer.FREQ));
        assertThat(getToken("fReQ").getType(), is(ICalendarLexer.FREQ));
    }

    @Test
    public void GEO_Test() {
        assertThat(getToken("GEO").getType(), is(ICalendarLexer.GEO));
        assertThat(getToken("GeO").getType(), is(ICalendarLexer.GEO));
    }

    @Test
    public void GREGORIAN_Test() {
        assertThat(getToken("GREGORIAN").getType(), is(ICalendarLexer.GREGORIAN));
        assertThat(getToken("GReGORIaN").getType(), is(ICalendarLexer.GREGORIAN));
    }

    @Test
    public void GROUP_Test() {
        assertThat(getToken("GROUP").getType(), is(ICalendarLexer.GROUP));
        assertThat(getToken("GROuP").getType(), is(ICalendarLexer.GROUP));
    }

    @Test
    public void HOURLY_Test() {
        assertThat(getToken("HOURLY").getType(), is(ICalendarLexer.HOURLY));
        assertThat(getToken("HOuRLY").getType(), is(ICalendarLexer.HOURLY));
    }

    @Test
    public void IN_PROGRESS_Test() {
        assertThat(getToken("IN-PROGRESS").getType(), is(ICalendarLexer.IN_PROGRESS));
        assertThat(getToken("IN-PROGReSS").getType(), is(ICalendarLexer.IN_PROGRESS));
    }

    @Test
    public void INDIVIDUAL_Test() {
        assertThat(getToken("INDIVIDUAL").getType(), is(ICalendarLexer.INDIVIDUAL));
        assertThat(getToken("INDIVIduaL").getType(), is(ICalendarLexer.INDIVIDUAL));
    }

    @Test
    public void INTEGER_Test() {
        assertThat(getToken("INTEGER").getType(), is(ICalendarLexer.INTEGER));
        assertThat(getToken("INteGeR").getType(), is(ICalendarLexer.INTEGER));
    }

    @Test
    public void INTERVAL_Test() {
        assertThat(getToken("INTERVAL").getType(), is(ICalendarLexer.INTERVAL));
        assertThat(getToken("INteRVaL").getType(), is(ICalendarLexer.INTERVAL));
    }

    @Test
    public void K_D_Test() {
        assertThat(getToken("D").getType(), is(ICalendarLexer.K_D));
        assertThat(getToken("d").getType(), is(ICalendarLexer.K_D));
    }

    @Test
    public void K_H_Test() {
        assertThat(getToken("H").getType(), is(ICalendarLexer.K_H));
        assertThat(getToken("h").getType(), is(ICalendarLexer.K_H));
    }

    @Test
    public void K_M_Test() {
        assertThat(getToken("M").getType(), is(ICalendarLexer.K_M));
        assertThat(getToken("m").getType(), is(ICalendarLexer.K_M));
    }

    @Test
    public void K_N_Test() {
        assertThat(getToken("N").getType(), is(ICalendarLexer.K_N));
        assertThat(getToken("n").getType(), is(ICalendarLexer.K_N));
    }

    @Test
    public void K_P_Test() {
        assertThat(getToken("P").getType(), is(ICalendarLexer.K_P));
        assertThat(getToken("p").getType(), is(ICalendarLexer.K_P));
    }

    @Test
    public void K_S_Test() {
        assertThat(getToken("S").getType(), is(ICalendarLexer.K_S));
        assertThat(getToken("s").getType(), is(ICalendarLexer.K_S));
    }

    @Test
    public void K_T_Test() {
        assertThat(getToken("T").getType(), is(ICalendarLexer.K_T));
        assertThat(getToken("t").getType(), is(ICalendarLexer.K_T));
    }

    @Test
    public void K_W_Test() {
        assertThat(getToken("W").getType(), is(ICalendarLexer.K_W));
        assertThat(getToken("w").getType(), is(ICalendarLexer.K_W));
    }

    @Test
    public void K_Z_Test() {
        assertThat(getToken("Z").getType(), is(ICalendarLexer.K_Z));
        assertThat(getToken("z").getType(), is(ICalendarLexer.K_Z));
    }

    @Test
    public void LANGUAGE_Test() {
        assertThat(getToken("LANGUAGE").getType(), is(ICalendarLexer.LANGUAGE));
        assertThat(getToken("LANGuaGe").getType(), is(ICalendarLexer.LANGUAGE));
    }

    @Test
    public void LAST_MODIFIED_Test() {
        assertThat(getToken("LAST-MODIFIED").getType(), is(ICalendarLexer.LAST_MODIFIED));
        assertThat(getToken("LASt-mODIFIeD").getType(), is(ICalendarLexer.LAST_MODIFIED));
    }

    @Test
    public void LOCATION_Test() {
        assertThat(getToken("LOCATION").getType(), is(ICalendarLexer.LOCATION));
        assertThat(getToken("LOCatION").getType(), is(ICalendarLexer.LOCATION));
    }

    @Test
    public void MEMBER_Test() {
        assertThat(getToken("MEMBER").getType(), is(ICalendarLexer.MEMBER));
        assertThat(getToken("MemBeR").getType(), is(ICalendarLexer.MEMBER));
    }

    @Test
    public void METHOD_Test() {
        assertThat(getToken("METHOD").getType(), is(ICalendarLexer.METHOD));
        assertThat(getToken("metHOd").getType(), is(ICalendarLexer.METHOD));
    }

    @Test
    public void MINUTELY_Test() {
        assertThat(getToken("MINUTELY").getType(), is(ICalendarLexer.MINUTELY));
        assertThat(getToken("mINuteLY").getType(), is(ICalendarLexer.MINUTELY));
    }

    @Test
    public void MO_Test() {
        assertThat(getToken("MO").getType(), is(ICalendarLexer.MO));
        assertThat(getToken("mO").getType(), is(ICalendarLexer.MO));
    }

    @Test
    public void MONTHLY_Test() {
        assertThat(getToken("MONTHLY").getType(), is(ICalendarLexer.MONTHLY));
        assertThat(getToken("mONtHLY").getType(), is(ICalendarLexer.MONTHLY));
    }

    @Test
    public void NEEDS_ACTION_Test() {
        assertThat(getToken("NEEDS-ACTION").getType(), is(ICalendarLexer.NEEDS_ACTION));
        assertThat(getToken("NEeDS-ACtION").getType(), is(ICalendarLexer.NEEDS_ACTION));
    }

    @Test
    public void NON_PARTICIPANT_Test() {
        assertThat(getToken("NON-PARTICIPANT").getType(), is(ICalendarLexer.NON_PARTICIPANT));
        assertThat(getToken("NON-PARTICIPANt").getType(), is(ICalendarLexer.NON_PARTICIPANT));
    }

    @Test
    public void OPAQUE_Test() {
        assertThat(getToken("OPAQUE").getType(), is(ICalendarLexer.OPAQUE));
        assertThat(getToken("OPaQue").getType(), is(ICalendarLexer.OPAQUE));
    }

    @Test
    public void OPT_PARTICIPANT_Test() {
        assertThat(getToken("OPT-PARTICIPANT").getType(), is(ICalendarLexer.OPT_PARTICIPANT));
        assertThat(getToken("OPT-PARTICIPANt").getType(), is(ICalendarLexer.OPT_PARTICIPANT));
    }

    @Test
    public void ORGANIZER_Test() {
        assertThat(getToken("ORGANIZER").getType(), is(ICalendarLexer.ORGANIZER));
        assertThat(getToken("ORGaNIZeR").getType(), is(ICalendarLexer.ORGANIZER));
    }

    @Test
    public void PARENT_Test() {
        assertThat(getToken("PARENT").getType(), is(ICalendarLexer.PARENT));
        assertThat(getToken("PaReNt").getType(), is(ICalendarLexer.PARENT));
    }

    @Test
    public void PARTICIPANT_Test() {
        assertThat(getToken("PARTICIPANT").getType(), is(ICalendarLexer.PARTICIPANT));
        assertThat(getToken("PARTICIPaNt").getType(), is(ICalendarLexer.PARTICIPANT));
    }

    @Test
    public void PARTSTAT_Test() {
        assertThat(getToken("PARTSTAT").getType(), is(ICalendarLexer.PARTSTAT));
        assertThat(getToken("PARTSTat").getType(), is(ICalendarLexer.PARTSTAT));
    }

    @Test
    public void PERCENT_COMPLETE_Test() {
        assertThat(getToken("PERCENT-COMPLETE").getType(), is(ICalendarLexer.PERCENT_COMPLETE));
        assertThat(getToken("PERCENT-COmPLEte").getType(), is(ICalendarLexer.PERCENT_COMPLETE));
    }

    @Test
    public void PERIOD_Test() {
        assertThat(getToken("PERIOD").getType(), is(ICalendarLexer.PERIOD));
        assertThat(getToken("PeRIOd").getType(), is(ICalendarLexer.PERIOD));
    }

    @Test
    public void PRIORITY_Test() {
        assertThat(getToken("PRIORITY").getType(), is(ICalendarLexer.PRIORITY));
        assertThat(getToken("PRIORItY").getType(), is(ICalendarLexer.PRIORITY));
    }

    @Test
    public void PRIVATE_Test() {
        assertThat(getToken("PRIVATE").getType(), is(ICalendarLexer.PRIVATE));
        assertThat(getToken("PRIVate").getType(), is(ICalendarLexer.PRIVATE));
    }

    @Test
    public void PROCESS_Test() {
        assertThat(getToken("PROCESS").getType(), is(ICalendarLexer.PROCESS));
        assertThat(getToken("PROCeSS").getType(), is(ICalendarLexer.PROCESS));
    }

    @Test
    public void PRODID_Test() {
        assertThat(getToken("PRODID").getType(), is(ICalendarLexer.PRODID));
        assertThat(getToken("PRODId").getType(), is(ICalendarLexer.PRODID));
    }

    @Test
    public void PUBLIC_Test() {
        assertThat(getToken("PUBLIC").getType(), is(ICalendarLexer.PUBLIC));
        assertThat(getToken("PuBLIC").getType(), is(ICalendarLexer.PUBLIC));
    }

    @Test
    public void RANGE_Test() {
        assertThat(getToken("RANGE").getType(), is(ICalendarLexer.RANGE));
        assertThat(getToken("RaNGe").getType(), is(ICalendarLexer.RANGE));
    }

    @Test
    public void RDATE_Test() {
        assertThat(getToken("RDATE").getType(), is(ICalendarLexer.RDATE));
        assertThat(getToken("Rdate").getType(), is(ICalendarLexer.RDATE));
    }

    @Test
    public void RECUR_Test() {
        assertThat(getToken("RECUR").getType(), is(ICalendarLexer.RECUR));
        assertThat(getToken("ReCuR").getType(), is(ICalendarLexer.RECUR));
    }

    @Test
    public void RECURRENCE_ID_Test() {
        assertThat(getToken("RECURRENCE-ID").getType(), is(ICalendarLexer.RECURRENCE_ID));
        assertThat(getToken("RECuRRENCe-ID").getType(), is(ICalendarLexer.RECURRENCE_ID));
    }

    @Test
    public void RELAT_Test() {
        assertThat(getToken("RELAT").getType(), is(ICalendarLexer.RELAT));
        assertThat(getToken("ReLat").getType(), is(ICalendarLexer.RELAT));
    }

    @Test
    public void RELATED_Test() {
        assertThat(getToken("RELATED").getType(), is(ICalendarLexer.RELATED));
        assertThat(getToken("ReLated").getType(), is(ICalendarLexer.RELATED));
    }

    @Test
    public void RELATED_TO_Test() {
        assertThat(getToken("RELATED-TO").getType(), is(ICalendarLexer.RELATED_TO));
        assertThat(getToken("RELATeD-tO").getType(), is(ICalendarLexer.RELATED_TO));
    }

    @Test
    public void RELTYPE_Test() {
        assertThat(getToken("RELTYPE").getType(), is(ICalendarLexer.RELTYPE));
        assertThat(getToken("ReLtYPe").getType(), is(ICalendarLexer.RELTYPE));
    }

    @Test
    public void REPEAT_Test() {
        assertThat(getToken("REPEAT").getType(), is(ICalendarLexer.REPEAT));
        assertThat(getToken("RePeat").getType(), is(ICalendarLexer.REPEAT));
    }

    @Test
    public void REQ_PARTICIPANT_Test() {
        assertThat(getToken("REQ-PARTICIPANT").getType(), is(ICalendarLexer.REQ_PARTICIPANT));
        assertThat(getToken("ReQ-PARTICIPANt").getType(), is(ICalendarLexer.REQ_PARTICIPANT));
    }

    @Test
    public void REQUEST_STATUS_Test() {
        assertThat(getToken("REQUEST-STATUS").getType(), is(ICalendarLexer.REQUEST_STATUS));
        assertThat(getToken("REQUeST-STAtuS").getType(), is(ICalendarLexer.REQUEST_STATUS));
    }

    @Test
    public void RESOURCE_Test() {
        assertThat(getToken("RESOURCE").getType(), is(ICalendarLexer.RESOURCE));
        assertThat(getToken("ReSOuRCe").getType(), is(ICalendarLexer.RESOURCE));
    }

    @Test
    public void RESOURCES_Test() {
        assertThat(getToken("RESOURCES").getType(), is(ICalendarLexer.RESOURCES));
        assertThat(getToken("ReSOuRCeS").getType(), is(ICalendarLexer.RESOURCES));
    }

    @Test
    public void ROLE_Test() {
        assertThat(getToken("ROLE").getType(), is(ICalendarLexer.ROLE));
        assertThat(getToken("ROLe").getType(), is(ICalendarLexer.ROLE));
    }

    @Test
    public void ROOM_Test() {
        assertThat(getToken("ROOM").getType(), is(ICalendarLexer.ROOM));
        assertThat(getToken("ROOm").getType(), is(ICalendarLexer.ROOM));
    }

    @Test
    public void RRULE_Test() {
        assertThat(getToken("RRULE").getType(), is(ICalendarLexer.RRULE));
        assertThat(getToken("RRuLe").getType(), is(ICalendarLexer.RRULE));
    }

    @Test
    public void RSVP_Test() {
        assertThat(getToken("RSVP").getType(), is(ICalendarLexer.RSVP));
        assertThat(getToken("RSVP").getType(), is(ICalendarLexer.RSVP));
    }

    @Test
    public void SA_Test() {
        assertThat(getToken("SA").getType(), is(ICalendarLexer.SA));
        assertThat(getToken("Sa").getType(), is(ICalendarLexer.SA));
    }

    @Test
    public void SECONDLY_Test() {
        assertThat(getToken("SECONDLY").getType(), is(ICalendarLexer.SECONDLY));
        assertThat(getToken("SeCONdLY").getType(), is(ICalendarLexer.SECONDLY));
    }

    @Test
    public void SENT_BY_Test() {
        assertThat(getToken("SENT-BY").getType(), is(ICalendarLexer.SENT_BY));
        assertThat(getToken("SeNt-BY").getType(), is(ICalendarLexer.SENT_BY));
    }

    @Test
    public void SEQUENCE_Test() {
        assertThat(getToken("SEQUENCE").getType(), is(ICalendarLexer.SEQUENCE));
        assertThat(getToken("SEQueNCe").getType(), is(ICalendarLexer.SEQUENCE));
    }

    @Test
    public void SIBLING_Test() {
        assertThat(getToken("SIBLING").getType(), is(ICalendarLexer.SIBLING));
        assertThat(getToken("SIBLING").getType(), is(ICalendarLexer.SIBLING));
    }

    @Test
    public void STANDARD_Test() {
        assertThat(getToken("STANDARD").getType(), is(ICalendarLexer.STANDARD));
        assertThat(getToken("StANDaRd").getType(), is(ICalendarLexer.STANDARD));
    }

    @Test
    public void START_Test() {
        assertThat(getToken("START").getType(), is(ICalendarLexer.START));
        assertThat(getToken("STaRt").getType(), is(ICalendarLexer.START));
    }

    @Test
    public void STATUS_Test() {
        assertThat(getToken("STATUS").getType(), is(ICalendarLexer.STATUS));
        assertThat(getToken("STatuS").getType(), is(ICalendarLexer.STATUS));
    }

    @Test
    public void SU_Test() {
        assertThat(getToken("SU").getType(), is(ICalendarLexer.SU));
        assertThat(getToken("Su").getType(), is(ICalendarLexer.SU));
    }

    @Test
    public void SUMMARY_Test() {
        assertThat(getToken("SUMMARY").getType(), is(ICalendarLexer.SUMMARY));
        assertThat(getToken("SuMmaRY").getType(), is(ICalendarLexer.SUMMARY));
    }

    @Test
    public void TENTATIVE_Test() {
        assertThat(getToken("TENTATIVE").getType(), is(ICalendarLexer.TENTATIVE));
        assertThat(getToken("TeNTatIVe").getType(), is(ICalendarLexer.TENTATIVE));
    }

    @Test
    public void TEXT_Test() {
        assertThat(getToken("TEXT").getType(), is(ICalendarLexer.TEXT));
        assertThat(getToken("TeXt").getType(), is(ICalendarLexer.TEXT));
    }

    @Test
    public void TH_Test() {
        assertThat(getToken("TH").getType(), is(ICalendarLexer.TH));
        assertThat(getToken("tH").getType(), is(ICalendarLexer.TH));
    }

    @Test
    public void THISANDFUTURE_Test() {
        assertThat(getToken("THISANDFUTURE").getType(), is(ICalendarLexer.THISANDFUTURE));
        assertThat(getToken("THISaNdfUtuRe").getType(), is(ICalendarLexer.THISANDFUTURE));
    }

    @Test
    public void TIME_Test() {
        assertThat(getToken("TIME").getType(), is(ICalendarLexer.TIME));
        assertThat(getToken("tIme").getType(), is(ICalendarLexer.TIME));
    }

    @Test
    public void TRANSP_Test() {
        assertThat(getToken("TRANSP").getType(), is(ICalendarLexer.TRANSP));
        assertThat(getToken("tRaNSP").getType(), is(ICalendarLexer.TRANSP));
    }

    @Test
    public void TRANSPARENT_Test() {
        assertThat(getToken("TRANSPARENT").getType(), is(ICalendarLexer.TRANSPARENT));
        assertThat(getToken("TRANSPaReNt").getType(), is(ICalendarLexer.TRANSPARENT));
    }

    @Test
    public void TRIGGER_Test() {
        assertThat(getToken("TRIGGER").getType(), is(ICalendarLexer.TRIGGER));
        assertThat(getToken("tRIGGeR").getType(), is(ICalendarLexer.TRIGGER));
    }

    @Test
    public void TRUE_Test() {
        assertThat(getToken("TRUE").getType(), is(ICalendarLexer.TRUE));
        assertThat(getToken("tRue").getType(), is(ICalendarLexer.TRUE));
    }

    @Test
    public void TU_Test() {
        assertThat(getToken("TU").getType(), is(ICalendarLexer.TU));
        assertThat(getToken("tu").getType(), is(ICalendarLexer.TU));
    }

    @Test
    public void TZID_Test() {
        assertThat(getToken("TZID").getType(), is(ICalendarLexer.TZID));
        assertThat(getToken("tZId").getType(), is(ICalendarLexer.TZID));
    }

    @Test
    public void TZNAME_Test() {
        assertThat(getToken("TZNAME").getType(), is(ICalendarLexer.TZNAME));
        assertThat(getToken("tZName").getType(), is(ICalendarLexer.TZNAME));
    }

    @Test
    public void TZOFFSETFROM_Test() {
        assertThat(getToken("TZOFFSETFROM").getType(), is(ICalendarLexer.TZOFFSETFROM));
        assertThat(getToken("TZOFFSetfROm").getType(), is(ICalendarLexer.TZOFFSETFROM));
    }

    @Test
    public void TZOFFSETTO_Test() {
        assertThat(getToken("TZOFFSETTO").getType(), is(ICalendarLexer.TZOFFSETTO));
        assertThat(getToken("TZOFfSeTtO").getType(), is(ICalendarLexer.TZOFFSETTO));
    }

    @Test
    public void TZURL_Test() {
        assertThat(getToken("TZURL").getType(), is(ICalendarLexer.TZURL));
        assertThat(getToken("tZuRL").getType(), is(ICalendarLexer.TZURL));
    }

    @Test
    public void UID_Test() {
        assertThat(getToken("UID").getType(), is(ICalendarLexer.UID));
        assertThat(getToken("uId").getType(), is(ICalendarLexer.UID));
    }

    @Test
    public void UNKNOWN_Test() {
        assertThat(getToken("UNKNOWN").getType(), is(ICalendarLexer.UNKNOWN));
        assertThat(getToken("uNKNOwN").getType(), is(ICalendarLexer.UNKNOWN));
    }

    @Test
    public void UNTIL_Test() {
        assertThat(getToken("UNTIL").getType(), is(ICalendarLexer.UNTIL));
        assertThat(getToken("uNtIL").getType(), is(ICalendarLexer.UNTIL));
    }

    @Test
    public void URI_Test() {
        assertThat(getToken("URI").getType(), is(ICalendarLexer.URI));
        assertThat(getToken("uRI").getType(), is(ICalendarLexer.URI));
    }

    @Test
    public void URL_Test() {
        assertThat(getToken("URL").getType(), is(ICalendarLexer.URL));
        assertThat(getToken("uRL").getType(), is(ICalendarLexer.URL));
    }

    @Test
    public void UTC_OFFSET_Test() {
        assertThat(getToken("UTC-OFFSET").getType(), is(ICalendarLexer.UTC_OFFSET));
        assertThat(getToken("uTC-OFFSet").getType(), is(ICalendarLexer.UTC_OFFSET));
    }

    @Test
    public void VALARM_Test() {
        assertThat(getToken("VALARM").getType(), is(ICalendarLexer.VALARM));
        assertThat(getToken("VALaRm").getType(), is(ICalendarLexer.VALARM));
    }

    @Test
    public void VALUE_Test() {
        assertThat(getToken("VALUE").getType(), is(ICalendarLexer.VALUE));
        assertThat(getToken("VaLue").getType(), is(ICalendarLexer.VALUE));
    }

    @Test
    public void VCALENDAR_Test() {
        assertThat(getToken("VCALENDAR").getType(), is(ICalendarLexer.VCALENDAR));
        assertThat(getToken("VCALeNdaR").getType(), is(ICalendarLexer.VCALENDAR));
    }

    @Test
    public void VERSION_Test() {
        assertThat(getToken("VERSION").getType(), is(ICalendarLexer.VERSION));
        assertThat(getToken("VeRSION").getType(), is(ICalendarLexer.VERSION));
    }

    @Test
    public void VEVENT_Test() {
        assertThat(getToken("VEVENT").getType(), is(ICalendarLexer.VEVENT));
        assertThat(getToken("VeVeNt").getType(), is(ICalendarLexer.VEVENT));
    }

    @Test
    public void VFREEBUSY_Test() {
        assertThat(getToken("VFREEBUSY").getType(), is(ICalendarLexer.VFREEBUSY));
        assertThat(getToken("VfReeBuSY").getType(), is(ICalendarLexer.VFREEBUSY));
    }

    @Test
    public void VJOURNAL_Test() {
        assertThat(getToken("VJOURNAL").getType(), is(ICalendarLexer.VJOURNAL));
        assertThat(getToken("VJOuRNaL").getType(), is(ICalendarLexer.VJOURNAL));
    }

    @Test
    public void VTIMEZONE_Test() {
        assertThat(getToken("VTIMEZONE").getType(), is(ICalendarLexer.VTIMEZONE));
        assertThat(getToken("VtImeZONe").getType(), is(ICalendarLexer.VTIMEZONE));
    }

    @Test
    public void VTODO_Test() {
        assertThat(getToken("VTODO").getType(), is(ICalendarLexer.VTODO));
        assertThat(getToken("VtOdO").getType(), is(ICalendarLexer.VTODO));
    }

    @Test
    public void WE_Test() {
        assertThat(getToken("WE").getType(), is(ICalendarLexer.WE));
        assertThat(getToken("we").getType(), is(ICalendarLexer.WE));
    }

    @Test
    public void WEEKLY_Test() {
        assertThat(getToken("WEEKLY").getType(), is(ICalendarLexer.WEEKLY));
        assertThat(getToken("weeKLY").getType(), is(ICalendarLexer.WEEKLY));
    }

    @Test
    public void WKST_Test() {
        assertThat(getToken("WKST").getType(), is(ICalendarLexer.WKST));
        assertThat(getToken("wKSt").getType(), is(ICalendarLexer.WKST));
    }

    @Test
    public void YEARLY_Test() {
        assertThat(getToken("YEARLY").getType(), is(ICalendarLexer.YEARLY));
        assertThat(getToken("YeaRLY").getType(), is(ICalendarLexer.YEARLY));
    }

    @Test
    public void X_NAME_Test() {
        assertThat(getToken("X-aaaBBB-xyz").getType(), is(ICalendarLexer.X_NAME));
        assertThat(getToken("x-aaaBBB-xyz").getType(), is(ICalendarLexer.X_NAME));
        assertThat(getToken("X-aaaBBB").getType(), is(ICalendarLexer.X_NAME));
    }

    @Test
    public void NON_KEYWORD_Test() {
        assertThat(getToken("YEARLYy").getType(), is(ICalendarLexer.NON_KEYWORD));
        assertThat(getToken("foobar").getType(), is(ICalendarLexer.NON_KEYWORD));
    }

    @Test
    public void LINE_FOLD_Test() {
        assertThat(getTokens("\r\n ").size(), is(0));
        assertThat(getTokens("\n\t").size(), is(0));
    }

    @Test
    public void WSP_Test() {
        assertThat(getToken(" ").getType(), is(ICalendarLexer.WSP));
        assertThat(getToken("\t").getType(), is(ICalendarLexer.WSP));
    }

    @Test
    public void EscapedChar_Test() {
        assertThat(getToken("\\n").getType(), is(ICalendarLexer.ESCAPED_CHAR));
        assertThat(getToken("\\\\").getType(), is(ICalendarLexer.ESCAPED_CHAR));
        assertThat(getToken("\\,").getType(), is(ICalendarLexer.ESCAPED_CHAR));
        assertThat(getToken("\\;").getType(), is(ICalendarLexer.ESCAPED_CHAR));
    }

    @Test
    public void CRLF_Test() {
        assertThat(getToken("\r\n").getType(), is(ICalendarLexer.CRLF));
        assertThat(getToken("\n").getType(), is(ICalendarLexer.CRLF));
    }

    @Test
    public void X21_Test() {
        assertThat(getToken("!").getType(), is(ICalendarLexer.X21));
    }

    @Test
    public void X22_Test() {
        assertThat(getToken("\"").getType(), is(ICalendarLexer.X22));
    }

    @Test
    public void X23_Test() {
        assertThat(getToken("#").getType(), is(ICalendarLexer.X23));
    }

    @Test
    public void X24_Test() {
        assertThat(getToken("$").getType(), is(ICalendarLexer.X24));
    }

    @Test
    public void X25_Test() {
        assertThat(getToken("%").getType(), is(ICalendarLexer.X25));
    }

    @Test
    public void X26_Test() {
        assertThat(getToken("&").getType(), is(ICalendarLexer.X26));
    }

    @Test
    public void X27_Test() {
        assertThat(getToken("'").getType(), is(ICalendarLexer.X27));
    }

    @Test
    public void X28_Test() {
        assertThat(getToken("(").getType(), is(ICalendarLexer.X28));
    }

    @Test
    public void X29_Test() {
        assertThat(getToken(")").getType(), is(ICalendarLexer.X29));
    }

    @Test
    public void X2A_Test() {
        assertThat(getToken("*").getType(), is(ICalendarLexer.X2A));
    }

    @Test
    public void X2B_Test() {
        assertThat(getToken("+").getType(), is(ICalendarLexer.X2B));
    }

    @Test
    public void X2C_Test() {
        assertThat(getToken(",").getType(), is(ICalendarLexer.X2C));
    }

    @Test
    public void X2D_Test() {
        assertThat(getToken("-").getType(), is(ICalendarLexer.X2D));
    }

    @Test
    public void X2E_Test() {
        assertThat(getToken(".").getType(), is(ICalendarLexer.X2E));
    }

    @Test
    public void X2F_Test() {
        assertThat(getToken("/").getType(), is(ICalendarLexer.X2F));
    }

    @Test
    public void X30_Test() {
        assertThat(getToken("0").getType(), is(ICalendarLexer.X30));
    }

    @Test
    public void X31_Test() {
        assertThat(getToken("1").getType(), is(ICalendarLexer.X31));
    }

    @Test
    public void X32_Test() {
        assertThat(getToken("2").getType(), is(ICalendarLexer.X32));
    }

    @Test
    public void X33_Test() {
        assertThat(getToken("3").getType(), is(ICalendarLexer.X33));
    }

    @Test
    public void X34_Test() {
        assertThat(getToken("4").getType(), is(ICalendarLexer.X34));
    }

    @Test
    public void X35_Test() {
        assertThat(getToken("5").getType(), is(ICalendarLexer.X35));
    }

    @Test
    public void X36_Test() {
        assertThat(getToken("6").getType(), is(ICalendarLexer.X36));
    }

    @Test
    public void X37_Test() {
        assertThat(getToken("7").getType(), is(ICalendarLexer.X37));
    }

    @Test
    public void X38_Test() {
        assertThat(getToken("8").getType(), is(ICalendarLexer.X38));
    }

    @Test
    public void X39_Test() {
        assertThat(getToken("9").getType(), is(ICalendarLexer.X39));
    }

    @Test
    public void X3A_Test() {
        assertThat(getToken(":").getType(), is(ICalendarLexer.X3A));
    }

    @Test
    public void X3B_Test() {
        assertThat(getToken(";").getType(), is(ICalendarLexer.X3B));
    }

    @Test
    public void X3C_Test() {
        assertThat(getToken("<").getType(), is(ICalendarLexer.X3C));
    }

    @Test
    public void X3D_Test() {
        assertThat(getToken("=").getType(), is(ICalendarLexer.X3D));
    }

    @Test
    public void X3E_Test() {
        assertThat(getToken(">").getType(), is(ICalendarLexer.X3E));
    }

    @Test
    public void X3F_Test() {
        assertThat(getToken("?").getType(), is(ICalendarLexer.X3F));
    }

    @Test
    public void X40_Test() {
        assertThat(getToken("@").getType(), is(ICalendarLexer.X40));
    }

    @Test
    public void X5B_Test() {
        assertThat(getToken("[").getType(), is(ICalendarLexer.X5B));
    }

    @Test
    public void X5C_Test() {
        assertThat(getToken("\\").getType(), is(ICalendarLexer.X5C));
    }

    @Test
    public void X5D_Test() {
        assertThat(getToken("]").getType(), is(ICalendarLexer.X5D));
    }

    @Test
    public void X5E_Test() {
        assertThat(getToken("^").getType(), is(ICalendarLexer.X5E));
    }

    @Test
    public void X5F_Test() {
        assertThat(getToken("_").getType(), is(ICalendarLexer.X5F));
    }

    @Test
    public void X60_Test() {
        assertThat(getToken("`").getType(), is(ICalendarLexer.X60));
    }

    @Test
    public void X7B_Test() {
        assertThat(getToken("{").getType(), is(ICalendarLexer.X7B));
    }

    @Test
    public void X7C_Test() {
        assertThat(getToken("|").getType(), is(ICalendarLexer.X7C));
    }

    @Test
    public void X7D_Test() {
        assertThat(getToken("}").getType(), is(ICalendarLexer.X7D));
    }

    @Test
    public void X7E_Test() {
        assertThat(getToken("~").getType(), is(ICalendarLexer.X7E));
    }

    @Test
    public void NON_US_ASCII_Test() {
        for (int ch = 129; ch <= 1024; ch++) {
            assertThat(getToken(String.valueOf((char) ch)).getType(), is(ICalendarLexer.NON_US_ASCII));
        }
    }
}
