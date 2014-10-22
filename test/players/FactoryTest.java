package players;

import TTT.GameState;
import TTT.View;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by Alex Codreanu on 10/17/14.
 */
public class FactoryTest {
    @Test
    public void returnsHumanPlayerWhenRequired() {
        View view = new View(new Scanner("8"), System.out);
        Factory factory = new Factory(view);
        Player human = factory.newPlayer("human", "X");
        assertTrue(human instanceof Human);
    }

    @Test
    public void returnsAIPlayerWhenRequired() {
        View view = new View(new Scanner("8"), System.out);
        Factory factory = new Factory(view);
        Player human = factory.newPlayer("ai", "O");
        assertTrue(human instanceof AI);
    }
}
