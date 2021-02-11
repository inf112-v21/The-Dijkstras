package inf112.skeleton.grid;

import java.util.Iterator;

/**
 * Source is inf101 spring 20 semester oblig 2 solution
 *
 */

public class LocationIterator implements Iterator<Location>, Iterable<Location>{
    int numRows;
    int numCols;
    int currentRow;
    int currentCol;

    /**
     * Constructs a GridLocationIterator
     * @param grid - the inf112.skeleton.grid which we want to iterate through
     */
    public LocationIterator(Grid<?> grid) {
        this(grid.numRows(),grid.numCols());
    }

    /**
     * Constructs a GridLocationIterator
     * @param numRows - number of rows in the inf112.skeleton.grid
     * @param numCols - number of columns in the inf112.skeleton.grid
     */
    public LocationIterator(int numRows,int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
    }

    @Override
    public boolean hasNext() {
        return currentRow<numRows && currentCol<numCols;
    }

    @Override
    public Location next() {
        Location elem = new Location(currentRow, currentCol);
        currentCol++;
        if(currentCol>=numCols) {
            currentCol=0;
            currentRow++;
        }
        return elem;
    }

    @Override
    public Iterator<Location> iterator() {
        return new LocationIterator(numRows,numCols);
    }
}