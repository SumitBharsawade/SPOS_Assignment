%{
#include<stdio.h>
%}

%%
[!@#$%^&*]+ printf("\n\tSpecial Charst\t: \t%s",yytext);
char|bool|int|float|public|static|void|import|java|package|class|main|args|String|throws|new|if|else|do|while 	printf("\n\tKeyword\t: \t%s",yytext);
[-,+]?[0-9]+ printf("\n\tConstants\t: \t%s",yytext);
[,.'"]+ printf("\n\tPunctuation Chars\t: \t%s",yytext);
[<>=]	printf("\n\tBinary operators\t: \t%s",yytext);
[a-zA-Z]+ printf("\n\tIdentifiers\t: \t%s",yytext);
\{|\}|\(|\)|\[|\]	printf("\n\tBrackets: \t%s",yytext);
;	printf("\n\tTerminator\t: \t%s",yytext);
%%

int main()
{
	yyin= fopen("sample.java","r");
	yylex();
	return 0;
}
int yywrap()
{
	return 1;
}
