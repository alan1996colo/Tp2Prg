package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Presentacion {

	private JFrame frame;

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
	
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(25, 32, 46, 14);
		frame.getContentPane().add(lblNewLabel);
	}
	
	
	
	
	
	
}
