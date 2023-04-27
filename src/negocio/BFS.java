package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class BFS implements Serializable
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ArrayList<Integer> L;
	private static boolean [] marcados;
	//modificar la clase para que funcione con "GrafoLista" y "Nodo"
	

	public static boolean esConexo(GrafoLista g) 
	{
		if (g==null)
			throw new IllegalArgumentException("Se consulta un grafo null");
		if (g.getTamanio()==0)
			return true;	
		
		return alcanzables(g,0).size()==g.getTamanio();
	}


	public static Set<Integer> alcanzables (GrafoLista g, int origen)
	{
		Set<Integer> ret = new HashSet<Integer>();
		inicializarRecorrido(g,origen);
		while (L.size()>0)
		{
			int i=L.get(0);
			marcados[i]=true;
			ret.add(i); 
			agregarVecinosPendientes(g,i);
			L.remove(0);			
		}	
		return ret;
	}


	private static void agregarVecinosPendientes(GrafoLista g, int i)
	{int cont=0;
		for ( Arista vertice : g.getVecinos(i)) 
			
			if (marcados[cont]==false && L.contains(vertice)==false)
				L.add(cont);
		cont++;
	}


	private static void inicializarRecorrido(GrafoLista g, int origen) {
		L = new ArrayList<Integer>();
		marcados= new boolean [g.getTamanio()];
		L.add(origen);
	}

}
