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

/*DETECCI�N DEL IF, DO UNTIL, WHILE y FOR*/
"program" 	{return new Symbol(sym.PROGRAM);}
";" {return new Symbol(sym.POINT_SEMICOLON);}



/*DETECCI�N DE EXPRESIONES CONDICIONALES*/


/* DETECCI�N DEL DEFINE */

/*DETECCI�N DE TIPOS */



/*INDENTIFICADORES*/
{identificador} { return new Symbol(sym.IDENT, yyline + 1, yycolumn +1, yytext());}

/*CONSTANTES ENTERAS*/

/*CONSTANTES LITERALES*/

/*DETECCI�N DE COMENTARIOS */


/* DETECCI�N DE LLAVES */


/* DETECCI�N DE PAR�NTESIS */





/*DETECCI�N DE LA COMA */



/*DETECCI�N DE ;*/



/*DETECCI�N DE SALTO DE L�NEA Y ESPACIO EN BLANCO*/

[ \t] | \n | \r | \r\n {;}

/*DETECCI�N DE UN CAR�CTER ERR�NEO*/

. { System.err.println("Error lexico: caracter no reconocido <" + yytext() + "> en la linea " + (yyline+1) + " y columna " + (yycolumn+1));}






































