package negocio;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class NegocioTest {
	Negocio neg;
	@Before
	public void inicializar_neg() {
		neg=new Negocio(1,2,0);
		neg.inicializarGrafo();
	}
	@Test
	public void registrarLocalidad() {
		neg.registrarLocalidad("asd", "cordoba", 0, 0);
		neg.registrarLocalidad("23", "cordoba", 0, 0);
		neg.registrarLocalidad("as4d", "cordoba", 0, 0);
		assertEquals(neg.grafo.getTamanio(),3);
	}

	@Ignore@Test 
	public void agregarNodoConNombreDesdeArchivoJsonTest() {
		neg.agregarNodoConNombreDesdeArchivoJson("Lanús","argentinaCitys.json");
		neg.MostrarGrafos();
	}
	@Test
	public void agregarConexionSinNodos() {
		assertFalse(neg.agregarConexion("nada","ninguno"));
	}

	@Test
	public void agregarConexionConNodos() {
		neg.agregarNodoConNombreDesdeArchivoJson("Lanús","argentinaCitys.json");
		neg.agregarNodoConNombreDesdeArchivoJson("Rosario","argentinaCitys.json");
		neg.grafo.incializarVecinosNodos();
		assertTrue(neg.agregarConexion("Lanús","Rosario"));
		
	}
	@Ignore @Test
	public void mostrarConexionNodoTest() {
		neg.agregarNodoConNombreDesdeArchivoJson("Lanús","argentinaCitys.json");
		neg.agregarNodoConNombreDesdeArchivoJson("Rosario","argentinaCitys.json");
		neg.grafo.incializarVecinosNodos();
		neg.agregarConexion("Lanús","Rosario");
		neg.mostrarConexionNodoNumero(0);
		
		
	}
	@Test
	public void IsProvinciasDistintasProvIguales() {
		neg.inicializarGrafo();
		neg.registrarLocalidad("SanMI", "Buenos Aires", 0, 0);
		neg.registrarLocalidad("Muñiz", "Buenos Aires", 3, 4);
		assertFalse(neg.IsProvinciasDistintas("San MI", "MuñiZ"));//Funciona incluso si hay diferencia de mayuscula y minuscula
	}
	@Test
	public void IsProvinciasDistintasProvDistintas() {
	
		neg.registrarLocalidad("SanMI", "Buenos Aires", 0, 0);
		neg.registrarLocalidad("Muñiz", "tucuman", 3, 4);
		assertTrue(neg.IsProvinciasDistintas("SanMI", "Muñiz"));
	}
	@Test
	public void eliminarCiudadExistente() { 
		neg.registrarLocalidad("SanMI", "Buenos Aires", 0, 0);
		neg.registrarLocalidad("Muñiz", "tucuman", 3, 4);
		assertTrue(neg.quitarCiudad("Muñiz"));
	}
	@Test (expected=IllegalArgumentException.class)
	public void eliminarCiudadNoExistente() { 
		neg.registrarLocalidad("SanMI", "Buenos Aires", 0, 0);
		assertFalse(neg.quitarCiudad("Muñiz"));
	}
	@Ignore@Test
	public void mostrarGrafoConAristas() {
	
		neg.registrarLocalidad("SanMI", "Buenos Aires", 0, 0);
		neg.registrarLocalidad("Muñiz", "tucuman", 3, 4);
	//	neg.grafo.incializarVecinosNodos();
		neg.agregarConexion("SanMI", "Muñiz");
		neg.MostrarGrafos();
	}
	@Test
	public void eliminarCiudadEliminaTodasSusAristas() {
		neg.registrarLocalidad("SanMI", "Buenos Aires", 0, 0);
		neg.registrarLocalidad("Muñiz", "tucuman", 3, 4);
		neg.registrarLocalidad("jcp", "tucuman", 3, 4);
		neg.agregarConexion("SanMI", "Muñiz");
		neg.agregarConexion("jcp", "Muñiz");
		neg.quitarCiudad("Muñiz");
		neg.MostrarGrafos();
		
		
	}
	@Test
	public void DarCostoEnpesosTest() {
		neg.registrarLocalidad("SanMI", "Buenos Aires", 0, 0);
		neg.registrarLocalidad("Muñiz", "tucuman", 3, 4);
		neg.registrarLocalidad("jcp", "tucuman", 3, 4);
		neg.agregarConexion("SanMI", "Muñiz");
		neg.agregarConexion("jcp", "Muñiz");
		assertEquals(neg.darCostoEnpesos(),0,1);//¿Delta perdida de precision?
	}
}
