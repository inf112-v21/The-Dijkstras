package inf112.skeleton.grid;

import inf112.skeleton.Game.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * An extension of the Grid class
 * Gives increased functionality, mainly in the form of "layered" grids, such that it can represent a game board
 * The class holds a list of Grids
 *TODO write documents to all methods
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
            Grid<TileObject> tempGrid = new Grid<>(rows, cols, empty, i);
            grids.add(tempGrid);

        }
        this.layers = layers;
        robotsOnBoard = new HashMap<>();
    }

// grid og layer metoder
    public List<Grid<TileObject>> getGrids() {
        return grids;
    }

    public int getLayers() {return layers;}

    public Grid<TileObject> getGridLayer(int layer) {
        return grids.get(layer);
    }

    private Grid<TileObject> getReferenceLayer() { return grids.get(0); }


    // Location methods

    public Location getRobotLocation(IRobot robot){
        return robotsOnBoard.get(robot);
    }

    public void setRobotLocation(Location loc, IRobot robot){
        getGridLayer(loc.getLayer()).set(loc, robot);
        robotsOnBoard.put(robot, loc);
    }

    //TODO in locatonOf and contains methods the check should be in robotsOnBoard (with new name may be? or another hashMap with tileObjects as keys)
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

    public boolean contains(TileObject obj) {

        for (int i = 0; i < getLayers(); i++) {
            if (getGridLayer(i).contains(obj)) {
                return true;
            }
        }
        return false;
    }


    public void set(Location loc, TileObject tile){
        getGridLayer(loc.getLayer()).set(loc, tile);
    }


    public TileObject get(Location loc){
        return getGridLayer(loc.getLayer()).get(loc);
    }

    private void clearLocation(Location loc){
        set(loc, new emptyTile());
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





    // Moving methods
    public void moveRobot(Directions dir, IRobot robot){
        Location currLoc = robotsOnBoard.get(robot);
        Location endLoc = currLoc.move(dir);
        if (!validCoordinate(endLoc)){
            robot.addDamage(1);
            debugPrint("Robo: "+dir+" Out of bounds. "+endLoc.toString()+ "| Added 1 dmg");
           //TODO set robot on his spawn point
        }

        else if (robotCanGo(robot,currLoc,dir)){
            setRobotLocation(endLoc,robot);
            clearLocation(currLoc);
            debugPrint("Moved Robot from "+currLoc.toString()+" to "+endLoc.toString());
        }
    }

    public boolean robotCanGo(IRobot robotOnMove, Location currLoc, Directions dir){
        Location endLoc = currLoc.move(dir);
        if (get(endLoc) instanceof Robot){
            IRobot placidRobot = (IRobot) get(endLoc);
            if (robotCanGo(placidRobot, endLoc, dir)){
                moveRobot(dir, placidRobot);
                return true;
            } else{
                return false;}
        }
        return true;
    }

    public boolean validCoordinate(Location loc) {
        return getReferenceLayer().validCoordinate(loc.getCol(),loc.getRow());
    }




    //copy method

    public GameBoard copy() {
        Grid<TileObject> tempGrid = getReferenceLayer();
        GameBoard gameBoardCopy = new GameBoard(tempGrid.numRows(), tempGrid.numCols(), getLayers());
        for (int i = 0; i < getLayers(); i++) {
            gameBoardCopy.grids.set(i, (Grid<TileObject>) getGridLayer(i).copy());
        }
        return gameBoardCopy;
    }



//Print method

    private void debugPrint(String debugString){
        if (debugMode){
        System.out.println(debugString);
        }
    }
}
