package negocio;

import java.io.Serializable;

public class Arista implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Nodo nodoOrigen;
	private Nodo nodoDestino;
    private double peso;

    public Arista(Nodo nodoOrigen, Nodo nodoDestino, double peso) {
    	this.nodoOrigen=nodoOrigen;
        this.nodoDestino = nodoDestino;
        this.peso = peso;
    }
    public Nodo getNodoOrigen() {
        return nodoOrigen;
    }

    public Nodo getNodoDestino() {
        return nodoDestino;
    }

    public double getPeso() {
        return peso;
    }   
   
    public String toString() {
    	return "-->"+getNodoOrigen().getNombreCiudad()+ "--> " + getNodoDestino().getNombreCiudad()+" "+getPeso();
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nodoDestino == null) ? 0 : nodoDestino.hashCode());
        result = prime * result + ((nodoOrigen == null) ? 0 : nodoOrigen.hashCode());
        result = prime * result +(int) peso;
        return result;
    }
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final Arista other = (Arista) obj;
        if ((nodoDestino.equals(other.nodoDestino) && nodoOrigen.equals(other.nodoOrigen)) ||
                (nodoDestino.equals(other.nodoOrigen) && nodoOrigen.equals(other.nodoDestino))) {
            return true;
        }
        return false;
    }


    
    
}