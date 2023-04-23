package negocio;

import java.util.*;

public class Nodo {
    private String nombreCiudad;
    private String nombreProvincia;
    private double latitud;
    private double longitud;
    private List<Arista> vecinos;

    public Nodo(String nombreCiudad, String nombreProvincia, double latitud, double longitud ) {
        this.nombreCiudad = nombreCiudad;
        this.nombreProvincia = nombreProvincia;
        this.latitud=latitud;
        this.longitud=longitud;
        this.vecinos = new ArrayList<>();
    }

    public void agregarVecino(Nodo nodo, double peso) {
        vecinos.add(new Arista(nodo, peso));
        
    }    

    public String getId() {
        return nombreCiudad	;
    }

    //devuelvo los vecinos de un nodo
    public List<Arista> getVecinos() {
        return vecinos;
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
    
}
