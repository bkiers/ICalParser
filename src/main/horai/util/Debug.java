package horai.util;

import horai.parser.ICalendarLexer;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Token;

public final class Debug {

    public static void dumpTokens(String source) {

        ICalendarLexer lexer = new ICalendarLexer(new ANTLRInputStream(source));

        for(Token token : lexer.getAllTokens()) {
            System.out.printf("%-15s%s%n",
                    ICalendarLexer.tokenNames[token.getType()],
                    token.getText().replace("\n", "\\n")
            );
        }
    }
}
