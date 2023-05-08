package negocio;

import static org.junit.Assert.*;

import org.junit.Test;

public class NodoTest {

	@Test
	public void agregarVecinoTest() {
	    GrafoLista grafo = new GrafoLista();
	    Nodo Escobar = new Nodo("Escobar","BSAS",-34.34428257177653, -58.778214488840106);
	    Nodo Polvorines =new Nodo("Polvorines", "BSAS",-34.49933901727254, -58.693703801287086);
	    grafo.agregarNodo(Escobar);
	    grafo.agregarNodo(Polvorines);
	    Escobar.agregarVecino(Escobar, Polvorines,5);
	    
	    // Buscar la arista que tiene a Polvorines como destino
	    Arista arista = null;
	    for (Arista a : Escobar.getAristas()) {
	        if (a.getNodoDestino().equals(Polvorines)) {
	            arista = a;
	            break;
	        }
	    }
	    
	    assertNotNull(arista);
	    assertEquals(arista.getNodoDestino(), Polvorines);
	}

	@Test
	public void equalTest() {
		Nodo Escobar = new Nodo("Escobar","BSAS",-34.34428257177653, -58.778214488840106);
		Nodo e = new Nodo("Escobar","BSAS",-34.34428257177653, -58.778214488840106);
		assertTrue(e.equals(Escobar));
		assertTrue(Escobar.equals(e));
	
	}
	//Arista
	@Test
	public void equalArista() {
		Nodo Escobar = new Nodo("Escobar","BSAS",-34.34428257177653, -58.778214488840106);
		Nodo Polvorines =new Nodo("Polvorines", "BSAS",-34.49933901727254, -58.693703801287086);
		
		Arista a=new Arista(Escobar,Polvorines,4);
		Arista b=new Arista(Escobar,Polvorines,3);
		assertTrue(a.equals(b));
	}
}
