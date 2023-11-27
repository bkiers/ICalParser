package ical;

import ical.parser.ICalendarLexer;
import ical.parser.ICalendarParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;

public class Main {

    public static void main(String[] args) throws Exception {

        String fileName;

        if(args.length == 0) {
            fileName = "src/ics/test.ics";
            System.err.println("Didn't provide a file to parse, " +
                    "falling back to default file: " + fileName);
        }
        else {
            fileName = args[0];
            System.out.println("Parsing file: " + fileName);
        }

        ICalendarLexer lexer = new ICalendarLexer(new ANTLRInputStream(new FileInputStream(fileName)));
        ICalendarParser parser = new ICalendarParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.parse();
        ParseTreeWalker walker = new ParseTreeWalker();

        System.out.println("\nParseTreeWalker output:\n");

        walker.walk(new ICalendarWalker(), tree);
    }
}
