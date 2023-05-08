package negocio;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class AGMPrimTest {


	GrafoLista grafo = new GrafoLista();
	Nodo CABA = new Nodo("CABA","CABA",-34.6144934119689, -58.4458563545429);		
	Nodo Salta = new Nodo("Salta","Salta",-24.2991344492002, -64.8144629600627);
	Nodo Neuquen = new Nodo("Neuquen", "Neuquen",-38.6417575824599, -70.1185705180601);
	Nodo Misiones = new Nodo( "Misiones", "Misiones",-26.8753965086829,-54.6516966230371);
	Nodo SanLuis= new Nodo("San Luis", "San Luis", -33.7577257449137,-66.0281298195836);
	Nodo SanJuan = new Nodo( "San Juan", "San Juan", -30.8653679979618,-68.8894908486844);
	Nodo EntreRios = new Nodo( "Entre Rios","Entre Rios",-32.0588735436448,-59.2014475514635);
	Nodo Cordoba = new Nodo("Cordoba","Cordoba",-32.142932663607,-63.8017532741662);
	Nodo Mendoza = new Nodo("Mendoza","Mendoza",-34.6298873058957,-68.5831228183798);
	
	@Test
	public void agmMainTest() {
	grafo.agregarNodo(CABA);
	grafo.agregarNodo(Salta);
	grafo.agregarNodo(Neuquen);
	grafo.agregarNodo(Misiones);
	grafo.agregarNodo(SanLuis);
	grafo.agregarNodo(SanJuan);
	grafo.agregarNodo(EntreRios);
	grafo.agregarNodo(Cordoba);
	grafo.agregarNodo(Mendoza);
	
	grafo.agregarArista(CABA, Salta);	
	grafo.agregarArista(CABA, Neuquen);		
	grafo.agregarArista(Salta, Neuquen);
	grafo.agregarArista(Neuquen, SanJuan);
	grafo.agregarArista(Neuquen,SanLuis);		
	grafo.agregarArista(Salta, Misiones);
	grafo.agregarArista(Misiones, SanLuis);		
	grafo.agregarArista(SanLuis,SanJuan);
	grafo.agregarArista(Misiones, EntreRios);				
	grafo.agregarArista(SanJuan,Cordoba);		
	grafo.agregarArista(Misiones, Cordoba);
	grafo.agregarArista(EntreRios, Cordoba);
	grafo.agregarArista(EntreRios,Mendoza);
	grafo.agregarArista(Cordoba, Mendoza);
	
	System.out.println("*********Todas las aristas del grafo******");
	for (Nodo nodo : grafo.getNodos()) {
		for(Arista arista : nodo.getArista()) {
			System.out.println(arista.getNodoOrigen().getNombreCiudad() + " ---> " + arista.getNodoDestino().getNombreCiudad() + " : " + Math.round(arista.getPeso()) + " km");
		}				
	}
	
	AGMPrim conexiones= new AGMPrim();
	List<Arista> todasLasAristas =conexiones.AGMPrim(grafo);
	System.out.println("*********Aristas del Arbol generador minimo******");
	
	int pesoDelArbolMinimo=0;
	for (Arista arista : todasLasAristas) {		
		System.out.println(arista.getNodoOrigen().getNombreCiudad() +" ---> " +arista.getNodoDestino().getNombreCiudad()+ " : "+ Math.round(arista.getPeso()) + "km");
		pesoDelArbolMinimo+=Math.round(arista.getPeso());
	}
	System.out.println("***********");
	System.out.println("Peso total del arbol minimo: " + pesoDelArbolMinimo);
	}
}
