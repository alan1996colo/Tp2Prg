package negocio;

import static org.junit.Assert.*;

import java.util.List;

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
		neg.registrarLocalidad("Muñiz", "tucuman", 0, 0);
		neg.registrarLocalidad("jcp", "tucuman", 0, 0);
		neg.agregarConexion("SanMI", "Muñiz");
		neg.agregarConexion("jcp", "Muñiz");
		Nodo sanmi=neg.getGrafo().getNodoNum(0);
		Nodo muniz=neg.getGrafo().getNodoNum(1);
		Nodo jcp=neg.getGrafo().getNodoNum(2);
		Arista ar=new Arista(sanmi,muniz,0);
		Arista ar2=new Arista(jcp,muniz,0);
		neg.quitarCiudad("Muñiz");
		
		assertFalse(neg.getGrafo().getNodoNum(0).getAristas().contains(ar)||neg.getGrafo().getNodoNum(0).getAristas().contains(ar2));
		
		
	}
	@Test
	public void DarCostoEnpesosTest() {
		neg.registrarLocalidad("SanMI", "tucuman", 0, 0);
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
	public void distanciaEntreTandilBahia() {
		Nodo tandil=new Nodo("Tandil","Buenos Aires",-37.32167,-59.13316);
		Nodo bahiaBlanca=new Nodo("Bahia blanca","Buenos Aires",-38.71959,-62.27243);
		assertTrue(neg.distanciaEntreNodos(tandil,bahiaBlanca)>=315 && neg.distanciaEntreNodos(tandil,bahiaBlanca)<=316);
	}
	@Test
	public void distanciaEntreTandilMardel() {
		Nodo tandil=new Nodo("Tandil","Buenos Aires",-37.32167,-59.13316);
		Nodo marDelPlata=new Nodo("Mar del plata","Buenos Aires",-38.0042,-57.5562);
	
		assertTrue(neg.distanciaEntreNodos(tandil,marDelPlata)>=158 && neg.distanciaEntreNodos(tandil,marDelPlata)<=159);
		
		
	}
	@Test
	public void distanciaEntreBahiaBlancaMardelPlata() {
		
		Nodo marDelPlata=new Nodo("Mar del plata","Buenos Aires",-38.0042,-57.5562);
		Nodo bahiaBlanca=new Nodo("Bahia blanca","Buenos Aires",-38.71959,-62.27243);
		assertTrue(neg.distanciaEntreNodos(bahiaBlanca,marDelPlata)>=418 && neg.distanciaEntreNodos(bahiaBlanca,marDelPlata)<=420);
		
	}
	@Test
	public void agmDanielBertacciniAristaMasCortaDebeEstarEnElAGM() {
		Negocio neg= new Negocio(0,0,1);
		neg.inicializarGrafo();
		neg.cambiarGrafoPor("pruebaDB.json");
		neg.generarGrafoCompleto();
		AGMPrim conexiones=new AGMPrim();
		List<Arista> todasLasAristas = conexiones.AGMPrim(neg.getGrafo());
		Nodo acha=neg.getGrafo().buscarNodoCiudad("General Acha");
		Nodo rosa=neg.getGrafo().buscarNodoCiudad("Santa Rosa");
		Arista ar=new Arista(acha,rosa,Negocio.distanciaEntreNodos(acha,rosa));
		Arista ar2=new Arista(rosa,acha,Negocio.distanciaEntreNodos(acha,rosa));
		assertTrue(todasLasAristas.contains(ar)||todasLasAristas.contains(ar2));
		
		
	}
	@Test
	public void agmDanielBertacciniAristaMasLargaNoDebeEstarEnElAGM() {
		Negocio neg= new Negocio(0,0,1);
		neg.inicializarGrafo();
		neg.cambiarGrafoPor("pruebaDB.json");
		neg.generarGrafoCompleto();
		AGMPrim conexiones=new AGMPrim();
		List<Arista> todasLasAristas = conexiones.AGMPrim(neg.getGrafo());
		Nodo roca=neg.getGrafo().buscarNodoCiudad("General Roca");
		Nodo tandil=neg.getGrafo().buscarNodoCiudad("Tandil");
		Arista ar=new Arista(roca,tandil,Negocio.distanciaEntreNodos(roca,tandil));
		Arista ar2=new Arista(tandil,roca,Negocio.distanciaEntreNodos(tandil,roca));
		assertFalse(todasLasAristas.contains(ar)||todasLasAristas.contains(ar2));
		
	}
	@Test
	public void agmDanielBertacciniCantidadDeAristaCantidadDeVerticeMenosUno() {
		Negocio neg= new Negocio(0,0,1);
		neg.inicializarGrafo();
		neg.cambiarGrafoPor("pruebaDB.json");
		neg.generarGrafoCompleto();
		AGMPrim conexiones=new AGMPrim();
		List<Arista> todasLasAristas = conexiones.AGMPrim(neg.getGrafo());
		assertTrue(todasLasAristas.size()==neg.getGrafo().getTamanio()-1);
	}
	
	
	@Test
	public void agmDanielBertacciniTest() {//test visual.
		Negocio neg= new Negocio(0,0,1);
		neg.inicializarGrafo();
		neg.cambiarGrafoPor("pruebaDB.json");
		neg.generarGrafoCompleto();
	
		System.out.println("*********Todas las aristas del grafo******");
		for (Nodo nodo : neg.getGrafo().getNodos()) {
			for (Arista arista : nodo.getAristas()) {
				System.out.println(arista.getNodoOrigen().getNombreCiudad() + " ---> "
						+ arista.getNodoDestino().getNombreCiudad() + " : " + Math.round(arista.getPeso()) + " km");
			}
		}

		AGMPrim conexiones = new AGMPrim();
		List<Arista> todasLasAristas = conexiones.AGMPrim(neg.getGrafo());
		System.out.println("*********Aristas del Arbol generador minimo******");

		int pesoDelArbolMinimo = 0;
		for (Arista arista : todasLasAristas) {
			System.out.println(arista.getNodoOrigen().getNombreCiudad() + " ---> "
					+ arista.getNodoDestino().getNombreCiudad() + " : " + Math.round(arista.getPeso()) + "km");
			pesoDelArbolMinimo += Math.round(arista.getPeso());
		}
		System.out.println("***********");
		System.out.println("Peso total del arbol minimo: " + pesoDelArbolMinimo);
		
	}
	@Test
	public void calcularPesoTestMarDelPlataAcha624km() {
		neg.setPrecios(0, 0, 1);
		neg.registrarLocalidad("mdp", "bsas", -38.0042, -57.5562);
		neg.registrarLocalidad("acha", "pampa", -37.37698, -64.60431);
		neg.agregarConexion("mdp", "acha");
		//AGMPrim conexiones = new AGMPrim();
	//	List<Arista> todasLasAristas = conexiones.AGMPrim(neg.getGrafo());
		double peso=AGMPrim.pesoDelGrafo(neg.getGrafo());
		assertTrue(peso>623.0&&peso<625.0);
	}
	@Test
	public void calcularPesoSupera300kmMarDelPlataAcha() {
		neg.setPrecios(100, 0, 1);
		neg.registrarLocalidad("mdp", "bsas", -38.0042, -57.5562);
		neg.registrarLocalidad("acha", "pampa", -37.37698, -64.60431);
		neg.agregarConexion("mdp", "acha");
		double peso=AGMPrim.pesoDelGrafo(neg.getGrafo());
		assertTrue(peso>1240.0&&peso<1250.0);
	}
	@Test
	public void calcularPesoSoloProvDistinta() {
		neg.setPrecios(0, 1000, 0);
		neg.registrarLocalidad("mdp", "bsas", -38.0042, -57.5562);
		neg.registrarLocalidad("acha", "pampa", -37.37698, -64.60431);
		neg.agregarConexion("mdp", "acha");
		double peso=AGMPrim.pesoDelGrafo(neg.getGrafo());
		assertTrue(peso==1000.0);
	}
	@Test
	public void calcularPesoProvIguales() {
		neg.setPrecios(0, 1000, 0);
		neg.registrarLocalidad("mdp", "bsas", -38.0042, -57.5562);
		neg.registrarLocalidad("acha", "bsas", -37.37698, -64.60431);
		neg.agregarConexion("mdp", "acha");
		double peso=AGMPrim.pesoDelGrafo(neg.getGrafo());
		assertTrue(peso==0.0);
		
	}
	@Test
	public void calcularPesoTodoAchaMardelPlata624() {
		neg.setPrecios(100, 1000, 1);//1000+624=1624+1624(100%de 1624)=3248
		neg.registrarLocalidad("mdp", "bsas", -38.0042, -57.5562);
		neg.registrarLocalidad("acha", "pampa", -37.37698, -64.60431);
		neg.agregarConexion("mdp", "acha");
		double peso=AGMPrim.pesoDelGrafo(neg.getGrafo());
		assertTrue(peso>=3247&&peso<=3250);
	}
	
	
	
}
