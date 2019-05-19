package analizador;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

	private static String traductor(ArrayList<Programa> p) {
		String code = null;
		// cabecera
		for (final Programa pr : p) {
			// Here your room is available
			System.out.println(pr.identificador);
		}
		// blq

		// resto

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

			// transformo en el nuevo lenguaje
			// String c = traductor(cabecera);

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
