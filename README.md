## ICalParser

An iCalendar ([RFC 5545](https://tools.ietf.org/html/rfc5545)) parser backed up by an 
[ANTLR v4 grammar](https://github.com/bkiers/ICalParser/blob/master/src/grammar/ICalendar.g4).

### Introduction

Although [ANTLR v4](http://www.antlr4.org/) currently has one target implemented (Java), the 
grammar contains no target specific code. Therefor the Ant target responsible for generating
the parser prepends a package declaration after the ANTLR tool has generated `.java` source 
files.

Note that this is a alpha release. Although most production rules in the parser are tested, 
much more thorough tests are preferred.

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

The grammar ...
