package TTT;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import players.Factory;
import players.Player;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import static org.junit.Assert.*;


/**
 * Created by Alex Codreanu on 10/18/14.
 */
public class GameRunnerTest {
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
    public void gameEndsWhenBoardIsFull() {
        Scanner scanner = new Scanner("0");
        String[] board = new String[] {"X", "O", "X",
                                       "O", "X", "X",
                                       "O", "X", "O"};
        newGameRunner(scanner, board).start();
        assertTrue(outputStream.toString().contains("Game has ended!!!"));
        assertFalse(outputStream.toString().contains("The Winner is"));
    }

    @Test
    public void gameEndsWhenAPlayerWins() {
        Scanner scanner = new Scanner("6");
        String[] board = new String[] { "O", "O", "X",
                                       null, "X", "O",
                                       null, "X", null };
        newGameRunner(scanner, board).start();
        assertTrue(outputStream.toString().contains("Game has ended!!!"));
        assertTrue(outputStream.toString().contains("The Winner is X"));
    }

    @Test
    public void gameAsksForPlayersMoveWhenGameIsNotOver() {
        Scanner scanner = new Scanner("2 3 6");
        String[] board = new String[] { "O", "O", null,
                                       null, "X", "O",
                                       null, "X", null };
        newGameRunner(scanner, board).start();
        assertTrue(outputStream.toString().contains("X's turn:"));
        assertTrue(outputStream.toString().contains("O's turn:"));
    }

    private GameRunner newGameRunner(Scanner scanner, String[] board) {
        View view = new View(scanner, System.out);
        Player player1 = newPlayer(view, "X");
        Player player2 = newPlayer(view, "O");
        return new GameRunner(player1, player2, view, board);
    }

    private Player newPlayer(View view, String symbol) {
        return new Factory(view).newPlayer("human", symbol);
    }
}
