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

        ///*
        FileInputStream input = new FileInputStream("src/ics/test.ics");
        ICalendarLexer lexer = new ICalendarLexer(new ANTLRInputStream(input));
        ICalendarParser parser = new ICalendarParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.parse();

        //System.out.println("tree=" + tree);


        ParseTreeWalker walker = new ParseTreeWalker();
        ICalendarWalker calendarWalker = new ICalendarWalker();
        walker.walk(calendarWalker, tree);
        //*/
    }
}
