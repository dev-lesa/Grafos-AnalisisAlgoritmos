package algoritmosgrafos;

import java.util.ArrayList;
import java.util.List;

public class GrafoNoDirigido {

    private int V;
    private List<Arista> aristas;
    private String[] nombres;

    public GrafoNoDirigido(int V, String[] nombres) {
        this.V = V;
        this.nombres = nombres;
        this.aristas = new ArrayList<>();
    }

    public void agregarArista(int u, int v, int peso) {
        aristas.add(new Arista(u - 1, v - 1, peso));
    }

    public int getV() {
        return V;
    }

    public List<Arista> getAristas() {
        return aristas;
    }

    public String getNombre(int i) {
        return nombres[i];
    }

    public String[] getNombres() {
        return nombres;
    }

    public void imprimir() {
        System.out.println("grafo no dirigido - red de influencia musical");
        System.out.println("vertices: " + V + "    |    aristas: " + aristas.size());
        System.out.println();
        System.out.println("lista de vertices:");
        for (int i = 0; i < V; i++) {
            System.out.println("    " + (i + 1) + ". " + nombres[i]);
        }
        System.out.println();
        System.out.println("lista de aristas (origen -- destino, peso):");
        System.out.println("------------------------------------------------------");
        System.out.printf("    %-18s    %-18s    peso%n", "origen", "destino");
        System.out.println("------------------------------------------------------");
        for (Arista a : aristas) {
            System.out.printf("    %-18s    %-18s    %d%n",
                    nombres[a.origen], nombres[a.destino], a.peso);
        }
        System.out.println("------------------------------------------------------");
    }
}
