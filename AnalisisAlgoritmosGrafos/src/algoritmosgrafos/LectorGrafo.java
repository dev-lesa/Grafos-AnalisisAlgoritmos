package algoritmosgrafos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorGrafo {

    public static GrafoNoDirigido leerNoDirigido(String ruta) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            int V = 0;
            String[] nombres = null;
            List<int[]> aristasBruto = new ArrayList<>();
            boolean enVertices = false, enAristas = false;
            int contV = 0;

            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue;
                }

                if (linea.equals("VERTICES")) {
                    enVertices = true;
                    enAristas = false;
                    continue;
                }
                if (linea.equals("ARISTAS")) {
                    enAristas = true;
                    enVertices = false;
                    continue;
                }

                if (enVertices) {
                    if (V == 0) {
                        V = Integer.parseInt(linea);
                        nombres = new String[V];
                    } else if (contV < V) {
                        nombres[contV++] = linea;
                    }
                } else if (enAristas) {
                    String[] p = linea.split(",");
                    aristasBruto.add(new int[]{
                        Integer.parseInt(p[0].trim()),
                        Integer.parseInt(p[1].trim()),
                        Integer.parseInt(p[2].trim())
                    });
                }
            }

            GrafoNoDirigido g = new GrafoNoDirigido(V, nombres);
            for (int[] a : aristasBruto) {
                g.agregarArista(a[0], a[1], a[2]);
            }
            System.out.println("archivo leido: " + ruta);
            return g;

        } catch (IOException e) {
            System.out.println("no se pudo leer: " + ruta);
            return null;
        }
    }

    public static GrafoDirigido leerDirigido(String ruta) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            int V = 0;
            String[] nombres = null;
            List<int[]> aristasBruto = new ArrayList<>();
            boolean enVertices = false, enAristas = false;
            int contV = 0;

            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue;
                }

                if (linea.equals("VERTICES")) {
                    enVertices = true;
                    enAristas = false;
                    continue;
                }
                if (linea.equals("ARISTAS")) {
                    enAristas = true;
                    enVertices = false;
                    continue;
                }

                if (enVertices) {
                    if (V == 0) {
                        V = Integer.parseInt(linea);
                        nombres = new String[V];
                    } else if (contV < V) {
                        nombres[contV++] = linea;
                    }
                } else if (enAristas) {
                    String[] p = linea.split(",");
                    aristasBruto.add(new int[]{
                        Integer.parseInt(p[0].trim()),
                        Integer.parseInt(p[1].trim()),
                        Integer.parseInt(p[2].trim())
                    });
                }
            }

            GrafoDirigido g = new GrafoDirigido(V, nombres);
            for (int[] a : aristasBruto) {
                g.agregarArista(a[0], a[1], a[2]);
            }
            System.out.println("archivo leido: " + ruta);
            return g;

        } catch (IOException e) {
            System.out.println("no se pudo leer: " + ruta);
            return null;
        }
    }
}
