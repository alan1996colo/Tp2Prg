package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.openstreetmap.gui.jmapviewer.Coordinate;

import fileManager.GestorArchivos;

public class Negocio implements Serializable {
	private static final long serialVersionUID = 1L;
	int aumentoPesosPorcentaje;
	int costoFijoprovDistinta;
	int costoPesosxKM;
	GrafoLista grafo;

	public Negocio(int aumentoPesosPorcentaje, int costoFijoprovDistancia, int costoPesos) {
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
	public Coordinate getCoordenadasFrom(String localidad) {
		Nodo n = this.grafo.buscarNodoCiudad(localidad);
		if (n != null) {
			Coordinate ret = new Coordinate(n.getLatitud(), n.getLongitud());
			return ret;
		} else {
			throw new RuntimeException("No se encontro la ciudad en la lista, revise clase NEgocio.");

		}
	}

	public ArrayList<String> todasLasLocalidades() {
		ArrayList<String> ret = new ArrayList<String>();
		for (Nodo n : this.grafo.getNodos()) {
			ret.add(n.getNombreCiudad());
		}
		return ret;
	}

	/** Inteta registrar la localidad, true-> se logro , false->algo fallo. */
	public boolean registrarLocalidad(String nombreCiudad, String nombreProvincia, double latitud, double longitud) {
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
	public void agregarNodoConNombreDesdeArchivoJson(String name, String fname) {
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
	public boolean agregarConexion(String ciudadOrigen, String ciudadDestino) {
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

	public /* Grafo */void CrearArbolGeneradorMinimo() {

		// desarrollar para que la capa de presentacion no maneje ni nodos ni grafos,
		// solo strings o coordenadas
	};

	/**
	 * Si las dos localidades pasadas pertenecen a provincias distintas devuelve el
	 * costo fijo, sino 0
	 */
	public int CostoFijoPesosProvDiff(String localidad_1, String localidad_2) {
		if (IsProvinciasDistintas(localidad_1, localidad_2)) {
			return costoFijoprovDistinta;
		}

		return 0;
	};

	/* Devuelve el porcentaje adicional del precio de la conexion */
	public double PorcentajeDeAumentoConexion() {
		if (darCostoEnpesos() / costoPesosxKM > 300) {
			return darCostoEnpesos() * aumentoPesosPorcentaje / 100;
		}
		return 0;
	}

	public boolean IsProvinciasDistintas(String ciudad1, String ciudad2) {
		return this.grafo.isProvDiff(this.grafo.buscarNodoCiudad(ciudad1), this.grafo.buscarNodoCiudad(ciudad2));
	}

	/* Devuelve el costo en pesos total de la conexion */
	public double darCostoEnpesos() {
		double ret = 0;
		List<Arista> arbol = AGMPrim.AGMPrim(this.grafo);
		for (Arista a : arbol) {
			ret = ret + (a.getPeso() * costoPesosxKM);

		}

		return ret;
	}

	public void MostrarGrafos() {
		this.grafo.mostrarGrafoConAristas();
	}

	/*
	 * Intenta borrar la ciudad pasada , true se logro, false no se pudo(quiza no
	 * estaba en el grafo)
	 */
	public boolean quitarCiudad(String ciudadN) {
		return this.grafo.eliminarNodoCiudad(ciudadN);

	}

	/**
	 * Muestra las aristas del nodo indice pasado.
	 **/
	public void mostrarConexionNodoNumero(int i) {
		this.grafo.mostrarVecinosNodoNumero(i);

	}

	public void generarGrafoCompleto() {
		ArrayList<Nodo> nodos = new ArrayList<Nodo>();
		for (int j = 0; j < grafo.getTamanio(); j++) {
			Nodo origen = grafo.getNodoNum(j);
			origen.inicializarVecinos();
			// System.out.println(origen);
			for (int i = 0; i < grafo.getTamanio(); i++) {

				if (i != j) {
					Nodo destino = grafo.getNodoNum(i);

					origen.agregarVecino(origen, destino, GrafoLista.distanciaEntreNodos(origen, destino));
					nodos.add(origen);
					// System.out.println("La "+origen+ " con: "+destino + "el peso es:"+
					// GrafoLista.distanciaEntreNodos(origen, destino));
				}
			}

		}

		this.grafo.setNodos(nodos);

	}

}
