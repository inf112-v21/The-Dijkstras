package inf112.skeleton.grid;

/**
 * Source is inf101 spring 20 semester oblig 2 solution
 *
 */

public class Location {

    private int row;
    private int col;
    private int layer;
    public Location (int x,int y,int layer){
        this.row = x;
        this.col = y;
        this.layer = layer;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public int getLayer() { return layer; }

    public Location move(Directions dir){
        return new Location(row +dir.getDy(), col +dir.getDx(), layer);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj ==null || !(obj instanceof Location)) {
            return false;
        }
        Location loc = (Location) obj;
        return this.getRow()==loc.getRow() && this.getCol()==loc.getCol() && this.getLayer()==loc.getLayer();
    }

}