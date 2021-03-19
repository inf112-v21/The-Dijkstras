package inf112.skeleton.grid;

import inf112.skeleton.Game.IRobot;
import inf112.skeleton.Game.TileObject;
import inf112.skeleton.Game.emptyTile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * An extension of the Grid class
 * Gives increased functionality, mainly in the form of "layered" grids, such that it can represent a game board
 * The class holds a list of Grids
 *
 */

public class GameBoard{
    private List<Grid<TileObject>> grids;
    private int layers;
    private HashMap<IRobot,Location> robotsOnBoard;

    public GameBoard(int rows, int cols, int layers) {
        grids = new ArrayList<>(layers);
        for (int i = 0; i < layers; i++) {
            TileObject empty = new emptyTile();
            Grid<TileObject> tempgrid = new Grid<>(rows, cols, empty, i);
            grids.add(tempgrid);
        }
        this.layers = layers;
        robotsOnBoard = new HashMap<>();
    }

    public GameBoard() {
    }

    public List<Grid<TileObject>> getGrids() {
        return grids;
    }

    public int getLayers() {return layers;}

    public Grid<TileObject> getGridLayer(int layer) {
        return grids.get(layer);
    }

    private Grid<TileObject> getReferenceLayer() { return grids.get(0); }

    public Location getRobotLocation(IRobot robot){
        return robotsOnBoard.get(robot);
    }

    public void setRobotLocation(Location loc, IRobot robot){
        getGridLayer(loc.getLayer()).set(loc, robot);
        robotsOnBoard.put(robot, loc);
    }

    public void moveRobot(Directions dir, IRobot robot){
        Location loc = robotsOnBoard.get(robot);
        Location loc2 = loc.move(dir);
        setRobotLocation(loc2,robot);
        clearLocation(loc);
    }

    public boolean canGoTo(Location loc, Directions dir){
        return true;
    }

    public void set(Location loc, T elem){
        getGridLayer(loc.getLayer()).set(loc, elem);
    }

    public T get(Location loc){
        return (T) getGridLayer(loc.getLayer()).get(loc);
    }

    private void clearLocation(Location loc){
        set(loc, new emptyTile());
    }

    public GameBoard copy() {
        Grid<TileObject> tempgrid = getReferenceLayer();
        GameBoard gameBoardCopy = new GameBoard(tempgrid.numRows(), tempgrid.numCols(), getLayers());
        for (int i = 0; i < getLayers(); i++) {
            gameBoardCopy.grids.set(i, getGridLayer(i).copy());
        }
        return gameBoardCopy;
    }

    public boolean validCoordinate(int x, int y) {
        return getReferenceLayer().validCoordinate(x,y);
    }

    public boolean contains(TileObject obj) {
        boolean contains = false;
        for (int i = 0; i < getLayers(); i++) {
            if (getGridLayer(i).contains(obj)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    public Location locationOf(TileObject target) {
        Location loc;
        for (int i = 0; i < getLayers(); i++) {
            loc = getGridLayer(i).locationOf(target);
            if (loc != null) {
                return loc;
            }
        }
        return null;
    }

    public boolean sameXYLocation(TileObject obj1, TileObject obj2) {
        Location loc1 = locationOf(obj1);
        Location loc2 = locationOf(obj2);
        return (loc1.getRow() == loc2.getRow() && loc1.getCol() == loc2.getCol());
    }


    public List<TileObject> getXYObjects(Location loc) {
        List<TileObject> returnList = new ArrayList<>(layers);
        for (Grid<TileObject> grid : grids) {
            returnList.add(grid.get(loc));
        }
        return returnList;
    }
}
