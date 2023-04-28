package negocio;

import java.io.Serializable;

public class Negocio implements Serializable{
	private static final long serialVersionUID = 1L;
	int aumentoPesosPorcentaje;
	int costoFijoprovDistancia;
	int costoPesos;
	GrafoLista grafo;
	public Negocio(int aumentoPesosPorcentaje,int costoFijoprovDistancia,int costoPesos) {
		this.costoPesos=costoPesos;
		this.aumentoPesosPorcentaje=aumentoPesosPorcentaje;
		this.costoFijoprovDistancia=costoFijoprovDistancia;
		
	}
	public void inicializarGrafo() {
		this.grafo=new GrafoLista();
	}
	/**
	 * Dado un nombre de ciudad pasado, lo busca en el archivo pasado y lo agrega a la lista de nodos del grafo.
	 * **/
	public void agregarNodoConNombreDesdeArchivoJson(String name,String fname) {
		GestorArchivos gest=new GestorArchivos();
		GrafoLista temp=new GrafoLista(gest.cargarJson(fname));
		this.grafo.agregarNodo(temp.buscarNodoCiudad(name));
	}
	public boolean agregarConexion(String ciudadOrigen,String ciudadDestino) {
		//seguir mañana
		return false;
	}
	
	
	public /*Grafo*/void  CrearArbolGeneradorMinimo() {};
	public int CostoPesos(int localidad_1, int localidad_2) {return 0;};
	private int PorcentajeDeAumentoConexion() {return 0;}
	public boolean IsProvinciasDistintas() {return false;}
	public int DarCostoEnpesos() {return 0;}
	public void MostrarGrafos() {}
	
	// borrar, solo pruebo comittear
	public void metodoDePrueba() {}
	

}
