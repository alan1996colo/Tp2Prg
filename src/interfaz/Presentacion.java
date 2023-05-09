package interfaz;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.util.Random;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

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
	private JLabel labelcosto;
	private JLabel labelporcen;
	private JLabel labelfijo;
	private JButton botonPlanificar;
	private JButton botonUnir;
	private JButton conectar;
	JMenuItem openMenuItem;
	private double costoenPesos=0;
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

	public Presentacion() {

		initialize();
		negocio.inicializarGrafo();
		
		eventos();
	}

	private void crearMenuDesplegable() {

		// Crear la barra de menú
		JMenuBar menuBar = new JMenuBar();

		// Crear el menú "File"
		JMenu fileMenu = new JMenu("Archivo");

		// Crear la opción "Open" y agregarla al menú "File"
		 openMenuItem = new JMenuItem("Abrir archivo");
		fileMenu.add(openMenuItem);

		// Crear la opción "Exit" y agregarla al menú "File"
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		fileMenu.add(exitMenuItem);

		// Agregar el menú "File" a la barra de menú
		menuBar.add(fileMenu);

		// Agregar la barra de menú al frame
		frame.setJMenuBar(menuBar);

	}

	// --------------Botones y secciones de interfaz grafica---------------
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

	private void crearBotonPlanificar() {
		botonPlanificar = new JButton("Planificar");
		botonPlanificar.setName("Planificar");
		botonPlanificar.setSize(300, 200);
		botonPlanificar.setLayout(new FlowLayout());
		frame.add(botonPlanificar);
		botonPlanificar.setBounds(80, 500, 170, 31);
		botonPlanificar.setToolTipText("Haga click para Mostrar la solucion \n(Crea un arbol generador minimo)");
		botonPlanificar.setVisible(true);

	}

	private void crearBotonUnirTodos() {
		botonUnir = new JButton("Unir todos");
		botonUnir.setName("Unir todo");
		botonUnir.setSize(300, 200);
		botonUnir.setLayout(new FlowLayout());
		frame.add(botonUnir);
		botonUnir.setBounds(80, 400, 170, 31);
		botonUnir.setToolTipText("Haga click para unir todas las ciudades con todas(Crea un grafo completo)");
		botonUnir.setVisible(true);

	}

	private void crearBotonConectar() {
		conectar = new JButton("Conectar");
		conectar.setName("Conectar");
		// conectar.setLayout(new FlowLayout());
		conectar.setSize(30, 30);
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

		this.mapa = new JMapViewer();
		this.mapa.setBounds(572, 234, 400, 400);
		panelMapa.add(mapa);
		this.mapa.setDisplayPosition(coordinate, 5);
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
	private void crearSeccionPrecios() {
		 labelcosto = new JLabel("Precio de la conexion: "+costoenPesos);
		labelcosto.setBounds(400, 44, 300, 31);
		labelporcen= new JLabel("Porcentaje aumento: "+negocio.PorcentajeDeAumentoConexion());
		labelporcen.setBounds(400, 24, 300, 31);
		labelfijo= new JLabel("Costo fijo provincias distintas: "+negocio.CostoFijoPesosProvDiff());
		labelfijo.setBounds(400, 64, 300, 31);
		frame.getContentPane().add(labelcosto);
		frame.getContentPane().add(labelporcen);
		frame.getContentPane().add(labelfijo);
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

	// ------------metodos y funcionalidades--------------------------------

	/****
	 * Ingresa la localidad, devuelve true o false si se pudo o no
	 ***/
	public boolean IngresarLocalidad(String localidad) {
		return false;
	}

	/** Quita del mapa las lineas creadas anteriormente. */
	private void eliminarVisualmenteLasConexiones() {
		List<MapPolygon> polygons = new ArrayList<>(mapa.getMapPolygonList());

		// Eliminar cada polígono de la lista
		for (MapPolygon polygon : polygons) {
			mapa.removeMapPolygon(polygon);
		}
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

	private void actualizarPRecio() {	SwingUtilities.invokeLater(() -> {
	    // Actualizar el texto del JLabel
		labelcosto.setText("El precio es"+negocio.darCostoEnpesos());
		labelporcen.setText("El porcentaje de aumento es"+negocio.PorcentajeDeAumentoConexion());
		labelfijo.setText("El costo fijo es es"+negocio.CostoFijoPesosProvDiff());
		
	});
	}
	public void planificar() {
		for (AbstractMap.SimpleEntry<String, String> entrada : negocio.CrearArbolGeneradorMinimo()) {
			dibujarConexion(entrada.getKey(), entrada.getValue());
		}
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

	private ArrayList<Nodo> ObtenerCiudadDeArchivo(GrafoLista ciudades, String provincia) {

		for (int i = 0; i < ciudades.getTamanio(); i++) {
			if (ciudades.getNodoNum(i).getNombreProvincia().equals(provincia)) {
				this.ciudad.add(ciudades.getNodoNum(i));

			}

		}
		return ciudad;
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
		// System.out.println(coordenadas);

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

	private void dibujarTodasLasConexiones() {

		for (String origen : negocio.todasLasLocalidades()) {
			for (String destino : negocio.todasLasLocalidades()) {
				if (!origen.equals(destino)) {
					dibujarConexion(origen, destino);
				}
			}
		}

	}

	private void dibujarConexion(String localidadOrigen, String localidadDestino) {
		ArrayList<Coordinate> coordenadas = new ArrayList<Coordinate>();
		coordenadas.add(getCoord(localidadOrigen));
		coordenadas.add(getCoord(localidadDestino));
		coordenadas.add(coordenadaIntermedia(getCoord(localidadOrigen), getCoord(localidadDestino)));
		MapPolygon poligono = new MapPolygonImpl(coordenadas);
		mapa.addMapPolygon(poligono);

	}

	/* Dado el nombre de ciudad devuelve las coordenadas correspondientes. */
	private Coordinate getCoord(String localidad) {
		return negocio.getCoordenadasFrom(localidad);
	}

	public static Coordinate coordenadaIntermedia(Coordinate coordenada1, Coordinate coordenada2) {
		double latitudMedia = (coordenada1.getLat() + coordenada2.getLat()) / 2.0;
		double longitudMedia = (coordenada1.getLon() + coordenada2.getLon()) / 2.0;
		return new Coordinate(latitudMedia, longitudMedia);
	}

	private void dibujarPuntosEnMapa() {
		GestorArchivos gestor = new GestorArchivos();
		ArrayList<Nodo> nodo = gestor.cargarJsonLista("argentinaCitys.json");
		GrafoLista TodasLasciudades = new GrafoLista(nodo);
		GrafoLista ciudadesSeleccionadas = new GrafoLista(ObtenerCiudadDeArchivo(TodasLasciudades, "Entre Ríos"));
		// obtenerCoordenadasDeCiudades();
		// generarConexionGrafoCompleto(ciudadesSeleccionadas);
		dibujarPuntos();

		AGMPrim calcular = new AGMPrim();
		List<Arista> AristasMinimas = calcular.AGMPrim(ciudadesSeleccionadas);
		System.out.println(AristasMinimas);
		dibujarAristas(AristasMinimas);

		// System.out.println("Las ciudades seleccionadas son:" +
		// ciudadesSeleccionadas.getNodos());
		// System.out.println(nodo);

	}

	/* Todos los eventos , acciones del usuario en un solo lugar. */
	private void eventos() {JFileChooser fc = new JFileChooser();
	GestorArchivos gestor=new GestorArchivos();
	File initialDir = new File("src/fileManager/");
	fc.setCurrentDirectory(initialDir); // Establecer directorio inicial
	openMenuItem.addActionListener(ev -> {
	  int returnVal = fc.showOpenDialog(frame);
	  if (returnVal == JFileChooser.APPROVE_OPTION) {
	    File file = fc.getSelectedFile();
	    try {
	      BufferedReader input = new BufferedReader(new InputStreamReader(
	          new FileInputStream(file)));
	      		//System.out.println(file.getPath());
	      this.negocio.cambiarGrafoPor(file.getName());
	      //Desde aca hay que seguir para fibujar todos los puntos
	      	
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  } else {
	    System.out.println("Operation is CANCELLED :(");
	  }
	});


		
		
		
		botonAgregar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				double latitud = Double.parseDouble(latitudSpinner.getValue().toString());
				double longitud = Double.parseDouble(longitudSpinner.getValue().toString());
				String name = tfName.getText();
				String prov = tfProv.getText();
				dibujarUnPunto(longitud, latitud, name);
				negocio.registrarLocalidad(name, prov, latitud, longitud);

			}
		});

		conectar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String origen = tfConexion.getText();
				String destino = tfConexion2.getText();
				System.out.println("Agregar conexion fue: " + negocio.agregarConexion(origen, destino));
				dibujarConexion(origen, destino);
				actualizarPRecio();
				// Dentro de un evento o hilo diferente al de la UI
			
				
			}
		});
		botonUnir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				negocio.generarGrafoCompleto();
				dibujarTodasLasConexiones();
				actualizarPRecio();
				
			}
		});
		botonPlanificar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				eliminarVisualmenteLasConexiones();
				planificar();
				actualizarPRecio();
				

			}
		});
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
		crearBotonUnirTodos();
		crearMenuDesplegable();
		crearBotonPlanificar();
		crearSeccionPrecios();

	}
}
