package analizador;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class Main {

	private static String traductor(ArrayList<Programa> programa, ArrayList<Variable> variables,
			ArrayList<Constante> constantes, ArrayList<Procedimiento> procedimientos, ArrayList<Funcion> funciones) {
		String code = null;
		// directivas
		code = "#include <stdio.h>\n";

		// variables
		if (variables.size() != 0) {

			for (Variable variable : variables) {
				// NO APARECE EN LA GRAMATICA RESULTADO
			}
		}
		// constantes
		if (constantes.size() != 0) {

			for (Constante constante : constantes) {

				// System.out.println("#define " + constante.identificador + " " +
				// constante.valor);
				code = code + "\n#define " + constante.identificador + " " + constante.valor;
			}
		}

		// Procedimientos
		if (procedimientos.size() != 0) {
			for (Procedimiento procedimiento : procedimientos) {
				// System.out.print(procedimiento.procedimiento);

				code = code + procedimiento.procedimiento;
				// System.out.print(" ");
				code = code + " ";
				// System.out.print(procedimiento.identificador);
				code = code + procedimiento.identificador;

				// FALTA CONTROL DE VACIOS PARA VOID
				// System.out.print("(");
				code = code + "(";
				for (FormalParam parametros : procedimiento.formalParam) {
					String tipo = parametros.tipo;
					int cont = parametros.identificador.size();
					for (String ident : parametros.identificador) {
						// System.out.print(tipo);
						code = code + tipo;
						// System.out.print(" ");
						code = code + " ";
						// System.out.print(ident);
						code = code + ident;
						cont--;
						if (cont != 0) {
							// System.out.print(",");
							code = code + ",";
						}

					}
				}
				// System.out.print(")");
				code = code + ")";
				// cuerpo procedimeinto
				// System.out.println("{");
				code = code + "\n{";
				for (Sent sentencia : procedimiento.bloque.sentlist) {
					if (sentencia.asig != null) {
						// System.out.print(sentencia.asig);
						code = code + sentencia.asig;
					}
					if (sentencia.pro_call != null) {
						// System.out.print(sentencia.pro_call);
						code = code + sentencia.pro_call;
					}
				}
				// System.out.println("}");
				code = code + "\n}";
			}
		}

		// funciones
		if (funciones.size() != 0) {
			for (Funcion funcion : funciones) {
				// System.out.print(funcion.tipo);
				code = code + funcion.tipo;
				// System.out.print(" ");
				code = code + " ";
				// System.out.print(funcion.identificador);
				code = code + funcion.identificador;
				// FALTA CONTROL DE VACIOS PARA VOID
				// System.out.print("(");
				code = code + "(";
				for (FormalParam parametros : funcion.formalParam) {
					String tipo = parametros.tipo;
					int cont = parametros.identificador.size();
					for (String ident : parametros.identificador) {
						// System.out.print(tipo);
						code = code + tipo;
						// System.out.print(" ");
						code = code + " ";
						// System.out.print(ident);
						code = code + ident;
						cont--;
						if (cont != 0) {
							// System.out.print(",");
							code = code + ",";
						}

					}

				}
				// System.out.print(")");
				code = code + ")";
				// System.out.print(funcion.bloque.begin);
				code = code + funcion.bloque.begin;
				// cuerpo funcion
				String returno = "";
				int cont = funcion.bloque.sentlist.size();
				for (Sent sentencia : funcion.bloque.sentlist) {
					if (sentencia.asig != null) {
						// System.out.print(sentencia.asig);
						code = code + sentencia.asig;
					}
					if (sentencia.pro_call != null) {
						// System.out.print(sentencia.pro_call);
						code = code + sentencia.pro_call;
					}
					// EL ULTIMO SERA de retorno
					cont--;
					if (cont == 0) {
						if (sentencia.asig != null) {
							// System.out.print("retun " + sentencia.asig);
							code = code + "retun " + sentencia.asig;
						}
						if (sentencia.pro_call != null) {
							// System.out.print("retun " + sentencia.pro_call);
							code = code + sentencia.pro_call;
						}
					}
				}
				// System.out.print(funcion.bloque.end);
				code = code + funcion.bloque.end;
				// System.out.println("");
				code = code + "\n ";
			}

		}
		// programa principal

		if (programa.size() != 0) {
			for (Programa program : programa) {
				// System.out.print("void " + program.identificador + "(void)");
				code = code + "void " + program.identificador + "(void)";
				// System.out.println("{");
				code = code + "\n{";
				// cuerpo
				for (Sent sentencia : program.bloque.sentlist) {
					if (sentencia.asig != null) {
						// System.out.print(sentencia.asig);
						code = code + sentencia.asig;
					}
					if (sentencia.pro_call != null) {
						// System.out.print(sentencia.pro_call);
						code = code + sentencia.pro_call;
					}
				}
				// System.out.println("}");
				code = code + "\n}";
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
			// isntancio elementos

			ArrayList<Variable> variables = sintaxis.variable;
			ArrayList<Constante> constantes = sintaxis.constante;
			ArrayList<Procedimiento> procedimientos = sintaxis.procedimiento;
			ArrayList<Funcion> funciones = sintaxis.funcion;
			ArrayList<Programa> programa = sintaxis.programa;
			// CLASE TRADUCTOR
			String code = traductor(programa, variables, constantes, procedimientos, funciones);
			System.out.println(code);
			// ggenerador de fichero
			// String str = "World";
			/*
			 * BufferedWriter writer = new BufferedWriter(new FileWriter("programa.c",
			 * true)); writer.append(' '); writer.append(code); writer.close();
			 */
			try (Writer writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream("programa.c"), "utf-8"))) {
				writer.write(code);
			}

		}

	}

}
