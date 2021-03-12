package inf112.skeleton.grid;

/**
 * Source is inf101 spring 20 semester oblig 2 solution
 *
 */

public class Location {

    private int col;
    private int row;
    private int layer;

    public Location (int x, int y, int layer){
        this.col = x;
        this.row = y;
        this.layer = layer;
    }

    public Location (int x, int y) {
        this.col = x;
        this.row = y;
        this.layer = -1;
    }

    /**
     *
     * @return row which is x coordinator
     */
    public int getRow(){
        return row;
    }

    /**
     *
     * @return col column number which is y coordinator
     */
    public int getCol(){
        return col;
    }

    public int getLayer() { return layer; }

    public boolean hasLayer() { return !(getLayer() == -1);}

    public Location move(Directions dir){
        return new Location(col +dir.getDx(), row +dir.getDy(), layer);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Location)) {
            return false;
        }
        Location loc = (Location) obj;
        return this.getRow()==loc.getRow() && this.getCol()==loc.getCol() && this.getLayer()==loc.getLayer();
    }

}