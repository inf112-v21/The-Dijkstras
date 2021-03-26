package inf112.skeleton.app;
/**
 * Please save my file
 */


public class GameInit {

    private final MapBuilder mapBuilder;

    public GameInit (String map_filename, int playerLimit) {
        mapBuilder = new MapBuilder(map_filename);


    }
    public MapBuilder getMapBuilder() {
        return this.mapBuilder;
    }
}
