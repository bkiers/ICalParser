package ical;

import ical.parser.ICalendarBaseListener;
import ical.parser.ICalendarParser;

public class ICalendarWalker extends ICalendarBaseListener {

    // 8.3.1.  Components Registry
    // https://tools.ietf.org/html/rfc5545#section-8.3.1

    @Override
    public void enterIcalobject(ICalendarParser.IcalobjectContext ctx) {
        System.out.println("Icalobject");
    }

    @Override
    public void enterEventc(ICalendarParser.EventcContext ctx) {
        System.out.println("    Eventc");
    }

    @Override
    public void enterTodoc(ICalendarParser.TodocContext ctx) {
        System.out.println("    Todoc");
    }

    @Override
    public void enterJournalc(ICalendarParser.JournalcContext ctx) {
        System.out.println("    Journalc");
    }

    @Override
    public void enterFreebusyc(ICalendarParser.FreebusycContext ctx) {
        System.out.println("    Freebusyc");
    }

    @Override
    public void enterTimezonec(ICalendarParser.TimezonecContext ctx) {
        System.out.println("    Timezonec");
    }

    @Override
    public void enterAlarmc(ICalendarParser.AlarmcContext ctx) {
        System.out.println("    Alarmc");
    }

    @Override
    public void enterStandardc(ICalendarParser.StandardcContext ctx) {
        System.out.println("    Standardc");
    }

    @Override
    public void enterDaylightc(ICalendarParser.DaylightcContext ctx) {
        System.out.println("    Daylightc");
    }

    // 8.3.2.  Properties Registry
    // https://tools.ietf.org/html/rfc5545#section-8.3.2

    @Override
    public void enterCalscale(ICalendarParser.CalscaleContext ctx) {
        System.out.println("        Calscale -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterMethod(ICalendarParser.MethodContext ctx) {
        System.out.println("        Method -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterProdid(ICalendarParser.ProdidContext ctx) {
        System.out.println("        Prodid -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterVersion(ICalendarParser.VersionContext ctx) {
        System.out.println("        Version -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterAttach(ICalendarParser.AttachContext ctx) {
        System.out.println("        Attach -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterCategories(ICalendarParser.CategoriesContext ctx) {
        System.out.println("        Categories -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterClazz(ICalendarParser.ClazzContext ctx) {
        System.out.println("        Clazz -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterComment(ICalendarParser.CommentContext ctx) {
        System.out.println("        Comment -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterDescription(ICalendarParser.DescriptionContext ctx) {
        System.out.println("        Description -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterGeo(ICalendarParser.GeoContext ctx) {
        System.out.println("        Geo -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterLocation(ICalendarParser.LocationContext ctx) {
        System.out.println("        Location -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterPercent(ICalendarParser.PercentContext ctx) {
        System.out.println("        Percent -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterPriority(ICalendarParser.PriorityContext ctx) {
        System.out.println("        Priority -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterResources(ICalendarParser.ResourcesContext ctx) {
        System.out.println("        Resources -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterStatus(ICalendarParser.StatusContext ctx) {
        System.out.println("        Status -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterSummary(ICalendarParser.SummaryContext ctx) {
        System.out.println("        Summary -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterCompleted(ICalendarParser.CompletedContext ctx) {
        System.out.println("        Completed -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterDtend(ICalendarParser.DtendContext ctx) {
        System.out.println("        Dtend -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterDue(ICalendarParser.DueContext ctx) {
        System.out.println("        Due -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterDtstart(ICalendarParser.DtstartContext ctx) {
        System.out.println("        Dtstart -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterDuration(ICalendarParser.DurationContext ctx) {
        System.out.println("        Duration -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterFreebusy(ICalendarParser.FreebusyContext ctx) {
        System.out.println("        Freebusy -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterTransp(ICalendarParser.TranspContext ctx) {
        System.out.println("        Transp -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterTzid(ICalendarParser.TzidContext ctx) {
        System.out.println("        Tzid -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterTzname(ICalendarParser.TznameContext ctx) {
        System.out.println("        Tzname -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterTzoffsetfrom(ICalendarParser.TzoffsetfromContext ctx) {
        System.out.println("        Tzoffsetfrom -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterTzoffsetto(ICalendarParser.TzoffsettoContext ctx) {
        System.out.println("        Tzoffsetto -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterTzurl(ICalendarParser.TzurlContext ctx) {
        System.out.println("        Tzurl -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterAttendee(ICalendarParser.AttendeeContext ctx) {
        System.out.println("        Attendee -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterContact(ICalendarParser.ContactContext ctx) {
        System.out.println("        Contact -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterOrganizer(ICalendarParser.OrganizerContext ctx) {
        System.out.println("        Organizer -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterRecurid(ICalendarParser.RecuridContext ctx) {
        System.out.println("        Recurid -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterRelated(ICalendarParser.RelatedContext ctx) {
        System.out.println("        Related -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterUrl(ICalendarParser.UrlContext ctx) {
        System.out.println("        Url -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterUid(ICalendarParser.UidContext ctx) {
        System.out.println("        Uid -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterExdate(ICalendarParser.ExdateContext ctx) {
        System.out.println("        Exdate -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterRdate(ICalendarParser.RdateContext ctx) {
        System.out.println("        Rdate -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterRrule(ICalendarParser.RruleContext ctx) {
        System.out.println("        Rrule -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterAction(ICalendarParser.ActionContext ctx) {
        System.out.println("        Action -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterRepeat(ICalendarParser.RepeatContext ctx) {
        System.out.println("        Repeat -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterTrigger(ICalendarParser.TriggerContext ctx) {
        System.out.println("        Trigger -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterCreated(ICalendarParser.CreatedContext ctx) {
        System.out.println("        Created -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterDtstamp(ICalendarParser.DtstampContext ctx) {
        System.out.println("        Dtstamp -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterLast_mod(ICalendarParser.Last_modContext ctx) {
        System.out.println("        Last_mod -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterSeq(ICalendarParser.SeqContext ctx) {
        System.out.println("        Seq -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterRstatus(ICalendarParser.RstatusContext ctx) {
        System.out.println("        Rstatus -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    // 8.3.3.  Parameters Registry
    // https://tools.ietf.org/html/rfc5545#section-8.3.3

    @Override
    public void enterAltrepparam(ICalendarParser.AltrepparamContext ctx) {
        System.out.println("            Altrepparam -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterCnparam(ICalendarParser.CnparamContext ctx) {
        System.out.println("            Cnparam -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterCutypeparam(ICalendarParser.CutypeparamContext ctx) {
        System.out.println("            Cutypeparam -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterDelfromparam(ICalendarParser.DelfromparamContext ctx) {
        System.out.println("            Delfromparam -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterDeltoparam(ICalendarParser.DeltoparamContext ctx) {
        System.out.println("            Deltoparam -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterDirparam(ICalendarParser.DirparamContext ctx) {
        System.out.println("            Dirparam -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterEncodingparam(ICalendarParser.EncodingparamContext ctx) {
        System.out.println("            Encodingparam -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterFmttypeparam(ICalendarParser.FmttypeparamContext ctx) {
        System.out.println("            Fmttypeparam -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterFbtypeparam(ICalendarParser.FbtypeparamContext ctx) {
        System.out.println("            Fbtypeparam -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterLanguageparam(ICalendarParser.LanguageparamContext ctx) {
        System.out.println("            Languageparam -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterMemberparam(ICalendarParser.MemberparamContext ctx) {
        System.out.println("            Memberparam -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterPartstatparam(ICalendarParser.PartstatparamContext ctx) {
        System.out.println("            Partstatparam -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterRangeparam(ICalendarParser.RangeparamContext ctx) {
        System.out.println("            Rangeparam -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterTrigrelparam(ICalendarParser.TrigrelparamContext ctx) {
        System.out.println("            Trigrelparam -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterReltypeparam(ICalendarParser.ReltypeparamContext ctx) {
        System.out.println("            Reltypeparam -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterRoleparam(ICalendarParser.RoleparamContext ctx) {
        System.out.println("            Roleparam -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterRsvpparam(ICalendarParser.RsvpparamContext ctx) {
        System.out.println("            Rsvpparam -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterSentbyparam(ICalendarParser.SentbyparamContext ctx) {
        System.out.println("            Sentbyparam -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterTzidparam(ICalendarParser.TzidparamContext ctx) {
        System.out.println("            Tzidparam -> " + ctx.getText().trim().replace("\n", "\\n"));
    }

    @Override
    public void enterValue(ICalendarParser.ValueContext ctx) {
        System.out.println("            Value -> " + ctx.getText().trim().replace("\n", "\\n"));
    }
}