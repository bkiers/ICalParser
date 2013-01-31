package horai;

import horai.parser.*;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Horai {

    public static void main(String[] args) throws Exception {

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

        System.out.println(source);

        ///*
        FileInputStream input = new FileInputStream("src/ics/test.ics");
        ICalendarLexer lexer = new ICalendarLexer(new ANTLRInputStream(source));
        ICalendarParser parser = new ICalendarParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.parse();

        //System.out.println("tree=" + tree);


        ParseTreeWalker walker = new ParseTreeWalker();
        ICalendarWalker calendarWalker = new ICalendarWalker();
        walker.walk(calendarWalker, tree);
        //*/
    }
}
