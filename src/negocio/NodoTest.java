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
		assertEquals(Escobar.getArista().get(0).getNodoDestino(),Polvorines);
	}

}
