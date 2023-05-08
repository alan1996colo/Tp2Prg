package negocio;

import java.io.Serializable;
import java.util.List;

import fileManager.GestorArchivos;

public class Negocio implements Serializable{
	private static final long serialVersionUID = 1L;
	int aumentoPesosPorcentaje;
	int costoFijoprovDistinta;
	int costoPesosxKM;
	GrafoLista grafo;
	public Negocio(int aumentoPesosPorcentaje,int costoFijoprovDistancia,int costoPesos) {
		this.costoPesosxKM=costoPesos;
		this.aumentoPesosPorcentaje=aumentoPesosPorcentaje;
		this.costoFijoprovDistinta=costoFijoprovDistancia;
		
	}
	public void inicializarGrafo() {
		this.grafo=new GrafoLista();
	}
	
	
	
	/**Inteta registrar la localidad, true-> se logro , false->algo fallo.*/
	public boolean registrarLocalidad(String nombreCiudad, String nombreProvincia, double latitud, double longitud) {
		Nodo nodo=new Nodo(nombreCiudad,  nombreProvincia, latitud, longitud);
		try{this.grafo.agregarNodo(nodo);
		return true;
		}
		catch(IllegalArgumentException i){
			System.out.println("No se pudo registrar la localidad, porque ya se encuentra registrada.");
		}
		return false;
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
			this.grafo.agregarArista(origen, destino);
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
	public boolean IsProvinciasDistintas(String ciudad1,String ciudad2) {
		 return this.grafo.isProvDiff(this.grafo.buscarNodoCiudad(ciudad1),this.grafo.buscarNodoCiudad(ciudad2));
		}
	
	/*Una primera version para dar costo en pesos*/
	public double darCostoEnpesos() {
		double ret=0;
		List<Arista> arbol=AGMPrim.AGMPrim(this.grafo);
		for(Arista a:arbol) {ret=ret+(a.getPeso()*costoPesosxKM);
			
			
		}
		
		return ret;}
	public void MostrarGrafos() {
		this.grafo.mostrarGrafoConAristas();
			}
	
	/*Intenta borrar la ciudad pasada , true se logro, false no se pudo(quiza no estaba en el grafo)*/
	public boolean quitarCiudad(String ciudadN) {
		return this.grafo.eliminarNodoCiudad(ciudadN);
		
	}
	
	
	/**
	 * Muestra las aristas del nodo indice pasado.
	 * **/
	public void mostrarConexionNodoNumero(int i){
		this.grafo.mostrarVecinosNodoNumero(i);
		
	}
	public void generarGrafoCompleto(GrafoLista grafo) {
		
		for(int j=0;j<grafo.getTamanio();j++) {
			Nodo origen = grafo.getNodoNum(j);
			origen.inicializarVecinos();
			System.out.println(origen);
			for(int i=1;i<grafo.getTamanio();i++) {
				
				if(i!=j) {
					Nodo destino = grafo.getNodoNum(i);
					
					origen.agregarVecino(origen,destino,GrafoLista.distanciaEntreNodos(origen, destino) );
					System.out.println("La    "+origen+ "   con:   "+destino + "el peso es:"+ GrafoLista.distanciaEntreNodos(origen, destino));
				}
			}
			
		}
	
		
		
	}
		
	

}
