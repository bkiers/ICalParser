package horai;

import horai.parser.ICalendarBaseListener;
import horai.parser.ICalendarListener;
import horai.parser.ICalendarParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class ICalendarWalker extends ICalendarBaseListener {

    @Override
    public void exitStandardc(ICalendarParser.StandardcContext ctx) {
        System.out.println("< " + ctx.getText());
    }
}