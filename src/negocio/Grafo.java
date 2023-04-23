package grafos;


import java.util.HashSet;
import java.util.Set;

public class Grafo {
	// representacion como matriz de adyacencia
	private boolean [][] A;
		
	// en este caso la cantidad de vertices esta predeterminada por el constructor
	public Grafo (int vertices)
	{
		A= new boolean [vertices][vertices];		
	}   
	
	public void agregarArista(int i, int j)
	{
		verificarVertice(i);	
		verificarVertice(j);	
		verificarDistintos(i, j);
		
		A [i][j]=true;
		A [j][i]=true;
	}
	public void eliminarArista(int i, int j)
	{
		verificarVertice(i);	
		verificarVertice(j);	
		verificarDistintos(i, j);
		
		A [i][j]=false;
		A [j][i]=false;
	}	
	public boolean existeArista(int i, int j)
	{
		verificarVertice(i);	
		verificarVertice(j);
		verificarDistintos(i,j);
		return A [i][j];
	}
	public int tamanio()
	{
		return A.length;
	}
	
	//vecinos de un vertice, implementacion en un SET
	public Set<Integer> vecinos(int i)
	{
		verificarVertice(i);
		Set<Integer> ret = new HashSet<Integer>();
		for (int j=0; j<this.tamanio();j++) if (i !=j)
		{
			if(this.existeArista(i,j))
				ret.add(j); 
		}				
		return ret;
	}
	
	// verifica que no se cree un loop
	private void verificarDistintos(int i, int j) {
		if (i==j)
			throw new IllegalArgumentException("No se permiten loops : (" + i + ", " + j + ")");
	}
	// verifica que sea un vertice valido	
	private void verificarVertice(int i) {
		if (i <0)
			throw new IllegalArgumentException("El vertice no puede ser negativo: " + i);
		if (i>=A.length)
			throw new IllegalArgumentException("Los vertices deben estar entre 0 y |V| : " + i);
	}	
	public static void main (String[] args)
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 3);
		grafo.agregarArista(3, 4);
		System.out.println(grafo.existeArista(2, 3));
		System.out.println(grafo.existeArista(1, 1));
	}

	
	
}
