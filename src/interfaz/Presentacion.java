package interfaz;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import fileManager.GestorArchivos;
import negocio.GrafoLista;
import negocio.Nodo;

import java.awt.Canvas;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Presentacion {

	private JFrame frame;
	private JMapViewer mapa;
	private JPanel panelMapa;
	private JTextField textField;
	private ArrayList<Nodo>ciudad = new ArrayList<Nodo>();
	
	
	
	
	
	

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
	
	
	
	
    private void ObtenerCiudadDeArchivo( GrafoLista ciudades ,String provincia) {
    	
		for( int i=0; i<ciudades.getTamanio();i++) {
			if(ciudades.getNodoNum(i).getNombreProvincia().equals(provincia)) {
				this.ciudad.add(ciudades.getNodoNum(i));
			}
			
		}
	}
  
	private void dibujarPuntosEnMapa() {
		GestorArchivos gestor = new GestorArchivos();
		ArrayList<Nodo> nodo= gestor.cargarJsonLista("argentinaCitys.json");
		GrafoLista ciudades = new GrafoLista(nodo);
		ObtenerCiudadDeArchivo(ciudades,"Buenos Aires");
		System.out.println(nodo);
		
		for(Nodo coordenadas :ciudad ) {
			double latitud = coordenadas.getLatitud();
			double longitud = coordenadas.getLongitud();
			System.out.println(latitud);
			String NombreCiudad = coordenadas.getNombreCiudad();
			Coordinate coordinate = new Coordinate (latitud, longitud);
			
			MapMarker punto = new MapMarkerDot(NombreCiudad,coordinate);	
			punto.getStyle().setBackColor(Color.black);
			punto.getStyle().setColor(Color.yellow);
			mapa.addMapMarker(punto);
			
			
		}
		
	}
	

	

	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1019, 673);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panelMapa = new JPanel();
		panelMapa.setBounds(461, 173, 532, 450);
		frame.getContentPane().add(panelMapa);
		frame.getContentPane().setLayout(null);
		
		
		
		Coordinate coordinate = new Coordinate (-38.416097, -63.616672);
		
		MapMarker punto = new MapMarkerDot("Aqui",coordinate);
		punto.getStyle().setBackColor(Color.red);
		
		
		
		
		// agrego poligono "lineas"
		ArrayList<Coordinate> coordenadas = new  ArrayList<Coordinate>();
		ArrayList<Coordinate> puntos = new  ArrayList<Coordinate>();
//		coordenadas.add(new Coordinate(-34.521,-58.7008));
//		coordenadas.add(new Coordinate(-34.546,-58.719));
//		coordenadas.add(new Coordinate(-34.521,-58.737));
//		coordenadas.add(new Coordinate(-34.559,-58.725));
//		coordenadas.add(new Coordinate(-34.521,-58.7008));
//		
		
		MapPolygon poligono = new MapPolygonImpl(coordenadas);
		
		
		// llamamos al mapa
		
		mapa = new JMapViewer();
		mapa.setBounds(572, 234, 400, 400);
		panelMapa.add(mapa);
		mapa.setDisplayPosition(coordinate, 5);
		//punto.getStyle().setColor(Color.yellow);
		mapa.addMapMarker(punto);
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
