package negocio;
import java.util.*;

public class AGMPrim {
	//Antes de heap-->O(V^2) , despues de heap --> O(E log V).
	public static List<Arista> AGMPrim(GrafoLista grafo){
	    List<Arista> arbol = new ArrayList<>();
	    Set<Nodo> visitados = new HashSet<>();
	    PriorityQueue<Arista> heap = new PriorityQueue<>(Comparator.comparing(Arista::getPeso));

	    Nodo actual = grafo.getNodos().get(0);
	    visitados.add(actual);

	    for (Arista arista : actual.getAristas()) {
	        heap.add(arista);
	    }

	    while (visitados.size() < grafo.getTamanio()) {
	        Arista menorPeso = heap.poll();
	        if (menorPeso == null) {
	            break;
	        }

	        if (visitados.contains(menorPeso.getNodoDestino())) {
	            continue;
	        }

	        visitados.add(menorPeso.getNodoDestino());
	        arbol.add(menorPeso);

	        for (Arista arista : menorPeso.getNodoDestino().getAristas()) {
	            if (!visitados.contains(arista.getNodoDestino())) {
	                heap.add(arista);
	            }
	        }
	    }

	    return arbol;
	}

}