package algoritmosgrafos;

import java.util.ArrayList;
import java.util.List;

public class GrafoDirigido {

    private int V;
    private List<List<Arista>> adyacencia;
    private String[] nombres;
    private int totalAristas;

    public GrafoDirigido(int V, String[] nombres) {
        this.V = V;
        this.nombres = nombres;
        this.totalAristas = 0;
        adyacencia = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adyacencia.add(new ArrayList<>());
        }
    }

    public void agregarArista(int u, int v, int peso) {
        adyacencia.get(u - 1).add(new Arista(u - 1, v - 1, peso));
        totalAristas++;
    }

    public int getV() {
        return V;
    }

    public List<Arista> getVecinos(int v) {
        return adyacencia.get(v);
    }

    public String getNombre(int i) {
        return nombres[i];
    }

    public String[] getNombres() {
        return nombres;
    }

    public void imprimir() {
        System.out.println("grafo dirigido - flujo de influencia musical");
        System.out.println("vertices: " + V + "    |    aristas: " + totalAristas);
        System.out.println();
        System.out.println("lista de adyacencia:");
        System.out.println("------------------------------------------------------");
        for (int i = 0; i < V; i++) {
            System.out.print("    [" + (i + 1) + "] " + nombres[i] + " -> ");
            List<Arista> vec = adyacencia.get(i);
            if (vec.isEmpty()) {
                System.out.println("(sin salidas)");
            } else {
                StringBuilder sb = new StringBuilder();
                for (Arista a : vec) {
                    sb.append(nombres[a.destino]).append("(").append(a.peso).append(")    ");
                }
                System.out.println(sb.toString().trim());
            }
        }
        System.out.println("------------------------------------------------------");
    }
}
