package analizador;

import java.util.ArrayList;

public class SubFuncion extends Simbolo {

	public String identificador;
	public ArrayList<Simbolo> simboloArray;
	public ArrayList<Simbolo> cteLista;
	public ArrayList<Simbolo> defvarLista;
	public ArrayList<Simbolo> formal_param;
}
