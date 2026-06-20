package algoritmosgrafos;

public class UnionFind {

    private int[] padre;
    private int[] rango;

    public UnionFind(int n) {
        padre = new int[n];
        rango = new int[n];
        for (int i = 0; i < n; i++) {
            padre[i] = i;    
            rango[i] = 0;
        }
    }


    public int find(int x) {
        if (padre[x] != x) {
            padre[x] = find(padre[x]); 
        }
        return padre[x];
    }


    public boolean union(int x, int y) {
        int raizX = find(x);
        int raizY = find(y);

        if (raizX == raizY) {
            return false; 
        }

        if (rango[raizX] < rango[raizY]) {
            padre[raizX] = raizY;
        } else if (rango[raizX] > rango[raizY]) {
            padre[raizY] = raizX;
        } else {
            padre[raizY] = raizX;
            rango[raizX]++;
        }
        return true;
    }

    public boolean mismoConjunto(int x, int y) {
        return find(x) == find(y);
    }
}
