package negocio;
import java.util.*;

public class AGMPrim {
	
	public static List<Arista> AGMPrim(GrafoLista grafo){
		List<Arista> arbol=new ArrayList<>();
		Set<Nodo> visitados= new HashSet<>();
						
		Nodo actual=grafo.getNodos().get(0);
		visitados.add(actual);
		
		while(visitados.size()<grafo.getTamanio()) {
			Arista menorPeso=null;
			for(Nodo nodo : visitados) {
				for(Arista arista : nodo.getArista()) {
					if(!visitados.contains(arista.getNodoDestino()) && (menorPeso==null || arista.getPeso() < menorPeso.getPeso())) {
						menorPeso=arista;						
					}
				}				
			}
			
			visitados.add(menorPeso.getNodoDestino());
			arbol.add(menorPeso);
		}			
		return arbol;
	}
}