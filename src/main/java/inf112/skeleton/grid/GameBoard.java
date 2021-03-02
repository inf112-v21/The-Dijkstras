package inf112.skeleton.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    public List<Grid> getgrids() {
        return grids;
    }

    public int getLayers() {return layers;}

    public Grid getGridLayer(int layer) {
        return grids.get(layer);
    }

    private Grid getReferenceLayer() { return grids.get(0); }

    public void set(Location loc, T elem){

        Grid tempgrid = getGridLayer(loc.getLayer());
        tempgrid.set(loc, elem);
    }

    public T get(Location loc){

        Grid tempgrid = getGridLayer(loc.getLayer());
        return (T) tempgrid.get(loc);
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
}
