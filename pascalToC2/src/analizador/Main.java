package analizador;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

	private static void traductor(ArrayList<Programa> programa, ArrayList<Variable> variables,
			ArrayList<Constante> constantes, ArrayList<Procedimiento> procedimientos, ArrayList<Funcion> funciones) {
		String code = null;
		// directivas
		code = "#include <stdio.h>/n";

		// variables
		if (variables.size() != 0) {

			for (Variable variable : variables) {
				// NO APARECE EN LA GRAMATICA RESULTADO
			}
		}
		// constantes
		if (constantes.size() != 0) {

			for (Constante constante : constantes) {

				System.out.println("#define " + constante.identificador + " " + constante.valor);
			}
		}

		// Procedimientos
		if (procedimientos.size() != 0) {
			for (Procedimiento procedimiento : procedimientos) {
				System.out.print(procedimiento.procedimiento);
				System.out.print(" ");
				System.out.print(procedimiento.identificador);
				// FALTA CONTROL DE VACIOS PARA VOID
				System.out.print("(");
				for (FormalParam parametros : procedimiento.formalParam) {
					String tipo = parametros.tipo;
					int cont = parametros.identificador.size();
					for (String ident : parametros.identificador) {
						System.out.print(tipo);
						System.out.print(" ");
						System.out.print(ident);
						cont--;
						if (cont != 0) {
							System.out.print(",");
						}

					}
				}
				System.out.print(")");
				// cuerpo procedimeinto
				System.out.println("{");
				for (Sent sentencia : procedimiento.bloque.sentlist) {
					if (sentencia.asig != null) {
						System.out.print(sentencia.asig);
					}
					if (sentencia.pro_call != null) {
						System.out.print(sentencia.pro_call);
					}
				}
				System.out.println("}");
			}
		}

		// funciones
		if (funciones.size() != 0) {
			for (Funcion funcion : funciones) {
				System.out.print(funcion.tipo);
				System.out.print(" ");
				System.out.print(funcion.identificador);
				// FALTA CONTROL DE VACIOS PARA VOID
				System.out.print("(");
				for (FormalParam parametros : funcion.formalParam) {
					String tipo = parametros.tipo;
					int cont = parametros.identificador.size();
					for (String ident : parametros.identificador) {
						System.out.print(tipo);
						System.out.print(" ");
						System.out.print(ident);
						cont--;
						if (cont != 0) {
							System.out.print(",");
						}

					}

				}
				System.out.print(")");

				System.out.print(funcion.bloque.begin);
				// cuerpo funcion
				String returno = "";
				int cont = funcion.bloque.sentlist.size();
				for (Sent sentencia : funcion.bloque.sentlist) {
					if (sentencia.asig != null) {
						System.out.print(sentencia.asig);
					}
					if (sentencia.pro_call != null) {
						System.out.print(sentencia.pro_call);
					}
					// EL ULTIMO SERA de retorno
					cont--;
					if (cont == 0) {
						if (sentencia.asig != null) {
							System.out.print("retun " + sentencia.asig);
						}
						if (sentencia.pro_call != null) {
							System.out.print("retun " + sentencia.pro_call);
						}
					}
				}
				System.out.print(funcion.bloque.end);
				System.out.println("");
			}

		}
		// programa principal

		if (programa.size() != 0) {
			for (Programa program : programa) {
				System.out.print("void " + program.identificador + "(void)");
				System.out.println("{");
				// cuerpo
				for (Sent sentencia : program.bloque.sentlist) {
					if (sentencia.asig != null) {
						System.out.print(sentencia.asig);
					}
					if (sentencia.pro_call != null) {
						System.out.print(sentencia.pro_call);
					}
				}
				System.out.println("}");
			}

		}

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
			ArrayList<Procedimiento> procedimientos = sintaxis.procedimiento;
			ArrayList<Funcion> funciones = sintaxis.funcion;
			ArrayList<Programa> programa = sintaxis.programa;
			// CLASE TRADUCTOR
			traductor(programa, variables, constantes, procedimientos, funciones);

		}

	}

}
