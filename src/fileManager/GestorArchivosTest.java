package fileManager;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import negocio.GrafoLista;
import negocio.Nodo;

public class GestorArchivosTest {
	GestorArchivos gestor;
	String name, texto;
	String path;

	@Before
	public void setUp() {
		gestor = new GestorArchivos();
		name = "test.txt";
		texto = "1234";
		path="src/fileManager/";
	}

	@Test
	public void leerArchivoXmlExistente() throws FileNotFoundException {
		gestor.leerArchivoXml("ejemplo.xml");
	}

	@Test(expected = FileNotFoundException.class)
	public void leerArchivoXmlINexistente() throws FileNotFoundException {
		gestor.leerArchivoXml("nada.xml");// -->[Fatal Error] test.txt:1:1: Final de archivo prematuro.
	}

	@Test
	public void testCrear() {

		assertTrue(gestor.crearArchivo(name));

	}

	@Test
	public void escribirArchivo() {

		assertTrue(gestor.escribirArchivo(name, texto));

	}

	@Test
	public void leerArchivoExistente() {
		gestor.crearArchivo(name);
		assertTrue(gestor.leerArchivo(name));
	}

	@Test // (expected = FileNotFoundException.class)
	public void leerArchivoInexistente() {
		assertFalse(gestor.leerArchivo("nope"));
	}

	@Test
	public void borrarArchivoQueNoExiste() {
		assertFalse(gestor.borrarArchivo(name));
	}

	@Test
	public void borrarArchivoQueSiExiste() {
		gestor.crearArchivo(name);
		assertTrue(gestor.borrarArchivo(name));
	}

	@Test
	public void serializarObjeto() {
		Nodo nodo = new Nodo("san miguel", "bsas", 5432, 2345);
		assertTrue(gestor.serializarObjeto(nodo));
	}

	@Test
	public void serializarObjetoNulo() {
		assertFalse(gestor.serializarObjeto(null));
	}

	@Test
	public void cargarSerializado() {
		Nodo nodo = new Nodo("san miguel", "bsas", 5432, 2345);
		gestor.serializarObjeto(nodo);
		Nodo nodo2 = (Nodo) gestor.dameObjetoSerializado("Nodo");
		assertEquals(nodo2.getNombreCiudad(), "san miguel");

	}

	@Test
	public void generarJSon() {
		Nodo nodo = new Nodo("muñiz", "Tucuman", 5432, 2345);
		assertTrue(gestor.generarJSON("archivojason.json", nodo));

	}

	@Test
	public void generarJsonNULL() {
		assertFalse(gestor.generarJSON("archivaso", null));
	}

	@Test
	public void leerJSON() {
		Nodo nodo = new Nodo("san miguel", "bsas", 5432, 2345);
		gestor.generarJSON("archivojason", nodo);
		assertEquals(nodo.getNombreCiudad(), "san miguel");

	}

	@Test
	public void cargarJsonEnGrafoLista() {
		GrafoLista temp = new GrafoLista(gestor.cargarJsonLista("argentinaCitys.json"));
		System.out.println(temp.getNodoNum(8).getNombreCiudad());
	}
	@Test
	public void cargarJsonEnGrafoListaGetNumber() {
		GrafoLista temp = new GrafoLista(gestor.cargarJsonLista("argentinaCitys.json"));
		System.out.println(temp.getNodoNum(9).getNombreCiudad());
		System.out.println(temp.getNodoNum(9).getLatitud());
		
	}
	@Test
	public void SerializarListaEnJSON() {
		GrafoLista temp=new GrafoLista();
		Nodo nodo = new Nodo("muz", "Tucuan", 523432, 2345);
		Nodo nodo2 = new Nodo("muiz", "Tuuman", 51432, -2345);
		Nodo nodo3 = new Nodo("mz", "Tucuan", -5432, 2345);
		temp.agregarNodo(nodo3);
		temp.agregarNodo(nodo2);
		temp.agregarNodo(nodo);
		gestor.generarJSONdesdeLista(path+"jasonlista.json",temp.getNodos());
	}

	@After
	public void cleanUp() {
		// System.out.print("cleanUp_");
		gestor.borrarArchivo(name);
		gestor.borrarArchivo("Nodo");
		gestor.borrarArchivo("archivojason");
		gestor.borrarArchivo("archivojason.json");
		gestor.borrarArchivo("jasonlista.json");
		
	}
	@Ignore
	public void testNull() {}

}
