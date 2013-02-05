package ical;

import ical.parser.ICalendarBaseListener;
import ical.parser.ICalendarLexer;
import ical.parser.ICalendarParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;

public class TodoDemo {

    public static void main(String[] args) throws Exception {

        FileInputStream fis = new FileInputStream("src/ics/test.ics");
        ICalendarLexer lexer = new ICalendarLexer(new ANTLRInputStream(fis));
        ICalendarParser parser = new ICalendarParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.parse();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new TodoListener(), tree);
    }
}

class TodoListener extends ICalendarBaseListener {

    /*
     * The production of a `todoc` in the ANTLR grammar looks like this:
     *
     *   // 3.6.2 - To-Do Component
     *   todoc
     *    : k_begin COL k_vtodo CRLF
     *      todoprop*?
     *      alarmc*?
     *      k_end COL k_vtodo CRLF
     *    ;
     */
    @Override
    public void enterTodoc(ICalendarParser.TodocContext ctx) {

        // the first property is DTSTAMP
        String dtstamp = ctx.todoprop(0).dtstamp().date_time().getText();

        // the 4th property is ORGANIZER
        String organizer = ctx.todoprop(3).organizer().cal_address().getText();

        System.out.println("dtstamp   -> " + dtstamp);
        System.out.println("organizer -> " + organizer);
    }
}