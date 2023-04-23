package negocio;

import static org.junit.Assert.*;

import org.junit.Test;

public class GrafoTest {

	
	/*Cualquier cosa que se quiera probar en un "main" se puede probar desde un "mainTest" de la clase correspondiente*/
	@Test
	public void mainTest() {
	//	fail("Not yet implemented");
		//vemos como funciona la clase
		
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 3);
		grafo.agregarArista(3, 4);
		System.out.println(grafo.existeArista(2, 3));
		System.out.println(grafo.existeArista(1, 2));

	}
	//-----------Consulta de vecinos--------------------------
	@Test(expected = IllegalArgumentException.class)
	public void testNoLoops() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(1, 1);
		
	}

	@Test
	public void todosAisladosTest() {
		Grafo grafo = new Grafo(5);
		assertEquals(0,grafo.vecinos(2).size());		
	}	
	@Test (expected = IllegalArgumentException.class)
	public void verticeNegativoTest() {
		Grafo grafo = new Grafo(5);
		grafo.vecinos(-1);		
	}
	@Test (expected = IllegalArgumentException.class)
	public void verticeExcedidoTest() {
		Grafo grafo = new Grafo(5);
		grafo.vecinos(5);		
	}	
	@Test
	public void verticeUniversalTest()
	{
		Grafo grafo = new Grafo(4);
		grafo.agregarArista(1, 0);
		grafo.agregarArista(1, 2);
		grafo.agregarArista(1, 3);
		
		int [] esperado= {0, 2,3};		
		Assert.iguales(esperado, grafo.vecinos(1));
			
	}
	@Test
	public void verticeNormalTest()
	{
		Grafo grafo = new Grafo(4);
		grafo.agregarArista(1, 3);
		grafo.agregarArista(2, 3);
		int [] esperado = {1,2};
		Assert.iguales(esperado,grafo.vecinos(3));
	}
	//------------------Edicion de aristas------------------------

	
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
