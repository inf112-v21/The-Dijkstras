package inf112.skeleton.grid;

import inf112.skeleton.Game.*;

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
    private final boolean debugMode = true;

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
        if (!validCoordinate(loc2)){
            robot.addDamage(1);
            debugPrint("Robo: "+dir+" Out of bounds. "+loc2.toString());
        }
        else if (roboCanGo(robot,loc,dir)){
            setRobotLocation(loc2,robot);
            clearLocation(loc);
            debugPrint("Moved Robot from "+loc.toString()+" to "+loc2.toString());
        }
    }

    public boolean roboCanGo(IRobot robo, Location loc, Directions dir){
        Location endloc = loc.move(dir);
        if (get(endloc) instanceof Robot){
            IRobot robot = (IRobot) get(loc);
            if (roboCanGo(robot, endloc, dir)){
                moveRobot(dir, robot);
                return true;
            } else{
                return false;}
        }
        return true;
    }


    public void set(Location loc, TileObject elem){
        getGridLayer(loc.getLayer()).set(loc, elem);
    }

    public TileObject get(Location loc){
        return getGridLayer(loc.getLayer()).get(loc);
    }

    private void clearLocation(Location loc){
        set(loc, new emptyTile());
    }

    public GameBoard copy() {
        Grid<TileObject> tempgrid = getReferenceLayer();
        GameBoard gameBoardCopy = new GameBoard(tempgrid.numRows(), tempgrid.numCols(), getLayers());
        for (int i = 0; i < getLayers(); i++) {
            gameBoardCopy.grids.set(i, (Grid<TileObject>) getGridLayer(i).copy());
        }
        return gameBoardCopy;
    }

    public boolean validCoordinate(Location loc) {
        return getReferenceLayer().validCoordinate(loc.getCol(),loc.getRow());
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

    private void debugPrint(String debugString){
        if (debugMode){
        System.out.println(debugString);
        }
    }
}
