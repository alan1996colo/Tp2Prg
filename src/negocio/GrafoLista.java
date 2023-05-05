package negocio;

import java.io.Serializable;
import java.util.*;

public class GrafoLista implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Nodo> nodos;// lista de nodos

	public GrafoLista() {
		this.nodos = new ArrayList<>();
	}

	public GrafoLista(ArrayList<Nodo> nodos) {// construir grafo lista con una lista de nodos ya creada.
		this.nodos = nodos;

	}

	public Nodo getNodoNum(int n) {
		return this.nodos.get(n);
	}

	public void agregarNodo(Nodo nodo) {
		if(this.nodos.contains(nodo)) {throw new IllegalArgumentException("El nodo ya estaba incluido en el grafo");}
		else{nodos.add(nodo);
	}}
	public void incializarVecinosNodos() {
		for(Nodo iter:this.nodos) {
			if(iter.getArista()==null)
			iter.inicializarVecinos();
		}
	}
	public void mostrarVecinosNodoNumero(int i) {
		this.nodos.get(i).mostrarVecinos();
	}
	
	/*
	 * Intenta agreagar la arista entre dos nodos
	 * */
	public void agregarArista(Nodo nodo1, Nodo nodo2, double peso) {
		if(nodo1.equals(nodo2)) {throw new IllegalArgumentException("No se permiten bucles");}
		else {
			nodo1.agregarVecino(nodo2, peso);
			nodo2.agregarVecino(nodo1, peso);
		}
		
	}

	// devuelvo los nodos del grafo
	public List<Nodo> getNodos() {
		return nodos;
	}

	public int getTamanio() {// cambiarle nombre a getTamanio mas tarde
		return this.nodos.size();
	}

	// cambiar nombre a getVecinos mas tarde
	public List<Arista> getVecinos(int i) {
		return this.nodos.get(i).getArista();
	
	}
	
	
	/**Busca la ciudad pasada en la lista de nodos y la elimina de la lista, por ahora no actualiza ninguna arista ni referencia*/
	public boolean eliminarNodoCiudad(String name) {
		
		Nodo borrar=buscarNodoCiudad(name);
		if(borrar==null||borrar.equals(null)) {throw new IllegalArgumentException("No se puede quitar una ciudad que no existe en el grafo");}
		//Primero revisa las aristas, luego borra todas las aristas involucradas con el nodo, y luego quita el nodo
		for(Arista ar:borrar.getArista()) {
		buscarNodoCiudad(ar.getNodoDestino().getNombreCiudad()).quitarArista(borrar.getNombreCiudad());
		
		}
		
		if(this.nodos.remove(borrar)) {
				return true;
			
		}
	
		return false;
		
	}
	/**
	Busca el nodo por nombre de ciudad, si lo encuentra retorna el nodo,sino nada.
	*/
	public Nodo buscarNodoCiudad(String name) {
		if(this.nodos==null||this.nodos.equals(null)) {
			throw new NullPointerException("La lista de nodos esta vacia");

		}
		for(Nodo iter:this.nodos) {
			if(iter.getNombreCiudad().toLowerCase().replaceAll("\\s", "").equals(name.toLowerCase().replaceAll("\\s", ""))) {
				return iter;
			}
		}
		return null;
		
	}

	/**
	 * True =contiene al nodo en su lista
	 * False= el nodo no está
	 * **/
	public boolean contains(Nodo nodo) {
		for(Nodo iter:this.nodos) {
			if(iter.equals(nodo)) {
				return true;
			}
		}
		return false;
		
	}
	

	public static double distanciaEntreNodos(Nodo ciudad1, Nodo ciudad2) {
		double latitud1 = ciudad1.getLatitud();
		double latitud2 = ciudad2.getLatitud();
		double longitud1 = ciudad1.getLongitud();
		double longitud2 = ciudad2.getLongitud();
		final int radioTierra = 6371; // Radio de la Tierra en kilómetros
		double latitudDistancia = Math.toRadians(latitud2 - latitud1);
		double longitudDistancia = Math.toRadians(longitud2 - longitud1);
		double a = Math.sin(latitudDistancia / 2) * Math.sin(latitudDistancia / 2)
				+ Math.cos(Math.toRadians(latitud1)) * Math.cos(Math.toRadians(latitud2))
						* Math.sin(longitudDistancia / 2) * Math.sin(longitudDistancia / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distancia = radioTierra * c;
		return distancia;
	}
	public boolean isProvDiff(Nodo n1, Nodo n2) {
		return !n1.equalsProv(n2);
	}
	
	public void mostrarGrafo() {
		// TODO Auto-generated method stub
		for(Nodo iter:this.nodos) {
			System.out.println(iter.toString());
		}
		
	}
	public void mostrarGrafoConAristas() {
		for(Nodo iter:this.nodos) {
			System.out.println(iter.getNombreCiudad()+" :");
			iter.mostrarAristas();
		}
	}

}
