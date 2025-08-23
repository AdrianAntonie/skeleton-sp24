import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.LinkedList;
import java.util.List;

public class Percolation {
    //int xyz
    //x = 1 => not open
    //x = 2 => open
    //y = 1 => not full
    //y = 2 => full
    // !!!if not open but full it's only convenience the sites are not open and not full
    //z = 1 => not bottom line connected
    //z = 2 => bottom line connected
    private int[][] grid;
    //N * N disjoint grid data structure
    //use convert to get from grid to here
    //to get from here to grid n / 10 is x and n % 10 is y
    private WeightedQuickUnionUF disjointGrid;

    private int numberOfOpenSites = 0;
    private boolean percolates = false;
    private int n;


    public Percolation(int N) {
        if(N <= 0) {
            throw new java.lang.IndexOutOfBoundsException("Invalid");
        }
        n = N;
        grid = new int[N][N];
        int i = 0;
        for(int j = 0; j < N; j++) {
            grid[i][j] = 121;
        }
        for(i = 1; i < N - 1; i++) {
            for(int j = 0; j < N; j++) {
                grid[i][j] = 111;
            }
        }
        for(int j = 0; j < N; j++) {
            if(N == 1) {
                grid[N - 1][j] = 122;
            } else {
                grid[N - 1][j] = 112;
            }
        }
        disjointGrid = new WeightedQuickUnionUF(N * N);
    }

    public void open(int row, int col) {
        if(!inBounds(row, col, n)) {
            throw new java.lang.IndexOutOfBoundsException("Invalid cell");
        }
        if(!isOpen(row, col)) {
            grid[row][col] += 100;
            numberOfOpenSites++;
            if(n == 1) {
                percolates = true;
            }
        }
        neighbourChecks(row, col);
    }

    public boolean isOpen(int row, int col) {
        if(!inBounds(row, col, n)) {
            throw new java.lang.IndexOutOfBoundsException("Invalid cell");
        }
        return grid[row][col] / 100 == 2;
    }

    public boolean isFull(int row, int col) {
        if(!inBounds(row, col, n)) {
            throw new java.lang.IndexOutOfBoundsException("Invalid cell");
        }
        //return isOpen(row, col) && (grid[row][col] / 10 % 10) == 2;
        int root = disjointGrid.find(convert(row, col));
        int rootX = root / n;
        int rootY = root % n;
        return isOpen(rootX, rootY) && grid[rootX][rootY] / 10 % 10 == 2;
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        return percolates;
    }


    //Converts coords from grid to disjoint set
    private int convert(int x, int y) {
        //return x * 10 + y;
        return x * n + y;
    }

    //Changes open full and percolates states for site and neighbours
    private void neighbourChecks(int x, int y) {
        List<Integer> neighbours = getNeighbours(x, y);
        for(int neighbour: neighbours) {
            check(neighbour, x, y);
        }

    }

    //Returns all the existing neighbours of an x, y represented element in a list of Integers of type x * 10 + y
    private List<Integer> getNeighbours(int x, int y) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        List<Integer> neighbours = new LinkedList<>();
        for(int[] d : directions) {
            int nx = x + d[0];
            int ny = y + d[1];
            if(inBounds(nx, ny, n)) {
                neighbours.add(convert(nx, ny));
            }
        }
        return neighbours;
    }

    // @return true if inBounds
    private boolean inBounds(int nx, int ny, int n) {
        return nx >= 0 && nx < n && ny >= 0 && ny < n;
    }

    // Check for important properties of a neighbour: open, full, z = 2
    private void check(int neighbour, int elX, int elY) {
        int rootN = disjointGrid.find(neighbour);
        int rootE = disjointGrid.find(convert(elX, elY));
        int rootNx = rootN / n;
        int rootNy = rootN % n;
        int rootEx = rootE / n;
        int rootEy = rootE % n;
        int newValue = 0;
        if(isOpen(rootNx, rootNy)) {
            for(int i = 1; i <= 100; i *= 10) {
                if(grid[rootNx][rootNy] / i % 10 >= grid[rootEx][rootEy] / i % 10) {
                    newValue += (grid[rootNx][rootNy] / i % 10 * i);
                } else {
                    newValue += (grid[rootEx][rootEy] / i % 10 * i);
                }
            }
            grid[rootNx][rootNy] = newValue;
            grid[rootEx][rootEy] = newValue;
            if(newValue % 10 == 2 && newValue / 10 % 10 == 2) {
                percolates = true;
            }
            disjointGrid.union(rootN, rootE);
        }
    }
}
