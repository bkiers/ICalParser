package horai.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ICalendarParserTest {

    private ICalendarParser getParser(String source) {
        ICalendarLexer lexer = new ICalendarLexer(new ANTLRInputStream(source));
        return new ICalendarParser(new CommonTokenStream(lexer));
    }

    @Test
    public void altrepparamTest() {

        // 3.2.1
        // altrepparam
        //  : ALTREP '=' '"' uri '"'
        //  ;

        String paramName = "ALTREP";
        String value = "CID:part3.msg.970415T083000@example.com";
        String source = paramName + "=\"" + value + "\"";

        ICalendarParser.AltrepparamContext ctx = getParser(source).altrepparam();

        assertThat( ctx.getText(), is(source) );

        assertThat( ctx.ALTREP().getText(), is(paramName) );
        assertThat( ctx.uri().getText(), is(value) );
    }

    @Test
    public void cnparamTest() {

        // 3.2.2
        // cnparam
        //  : CN '=' param_value
        //  ;

        String paramName = "CN";
        String value = "\"John Smith\"";
        String source = paramName + "=" + value;

        ICalendarParser.CnparamContext ctx = getParser(source).cnparam();

        assertThat( ctx.getText(), is(source) );

        assertThat( ctx.CN().getText(), is(paramName) );
        assertThat( ctx.param_value().getText(), is(value) );
    }

    @Test
    public void cutypeparamTest() {

        // 3.2.3
        // cutypeparam
        //  : CUTYPE '=' ( INDIVIDUAL
        //               | GROUP
        //               | RESOURCE
        //               | ROOM
        //               | UNKNOWN
        //               | X_NAME
        //               | iana_token
        //               )
        //  ;


        String paramName = "CUTYPE";
        String value = "GROUP";
        String source = paramName + "=" + value;

        ICalendarParser.CutypeparamContext ctx = getParser(source).cutypeparam();

        assertThat( ctx.getText(), is(source) );

        assertThat( ctx.CUTYPE().getText(), is(paramName) );
        assertThat( ctx.GROUP().getText(), is(value) );

        value = "INDIVIDUAL";
        source = paramName + "=" + value;

        ctx = getParser(source).cutypeparam();

        assertThat( ctx.getText(), is(source) );

        assertThat( ctx.CUTYPE().getText(), is(paramName) );
        assertThat( ctx.INDIVIDUAL().getText(), is(value) );
    }

    @Test
    public void delfromparamTest() {

        // 3.2.4
        // delfromparam
        // : DELEGATED_FROM '=' '"' cal_address '"' (',' '"' cal_address '"')*
        // ;

        String paramName = "DELEGATED-FROM";
        String value1 = "mailto:jsmith@example.com";
        String value2 = "mailto:mu@boo.com";
        String source = paramName + "=\"" + value1 + "\",\"" + value2 + "\"";

        ICalendarParser.DelfromparamContext ctx = getParser(source).delfromparam();

        assertThat( ctx.getText(), is(source) );

        assertThat( ctx.DELEGATED_FROM().getText(), is(paramName) );
        assertThat( ctx.cal_address().size(), is(2) );
        assertThat( ctx.cal_address().get(0).getText(), is(value1) );
        assertThat( ctx.cal_address().get(1).getText(), is(value2) );
    }

    @Test
    public void deltoparamTest() {

        // 3.2.5
        // deltoparam
        // : DELEGATED_TO '=' '"' cal_address '"' (',' '"' cal_address '"')*
        // ;

        String paramName = "DELEGATED-TO";
        String value1 = "mailto:jsmith@example.com";
        String value2 = "mailto:mu@boo.com";
        String source = paramName + "=\"" + value1 + "\",\"" + value2 + "\"";

        ICalendarParser.DeltoparamContext ctx = getParser(source).deltoparam();

        assertThat( ctx.getText(), is(source) );

        assertThat( ctx.DELEGATED_TO().getText(), is(paramName) );
        assertThat( ctx.cal_address().size(), is(2) );
        assertThat( ctx.cal_address().get(0).getText(), is(value1) );
        assertThat( ctx.cal_address().get(1).getText(), is(value2) );
    }

    @Test
    public void dirparamTest() {

        // 3.2.6
        // dirparam
        // : DIR '=' '"' uri '"'
        // ;

        String paramName = "DIR";
        String value = "ldap://example.com:6666/o=ABC%20Industries,c=US???(cn=Jim%20Dolittle)";
        String source = paramName + "=\"" + value + "\"";

        ICalendarParser.DirparamContext ctx = getParser(source).dirparam();

        assertThat( ctx.getText(), is(source) );

        assertThat( ctx.DIR().getText(), is(paramName) );
        assertThat( ctx.uri().getText(), is(value) );
    }

    @Test
    public void encodingparamTest() {

        // 3.2.7
        // encodingparam
        //  : ENCODING '=' ( '8' BIT
        //                 | BASE '6' '4'
        //                 )
        //  ;

        String paramName = "ENCODING";
        String value = "8BIT";

        String source = paramName + "=" + value;

        ICalendarParser.EncodingparamContext ctx = getParser(source).encodingparam();

        assertThat( ctx.getText(), is(source) );

        assertThat( ctx.ENCODING().getText(), is(paramName) );
        assertThat( ctx.BIT().getText(), is(value.replaceAll("\\d", "")) );

        value = "Base64";

        source = paramName + "=" + value;

        ctx = getParser(source).encodingparam();

        assertThat( ctx.getText(), is(source) );

        assertThat( ctx.ENCODING().getText(), is(paramName) );
        assertThat( ctx.BASE().getText(), is(value.replaceAll("\\d", "")) );
    }

    @Test
    public void fmttypeparamTest() {

        // 3.2.8
        // fmttypeparam
        // : FMTTYPE '=' type_name '/' subtype_name
        // ;

        String paramName = "FMTTYPE";
        String value1 = "application";
        String value2 = "msword";
        String source = paramName + "=" + value1 + "/" + value2;

        ICalendarParser.FmttypeparamContext ctx = getParser(source).fmttypeparam();

        assertThat( ctx.getText(), is(source) );

        assertThat( ctx.FMTTYPE().getText(), is(paramName) );
        assertThat( ctx.type_name().getText(), is(value1) );
        assertThat( ctx.subtype_name().getText(), is(value2) );
    }

    @Test
    public void fbtypeparamTest() {

        // 3.2.9
        // fbtypeparam
        //  : FBTYPE '=' ( FREE
        //               | BUSY
        //               | BUSY_UNAVAILABLE
        //               | BUSY_TENTATIVE
        //               | X_NAME
        //               | iana_token
        //               )
        //  ;

        String paramName = "FBTYPE";
        String value = "BUSY-UNAVAILABLE";
        String source = paramName + "=" + value;

        ICalendarParser.FbtypeparamContext ctx = getParser(source).fbtypeparam();

        assertThat( ctx.getText(), is(source) );

        assertThat( ctx.FBTYPE().getText(), is(paramName) );
        assertThat( ctx.BUSY_UNAVAILABLE().getText(), is(value) );

        value = "FREE";
        source = paramName + "=" + value;

        ctx = getParser(source).fbtypeparam();

        assertThat( ctx.getText(), is(source) );

        assertThat( ctx.FBTYPE().getText(), is(paramName) );
        assertThat( ctx.FREE().getText(), is(value) );
    }

    @Test
    public void languageparamTest() {

        // 3.2.10
        // languageparam
        //  : LANGUAGE '=' language
        //  ;

        String paramName = "LANGUAGE";
        String value = "en-US:Company Holiday Party";
        String source = paramName + "=" + value;

        ICalendarParser.LanguageparamContext ctx = getParser(source).languageparam();

        assertThat( ctx.getText(), is(source) );

        assertThat( ctx.LANGUAGE().getText(), is(paramName) );
        assertThat( ctx.language().getText(), is(value) );
    }

    @Test
    public void memberparamTest() {

        // 3.2.11
        // memberparam
        //  : MEMBER '=' '"' cal_address '"' (',' '"' cal_address '"')*
        //  ;

        String paramName = "MEMBER";
        String value1 = "mailto:projectA@example.com";
        String value2 = "mailto:projectB@example.com";
        String source = paramName + "=\"" + value1 + "\",\"" + value2 + "\"";

        ICalendarParser.MemberparamContext ctx = getParser(source).memberparam();

        assertThat( ctx.getText(), is(source) );

        assertThat( ctx.MEMBER().getText(), is(paramName) );
        assertThat( ctx.cal_address().size(), is(2) );
        assertThat( ctx.cal_address().get(0).getText(), is(value1) );
        assertThat( ctx.cal_address().get(1).getText(), is(value2) );
    }

    @Test
    public void partstatparamTest() {

        // 3.2.12
        // partstatparam
        //  : PARTSTAT '=' ( partstat_event
        //                 | partstat_todo
        //                 | partstat_jour
        //                 )
        //  ;

        String paramName = "PARTSTAT";
        String value = "DELEGATED";
        String source = paramName + "=" + value;

        ICalendarParser.PartstatparamContext ctx = getParser(source).partstatparam();

        assertThat( ctx.getText(), is(source) );

        assertThat( ctx.PARTSTAT().getText(), is(paramName) );
        assertThat( ctx.partstat_event().getText(), is(value) );
    }

    @Test
    public void rangeparamTest() {

        // 3.2.13
        // rangeparam
        //  : RANGE '=' THISANDFUTURE
        //  ;

        String paramName = "RANGE";
        String value = "THISANDFUTURE";
        String source = paramName + "=" + value;

        ICalendarParser.RangeparamContext ctx = getParser(source).rangeparam();

        assertThat( ctx.getText(), is(source) );

        assertThat( ctx.RANGE().getText(), is(paramName) );
        assertThat( ctx.THISANDFUTURE().getText(), is(value) );
    }

    @Test
    public void trigrelparamTest() {

        // 3.2.14
        // trigrelparam
        //  : RELATED '=' ( START
        //                | END
        //                )
        //  ;

        String paramName = "RELATED";
        String value = "END";
        String source = paramName + "=" + value;

        ICalendarParser.TrigrelparamContext ctx = getParser(source).trigrelparam();

        assertThat( ctx.getText(), is(source) );

        assertThat( ctx.RELATED().getText(), is(paramName) );
        assertThat( ctx.END().getText(), is(value) );
    }

    @Test
    public void reltypeparamTest() {

        // 3.2.15
        // reltypeparam
        //  : RELTYPE '=' ( PARENT
        //                | CHILD
        //                | SIBLING
        //                | iana_token
        //                | X_NAME
        //                )
        //  ;

        String paramName = "RELTYPE";
        String value = "x-some-name";
        String source = paramName + "=" + value;

        ICalendarParser.ReltypeparamContext ctx = getParser(source).reltypeparam();

        assertThat( ctx.getText(), is(source) );

        assertThat( ctx.RELTYPE().getText(), is(paramName) );
        assertThat( ctx.X_NAME().getText(), is(value) );
    }

    @Test
    public void roleparamTest() {

        // 3.2.16
        // roleparam
        //  : ROLE '=' ( CHAIR
        //             | REQ_PARTICIPANT
        //             | OPT_PARTICIPANT
        //             | NON_PARTICIPANT
        //             | iana_token
        //             | X_NAME
        //             )
        //  ;

        String paramName = "ROLE";
        String value = "iana-some-name";
        String source = paramName + "=" + value;

        ICalendarParser.RoleparamContext ctx = getParser(source).roleparam();

        assertThat( ctx.getText(), is(source) );

        assertThat( ctx.ROLE().getText(), is(paramName) );
        assertThat( ctx.iana_token().getText(), is(value) );
    }

    @Test
    public void rsvpparamTest() {

        // 3.2.17
        // rsvpparam
        //  : RSVP '=' ( TRUE
        //             | FALSE
        //             )
        //  ;


        String paramName = "RSVP";
        String value = "TRUE";
        String source = paramName + "=" + value;

        ICalendarParser.RsvpparamContext ctx = getParser(source).rsvpparam();

        assertThat( ctx.getText(), is(source) );

        assertThat( ctx.RSVP().getText(), is(paramName) );
        assertThat( ctx.TRUE().getText(), is(value) );
    }

    @Test
    public void sentbyparamTest() {

        // 3.2.18
        // sentbyparam
        //  : SENT_BY '=' '"' cal_address '"'
        //  ;

        String paramName = "SENT-BY";
        String value = "mailto:projectA@example.com";
        String source = paramName + "=\"" + value + "\"";

        ICalendarParser.SentbyparamContext ctx = getParser(source).sentbyparam();

        assertThat( ctx.getText(), is(source) );

        assertThat( ctx.SENT_BY().getText(), is(paramName) );
        assertThat( ctx.cal_address().getText(), is(value) );
    }

    @Test
    public void tzidparamTest() {

        // 3.2.19
        // tzidparam
        //  : TZID '=' '/'? paramtext
        //  ;

        String paramName = "TZID";
        String value = "America/New_York";
        String source = paramName + "=/" + value;

        ICalendarParser.TzidparamContext ctx = getParser(source).tzidparam();

        assertThat( ctx.getText(), is(source) );

        assertThat( ctx.TZID().getText(), is(paramName) );
        assertThat( ctx.paramtext().getText(), is(value) );
    }

    @Test
    public void valuetypeparamTest() {

        // 3.2.20
        // valuetypeparam
        //  : VALUE '=' valuetype
        //  ;

        String paramName = "VALUE";
        String value = "TIME";
        String source = paramName + "=" + value;

        ICalendarParser.ValuetypeparamContext ctx = getParser(source).valuetypeparam();

        assertThat( ctx.getText(), is(source) );

        assertThat( ctx.VALUE().getText(), is(paramName) );
        assertThat( ctx.valuetype().getText(), is(value) );
    }

    @Test
    public void binaryTest() {

        // 3.3.1 - A "BASE64" encoded character string, as defined by [RFC4648].
        // binary
        //  : b_chars b_end?
        //  ;

        String value = "AAABAAEAEBAQAAEABAAoAQAAFgAAACgAAAAQAAAAIAAAAAEABAAA\n" +
                " AAAAAAAAAAAAAAAAAAAAAAAAAAAAAACAAAAAgIAAAICAgADAwMAA////AAAA\n" +
                " AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                " AAAAAAAAAAAAAAAAAAAAAAMwAAAAAAABNEMQAAAAAAAkQgAAAAAAJEREQgAA\n" +
                " ACECQ0QgEgAAQxQzM0E0AABERCRCREQAADRDJEJEQwAAAhA0QwEQAAAAAERE\n" +
                " AAAAAAAAREQAAAAAAAAkQgAAAAAAAAMgAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                " AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                " AAAAAAAAAA";

        String end = "==";

        String source = value + end;

        ICalendarParser.BinaryContext ctx = getParser(source).binary();

        assertThat( ctx.b_chars().getText(), is(value.replace("\n ", "")) );
        assertThat( ctx.b_end().getText(), is(end) );
    }

    @Test
    public void boolTest() {

        // 3.3.2
        // bool
        //  : TRUE
        //  | FALSE
        //  ;

        String source = "trUe";

        ICalendarParser.BoolContext ctx = getParser(source).bool();

        assertThat( ctx.TRUE().getText(), is(source) );
    }

    @Test
    public void cal_addressTest() {

        // 3.3.3
        // cal_address
        //  : uri
        //  ;

        String source = "mailto:jane_doe@example.com";

        ICalendarParser.Cal_addressContext ctx = getParser(source).cal_address();

        assertThat( ctx.uri().getText(), is(source) );
    }

    @Test
    public void dateTest() {

        // 3.3.4
        // date
        //  : date_value
        //  ;

        String source = "19970714";

        ICalendarParser.DateContext ctx = getParser(source).date();

        assertThat( ctx.date_value().getText(), is(source) );
    }

    @Test
    public void date_timeTest() {

        // 3.3.5
        // date_time
        //  : date K_T time
        //  ;

        String date = "19980118";
        String time = "230000";
        String source = date + "t" + time;

        ICalendarParser.Date_timeContext ctx = getParser(source).date_time();

        assertThat( ctx.date().getText(), is(date) );
        assertThat( ctx.time().getText(), is(time) );
    }

    @Test
    public void dur_valueTest() {

        // 3.3.6
        // dur_value
        //  : '-' (K_P | NON_KEYWORD) (dur_date | dur_time | dur_week)
        //  | '+'? (K_P  | NON_KEYWORD) (dur_date | dur_time | dur_week)
        //  ;

        String date = "15DT5H0M20S";
        String source = "+P" + date;

        ICalendarParser.Dur_valueContext ctx = getParser(source).dur_value();

        assertThat( ctx.dur_date().getText(), is(date) );

        String weeks = "7w";
        source = "P" + weeks;

        ctx = getParser(source).dur_value();

        assertThat( ctx.dur_week().getText(), is(weeks) );
    }

    @Test
    public void float_numTest() {

        // 3.3.7
        // float_num
        //  : '-' digits ('.' digits)?
        //  | '+'? digits ('.' digits)?
        //  ;

        String source = "123.45";

        ICalendarParser.Float_numContext ctx = getParser(source).float_num();

        assertThat( ctx.digits().get(0).getText(), is("123") );
        assertThat( ctx.digits().get(1).getText(), is("45") );

        source = "-42";

        ctx = getParser(source).float_num();

        assertThat( ctx.digits().get(0).getText(), is("42") );
    }

    @Test
    public void integerTest() {

        // 3.3.8
        // integer
        //  : '-' digits
        //  | '+'? digits
        //  ;

        String source = "+123";

        ICalendarParser.IntegerContext ctx = getParser(source).integer();

        assertThat( ctx.digits().getText(), is("123") );
    }

    @Test
    public void periodTest() {

        // 3.3.9
        // period
        //  : period_explicit
        //  | period_start
        //  ;

        String source = "19970101T180000Z/19970102T070000Z";

        ICalendarParser.PeriodContext ctx = getParser(source).period();

        assertThat( ctx.period_explicit().getText(), is(source) );

        source = "19970101T180000Z/PT5H30M";

        ctx = getParser(source).period();

        assertThat( ctx.period_start().getText(), is(source) );
    }

    @Test
    public void recurTest() {

        // 3.3.10
        // recur
        //  : recur_rule_part (';' recur_rule_part)*
        //  ;

        String value1 = "FREQ=DAILY";
        String value2 = "BYDAY=-42WE";
        String source = value1 + ";" + value2;

        ICalendarParser.RecurContext ctx = getParser(source).recur();

        assertThat( ctx.recur_rule_part().size(), is(2) );

        assertThat( ctx.recur_rule_part().get(0).getText(), is(value1) );
        assertThat( ctx.recur_rule_part().get(1).getText(), is(value2) );
    }

    @Test
    public void textTest() {

        // 3.3.11
        // text
        //  : (tsafe_char | ':' | '"' | escaped_char)*
        //  ;

        String source = "Project XYZ Final Review\\nConference Room - 3B\\nCome Prepared.";

        ICalendarParser.TextContext ctx = getParser(source).text();

        assertThat( ctx.getText(), is(source) );
    }

    @Test
    public void timeTest() {

        // 3.3.12
        // time
        //  : time_hour time_minute time_second K_Z?
        //  ;

        String hour = "12";
        String minute = "34";
        String second = "56";
        String source = hour + minute + second;

        ICalendarParser.TimeContext ctx = getParser(source).time();

        assertThat( ctx.time_hour().getText(), is(hour) );
        assertThat( ctx.time_minute().getText(), is(minute) );
        assertThat( ctx.time_second().getText(), is(second) );

        hour = "23";
        minute = "00";
        second = "09";
        source = hour + minute + second + "Z";

        ctx = getParser(source).time();

        assertThat( ctx.time_hour().getText(), is(hour) );
        assertThat( ctx.time_minute().getText(), is(minute) );
        assertThat( ctx.time_second().getText(), is(second) );
    }

    @Test
    public void uriTest() {

        // 3.3.13 - As defined in Section 3 of [RFC3986].
        // uri
        //  : qsafe_char+
        //  ;

        String source = "http://example.com/my-report.txt";

        ICalendarParser.UriContext ctx = getParser(source).uri();

        assertThat( ctx.getText(), is(source) );
    }

    @Test
    public void utc_offsetTest() {

        // 3.3.14
        // utc_offset
        //  : time_numzone
        //  ;

        String hour = "12";
        String minute = "34";
        String second = "";
        String source = "+" + hour + minute + second;

        ICalendarParser.Utc_offsetContext ctx = getParser(source).utc_offset();

        assertThat( ctx.getText().startsWith("+"), is(true) );
        assertThat( ctx.time_numzone().time_hour().getText(), is(hour) );
        assertThat( ctx.time_numzone().time_minute().getText(), is(minute) );

        hour = "23";
        minute = "00";
        second = "09";
        source = "-" + hour + minute + second;

        ctx = getParser(source).utc_offset();

        assertThat( ctx.getText().startsWith("-"), is(true) );
        assertThat( ctx.time_numzone().time_hour().getText(), is(hour) );
        assertThat( ctx.time_numzone().time_minute().getText(), is(minute) );
        assertThat( ctx.time_numzone().time_second().getText(), is(second) );
    }

    @Test
    public void eventcTest() {

        // 3.6.1 - Event Component
        // eventc
        //  : BEGIN ':' VEVENT CRLF
        //    eventprop*
        //    alarmc*
        //    END ':' VEVENT CRLF
        //  ;

        String source = "BEGIN:VEVENT\n" +
                "UID:19970901T130000Z-123401@example.com\n" +
                "DTSTAMP:19970901T130000Z\n" +
                "DTSTART:19970903T163000Z\n" +
                "DTEND:19970903T190000Z\n" +
                "SUMMARY:Annual Employee Review\n" +
                "CLASS:PRIVATE\n" +
                "CATEGORIES:BUSINESS,HUMAN RESOURCES\n" +
                "END:VEVENT\n";

        ICalendarParser.EventcContext ctx = getParser(source).eventc();
        assertThat( ctx.eventprop().size(), is(7) );

        source = "BEGIN:VEVENT\n" +
                "UID:19970901T130000Z-123402@example.com\n" +
                "DTSTAMP:19970901T130000Z\n" +
                "DTSTART:19970401T163000Z\n" +
                "DTEND:19970402T010000Z\n" +
                "SUMMARY:Laurel is in sensitivity awareness class.\n" +
                "CLASS:PUBLIC\n" +
                "CATEGORIES:BUSINESS,HUMAN RESOURCES\n" +
                "TRANSP:TRANSPARENT\n" +
                "END:VEVENT\n";

        ctx = getParser(source).eventc();
        assertThat( ctx.eventprop().size(), is(8) );

        source = "BEGIN:VEVENT\n" +
                "UID:19970901T130000Z-123403@example.com\n" +
                "DTSTAMP:19970901T130000Z\n" +
                "DTSTART;VALUE=DATE:19971102\n" +
                "SUMMARY:Our Blissful Anniversary\n" +
                "TRANSP:TRANSPARENT\n" +
                "CLASS:CONFIDENTIAL\n" +
                "CATEGORIES:ANNIVERSARY,PERSONAL,SPECIAL OCCASION\n" +
                "RRULE:FREQ=YEARLY\n" +
                "END:VEVENT\n";

        ctx = getParser(source).eventc();
        assertThat( ctx.eventprop().size(), is(8) );

        source = "BEGIN:VEVENT\n" +
                "UID:20070423T123432Z-541111@example.com\n" +
                "DTSTAMP:20070423T123432Z\n" +
                "DTSTART;VALUE=DATE:20070628\n" +
                "DTEND;VALUE=DATE:20070709\n" +
                "SUMMARY:Festival International de Jazz de Montreal\n" +
                "TRANSP:TRANSPARENT\n" +
                "END:VEVENT\n";

        ctx = getParser(source).eventc();
        assertThat( ctx.eventprop().size(), is(6) );
    }

    @Test
    public void todocTest() {

        // 3.6.2 - To-Do Component
        // todoc
        //  : BEGIN ':' VTODO CRLF
        //    todoprop*
        //    alarmc*
        //    END ':' VTODO CRLF
        //  ;

        String source = "BEGIN:VTODO\n" +
                "UID:20070313T123432Z-456553@example.com\n" +
                "DTSTAMP:20070313T123432Z\n" +
                "DUE;VALUE=DATE:20070501\n" +
                "SUMMARY:Submit Quebec Income Tax Return for 2006\n" +
                "CLASS:CONFIDENTIAL\n" +
                "CATEGORIES:FAMILY,FINANCE\n" +
                "STATUS:NEEDS-ACTION\n" +
                "END:VTODO\n";

        ICalendarParser.TodocContext ctx = getParser(source).todoc();
        assertThat( ctx.todoprop().size(), is(7) );

        source = "BEGIN:VTODO\n" +
                "UID:20070514T103211Z-123404@example.com\n" +
                "DTSTAMP:20070514T103211Z\n" +
                "DTSTART:20070514T110000Z\n" +
                "DUE:20070709T130000Z\n" +
                "COMPLETED:20070707T100000Z\n" +
                "SUMMARY:Submit Revised Internet-Draft\n" +
                "PRIORITY:1\n" +
                "STATUS:NEEDS-ACTION\n" +
                "END:VTODO\n";

        ctx = getParser(source).todoc();
        assertThat( ctx.todoprop().size(), is(8) );
    }

    @Test
    public void journalcTest() {

        // 3.6.3 - Journal Component
        // journalc
        //  : BEGIN ':' VJOURNAL CRLF
        //    jourprop*
        //    END ':' VJOURNAL CRLF
        //  ;

        String source = "BEGIN:VJOURNAL\n" +
                "UID:19970901T130000Z-123405@example.com\n" +
                "DTSTAMP:19970901T130000Z\n" +
                "DTSTART;VALUE=DATE:19970317\n" +
                "SUMMARY:Staff meeting minutes\n" +
                "DESCRIPTION:1. Staff meeting: Participants include Joe\\,\n" +
                "  Lisa\\, and Bob. Aurora project plans were reviewed.\n" +
                "  There is currently no budget reserves for this project.\n" +
                "  Lisa will escalate to management. Next meeting on Tuesday.\\n\n" +
                " 2. Telephone Conference: ABC Corp. sales representative\n" +
                "  called to discuss new printer. Promised to get us a demo by\n" +
                "  Friday.\\n3. Henry Miller (Handsoff Insurance): Car was\n" +
                "  totaled by tree. Is looking into a loaner car. 555-2323\n" +
                "  (tel).\n" +
                "END:VJOURNAL\n";

        ICalendarParser.JournalcContext ctx = getParser(source).journalc();
        assertThat( ctx.jourprop().size(), is(5) );
    }

    @Test
    public void freebusycTest() {

        // 3.6.4 - Free/Busy Component
        // freebusyc
        //  : BEGIN ':' VFREEBUSY CRLF
        //    fbprop*
        //    END ':' VFREEBUSY CRLF
        //  ;

        String source = "BEGIN:VFREEBUSY\n" +
                "UID:19970901T082949Z-FA43EF@example.com\n" +
                "ORGANIZER:mailto:jane_doe@example.com\n" +
                "ATTENDEE:mailto:john_public@example.com\n" +
                "DTSTART:19971015T050000Z\n" +
                "DTEND:19971016T050000Z\n" +
                "DTSTAMP:19970901T083000Z\n" +
                "END:VFREEBUSY\n";

        ICalendarParser.FreebusycContext ctx = getParser(source).freebusyc();
        assertThat( ctx.fbprop().size(), is(6) );

        source = "BEGIN:VFREEBUSY\n" +
                "UID:19970901T095957Z-76A912@example.com\n" +
                "ORGANIZER:mailto:jane_doe@example.com\n" +
                "ATTENDEE:mailto:john_public@example.com\n" +
                "DTSTAMP:19970901T100000Z\n" +
                "FREEBUSY:19971015T050000Z/PT8H30M,\n" +
                " 19971015T160000Z/PT5H30M,19971015T223000Z/PT6H30M\n" +
                "URL:http://example.com/pub/busy/jpublic-01.ifb\n" +
                "COMMENT:This iCalendar file contains busy time information for\n" +
                "  the next three months.\n" +
                "END:VFREEBUSY\n";

        ctx = getParser(source).freebusyc();
        assertThat( ctx.fbprop().size(), is(7) );

        source = "BEGIN:VFREEBUSY\n" +
                "UID:19970901T115957Z-76A912@example.com\n" +
                "DTSTAMP:19970901T120000Z\n" +
                "ORGANIZER:jsmith@example.com\n" +
                "DTSTART:19980313T141711Z\n" +
                "DTEND:19980410T141711Z\n" +
                "FREEBUSY:19980314T233000Z/19980315T003000Z\n" +
                "FREEBUSY:19980316T153000Z/19980316T163000Z\n" +
                "FREEBUSY:19980318T030000Z/19980318T040000Z\n" +
                "URL:http://www.example.com/calendar/busytime/jsmith.ifb\n" +
                "END:VFREEBUSY\n";

        ctx = getParser(source).freebusyc();
        assertThat( ctx.fbprop().size(), is(9) );
    }

    @Test
    public void timezonecTest() {

        // 3.6.5 - Time Zone Component
        // timezonec
        //  : BEGIN ':' VTIMEZONE CRLF
        //    timezoneprop*
        //    END ':' VTIMEZONE CRLF
        //  ;

        String source = "BEGIN:VTIMEZONE\n" +
                "TZID:America/New_York\n" + // property 1
                "LAST-MODIFIED:20050809T050000Z\n" + // property 2
                "BEGIN:DAYLIGHT\n" + // property 3
                "DTSTART:19670430T020000\n" +
                "RRULE:FREQ=YEARLY;BYMONTH=4;BYDAY=-1SU;UNTIL=19730429T070000Z\n" +
                "TZOFFSETFROM:-0500\n" +
                "TZOFFSETTO:-0400\n" +
                "TZNAME:EDT\n" +
                "END:DAYLIGHT\n" +
                "BEGIN:STANDARD\n" + // property 4
                "DTSTART:19671029T020000\n" +
                "RRULE:FREQ=YEARLY;BYMONTH=10;BYDAY=-1SU;UNTIL=20061029T060000Z\n" +
                "TZOFFSETFROM:-0400\n" +
                "TZOFFSETTO:-0500\n" +
                "TZNAME:EST\n" +
                "END:STANDARD\n" +
                "BEGIN:DAYLIGHT\n" + // property 5
                "DTSTART:19740106T020000\n" +
                "RDATE:19750223T020000\n" +
                "TZOFFSETFROM:-0500\n" +
                "TZOFFSETTO:-0400\n" +
                "TZNAME:EDT\n" +
                "END:DAYLIGHT\n" +
                "BEGIN:DAYLIGHT\n" + // property 6
                "DTSTART:19760425T020000\n" +
                "RRULE:FREQ=YEARLY;BYMONTH=4;BYDAY=-1SU;UNTIL=19860427T070000Z\n" +
                "TZOFFSETFROM:-0500\n" +
                "TZOFFSETTO:-0400\n" +
                "TZNAME:EDT\n" +
                "END:DAYLIGHT\n" +
                "BEGIN:DAYLIGHT\n" + // property 7
                "DTSTART:19870405T020000\n" +
                "RRULE:FREQ=YEARLY;BYMONTH=4;BYDAY=1SU;UNTIL=20060402T070000Z\n" +
                "TZOFFSETFROM:-0500\n" +
                "TZOFFSETTO:-0400\n" +
                "TZNAME:EDT\n" +
                "END:DAYLIGHT\n" +
                "BEGIN:DAYLIGHT\n" + // property 8
                "DTSTART:20070311T020000\n" +
                "RRULE:FREQ=YEARLY;BYMONTH=3;BYDAY=2SU\n" +
                "TZOFFSETFROM:-0500\n" +
                "TZOFFSETTO:-0400\n" +
                "TZNAME:EDT\n" +
                "END:DAYLIGHT\n" +
                "BEGIN:STANDARD\n" + // property 9
                "DTSTART:20071104T020000\n" +
                "RRULE:FREQ=YEARLY;BYMONTH=11;BYDAY=1SU\n" +
                "TZOFFSETFROM:-0400\n" +
                "TZOFFSETTO:-0500\n" +
                "TZNAME:EST\n" +
                "END:STANDARD\n" +
                "END:VTIMEZONE\n";

        ICalendarParser.TimezonecContext ctx = getParser(source).timezonec();
        assertThat( ctx.timezoneprop().size(), is(9) );

        source = "BEGIN:VTIMEZONE\n" +
                "TZID:America/New_York\n" + // property 1
                "LAST-MODIFIED:20050809T050000Z\n" + // property 2
                "BEGIN:STANDARD\n" + // property 3
                "DTSTART:20071104T020000\n" +
                "TZOFFSETFROM:-0400\n" +
                "TZOFFSETTO:-0500\n" +
                "TZNAME:EST\n" +
                "END:STANDARD\n" +
                "BEGIN:DAYLIGHT\n" + // property 4
                "DTSTART:20070311T020000\n" +
                "TZOFFSETFROM:-0500\n" +
                "TZOFFSETTO:-0400\n" +
                "TZNAME:EDT\n" +
                "END:DAYLIGHT\n" +
                "END:VTIMEZONE\n";

        ctx = getParser(source).timezonec();
        assertThat( ctx.timezoneprop().size(), is(4) );

        source = "BEGIN:VTIMEZONE\n" +
                "TZID:America/New_York\n" + // property 1
                "LAST-MODIFIED:20050809T050000Z\n" + // property 2
                "TZURL:http://zones.example.com/tz/America-New_York.ics\n" + // property 3
                "BEGIN:STANDARD\n" + // property 4
                "DTSTART:20071104T020000\n" +
                "RRULE:FREQ=YEARLY;BYMONTH=11;BYDAY=1SU\n" +
                "TZOFFSETFROM:-0400\n" +
                "TZOFFSETTO:-0500\n" +
                "TZNAME:EST\n" +
                "END:STANDARD\n" +
                "BEGIN:DAYLIGHT\n" + // property 5
                "DTSTART:20070311T020000\n" +
                "RRULE:FREQ=YEARLY;BYMONTH=3;BYDAY=2SU\n" +
                "TZOFFSETFROM:-0500\n" +
                "TZOFFSETTO:-0400\n" +
                "TZNAME:EDT\n" +
                "END:DAYLIGHT\n" +
                "END:VTIMEZONE\n";

        ctx = getParser(source).timezonec();
        assertThat( ctx.timezoneprop().size(), is(5) );

        source = "BEGIN:VTIMEZONE\n" +
                "TZID:Fictitious\n" + // property 1
                "LAST-MODIFIED:19870101T000000Z\n" + // property 2
                "BEGIN:STANDARD\n" + // property 3
                "DTSTART:19671029T020000\n" +
                "RRULE:FREQ=YEARLY;BYDAY=-1SU;BYMONTH=10\n" +
                "TZOFFSETFROM:-0400\n" +
                "TZOFFSETTO:-0500\n" +
                "TZNAME:EST\n" +
                "END:STANDARD\n" +
                "BEGIN:DAYLIGHT\n" + // property 4
                "DTSTART:19870405T020000\n" +
                "RRULE:FREQ=YEARLY;BYDAY=1SU;BYMONTH=4;UNTIL=19980404T070000Z\n" +
                "TZOFFSETFROM:-0500\n" +
                "TZOFFSETTO:-0400\n" +
                "TZNAME:EDT\n" +
                "END:DAYLIGHT\n" +
                "END:VTIMEZONE\n";

        ctx = getParser(source).timezonec();
        assertThat( ctx.timezoneprop().size(), is(4) );

        source = "BEGIN:VTIMEZONE\n" +
                "TZID:Fictitious\n" + // property 1
                "LAST-MODIFIED:19870101T000000Z\n" + // property 2
                "BEGIN:STANDARD\n" + // property 3
                "DTSTART:19671029T020000\n" +
                "RRULE:FREQ=YEARLY;BYDAY=-1SU;BYMONTH=10\n" +
                "TZOFFSETFROM:-0400\n" +
                "TZOFFSETTO:-0500\n" +
                "TZNAME:EST\n" +
                "END:STANDARD\n" +
                "BEGIN:DAYLIGHT\n" + // property 4
                "DTSTART:19870405T020000\n" +
                "RRULE:FREQ=YEARLY;BYDAY=1SU;BYMONTH=4;UNTIL=19980404T070000Z\n" +
                "TZOFFSETFROM:-0500\n" +
                "TZOFFSETTO:-0400\n" +
                "TZNAME:EDT\n" +
                "END:DAYLIGHT\n" +
                "BEGIN:DAYLIGHT\n" + // property 5
                "DTSTART:19990424T020000\n" +
                "RRULE:FREQ=YEARLY;BYDAY=-1SU;BYMONTH=4\n" +
                "TZOFFSETFROM:-0500\n" +
                "TZOFFSETTO:-0400\n" +
                "TZNAME:EDT\n" +
                "END:DAYLIGHT\n" +
                "END:VTIMEZONE\n";

        ctx = getParser(source).timezonec();
        assertThat( ctx.timezoneprop().size(), is(5) );
    }

    @Test
    public void alarmcTest() {

        // 3.6.6 - Alarm Component
        // alarmc
        //  : BEGIN ':' VALARM CRLF
        //    alarmprop?
        //    END ':' VALARM CRLF
        //  ;

        String source = "BEGIN:VALARM\n" +
                "TRIGGER;VALUE=DATE-TIME:19970317T133000Z\n" +
                "REPEAT:4\n" +
                "DURATION:PT15M\n" +
                "ACTION:AUDIO\n" +
                "ATTACH;FMTTYPE=audio/basic:ftp://example.com/pub/\n" +
                " sounds/bell-01.aud\n" +
                "END:VALARM\n";

        ICalendarParser.AlarmcContext ctx = getParser(source).alarmc();
        assertThat( ctx.alarmprop().size(), is(5) );

        source = "BEGIN:VALARM\n" +
                "TRIGGER:-PT30M\n" +
                "REPEAT:2\n" +
                "DURATION:PT15M\n" +
                "ACTION:DISPLAY\n" +
                "DESCRIPTION:Breakfast meeting with executive\\n\n" +
                " team at 8:30 AM EST.\n" +
                "END:VALARM\n";

        ctx = getParser(source).alarmc();
        assertThat( ctx.alarmprop().size(), is(5) );

        source = "BEGIN:VALARM\n" +
                "TRIGGER;RELATED=END:-P2D\n" +
                "ACTION:EMAIL\n" +
                "ATTENDEE:mailto:john_doe@example.com\n" +
                "SUMMARY:*** REMINDER: SEND AGENDA FOR WEEKLY STAFF MEETING ***\n" +
                "DESCRIPTION:A draft agenda needs to be sent out to the attendees\n" +
                "  to the weekly managers meeting (MGR-LIST). Attached is a\n" +
                "  pointer the document template for the agenda file.\n" +
                "ATTACH;FMTTYPE=application/msword:http://example.com/\n" +
                " templates/agenda.doc\n" +
                "END:VALARM\n";

        ctx = getParser(source).alarmc();
        assertThat( ctx.alarmprop().size(), is(6) );
    }

    @Test
    public void calscaleTest() {

        // 3.7.1 - Calendar Scale
        // calscale
        //  : CALSCALE (';' other_param)* ':' GREGORIAN CRLF
        //  ;

        String source = "CALSCALE:GREGORIAN\n";

        ICalendarParser.CalscaleContext ctx = getParser(source).calscale();
        assertThat(ctx.CALSCALE().getText(), is("CALSCALE"));
        assertThat( ctx.GREGORIAN().getText(), is("GREGORIAN") );
    }

    @Test
    public void methodTest() {

        // 3.7.2 - Method
        // method
        //  : METHOD (';' other_param)* ':' iana_token CRLF
        //  ;

        String source = "METHOD:REQUEST\n";

        ICalendarParser.MethodContext ctx = getParser(source).method();
        assertThat( ctx.METHOD().getText(), is("METHOD") );
        assertThat( ctx.iana_token().getText(), is("REQUEST") );
    }

    @Test
    public void prodidTest() {

        // 3.7.3 - Product Identifier
        // prodid
        //  : PRODID (';' other_param)* ':' text CRLF
        //  ;

        String source = "PRODID:-//ABC Corporation//NONSGML My Product//EN\n";

        ICalendarParser.ProdidContext ctx = getParser(source).prodid();
        assertThat( ctx.PRODID().getText(), is("PRODID") );
        assertThat( ctx.text().getText(), is("-//ABC Corporation//NONSGML My Product//EN") );
    }

    @Test
    public void versionTest() {

        // 3.7.4 - Version
        // version
        //  : VERSION (';' other_param)* ':' vervalue CRLF
        //  ;

        String source = "VERSION:2.0\n";

        ICalendarParser.VersionContext ctx = getParser(source).version();
        assertThat( ctx.VERSION().getText(), is("VERSION") );
        assertThat( ctx.vervalue().maxver().getText(), is("2.0") );

        source = "VERSION:1.1;2.2\n";

        ctx = getParser(source).version();
        assertThat( ctx.VERSION().getText(), is("VERSION") );
        assertThat( ctx.vervalue().minver().getText(), is("1.1") );
        assertThat( ctx.vervalue().maxver().getText(), is("2.2") );
    }

    @Test
    public void attachTest() {

        // 3.8.1.1 - Attachment
        // attach
        //  : ATTACH attachparam* ( ':' uri
        //                        | ';' ENCODING '=' BASE '6' '4' ';' VALUE '=' BINARY ':' binary
        //                        )
        //    CRLF
        //  ;

        String source = "ATTACH:CID:jsmith.part3.960817T083000.xyzMail@example.com\n";

        ICalendarParser.AttachContext ctx = getParser(source).attach();
        assertThat( ctx.ATTACH().getText(), is("ATTACH") );
        assertThat( ctx.uri().getText(), is("CID:jsmith.part3.960817T083000.xyzMail@example.com") );

        source = "ATTACH;FMTTYPE=application/postscript:ftp://example.com/pub/\n" +
                " reports/r-960812.ps\n";

        ctx = getParser(source).attach();
        assertThat( ctx.ATTACH().getText(), is("ATTACH") );
        assertThat( ctx.attachparam().size(), is(1) );
        assertThat( ctx.attachparam(0).fmttypeparam().getText(), is("FMTTYPE=application/postscript") );
        assertThat( ctx.uri().getText(), is("ftp://example.com/pub/reports/r-960812.ps") );
    }

    @Test
    public void categoriesTest() {

        // 3.8.1.2 - Categories
        // categories
        //  : CATEGORIES catparam* ':' text (',' text)* CRLF
        //  ;

        String source = "CATEGORIES:MEETING\n";

        ICalendarParser.CategoriesContext ctx = getParser(source).categories();
        assertThat( ctx.CATEGORIES().getText(), is("CATEGORIES") );
        assertThat( ctx.text().size(), is(1) );
        assertThat( ctx.text(0).getText(), is("MEETING") );

        source = "CATEGORIES:APPOINTMENT,EDUCATION\n";

        ctx = getParser(source).categories();
        assertThat( ctx.CATEGORIES().getText(), is("CATEGORIES") );
        assertThat( ctx.text().size(), is(2) );
        assertThat( ctx.text(0).getText(), is("APPOINTMENT") );
        assertThat( ctx.text(1).getText(), is("EDUCATION") );
    }

    @Test
    public void clazzTest() {

        // 3.8.1.3 - Classification
        // clazz
        //  : CLASS (';' other_param)* ':' classvalue CRLF
        //  ;

        String source = "CLASS:PUBLIC\n";

        ICalendarParser.ClazzContext ctx = getParser(source).clazz();
        assertThat( ctx.CLASS().getText(), is("CLASS") );
        assertThat( ctx.classvalue().getText(), is("PUBLIC") );
    }

    @Test
    public void commentTest() {

        // 3.8.1.4 - Comment
        // comment
        //  : COMMENT commparam* ':' text CRLF
        //  ;

        String comment = "The meeting really needs to include both ourselves\n" +
                " and the customer. We can't hold this meeting without them.\n" +
                " As a matter of fact\\, the venue for the meeting ought to be at\n" +
                " their site. - - John";

        String source = "COMMENT:" + comment + "\n";

        ICalendarParser.CommentContext ctx = getParser(source).comment();
        assertThat( ctx.COMMENT().getText(), is("COMMENT") );
        assertThat( ctx.text().getText(), is(comment.replace("\n ", "")) );
    }

    @Test
    public void descriptionTest() {

        // 3.8.1.5 - Description
        // description
        //  : DESCRIPTION descparam* ':' text CRLF
        //  ;

        String description = "Meeting to provide technical review for \"Phoenix\"\n" +
                " design.\\nHappy Face Conference Room. Phoenix design team\n" +
                " MUST attend this meeting.\\nRSVP to team leader.";

        String source = "DESCRIPTION:" + description + "\n";

        ICalendarParser.DescriptionContext ctx = getParser(source).description();

        assertThat( ctx.DESCRIPTION().getText(), is("DESCRIPTION") );
        assertThat( ctx.text().getText(), is(description.replace("\n ", "")) );
    }

    @Test
    public void geoTest() {

        // 3.8.1.6 - Geographic Position
        // geo
        //  : GEO (';' other_param)* ':' geovalue CRLF
        //  ;

        String source = "GEO:37.386013;-122.082932\n";

        ICalendarParser.GeoContext ctx = getParser(source).geo();

        assertThat( ctx.GEO().getText(), is("GEO") );

        assertThat( ctx.geovalue().float_num().size(), is(2) );

        assertThat( ctx.geovalue().float_num(0).getText(), is("37.386013") );
        assertThat( ctx.geovalue().float_num(1).getText(), is("-122.082932") );
    }

    @Test
    public void locationTest() {

        // 3.8.1.7 - Location
        // location
        //  : LOCATION locparam* ':' text CRLF
        //  ;

        String source = "LOCATION:Conference Room - F123\\, Bldg. 002\n";

        ICalendarParser.LocationContext ctx = getParser(source).location();

        assertThat( ctx.LOCATION().getText(), is("LOCATION") );
        assertThat( ctx.text().getText(), is("Conference Room - F123\\, Bldg. 002") );

        source = "LOCATION;ALTREP=\"http://xyzcorp.com/conf-rooms/f123.vcf\":\n" +
                " Conference Room - F123\\, Bldg. 002\n";

        ctx = getParser(source).location();

        assertThat( ctx.LOCATION().getText(), is("LOCATION") );
        assertThat( ctx.locparam().size(), is(1) );
        assertThat( ctx.locparam(0).altrepparam().uri().getText(), is("http://xyzcorp.com/conf-rooms/f123.vcf") );
        assertThat( ctx.text().getText(), is("Conference Room - F123\\, Bldg. 002") );
    }

    @Test
    public void percentTest() {

        // 3.8.1.8 - Percent Complete
        // percent
        //  : PERCENT_COMPLETE (';' other_param)* ':' integer CRLF
        //  ;

        String source = "PERCENT-COMPLETE:39\n";

        ICalendarParser.PercentContext ctx = getParser(source).percent();

        // TODO
    }

    @Test
    public void priorityTest() {

        // 3.8.1.9 - Priority
        // priority
        //  : PRIORITY (';' other_param)* ':' priovalue CRLF
        //  ;

        String source = "PRIORITY:1\n";

        ICalendarParser.PriorityContext ctx = getParser(source).priority();

        // TODO
    }

    @Test
    public void resourcesTest() {

        // 3.8.1.10 - Resources
        // resources
        //  : RESOURCES resrcparam* ':' text (',' text)* CRLF
        //  ;

        String source = "RESOURCES:EASEL,PROJECTOR,VCR\n";

        ICalendarParser.ResourcesContext ctx = getParser(source).resources();

        source = "RESOURCES;LANGUAGE=fr:Nettoyeur haute pression\n";

        // TODO
    }

    @Test
    public void statusTest() {

        // 3.8.1.11 - Status
        // status
        //  : STATUS (';' other_param)* ':' statvalue CRLF
        //  ;

        String source = "";

        ICalendarParser.StatusContext ctx = getParser(source).status();

        // TODO
    }

    @Test
    public void summaryTest() {

        // 3.8.1.12 - Summary
        // summary
        //  : SUMMARY summparam* ':' text CRLF
        //  ;

        String source = "";

        ICalendarParser.SummaryContext ctx = getParser(source).summary();

        // TODO
    }

    /*
    @Test
    public void test() {
        for(Token t : new ICalendarLexer(new ANTLRInputStream("19970101T180000Z/PT5H30M")).getAllTokens()) {
            System.out.println(t);
        }
    }
    */
}

