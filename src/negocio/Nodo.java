package negocio;

import java.io.Serializable;
import java.util.*;

import org.openstreetmap.gui.jmapviewer.Coordinate;

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

	public void agregarVecino(Nodo nodoOrigen, Nodo nodoDestino, double peso) {
		if(this.vecinos.contains(new Arista(nodoOrigen,nodoDestino,peso))) {
			throw new IllegalArgumentException("La arista ya existe");
		}
		vecinos.add(new Arista(nodoOrigen, nodoDestino, peso));

	}
	/*Recorre los vecinos y elimina la arista con el nombre pasado.*/
	public void quitarArista(String name) {
	    Iterator<Arista> iter = this.vecinos.iterator();
	    while (iter.hasNext()) {
	        Arista a = iter.next();
	        if (a.getNodoDestino().getNombreCiudad().equals(name)) {
	            iter.remove();
	        }
	    }
	}


	public String getId() {
		return nombreCiudad;
	}

	// devuelvo los vecinos de un nodo
	public List<Arista> getArista() {
		return vecinos;
	}
	public void mostrarVecinos() {
		for (Arista iter:this.vecinos) {
			System.out.println(iter.getNodoDestino().toString());
		}
	}
	public void mostrarAristas() {
		for(Arista ar:this.vecinos) {
			System.out.println(ar.toString());
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
	 
	 public boolean equalsProv(Nodo n) {		 
		 return this.getNombreProvincia().equals(n.getNombreProvincia());
	 }

	

}
