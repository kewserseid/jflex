%%

%unicode 12.0
%public
%class UnicodeScripts_12_0_extensions_14

%type int
%standalone

%include ../../resources/common-unicode-all-enumerated-property-defined-values-only-java

%%

<<EOF>> { printOutput(); return 1; }
\p{Script_Extensions:Nandinagari} { setCurCharPropertyValue("Script_Extensions:Nandinagari"); }
[^] { }
