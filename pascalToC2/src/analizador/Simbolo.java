package analizador;

interface Simbolo {
	public String program = "";
	public boolean esProgram = false;
	public String identificador = "";
	public boolean esIdentificador = false;
	public String empieza = "";
	public boolean esEmpieza = false;
	public String fin = "";
	public boolean esFin = false;
	public String constante = "";
	public boolean esConstante = false;
	public boolean esIdenoicador = false;
	public String igual = "";
	public boolean esIgual = false;
	public String puntocoma = "";
	public boolean esPuntocoma = false;
	public String numericoEnteroConstante = "";
	public boolean esNumericoEnteroConstante = false;
	public String variable = "";
	public boolean esVariable = false;
	public String abreParenteis = "";
	public boolean esAbreParenteis = false;
	public String cierraParentesis = "";
	public boolean esCierraParentesis = false;
	public String dosPuntos = "";
	public boolean esDosPuntosfalse = false;
	public Object textoconstante = "";
	public boolean esTextoconstante = false;
	public String funcion = "";

}
