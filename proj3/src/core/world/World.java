package core.world;

import tileengine.TETile;

/**
 * Represents the World created by the seed provided
 */
public class World {
    /**
     * 2d array that contains the state of each tile in the world.
     * <p>(0, 0) is bottom left.</p>
     */
    private TETile[][] world;
    private final int width;
    private final int height;

    /**
     * Initializes a world with the seed provided
     * @param seed String that represents the seed
     */
    public World(long seed, int width, int height) {
        //WorldGenerator generator = new WorldGenerator(seed);
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the tile at coordinates (x, y)
     * (0, 0) is top left
     * @param x horizontal coordinate
     * @param y vertical coordinate
     */
    private TETile getTile(int x, int y) {
        return world[x][y];
    }

    /**
     * Sets the tile at coordinates (x, y) to given {@link TETile}
     * (0, 0) is top left
     * @param x horizontal coordinate
     * @param y vertical coordinate
     */
    private void setTile(int x, int y, TETile tile) {
        world[x][y] = tile;
    }

    private int getWidth() {
        return width;
    }

    private int getHeight() {
        return height;
    }

}
