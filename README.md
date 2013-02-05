## ICalParser

An iCalendar ([RFC 5545](https://tools.ietf.org/html/rfc5545)) parser backed up by an 
[ANTLR v4 grammar](https://github.com/bkiers/ICalParser/blob/master/src/grammar/ICalendar.g4).

### Introduction

Although [ANTLR v4](http://www.antlr4.org/) currently has one target implemented (Java), the 
grammar contains no target specific code. Therefor the Ant target responsible for generating
the parser prepends a package declaration after the ANTLR tool has generated `.java` source 
files.

Note that this should be considered a pre-alpha release. Although most production rules in 
the parser are tested, much more thorough tests are preferred. My main reason for writing 
this grammar was to get acquainted with ANTLR v4, not to use the parser in production! 
However, feel free to send pull requests of fixes, or 
[report issues](https://github.com/bkiers/ICalParser/issues) you encountered. I am willing 
to put in some extra effort to make things more robust.

### Getting started

Make sure you have Ant installed.

Do the following:

* clone this repository: `git clone https://github.com/bkiers/ICalParser.git`
* generate the parser source files: `ant generate`
* run the `Main` class: `ant run`

The `run` target will parse `src/ics/test.ics` and will print some information
about this iCalendar file. If you'd like to parse another file, provide it on the command
line like this: `ant -Dics=path/to/other/file.ics run`

### Walking the parse tree

After generating the parser files, ANTLR also creates some listener files that can be used
to let a tree walker traverse the parse tree the parser creates of the input, and then will
let you "listen" for certain *enter* or *exit* events that are fired whenever the tree walker
enters or exits a production rule.

This might sound a bit vague, so I'll give a small demo. Let's say you're only interested 
in `TODO` components of an iCalendar file, and you'd like to know the `DTSTAMP` and 
`ORGANIZER` properties of this component.

You start by creating a class, `TodoListener` that extends (the generated) 
`ICalendarBaseListener`. This `ICalendarBaseListener` has empty methods for *all* production
rules in the grammar. So we're only going to override the method that gets invoked whenever
the tree walker enters the `todoc` parser rule:

```java
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
```

To test this class, do the following:

```java
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
```

The file `src/ics/test.ics` contains the following:

```
BEGIN:VCALENDAR
VERSION:2.0
PRODID:-//ABC Corporation//NONSGML My Product//EN
BEGIN:VTODO
DTSTAMP:19980130T134500Z
SEQUENCE:2
UID:uid4@example.com
ORGANIZER:mailto:unclesam@example.com
ATTENDEE;PARTSTAT=ACCEPTED:mailto:jqpublic@example.com
DUE:19980415T000000
STATUS:NEEDS-ACTION
SUMMARY:Submit Income Taxes
BEGIN:VALARM
ACTION:AUDIO
TRIGGER:19980403T120000Z
ATTACH;FMTTYPE=audio/basic:http://example.com/pub/audio-
 files/ssbanner.aud
REPEAT:4
DURATION:PT1H
END:VALARM
END:VTODO
END:VCALENDAR
```

If you now run `TodoDemo`, you will see the following being printed to your console:

```
dtstamp   -> 19980130T134500Z
organizer -> mailto:unclesam@example.com
```

See [src/main/ical/TodoDemo.java](https://github.com/bkiers/ICalParser/blob/master/src/main/ical/TodoDemo.java) 
for a working version of the demo above.

### Grammar

I tried to follow the naming convention and grammar rules as used in 
[RFC 5545](https://tools.ietf.org/html/rfc5545) as much a possible. I did stray from the
specs at times: `'\n'` will also be considered a `CRLF`.

The grammar defines very little lexer rules. This is because it is hard to properly define
keyword- and identifier tokens in iCalendar. To define keywords on a lexical level, you'd
also need to create a rule that matches an identifier. For example, if you create a lexer
rule like this:

```
VERSION : 'VERSION';
...
ALPHA : [a-zA-Z];
```

you don't want input like `"VERSIONs"` to be tokenized as a `VERSION` token followed by 
an `ALPHA`. So there should also be a rule that matches identifiers:

```
VERSION : 'VERSION';
...
IDENTIFIER : [a-zA-Z-]+;
```

causing `"VERSIONs"` to be properly tokenized as a `IDENTIFIER`. However, the iCalendar
RFC defines a `dur-value` as follows:

```
dur-value = (["+"] / "-") "P" (dur-date / dur-time / dur-week)
dur-time  = "T" (dur-hour / dur-minute / dur-second)
dur-hour  = 1*DIGIT "H" [dur-minute]
```

meaning that the input `"-PT12H"` is a valid `dur-value`. However, the substring `"PT"` 
would already be tokenized as an `IDENTIFIER` token, making it messy in production rule(s)
to match these corner cases properly (there are much more of such cases!). Therefor the 
lexer tokenizes single characters and the parser will match keywords (and identifiers), 
making it behave much like a [PEG](http://bford.info/packrat/).
