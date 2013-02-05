## ICalParser

An iCalendar ([RFC 5545](https://tools.ietf.org/html/rfc5545)) parser backed up by an 
[ANTLR v4 grammar](https://github.com/bkiers/ICalParser/blob/master/src/grammar/ICalendar.g4).

### Introduction

Although [ANTLR v4](http://www.antlr4.org/) currently has one target implemented (Java), the 
grammar contains no target specific code. Therefor the Ant target responsible for generating
the parser prepends a package declaration after the ANTLR tool has generated `.java` source 
files.

Note that this is a alpha release. Although most production rules in the parser are tested, 
much more thorough tests are preferred. I wrote this grammar to get acquainted with ANTLR v4
not to use the parser in production! However, feel free to send pull requests of fixes, or 
[report issues](https://github.com/bkiers/ICalParser/issues) you encountered. I am willing 
to put in some extra effort to make things more robust.

### Getting started

Make sure you have Ant installed.

Do the following:

* clone this repository: `git clone https://github.com/bkiers/ICalParser.git`
* generate the parser source files: `ant generate`
* run the `Main` class: `ant run`

The `run` target will parse the file `src/ics/test.ics` and will print some information
about this iCalendar file. If you'd like to parse another file, provide it on the command
line like this: `ant -Dics=path/to/other/file.ics run`

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
making it bahve much like a [PEG](http://bford.info/packrat/).
