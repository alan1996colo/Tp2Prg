package negocio;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.*;
import negocio.Negocio;


public class GrafoLista implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<Nodo> nodos;// lista de nodos
	private Negocio negocio;

	public GrafoLista() {// OK
		this.nodos = new ArrayList<>();
	}

	public GrafoLista(ArrayList<Nodo> nodos) { // OK// construir grafo lista con una lista de nodos ya creada.
		this.nodos = nodos;
	}

	/* Cambia la lista de nodos a la lista pasada */
	public void setNodos(ArrayList<Nodo> nodos) { // OK
		this.nodos = nodos;
	}

	public Nodo getNodoNum(int n) { // OK
		return this.nodos.get(n);
	}

	public void agregarNodo(Nodo nodo) {// OK
		if (this.nodos.contains(nodo)) {
			throw new IllegalArgumentException("El nodo ya estaba incluido en el grafo");
		} else {
			nodos.add(nodo);
		}
	}

	public void incializarVecinosNodos() { // NO SE USA
		for (Nodo iter : this.nodos) {
			if (iter.getAristas() == null)
				iter.inicializarVecinos();
		}
	}

	public void mostrarVecinosNodoNumero(int i) { // OK
		this.nodos.get(i).mostrarVecinos();
	}

	/*
	 * Intenta agreagar la arista entre dos nodos
	 */
	public void agregarArista(Nodo nodoOrigen, Nodo nodoDestino, double peso) { // OK
		if (nodoOrigen.equals(nodoDestino)) {
			throw new IllegalArgumentException("No se permiten bucles");
		} else {
			
		//	double peso = GrafoLista.distanciaEntreNodos(nodoOrigen, nodoDestino);
			nodoOrigen.agregarVecino(nodoDestino, peso);
			nodoDestino.agregarVecino( nodoOrigen, peso);
		}
	}
	
	

	// devuelvo los nodos del grafo
	public List<Nodo> getNodos() {// OK
		return nodos;
	}

	public int getTamanio() {// OK 
		return this.nodos.size();
	}

	// cambiar nombre a getVecinos mas tarde
	public Set<Arista> getVecinos(int i) { // NO SE USA
		return this.nodos.get(i).getAristas();

	}

	/**
	 * Busca la ciudad pasada en la lista de nodos y la elimina de la lista,
	 * devuelve true si se elimino el nodo
	 */
	public boolean eliminarNodoCiudad(String name) { // OK
		Nodo borrar = buscarNodoCiudad(name);
		if (borrar == null || borrar.equals(null)) {
			throw new IllegalArgumentException("No se puede quitar una ciudad que no existe en el grafo");
		}

		// Eliminar todas las aristas que conectan con el nodo a eliminar

		for (Nodo n : nodos) {
			n.quitarArista(borrar.getNombreCiudad());
		}

		// Eliminar el nodo a eliminar del grafo
		return nodos.remove(borrar);
	}

	/**
	 * Busca el nodo por nombre de ciudad, si lo encuentra retorna el nodo,sino
	 * nada.
	 */
	public Nodo buscarNodoCiudad(String name) { // OK
		if (this.nodos == null || this.nodos.equals(null)) {
			throw new NullPointerException("La lista de nodos esta vacia");

		}
		for (Nodo iter : this.nodos) {
			if (iter.getNombreCiudad().toLowerCase().replaceAll("\\s", "")
					.equals(name.toLowerCase().replaceAll("\\s", ""))) {
				return iter;
			}
		}
		throw new RuntimeException("no se encontro el nodo: " + name);

	}

	/**
	 * True =contiene al nodo en su lista False= el nodo no está
	 **/
	public boolean contains(Nodo nodo) { // NO SE USA
		for (Nodo iter : this.nodos) {
			if (iter.equals(nodo)) {
				return true;
			}
		}
		return false;
	}



	public boolean isProvDiff(Nodo n1, Nodo n2) { // OK
		return !n1.equalsProv(n2);
	}

	public void mostrarGrafo() { // NO SE USA
		// TODO Auto-generated method stub
		for (Nodo iter : this.nodos) {
			System.out.println(iter.toString());
		}

	}

	public void mostrarGrafoConAristas() { // OK
		for (Nodo iter : this.nodos) {
			System.out.println(iter.getNombreCiudad() + " :");
			iter.mostrarAristas();
		}
	}
	

}
