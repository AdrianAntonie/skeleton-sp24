import java.util.Arrays;

public class UnionFind {
    // TODO: Instance variables
    int[] parent;
    int size;

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
        parent = new int[N];
        Arrays.fill(parent, -1);
        size = N;
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        return -parent[find(v)];
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        return parent[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        int fv1 = find(v1);
        int fv2 = find(v2);
        return fv1 == v2 || fv2 == v1 || (fv1 == fv2 && fv1 >= 0);
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        if(v < 0 || v >= size) {
            throw new IllegalArgumentException(v + " is not a valid node.");
        }
        int v2 = v;
        while(parent[v] >= 0) {
            v = parent[v];
        }
        int root = v;
        v = v2;
        while(parent[v] >= 0) {
            v2 = parent[v];
            parent[v] = root;
            v = v2;
        }
        return root;
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        int fv1 = find(v1);
        int fv2 = find(v2);
        if(fv1 == fv2) {
            return;
        }
        if(fv1 > fv2) {
            parent[fv1] += parent[fv2];
            parent[fv2] = fv1;
        } else {
            parent[fv2] += parent[fv1];
            parent[fv1] = fv2;
        }
    }

}
