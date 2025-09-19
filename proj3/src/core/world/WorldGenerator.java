package core.world;

import tileengine.TETile;

import java.util.Random;

/**
 * Generates the World
 */
public class WorldGenerator {
    /**
     * PRNG
     */
    private final Random r;
    private final long seed;

    public WorldGenerator(long seed) {
        this.seed = seed;
        r = new Random(seed);
    }

    /**
     * High level method for creating the world
     * @return {@link World}
     */
    public World createWorld() {
        World world = new World(seed);
        createRoomPoints(world);

        return world;
    }


    private void createRoomPoints(World world) {

    }
}
