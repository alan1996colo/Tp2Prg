package negocio;

import static org.junit.Assert.*;

import java.util.ArrayList;
//import java.util.List;

//import org.junit.Before;
import org.junit.Test;

public class GrafoListaTest {

	@Test
	public void nodosAgregadosTest() {
		GrafoLista grafo = new GrafoLista();
		Nodo Escobar = new Nodo("Escobar", "BSAS", -34.34428257177653, -58.778214488840106);
		Nodo Garin = new Nodo("Garin", "BSAS", -34.42979805864225, -58.724099137999104);
		grafo.agregarNodo(Escobar);
		grafo.agregarNodo(Garin);
		assertEquals(2, grafo.getNodos().size());
	}

	@Test
	public void nodoAgregadoTest() {
		GrafoLista grafo = new GrafoLista();
		Nodo Escobar = new Nodo("Escobar", "BSAS", -34.34428257177653, -58.778214488840106);
		grafo.agregarNodo(Escobar);
		assertEquals(grafo.getNodos().get(0).getNombreCiudad(), "Escobar");
	}

	@Test
	public void nodoContainsTest() {
		GrafoLista grafo = new GrafoLista();
		Nodo Escobar = new Nodo("Escobar", "BSAS", -34.34428257177653, -58.778214488840106);
		grafo.agregarNodo(Escobar);
		assertTrue(grafo.contains(Escobar));
	}

	public void nodoNoContainsTest() {
		GrafoLista grafo = new GrafoLista();
		Nodo Escobar = new Nodo("Escobar", "BSAS", -34.34428257177653, -58.778214488840106);
		assertFalse(grafo.contains(Escobar));
	}

	@Test
	public void buscarNodo() {
		GrafoLista grafo = new GrafoLista();
		Nodo Escobar = new Nodo("Escobar", "BSAS", -34.34428257177653, -58.778214488840106);
		Nodo Garin = new Nodo("Garin", "BSAS", -34.42979805864225, -58.724099137999104);
		grafo.agregarNodo(Garin);
		grafo.agregarNodo(Escobar);
		assertEquals(Escobar, grafo.buscarNodoCiudad("Es cO bAr"));
	}

	@Test(expected = RuntimeException.class)
	public void buscarNodoNoExiste() {
		GrafoLista grafo = new GrafoLista();
		Nodo Escobar = new Nodo("Escobar", "BSAS", -34.34428257177653, -58.778214488840106);
		Nodo Garin = new Nodo("Garin", "BSAS", -34.42979805864225, -58.724099137999104);
		grafo.agregarNodo(Garin);
		grafo.agregarNodo(Escobar);
		assertNotEquals(Escobar, grafo.buscarNodoCiudad("nada"));

	}

	@Test(expected = RuntimeException.class)
	public void buscarNodoSinLista() {
		GrafoLista grafo1 = new GrafoLista();
		assertEquals(grafo1.buscarNodoCiudad("Es cO bAr"), null);

	}


	@Test
	public void eliminarNodoCiudadCiudadExiste() {
		GrafoLista grafo = new GrafoLista();
		Nodo Escobar = new Nodo("Escobar", "BSAS", -34.34428257177653, -58.778214488840106);
		Nodo Garin = new Nodo("Garin", "BSAS", -34.42979805864225, -58.724099137999104);
		grafo.agregarNodo(Garin);
		grafo.agregarNodo(Escobar);
		assertTrue(grafo.eliminarNodoCiudad("escobar"));
	}

	@Test(expected = RuntimeException.class)
	public void eliminarNodoCiudadQueNoExiste() {
		GrafoLista grafo = new GrafoLista();
		Nodo Escobar = new Nodo("Escobar", "BSAS", -34.34428257177653, -58.778214488840106);
		Nodo Garin = new Nodo("Garin", "BSAS", -34.42979805864225, -58.724099137999104);
		grafo.agregarNodo(Garin);
		grafo.agregarNodo(Escobar);
		assertFalse(grafo.eliminarNodoCiudad("pichincha"));

	}

	

	@Test
	public void setNOdosCambiaSize() {
		GrafoLista grafo = new GrafoLista();
		Nodo Escobar = new Nodo("Escobar", "BSAS", -34.34428257177653, -58.778214488840106);
		Nodo algo = new Nodo("asd", "BSAS", -34.34428257177653, -58.778214488840106);
		Nodo Garin = new Nodo("Garin", "BSAS", -34.34342, -58.724099137999104);
		ArrayList<Nodo> n = new ArrayList<Nodo>();
		n.add(Escobar);
		n.add(Garin);
		grafo.agregarNodo(algo);
		int antes = grafo.getTamanio();
		grafo.setNodos(n);
		assertTrue(antes < grafo.getTamanio());

	}

}
