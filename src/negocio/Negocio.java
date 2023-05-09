package negocio;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

import org.openstreetmap.gui.jmapviewer.Coordinate;

import fileManager.GestorArchivos;

public class Negocio implements Serializable {
	private static final long serialVersionUID = 1L;
	double aumentoPesosPorcentaje;
	double costoFijoprovDistinta;
	double costoPesosxKM;
	GrafoLista grafo;

	public Negocio(double aumentoPesosPorcentaje, double costoFijoprovDistancia, double costoPesos) {
		this.costoPesosxKM = costoPesos;
		this.aumentoPesosPorcentaje = aumentoPesosPorcentaje;
		this.costoFijoprovDistinta = costoFijoprovDistancia;

	}

	/* Modifica los precios del negocio actual */
	public void setPrecios(double aumentoPesosPorcentaje, double costoFijoprovDistancia, double costoPesos) {
		this.costoPesosxKM = costoPesos;
		this.aumentoPesosPorcentaje = aumentoPesosPorcentaje;
		this.costoFijoprovDistinta = costoFijoprovDistancia;
	}

	public void inicializarGrafo() {
		this.grafo = new GrafoLista();
	}

	/*
	 * Dado el nombre de ciudad pasadas, revisa si esta y devuelve las coordenadas
	 */
	public Coordinate getCoordenadasFrom(String localidad) { // OK
		Nodo n = this.grafo.buscarNodoCiudad(localidad);
		if (n != null) {
			Coordinate ret = new Coordinate(n.getLatitud(), n.getLongitud());
			return ret;
		} else {
			throw new RuntimeException("No se encontro la ciudad en la lista, revise clase NEgocio.");

		}
	}

	/*
	 * Retorna un arrayList con el nombre de todas las localidades en el grafo.
	 */
	public ArrayList<String> todasLasLocalidades() { // OK
		ArrayList<String> ret = new ArrayList<String>();
		for (Nodo n : this.grafo.getNodos()) {
			ret.add(n.getNombreCiudad());
		}
		return ret;
	}

	/** Inteta registrar la localidad, true-> se logro , false->algo fallo. */
	public boolean registrarLocalidad(String nombreCiudad, String nombreProvincia, double latitud, double longitud) { // OK
		Nodo nodo = new Nodo(nombreCiudad, nombreProvincia, latitud, longitud);
		try {
			this.grafo.agregarNodo(nodo);
			return true;
		} catch (IllegalArgumentException i) {
			System.out.println("No se pudo registrar la localidad, porque ya se encuentra registrada.");
		}
		return false;
	}

	/**
	 * Dado un nombre de ciudad pasado, lo busca en el archivo pasado y lo agrega a
	 * la lista de nodos del grafo.
	 **/
	public void agregarNodoConNombreDesdeArchivoJson(String name, String fname) { // "NO SE USA"
		GestorArchivos gest = new GestorArchivos();
		GrafoLista temp = new GrafoLista(gest.cargarJsonLista(fname));
		this.grafo.agregarNodo(temp.buscarNodoCiudad(name));
	}

	/**
	 * Dado dos Strings de ciudades, bien o mal escritas, revisa : Si esas ciudades
	 * estan en el grafo, entonces agrega la conexion. Si esas ciudades no estan en
	 * el grafo, entonces Devuelve False, lo que significa que se debe agregar el
	 * nodo al grafo primero.
	 * 
	 **/
	public boolean agregarConexion(String ciudadOrigen, String ciudadDestino) { // OK
		Nodo origen = this.grafo.buscarNodoCiudad(ciudadOrigen);
		Nodo destino = this.grafo.buscarNodoCiudad(ciudadDestino);
		if (origen != null && destino != null) {
			this.grafo.agregarArista(origen, destino);
			return true;
		} else {
			return false;
		}
		// seguir mañana

	}

	/*
	 * Devuelve el arbol generador minimo del grafo actual, en forma de lista de
	 * pares StringString ordenados, los cuales son el NOmbre Origen de Ciudad y el
	 * nombre Destino de ciudad
	 **/
	public List<AbstractMap.SimpleEntry<String, String>> CrearArbolGeneradorMinimo() { // OK
		List<AbstractMap.SimpleEntry<String, String>> lista = new ArrayList<>();

		List<Arista> recorrer = new ArrayList<Arista>();
		recorrer = AGMPrim.AGMPrim(this.grafo);
		for (Arista ar : recorrer) {
			lista.add(new AbstractMap.SimpleEntry<>(ar.getNodoOrigen().getNombreCiudad(),
					ar.getNodoDestino().getNombreCiudad()));
		}
		return lista;
	};

	/**
	 * Si existe alguna conexion entre ciudades que sean de distintas provincias,
	 * entonces devolvera el costo fijo
	 */
	public double CostoFijoPesosProvDiff() { // OK
		if (this.grafo == null) {
			return 0;
		}
		for (Nodo n : this.grafo.getNodos()) {
			for (Nodo comparar : this.grafo.getNodos()) {
				if (!n.equals(comparar) && !n.getNombreProvincia().equals(comparar.getNombreProvincia())) {
					return costoFijoprovDistinta;

				}
			}

		}

		return 0;
	};

	/* Devuelve el porcentaje adicional del precio de la conexion */
	public double PorcentajeDeAumentoConexion() { // OK
		if (darCostoEnpesos() / costoPesosxKM > 300) {
			return darCostoEnpesos() * aumentoPesosPorcentaje / 100;
		}
		return 0;
	}

	/* Devuelve el porcentaje adicional del precio de la conexion sin planificar */
	public double PorcentajeDeAumentoConexionSinPlanificar() { // OK
		if (darCostoEnpesosSinPLanificar() / costoPesosxKM > 300) {
			return darCostoEnpesosSinPLanificar() * aumentoPesosPorcentaje / 100;
		}
		return 0;
	}

	public boolean IsProvinciasDistintas(String ciudad1, String ciudad2) { // NO SE USA
		return this.grafo.isProvDiff(this.grafo.buscarNodoCiudad(ciudad1), this.grafo.buscarNodoCiudad(ciudad2));
	}

	public double darCostoEnpesosSinPLanificar() { // OK
		if (this.grafo == null) {
			return 0;
		}
		return AGMPrim.pesoDelGrafo(this.grafo);
	}

	/* Devuelve el costo en pesos total de la conexion despues de planificar */
	public double darCostoEnpesos() { // OK
		if (this.grafo == null) {
			return 0;
		}
		double ret = 0;
		List<Arista> arbol = AGMPrim.AGMPrim(this.grafo);
		for (Arista a : arbol) {
			ret = ret + (a.getPeso() * costoPesosxKM);

		}

		return ret;
	}

	public void MostrarGrafos() { // NO SE USA
		this.grafo.mostrarGrafoConAristas();
	}

	/*
	 * Intenta borrar la ciudad pasada , true se logro, false no se pudo(quiza no
	 * estaba en el grafo)
	 */
	public boolean quitarCiudad(String ciudadN) { // NO SE USA
		return this.grafo.eliminarNodoCiudad(ciudadN);

	}

	/**
	 * Muestra las aristas del nodo indice pasado.
	 **/
	public void mostrarConexionNodoNumero(int i) { // NO SE USA
		this.grafo.mostrarVecinosNodoNumero(i);

	}

	/*
	 * Genera conexion entre todas las ciudades con todas, la anterior
	 * implementacion era O(n**2)-->ahora O(n**"2/2)
	 */
	public void generarGrafoCompleto() {
		ArrayList<Nodo> nodos = new ArrayList<Nodo>();
		for (int j = 0; j < grafo.getTamanio(); j++) {
			Nodo origen = grafo.getNodoNum(j);
			origen.inicializarVecinos();
			for (int i = j + 1; i < grafo.getTamanio(); i++) {
				Nodo destino = grafo.getNodoNum(i);
				destino.inicializarVecinos();
				origen.agregarVecino(origen, destino, GrafoLista.distanciaEntreNodos(origen, destino));
				destino.agregarVecino(destino, origen, GrafoLista.distanciaEntreNodos(origen, destino));
			}
			nodos.add(origen);
		}
		this.grafo.setNodos(nodos);
	}

	public void guardarSesion(String fname) {
		GestorArchivos gestor = new GestorArchivos();
		gestor.generarJSON(fname, this.grafo.getNodos());

	}

	/*
	 * Reemplaza el grafo actual por el archivo pasado
	 **/
	public void cambiarGrafoPor(String name) { // OK
		GestorArchivos gestor = new GestorArchivos();
		this.grafo.setNodos(gestor.cargarJsonLista(name));
		// TODO Auto-generated method stub

	}

}
