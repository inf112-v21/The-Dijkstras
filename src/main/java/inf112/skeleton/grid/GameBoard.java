package inf112.skeleton.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import inf112.skeleton.grid.Grid;

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

    public List<Grid> getgrids() {
        return grids;
    }

    public Grid getGridLayer(int layer) {
        return grids.get(layer);
    }

    public void setCell(Location loc, T elem, int layer){
        if (layer < 0 || layer >= layers) {throw new IllegalStateException();}
        Grid tempgrid = getGridLayer(layer);
        tempgrid.set(loc, elem);
    }

    public T getCell(Location loc, int layer){
        if (layer < 0 || layer >= layers) { throw new IllegalStateException();}
        Grid tempgrid = getGridLayer(layer);
        return (T) tempgrid.get(loc);
    }

}
