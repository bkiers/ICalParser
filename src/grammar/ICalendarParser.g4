parser grammar ICalendarParser;

import ICalendarLexer;

parse
 : icalstream EOF
 ;

// 3.4 - iCalendar Object
icalstream
 : icalobject+
 ;

icalobject
 : begin X3A v c a l e n d a r CRLF
   icalbody
   end X3A v c a l e n d a r CRLF
 ;

// 3.6 - Calendar Components
icalbody
 : calprops component
 ;

calprops
 : ( prodid
   | version
   | calscale
   | method
   | x_prop
   | iana_prop
   )*
 ;

// 3.7.1 - Calendar Scale
calscale
 : c a l s c a l e calparam X3A calvalue CRLF
 ;

calparam
 : (X3B other_param)*
 ;

calvalue
 : g r e g o r i a n
 ;

// 3.7.2 - Method
method
 : m e t h o d metparam X3A metvalue CRLF
 ;

metparam
 : (X3B other_param)*
 ;

metvalue
 : iana_token
 ;

// 3.7.3 - Product Identifier
prodid
 : p r o d i d pidparam X3A pidvalue CRLF
 ;

pidparam
 : (X3B other_param)*
 ;

pidvalue
 : text
 ;

// 3.7.4 - Version
version
 : v e r s i o n verparam X3A vervalue CRLF
 ;

verparam
 : (X3B other_param)*
 ;

vervalue
 : minver X3B maxver
 | maxver
 ;

minver
 : float_num
 ;

maxver
 : float_num
 ;

component
 : ( eventc
   | todoc
   | journalc
   | freebusyc
   | timezonec
   | iana_comp
   | x_comp
   )+
 ;

iana_comp
 : begin X3A iana_token CRLF
   contentline+
   end X3A iana_token CRLF
 ;

x_comp
 : begin X3A x_name CRLF
   contentline+
   end X3A x_name CRLF
 ;

contentline
 : name (X3B icalparameter)* X3A value CRLF
 ;

name
 : iana_token
 | x_name
 ;

value
 : value_char*
 ;

// 3.6.1 - Event Component
eventc
 : begin X3A v e v e n t CRLF
   eventprop alarmc*
   end X3A v e v e n t CRLF
 ;

// 3.6.2 - To-Do Component
todoc
 : begin X3A v t o d o CRLF
   todoprop alarmc*
   end X3A v t o d o CRLF
 ;

// 3.6.3 - Journal Component
journalc
 : begin X3A v j o u r n a l CRLF
   jourprop
   end X3A v j o u r n a l CRLF
 ;

// 3.6.4 - Free/Busy Component
freebusyc
 : begin X3A v f r e e b u s y CRLF
   fbprop
   end X3A v f r e e b u s y CRLF
 ;

// 3.6.5 - Time Zone Component
timezonec
 : begin X3A v t i m e z o n e CRLF
   ( tzid
   | last_mod
   | tzurl
   | standardc
   | daylightc
   | x_prop
   | iana_prop
   )*
   end X3A v t i m e z o n e CRLF
 ;

standardc
 : begin X3A s t a n d a r d CRLF
   tzprop
   end X3A s t a n d a r d CRLF
 ;

daylightc
 : begin X3A d a y l i g h t CRLF
   tzprop
   end X3A d a y l i g h t CRLF
 ;

// 3.6.6 - Alarm Component
alarmc
 : begin X3A v a l a r m CRLF
   ( audioprop
   | dispprop
   | emailprop
   )
   end X3A v a l a r m CRLF
 ;

// START event properties
eventprop
 : ( dtstamp
   | uid
   | dtstart
   | clazz
   | created
   | description
   | geo
   | last_mod
   | location
   | organizer
   | priority
   | seq
   | status
   | summary
   | transp
   | url
   | recurid
   | rrule
   | dtend
   | duration
   | attach
   | attendee
   | categories
   | comment
   | contact
   | exdate
   | rstatus
   | related
   | resources
   | rdate
   | x_prop
   | iana_prop
   )*
 ;

todoprop
 : ( dtstamp
   | uid
   | clazz
   | completed
   | created
   | description
   | dtstart
   | geo
   | last_mod
   | location
   | organizer
   | percent
   | priority
   | recurid
   | seq
   | status
   | summary
   | url
   | rrule
   | due
   | duration
   | attach
   | attendee
   | categories
   | comment
   | contact
   | exdate
   | rstatus
   | related
   | resources
   | rdate
   | x_prop
   | iana_prop
   )*
 ;

jourprop
 : ( dtstamp
   | uid
   | clazz
   | created
   | dtstart
   | last_mod
   | organizer
   | recurid
   | seq
   | status
   | summary
   | url
   | rrule
   | attach
   | attendee
   | categories
   | comment
   | contact
   | description
   | exdate
   | related
   | rdate
   | rstatus
   | x_prop
   | iana_prop
   )*
 ;

fbprop
 : ( dtstamp
   | uid
   | contact
   | dtstart
   | dtend
   | organizer
   | url
   | attendee
   | comment
   | freebusy
   | rstatus
   | x_prop
   | iana_prop
   )*
 ;

tzprop
 : ( dtstart
   | tzoffsetto
   | tzoffsetfrom
   | rrule
   | comment
   | rdate
   | tzname
   | x_prop
   | iana_prop
   )*
 ;

audioprop
 : ( action
   | trigger
   | duration
   | repeat
   | attach
   | x_prop
   | iana_prop
   )*
 ;

dispprop
 : ( action
   | description
   | trigger
   | duration
   | repeat
   | x_prop
   | iana_prop
   )*
 ;

emailprop
 : ( action
   | description
   | trigger
   | summary
   | attendee
   | duration
   | repeat
   | attach
   | x_prop
   | iana_prop
   )*
 ;
// END event properties

// 3.8.1.1 - Attachment
attach
 : a t t a c h attachparam ( X3A uri
                           | X3B e n c o d i n g X3D b a s e X36 X34
                             X3B v a l u e X3D b i n a r y
                             X3A binary
                           )
   CRLF
 ;

attachparam
 : ( X3B fmttypeparam
   | X3B other_param
   )*
 ;

// 3.8.1.2 - Categories
categories
 : c a t e g o r i e s catparam X3A text (X2C text)* CRLF
 ;

catparam
 : ( X3B languageparam
   | X3B other_param
   )*
 ;

// 3.8.1.3 - Classification
clazz
 : c l a s s classparam X3A classvalue CRLF
 ;

classparam
 : (X3B other_param)*
 ;

classvalue
 : p u b l i c
 | p r i v a t e
 | c o n f i d e n t i a l
 | iana_token
 | x_name
 ;

// 3.8.1.4 - Comment
comment
 : c o m m e n t commparam X3A text CRLF
 ;

commparam
 : ( X3B altrepparam
   | X3B languageparam
   | X3B other_param
   )*
 ;

// 3.8.1.5 - Description
description
 : d e s c r i p t i o n descparam X3A text CRLF
 ;

descparam
 : ( X3B altrepparam
   | X3B languageparam
   | X3B other_param
   )*
 ;

// 3.8.1.6 - Geographic Position
geo
 : g e o geoparam X3A geovalue CRLF
 ;

geoparam
 : (X3B other_param)*
 ;

geovalue
 : float_num X3B float_num
 ;

// 3.8.1.7 - Location
location
 : l o c a t i o n locparam X3A text CRLF
 ;

locparam
 : ( X3B altrepparam
   | X3B languageparam
   | X3B other_param
   )*
 ;

// 3.8.1.8 - Percent Complete
percent
 : p e r c e n t X2D c o m p l e t e pctparam X3A integer CRLF
 ;

pctparam
 : (X3B other_param)*
 ;

// 3.8.1.9 - Priority
priority
 : p r i o r i t y prioparam X3A priovalue CRLF
 ;

prioparam
 : (X3B other_param)*
 ;

priovalue
 : integer
 ;

// 3.8.1.10 - Resources
resources
 : r e s o u r c e s resrcparam X3A text (X2C text)* CRLF
 ;

resrcparam
 : ( X3B altrepparam
   | X3B languageparam
   | X3B other_param
   )*
 ;

// 3.8.1.11 - Status
status
 : s t a t u s statparam X3A statvalue CRLF
 ;

statparam
 : (X3B other_param)*
 ;

statvalue
 : statvalue_event
 | statvalue_todo
 | statvalue_jour
 ;

statvalue_event
 : t e n t a t i v e
 | c o n f i r m e d
 | c a n c e l l e d
 ;

statvalue_todo
 : n e e d s X2D a c t i o n
 | c o m p l e t e d
 | i n X2D p r o g r e s s
 | c a n c e l l e d
 ;

statvalue_jour
 : d r a f t
 | f i n a l
 | c a n c e l l e d
 ;

// 3.8.1.12 - Summary
summary
 : s u m m a r y summparam X3A text CRLF
 ;

summparam
 : ( X3B altrepparam
   | X3B languageparam
   | X3B other_param
   )*
 ;

// 3.8.2.1 - Date-Time Completed
completed
 : c o m p l e t e d compparam X3A date-time CRLF
 ;

compparam
 : (X3B other_param)*
 ;

// 3.8.2.2 - Date-Time End
dtend
 : d t e n d dtendparam X3A dtendval CRLF
 ;

dtendparam
 : ( X3B v a l u e X3D d a t e X2D t i m e
   | X3B v a l u e X3D d a t e
   | X3B tzidparam
   | X3B other_param
   )*
 ;

dtendval
 : date_time
 | date
 ;

// 3.8.2.3 - Date-Time Due
due
 : d u e dueparam X3A dueval CRLF
 ;

dueparam
 : ( X3B v a l u e X3D d a t e X2D t i m e
   | X3B v a l u e X3D d a t e
   | X3B tzidparam
   | X3B other_param
   )*
 ;

dueval
 : date_time
 | date
 ;

// 3.8.2.4 - Date-Time Start
dtstart
 : d t s t a r t dtstparam X3A dtstval CRLF
 ;

dtstparam
 : ( X3B v a l u e X3D d a t e X2D t i m e
   | X3B v a l u e X3D d a t e
   | X3B tzidparam
   | X3B other_param
   )*
 ;

dtstval
 : date_time
 | date
 ;

// 3.8.2.5 - Duration
duration
 : d u r a t i o n durparam X3A dur_value CRLF
 ;

durparam
 : (X3B other_param)*
 ;

// 3.8.2.6 - Free/Busy Time
freebusy
 : f r e e b u s y fbparam X3A fbvalue CRLF
 ;

fbparam
 : ( X3B fbtypeparam
   | X3B other_param
   )*
 ;

fbvalue
 : period (X2C period)*
 ;

// 3.8.2.7 - Time Transparency
transp
 : t r a n s p transparam X3A transvalue CRLF
 ;

transparam
 : (X3B other_param)*
 ;

transvalue
 : o p a q u e
 | t r a n s p a r e n t
 ;

// 3.8.3.1 - Time Zone Identifier
tzid
 : t z i d tzidpropparam X3A tzidprefix? text CRLF
 ;

tzidpropparam
 : (X3B other_param)*
 ;

tzidprefix
 : X2F
 ;

// 3.8.3.2.  Time Zone Name
tzname
 : t z n a m e tznparam X3A text CRLF
 ;

tznparam
 : ( X3B languageparam
   | X3B other_param
   )*
 ;

// 3.8.3.3 - Time Zone Offset From
tzoffsetfrom
 : t z o f f s e t f r o m frmparam X3A utc_offset CRLF
 ;

frmparam
 : (X3B other_param)*
 ;

// 3.8.3.4 - Time Zone Offset To
tzoffsetto
 : t z o f f s e t t o toparam X3A utc_offset CRLF
 ;

toparam
 : (X3B other_param)*
 ;

// 3.8.3.5.  Time Zone URL
tzurl
 : t z u r l tzurlparam X3A uri CRLF
 ;

tzurlparam
 : (X3B other_param)*
 ;

// 3.8.4.1 - Attendee
attendee
 : a t t e n d e e attparam X3A cal_address CRLF
 ;

attparam
 : ( X3B cutypeparam
   | X3B memberparam
   | X3B roleparam
   | X3B partstatparam
   | X3B rsvpparam
   | X3B deltoparam
   | X3B delfromparam
   | X3B sentbyparam
   | X3B cnparam
   | X3B dirparam
   | X3B languageparam
   | X3B other_param
   )*
 ;

// 3.8.4.2 - Contact
contact
 : c o n t a c t contparam X3A text CRLF
 ;

contparam
 : ( X3B altrepparam
   | X3B languageparam
   | X3B other_param
   )*
 ;

// 3.8.4.3 - Organizer
organizer
 : o r g a n i z e r orgparam X3A cal_address CRLF
 ;

orgparam
 : ( X3B cnparam
   | X3B dirparam
   | X3B sentbyparam
   | X3B languageparam
   | X3B other_param
   )*
 ;

// 3.8.4.4 - Recurrence ID
recurid
 : r e c u r r e n c e X2D i d ridparam X3A ridval CRLF
 ;

ridparam
 : ( X3B v a l u e X3D d a t e X2D t i m e
   | X3B v a l u e X3D d a t e
   | X3B tzidparam
   | X3B rangeparam
   | X3B other_param
   )*
 ;

ridval
 : date_time
 | date
 ;

// 3.8.4.5.  Related To
related
 : r e l a t  e d X2D t o relparam X3A text CRLF
 ;

relparam
 : ( X3B reltypeparam
   | X3B other_param
   )*
 ;

// 3.8.4.6 - Uniform Resource Locator
url
 : u r l urlparam X3A uri CRLF
 ;

urlparam
 : (X3B other_param)*
 ;

// 3.8.4.7 - Unique Identifier
uid
 : u i d uidparam X3A text CRLF
 ;

uidparam
 : (X3B other_param)*
 ;

// 3.8.5.1 - Exception Date-Times
exdate
 : e x d a t e exdtparam X3A exdtval (X2C exdtval)* CRLF
 ;

exdtparam
 : ( X3B v a l u e X3D d a t e X2D t i m e
   | X3B v a l u e X3D d a t e
   | X3B tzidparam
   | X3B other_param
   )*
 ;

exdtval
 : date_time
 | date
 ;

// 3.8.5.2 - Recurrence Date-Times
rdate
 : r d a t e rdtparam X3A rdtval (X2C rdtval)* CRLF
 ;

rdtparam
 : ( X3B v a l u e X3D d a t e X2D t i m e
   | X3B v a l u e X3D d a t e
   | X3B v a l u e X3D p e r i o d
   | X3B tzidparam
   | X3B other_param
   )*
 ;

rdtval
 : date_time
 | date
 ;

// 3.8.5.3 - Recurrence Rule
rrule
 : r r u l e rrulparam X3A recur CRLF
 ;

rrulparam
 : (X3B other_param)*
 ;

// 3.8.6.1 - Action
action
 : a c t i o n actionparam X3A actionvalue CRLF
 ;

actionparam
 : (X3B other_param)*
 ;

actionvalue
 : a u d i o
 | d i s p l a y
 | e m a i l
 | iana_token
 | x_name
 ;

// 3.8.6.2 - Repeat Count
repeat
 : r e p e a t repparam X3A integer CRLF
 ;

repparam
 : (X3B other_param)*
 ;

// 3.8.6.3 - Trigger
trigger
 : t r i g g e r (trigrel | trigabs) CRLF
 ;

trigrel
 : ( X3B v a l u e X3D  d u r a t i o n
   | X3B trigrelparam
   | X3B other_param
   )*
   X3A dur_value
 ;

trigabs
 : ( X3B v a l u e X3D d a t e X2D t i m e
   | X3B other_param
   )*
   X3A date_time
 ;

// 3.8.7.1 - Date-Time Created
created
 : c r e a t e d creaparam X3A date_time CRLF
 ;

creaparam
 : (X3B other_param)*
 ;

// 3.8.7.2 - Date-Time Stamp
dtstamp
 : d t s t a m p stmparam X3A date-time CRLF
 ;

stmparam
 : (X3B other_param)*
 ;

// 3.8.7.3 - Last Modified
last_mod
 : l a s t X2D m o d i f i e d lstparam X3A date_time CRLF
 ;

lstparam
 : (X3B other_param)*
 ;

// 3.8.7.4 - Sequence Number
seq
 : s e q u e n c e seqparam X3A integer CRLF
 ;

seqparam
 : (X3B other_param)*
 ;

// 3.8.8.1 - IANA Properties
iana_prop
 : iana_token (X3B icalparameter)* X3A value CRLF
 ;

// 3.8.8.2 - Non-Standard Propertie
x_prop
 : x_name (X3B icalparameter)* X3A value CRLF
;

// 3.8.8.3 - Request Status
rstatus
 : r e q u e s t X2D s t a t u s rstatparam X3A statcode X3B statdesc (X3B extdata)?
 ;

rstatparam
 : ( X3B languageparam
   | X3B other_param
   )*
 ;

statcode
 : digit+ (X2E digit+) (X2E digit+)?
 ;

statdesc
 : text
 ;

extdata
 : text
 ;

param_name
 : iana_token
 | x_name
 ;

param_value
 : paramtext
 | quoted_string
 ;

paramtext
 : safe_char*
 ;

quoted_string
 : X22 qsafe_char* X22
 ;

// Any character except CONTROL, DQUOTE, ";", ":", ","
safe_char
 : x23_2B
 | x2D_39
 | x3C_7E
 | WSP
 | X21
 | NON_US_ASCII
 ;

// Any textual character
value_char
 : x21_7E
 | WSP
 | NON_US_ASCII
 ;

// Any character except CONTROL and DQUOTE
qsafe_char
 : x23_7E
 | WSP
 | X21
 | NON_US_ASCII
 ;

// iCalendar identifier registered with IANA
iana_token
 : (alpha | digit | X2D)+
 ;

// Reserved for experimental use.
x_name
 : x X2D (vendorid X2D)? (alpha | digit | X2D)+
 ;

vendorid
 : (alpha | digit) (alpha | digit) (alpha | digit)+
 ;

// 3.2
icalparameter
 : altrepparam
 | cnparam
 | cutypeparam
 | delfromparam
 | deltoparam
 | dirparam
 | encodingparam
 | fmttypeparam
 | fbtypeparam
 | languageparam
 | memberparam
 | partstatparam
 | rangeparam
 | trigrelparam
 | reltypeparam
 | roleparam
 | rsvpparam
 | sentbyparam
 | tzidparam
 | valuetypeparam
 | other_param
 ;

// 3.2.1
altrepparam
 : a l t r e p X3D X22 uri X22
 ;

// 3.2.2
cnparam
 : c n X3D param_value
 ;

// 3.2.3
cutypeparam
 : c u t y p e X3D ( i n d i v i d u a l
                   | g r o u p
                   | r e s o u r c e
                   | r o o m
                   | u n k n o w n
                   | x_name
                   | iana_token
                   )
 ;

// 3.2.4
delfromparam
 : d e l e g a t e d X2D f r o m X3D X22 cal_address X22 (X2C X22 cal_address X22)*
 ;

// 3.2.5
deltoparam
 : d e l e g a t e d X2D t o X3D X22 cal_address X22 (X2C X22 cal_address X22)*
 ;

// 3.2.6
dirparam
 : d i r X3D X22 uri X22
 ;

// 3.2.7
encodingparam
 : e n c o d i n g X3D ( X38 b i t
                       | b a s e X36 X34
                       )
 ;

// 3.2.8
fmttypeparam
 : f m t t y p e X3D type_name X2F subtype_name
 ;

// 3.2.9
fbtypeparam
 : f b t y p e X3D ( f r e e
                   | b u s y
                   | b u s y X2D u n a v a i l a b l e
                   | b u s y X2D t e n t a t i v e
                   | x_name
                   | iana_token
                   )
 ;

// 3.2.10
languageparam
 : l a n g u a g e X3D language
 ;

// 3.2.11
memberparam
 : m e m b e r X3D X22 cal_address X22 (X2C X22 cal_address X22)*
 ;

// 3.2.12
partstatparam
 : p a r t s t a t X3D ( partstat_event
                       | partstat_todo
                       | partstat_jour
                       )
 ;

// 3.2.13
rangeparam
 : r a n g e X3D t h i s a n d f u t u r e
 ;

// 3.2.14
trigrelparam
 : r e l a t e d X3D ( s t a r t
                     | e n d
                     )
 ;

// 3.2.15
reltypeparam
 : r e l t y p e X3D ( p a r e n t
                     | c h i l d
                     | s i b l i n g
                     | iana_token
                     | x_name
                     )
 ;

// 3.2.16
roleparam
 : r o l e X3D ( c h a i r
               | r e q X2D p a r t i c i p a n t
               | o p t X2D p a r t i c i p a n t
               | n o n X2D p a r t i c i p a n t
               | iana_token
               | x_name
               )
 ;

// 3.2.17
rsvpparam
 : r s v p X3D ( t r u e
               | f a l s e
               )
 ;

// 3.2.18
sentbyparam
 : s e n t X2D b y X3D X22 cal_address X22
 ;

// 3.2.19
tzidparam
 : t z i d X3D tzidprefix? paramtext
 ;

// 3.2.20
valuetypeparam
 : v a l u e X3D valuetype
 ;

valuetype
 : b i n a r y
 | b o o l e a n
 | c a l X2D a d d r e s s
 | d a t e
 | d a t e X2D t i m e
 | d u r a t i o n
 | f l o a t
 | i n t e g e r
 | p e r i o d
 | r e c u r
 | t e x t
 | t i m e
 | u r i
 | u t c X2D o f f s e t
 | x_name
 | iana_token
 ;

// 3.3.1 - A "BASE64" encoded character string, as defined by [RFC4648].
binary
 : (b_char b_char b_char b_char)* b_end?
 ;

// 3.3.2
bool
 : t r u e
 | f a l s e
 ;

// 3.3.3
cal_address
 : uri
 ;

// 3.3.4
date
 : date_value
 ;

// 3.3.5
date_time
 : date t time
 ;

// 3.3.6
dur_value
 : X2D p (dur_date | dur_time | dur_week)
 | X2B? p (dur_date | dur_time | dur_week)
 ;

// 3.3.7
float_num
 : X2D digit+ (X2E digit+)?
 | X2B? digit+ (X2E digit+)?
 ;

// 3.3.8
integer
 : X2D digit+
 | X2B? digit+
 ;

// 3.3.9
period
 : period_explicit
 | period_start
 ;

// 3.3.10
recur
 : recur_rule_part (X3B recur_rule_part)*
 ;

// 3.3.11
text
 : (tsafe_char | X3A | X22 | escaped_char)*
 ;

// 3.3.12
time
 : time_hour time_minute time_second time_utc?
 ;

// 3.3.13 - As defined in Section 3 of [RFC3986].
uri
 : qsafe_char+
 ;

// 3.3.14
utc_offset
 : time_numzone
 ;

// Applications MUST ignore x-param and iana-param values they don't
// recognize.
other_param
 : iana_param
 | x_param
 ;

// Some other IANA-registered iCalendar parameter.
iana_param
 : iana_token X3D param_value (X2C param_value)*
 ;

// A non-standard, experimental parameter.
x_param
 : x_name X3D param_value (X2C param_value)*
 ;

// As defined in Section 4.2 of [RFC4288].
type_name
 : reg_name
 ;

// As defined in Section 4.2 of [RFC4288].
subtype_name
 : reg_name
 ;

// Between 1 and 127 chars allowed as defined in Section 4.2 of [RFC4288].
reg_name
 : reg_name_char+
 ;

// Loosely matched language (see [RFC5646]).
language
 : language_char+
 ;

partstat_event
 : n e e d s X2D a c t i o n
 | a c c e p t e d
 | d e c l i n e d
 | t e n t a t i v e
 | d e l e g a t e d
 | x_name
 | iana_token
 ;

partstat_todo
 : n e e d s X2D a c t i o n
 | a c c e p t e d
 | d e c l i n e d
 | t e n t a t i v e
 | d e l e g a t e d
 | c o m p l e t e d
 | i n X2D p r o c e s s
 | x_name
 | iana_token
 ;

partstat_jour
 : n e e d s X2D a c t i o n
 | a c c e p t e d
 | d e c l i n e d
 | x_name
 | iana_token
 ;

b_char
 : alpha
 | digit
 | X2B
 | X2F
 ;

b_end
 : b_char b_char X3D X3D
 | b_char b_char b_char X3D
 ;

date_value
 : date_fullyear date_month date_mday
 ;

date_fullyear
 : digit digit digit digit
 ;

date_month
 : digit digit
 ;

date_mday
 : digit digit
 ;

time_hour
 : digit digit
 ;

time_minute
 : digit digit
 ;

time_second
 : digit digit
 ;

time_utc
 : z
 ;

dur_date
 : dur_day dur_time?
 ;

dur_time
 : t (dur_hour | dur_minute | dur_second)
 ;

dur_week
 : digit+ w
 ;

dur_day
 : digit+ d
 ;

dur_hour
 : digit+ h dur_minute?
 ;

dur_minute
 : digit+ m dur_second
 ;

dur_second
 : digit+ s
 ;

period_explicit
 : date_time X2F date_time
 ;

period_start
 : date_time X2F dur_value
 ;

recur_rule_part
 : f r e q X3D freq
 | u n t i l X3D enddate
 | c o u n t X3D digit+
 | i n t e r v a l X3D digit+
 | b y s e c o n d X3D byseclist
 | b y m i n u t e X3D byminlist
 | b y h o u r X3D byhrlist
 | b y d a y X3D bywdaylist
 | b y m o n t h d a y X3D bymodaylist
 | b y y e a r d a y X3D byyrdaylist
 | b y w e e k n o X3D bywknolist
 | b y m o n t h X3D bymolist
 | b y s e t p o s X3D bysplist
 | w k s t X3D weekday
 ;

freq
 : s e c o n d l y
 | m i n u t e l y
 | h o u r l y
 | d a i l y
 | w e e k l y
 | m o n t h l y
 | y e a r l y
 ;

enddate
 : date
 | date_time
 ;

byseclist
 : seconds (X2C seconds)*
 ;

seconds
 : digit digit?
 ;

byminlist
 : minutes (X2C minutes)*
 ;

minutes
 : digit digit?
 ;

byhrlist
 : hour (X2C hour)*
 ;

hour
 : digit digit?
 ;

bywdaylist
 : weekdaynum (X2C weekdaynum)*
 ;

weekdaynum
 : ((plus | minus)? ordwk)? weekday
 ;

plus
 : X2B
 ;

minus
 : X2D
 ;

ordwk
 : digit digit?
 ;

weekday
 : s u
 | m o
 | t u
 | w e
 | t h
 | f r
 | s a
 ;

bymodaylist
 : monthdaynum (X2C monthdaynum)*
 ;

monthdaynum
 : (plus | minus)? ordmoday
 ;

ordmoday
 : digit digit?
 ;

byyrdaylist
 : yeardaynum (X2C yeardaynum)*
 ;

yeardaynum
 : (plus | minus)? ordyrday
 ;

ordyrday
 : digit (digit digit?)?
 ;

bywknolist
 : weeknum (X2C weeknum)*
 ;

weeknum
 : (plus | minus)? ordwk
 ;

bymolist
 : monthnum (X2C monthnum)*
 ;

monthnum
 : digit digit?
 ;

bysplist
 : setposday (X2C setposday)*
 ;

setposday
 : yeardaynum
 ;

tsafe_char
 : x23_2B
 | x2D_39
 | x3C_5B
 | x5D_7E
 | NON_US_ASCII
 | WSP
 | X21
 ;

escaped_char
 : X5C X5C
 | X5C X3B
 | X5C X2C
 | X5C n
 ;

time_numzone
 : (X2B | X2D) time_hour time_minute time_second?
 ;

reg_name_char
 : alpha
 | digit
 | X21
 | X23
 | X24
 | X26
 | X2E
 | X2B
 | X2D
 | X5E
 | X5F
 ;

language_char
 : alpha
 | digit
 | X2D
 | X3A
 ;

begin
 : b e g i n
 ;

end
 : e n d
 ;

// lower- and uppercase letters: a..z, A..Z
alpha
 : X41 | X42 | X43 | X44 | X45 | X46 | X47 | X48 | X49 | X4A | X4B | X4C | X4D
 | X4E | X4F | X50 | X51 | X52 | X53 | X54 | X55 | X56 | X57 | X58 | X59 | X5A
 | X61 | X62 | X63 | X64 | X65 | X66 | X67 | X68 | X69 | X6A | X6B | X6C | X6D
 | X6E | X6F | X70 | X71 | X72 | X73 | X74 | X75 | X76 | X77 | X78 | X79 | X7A
 ;

// the digits: 0..9
digit
 : X30 | X31 | X32 | X33 | X34 | X35 | X36 | X37 | X38 | X39
 ;

// Other char-ranges
x23_2B
 : X23 | X24 | X25 | X26 | X27 | X28 | X29 | X2A | X2B
 ;

x2D_39
 : X2D | X2E | X2F | X30 | X31 | X32 | X33 | X34 | X35 | X36 | X37 | X38 | X39
 ;

x3C_5B
 : X3C | X3D | X3E | X3F | X40 | X41 | X42 | X43 | X44 | X45 | X46 | X47 | X48
 | X49 | X4A | X4B | X4C | X4D | X4E | X4F | X50 | X51 | X52 | X53 | X54 | X55
 | X56 | X57 | X58 | X59 | X5A | X5B
 ;

x5D_7E
 : X5D | X5E | X5F | X60 | X61 | X62 | X63 | X64 | X65 | X66 | X67 | X68 | X69
 | X6A | X6B | X6C | X6D | X6E | X6F | X70 | X71 | X72 | X73 | X74 | X75 | X76
 | X77 | X78 | X79 | X7A | X7B | X7C | X7D | X7E
 ;

x3C_7E
 : X3C | X3D | X3E | X3F | X40 | X41 | X42 | X43 | X44 | X45 | X46 | X47 | X48
 | X49 | X4A | X4B | X4C | X4D | X4E | X4F | X50 | X51 | X52 | X53 | X54 | X55
 | X56 | X57 | X58 | X59 | X5A | X5B | X5C | X5D | X5E | X5F | X60 | X61 | X62
 | X63 | X64 | X65 | X66 | X67 | X68 | X69 | X6A | X6B | X6C | X6D | X6E | X6F
 | X70 | X71 | X72 | X73 | X74 | X75 | X76 | X77 | X78 | X79 | X7A | X7B | X7C
 | X7D | X7E
 ;

x23_7E
 : X23 | X24 | X25 | X26 | X27 | X28 | X29 | X2A | X2B | X2C | X2D | X2E | X2F
 | X30 | X31 | X32 | X33 | X34 | X35 | X36 | X37 | X38 | X39 | X3A | X3B | X3C
 | X3D | X3E | X3F | X40 | X41 | X42 | X43 | X44 | X45 | X46 | X47 | X48 | X49
 | X4A | X4B | X4C | X4D | X4E | X4F | X50 | X51 | X52 | X53 | X54 | X55 | X56
 | X57 | X58 | X59 | X5A | X5B | X5C | X5D | X5E | X5F | X60 | X61 | X62 | X63
 | X64 | X65 | X66 | X67 | X68 | X69 | X6A | X6B | X6C | X6D | X6E | X6F | X70
 | X71 | X72 | X73 | X74 | X75 | X76 | X77 | X78 | X79 | X7A | X7B | X7C | X7D
 | X7E
 ;

x21_7E
 : X21 | X22 | X23 | X24 | X25 | X26 | X27 | X28 | X29 | X2A | X2B | X2C | X2D
 | X2E | X2F | X30 | X31 | X32 | X33 | X34 | X35 | X36 | X37 | X38 | X39 | X3A
 | X3B | X3C | X3D | X3E | X3F | X40 | X41 | X42 | X43 | X44 | X45 | X46 | X47
 | X48 | X49 | X4A | X4B | X4C | X4D | X4E | X4F | X50 | X51 | X52 | X53 | X54
 | X55 | X56 | X57 | X58 | X59 | X5A | X5B | X5C | X5D | X5E | X5F | X60 | X61
 | X62 | X63 | X64 | X65 | X66 | X67 | X68 | X69 | X6A | X6B | X6C | X6D | X6E
 | X6F | X70 | X71 | X72 | X73 | X74 | X75 | X76 | X77 | X78 | X79 | X7A | X7B
 | X7C | X7D | X7E
 ;

// Upper- and lowercase letters
a : X61 | X41;
b : X62 | X42;
c : X63 | X43;
d : X64 | X44;
e : X65 | X45;
f : X66 | X46;
g : X67 | X47;
h : X68 | X48;
i : X69 | X49;
j : X6A | X4A;
k : X6B | X4B;
l : X6C | X4C;
m : X6D | X4D;
n : X6E | X4E;
o : X6F | X4F;
p : X70 | X50;
q : X71 | X51;
r : X72 | X52;
s : X73 | X53;
t : X74 | X54;
u : X75 | X55;
v : X76 | X56;
w : X77 | X57;
x : X78 | X58;
y : X79 | X59;
z : X7A | X5A;
