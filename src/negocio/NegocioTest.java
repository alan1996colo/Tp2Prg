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
		neg.grafo.incializarVecinosNodos();
		assertTrue(neg.agregarConexion("Lanús","Rosario"));
		
	}
	@Test
	public void mostrarConexionNodoTest() {
		neg.agregarNodoConNombreDesdeArchivoJson("Lanús","src/negocio/argentinaCitys.json");
		neg.agregarNodoConNombreDesdeArchivoJson("Rosario","src/negocio/argentinaCitys.json");
		neg.grafo.incializarVecinosNodos();
		neg.agregarConexion("Lanús","Rosario");
		neg.mostrarConexionNodoNumero(0);
		
		
	}
}
