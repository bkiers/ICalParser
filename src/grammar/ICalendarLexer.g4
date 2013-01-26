lexer grammar ICalendarLexer;

LINE_FOLD
 : CRLF WSP -> skip
 ;

WSP
 : ' '
 | '\t'
 ;

CRLF
 : ('\r'? '\n' | '\r')
 ;

// All the controls except HTAB
CONTROL // TODO: warn if used?
 : [\u0000-\u0008]
 | [\u000A-\u001F]
 | [\u007F]
 ;

X21 : '!';
X22 : '"';
X23 : '#';
X24 : '$';
X25 : '%';
X26 : '&';
X27 : '\'';
X28 : '(';
X29 : ')';
X2A : '*';
X2B : '+';
X2C : ',';
X2D : '-';
X2E : '.';
X2F : '/';
X30 : '0';
X31 : '1';
X32 : '2';
X33 : '3';
X34 : '4';
X35 : '5';
X36 : '6';
X37 : '7';
X38 : '8';
X39 : '9';
X3A : ':';
X3B : ';';
X3C : '<';
X3D : '=';
X3E : '>';
X3F : '?';
X40 : '@';
X41 : 'A';
X42 : 'B';
X43 : 'C';
X44 : 'D';
X45 : 'E';
X46 : 'F';
X47 : 'G';
X48 : 'H';
X49 : 'I';
X4A : 'J';
X4B : 'K';
X4C : 'L';
X4D : 'M';
X4E : 'N';
X4F : 'O';
X50 : 'P';
X51 : 'Q';
X52 : 'R';
X53 : 'S';
X54 : 'T';
X55 : 'U';
X56 : 'V';
X57 : 'W';
X58 : 'X';
X59 : 'Y';
X5A : 'Z';
X5B : '[';
X5C : '\\';
X5D : ']';
X5E : '^';
X5F : '_';
X60 : '`';
X61 : 'a';
X62 : 'b';
X63 : 'c';
X64 : 'd';
X65 : 'e';
X66 : 'f';
X67 : 'g';
X68 : 'h';
X69 : 'i';
X6A : 'j';
X6B : 'k';
X6C : 'l';
X6D : 'm';
X6E : 'n';
X6F : 'o';
X70 : 'p';
X71 : 'q';
X72 : 'r';
X73 : 's';
X74 : 't';
X75 : 'u';
X76 : 'v';
X77 : 'w';
X78 : 'x';
X79 : 'y';
X7A : 'z';
X7B : '{';
X7C : '|';
X7D : '}';
X7E : '~';

NON_US_ASCII
 : .
 ;