package grafos;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class BFS
{	
	private static ArrayList<Integer> L;
	private static boolean [] marcados;
	

	public static boolean esConexo(Grafo g) 
	{
		if (g==null)
			throw new IllegalArgumentException("Se consulta un grafo null");
		if (g.tamanio()==0)
			return true;	
		
		return alcanzables(g,0).size()==g.tamanio();
	}


	public static Set<Integer> alcanzables (Grafo g, int origen)
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


	private static void agregarVecinosPendientes(Grafo g, int i)
	{
		for (int vertice : g.vecinos(i))
			if (marcados[vertice]==false && L.contains(vertice)==false)
				L.add(vertice);
	}


	private static void inicializarRecorrido(Grafo g, int origen) {
		L = new ArrayList<Integer>();
		marcados= new boolean [g.tamanio()];
		L.add(origen);
	}

}
