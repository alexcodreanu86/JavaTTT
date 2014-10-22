package players;

import TTT.GameState;
import TTT.View;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by Alex Codreanu on 10/17/14.
 */
public class HumanTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void getSymbolTest() {
        View view = new View(new Scanner("8"), System.out);
        Player player = new Human(view, "X");
        assertEquals("X", player.getSymbol());
    }

    @Test
    public void nextMoveReturnsPlayerInputWhenItIsValid() {
        View view = new View(new Scanner("8"), System.out);
        GameState state = newGameState(new String[9], "X");
        Player player = new Human(view, "X");
        assertEquals(8, player.nextMove(state));
    }

    @Test
    public void nextMoveCatchesErrorWhenInputIsInvalid() {
        View view = new View(new Scanner("a 8"), System.out);
        GameState state = newGameState(new String[9], "X");
        Player player = new Human(view, "X");
        try{
            assertEquals(8, player.nextMove(state));
        } catch (NumberFormatException e) {
            fail("nextMove did not catch exception");
        }
    }
    @Test
    public void nextMoveReturnsMovesThatAreAvailable() {
        View view = new View(new Scanner("0 8"), System.out);
        GameState state = newGameState(new String[9], "X");
        state.setChoice(0);
        Player player = new Human(view, "X");
        assertEquals(8, player.nextMove(state));
    }

    private GameState newGameState(String[] board, String symbol) {
        return new GameState(board, symbol);
    }
}
