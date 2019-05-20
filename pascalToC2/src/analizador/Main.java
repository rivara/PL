package analizador;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

	private static void traductor(ArrayList<Programa> p, ArrayList<Variable> variables, ArrayList<Constante> constantes,
			ArrayList<Procedimiento> procedimientos, ArrayList<Funcion> funciones) {
		String code = null;
		// directivas
		code = "#include <stdio.h>/n";

		// variables
		if (variables.size() != 0) {

			for (Variable variable : variables) {
				// NO APARECE EN LA GRAMATICA RESULTADO
				// System.out.println(variable.tipo);
				/*
				 * String tipo = variable.tipo; for (String ident : variable.identificador) {
				 * System.out.println("#define " + ident + " " + tipo); }
				 */
			}
		}
		// constantes
		if (constantes.size() != 0) {

			for (Constante constante : constantes) {

				System.out.println("#define " + constante.identificador + " " + constante.valor);
			}
		}

		// Procedimientos

		/// public String procedimiento = "";
		/// public String identificador = null;
		// public ArrayList<FormalParam> formalParam = null;
		if (procedimientos.size() != 0) {
			for (Procedimiento procedimiento : procedimientos) {
				// System.out.println(procedimiento.identificador);
				for (FormalParam parametros : procedimiento.formalParam) {

					for (String ident : parametros.identificador) {
						System.out.println(ident);
					}

				}

			}
		}

		// funciones
		if (funciones.size() != 0) {
			for (Funcion funcion : funciones) {
				System.out.println(funcion.identificador);
			}
		}
		// programa principal

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
			// isntancio elementos

			ArrayList<Variable> variables = sintaxis.variable;
			ArrayList<Constante> constantes = sintaxis.constante;
			ArrayList<Procedimiento> procedimiento = sintaxis.procedimiento;
			ArrayList<Funcion> funcion = sintaxis.funcion;
			ArrayList<Programa> programa = sintaxis.programa;
			System.out.println(programa);
			// CLASE TRADUCTOR
			// System.out.println(funcion);
			// traductor(programa, variables, constantes, procedimiento, funcion);

		}

	}

}
