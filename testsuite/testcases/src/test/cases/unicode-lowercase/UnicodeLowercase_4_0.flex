%%

%unicode 4.0
%public
%class UnicodeLowercase_4_0

%type int
%standalone

%include ../../resources/common-unicode-all-enumerated-property-java

%%

<<EOF>> { printOutput(); return 1; }
[:lowercase:] { setCurCharPropertyValue("Lowercase"); }
[^[:lowercase:]] { setCurCharPropertyValue("Not-Lowercase"); }
