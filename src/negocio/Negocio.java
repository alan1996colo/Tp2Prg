package negocio;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

import org.openstreetmap.gui.jmapviewer.Coordinate;

import fileManager.GestorArchivos;

public class Negocio implements Serializable {
	private List<Arista> recorrer = new ArrayList<Arista>();
	private static final long serialVersionUID = 1L;
	double kmExcedido=300;
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
	
	public double calcularPesoMinimo() {
		double valor=0;
		
		for(Arista re:recorrer) {
			valor=valor+re.getPeso();
			
		}
		return valor;
	}
	
	public List<String> dameDatosAGM() {
		List<String> lista= new  ArrayList<String>();
		DecimalFormat df = new DecimalFormat("#.00");
		for(Arista ar : recorrer) {
			lista.add(ar.getNodoOrigen().getNombreCiudad()+"--->"+ar.getNodoDestino().getNombreCiudad()+", el peso es: "+ df.format(ar.getPeso())+"\n" );
		}
		
		return lista;
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
	public void agregarNodoConNombreDesdeArchivoJson(String name, String fname) { // "NO SE USA"..disponible para futuras imlementaciones
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
			double peso= calcularPeso(origen,destino);
			this.grafo.agregarArista(origen, destino,peso);
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

		//List<Arista> recorrer = new ArrayList<Arista>();
		recorrer = AGMPrim.AGMPrim(this.grafo);
		for (Arista ar : recorrer) {
			lista.add(new AbstractMap.SimpleEntry<>(ar.getNodoOrigen().getNombreCiudad(),ar.getNodoDestino().getNombreCiudad()));
			
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

	/*Este metodo se va eliminar porque correspondia a la anterior modelo */
	public double PorcentajeDeAumentoConexion() { //borrar luego.
		
		return 0;
	}

	/* Este metodo tambien se va borrar porque correspondia al anterior modelo. */
	public double PorcentajeDeAumentoConexionSinPlanificar() { // OK
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
			ret = ret + (a.getPeso());

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
	 * Genera conexion entre todas las ciudades con todas.
	 */
	public void generarGrafoCompleto() {
		for(Nodo origen:this.grafo.getNodos()) {
			origen.inicializarVecinos();
			for(Nodo destino:this.grafo.getNodos()) {
				if(!origen.equals(destino)) {
					origen.agregarVecino(destino,calcularPeso(origen,destino));
				}
			}
		}
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
	

	}
	public double getAumentoPesosPorcentaje() {
		return aumentoPesosPorcentaje;
	}

	public void setAumentoPesosPorcentaje(double aumentoPesosPorcentaje) {
		this.aumentoPesosPorcentaje = aumentoPesosPorcentaje;
	}

	public double getCostoFijoprovDistinta() {
		return costoFijoprovDistinta;
	}

	public void setCostoFijoprovDistinta(double costoFijoprovDistinta) {
		this.costoFijoprovDistinta = costoFijoprovDistinta;
	}

	public double getCostoPesosxKM() {
		return costoPesosxKM;
	}

	public void setCostoPesosxKM(double costoPesosxKM) {
		this.costoPesosxKM = costoPesosxKM;
	}
	public double getKmExcedido() {
		return kmExcedido;
	}

	public void setKmExcedido(double kmExcedido) {
		this.kmExcedido = kmExcedido;
	}

	/**Este metodo calcula el precio total del enunciado , considerando todo, y se usa para asignar el peso a las aristas.*/
	private double calcularPeso(Nodo nodoOrigen, Nodo nodoDestino) {
		
		double distancia =distanciaEntreNodos(nodoOrigen, nodoDestino);
		double PesoDistanciaPorKilometro = distancia* this.getCostoPesosxKM();
		
		if(nodoOrigen.getNombreProvincia()!=nodoDestino.getNombreProvincia()) {
			PesoDistanciaPorKilometro= PesoDistanciaPorKilometro + this.getCostoFijoprovDistinta();
		}
		if (distancia>this.getKmExcedido()) {
				PesoDistanciaPorKilometro= ((PesoDistanciaPorKilometro*(aumentoPesosPorcentaje/100)))+PesoDistanciaPorKilometro;
		}
		
		return PesoDistanciaPorKilometro;
	}
	
	
	public static double distanciaEntreNodos(Nodo ciudad1, Nodo ciudad2) { // OK
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
	public GrafoLista getGrafo() {
		return this.grafo;
	}



}
