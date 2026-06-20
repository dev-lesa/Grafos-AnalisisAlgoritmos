package algoritmosgrafos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kruskal {

        private GrafoNoDirigido grafo;

        public Kruskal(GrafoNoDirigido grafo) {
                this.grafo = grafo;
        }

        public void ejecutar() {
                int V = grafo.getV();
                String[] nombres = grafo.getNombres();

                System.out.println();
                System.out.println("==================================================");
                System.out.println("    Algoritmo de Kruskal - Arbol de Recubrimiento Minimo");
                System.out.println("==================================================");

                // paso 1: ordenar aristas
                List<Arista> aristas = new ArrayList<>(grafo.getAristas());
                Collections.sort(aristas);

                System.out.println();
                System.out.println("paso 1: aristas ordenadas por peso");
                System.out.println("--------------------------------------------------");
                System.out.printf("    %-3s    %-16s    %-16s    peso%n", "N", "origen", "destino");
                System.out.println("--------------------------------------------------");
                int pos = 1;
                for (Arista a : aristas) {
                        System.out.printf("    %-3d    %-16s    %-16s    %d%n",
                                        pos++, nombres[a.origen], nombres[a.destino], a.peso);
                }

                // paso 2: inicializar union-find
                UnionFind uf = new UnionFind(V);
                List<Arista> arm = new ArrayList<>();
                int costoTotal = 0;

                System.out.println();
                System.out.println("paso 2: conjuntos iniciales (Union-Find)");
                System.out.print("    ");
                for (int i = 0; i < V; i++) {
                        System.out.print("{" + (i + 1) + "}");
                        if (i < V - 1) {
                                System.out.print(", ");
                        }
                }
                System.out.println();

                // paso 3: procesar aristas
                System.out.println();
                System.out.println("paso 3: procesar aristas");
                System.out.println("--------------------------------------------------");

                int paso = 1;
                for (Arista a : aristas) {
                        if (arm.size() == V - 1) {
                                break;
                        }

                        String origen = nombres[a.origen];
                        String destino = nombres[a.destino];

                        System.out.printf("    e=(%s, %s)=%d%n", origen, destino, a.peso);
                        System.out.println("        compu=" + conjuntoStr(uf, a.origen, V)
                                        + "    compv=" + conjuntoStr(uf, a.destino, V));

                        if (uf.mismoConjunto(a.origen, a.destino)) {
                                System.out.println("        -> rechazada, formaria un ciclo");
                        } else {
                                uf.union(a.origen, a.destino);
                                arm.add(a);
                                costoTotal += a.peso;
                                System.out.println("        -> aceptada | ARM tiene " + arm.size() + "/" + (V - 1) + " aristas");
                                System.out.println("        conjuntos: " + todosConjuntosStr(uf, V));
                        }
                        paso++;
                }

                System.out.println();
                System.out.println("==================================================");
                System.out.println("    Resultado: Arbol de Recubrimiento Minimo");
                System.out.println("==================================================");
                System.out.printf("    %-18s    %-18s    peso%n", "origen", "destino");
                System.out.println("--------------------------------------------------");
                for (Arista a : arm) {
                        System.out.printf("    %-18s    %-18s    %d%n",
                                        nombres[a.origen], nombres[a.destino], a.peso);
                }
                System.out.println("--------------------------------------------------");
                System.out.println("    costo total del ARM: " + costoTotal);
                System.out.println("    aristas del ARM: " + arm.size() + " (de " + grafo.getAristas().size() + " totales)");
                System.out.println("==================================================");
        }

        private String conjuntoStr(UnionFind uf, int v, int V) {
                int raiz = uf.find(v);
                StringBuilder sb = new StringBuilder("{");
                boolean primero = true;
                for (int i = 0; i < V; i++) {
                        if (uf.find(i) == raiz) {
                                if (!primero) {
                                        sb.append(", ");
                                }
                                sb.append(i + 1);
                                primero = false;
                        }
                }
                sb.append("}");
                return sb.toString();
        }

        private String todosConjuntosStr(UnionFind uf, int V) {
                List<String> vistos = new ArrayList<>();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < V; i++) {
                        int raiz = uf.find(i);
                        String clave = String.valueOf(raiz);
                        if (!vistos.contains(clave)) {
                                vistos.add(clave);
                                if (sb.length() > 0) {
                                        sb.append(", ");
                                }
                                sb.append(conjuntoStr(uf, i, V));
                        }
                }
                return sb.toString();
        }
}
