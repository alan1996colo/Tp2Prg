package interfaz;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import java.util.AbstractMap;
import java.util.ArrayList;


import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import negocio.Negocio;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import java.awt.Font;

public class Presentacion {
	private JSpinner latitudSpinner;
	private JSpinner longitudSpinner;
//	private Negocio negocio = new Negocio(20, 200, 10);
	
	private Negocio negocio;
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
	private JTextField tf_CostoKm;
	private JTextField tf_CostoSup300KM;
	private JTextField tf_CambioProv;

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
		inicializarNegocio(); // REVISAR
		
		
		negocio.inicializarGrafo();
		
		
		

		eventos();
	}

	private void inicializarNegocio() {
		int CostoPorKM=Integer.parseInt(tf_CostoKm.getText());
		int CostoSup300=Integer.parseInt(tf_CostoSup300KM.getText());
		int CostoCambProv=Integer.parseInt(tf_CambioProv.getText());
		negocio.setCostoPesosxKM(CostoPorKM);
		negocio.setKmExcedido(CostoSup300);
		negocio.setCostoFijoprovDistinta(CostoCambProv);
		
		
	}

	private void crearMenuDesplegable() {

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("Archivo");
		openMenuItem = new JMenuItem("Abrir archivo");
		fileMenu.add(openMenuItem);
		saveMenu = new JMenuItem("Guardar Archivo");
		fileMenu.add(saveMenu);
		menuBar.add(fileMenu);
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
		botonAgregar.setBounds(80, 252, 170, 31);
		botonAgregar.setVisible(true);

	}

	private void crearBotonPlanificar() {
		botonPlanificar = new JButton("Planificar");
		botonPlanificar.setFont(new Font("Tahoma", Font.BOLD, 13));
		botonPlanificar.setName("Planificar");
		botonPlanificar.setSize(300, 200);
		botonPlanificar.setLayout(new FlowLayout());
		frame.getContentPane().add(botonPlanificar);
		botonPlanificar.setBounds(80, 537, 170, 31);
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
		botonUnir.setBounds(80, 447, 170, 31);
		botonUnir.setToolTipText("Haga click para unir todas las ciudades con todas(Crea un grafo completo)");
		botonUnir.setVisible(true);

	}

	private void crearBotonConectar() {
		conectar = new JButton("Conectar");
		conectar.setFont(new Font("Tahoma", Font.BOLD, 13));
		conectar.setName("Conectar");
		conectar.setSize(30, 30);
		conectar.setText("Conectar");

		frame.getContentPane().add(conectar);
		conectar.setBounds(351, 364, 100, 31);
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
		lblNewLabel.setBounds(10, 84, 67, 31);
		frame.getContentPane().add(lblNewLabel);

		tfName = new JTextField();
		tfName.setBounds(80, 90, 170, 20);
		frame.getContentPane().add(tfName);
		tfName.setColumns(10);
	}

	private void crearSeccionPrecios() {
		labelcosto = new JLabel("Precio de la conexion: " + costoenPesos);
		labelcosto.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelcosto.setBounds(663, 33, 300, 31);
		labelporcen = new JLabel("Porcentaje aumento: " + negocio.PorcentajeDeAumentoConexion());
		labelporcen.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelporcen.setBounds(663, 8, 300, 31);
		labelfijo = new JLabel("Costo fijo provincias distintas: " + negocio.CostoFijoPesosProvDiff());
		labelfijo.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelfijo.setBounds(663, 60, 300, 31);
		frame.getContentPane().add(labelcosto);
		frame.getContentPane().add(labelporcen);
		frame.getContentPane().add(labelfijo);

		JLabel lblFondo = new JLabel("Fondo");
		lblFondo.setIcon(new ImageIcon("src" + File.separator + "ImagenFondo" + File.separator + "Fondo.jpg"));
		lblFondo.setBounds(-22, 472, 1015, 623);
		frame.getContentPane().add(lblFondo);
		
		
		
		
		
		
		JLabel lblCostoPorKM = new JLabel("Costo por KM");
		lblCostoPorKM.setBounds(302, 42, 77, 14);
		frame.getContentPane().add(lblCostoPorKM);
		
		JLabel lblCostoMas300KM = new JLabel("Costo + 300KM");
		lblCostoMas300KM.setBounds(302, 69, 77, 14);
		frame.getContentPane().add(lblCostoMas300KM);
		
		JLabel lblCostoPorCamProv = new JLabel("Costo por cambio de provincia");
		lblCostoPorCamProv.setBounds(302, 93, 149, 14);
		frame.getContentPane().add(lblCostoPorCamProv);
		
		tf_CostoKm = new JTextField();
		tf_CostoKm.setBounds(473, 39, 109, 20);
		frame.getContentPane().add(tf_CostoKm);
		tf_CostoKm.setColumns(10);
		
		tf_CostoSup300KM = new JTextField();
		tf_CostoSup300KM.setBounds(473, 66, 109, 20);
		frame.getContentPane().add(tf_CostoSup300KM);
		tf_CostoSup300KM.setColumns(10);
		
		tf_CambioProv = new JTextField();
		tf_CambioProv.setBounds(473, 90, 109, 20);
		frame.getContentPane().add(tf_CambioProv);
		tf_CambioProv.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Agregar costos");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(302, 10, 148, 24);
		frame.getContentPane().add(lblNewLabel_4);
	}

	private void crearSeccionConexion() {
		JLabel lblNewLabel = new JLabel("Conexion desde");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 344, 170, 31);
		frame.getContentPane().add(lblNewLabel);

		tfConexion = new JTextField();
		tfConexion.setBounds(160, 350, 170, 20);
		frame.getContentPane().add(tfConexion);
		tfConexion.setColumns(10);

		JLabel lblNewLabel2 = new JLabel("Conexion Hacia");
		lblNewLabel2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel2.setBounds(10, 386, 170, 31);
		frame.getContentPane().add(lblNewLabel2);

		tfConexion2 = new JTextField();
		tfConexion2.setBounds(160, 392, 170, 20);
		frame.getContentPane().add(tfConexion2);
		tfConexion2.setColumns(10);
	}

	private void crearSeccionProvincia() {
		JLabel lblNewLabel = new JLabel("Provincia");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 126, 67, 31);
		frame.getContentPane().add(lblNewLabel);

		tfProv = new JTextField();
		tfProv.setBounds(80, 132, 170, 20);
		frame.getContentPane().add(tfProv);
		tfProv.setColumns(10);
	}

	private void crearSeccionLatitudYLOngitud() {

		JLabel lblLongitud = new JLabel("Longitud");
		lblLongitud.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLongitud.setBounds(10, 168, 58, 31);
		frame.getContentPane().add(lblLongitud);

		JLabel lblLatitud = new JLabel("Latitud");
		lblLatitud.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLatitud.setBounds(10, 210, 58, 31);
		frame.getContentPane().add(lblLatitud);

		// Crear un JSpinner con un SpinnerNumberModel
		latitudSpinner = new JSpinner(new SpinnerNumberModel(0, -89.99999999, 90.0, 0.000001));
		longitudSpinner = new JSpinner(new SpinnerNumberModel(0.0, -180.0, 180.0, 0.0001));

		JSpinner.NumberEditor latitudEditor = (JSpinner.NumberEditor) latitudSpinner.getEditor();
		latitudEditor.getFormat().applyPattern("#0.000000");

		JSpinner.NumberEditor longitudEditor = (JSpinner.NumberEditor) longitudSpinner.getEditor();
		longitudEditor.getFormat().applyPattern("#0.0000");
		latitudSpinner.setBounds(80, 211, 170, 31);
		longitudSpinner.setBounds(80, 169, 170, 31);
		frame.getContentPane().add(new JLabel("Latitud:"));
		frame.getContentPane().add(latitudSpinner);
		frame.getContentPane().add(new JLabel("Longitud:"));
		frame.getContentPane().add(longitudSpinner);

	}

	// ------------metodos y funcionalidades--------------------------------

	/*
	 * Cambia el estado del programa, al de un archivo json que se pase
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


	/** Quita del mapa las lineas creadas anteriormente. */
	private void eliminarVisualmenteLasConexiones() {
		this.mapa.removeAllMapPolygons();
		this.mapa.repaint();
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
	}
	
	/* Todos los eventos , acciones del usuario en un solo lugar. */
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
