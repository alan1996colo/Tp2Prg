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
		GrafoLista temp=new GrafoLista(gest.cargarJsonLista(fname));
		this.grafo.agregarNodo(temp.buscarNodoCiudad(name));
	}
	/**
	 * Dado dos Strings de ciudades, bien o mal escritas, revisa :
	 * Si esas ciudades estan en el grafo, entonces agrega la conexion.
	 * Si esas ciudades no estan en el grafo, entonces Devuelve False, lo que significa que se debe agregar el nodo al grafo primero.
	 * 
	 * **/
	public boolean agregarConexion(String ciudadOrigen,String ciudadDestino) {
		Nodo origen=this.grafo.buscarNodoCiudad(ciudadOrigen);
		Nodo destino=this.grafo.buscarNodoCiudad(ciudadDestino);
		if(origen!=null&&destino!=null) {
			origen.agregarVecino(destino,GrafoLista.distanciaEntrePuntos(origen, destino));
			return true;
		}
		else {
			return false;
		}
		//seguir mañana
		
	}
	
	
	public /*Grafo*/void  CrearArbolGeneradorMinimo() {};
	public int CostoPesos(int localidad_1, int localidad_2) {return 0;};
	private int PorcentajeDeAumentoConexion() {return 0;}
	public boolean IsProvinciasDistintas() {return false;}
	public int DarCostoEnpesos() {return 0;}
	public void MostrarGrafos() {
		this.grafo.mostrarGrafo();
			
			
		}
		
	

}
