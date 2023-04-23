package negocio;

import java.util.List;

public class mainPrueba {

	public static void main(String[] args) {
	
		GrafoLista grafo = new GrafoLista();
		Nodo Escobar = new Nodo("Escobar","BSAS",-34.34428257177653, -58.778214488840106);
		Nodo Garin = new Nodo("Garin","BSAS",-34.42979805864225, -58.724099137999104);
		Nodo Misiones= new Nodo ("Misiones","Misiones", -26.8753965086829,-54.6516966230371);
		Nodo SanLuis= new Nodo("SanLuis","SanLuis",-33.7577257449137,-66.0281298195836);
		Nodo EntreRios = new Nodo("EntreRios","EntreRios",-32.0588735436448,-59.2014475514635);
		Nodo SanJuan= new Nodo("SanJuan","SanJuan", -30.8653679979618,-68.8894908486844);
		Nodo SantaCruz= new Nodo("SantaCruz","SantaCruz",-48.8154851827063,-69.9557621671973);
		
		grafo.agregarNodo(Garin);
		grafo.agregarNodo(Escobar);
		grafo.agregarNodo(Misiones);
		grafo.agregarNodo(SanLuis);
		grafo.agregarNodo(EntreRios);
		grafo.agregarNodo(SanJuan);
		grafo.agregarNodo(SantaCruz);
		
		grafo.agregarArista(SanJuan, SantaCruz, grafo.distanciaEntrePuntos(SanJuan, SantaCruz));			
		grafo.agregarArista(Escobar, Garin,grafo.distanciaEntrePuntos(Escobar, Garin));
		grafo.agregarArista(Escobar, Misiones,grafo.distanciaEntrePuntos(Escobar, Misiones));
		
		System.out.println("*****Muestro los nodos cargados****");
		for (Nodo nodo : grafo.getNodos()) {
			System.out.println(nodo.getId()); 
		}		
		
		
		System.out.println("*****Muestro los vecinos de Escobar****");
		List<Arista> vecinosDeEscobar =Escobar.getVecinos();
		
		for (Arista arista : vecinosDeEscobar) {
			Nodo nodoVecinoEscobar= arista.getNodoDestino();
			String nombreCiudadVecinaEscobar=nodoVecinoEscobar.getId();			
			System.out.println("La ciudad vecina de Escobar es: " + nombreCiudadVecinaEscobar);			
		}
//		
		System.out.println("*****Muestro las distancia entre los dos vecinos de Escobar que agregue ****");
		System.out.println("La distancia entre Escobar y Garin es : "+grafo.distanciaEntrePuntos(Escobar,Garin));
		System.out.println("La distancia entre Escobar y Misiones es: "+grafo.distanciaEntrePuntos(Escobar,Misiones));
	}

}
