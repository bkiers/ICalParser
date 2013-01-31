grammar ICalendar;

////////////////////////////// parser rules //////////////////////////////
parse
 : icalstream EOF
 ;

// 3.4 - iCalendar Object
icalstream
 : icalobject+
 ;

icalobject
 : BEGIN ':' VCALENDAR CRLF 
   icalbody
   END ':' VCALENDAR CRLF
 ;

// 3.6 - Calendar Components
icalbody
 : calprop* component+
 ;

calprop
 : prodid
 | version
 | calscale
 | method
 | x_prop
 | iana_prop
 ;

// 3.7.1 - Calendar Scale
calscale
 : CALSCALE (';' other_param)* ':' GREGORIAN CRLF
 ;

// 3.7.2 - Method
method
 : METHOD (';' other_param)* ':' iana_token CRLF
 ;

// 3.7.3 - Product Identifier
prodid
 : PRODID (';' other_param)* ':' text CRLF
 ;

// 3.7.4 - Version
version
 : VERSION (';' other_param)* ':' vervalue CRLF
 ;

vervalue
 : minver ';' maxver
 | maxver
 ;

minver
 : float_num
 ;

maxver
 : float_num
 ;

component
 : eventc 
 | todoc 
 | journalc 
 | freebusyc 
 | timezonec 
 | iana_comp 
 | x_comp
 ;

iana_comp
 : BEGIN ':' iana_token CRLF
   contentline+
   END ':' iana_token CRLF
 ;

x_comp
 : BEGIN ':' X_NAME CRLF
   contentline+
   END ':' X_NAME CRLF
 ;

contentline
 : name (';' icalparameter)* ':' value CRLF
 ;

name 
 : iana_token
 | X_NAME
 ;

value
 : value_char*
 ;

// 3.6.1 - Event Component
eventc
 : BEGIN ':' VEVENT CRLF
   eventprop* 
   alarmc*
   END ':' VEVENT CRLF
 ;

// 3.6.2 - To-Do Component
todoc
 : BEGIN ':' VTODO CRLF
   todoprop* 
   alarmc*
   END ':' VTODO CRLF
 ;

// 3.6.3 - Journal Component
journalc
 : BEGIN ':' VJOURNAL CRLF
   jourprop*
   END ':' VJOURNAL CRLF
 ;

// 3.6.4 - Free/Busy Component
freebusyc
 : BEGIN ':' VFREEBUSY CRLF
   fbprop*
   END ':' VFREEBUSY CRLF
 ;

// 3.6.5 - Time Zone Component
timezonec
 : BEGIN ':' VTIMEZONE CRLF
   timezoneprop*
   END ':' VTIMEZONE CRLF
 ;

standardc
 : BEGIN ':' STANDARD CRLF
   tzprop*
   END ':' STANDARD CRLF
 ;

daylightc
 : BEGIN ':' DAYLIGHT CRLF
   tzprop*
   END ':' DAYLIGHT CRLF
 ;

// 3.6.6 - Alarm Component
alarmc
 : BEGIN ':' VALARM CRLF
   alarmprop+
   END ':' VALARM CRLF
 ;

eventprop
 : dtstamp
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
 ;

todoprop
 : dtstamp
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
 ;

jourprop
 : dtstamp
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
 ;

fbprop
 : dtstamp
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
 ;

timezoneprop
 : tzid
 | last_mod
 | tzurl
 | standardc
 | daylightc
 | x_prop
 | iana_prop
 ;

tzprop
 : dtstart
 | tzoffsetto
 | tzoffsetfrom
 | rrule
 | comment
 | rdate
 | tzname
 | x_prop
 | iana_prop
 ;

alarmprop
 : action
 | description
 | trigger
 | summary
 | attendee
 | duration
 | repeat
 | attach
 | x_prop
 | iana_prop
 ;

// 3.8.1.1 - Attachment
attach
 : ATTACH attachparam* ( ':' uri 
                       | ';' ENCODING '=' BASE '6' '4' ';' VALUE '=' BINARY ':' binary
                       )
   CRLF
 ;

attachparam
 : ';' fmttypeparam
 | ';' other_param
 ;

// 3.8.1.2 - Categories
categories
 : CATEGORIES catparam* ':' text (',' text)* CRLF
 ;

catparam
 : ';' languageparam
 | ';' other_param
 ;

// 3.8.1.3 - Classification
clazz
 : CLASS (';' other_param)* ':' classvalue CRLF
 ;

classvalue
 : PUBLIC
 | PRIVATE
 | CONFIDENTIAL
 | iana_token
 | X_NAME
 ;

// 3.8.1.4 - Comment
comment
 : COMMENT commparam* ':' text CRLF
 ;

commparam
 : ';' altrepparam
 | ';' languageparam
 | ';' other_param
 ;

// 3.8.1.5 - Description
description
 : DESCRIPTION descparam* ':' text CRLF
 ;

descparam
 : ';' altrepparam
 | ';' languageparam
 | ';' other_param
 ;

// 3.8.1.6 - Geographic Position
geo
 : GEO (';' other_param)* ':' geovalue CRLF
 ;

geovalue
 : float_num ';' float_num
 ;

// 3.8.1.7 - Location
location
 : LOCATION locparam* ':' text CRLF
 ;

locparam
 : ';' altrepparam
 | ';' languageparam
 | ';' other_param
 ;

// 3.8.1.8 - Percent Complete
percent
 : PERCENT_COMPLETE (';' other_param)* ':' integer CRLF
 ;

// 3.8.1.9 - Priority
priority
 : PRIORITY (';' other_param)* ':' priovalue CRLF
 ;

priovalue
 : integer
 ;
               
// 3.8.1.10 - Resources
resources
 : RESOURCES resrcparam* ':' text (',' text)* CRLF
 ;

resrcparam
 : ';' altrepparam
 | ';' languageparam
 | ';' other_param
 ;

// 3.8.1.11 - Status
status
 : STATUS (';' other_param)* ':' statvalue CRLF
 ;

statvalue
 : statvalue_event
 | statvalue_todo
 | statvalue_jour
 ;

statvalue_event
 : TENTATIVE
 | CONFIRMED
 | CANCELLED
 ;

statvalue_todo
 : NEEDS_ACTION
 | COMPLETED
 | IN_PROGRESS
 | CANCELLED
 ;

statvalue_jour
 : DRAFT
 | FINAL
 | CANCELLED
 ;

// 3.8.1.12 - Summary
summary
 : SUMMARY summparam* ':' text CRLF
 ;

summparam
 : ';' altrepparam
 | ';' languageparam
 | ';' other_param
 ;

// 3.8.2.1 - Date-Time Completed
completed
 : COMPLETED (';' other_param)* ':' date_time CRLF
 ;

// 3.8.2.2 - Date-Time End
dtend
 : DTEND dtendparam* ':' date_time_date CRLF
 ;

dtendparam
 : ';' VALUE '=' DATE_TIME
 | ';' VALUE '=' DATE
 | ';' tzidparam
 | ';' other_param
 ;

// 3.8.2.3 - Date-Time Due
due
 : DUE dueparam* ':' date_time_date CRLF
 ;

dueparam
 : ';' VALUE '=' DATE_TIME
 | ';' VALUE '=' DATE
 | ';' tzidparam
 | ';' other_param
 ;

// 3.8.2.4 - Date-Time Start
dtstart
 : DTSTART dtstparam* ':' date_time_date CRLF
 ;

dtstparam
 : ';' VALUE '=' DATE_TIME
 | ';' VALUE '=' DATE
 | ';' tzidparam
 | ';' other_param
 ;

// 3.8.2.5 - Duration
duration
 : DURATION (';' other_param)* ':' dur_value CRLF
 ;

// 3.8.2.6 - Free/Busy Time
freebusy
 : FREEBUSY fbparam* ':' fbvalue CRLF
 ;

fbparam
 : ';' fbtypeparam
 | ';' other_param 
 ;

fbvalue
 : period (',' period)*
 ;

// 3.8.2.7 - Time Transparency
transp
 : TRANSP (';' other_param)* ':' transvalue CRLF
 ;

transvalue
 : OPAQUE
 | TRANSPARENT
 ;

// 3.8.3.1 - Time Zone Identifier
tzid
 : TZID (';' other_param)* ':' '/'? text CRLF
 ;

// 3.8.3.2.  Time Zone Name
tzname
 : TZNAME tznparam* ':' text CRLF
 ;

tznparam
 : ';' languageparam
 | ';' other_param
 ;

// 3.8.3.3 - Time Zone Offset From
tzoffsetfrom
 : TZOFFSETFROM (';' other_param)* ':' utc_offset CRLF
 ;

// 3.8.3.4 - Time Zone Offset To
tzoffsetto
 : TZOFFSETTO (';' other_param)* ':' utc_offset CRLF
 ;

// 3.8.3.5.  Time Zone URL
tzurl
 : TZURL (';' other_param)* ':' uri CRLF
 ;

// 3.8.4.1 - Attendee
attendee
 : ATTENDEE attparam* ':' cal_address CRLF
 ;

attparam
 : ';' cutypeparam
 | ';' memberparam
 | ';' roleparam
 | ';' partstatparam
 | ';' rsvpparam
 | ';' deltoparam
 | ';' delfromparam
 | ';' sentbyparam
 | ';' cnparam
 | ';' dirparam
 | ';' languageparam
 | ';' other_param
 ;

// 3.8.4.2 - Contact
contact
 : CONTACT contparam* ':' text CRLF
 ;

contparam
 : ';' altrepparam
 | ';' languageparam
 | ';' other_param
 ;

// 3.8.4.3 - Organizer
organizer
 : ORGANIZER orgparam* ':' cal_address CRLF
 ;

orgparam
 : ';' cnparam
 | ';' dirparam
 | ';' sentbyparam
 | ';' languageparam
 | ';' other_param
 ;

// 3.8.4.4 - Recurrence ID
recurid
 : RECURRENCE_ID ridparam* ':' date_time_date CRLF
 ;

ridparam
 : ';' VALUE '=' DATE_TIME
 | ';' VALUE '=' DATE
 | ';' tzidparam
 | ';' rangeparam
 | ';' other_param
 ;

// 3.8.4.5.  Related To
related
 : RELATED_TO relparam* ':' text CRLF
 ;

relparam
 : ';' reltypeparam
 | ';' other_param
 ;

// 3.8.4.6 - Uniform Resource Locator
url
 : URL (';' other_param)* ':' uri CRLF
 ;

// 3.8.4.7 - Unique Identifier
uid
 : UID (';' other_param)* ':' text CRLF
 ;

// 3.8.5.1 - Exception Date-Times
exdate
 : EXDATE exdtparam* ':' date_time_date (',' date_time_date)* CRLF
 ;

exdtparam
 : ';' VALUE '=' DATE_TIME
 | ';' VALUE '=' DATE
 | ';' tzidparam
 | ';' other_param
 ;

// 3.8.5.2 - Recurrence Date-Times
rdate
 : RDATE rdtparam* ':' date_time_date (',' date_time_date)* CRLF
 ;

rdtparam
 : ';' VALUE '=' DATE_TIME
 | ';' VALUE '=' DATE
 | ';' VALUE '=' PERIOD
 | ';' tzidparam
 | ';' other_param
 ;

date_time_date
 : date_time
 | date
 ;

// 3.8.5.3 - Recurrence Rule
rrule
 : RRULE (';' other_param)* ':' recur CRLF
 ;

// 3.8.6.1 - Action
action
 : ACTION (';' other_param)* ':' actionvalue CRLF
 ;

actionvalue
 : AUDIO
 | DISPLAY
 | EMAIL
 | iana_token
 | X_NAME
 ;

// 3.8.6.2 - Repeat Count
repeat
 : REPEAT (';' other_param)* ':' integer CRLF
 ;

// 3.8.6.3 - Trigger
trigger
 : TRIGGER trigrel* ':' dur_value CRLF
 | TRIGGER trigabs* ':' date_time CRLF
 ;

trigrel
 : ';' VALUE '='  DURATION
 | ';' trigrelparam
 | ';' other_param
 ;

trigabs
 : ';' VALUE '=' DATE_TIME
 | ';' other_param  
 ;
           
// 3.8.7.1 - Date-Time Created
created
 : CREATED (';' other_param)* ':' date_time CRLF
 ;

// 3.8.7.2 - Date-Time Stamp
dtstamp
 : DTSTAMP (';' other_param)* ':' date_time CRLF
 ;

// 3.8.7.3 - Last Modified
last_mod
 : LAST_MODIFIED (';' other_param)* ':' date_time CRLF
 ;

// 3.8.7.4 - Sequence Number
seq
 : SEQUENCE (';' other_param)* ':' integer CRLF
 ;

// 3.8.8.1 - IANA Properties
iana_prop
 : iana_token (';' icalparameter)* ':' value CRLF
 ;

// 3.8.8.2 - Non-Standard Propertie
x_prop
 : X_NAME (';' icalparameter)* ':' value CRLF
;

// 3.8.8.3 - Request Status
rstatus
 : REQUEST_STATUS rstatparam* ':' statcode ';' text (';' text)?
 ;

rstatparam
 : ';' languageparam
 | ';' other_param
 ;

statcode
 : digit+ '.' digit+ ('.' digit+)?
 ;

param_name
 : iana_token
 | X_NAME
 ;

param_value
 : paramtext
 | quoted_string
 ;

paramtext
 : safe_char*
 ;

quoted_string
 : '"' qsafe_char* '"'
 ;
  
// iCalendar identifier registered with IANA
iana_token
 : (alpha | '-')+
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
 : ALTREP '=' '"' uri '"'
 ;

// 3.2.2
cnparam
 : CN '=' param_value
 ;

// 3.2.3
cutypeparam
 : CUTYPE '=' ( INDIVIDUAL
              | GROUP
              | RESOURCE
              | ROOM
              | UNKNOWN
              | X_NAME
              | iana_token
              )
 ;

// 3.2.4
delfromparam
 : DELEGATED_FROM '=' '"' cal_address '"' (',' '"' cal_address '"')*
 ;

// 3.2.5
deltoparam
 : DELEGATED_TO '=' '"' cal_address '"' (',' '"' cal_address '"')*
 ;

// 3.2.6
dirparam
 : DIR '=' '"' uri '"'
 ;

// 3.2.7
encodingparam
 : ENCODING '=' ( '8' BIT
                | BASE '6' '4'
                )
 ;

// 3.2.8
fmttypeparam
 : FMTTYPE '=' type_name '/' subtype_name
 ;

// 3.2.9
fbtypeparam
 : FBTYPE '=' ( FREE
              | BUSY
              | BUSY_UNAVAILABLE
              | BUSY_TENTATIVE
              | X_NAME
              | iana_token
              )
 ;

// 3.2.10
languageparam
 : LANGUAGE '=' language
 ;

// 3.2.11
memberparam
 : MEMBER '=' '"' cal_address '"' (',' '"' cal_address '"')*
 ;

// 3.2.12
partstatparam
 : PARTSTAT '=' ( partstat_event
                | partstat_todo
                | partstat_jour
                )
 ;

// 3.2.13
rangeparam
 : RANGE '=' THISANDFUTURE
 ;

// 3.2.14
trigrelparam
 : RELATED '=' ( START
               | END
               )
 ;

// 3.2.15
reltypeparam
 : RELTYPE '=' ( PARENT
               | CHILD
               | SIBLING
               | iana_token
               | X_NAME
               )
 ;

// 3.2.16
roleparam
 : ROLE '=' ( CHAIR
            | REQ_PARTICIPANT
            | OPT_PARTICIPANT
            | NON_PARTICIPANT
            | iana_token
            | X_NAME
            )
 ;

// 3.2.17
rsvpparam
 : RSVP '=' ( TRUE
            | FALSE
            )
 ;

// 3.2.18
sentbyparam
 : SENT_BY '=' '"' cal_address '"'
 ;

// 3.2.19
tzidparam
 : TZID '=' '/'? paramtext
 ;

// 3.2.20
valuetypeparam
 : VALUE '=' valuetype
 ;

valuetype
 : BINARY
 | BOOLEAN
 | CAL_ADDRESS
 | DATE
 | DATE_TIME
 | DURATION
 | FLOAT
 | INTEGER
 | PERIOD
 | RECUR
 | TEXT
 | TIME
 | URI
 | UTC_OFFSET
 | X_NAME
 | iana_token
 ;

// 3.3.1 - A "BASE64" encoded character string, as defined by [RFC4648].
binary
 : b_chars b_end?
 ;

b_chars
 : b_char*
 ;

b_end
 : '=' '='?
 ;

// 3.3.2
bool
 : TRUE
 | FALSE
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
 : date K_T time
 ;

// 3.3.6 
dur_value
 : '-' (K_P | NON_KEYWORD) (dur_date | dur_time | dur_week)
 | '+'? (K_P | NON_KEYWORD) (dur_date | dur_time | dur_week)
 ;

// 3.3.7
float_num
 : '-' digits ('.' digits)?
 | '+'? digits ('.' digits)?
 ;

digits
 : digit+
 ;

// 3.3.8
integer
 : '-' digits
 | '+'? digits
 ;

// 3.3.9
period
 : period_explicit
 | period_start
 ;

// 3.3.10
recur
 : recur_rule_part (';' recur_rule_part)*
 ;

// 3.3.11
text
 : (tsafe_char | ':' | '"' | ESCAPED_CHAR)*
 ;

// 3.3.12
time
 : time_hour time_minute time_second K_Z?
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
 : iana_token '=' param_value (',' param_value)*
 ;

// A non-standard, experimental parameter.
x_param
 : X_NAME '=' param_value (',' param_value)*
 ;

// As defined in Section 4.2 of [RFC4288].
type_name
 : reg_name
 | keyword
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
 : NEEDS_ACTION
 | ACCEPTED
 | DECLINED
 | TENTATIVE
 | DELEGATED
 | X_NAME
 | iana_token
 ;

partstat_todo
 : NEEDS_ACTION
 | ACCEPTED
 | DECLINED
 | TENTATIVE
 | DELEGATED
 | COMPLETED
 | IN_PROGRESS
 | X_NAME
 | iana_token
 ;

partstat_jour
 : NEEDS_ACTION
 | ACCEPTED
 | DECLINED
 | X_NAME
 | iana_token
 ;

b_char
 : keyword
 | alpha
 | digit
 | '+'
 | '/'
 ;

date_value
 : date_fullyear date_month date_mday
 ;

date_fullyear
 : digits_2 digits_2
 ;

date_month
 : digits_2
 ;

date_mday
 : digits_2
 ;

time_hour
 : digits_2
 ;

time_minute
 : digits_2
 ;

time_second
 : digits_2
 ;

// "DT" is tokenized as a NON_KEYWORD, check if NON_KEYWORD=="DT"
dur_date
 : digit+ NON_KEYWORD (dur_hour | dur_minute | dur_second)
 | digit+ K_D
 ;

dur_day
 : digit+ K_D
 ;

dur_time
 : K_T? (dur_hour | dur_minute | dur_second)
 ;

dur_week
 : digit+ K_W
 ;

dur_hour
 : digit+ K_H dur_minute?
 ;

dur_minute
 : digit+ K_M dur_second?
 ;

dur_second
 : digit+ K_S
 ;

period_explicit
 : date_time '/' date_time
 ;

period_start
 : date_time '/' dur_value
 ;

recur_rule_part
 : FREQ '=' freq
 | UNTIL '=' enddate
 | COUNT '=' digit+
 | INTERVAL '=' digit+
 | BYSECOND '=' byseclist
 | BYMINUTE '=' byminlist
 | BYHOUR '=' byhrlist
 | BYDAY '=' bywdaylist
 | BYMONTHDAY '=' bymodaylist
 | BYYEARDAY '=' byyrdaylist
 | BYWEEKNO '=' bywknolist
 | BYMONTH '=' bymolist
 | BYSETPOS '=' bysplist
 | WKST '=' weekday
 ;

freq
 : SECONDLY
 | MINUTELY
 | HOURLY
 | DAILY
 | WEEKLY
 | MONTHLY
 | YEARLY
 ;

enddate
 : date 
 | date_time
 ;

byseclist
 : digits_1_2 (',' digits_1_2)*
 ;

byminlist
 : digits_1_2 (',' digits_1_2)*
 ;

byhrlist
 : digits_1_2 (',' digits_1_2)*
 ;

bywdaylist
 : weekdaynum (',' weekdaynum)*
 ;

weekdaynum
 : (('+' | '-')? digits_1_2)? weekday
 ;

weekday
 : SU
 | MO
 | TU
 | WE
 | TH
 | FR
 | SA
 ;

bymodaylist
 : monthdaynum (',' monthdaynum)*
 ;

monthdaynum
 : ('+' | '-')? digits_1_2
 ;

byyrdaylist
 : yeardaynum (',' yeardaynum)*
 ;

yeardaynum
 : ('+' | '-')? ordyrday
 ;

ordyrday
 : digit (digit digit?)?
 ;

bywknolist
 : weeknum (',' weeknum)*
 ;

weeknum
 : ('+' | '-')? digits_1_2
 ;

bymolist
 : digits_1_2 (',' digits_1_2)*
 ;

bysplist
 : yeardaynum (',' yeardaynum)*
 ;

digits_2
 : digit digit
 ;

digits_1_2
 : digit digit?
 ;

// Any character except CONTROL, DQUOTE, ";", ":", ","
safe_char
 : keyword
 | alpha
 | digit
 | X21
 | X23
 | X24
 | X25
 | X26
 | X27
 | X28
 | X29
 | X2A
 | X2B
 | X2D
 | X2E
 | X2F
 | X3C
 | X3D
 | X3E
 | X3F
 | X40
 | X5B
 | X5C
 | X5D
 | X5E
 | X5F
 | X60
 | X7B
 | X7C
 | X7D
 | X7E
 | WSP
 | NON_US_ASCII
 ;

// Any textual character
value_char
 : keyword
 | alpha
 | digit
 | X21
 | X22
 | X23
 | X24
 | X25
 | X26
 | X27
 | X28
 | X29
 | X2A
 | X2B
 | X2C
 | X2D
 | X2E
 | X2F
 | X3A
 | X3B
 | X3C
 | X3D
 | X3E
 | X3F
 | X40
 | X5B
 | X5C
 | X5D
 | X5E
 | X5F
 | X60
 | X7B
 | X7C
 | X7D
 | X7E
 | WSP
 | NON_US_ASCII
 ;

// Any character except CONTROL and DQUOTE
qsafe_char
 : keyword
 | alpha
 | digit
 | X21
 | X23
 | X24
 | X25
 | X26
 | X27
 | X28
 | X29
 | X2A
 | X2B
 | X2C
 | X2D
 | X2E
 | X2F
 | X3A
 | X3B
 | X3C
 | X3D
 | X3E
 | X3F
 | X40
 | X5B
 | X5C
 | X5D
 | X5E
 | X5F
 | X60
 | X7B
 | X7C
 | X7D
 | X7E
 | WSP
 | NON_US_ASCII
 ;

// Any character except CONTROLs not needed by the current
// character set, DQUOTE, ";", ":", "\", ","
tsafe_char
 : keyword
 | alpha
 | digit
 | X21
 | X23
 | X24
 | X25
 | X26
 | X27
 | X28
 | X29
 | X2A
 | X2B
 | X2D
 | X2E
 | X2F
 | X3C
 | X3D
 | X3E
 | X3F
 | X40
 | X5B
 | X5D
 | X5E
 | X5F
 | X60
 | X7B
 | X7C
 | X7D
 | X7E
 | NON_US_ASCII
 | WSP
 ;

time_numzone
 : ('+' | '-') time_hour time_minute time_second?
 ;

reg_name_char
 : alpha
 | digit
 | '!'
 | '#'
 | '$'
 | '&'
 | '.'
 | '+'
 | '-'
 | '^'
 | '_'
 ;

language_char
 : alpha
 | digit
 | '-'
 | ':'
 | WSP
 ;

keyword
 : ACCEPTED
 | ACTION
 | ADDRESS
 | ALTREP
 | ATTACH
 | ATTENDEE
 | AUDIO
 | BASE
 | BEGIN
 | BINARY
 | BIT
 | BOOLEAN
 | BUSY
 | BUSY_UNAVAILABLE
 | BUSY_TENTATIVE
 | BYDAY
 | BYHOUR
 | BYMINUTE
 | BYMONTH
 | BYMONTHDAY
 | BYSECOND
 | BYSETPOS
 | BYWEEKNO
 | BYYEARDAY
 | CAL_ADDRESS
 | CALSCALE
 | CANCELLED
 | CATEGORIES
 | CHAIR
 | CHILD
 | CLASS
 | CN
 | COMMENT
 | COMPLETED
 | CONFIDENTIAL
 | CONFIRMED
 | CONTACT
 | COUNT
 | CREATED
 | CUTYPE
 | DAILY
 | DATE
 | DATE_TIME
 | DAYLIGHT
 | DECLINED
 | DELEGATED
 | DELEGATED_FROM
 | DELEGATED_TO
 | DESCRIPTION
 | DIR
 | DISPLAY
 | DRAFT
 | DTEND
 | DTSTAMP
 | DTSTART
 | DUE
 | DURATION
 | EMAIL
 | ENCODING
 | END
 | EXDATE
 | FALSE
 | FBTYPE
 | FINAL
 | FLOAT
 | FMTTYPE
 | FR
 | FREE
 | FREEBUSY
 | FREQ
 | GEO
 | GREGORIAN
 | GROUP
 | HOURLY
 | IN_PROGRESS
 | INDIVIDUAL
 | INTEGER
 | INTERVAL
 | K_D
 | K_H
 | K_M
 | K_N
 | K_P
 | K_S
 | K_T
 | K_W
 | K_Z
 | LANGUAGE
 | LAST_MODIFIED
 | LOCATION
 | MEMBER
 | METHOD
 | MINUTELY
 | MO
 | MONTHLY
 | NEEDS_ACTION
 | NON_PARTICIPANT
 | OPAQUE
 | OPT_PARTICIPANT
 | ORGANIZER
 | PARENT
 | PARTICIPANT
 | PARTSTAT
 | PERCENT_COMPLETE
 | PERIOD
 | PRIORITY
 | PRIVATE
 | PROCESS
 | PRODID
 | PUBLIC
 | RANGE
 | RDATE
 | RECUR
 | RECURRENCE_ID
 | RELAT
 | RELATED
 | RELATED_TO
 | RELTYPE
 | REPEAT
 | REQ_PARTICIPANT
 | REQUEST_STATUS
 | RESOURCE
 | RESOURCES
 | ROLE
 | ROOM
 | RRULE
 | RSVP
 | SA
 | SECONDLY
 | SENT_BY
 | SEQUENCE
 | SIBLING
 | STANDARD
 | START
 | STATUS
 | SU
 | SUMMARY
 | TENTATIVE
 | TEXT
 | TH
 | THISANDFUTURE
 | TIME
 | TRANSP
 | TRANSPARENT
 | TRIGGER
 | TRUE
 | TU
 | TZID
 | TZNAME
 | TZOFFSETFROM
 | TZOFFSETTO
 | TZURL
 | UID
 | UNKNOWN
 | UNTIL
 | URI
 | URL
 | UTC_OFFSET
 | VALARM
 | VALUE
 | VCALENDAR
 | VERSION
 | VEVENT
 | VFREEBUSY
 | VJOURNAL
 | VTIMEZONE
 | VTODO
 | WE
 | WEEKLY
 | WKST
 | YEARLY
 | X_NAME
 ;

// the digits: 0..9
digit
 : X30 | X31 | X32 | X33 | X34 | X35 | X36 | X37 | X38 | X39
 ;

// Any alpha char, excluding keywords.
alpha
 : K_D
 | K_H
 | K_M
 | K_N
 | K_P
 | K_S
 | K_T
 | K_W
 | K_Z
 | NON_KEYWORD
 ;

////////////////////////////// lexer rules //////////////////////////////
ACCEPTED : A C C E P T E D;
ACTION : A C T I O N;
ADDRESS : A D D R E S S;
ALTREP : A L T R E P;
ATTACH : A T T A C H;
ATTENDEE : A T T E N D E E;
AUDIO : A U D I O;
BASE : B A S E;
BEGIN : B E G I N;
BINARY : B I N A R Y;
BIT : B I T;
BOOLEAN : B O O L E A N;
BUSY : B U S Y;
BUSY_UNAVAILABLE : B U S Y '-' U N A V A I L A B L E;
BUSY_TENTATIVE : B U S Y '-' T E N T A T I V E;
BYDAY : B Y D A Y;
BYHOUR : B Y H O U R;
BYMINUTE : B Y M I N U T E;
BYMONTH : B Y M O N T H;
BYMONTHDAY : B Y M O N T H D A Y;
BYSECOND : B Y S E C O N D;
BYSETPOS : B Y S E T P O S;
BYWEEKNO : B Y W E E K N O;
BYYEARDAY : B Y Y E A R D A Y;
CAL_ADDRESS : C A L '-' A D D R E S S;
CALSCALE : C A L S C A L E;
CANCELLED : C A N C E L L E D;
CATEGORIES : C A T E G O R I E S;
CHAIR : C H A I R;
CHILD : C H I L D;
CLASS : C L A S S;
CN : C N;
COMMENT : C O M M E N T;
COMPLETED : C O M P L E T E D;
CONFIDENTIAL : C O N F I D E N T I A L;
CONFIRMED : C O N F I R M E D;
CONTACT : C O N T A C T;
COUNT : C O U N T;
CREATED : C R E A T E D;
CUTYPE : C U T Y P E;
DAILY : D A I L Y;
DATE : D A T E;
DATE_TIME : D A T E '-' T I M E;
DAYLIGHT : D A Y L I G H T;
DECLINED : D E C L I N E D;
DELEGATED : D E L E G A T E D;
DELEGATED_FROM : D E L E G A T E D '-' F R O M;
DELEGATED_TO : D E L E G A T E D '-' T O;
DESCRIPTION : D E S C R I P T I O N;
DIR : D I R;
DISPLAY : D I S P L A Y;
DRAFT : D R A F T;
DTEND : D T E N D;
DTSTAMP : D T S T A M P;
DTSTART : D T S T A R T;
DUE : D U E;
DURATION : D U R A T I O N;
EMAIL : E M A I L;
ENCODING : E N C O D I N G;
END : E N D;
EXDATE : E X D A T E;
FALSE : F A L S E;
FBTYPE : F B T Y P E;
FINAL : F I N A L;
FLOAT : F L O A T;
FMTTYPE : F M T T Y P E;
FR : F R;
FREE : F R E E;
FREEBUSY : F R E E B U S Y;
FREQ : F R E Q;
GEO : G E O;
GREGORIAN : G R E G O R I A N;
GROUP : G R O U P;
HOURLY : H O U R L Y;
IN_PROGRESS : I N '-' P R O G R E S S;
INDIVIDUAL : I N D I V I D U A L;
INTEGER : I N T E G E R;
INTERVAL : I N T E R V A L;
K_D : D;
K_H : H;
K_M : M;
K_N : N;
K_P : P;
K_S : S;
K_T : T;
K_W : W;
K_Z : Z;
LANGUAGE : L A N G U A G E;
LAST_MODIFIED : L A S T '-' M O D I F I E D;
LOCATION : L O C A T I O N;
MEMBER : M E M B E R;
METHOD : M E T H O D;
MINUTELY : M I N U T E L Y;
MO : M O;
MONTHLY : M O N T H L Y;
NEEDS_ACTION : N E E D S '-' A C T I O N;
NON_PARTICIPANT : N O N '-' P A R T I C I P A N T;
OPAQUE : O P A Q U E;
OPT_PARTICIPANT : O P T '-' P A R T I C I P A N T;
ORGANIZER : O R G A N I Z E R;
PARENT : P A R E N T;
PARTICIPANT : P A R T I C I P A N T;
PARTSTAT : P A R T S T A T;
PERCENT_COMPLETE : P E R C E N T '-' C O M P L E T E;
PERIOD : P E R I O D;
PRIORITY : P R I O R I T Y;
PRIVATE : P R I V A T E;
PROCESS : P R O C E S S;
PRODID : P R O D I D;
PUBLIC : P U B L I C;
RANGE : R A N G E;
RDATE : R D A T E;
RECUR : R E C U R;
RECURRENCE_ID : R E C U R R E N C E '-' I D;
RELAT : R E L A T;
RELATED : R E L A T E D;
RELATED_TO : R E L A T E D '-' T O;
RELTYPE : R E L T Y P E;
REPEAT : R E P E A T;
REQ_PARTICIPANT : R E Q '-' P A R T I C I P A N T;
REQUEST_STATUS : R E Q U E S T '-' S T A T U S;
RESOURCE : R E S O U R C E;
RESOURCES : R E S O U R C E S;
ROLE : R O L E;
ROOM : R O O M;
RRULE : R R U L E;
RSVP : R S V P;
SA : S A;
SECONDLY : S E C O N D L Y;
SENT_BY : S E N T '-' B Y;
SEQUENCE : S E Q U E N C E;
SIBLING : S I B L I N G;
STANDARD : S T A N D A R D;
START : S T A R T;
STATUS : S T A T U S;
SU : S U;
SUMMARY : S U M M A R Y;
TENTATIVE : T E N T A T I V E;
TEXT : T E X T;
TH : T H;
THISANDFUTURE : T H I S A N D F U T U R E;
TIME : T I M E;
TRANSP : T R A N S P;
TRANSPARENT : T R A N S P A R E N T;
TRIGGER : T R I G G E R;
TRUE : T R U E;
TU : T U;
TZID : T Z I D;
TZNAME : T Z N A M E;
TZOFFSETFROM : T Z O F F S E T F R O M;
TZOFFSETTO : T Z O F F S E T T O;
TZURL : T Z U R L;
UID : U I D;
UNKNOWN : U N K N O W N;
UNTIL : U N T I L;
URI : U R I;
URL : U R L;
UTC_OFFSET : U T C '-' O F F S E T;
VALARM : V A L A R M;
VALUE : V A L U E;
VCALENDAR : V C A L E N D A R;
VERSION : V E R S I O N;
VEVENT : V E V E N T;
VFREEBUSY : V F R E E B U S Y;
VJOURNAL : V J O U R N A L;
VTIMEZONE : V T I M E Z O N E;
VTODO : V T O D O;
WE : W E;
WEEKLY : W E E K L Y;
WKST : W K S T;
YEARLY : Y E A R L Y;

// Reserved for experimental use.
X_NAME
 : X ([a-zA-Z0-9] [a-zA-Z0-9] [a-zA-Z0-9]+ '-')? [a-zA-Z-]+
 ;

NON_KEYWORD
  : [a-zA-Z] ([a-zA-Z-]* [a-zA-Z])?
  ;

LINE_FOLD
 : CRLF WSP -> skip
 ;

WSP
 : ' '
 | '\t'
 ;

ESCAPED_CHAR
 : '\\' '\\'
 | '\\' ';'
 | '\\' ','
 | '\\' K_N
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
X5B : '[';
X5C : '\\';
X5D : ']';
X5E : '^';
X5F : '_';
X60 : '`';
X7B : '{';
X7C : '|';
X7D : '}';
X7E : '~';

NON_US_ASCII
 : .
 ;

// Upper- and lowercase letters
fragment A : [aA];
fragment B : [bB];
fragment C : [cC];
fragment D : [dD];
fragment E : [eE];
fragment F : [fF];
fragment G : [gG];
fragment H : [hH];
fragment I : [iI];
fragment J : [jJ];
fragment K : [kK];
fragment L : [lL];
fragment M : [mM];
fragment N : [nN];
fragment O : [oO];
fragment P : [pP];
fragment Q : [qQ];
fragment R : [rR];
fragment S : [sS];
fragment T : [tT];
fragment U : [uU];
fragment V : [vV];
fragment W : [wW];
fragment X : [xX];
fragment Y : [yY];
fragment Z : [zZ];
