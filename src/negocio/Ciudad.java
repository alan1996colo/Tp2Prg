package grafoLista;

public class Ciudad {
	private String nombreCiudad;
	private String nombreProvincia;
	private double latitud;
	private double longitud;
	
	
	
	public Ciudad (String nombreCiudad, String nombreProvincia, double latitud, double longitud ) {
		this.nombreCiudad=nombreCiudad;
		this.nombreProvincia=nombreProvincia;
		this.latitud=latitud;
		this.longitud=longitud;
	}
	
	public String getNombreCiudad(Ciudad ciudad) {
		return ciudad.nombreCiudad;
	}
	public String getNombreProvincia(Ciudad ciudad) {
		return ciudad.nombreProvincia;
	}
	public double getLatitud(Ciudad ciudad) {
		return ciudad.latitud;
	}
	public double getLongitud(Ciudad ciudad) {
		return ciudad.longitud;
	}
	
	
    public static double distanciaEntrePuntos(Ciudad ciudad1, Ciudad ciudad2) {
    	double latitud1=ciudad1.latitud;
    	double latitud2=ciudad2.latitud;
    	double longitud1=ciudad1.longitud;
    	double longitud2=ciudad2.longitud;
    	final int radioTierra = 6371; // Radio de la Tierra en kilómetros
        double latitudDistancia = Math.toRadians(latitud2 - latitud1);
        double longitudDistancia = Math.toRadians(longitud2 - longitud1);
        double a = Math.sin(latitudDistancia / 2) * Math.sin(latitudDistancia / 2)
                + Math.cos(Math.toRadians(latitud1)) * Math.cos(Math.toRadians(latitud2))
                * Math.sin(longitudDistancia / 2) * Math.sin(longitudDistancia / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distancia = radioTierra * c;
        return distancia;
    }
	
	
	
	
	
	/*
    public static double distanciaEntrePuntos(double latitud1, double longitud1, double latitud2, double longitud2) {
        final int radioTierra = 6371; // Radio de la Tierra en kilómetros
        double latitudDistancia = Math.toRadians(latitud2 - latitud1);
        double longitudDistancia = Math.toRadians(longitud2 - longitud1);
        double a = Math.sin(latitudDistancia / 2) * Math.sin(latitudDistancia / 2)
                + Math.cos(Math.toRadians(latitud1)) * Math.cos(Math.toRadians(latitud2))
                * Math.sin(longitudDistancia / 2) * Math.sin(longitudDistancia / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distancia = radioTierra * c;
        return distancia;
    }*/
}
