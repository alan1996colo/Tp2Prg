package negocio;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class AGMPrimTest {

	//	TEST DEL EJEMPLO DADO EN CLASE SEMANA 3 AL 9 DE ABRIL.
	GrafoLista grafo = new GrafoLista();
	Nodo a = new Nodo("A", "CABA", -34.6144934119689, -58.4458563545429);//las coordenadas para este test son irrelevantes
	Nodo b = new Nodo("B", "CABA", -24.2991344492002, -64.8144629600627);//porque se les dara el mismo peso mas adelante
	Nodo c = new Nodo("C", "CABA", -38.6417575824599, -70.1185705180601);//que se les dio en la clase
	Nodo d = new Nodo("D", "CABA", -26.8753965086829, -54.6516966230371);
	Nodo e = new Nodo("E", "CABA", -33.7577257449137, -66.0281298195836);
	Nodo f = new Nodo("F", "CABA", -30.8653679979618, -68.8894908486844);
	Nodo g = new Nodo("G", "CABA", -32.0588735436448, -59.2014475514635);
	Nodo h = new Nodo("H", "CABA", -32.142932663607, -63.8017532741662);
	Nodo i = new Nodo("I", "CABA", -34.6298873058957, -68.5831228183798);

	@Test
	public void agmMainTestDelAgmDadoEnClase() {//esto demuestra que el agm funciona.
		grafo.agregarNodo(a);
		grafo.agregarNodo(b);
		grafo.agregarNodo(c);
		grafo.agregarNodo(d);
		grafo.agregarNodo(e);
		grafo.agregarNodo(f);
		grafo.agregarNodo(g);
		grafo.agregarNodo(h);
		grafo.agregarNodo(i);

		grafo.agregarArista(a, b,4);
		grafo.agregarArista(a, h,8);
		grafo.agregarArista(b, h, 12);
		grafo.agregarArista(b, c, 8);
		grafo.agregarArista(h, i, 6);
		grafo.agregarArista(h, g, 1);
		grafo.agregarArista(c, i, 3);
		grafo.agregarArista(i, g, 5);
		grafo.agregarArista(g, f, 3);
		grafo.agregarArista(c, f, 4);
		grafo.agregarArista(c, d, 6);
		grafo.agregarArista(d, f, 13);
		grafo.agregarArista(d, e, 9);
		grafo.agregarArista(f, e, 10);
		
		
		

		System.out.println("*********Todas las aristas del grafo******");
		for (Nodo nodo : grafo.getNodos()) {
			for (Arista arista : nodo.getAristas()) {
				System.out.println(arista.getNodoOrigen().getNombreCiudad() + " ---> "
						+ arista.getNodoDestino().getNombreCiudad() + " : " + Math.round(arista.getPeso()) + " km");
			}
		}

		AGMPrim conexiones = new AGMPrim();
		List<Arista> todasLasAristas = conexiones.AGMPrim(grafo);
		System.out.println("*********Aristas del Arbol generador minimo******");

		int pesoDelArbolMinimo = 0;
		for (Arista arista : todasLasAristas) {
			System.out.println(arista.getNodoOrigen().getNombreCiudad() + " ---> "
					+ arista.getNodoDestino().getNombreCiudad() + " : " + Math.round(arista.getPeso()) + "km");
			pesoDelArbolMinimo += Math.round(arista.getPeso());
		}
		System.out.println("***********");
		System.out.println("Peso total del arbol minimo: " + pesoDelArbolMinimo);
	}
	
	@Test
	public void agmDanielBertacciniTest() {
		Nodo bahia=new Nodo("Bahia","Buenos Aires",-38.71959,-62.27243);
		Nodo  generalAcha= new Nodo("General Acha","La Pampa",-37.37698,-64.60431);
		Nodo generalRoca=new Nodo("General Roca","Rio Negro",-39.03333,-67.58333);
		Nodo santaRosa= new Nodo("Santa Rosa","La Pampa",-36.61667,-64.28333);
		Nodo mardelPlata= new Nodo("Mar del Plata","Buenos Aires",-38.0042,-57.5562);
		Nodo viedma= new Nodo("VIedma","Rio Negro",-40.81345,-62.99668);
		Nodo Tandil= new Nodo("Tandil","Buenos Aires",-37.32167,-59.13316);
		
		Negocio neg=new Negocio(0,0,1);
		//continuar
		}
	
}
