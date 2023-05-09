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
	@Test (expected=RuntimeException.class)
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
	@Test (expected=RuntimeException.class)
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
	@Test
	public void darCostoEnpesosTest() {//Entre san miguel y jcp hay 5.99km
		neg.setPrecios(1, 300, 2);
		neg.registrarLocalidad("SanMI", "Buenos Aires", -34.54335,-58.71229);
		neg.registrarLocalidad("jcp", "Buenos Aires", -34.51541, -58.76813);
		neg.agregarConexion("SanMI", "jcp");
		//System.out.println(neg.darCostoEnpesos());
		assertTrue(neg.darCostoEnpesos()>11.8 && neg.darCostoEnpesos()<12);
	}
	@Test
	public void CostoFijoPesosProvDiffTest() {
		neg.setPrecios(1, 300, 2);
		neg.registrarLocalidad("SanMI", "Buenos Aires", -34.54335,-58.71229);
		neg.registrarLocalidad("chaco", "chaco", -34.01541, -57.76813);
		neg.agregarConexion("SanMI", "chaco");
		assertTrue(neg.CostoFijoPesosProvDiff()==300);
		
	}
	@Test
	public void CostoFijoPesosProvIgualesTest() {
		neg.setPrecios(1, 300, 2);
		neg.registrarLocalidad("SanMI", "Buenos Aires", -34.54335,-58.71229);
		neg.registrarLocalidad("chaco", "Buenos Aires", -34.01541, -57.76813);
		neg.agregarConexion("SanMI", "chaco");
		assertFalse(neg.CostoFijoPesosProvDiff()==300);
	}
	@Test
	public void PorcentajeDeAumentoConexionTest() {//Si la conexion supera los 300km debe dar un porcentaje de aumento
		neg.setPrecios(1, 300, 1);//seteamos el porcentaje en 1% y precio por km en 1 a 1.
		neg.registrarLocalidad("SanMI", "Buenos Aires", -34.54335,-58.71229);
		neg.registrarLocalidad("salta", "Salta",  -24.7859,  -65.41166);//entre san miguel y salta hay mas de 1260km
		neg.agregarConexion("SanMI", "salta");//el 1% de 1260 es 12.60
		assertTrue(neg.PorcentajeDeAumentoConexion()>12.5 && neg.PorcentajeDeAumentoConexion()< 12.7);
	}
	
	
	
}
