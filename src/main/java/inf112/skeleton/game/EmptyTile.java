package inf112.skeleton.game;
/**
 * Please save my file
 */


public class EmptyTile implements ITileObject {
    @Override
    public boolean equals(Object obj){
        if (obj instanceof EmptyTile){
            return true;
        }
        return false;
    }
    @Override
    public String toString(){
    return "empty";
}
}
