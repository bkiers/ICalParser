package ical;

import ical.parser.*;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Main {

    public static void main(String[] args) throws Exception {

        String source = "BEGIN:VCALENDAR\n" +
                "VERSION:2.0\n" +
                "PRODID:-//ABC Corporation//NONSGML My Product//EN\n" +
                "BEGIN:VTODO\n" +
                "DTSTAMP:19980130T134500Z\n" +
                "SEQUENCE:2\n" +
                "UID:uid4@example.com\n" +
                "ORGANIZER:mailto:unclesam@example.com\n" +
                "ATTENDEE;PARTSTAT=ACCEPTED:mailto:jqpublic@example.com\n" +
                "DUE:19980415T000000\n" +
                "STATUS:NEEDS-ACTION\n" +
                "SUMMARY:Submit Income Taxes\n" +
                "BEGIN:VALARM\n" +
                "ACTION:AUDIO\n" +
                "TRIGGER:19980403T120000Z\n" +
                "ATTACH;FMTTYPE=audio/basic:http://example.com/pub/audio-\n" +
                " files/ssbanner.aud\n" +
                "REPEAT:4\n" +
                "DURATION:PT1H\n" +
                "END:VALARM\n" +
                "END:VTODO\n" +
                "END:VCALENDAR\n";

        ICalendarLexer lexer = new ICalendarLexer(new ANTLRInputStream(source));
        ICalendarParser parser = new ICalendarParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.parse();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new ICalendarWalker(), tree);
    }
}
