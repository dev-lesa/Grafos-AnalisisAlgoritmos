package algoritmosgrafos;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {

    private static final int INF = Integer.MAX_VALUE / 2;
    private GrafoDirigido grafo;

    public Dijkstra(GrafoDirigido grafo) {
        this.grafo = grafo;
    }

    public void ejecutar(int fuenteUI) {
        int fuente = fuenteUI - 1;
        int V = grafo.getV();
        String[] nombres = grafo.getNombres();

        if (fuente < 0 || fuente >= V) {
            System.out.println("nodo invalido, debe ser entre 1 y " + V);
            return;
        }

        System.out.println();
        System.out.println("==================================================");
        System.out.println("    Algoritmo de Dijkstra - Caminos de menor costo");
        System.out.println("    nodo fuente: [" + fuenteUI + "] " + nombres[fuente]);
        System.out.println("==================================================");

        int[] dist = new int[V];
        int[] prev = new int[V];
        boolean[] visitado = new boolean[V];

        Arrays.fill(dist, INF);
        Arrays.fill(prev, -1);
        dist[fuente] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{0, fuente});

        // inicializacion
        System.out.println();
        System.out.println("inicializacion:");
        System.out.println("--------------------------------------------------");
        System.out.printf("    %-18s    dist%n", "vertice");
        System.out.println("--------------------------------------------------");
        for (int i = 0; i < V; i++) {
            System.out.printf("    %-18s    %s%n", nombres[i], i == fuente ? "0" : "INF");
        }

        // procesamiento
        System.out.println();
        System.out.println("procesamiento:");
        System.out.println("--------------------------------------------------");

        int iter = 1;
        while (!pq.isEmpty()) {
            int[] actual = pq.poll();
            int u = actual[1];
            if (visitado[u]) {
                continue;
            }
            visitado[u] = true;

            System.out.println();
            System.out.println("    paso " + iter + ": v=[" + (u + 1) + "] " + nombres[u] + "    dist=" + dist[u]);
            iter++;

            for (Arista a : grafo.getVecinos(u)) {
                int v = a.destino;
                int nueva = dist[u] + a.peso;
                String distActual = dist[v] == INF ? "INF" : String.valueOf(dist[v]);

                System.out.printf("        D[%d]=%s, D[%d]+L[%d,%d]=%d+%d=%d -> ",
                        v + 1, distActual, u + 1, u + 1, v + 1, dist[u], a.peso, nueva);

                if (nueva < dist[v]) {
                    System.out.println("min(" + distActual + "," + nueva + ")=" + nueva + " -> actualizado");
                    dist[v] = nueva;
                    prev[v] = u;
                    pq.offer(new int[]{nueva, v});
                } else {
                    System.out.println("min(" + distActual + "," + nueva + ")=" + distActual + " -> sin cambio");
                }
            }
        }

        System.out.println();
        System.out.println("==================================================");
        System.out.println("    Resultado: distancias minimas desde " + nombres[fuente]);
        System.out.println("==================================================");
        System.out.printf("    %-3s    %-18s    %-6s    camino%n", "id", "destino", "costo");
        System.out.println("--------------------------------------------------");
        for (int i = 0; i < V; i++) {
            String d = dist[i] == INF ? "INF" : String.valueOf(dist[i]);
            String camino = reconstruir(prev, i, nombres, fuente);
            System.out.printf("    %-3d    %-18s    %-6s    %s%n", i + 1, nombres[i], d, camino);
        }
        System.out.println("==================================================");
    }

    private String reconstruir(int[] prev, int dest, String[] nombres, int fuente) {
        if (dest == fuente) {
            return nombres[fuente];
        }
        StringBuilder sb = new StringBuilder();
        int actual = dest;
        while (actual != -1) {
            sb.insert(0, nombres[actual]);
            if (actual == fuente) {
                break;
            }
            if (prev[actual] == -1) {
                return "no alcanzable";
            }
            sb.insert(0, " -> ");
            actual = prev[actual];
        }
        return sb.toString();
    }
}
