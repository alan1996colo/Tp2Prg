package negocio;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

public class BFSTest {

	//modificar el test para que funcione con GrafoLista y ingresando objetos "Nodo"	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testNull() {
		BFS.esConexo(null);
	} 
	/**
	@Test
	public void vacioTest()
	{
		GrafoLista g = new GrafoLista(0);
		assertTrue(BFS.esConexo(g));
	}
	
	@Test
	public void alcanzablesTest()
	{
		GrafoLista g = inicializarGrafo();
		Set<Integer> alcanzables = BFS.alcanzables(g,0);
		int [] esperados = {0,1,2,3};
		Assert.iguales(esperados, alcanzables);
	}
	@Test
	public void conexoTest()
	{
		GrafoLista g = inicializarGrafo();
		g.agregarArista(3, 4);
		assertTrue(BFS.esConexo(g));
	}
	
	@Test
	public void noConexoTest()
	{
		Grafo g = inicializarGrafo();
		assertFalse(BFS.esConexo(g));
	}
	
	
	
	private Grafo inicializarGrafo()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(0, 1);		
		grafo.agregarArista(0, 2);	
		grafo.agregarArista(2, 3);	
		return grafo;
	}
	*/

}
