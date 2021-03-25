package inf112.skeleton.grid;

import inf112.skeleton.game.*;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class GridTest {
    //TODO implement  Grid test
    @Test
    public void numRowsTest() {
    }

    @Test
    public void numColsTest() {
    }

    @Test
    public void numLayersTest() {
    }

    @Test
    public void setTest() {
    }

    @Test
    public void getTest() {
    }

    @Test
    public void copyTest() {
    }

    @Test
    public void containsTest() {
    }

    @Test
    public void locationOfTest() {
    }

    @Test
    public void iteratorTest() {
    }

    @Test
    public void locationsTest() {
    }

    @Test
    public void equalsTest() {
        EmptyTile e = new EmptyTile();
        Grid<ITileObject> g1= new Grid<>(3,3,e,3);
        Grid<ITileObject> g2= new Grid<>(3,3,e,3);

        assertThat(g1.equals(g2),is(true));


        g2.set(new Location(0,0),new Robot());

        assertThat(g1.equals(g2),is(false));


    }

}
