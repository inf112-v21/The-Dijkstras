package inf112.skeleton;
/**
 * Please save my file
 */

import com.badlogic.gdx.Game;
import inf112.skeleton.screens.TitleScreen;

public class RoboRally extends Game {

    @Override
    public void create() {
        this.setScreen(new TitleScreen(this));
    }
}
