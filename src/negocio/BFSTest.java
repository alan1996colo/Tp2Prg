package negocio;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class BFSTest {

	//modificar el test para que funcione con GrafoLista y ingresando objetos "Nodo"	
	Nodo Escobar,Garin,Misiones,SanLuis,SanJuan,EntreRios,SantaCruz;
	GrafoLista grafo ;
	
	@Before
	public void setUP() {
	Escobar = new Nodo("Escobar","BSAS",-34.34428257177653, -58.778214488840106);
	grafo = new GrafoLista();
	 Escobar = new Nodo("Escobar","BSAS",-34.34428257177653, -58.778214488840106);
	 Garin = new Nodo("Garin","BSAS",-34.42979805864225, -58.724099137999104);
	 Misiones= new Nodo ("Misiones","Misiones", -26.8753965086829,-54.6516966230371);
	 SanLuis= new Nodo("SanLuis","SanLuis",-33.7577257449137,-66.0281298195836);
	 EntreRios = new Nodo("EntreRios","EntreRios",-32.0588735436448,-59.2014475514635);
	 SanJuan= new Nodo("SanJuan","SanJuan", -30.8653679979618,-68.8894908486844);
	 SantaCruz= new Nodo("SantaCruz","SantaCruz",-48.8154851827063,-69.9557621671973);
	
	grafo.agregarNodo(Garin);
	grafo.agregarNodo(Escobar);
	grafo.agregarNodo(Misiones);
	grafo.agregarNodo(SanLuis);
	grafo.agregarNodo(EntreRios);
	grafo.agregarNodo(SanJuan);
	grafo.agregarNodo(SantaCruz);}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNull() {
		BFS.esConexo(null);
	} 
	
	@Test
	public void vacioTest()
	{
		GrafoLista g = new GrafoLista();
		g.agregarNodo(Escobar);
		assertTrue(BFS.esConexo(g));
	}
	
	@Test
	public void alcanzablesTest()
	{
		
		Set<Integer> alcanzables = BFS.alcanzables(grafo,0);
		int [] esperados = {0,1,2,3};
		assertEquals(esperados, alcanzables);
	}
	@Test
	public void conexoTest()
	{
		grafo.agregarArista(SanJuan, SantaCruz, grafo.distanciaEntreNodos(SanJuan, SantaCruz));			
		grafo.agregarArista(Escobar, Garin,grafo.distanciaEntreNodos(Escobar, Garin));
		grafo.agregarArista(Escobar, Misiones,grafo.distanciaEntreNodos(Escobar, Misiones));
		assertTrue(BFS.esConexo(grafo));
	}
	
	@Test
	public void noConexoTest()
	{
		assertFalse(BFS.esConexo(grafo));
	}
	
	


}
