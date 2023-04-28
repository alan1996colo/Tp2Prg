package negocio;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NegocioTest {
	Negocio neg;
	@Before
	public void inicializar_neg() {
		neg=new Negocio(1,2,3);
		neg.inicializarGrafo();
	}


	@Test
	public void agregarNodoConNombreDesdeArchivoJsonTest() {
		neg.agregarNodoConNombreDesdeArchivoJson("Lanús","src/negocio/argentinaCitys.json");
		neg.MostrarGrafos();
	}
	@Test
	public void agregarConexionSinNodos() {
		assertFalse(neg.agregarConexion("nada","ninguno"));
	}

	@Test
	public void agregarConexionConNodos() {
		neg.agregarNodoConNombreDesdeArchivoJson("Lanús","src/negocio/argentinaCitys.json");
		neg.agregarNodoConNombreDesdeArchivoJson("Rosario","src/negocio/argentinaCitys.json");
		Nodo alg=new Nodo("asda","asdgg",213,123);
		Nodo alg2=new Nodo("per","asdgg",2,123);
		neg.grafo.agregarNodo(alg2);
		neg.grafo.agregarNodo(alg);
		assertTrue(neg.agregarConexion("per","asda"));
		
	}
}
