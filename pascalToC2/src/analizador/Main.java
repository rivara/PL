package analizador;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

	private static String traductor(ArrayList<Programa> p, ArrayList<Funcion> declaraciones, ArrayList<SubFuncion> sd) {
		String code = null;
		// directivas
		code = "#include <stdio.h>/n";

		// constantes y variables
		if (declaraciones.size() != 0) {

			for (Funcion declaracion : declaraciones) {
				// variables
				if (declaracion.variable.toString() != "") {
					// no
					// System.out.println(declaracion.variable.toString());
					// System.out.println(declaracion.identificador.toString());

				}
				// constante
				if (declaracion.constante.toString() != "") {
					System.out.println(declaracion.constante.toString());
				}
				// funcion
				if (declaracion.procedimiento.toString() != "") {
					System.out.println(declaracion.procedimiento.toString());
				}
				// procedimiento
				if (declaracion.funcion.toString() != "") {
					System.out.println(declaracion.funcion.toString());
				}
			}
		}

		return code;
	}

	public static void main(String[] args) throws Exception {

		String one = args[0];
		if (args.length == 0) {
			System.out.println("Es obligatorio pasar la ruta del archivo de prueba como parámetro");
		} else {
			AnalizadorLexico lexico = null;
			try {
				lexico = new AnalizadorLexico(new java.io.FileReader(args[0]));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			@SuppressWarnings("deprecation")
			sintactico sintaxis = new sintactico(lexico);
			sintaxis.parse();
//////////////////////////////TRADUCTOR
// isntancio elementos
			/*
			 * ArrayList<Programa> programa = sintaxis.pgrArray; ArrayList<Funcion>
			 * declaraciones = sintaxis.dclArray; ArrayList<SubFuncion> subdeclaraciones =
			 * sintaxis.subdclArray;
			 */
			ArrayList<Variable> variable = sintaxis.variable;
// ArrayList<String> p = sintaxis.prueba;

			// System.out.println(prueba.get(0).toString());
			System.out.println(variable);
// System.out.println(declaraciones.get(0).tbas);
			// String c = traductor(programa, declaraciones, subdeclaraciones);

////////////////////////////// GENERACION DEL FICHERO

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
