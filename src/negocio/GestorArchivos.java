package negocio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.Element;

public class GestorArchivos {
	
	//Recibe la ruta del archivo xml
	public void leerArchivoXml(String path) throws FileNotFoundException {
		try {
		DocumentBuilder builder=DocumentBuilderFactory.newInstance().newDocumentBuilder();
		File f = new File(path);
		Document documento= builder.parse(f);
		recorrerXml(documento);}
		catch(Exception e){throw new FileNotFoundException("Parece que no se encontro el archivo");
			
		}
		
		
	};
	
	
	public void recorrerXml(Node node) {
	    // Recorrer los hijos del nodo actual
	    NodeList hijos = node.getChildNodes();
	    for (int i = 0; i < hijos.getLength(); i++) {
	        Node hijo = hijos.item(i);
	        // Si el hijo es un elemento, imprimir su nombre y atributos
	        if (hijo.getNodeType() == Node.ELEMENT_NODE) {
	            Element elemento = (Element) hijo;
	            System.out.println("Elemento: " + elemento.getTagName());
	            NamedNodeMap atributos = elemento.getAttributes();
	            for (int j = 0; j < atributos.getLength(); j++) {
	                Node atributo = atributos.item(j);
	                System.out.println("Atributo: " + atributo.getNodeName() + " = " + atributo.getNodeValue());
	            }
	            // Recorrer los hijos de este elemento
	            recorrerXml(hijo);
	        }
	        // Si el hijo es un texto, imprimir su contenido
	        else if (hijo.getNodeType() == Node.TEXT_NODE) {
	            Text texto = (Text) hijo;
	            String contenido = texto.getWholeText().trim();
	            if (!contenido.isEmpty()) {
	                System.out.println("Texto: " + contenido);
	            }
	        }
	    }
	}

	
	/*Crea un archivo txt, con el nombre pasado, true-> se logro, false->algo fallo*/
	public boolean crearArchivo(String fname) {
		try {
			FileOutputStream fos = new FileOutputStream(fname);
			OutputStreamWriter out = new OutputStreamWriter(fos);
			
			out.close();
			return true;
			
		}
		catch(Exception e){ System.out.println("Hubo algun problema con el manejo de archivos");
			return false;
		}
	}
	
	public boolean escribirArchivo(String fname, String palabra) {
		//modificar mas adelante para que no cree un archivo nuevo, y solo intente escribir desde uno ya creado
		try {
			FileOutputStream fos = new FileOutputStream(fname);
			OutputStreamWriter out = new OutputStreamWriter(fos);
			out.write(palabra);
			
			out.close();
			return true;
			
		}
		catch(Exception e){ System.out.println("Hubo algun problema con el manejo de archivos");
			return false;
		}
	}
	
	public boolean borrarArchivo(String fname) {
		File fichero = new File(fname);
		if (fichero.delete()) {
			//   System.out.println("El fichero ha sido borrado satisfactoriamente");
			   return true;
		}
			else {
			  // System.out.println("El fichero no puede ser borrado");
			   return false;
			}
		
	}
	public boolean leerArchivo(String fname) {
		try {
		FileInputStream fis= new FileInputStream(fname);
		Scanner scan= new Scanner(fis);
		while(scan.hasNextLine()) {
			String s1=scan.nextLine();
			System.out.println(s1);
		}
		scan.close();
		return true;
	}catch(FileNotFoundException e){
	//	e.printStackTrace(); esto es re molesto verlo en la consola, dejemoslo en return false
		return false;
		
	}
		
	}
	
	

}
