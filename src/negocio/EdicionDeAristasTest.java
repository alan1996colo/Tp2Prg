package grafos;

import static org.junit.Assert.*;

import org.junit.Test;

public class EdicionDeAristasTest {
	
	@Test (expected = IllegalArgumentException.class)
	public void primerVerticeNegativoTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(-1, 3);
	}
	@Test (expected = IllegalArgumentException.class)
	public void primerVerticeNegativoExcedidoTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(5, 2);
	}
	@Test (expected = IllegalArgumentException.class)
	public void segundoVerticeNegativoTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(1, -3);
	}
	@Test (expected = IllegalArgumentException.class)
	public void segundoVerticeNegativoExcedidoTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 5);
	}
	@Test (expected = IllegalArgumentException.class)
	public void agregadoLoopTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 2);
	}	
	@Test
	public void aristaExistenteTest()
	{
		Grafo grafo = new Grafo (5);
		grafo.agregarArista(2, 3);
		assertTrue(grafo.existeArista(2, 3));
	}
	@Test
	public void aristaOpuestaTest()
	{
		Grafo grafo = new Grafo (5);
		grafo.agregarArista(3, 2);
		assertTrue(grafo.existeArista(3, 2));
	}
	@Test
	public void aristaInexistenteTest()
	{
		Grafo grafo = new Grafo (5);
		grafo.agregarArista(3, 2);
		assertFalse (grafo.existeArista(1, 4));
	}
	public void eliminarAristaExistenteTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 4);
		grafo.eliminarArista(2, 4);
		assertFalse(grafo.existeArista(2,4));
	}
	
	
	

}
