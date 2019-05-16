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
numeric_integer_const = [+|-]? [0-9]+
punto=[+|-]?([0-9]+)"."([0-9]+)
exponencial=([0-9]+)[e|E][+|-]?[0-9]+
mixto= punto [e|E][+|-]?[0-9]+
numeric_real_const =({punto}|{exponencial}|{mixto})

%%

/*DETECCIÓN DEL IF, DO UNTIL, WHILE y FOR*/
"program" 	{return new Symbol(sym.PROGRAM);}
";" {return new Symbol(sym.POINT_SEMICOLON);}



/*DETECCIÓN DE EXPRESIONES CONDICIONALES*/


/* DETECCIÓN DEL DEFINE */

/*DETECCIÓN DE TIPOS */



/*INDENTIFICADORES*/
{identificador} { return new Symbol(sym.IDENT, yyline + 1, yycolumn +1, yytext());}

/*CONSTANTES ENTERAS*/

/*CONSTANTES LITERALES*/

/*DETECCIÓN DE COMENTARIOS */


/* DETECCIÓN DE LLAVES */


/* DETECCIÓN DE PARÉNTESIS */





/*DETECCIÓN DE LA COMA */



/*DETECCIÓN DE ;*/



/*DETECCIÓN DE SALTO DE LÍNEA Y ESPACIO EN BLANCO*/

[ \t] | \n | \r | \r\n {;}

/*DETECCIÓN DE UN CARÁCTER ERRÓNEO*/

. { System.err.println("Error lexico: caracter no reconocido <" + yytext() + "> en la linea " + (yyline+1) + " y columna " + (yycolumn+1));}






































