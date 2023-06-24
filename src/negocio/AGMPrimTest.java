package negocio;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
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

	@Test@Ignore
	public void agmMainTestDelAgmDadoEnClase() {//esto demuestra que el agm funciona.
		//test visual se puede ignorar
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
	public void grafoSimpleCompletoTest() {
		grafo.agregarNodo(a);
		grafo.agregarNodo(b);
		grafo.agregarNodo(c);
		
		grafo.agregarArista(a, b, 2);
		grafo.agregarArista(b, c, 4);
		grafo.agregarArista(c, a, 6);
		
		ArrayList<Arista> comparar=new ArrayList<>();
		comparar.add(new Arista(a,b,2));
		comparar.add(new Arista(b,c,4));
		
		AGMPrim conexiones = new AGMPrim();
		List<Arista> todasLasAristas = conexiones.AGMPrim(grafo);
		
		int pesoDelArbolMinimo = 0;
		for (Arista arista : todasLasAristas) {
			pesoDelArbolMinimo += Math.round(arista.getPeso());
		}
		assertTrue(pesoDelArbolMinimo==6&&todasLasAristas.containsAll(comparar));
	
		
	}
	@Test
	public void grafoSimpleIncompleto() {
		grafo.agregarNodo(a);
		grafo.agregarNodo(b);
		grafo.agregarNodo(c);
		
		grafo.agregarArista(a, b, 2);
		grafo.agregarArista(b, c, 4);
	
		
		ArrayList<Arista> comparar=new ArrayList<>();
		comparar.add(new Arista(a,b,2));
		comparar.add(new Arista(b,c,4));
		
		AGMPrim conexiones = new AGMPrim();
		List<Arista> todasLasAristas = conexiones.AGMPrim(grafo);
		
		int pesoDelArbolMinimo = 0;
		for (Arista arista : todasLasAristas) {
			pesoDelArbolMinimo += Math.round(arista.getPeso());
		}
		assertTrue(pesoDelArbolMinimo==6&&todasLasAristas.containsAll(comparar));
	
		
	}
	@Test(expected=IllegalArgumentException.class)
	public void grafoVacioTest() {
		GrafoLista vacio=new GrafoLista();
		AGMPrim conexiones = new AGMPrim();
		List<Arista> todasLasAristas = conexiones.AGMPrim(vacio);
		assertTrue(todasLasAristas.size()==0);
		
	}
	@Test
	public void grafoUnSoloNodo() {
		GrafoLista uno=new GrafoLista();
		uno.agregarNodo(a);
		AGMPrim conexiones = new AGMPrim();
		List<Arista> todasLasAristas = conexiones.AGMPrim(uno);
		assertTrue(todasLasAristas.size()==0);
		
	}
	@Test
	public void grafoNoConexoTest(){
		grafo.agregarNodo(a);
		grafo.agregarNodo(b);
		grafo.agregarNodo(c);
		grafo.agregarNodo(d);
		
		grafo.agregarArista(a, b, 2);
		grafo.agregarArista(d, c, 4);
		
		
		
		AGMPrim conexiones = new AGMPrim();
		List<Arista> todasLasAristas = conexiones.AGMPrim(grafo);
		
		int pesoDelArbolMinimo = 0;
		for (Arista arista : todasLasAristas) {
			pesoDelArbolMinimo += Math.round(arista.getPeso());
		}
		assertTrue(pesoDelArbolMinimo==2||pesoDelArbolMinimo==4);
	}
	@Test (expected=RuntimeException.class)
	public void grafoConPesosNegativosTest() {
		grafo.agregarNodo(a);
		grafo.agregarNodo(b);
		grafo.agregarNodo(c);
		
		grafo.agregarArista(a, b, -2);
		grafo.agregarArista(b, c, -4);
		grafo.agregarArista(c, a, 6);
		
		ArrayList<Arista> comparar=new ArrayList<>();
		comparar.add(new Arista(a,b,-2));
		comparar.add(new Arista(b,c,-4));
		
		AGMPrim conexiones = new AGMPrim();
		List<Arista> todasLasAristas = conexiones.AGMPrim(grafo);
		
		int pesoDelArbolMinimo = 0;
		for (Arista arista : todasLasAristas) {
			pesoDelArbolMinimo += Math.round(arista.getPeso());
		}
		
	}
	
	@Test
	public void agmTestEnClasePesoTotalConocido() {
		//para este test sabemos que el peso era 38
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
		AGMPrim conexiones = new AGMPrim();
		List<Arista> todasLasAristas = conexiones.AGMPrim(grafo);
		
		int pesoDelArbolMinimo = 0;
		for (Arista arista : todasLasAristas) {
			pesoDelArbolMinimo += Math.round(arista.getPeso());
		}
		assertTrue(pesoDelArbolMinimo==38);
	}
	@Test
	public void agmClaseTestAristasQuePertenecen() {//Para este test revisamos el pdf pasado en clase y demostramos
		//que las mismas aristas pertenecen al arbol del ejemplo de kruscal en este caso.
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
		
		AGMPrim conexiones = new AGMPrim();
		List<Arista> todasLasAristas = conexiones.AGMPrim(grafo);
		ArrayList<Arista> comparar=new ArrayList<>();
		comparar.add(new Arista(a,b,4));
		comparar.add(new Arista(a,h,8));
		comparar.add(new Arista(c,d,6));
		comparar.add(new Arista(c,i,3));
		comparar.add(new Arista(c,f,4));
		comparar.add(new Arista(f,g,3));
		comparar.add(new Arista(g,h,1));
		comparar.add(new Arista(d,e,9));
		assertTrue(todasLasAristas.containsAll(comparar));
		
		//continuar
		}
	@Test
	public void agmClaseTestCantAristasCantVerticeMenosUno() {//Para este test revisamos que la cantidad de aristas sea la cantidad de vertices menos 1.
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
		
		AGMPrim conexiones = new AGMPrim();
		List<Arista> todasLasAristas = conexiones.AGMPrim(grafo);
		assertTrue(todasLasAristas.size()==grafo.getTamanio()-1);
		}
	
}
