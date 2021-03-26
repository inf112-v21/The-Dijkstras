package inf112.skeleton.app;
import org.junit.Test;
import org.junit.*;
import java.util.Scanner; 


import static org.hamcrest.CoreMatchers.*;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Manual test for GameApp.
 *
 * TODO: More Tests
 * TODO: Run Screens Simultanous
 */
public class AppManualTest {
    /**
     * Does not work as intended
     *
    @Test
    public void screenPopUp(){
        String question = "Does Screen appear?";
        String answer = askQuestion(question);
        assertThat(answer, is("y"));

    }


    @Test
    public void playerIsMovable(){
        String question = "Is Player moveable with arrow keys";
        String answer = askQuestion(question);
        assertThat(answer, is("y"));
    }


    private static String askQuestion(String question){
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Do you see you screen y/n");
        String answer = myScanner.nextLine();
        if (answer.equals("y") || answer.equals("n")){
            return answer;}
        else{
            System.out.println("Please answer with 'y' or 'n'");
            return askQuestion(question);
        }
    }
    */
}