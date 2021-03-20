package inf112.skeleton.grid;

import java.util.Arrays;
import java.util.List;

/**
 * Source is inf101 spring 20 semester oblig 2 solution
 *
 */

public enum Directions {
    EAST(1, 0),
    NORTH(0, -1),
    WEST(-1, 0),
    SOUTH(0, 1),
    CENTER(0, 0);

    /**
     * The four cardinal directions: {@link #NORTH}, {@link #SOUTH}, {@link #EAST},
     * {@link #WEST}.
     */
    public static final List<Directions> FOUR_DIRECTIONS = Arrays.asList(EAST, NORTH, WEST, SOUTH);

    private final int dx;
    private final int dy;

    private Directions(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }


    /**
     * @return The change to your X-coordinate if you were to move one step in this
     *         direction
     */
    public int getDx() {
        return dx;
    }

    /**
     * @return The change to your Y-coordinate if you were to move one step in this
     *         direction
     */
    public int getDy() {
        return dy;
    }
}
