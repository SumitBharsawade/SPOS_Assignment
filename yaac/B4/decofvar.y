%{
#include<stdio.h>
#include "y.tab.h"
%}
%token ID BUILTIN SC COMMA OPEN_SQ NUM CLOSE_SQ EQ NEW
%%
start	:	BUILTIN varlist SC {printf("Declaration is valid");}
	|	BUILTIN OPEN_SQ CLOSE_SQ ID EQ NEW BUILTIN OPEN_SQ NUM CLOSE_SQ SC {printf("Declaration is valid");}
	|	varlist:varlist COMMA ID|ID;
%%

int yywrap()
{
return 1;
}
main()
{
	printf("\nEnter a statement : ");
	yyparse();
}
yyerror(char *s)
{
   fprintf(stderr,"%s\n",s);
}

/*
admin1@admin1:~/shubha/assgnb4$ lex decofvar.l
admin1@admin1:~/shubha/assgnb4$ yacc -d decofvar.y
admin1@admin1:~/shubha/assgnb4$ gcc lex.yy.c y.tab.c
admin1@admin1:~/shubha/assgnb4$ ./a.out

Enter a statement : int b;
 Declaration is validadmin1@admin1:~/shubha/assgnb4$ ./a.out

Enter a statement : String[] a=new String[10];
  Declaration is validadmin1@admin1:~/shubha/assgnb4$*/
