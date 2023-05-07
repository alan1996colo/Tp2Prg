package negocio;

import java.util.List;

public class otraPrueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GrafoLista grafo = new GrafoLista();
		Nodo CABA = new Nodo("Buenos Aires","Buenos Aires, Ciudad Autónoma de",-34.5997, -58.3819);	
		grafo.agregarNodo(CABA);		
		Nodo Cordoba = new Nodo("Córdoba","Córdoba",-31.4167, -64.1833);	
		grafo.agregarNodo(Cordoba);		
		Nodo Rosario = new Nodo("Rosario","Santa Fe",-32.9575, -60.6394);	
		grafo.agregarNodo(Rosario);
		Nodo Parana=new Nodo("Paraná","Entre Ríos", -31.7444,-60.5175);
		grafo.agregarNodo(Parana);
		Nodo Merlo=new Nodo("Merlo","Buenos Aires",-34.6653,-58.7275);
		grafo.agregarNodo(Merlo);				
		Nodo Quilmes=new Nodo("Quilmes","Buenos Aires",-34.7167,-58.2667);
		grafo.agregarNodo(Quilmes);
		Nodo Banfield=new Nodo("Banfield","Buenos Aires",-34.7500,-58.3833);
		grafo.agregarNodo(Banfield);								
		Nodo Concordia=new Nodo("Concordia","Entre Rios", -31.3922,-58.0169);
		grafo.agregarNodo(Concordia);												
		Nodo Cafayate=new Nodo("Cafayate","Salta",-26.0700,-65.9800);
		grafo.agregarNodo(Cafayate);		
		Nodo Chepes=new Nodo("Chepes","La Rioja",-31.3500,-66.6000);
		grafo.agregarNodo(Chepes);
		
		grafo.agregarArista(CABA, Rosario);
		grafo.agregarArista(CABA,Cordoba);
		grafo.agregarArista(CABA,Parana);
		grafo.agregarArista(Rosario,Parana);
		grafo.agregarArista(Rosario,Chepes);
		grafo.agregarArista(Cordoba,Parana);
		grafo.agregarArista(Cordoba,Cafayate);
		grafo.agregarArista(Cordoba,Merlo);
	
	
		
		AGMPrim conexiones= new AGMPrim();
		List<Arista> todasLasAristas =conexiones.AGMPrim(grafo);
		
		int pesoDelArbolMinimo=0;
		for (Arista arista : todasLasAristas) {
			
			System.out.println(arista.getNodoOrigen().getNombreCiudad() + " a " + arista.getNodoDestino().getNombreCiudad()+ " Peso : "+ arista.getPeso());
			pesoDelArbolMinimo+=arista.getPeso();
		}
		System.out.println("***********");
		System.out.println("Peso total del arbol minimo: " + pesoDelArbolMinimo);
		
		
		}
		
		
	}

