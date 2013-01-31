package horai;

import horai.parser.ICalendarBaseListener;
import horai.parser.ICalendarListener;
import horai.parser.ICalendarParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class ICalendarWalker extends ICalendarBaseListener {

    @Override
    public void enterIcalobject(ICalendarParser.IcalobjectContext ctx) {
        System.out.println("icalobject");
    }

    @Override
    public void enterProdid(ICalendarParser.ProdidContext ctx) {
        System.out.println("  prodid=" + ctx.getText().trim());
    }

    @Override
    public void enterVersion(ICalendarParser.VersionContext ctx) {
        System.out.println("  version=" + ctx.getText().trim());
    }

    @Override
    public void enterEventc(ICalendarParser.EventcContext ctx) {
        System.out.println("  eventc");
    }

    @Override
    public void enterEventprop(ICalendarParser.EventpropContext ctx) {
        System.out.println("    eventprop=" + ctx.getText().trim());
    }
}