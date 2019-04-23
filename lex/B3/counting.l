/*Write a program using Lex specification to implement lexical analysis phase of compiler to count no.of words, lines, characters of a given input file.*/

%{
	int t=0,s=0,w=0,c=0,nl=0,n=0,d=0,sp=0;
%}
%%
[\t] {t++;}
[\n] {nl++;}
[ ] {s++;}
[0-9]+ {n++;d+=yyleng;}
[a-zA-Z]+ {w++;c+=yyleng;}
[^ \t\n] {sp++;}

%%

int main()
{
	char fnm[20];
	printf("Enter the File name:");
	scanf("%s",&fnm);
	yyin=fopen(fnm,"r");
	
	if(yyin==NULL)
	{
		printf("File not found.....");
	}
	yylex();
	printf("\nNumbers=%d\nDigits=%d\ntab=%d\nCharcter=%d\nWord=%d\nspace=%d\nnew line=%d\nspecial symbols=%d",n,d,t,c,w,s,nl,sp);
	return 0;
}
int yywrap()
{
	return 1;
}
