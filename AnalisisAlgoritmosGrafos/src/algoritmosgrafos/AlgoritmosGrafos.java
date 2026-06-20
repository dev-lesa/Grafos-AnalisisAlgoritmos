package algoritmosgrafos;

import java.util.Scanner;

public class AlgoritmosGrafos {

    private static final String ARCHIVO_KRUSKAL = "data/grafo_kruskal.txt";
    private static final String ARCHIVO_DIJKSTRA = "data/grafo_dijkstra.txt";

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.println();
        System.out.println("================================================");
        System.out.println("    Red de influencia musical - indie/alternativo");
        System.out.println("    Kruskal y Dijkstra | Tarea individual");
        System.out.println("================================================");

        int opcion;
        do {
            System.out.println();
            System.out.println("Menu:");
            System.out.println("    1. Kruskal    (grafo no dirigido -> arbol minimo)");
            System.out.println("    2. Dijkstra (grafo dirigido -> camino mas corto)");
            System.out.println("    3. Ver lista de aristas no dirigido");
            System.out.println("    4. Ver lista de aristas dirigido");
            System.out.println("    5. Salir");
            System.out.print("opcion: ");
            while (!entrada.hasNextInt()) {
                entrada.next();
            }
            opcion = entrada.nextInt();

            switch (opcion) {
                case 1:
                    ejecutarKruskal();
                    break;
                case 2:
                    ejecutarDijkstra(entrada);
                    break;
                case 3:
                    mostrarNoDirigido();
                    break;
                case 4:
                    mostrarDirigido();
                    break;
                case 5:
                    System.out.println("saliendo...");
                    break;
                default:
                    System.out.println("opcion invalida");
            }
        } while (opcion != 5);

        entrada.close();
    }

    private static void ejecutarKruskal() {
        System.out.println();
        GrafoNoDirigido grafo = LectorGrafo.leerNoDirigido(ARCHIVO_KRUSKAL);
        if (grafo == null) {
            return;
        }
        new Kruskal(grafo).ejecutar();
    }

    private static void ejecutarDijkstra(Scanner sc) {
        System.out.println();
        GrafoDirigido grafo = LectorGrafo.leerDirigido(ARCHIVO_DIJKSTRA);
        if (grafo == null) {
            return;
        }

        String[] nombres = grafo.getNombres();
        System.out.println("artistas disponibles:");
        for (int i = 0; i < nombres.length; i++) {
            System.out.println("    " + (i + 1) + ". " + nombres[i]);
        }
        System.out.print("nodo fuente (1-" + grafo.getV() + "): ");
        while (!sc.hasNextInt()) {
            sc.next();
        }
        int fuente = sc.nextInt();

        new Dijkstra(grafo).ejecutar(fuente);
    }

    private static void mostrarNoDirigido() {
        System.out.println();
        GrafoNoDirigido g = LectorGrafo.leerNoDirigido(ARCHIVO_KRUSKAL);
        if (g != null) {
            g.imprimir();
        }
    }

    private static void mostrarDirigido() {
        System.out.println();
        GrafoDirigido g = LectorGrafo.leerDirigido(ARCHIVO_DIJKSTRA);
        if (g != null) {
            g.imprimir();
        }
    }

}
