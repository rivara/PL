package analizador;

import java.io.FileNotFoundException;

public class Main {

	// TODO Auto-generated method stu
	// String codigo = "program Hello;\r\n" + "begin\r\n" + " writeln ('Hello,
	// world.');\r\n" + " readln;\r\n"
	// + "end.";
	// comprueba que el codigo este bien
	// analizador.checking(codigo);
	// si checking es igual a nulo no tiene errores y se puede traducir
	// traduce

	// TRADUCCIOn
	// private static void translatror(sintactico sintaxis) {
	// TODO Auto-generated method stub

	// ArrayList<String> codigo = new ArrayList<String>();
	// LIBRERIAS STANDARD
	// Programa p = this.checking(code);
	// System.out.println(p.identificador);
	// si program es diferente a vacio no teine erroeres
	// codigo.add("#include <stdio.h>" + p.identificador);
	// PROGRAM ::= DEFINES PARTES

	// DEFINES ::= LAMBDA | "#define" ident CTES DEFINES
	// SubFuncion subfuncion = new SubFuncion();
	// int cuentaCte = subfuncion.cteLista.size();/// add(simbolo);
	// subfuncion.cteLista.addAll(cl.cteLista);

	/*
	 * if (cuentaCte == 0) { codigo.add(""); } else // declaracion de variables
	 * 
	 * { // int cuentaCte=subfuncion.cteLista.ge;/// add(simbolo);
	 * 
	 * for (int i = 0; i < cuentaCte; i++) {
	 * System.out.println(subfuncion.cteLista.get(i)); }
	 */

	// }

	// forarch(CTELIST)
	// codigo.add("#define");
	// codigo.add("CTELIST.IDENTIFICADOR");
	// codigo.add("CTELIST.SIMPLEVALUE");

	// CTES ::= constint | constfloat | constlit

	// PARTES ::= PART PARTES | PART
	// PART ::= TYPE RESTPART
	// RESTPART ::= ident "(" LISTPARAM ")" BLQ
	// | ident "(" "void" ")" BLQ
	// BLQ ::= "{" SENTLIST "}"
	// LISTPARAM ::= LISTPARAM "," TYPE ident | TYPE ident
	// TYPE ::= "void" | "int" | "float"

	// String fin = "";

	// return fin;
//	}

	public static void main(String[] args) {

		String one = args[0];
		if (args.length == 0) {
			System.out.println("Es obligatorio pasar la ruta del archivo de prueba como parámetro");
		} else {
			rules lexico = null;
			try {
				lexico = new rules(new java.io.FileReader(args[0]));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			@SuppressWarnings("deprecation")
			sintactico sintaxis = new sintactico(lexico);
			try {
				Programa p = (Programa) sintaxis.parse().value;
				System.out.println(p.programa);

				Funcion f = (Funcion) sintaxis.parse().value;
				// f.formal_paramLista

				// System.out.println(p.abreParenteis);

				/// ESTA ES LA INSTANCIA DONDE CON LA SINTAXIS CARGADA GENERO LA TARDUCCION
				// translatror(sintaxis);

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
////////////////////////////// TRADUCCION Y GENERACION DEL FICHERO

			/*
			 * try {
			 * 
			 * FileInputStream fstream = new FileInputStream(args[0]); File file = new
			 * File("prueba.c"); FileOutputStream fop = new FileOutputStream(file); if
			 * (!file.exists()) { file.createNewFile(); } // LEO EL FICHERO String content =
			 * "hOLA"; // Imprimimos en fichero byte[] contentInBytes = content.getBytes();
			 * fop.write(contentInBytes); fop.flush(); fop.close(); } catch
			 * (FileNotFoundException e) { // TODO Auto-generated catch block
			 * e.printStackTrace();
			 * 
			 * }
			 * 
			 * catch (java.io.IOException e) { System.err.println();
			 * System.err.println("ERROR DURANTE LA LECTURA DEL ARCHIVO"); System.exit(0); }
			 * catch (Exception e) { System.err.println();
			 * System.err.println("[ERROR DESCONOCIDO]" + e.getMessage()); System.exit(0); }
			 */
		}

	}

}
