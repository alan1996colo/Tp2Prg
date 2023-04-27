package negocio;

import java.io.Serializable;

public class Arista implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Nodo nodoDestino;
    private double peso;

    public Arista(Nodo nodoDestino, double peso) {
        this.nodoDestino = nodoDestino;
        this.peso = peso;
    }

    public Nodo getNodoDestino() {
        return nodoDestino;
    }

    public double getPeso() {
        return peso;
    }   
    
}