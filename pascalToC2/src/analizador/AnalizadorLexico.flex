package analizador;
import java_cup.runtime.*;

%%

%class AnalizadorLexico
%line
%column
%xstate COM
%xstate CONSTANTES_LITERALES
%cup

%{
     StringBuffer aux = new StringBuffer();
     int auxLinea = 0;
     int auxColumna = 0;
%}

letras =[A-Za-z]+
identificador ={letras}({letras}|[0-9]+|"_")*
numeric_real_const = [+|-]? [0-9]+ "." [0-9]+  | [0-9]+ [e|E] [+|-]? [0-9]+  | [+|-]? [0-9]+ "." [0-9]+ [e|E] [+|-]? [0-9]+
string_const=[\u0027][^\n\r]+[\u0027]
numeric_integer_const = [+|-]? [0-9]+
%%

/*DETECCIÓN DEL IF, DO UNTIL, WHILE y FOR*/
"begin" 	{return new Symbol(sym.BEGIN);}
"end" 		{return new Symbol(sym.END);}
"program" 	{return new Symbol(sym.PROGRAM);}
"var" 		{return new Symbol(sym.VAR);}
"const" 	{return new Symbol(sym.CONST);}
"function" 	{return new Symbol(sym.FUNCTION);}
"procedure"	{return new Symbol(sym.PROCEDURE);}
"INTEGER"	{return new Symbol(sym.INTEGER);}
"REAL"		{return new Symbol(sym.REAL);}
";" 		{return new Symbol(sym.POINT_SEMICOLON);}
"." 		{return new Symbol(sym.POINT);}
"," 		{return new Symbol(sym.SEMICOLON);}
":=" 		{return new Symbol(sym.DOUBLEPOINTEQUAL);}
"=" 		{return new Symbol(sym.EQUAL);}
":" 		{return new Symbol(sym.DOUBLE_COLON);}
"(" 		{return new Symbol(sym.OPEN_PARENTESIS);}
")" 		{return new Symbol(sym.CLOSE_PARENTESIS);}
"+" 		{return new Symbol(sym.PLUS);}
"-" 		{return new Symbol(sym.MINUS);}
"*" 		{return new Symbol(sym.MULTIPLICACION);}
"div" 		{return new Symbol(sym.DIV);}
"mod" 		{return new Symbol(sym.MOD);}


/*INDENTIFICADORES*/
{identificador}  		{return new Symbol(sym.IDENT, yyline + 1, yycolumn +1, yytext());}


/*CONSTANTES*/
{numeric_real_const}  	{return new Symbol(sym.NUMERIC_REAL_CONST, new String(yytext())); } 
{numeric_integer_const} {return new Symbol(sym.NUMERIC_INTEGER_CONST, new String(yytext())); } 
{string_const} 			{return new Symbol(sym.STRING_CONST, new String(yytext())); } 



/*DETECCIÓN DE SALTO DE LÍNEA Y ESPACIO EN BLANCO*/

[ \t] | \n | \r | \r\n {;}

/*DETECCIÓN DE COMENTARIOS */

"{"[^\n]*"}" {;}

"(*" {yybegin(COM);
      auxLinea = yyline +1;
      auxColumna = yycolumn +1;}
<COM> [^] {;}
<COM><<EOF>> {
	System.err.println("Comentario sin cerrar, empieza en la linea " + (auxLinea) + " y en la columna " + (auxColumna));
	System.exit(0);
}
<COM> "*)" {
	yybegin(YYINITIAL);
}

/*DETECCIÓN DE UN CARÁCTER ERRÓNEO*/

. { System.err.println("Error lexico: caracter no reconocido <" + yytext() + "> en la linea " + (yyline+1) + " y columna " + (yycolumn+1));}






































