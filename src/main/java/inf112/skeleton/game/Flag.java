package inf112.skeleton.game;
/**
 * Please save my file
 */


public class Flag implements ITileObject {
    private final int flagIndex;


    public Flag(int flagindex) { //Start with the first flag having flagIndex = 1
        this.flagIndex = flagindex;
    }

    public boolean playerHasVisited(Player player) {
        return (player.getNextFlagIndex() > flagIndex);
    }

    public int getIndex() {
        return this.flagIndex;
    }
    @Override
    public String toString(){
        return "flag index "+flagIndex;
    }

}
