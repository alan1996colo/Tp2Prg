package negocio;
import java.util.*;

public class GrafoLista {	
    private List<Nodo> nodos;
    
    public GrafoLista() {
        this.nodos = new ArrayList<>();
    }

    public void agregarNodo(Nodo nodo) {
        nodos.add(nodo);
    }

    public void agregarArista(Nodo nodo1, Nodo nodo2, double peso) {
        nodo1.agregarVecino(nodo2, peso);
        nodo2.agregarVecino(nodo1, peso);     
    }
   
    //devuelvo los nodos del grafo
    public List<Nodo> getNodos() {
        return nodos;
    }
    
    
    public static double distanciaEntrePuntos(Nodo ciudad1, Nodo ciudad2) {
    	double latitud1=ciudad1.getLatitud();
    	double latitud2=ciudad2.getLatitud();
    	double longitud1=ciudad1.getLongitud();
    	double longitud2=ciudad2.getLongitud();
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

    


}
