package inf112.skeleton.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * An extension of the Grid class
 * Gives increased functionality, mainly in the form of "layered" grids, such that it can represent a game board
 * The class holds a list of Grids
 *
 * @param <T>
 */

public class GameBoard<T> extends Grid<T>{
    private List<Grid> grids;
    private int layers;

    public GameBoard(int rows, int cols, T initElement, int layers) {
        grids = new ArrayList<>(layers);
        for (int i = 0; i < layers; i++) {
            Grid tempgrid = new Grid(rows, cols, initElement, i);
            grids.add(tempgrid);
        }
        this.layers = layers;
    }

    public GameBoard() {
    }

    public List<Grid> getGrids() {
        return grids;
    }

    public int getLayers() {return layers;}

    public Grid getGridLayer(int layer) {
        return grids.get(layer);
    }

    private Grid getReferenceLayer() { return grids.get(0); }

    public void set(Location loc, T elem){
        getGridLayer(loc.getLayer()).set(loc, elem);
    }

    public T get(Location loc){
        return (T) getGridLayer(loc.getLayer()).get(loc);
    }

    public GameBoard copy() {
        Grid tempgrid = getReferenceLayer();
        GameBoard gameBoardCopy = new GameBoard(tempgrid.numRows(), tempgrid.numCols(), null, getLayers());
        for (int i = 0; i < getLayers(); i++) {
            gameBoardCopy.grids.set(i, getGridLayer(i).copy());
        }
        return gameBoardCopy;
    }

    public boolean validCoordinate(int x, int y) {
        return getReferenceLayer().validCoordinate(x,y);
    }

    public boolean contains(Object obj) {
        boolean contains = false;
        for (int i = 0; i < getLayers(); i++) {
            if (getGridLayer(i).contains(obj)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    public Location locationOf(Object target) {
        Location loc;
        for (int i = 0; i < getLayers(); i++) {
            loc = getGridLayer(i).locationOf(target);
            if (loc != null) {
                return loc;
            }
        }
        return null;
    }

    public boolean sameXYLocation(Object obj1, Object obj2) {
        Location loc1 = locationOf(obj1);
        Location loc2 = locationOf(obj2);
        return (loc1.getRow() == loc2.getRow() && loc1.getCol() == loc2.getCol());
    }
}
