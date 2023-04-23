package negocio;

public class Arista {
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