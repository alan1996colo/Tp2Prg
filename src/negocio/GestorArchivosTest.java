package negocio;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class GestorArchivosTest {
	GestorArchivos gestor;
	String name, texto;
	
	@Before public void setUp() {
		 gestor= new GestorArchivos();
		 name="test.txt";
		 texto="1234";}

	
	@Test
	public void leerArchivoXmlExistente() throws FileNotFoundException {
		gestor.leerArchivoXml("ejemplo.xml");
	}
	@Test(expected=FileNotFoundException.class)
	public void leerArchivoXmlINexistente() throws FileNotFoundException {
		gestor.leerArchivoXml("nada.xml");//-->[Fatal Error] test.txt:1:1: Final de archivo prematuro.
	}
	@Test
	public void testCrear() {
	
		assertTrue(gestor.crearArchivo(name));
		
	}
	@Test
	public void escribirArchivo() {
		
		assertTrue(gestor.escribirArchivo(name,texto));
		
		
	}
	
	@Test
	public void leerArchivoExistente() {
		gestor.crearArchivo(name);
		assertTrue(gestor.leerArchivo(name));
	}
	 @Test //(expected = FileNotFoundException.class)
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
		 Nodo nodo= new Nodo("san miguel","bsas",5432,2345);
		 assertTrue(gestor.serializarObjeto(nodo));
	 }
	 @Test
	 public void serializarObjetoNulo() {
		  assertFalse(gestor.serializarObjeto(null));
	 }
	 @Test
	 public void cargarSerializado() {
		 Nodo nodo= new Nodo("san miguel","bsas",5432,2345);
		 gestor.serializarObjeto(nodo);
		 Nodo nodo2=(Nodo) gestor.dameObjetoSerializado("Nodo");
		 assertEquals(nodo2.getNombreCiudad(),"san miguel");
		 
	 }
	 @Test (expected= NullPointerException.class)
	 public void cargarSerializadoNoExiste() {
		 Nodo nodo2=(Nodo) gestor.dameObjetoSerializado("nada");
		 assertEquals(nodo2.getNombreCiudad(),null);
	 }
	 
	 
	@After public void cleanUp() {
		//System.out.print("cleanUp_");
		gestor.borrarArchivo(name);
		gestor.borrarArchivo("Nodo.txt");
	
	}

}
