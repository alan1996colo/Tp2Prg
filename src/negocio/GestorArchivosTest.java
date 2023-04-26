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
		gestor.leerArchivoXml(name);
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
	@After public void cleanUp() {
		//System.out.print("cleanUp_");
		gestor.borrarArchivo(name);
		
	
	}

}
