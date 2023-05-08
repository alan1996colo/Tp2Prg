package interfaz;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
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

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class Presentacion {
	private JSpinner latitudSpinner;
	private JSpinner longitudSpinner;
	private Negocio negocio = new Negocio(2, 3, 4);
	private JFrame frame;
	private JMapViewer mapa;
	private JPanel panelMapa;
	private JTextField tfName;
	private JTextField tfConexion;
	private JTextField tfConexion2;
	private JTextField tfProv;
	private JButton botonAgregar;
	private JButton conectar;
	private ArrayList<Nodo> ciudad = new ArrayList<Nodo>(); // puntos de las ciudades

	private GrafoLista ciudadesSeleccionadas = new GrafoLista();
	private List<Arista> aristasDibujadas = new ArrayList<>();

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

	private void crearBotonAgregarPunto() {
		botonAgregar = new JButton("Haz click aquí");
		botonAgregar.setName("Botón de ejemplo");
		botonAgregar.setSize(300, 200);
		botonAgregar.setText("Agregar punto");
		botonAgregar.setLayout(new FlowLayout());
		frame.add(botonAgregar);
		botonAgregar.setBounds(80, 200, 170, 31);
		botonAgregar.setVisible(true);

	}
	private void crearBotonConectar() {
		conectar = new JButton("Conectar");
		conectar.setName("Conectar");
		//conectar.setLayout(new FlowLayout());
		conectar.setSize(30,30);
		conectar.setText("Conectar");
		
		frame.add(conectar);
		conectar.setBounds(350, 300, 100, 31);
		conectar.setVisible(true);

	}
	
	private void crearHead() {
		JLabel lbl = new JLabel("Agregar Ciudad:");
		lbl.setBounds(80, 12, 170, 31);
		frame.getContentPane().add(lbl);
	}

	private void launchWindows() {
		frame = new JFrame("Conectador de localidades");

		frame.setBounds(100, 100, 1019, 673);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panelMapa = new JPanel();
		panelMapa.setBounds(461, 173, 532, 450);
		frame.getContentPane().add(panelMapa);
		frame.getContentPane().setLayout(null);
	}

	private void crearSeccionMapa() {
		Coordinate coordinate = new Coordinate(-32.27696543467173, -59.13746600525774); // posicion del mapa

		mapa = new JMapViewer();
		mapa.setBounds(572, 234, 400, 400);
		panelMapa.add(mapa);
		mapa.setDisplayPosition(coordinate, 5);
	}

	private void crearSeccionNombre() {
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(10, 44, 170, 31);
		frame.getContentPane().add(lblNewLabel);

		tfName = new JTextField();
		tfName.setBounds(160, 49, 170, 20);
		frame.getContentPane().add(tfName);
		tfName.setColumns(10);
	}
	private void crearSeccionConexion() {
		JLabel lblNewLabel = new JLabel("Conexion desde:");
		lblNewLabel.setBounds(10, 300, 170, 31);
		frame.getContentPane().add(lblNewLabel);

		tfConexion = new JTextField();
		tfConexion.setBounds(160, 300, 170, 20);
		frame.getContentPane().add(tfConexion);
		tfConexion.setColumns(10);
		
		JLabel lblNewLabel2 = new JLabel("Conexion Hacia:");
		lblNewLabel2.setBounds(10, 320, 170, 31);
		frame.getContentPane().add(lblNewLabel2);

		tfConexion2 = new JTextField();
		tfConexion2.setBounds(160, 320, 170, 20);
		frame.getContentPane().add(tfConexion2);
		tfConexion2.setColumns(10);
	}

	private void crearSeccionProvincia() {
		JLabel lblNewLabel = new JLabel("Provincia:");
		lblNewLabel.setBounds(10, 70, 170, 31);
		frame.getContentPane().add(lblNewLabel);

		tfProv = new JTextField();
		tfProv.setBounds(160, 70, 170, 20);
		frame.getContentPane().add(tfProv);
		tfProv.setColumns(10);
	}
	private void crearSeccionLatitudYLOngitud() {

		JLabel lblLongitud = new JLabel("Longitud");
		lblLongitud.setBounds(10, 100, 170, 31);
		frame.getContentPane().add(lblLongitud);

		JLabel lblLatitud = new JLabel("Latitud");
		lblLatitud.setBounds(10, 150, 170, 31);
		frame.getContentPane().add(lblLatitud);

		// Crear un JSpinner con un SpinnerNumberModel
		latitudSpinner = new JSpinner(new SpinnerNumberModel(0, -89.99999999, 90.0, 0.000001));
		longitudSpinner = new JSpinner(new SpinnerNumberModel(0.0, -180.0, 180.0, 0.0001));

		// Obtener el editor del JSpinner y establecer su formato
		JSpinner.NumberEditor latitudEditor = (JSpinner.NumberEditor) latitudSpinner.getEditor();
		latitudEditor.getFormat().applyPattern("#0.000000");

		JSpinner.NumberEditor longitudEditor = (JSpinner.NumberEditor) longitudSpinner.getEditor();
		longitudEditor.getFormat().applyPattern("#0.0000");

		// Establecer el tamaño y la posición de los spinners
		latitudSpinner.setBounds(140, 150, 170, 31);
		longitudSpinner.setBounds(140, 100, 170, 31);

		// Agregar los spinners al panel
		frame.getContentPane().add(new JLabel("Latitud:"));
		frame.getContentPane().add(latitudSpinner);
		frame.getContentPane().add(new JLabel("Longitud:"));
		frame.getContentPane().add(longitudSpinner);

	}

	/****
	 * Ingresa la localidad, devuelve true o false si se pudo o no
	 ***/
	public boolean IngresarLocalidad(String localidad) {
		return false;
	}

	/**
	 * Funcion que utiliza la herrramienta map viewer para visualizar los puntos
	 **/
	public void llamarMapViewer() {
	}

	/***
	 * Este metodo va pedir a la clase negocio el camino minimo, y va intentar
	 * mostrar el menor camino por pantalla
	 **/
	public void DameElArbolGeneradorMInimo() {
	}

	/***
	 * Permite al usuario modificar la solucion, este metodo va meter mano en la
	 * clase negocio
	 **/
	public void ModificarSolucion() {
	}

	private void dibujarUnPunto(double longitud, double latitud, String name) {
		Coordinate coordinate = new Coordinate(latitud, longitud);
		MapMarkerDot punto = new MapMarkerDot(coordinate);
		punto.setName(name);
		punto.getStyle().setBackColor(Color.black);
		punto.getStyle().setColor(Color.yellow);
		mapa.addMapMarker(punto);
	}

	private void dibujarPuntos() {
		for (Nodo coordenadas : ciudad) {
			double latitud = coordenadas.getLatitud();
			double longitud = coordenadas.getLongitud();
			// System.out.println(latitud);
			String NombreCiudad = coordenadas.getNombreCiudad();
			Coordinate coordinate = new Coordinate(latitud, longitud);
			MapMarker punto = new MapMarkerDot(coordinate);
			punto.getStyle().setBackColor(Color.black);
			punto.getStyle().setColor(Color.yellow);
			mapa.addMapMarker(punto);
		}
	}

//	private void obtenerCoordenadasDeCiudades() {
//		for(int i=0;i<ciudad.size();i++) {
//			coordenadas.add(new Coordinate(ciudad.get(i).getLatitud(),ciudad.get(i).getLongitud()));
//		}
//		
//	}
	private ArrayList<Nodo> ObtenerCiudadDeArchivo(GrafoLista ciudades, String provincia) {

		for (int i = 0; i < ciudades.getTamanio(); i++) {
			if (ciudades.getNodoNum(i).getNombreProvincia().equals(provincia)) {
				this.ciudad.add(ciudades.getNodoNum(i));

			}

		}
		return ciudad;
	}

	private void generarConexionGrafoCompleto(GrafoLista ciudadesSeleccionadas) {

		negocio.generarGrafoCompleto(ciudadesSeleccionadas);
	}

	private void dibujarAristas(List<Arista> aristas) {

		ArrayList<Coordinate> coordenadas = new ArrayList<Coordinate>();
		Nodo nodoOrigen = null;
		Nodo nodoDestino = null;
		for (Arista arista : aristas) {
			nodoOrigen = arista.getNodoOrigen();
			nodoDestino = arista.getNodoDestino();

			if (yaSeDibujaronLosNodos(nodoOrigen, nodoDestino, aristasDibujadas)) {
				continue; // No se dibuja la arista para evitar triángulos
			}

			aristasDibujadas.add(arista);
		}
		coordenadas.add(new Coordinate(nodoOrigen.getLatitud(), nodoOrigen.getLongitud()));
		coordenadas.add(new Coordinate(nodoDestino.getLatitud(), nodoDestino.getLongitud()));
		MapPolygon poligono = new MapPolygonImpl(coordenadas);
		System.out.println(coordenadas);

		mapa.addMapPolygon(poligono);
		mapa.addMapPolygon(poligono);

	}

	private boolean yaSeDibujaronLosNodos(Nodo nodo1, Nodo nodo2, List<Arista> aristasDibujadas) {
		for (Arista arista : aristasDibujadas) {
			Nodo aristaNodo1 = arista.getNodoOrigen();
			Nodo aristaNodo2 = arista.getNodoDestino();

			if ((nodo1.equals(aristaNodo1) && nodo2.equals(aristaNodo2))
					|| (nodo1.equals(aristaNodo2) && nodo2.equals(aristaNodo1))) {
				return true; // Ya se dibujó una arista que conecta estos nodos
			}
		}
		return false;
	}
	private Coordinate getCoord(String localidad) {
		return negocio.getCoordenadasFrom(localidad);
	}
	
	private void dibujarArista(String localidadOrigen, String localidadDestino) {
		ArrayList<Coordinate> coordenadas = new ArrayList<Coordinate>();
		coordenadas.add(getCoord(localidadOrigen));
		coordenadas.add(getCoord(localidadDestino));
		MapPolygon poligono = new MapPolygonImpl(coordenadas);
		System.out.println(coordenadas);

		mapa.addMapPolygon(poligono);
	}

	private void dibujarPuntosEnMapa() {
		GestorArchivos gestor = new GestorArchivos();
		ArrayList<Nodo> nodo = gestor.cargarJsonLista("argentinaCitys.json");
		GrafoLista TodasLasciudades = new GrafoLista(nodo);
		GrafoLista ciudadesSeleccionadas = new GrafoLista(ObtenerCiudadDeArchivo(TodasLasciudades, "Entre Ríos"));
		// obtenerCoordenadasDeCiudades();
		generarConexionGrafoCompleto(ciudadesSeleccionadas);
		dibujarPuntos();

		AGMPrim calcular = new AGMPrim();
		List<Arista> AristasMinimas = calcular.AGMPrim(ciudadesSeleccionadas);
		System.out.println(AristasMinimas);
		dibujarAristas(AristasMinimas);

		// System.out.println("Las ciudades seleccionadas son:" +
		// ciudadesSeleccionadas.getNodos());
		// System.out.println(nodo);

	}

	private void initialize() {

		launchWindows();
		crearSeccionMapa();
		crearHead();
		crearSeccionNombre();
		crearSeccionProvincia();
		crearSeccionLatitudYLOngitud();
		crearBotonAgregarPunto();
		crearSeccionConexion();
		crearBotonConectar();
		negocio.inicializarGrafo();
		botonAgregar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				double latitud = Double.parseDouble(latitudSpinner.getValue().toString());
				double longitud = Double.parseDouble(longitudSpinner.getValue().toString());
				String name = tfName.getText();
				String prov=tfProv.getText();
				dibujarUnPunto(longitud, latitud, name);
				negocio.registrarLocalidad(name,prov,latitud,longitud);
				
			}
		});
		
		conectar.addActionListener(new ActionListener() {
			String origen=tfConexion.getText();
			String destino=tfConexion2.getText();

			public void actionPerformed(ActionEvent e) {
				negocio.agregarConexion(origen, destino);
				dibujarArista(origen,destino);
				
			}
		});

	}
}
