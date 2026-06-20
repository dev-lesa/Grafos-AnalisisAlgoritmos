package algoritmosgrafos;

public class Arista implements Comparable<Arista> {

    public int origen;
    public int destino;
    public int peso;

    public Arista(int origen, int destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    @Override
    public int compareTo(Arista otra) {
        return Integer.compare(this.peso, otra.peso);
    }

    @Override
    public String toString() {
        return "(" + origen + " -- " + destino + ", peso=" + peso + ")";
    }
}
