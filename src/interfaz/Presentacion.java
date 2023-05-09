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
import javax.swing.ImageIcon;
import java.awt.Font;

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
	private JMenuItem saveMenu;
	private JMenuItem openMenuItem;
	private double costoenPesos = 0;

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
		saveMenu = new JMenuItem("Guardar Archivo");
		fileMenu.add(saveMenu);

		// Agregar el menú "File" a la barra de menú
		menuBar.add(fileMenu);

		// Agregar la barra de menú al frame
		frame.setJMenuBar(menuBar);

	}

	// --------------Botones y secciones de interfaz grafica---------------
	private void crearBotonAgregarPunto() {
		botonAgregar = new JButton("Haz click aquí");
		botonAgregar.setFont(new Font("Tahoma", Font.BOLD, 13));
		botonAgregar.setName("Botón de ejemplo");
		botonAgregar.setSize(300, 200);
		botonAgregar.setText("Agregar punto");
		botonAgregar.setLayout(new FlowLayout());
		frame.getContentPane().add(botonAgregar);
		botonAgregar.setBounds(80, 200, 170, 31);
		botonAgregar.setVisible(true);

	}

	private void crearBotonPlanificar() {
		botonPlanificar = new JButton("Planificar");
		botonPlanificar.setFont(new Font("Tahoma", Font.BOLD, 13));
		botonPlanificar.setName("Planificar");
		botonPlanificar.setSize(300, 200);
		botonPlanificar.setLayout(new FlowLayout());
		frame.getContentPane().add(botonPlanificar);
		botonPlanificar.setBounds(80, 500, 170, 31);
		botonPlanificar.setToolTipText("Haga click para Mostrar la solucion \n(Crea un arbol generador minimo)");
		botonPlanificar.setVisible(true);

	}

	private void crearBotonUnirTodos() {
		botonUnir = new JButton("Unir todos");
		botonUnir.setFont(new Font("Tahoma", Font.BOLD, 13));
		botonUnir.setName("Unir todo");
		botonUnir.setSize(300, 200);
		botonUnir.setLayout(new FlowLayout());
		frame.getContentPane().add(botonUnir);
		botonUnir.setBounds(80, 400, 170, 31);
		botonUnir.setToolTipText("Haga click para unir todas las ciudades con todas(Crea un grafo completo)");
		botonUnir.setVisible(true);

	}

	private void crearBotonConectar() {
		conectar = new JButton("Conectar");
		conectar.setFont(new Font("Tahoma", Font.BOLD, 13));
		conectar.setName("Conectar");
		// conectar.setLayout(new FlowLayout());
		conectar.setSize(30, 30);
		conectar.setText("Conectar");

		frame.getContentPane().add(conectar);
		conectar.setBounds(350, 300, 100, 31);
		conectar.setVisible(true);

	}

	private void crearHead() {
		JLabel lbl = new JLabel("Agregar Ciudad");
		lbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl.setBounds(10, 7, 170, 31);
		frame.getContentPane().add(lbl);
	}

	private void launchWindows() {
		frame = new JFrame("Conectador de localidades");

		frame.setBounds(100, 100, 1019, 673);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panelMapa = new JPanel();
		panelMapa.setBounds(461, 171, 502, 430);
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
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 44, 170, 31);
		frame.getContentPane().add(lblNewLabel);

		tfName = new JTextField();
		tfName.setBounds(80, 50, 170, 20);
		frame.getContentPane().add(tfName);
		tfName.setColumns(10);
	}

	private void crearSeccionPrecios() {
		labelcosto = new JLabel("Precio de la conexion: " + costoenPesos);
		labelcosto.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelcosto.setBounds(400, 44, 300, 31);
		labelporcen = new JLabel("Porcentaje aumento: " + negocio.PorcentajeDeAumentoConexion());
		labelporcen.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelporcen.setBounds(400, 24, 300, 31);
		labelfijo = new JLabel("Costo fijo provincias distintas: " + negocio.CostoFijoPesosProvDiff());
		labelfijo.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelfijo.setBounds(400, 64, 300, 31);
		frame.getContentPane().add(labelcosto);
		frame.getContentPane().add(labelporcen);
		frame.getContentPane().add(labelfijo);

		JLabel lblFondo = new JLabel("Fondo");
		lblFondo.setIcon(new ImageIcon("src" + File.separator + "ImagenFondo" + File.separator + "Fondo.jpg"));
		lblFondo.setBounds(0, 0, 1015, 623);
		frame.getContentPane().add(lblFondo);
	}

	private void crearSeccionConexion() {
		JLabel lblNewLabel = new JLabel("Conexion desde");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 294, 170, 31);
		frame.getContentPane().add(lblNewLabel);

		tfConexion = new JTextField();
		tfConexion.setBounds(160, 300, 170, 20);
		frame.getContentPane().add(tfConexion);
		tfConexion.setColumns(10);

		JLabel lblNewLabel2 = new JLabel("Conexion Hacia");
		lblNewLabel2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel2.setBounds(10, 320, 170, 31);
		frame.getContentPane().add(lblNewLabel2);

		tfConexion2 = new JTextField();
		tfConexion2.setBounds(160, 326, 170, 20);
		frame.getContentPane().add(tfConexion2);
		tfConexion2.setColumns(10);
	}

	private void crearSeccionProvincia() {
		JLabel lblNewLabel = new JLabel("Provincia");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 70, 170, 31);
		frame.getContentPane().add(lblNewLabel);

		tfProv = new JTextField();
		tfProv.setBounds(80, 76, 170, 20);
		frame.getContentPane().add(tfProv);
		tfProv.setColumns(10);
	}

	private void crearSeccionLatitudYLOngitud() {

		JLabel lblLongitud = new JLabel("Longitud");
		lblLongitud.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLongitud.setBounds(10, 100, 170, 31);
		frame.getContentPane().add(lblLongitud);

		JLabel lblLatitud = new JLabel("Latitud");
		lblLatitud.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLatitud.setBounds(10, 150, 170, 31);
		frame.getContentPane().add(lblLatitud);

		// Crear un JSpinner con un SpinnerNumberModel
		latitudSpinner = new JSpinner(new SpinnerNumberModel(0, -89.99999999, 90.0, 0.000001));
		longitudSpinner = new JSpinner(new SpinnerNumberModel(0.0, -180.0, 180.0, 0.0001));

		JSpinner.NumberEditor latitudEditor = (JSpinner.NumberEditor) latitudSpinner.getEditor();
		latitudEditor.getFormat().applyPattern("#0.000000");

		JSpinner.NumberEditor longitudEditor = (JSpinner.NumberEditor) longitudSpinner.getEditor();
		longitudEditor.getFormat().applyPattern("#0.0000");

		// Establecer el tamaño y la posición de los spinners
		latitudSpinner.setBounds(80, 151, 170, 31);
		longitudSpinner.setBounds(80, 101, 170, 31);

		// Agregar los spinners al panel
		frame.getContentPane().add(new JLabel("Latitud:"));
		frame.getContentPane().add(latitudSpinner);
		frame.getContentPane().add(new JLabel("Longitud:"));
		frame.getContentPane().add(longitudSpinner);

	}

	// ------------metodos y funcionalidades--------------------------------

	/*
	 * 
	 * */
	public void cambiarSesion(String fname) {
		this.mapa.removeAllMapPolygons();
		this.mapa.removeAllMapMarkers();
		this.mapa.repaint();
		this.negocio.cambiarGrafoPor(fname);
		for (String localidad : negocio.todasLasLocalidades()) {
			dibujarUnPunto(getCoord(localidad).getLon(), getCoord(localidad).getLat(), localidad);
		}

	}

	/****
	 * Ingresa la localidad, devuelve true o false si se pudo o no
	 ***/
	public boolean IngresarLocalidad(String localidad) {
		return false;
	}

	/** Quita del mapa las lineas creadas anteriormente. */
	private void eliminarVisualmenteLasConexiones() {
		this.mapa.removeAllMapPolygons();
		this.mapa.repaint();
	}

	/***
	 * Permite al usuario modificar la solucion, este metodo va meter mano en la
	 * clase negocio
	 **/
	public void ModificarSolucion() {// desarrollar...
	}

	private void actualizarPrecio() {
		SwingUtilities.invokeLater(() -> {
			// Actualizar el texto del JLabel
			labelcosto.setText("El precio es" + negocio.darCostoEnpesos());
			labelporcen.setText("El porcentaje de aumento es" + negocio.PorcentajeDeAumentoConexion());
			labelfijo.setText("El costo fijo es es" + negocio.CostoFijoPesosProvDiff());

		});
	}

	private void actualizarPrecioSinPlanificar() {
		SwingUtilities.invokeLater(() -> {
			// Actualizar el texto del JLabel
			labelcosto.setText("El precio es" + negocio.darCostoEnpesosSinPLanificar());
			labelporcen.setText("El porcentaje de aumento es" + negocio.PorcentajeDeAumentoConexionSinPlanificar());
			labelfijo.setText("El costo fijo es es" + negocio.CostoFijoPesosProvDiff());

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

	private void dibujarTodasLasConexiones() {
		ArrayList<String> todas = negocio.todasLasLocalidades();
		for (String origen : todas) {
			for (String destino : todas) {
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
	} /* Todos los eventos , acciones del usuario en un solo lugar. */

	private void eventos() {

		JFileChooser fc = new JFileChooser();
		File initialDir = new File("src/fileManager/");
		fc.setCurrentDirectory(initialDir); // Establecer directorio inicial
		openMenuItem.addActionListener(ev -> {
			int returnVal = fc.showOpenDialog(frame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				try {
					// System.out.println(file.getPath());
					cambiarSesion(file.getName());
					// Desde aca hay que seguir para fibujar todos los puntos

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Operation is CANCELLED :(");
			}
		});

		saveMenu.addActionListener(ev -> {
			fc.setDialogTitle("Especifique el archivo a guardar");
			int returnVal = fc.showSaveDialog(frame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File fileToSave = fc.getSelectedFile();
				try {
					negocio.guardarSesion(fileToSave.getName());
					// Desde acá hay que seguir para dibujar todos los puntos
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				// El usuario canceló el diálogo de selección de archivo
				System.out.println("Selección de archivo cancelada");
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
				actualizarPrecioSinPlanificar();
				// Dentro de un evento o hilo diferente al de la UI

			}
		});
		botonUnir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				negocio.generarGrafoCompleto();
				dibujarTodasLasConexiones();
				actualizarPrecioSinPlanificar();

			}
		});
		botonPlanificar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				eliminarVisualmenteLasConexiones();
				planificar();
				actualizarPrecio();

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
