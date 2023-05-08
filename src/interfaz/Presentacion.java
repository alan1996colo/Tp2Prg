package interfaz;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import fileManager.GestorArchivos;
import negocio.AGMPrim;
import negocio.Arista;
import negocio.GrafoLista;
import negocio.Negocio;
import negocio.Nodo;

import java.awt.Canvas;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Presentacion {
	
	private Negocio negocio= new Negocio(2,3,4);
	private JFrame frame;
	private JMapViewer mapa;
	private JPanel panelMapa;
	private JTextField textField;
	private ArrayList<Nodo> ciudad = new ArrayList<Nodo>(); // puntos de las ciudades
	private ArrayList<Coordinate> coordenadas = new  ArrayList<Coordinate>(); // coordenadas de poligonos
	private GrafoLista ciudadesSeleccionadas = new GrafoLista();
	
	
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Presentacion window = new Presentacion();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Presentacion() {
		
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */

	/****
	 * Ingresa la localidad, devuelve true o false si se pudo o no***/
	public boolean IngresarLocalidad(String localidad) {return false;}
	/**
	 * Funcion que utiliza la herrramienta map viewer para visualizar los puntos**/
	public void llamarMapViewer() {}
	
	/***
	 * Este metodo va pedir a la clase negocio el camino minimo, y va intentar mostrar el menor camino por pantalla
	 * **/
	public void DameElArbolGeneradorMInimo() {}
	/***
	 * Permite al usuario modificar la solucion, este metodo va meter mano en la clase negocio
	 * **/
	public void ModificarSolucion() {}
	
	
	private void dibujarPuntos() {
		for(Nodo coordenadas :ciudad ) {
			double latitud = coordenadas.getLatitud();
			double longitud = coordenadas.getLongitud();
			//System.out.println(latitud);
			String NombreCiudad = coordenadas.getNombreCiudad();
			Coordinate coordinate = new Coordinate (latitud, longitud);
			MapMarker punto = new MapMarkerDot(coordinate);	
			punto.getStyle().setBackColor(Color.black);
			punto.getStyle().setColor(Color.yellow);
			mapa.addMapMarker(punto);
		}
	}
	private void obtenerCoordenadasDeCiudades() {
		for(int i=0;i<ciudad.size();i++) {
			coordenadas.add(new Coordinate(ciudad.get(i).getLatitud(),ciudad.get(i).getLongitud()));
		}
		
	}
    private ArrayList<Nodo> ObtenerCiudadDeArchivo( GrafoLista ciudades ,String provincia) {
    	
		for( int i=0; i<ciudades.getTamanio();i++) {
			if(ciudades.getNodoNum(i).getNombreProvincia().equals(provincia)) {
				this.ciudad.add(ciudades.getNodoNum(i));
				
			}
			
		}
		return ciudad;
	}
	private void generarConexionGrafoCompleto(GrafoLista ciudadesSeleccionadas) {
		
		negocio.generarGrafoCompleto(ciudadesSeleccionadas);
	}
	
	private void dibujarAristas(List<Arista> aristas) {
		
		List<Coordinate> coordenadas1 = new ArrayList<>();
		for (Arista arista : aristas) {
			
			
			double latitudOrigen = arista.getNodoOrigen().getLatitud();
	        double longitudOrigen = arista.getNodoOrigen().getLongitud();
	        coordenadas1.add(new Coordinate(latitudOrigen, longitudOrigen));
	        
	        double latitudDestino = arista.getNodoDestino().getLatitud();
	        double longitudDestino = arista.getNodoDestino().getLongitud();
	        coordenadas1.add(new Coordinate(latitudDestino, longitudDestino));

	    }
		MapPolygon poligono = new MapPolygonImpl(coordenadas1);
        mapa.addMapPolygon(poligono);
	     
		 
		
	}

  
	private void dibujarPuntosEnMapa() {
		GestorArchivos gestor = new GestorArchivos();
		ArrayList<Nodo> nodo= gestor.cargarJsonLista("argentinaCitys.json");
		GrafoLista TodasLasciudades = new GrafoLista(nodo);
		GrafoLista ciudadesSeleccionadas = new GrafoLista(ObtenerCiudadDeArchivo(TodasLasciudades,"Entre Ríos"));
		//obtenerCoordenadasDeCiudades();
		generarConexionGrafoCompleto(ciudadesSeleccionadas);
		dibujarPuntos();
	
		
		AGMPrim calcular= new AGMPrim();
		List<Arista> AristasMinimas = calcular.AGMPrim(ciudadesSeleccionadas);
		System.out.println(AristasMinimas);
		dibujarAristas(AristasMinimas);
		
		System.out.println("Las ciudades seleccionadas son:" + ciudadesSeleccionadas.getNodos());
		//System.out.println(nodo);
	

	}


	

	

	




	private void initialize() {
		
		frame = new JFrame();
		
		frame.setBounds(100, 100, 1019, 673);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panelMapa = new JPanel();
		panelMapa.setBounds(461, 173, 532, 450);
		frame.getContentPane().add(panelMapa);
		frame.getContentPane().setLayout(null);
		
		
		
		
		Coordinate coordinate = new Coordinate (-32.27696543467173, -59.13746600525774); // posicion del mapa
		
		
		
		// llamamos al mapa
		
		mapa = new JMapViewer();
		mapa.setBounds(572, 234, 400, 400);
		panelMapa.add(mapa);
		mapa.setDisplayPosition(coordinate, 5);
		//punto.getStyle().setColor(Color.yellow);
		
		//punto.getStyle().setColor(Color.yellow);
		

		
		JLabel lblNewLabel = new JLabel("Agregar ciudad:");
		lblNewLabel.setBounds(10, 44, 103, 31);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(110, 49, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		dibujarPuntosEnMapa();
		
	}
}

