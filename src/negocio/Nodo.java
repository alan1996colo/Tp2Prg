package negocio;

import java.io.Serializable;
import java.util.*;

public class Nodo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombreCiudad;
	private double latitud;
	private double longitud;
	private String nombreProvincia;
	transient private List<Arista> vecinos;

	public Nodo(String nombreCiudad, String nombreProvincia, double latitud, double longitud) {
		this.nombreCiudad = nombreCiudad;
		this.nombreProvincia = nombreProvincia;
		this.latitud = latitud;
		this.longitud = longitud;
		this.vecinos = new ArrayList<>();
	}
	public void inicializarVecinos() {
		this.vecinos= new ArrayList<Arista>();
	}

	public void agregarVecino(Nodo nodo, double peso) {
		vecinos.add(new Arista(nodo, peso));

	}

	public String getId() {
		return nombreCiudad;
	}

	// devuelvo los vecinos de un nodo
	public List<Arista> getVecinos() {
		return vecinos;
	}
	public void mostrarVecinos() {
		for (Arista iter:this.vecinos) {
			System.out.println(iter.getNodoDestino().toString());
		}
	}

	public String getNombreCiudad() {
		return this.nombreCiudad;
	}

	public String getNombreProvincia() {
		return this.nombreProvincia;
	}

	public double getLatitud() {
		return this.latitud;
	}

	public double getLongitud() {
		return this.longitud;
	}
	public String toString() {
		return "ciudad :"+this.nombreCiudad.toString()+" Provincia: "+this.nombreProvincia.toString()+" Latitud:"+Double.toString(this.latitud)+" Longitud:"+Double.toString(this.longitud);
	}
	
	 @Override
	    public boolean equals(Object obj) {
	        if (this == obj) return true;
	        if (obj == null) return false;
	        if (getClass() != obj.getClass()) return false;
	        final Nodo other = (Nodo)obj;
	        if (latitud != other.latitud) return false;
	        if (longitud != other.longitud) return false;
	        if (nombreCiudad == null) {
	            if (other.nombreCiudad != null) return false;
	        } else if (!nombreCiudad.equals(other.nombreCiudad)) return false;
	        if (nombreProvincia == null) {
	            if (other.nombreProvincia != null) return false;
	        } else if (!nombreProvincia.equals(other.nombreProvincia)) return false;
	        return true;
	    }

}
