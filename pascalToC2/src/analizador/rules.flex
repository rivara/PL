package analizador;
import java_cup.runtime.*;
%%
%class rules
%unicode
%line
%column
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
	  	yybegin(COMENTARIO); 
	  	commentCountMultilinea = commentCountMultilinea + 1; 
	  }

   //terminales
  
   	"program" {return new Symbol(sym.PROGRAM);}
	"begin" {return new Symbol(sym.BEGIN);}
	"end" {return new Symbol(sym.END);}
	"var" {return new Symbol(sym.VAR);}
	"const" {return new Symbol(sym.CONST);}
	"(" {return new Symbol(sym.OPEN_PARENTESIS);}
	")" {return new Symbol(sym.CLOSE_PARENTESIS);}
	";" {return new Symbol(sym.POINT_SEMICOLON);}
	"," {return new Symbol(sym.SEMICOLON);}
	"=" {return new Symbol(sym.EQUAL);}
	"+" {return new Symbol(sym.PLUS);}
	"-" {return new Symbol(sym.MINUS);}
	"*" {return new Symbol(sym.MULTIPLICACION);}
	":" {return new Symbol(sym.DOUBLE_COLON);}
	":=" {return new Symbol(sym.DOUBLE_COLON_EQUAL);}
	"div" {return new Symbol(sym.DIV);}
	"mod" {return new Symbol(sym.MOD);}
	
	//tokens 
	{identifier} {return new Symbol(sym.IDENTIFIER, new String(yytext())); } 
	{numeric_real_const}  {return new Symbol(sym.NUMERIC_REAL_CONST, new String(yytext())); } 
	{numeric_integer_const}  {return new Symbol(sym.NUMERIC_INTEGER_CONST, new String(yytext())); } 
	{string_const} {return new Symbol(sym.STRING_CONST, new String(yytext())); } 
	{fin_comentario_multilinea} {return new Symbol(sym.END_COMENT, new String(yytext())); }
	{comentario_linea}  {return new Symbol(sym.IDENTIFIER, new String(yytext())); } 
	{espacio} {}
	//
	[^] {return new Symbol(sym.PROGRAM);}
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



