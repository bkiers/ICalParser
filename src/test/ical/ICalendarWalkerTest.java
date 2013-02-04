package ical;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ICalendarWalkerTest {

    @Test
    public void test() {

        assertThat(1 + 1, is(2));

        String s = "icalobject\n" +
                "VEVENT\n" +
                "VTODO\n" +
                "VJOURNAL\n" +
                "VFREEBUSY\n" +
                "VTIMEZONE\n" +
                "VALARM\n" +
                "STANDARD\n" +
                "DAYLIGHT\n" +
                "CALSCALE\n" +
                "METHOD\n" +
                "PRODID\n" +
                "VERSION\n" +
                "ATTACH\n" +
                "CATEGORIES\n" +
                "CLASS\n" +
                "COMMENT\n" +
                "DESCRIPTION\n" +
                "GEO\n" +
                "LOCATION\n" +
                "PERCENT-COMPLETE\n" +
                "PRIORITY\n" +
                "RESOURCES\n" +
                "STATUS\n" +
                "SUMMARY\n" +
                "COMPLETED\n" +
                "DTEND\n" +
                "DUE\n" +
                "DTSTART\n" +
                "DURATION\n" +
                "FREEBUSY\n" +
                "TRANSP\n" +
                "TZID\n" +
                "TZNAME\n" +
                "TZOFFSETFROM\n" +
                "TZOFFSETTO\n" +
                "TZURL\n" +
                "ATTENDEE\n" +
                "CONTACT\n" +
                "ORGANIZER\n" +
                "RECURRENCE-ID\n" +
                "RELATED-TO\n" +
                "URL\n" +
                "UID\n" +
                "EXDATE\n" +
                "EXRULE\n" +
                "RDATE\n" +
                "RRULE\n" +
                "ACTION\n" +
                "REPEAT\n" +
                "TRIGGER\n" +
                "CREATED\n" +
                "DTSTAMP\n" +
                "LAST-MODIFIED\n" +
                "SEQUENCE\n" +
                "REQUEST-STATUS\n" +
                "ALTREP\n" +
                "CN\n" +
                "CUTYPE\n" +
                "DELEGATED-FROM\n" +
                "DELEGATED-TO\n" +
                "DIR\n" +
                "ENCODING\n" +
                "FMTTYPE\n" +
                "FBTYPE\n" +
                "LANGUAGE\n" +
                "MEMBER\n" +
                "PARTSTAT\n" +
                "RANGE\n" +
                "RELATED\n" +
                "RELTYPE\n" +
                "ROLE\n" +
                "RSVP\n" +
                "SENT-BY\n" +
                "TZID\n" +
                "VALUE\n";

        for(String w : s.trim().split("\n")) {
            String fixed = w.toLowerCase().replace("-", "_");

            fixed = Character.toUpperCase(fixed.charAt(0)) + fixed.substring(1);

            System.out.println(
                    "    @Override\n" +
                    "    public void enter" + fixed + "(ICalendarParser." + fixed + "Context ctx) {\n" +
                    "        // TODO\n" +
                    "    }\n"
            );
        }
    }
}
