package practica.analyzer;
import java_cup.runtime.*;
%%
%class rules
%unicode
%cup

%{
	private int commentCountMultilinea = 0;
%}

%state COMENTARIO
espacio=([\ \t\b\r\n\f])+
numeric_integer_const = [+|-]? [0-9]+
punto=[+|-]?([0-9]+)"."([0-9]+)
exponencial=([0-9]+)[e|E][+|-]?[0-9]+
mixto= punto [e|E][+|-]?[0-9]+
numeric_real_const =({punto}|{exponencial}|{mixto})
string_const=[\u0027][^\n\r]+[\u0027]
identifier = [a-zA-Z] [a-zA-Z0-9 \u005f]+ [^\u00f1]+ [^\u00d1]+ [a-zA-Z0-9  \u005f]+

salto=([\n])+
comentario_linea="{"[^\n\r]+"}"
ini_comentario_multilinea="(*"
fin_comentario_multilinea="*)"

%%

/*REGLAS LEXICAS*/
<YYINITIAL> 
{


	  {ini_comentario_multilinea}   
	  { 
	  System.out.print("@");
	  	yybegin(COMENTARIO); 
	  	commentCountMultilinea = commentCountMultilinea + 1; 
	  }

   //terminales
   	"program" {return symbol(sym.PRG);}
	"funcion" {return symbol(sym.FUNCION);}
	"procedure"	{return symbol(sym.PROCEDURE);}
	"begin" {return symbol(sym.BEGIN);}
	"end" {return symbol(sym.END);}
	"var" {return symbol(sym.VAR);}
	"real" {return symbol(sym.REAL);}
	"const" {return symbol(sym.CONST);}
	"return" {return symbol(sym.RETURN);}
	"(" {return symbol(sym.OPEN_PARENTESIS);}
	")" {return symbol(sym.CLOSE_PARENTESIS);}
	";" {return symbol(sym.POINT_SEMICOLON);}
	"," {return symbol(sym.SEMICOLON);}
	"=" {return symbol(sym.EQUAL);}
	"+" {return symbol(sym.PLUS);}
	"-" {return symbol(sym.MINUS);}
	"*" {return symbol(sym.MULTIPLICACION);}
	":" {return symbol(sym.DOUBLE_COLON);}
	":=" {return symbol(sym.DOUBLE_COLON_EQUAL);}
	"div" {return symbol(sym.DIV);}
	"mod" {return symbol(sym.MOD);}
	"void" {return symbol(sym.VOID);}
	"int" {return symbol(sym.INT);}
	"float" {return symbol(sym.FLOAT);}
	"if" {return symbol(sym.IF);}
	"then" {return symbol(sym.THEN);}
	"else" {return symbol(sym.ELSE);}
	"while" {return symbol(sym.WHILE);}
	"do" {return symbol(sym.DO);}
	"repat" {return symbol(sym.REPEAT);}
	"util" {return symbol(sym.UTIL);}
	"for" {return symbol(sym.FOR);}
	"to" {return symbol(sym.TO);}
	"downto" {return symbol(sym.DOWNTO);}
	"or" {return symbol(sym.OR);}
	"and" {return symbol(sym.AND);}
	"not" {return symbol(sym.NOT);}	
	"<" {System.out.println(return symbol(sym.GRETTER);}
	">" {System.out.println(return symbol(sym.LESS);}
	">=" {System.out.println(return symbol(sym.GRETTER_EQUAL);}
	"<=" {System.out.println(return symbol(sym.LESS_EQUAL);}
	"+=" {System.out.println(return symbol(sym.PLUS_EQUAL);}
	"-=" {System.out.println(return symbol(sym.MINUS_EQUAL);}
	"*=" {System.out.println(return symbol(sym.MULTIPLICACION_EQUAL);}
	"/=" {System.out.println(return symbol(sym.DIV_EQUAL);}
	//tokens 
	{identifier} {return symbol(sym.IDENTIFIER, new java.lang.String(yytext())); } 
	{numeric_real_const}  {return symbol(sym.NUMERIC_REAL_CONST, new java.lang.String(yytext())); } 
	{numeric_integer_const}  {return symbol(sym.NUMERIC_INTEGER_CONST, new java.lang.String(yytext())); } 
	{string_const} {return symbol(sym.STRING_CONST, new java.lang.String(yytext())); } 
	{fin_comentario_multilinea} {return symbol(sym.END_COMENT, new java.lang.String(yytext())); }
	{comentario_linea}  {return symbol(sym.ID, new java.lang.String(yytext())); } 
	{salto} {return symbol(sym.JUMP, new java.lang.String(yytext())); } 
	{espacio} {}
	[^] {return symbol(sym.PROGRAM);}
}



<COMENTARIO>    
{
    {fin_comentario_multilinea} 
    {
    	commentCountMultilinea--; 
    	if (commentCountMultilinea == 0) 
    	{ 
    	System.out.print("comentario");
    		yybegin(YYINITIAL);
    	}
    }
    [^] { }
}



