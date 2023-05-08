package negocio;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GrafoListaTest {
	
	@Test 
	public void nodosAgregadosTest(){
		GrafoLista grafo = new GrafoLista();
		Nodo Escobar = new Nodo("Escobar","BSAS",-34.34428257177653, -58.778214488840106);
		Nodo Garin = new Nodo("Garin","BSAS",-34.42979805864225, -58.724099137999104);
		grafo.agregarNodo(Escobar);
		grafo.agregarNodo(Garin);
		assertEquals(2,grafo.getNodos().size());
	}
	@Test
	public void nodoAgregadoTest() {
		GrafoLista grafo = new GrafoLista();
		Nodo Escobar = new Nodo("Escobar","BSAS",-34.34428257177653, -58.778214488840106);
		grafo.agregarNodo(Escobar);
		assertEquals(grafo.getNodos().get(0).getNombreCiudad(),"Escobar");		
	}
	@Test
	public void nodoContainsTest() {
		GrafoLista grafo = new GrafoLista();
		Nodo Escobar = new Nodo("Escobar","BSAS",-34.34428257177653, -58.778214488840106);
		grafo.agregarNodo(Escobar);
		assertTrue(grafo.contains(Escobar));	
	}
	public void nodoNoContainsTest() {
		GrafoLista grafo = new GrafoLista();
		Nodo Escobar = new Nodo("Escobar","BSAS",-34.34428257177653, -58.778214488840106);
		assertFalse(grafo.contains(Escobar));	
	}	
	@Test
	public void buscarNodo() {
		GrafoLista grafo = new GrafoLista();
		Nodo Escobar = new Nodo("Escobar","BSAS",-34.34428257177653, -58.778214488840106);
		Nodo Garin = new Nodo("Garin","BSAS",-34.42979805864225, -58.724099137999104);
		grafo.agregarNodo(Garin);
		grafo.agregarNodo(Escobar);
		assertEquals(Escobar,grafo.buscarNodoCiudad("Es cO bAr"));
	}
	@Test
	public void buscarNodoNoExiste() {
		GrafoLista grafo = new GrafoLista();
		Nodo Escobar = new Nodo("Escobar","BSAS",-34.34428257177653, -58.778214488840106);
		Nodo Garin = new Nodo("Garin","BSAS",-34.42979805864225, -58.724099137999104);
		grafo.agregarNodo(Garin);
		grafo.agregarNodo(Escobar);
		assertNotEquals(Escobar,grafo.buscarNodoCiudad("nada"));
		
	}
	@Test
	public void buscarNodoSinLista() {
		GrafoLista grafo1 = new GrafoLista();
		assertEquals(grafo1.buscarNodoCiudad("Es cO bAr"),null);
		
	}
	@Test (expected=IllegalArgumentException.class)
	public void noSePermitenBucles() {
		GrafoLista grafo = new GrafoLista();
		Nodo Escobar = new Nodo("Escobar","BSAS",-34.34428257177653, -58.778214488840106);
		grafo.agregarArista(Escobar, Escobar);		
	}
	@Test
	public void agregarAristaTest() {
		GrafoLista grafo = new GrafoLista();
		Nodo Escobar = new Nodo("Escobar","BSAS",-34.34428257177653, -58.778214488840106);
		Nodo Garin = new Nodo("Garin","BSAS",-34.42979805864225, -58.724099137999104);
		Nodo bb = new Nodo("ss","ss",-34.42979805864225, -58.724099137999104);
		grafo.agregarNodo(Garin);
		grafo.agregarNodo(Escobar);
		Garin.inicializarVecinos();
		Escobar.inicializarVecinos();
		grafo.agregarArista(Escobar, Garin);
		grafo.agregarArista(Escobar, bb);
		Arista eg=new Arista(Escobar,Garin,grafo.distanciaEntreNodos(Garin, Escobar));
		assertTrue(Escobar.getAristas().contains(eg));
	
	}
	@Test
	public void eliminarNodoCiudadCiudadExiste() {
		GrafoLista grafo = new GrafoLista();
		Nodo Escobar = new Nodo("Escobar","BSAS",-34.34428257177653, -58.778214488840106);
		Nodo Garin = new Nodo("Garin","BSAS",-34.42979805864225, -58.724099137999104);
		grafo.agregarNodo(Garin);
		grafo.agregarNodo(Escobar);
		assertTrue(grafo.eliminarNodoCiudad("escobar"));
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void eliminarNodoCiudadQueNoExiste() {
		GrafoLista grafo = new GrafoLista();
		Nodo Escobar = new Nodo("Escobar","BSAS",-34.34428257177653, -58.778214488840106);
		Nodo Garin = new Nodo("Garin","BSAS",-34.42979805864225, -58.724099137999104);
		grafo.agregarNodo(Garin);
		grafo.agregarNodo(Escobar);
		assertFalse(grafo.eliminarNodoCiudad("pichincha"));
		
	}
	@Test
	public void eliminarNodoCiudadAristasCambiaTamaño() {
		GrafoLista grafo = new GrafoLista();
		Nodo Escobar = new Nodo("Escobar","BSAS",-34.34428257177653, -58.778214488840106);
		Nodo Garin = new Nodo("Garin","BSAS",-34.42979805864225, -58.724099137999104);
		Nodo bb = new Nodo("ss","ss",-34.42979805864225, -58.724099137999104);
		grafo.agregarNodo(bb);
		grafo.agregarNodo(Garin);
		grafo.agregarNodo(Escobar);
		grafo.agregarArista(Escobar, Garin);
		
		grafo.agregarArista(bb, Garin);
		grafo.agregarArista(Escobar, bb);
		grafo.eliminarNodoCiudad("escobar");
		Garin.mostrarAristas();
		assertEquals(Garin.getAristas().size(),1);
		
	}
	
}


