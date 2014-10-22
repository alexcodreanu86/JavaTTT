package TTT;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by Alex Codreanu on 10/13/14.
 */
public class    ViewTest {
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
    public void getInputIsGettingUserInput() {
        Scanner scanner = new Scanner("8");
        View view = new View(scanner, System.out);
        assertEquals(view.getInput(), 8);
        System.setIn(System.in);
    }

    @Test(expected = NumberFormatException.class)
    public void testForGetInputException() {
        Scanner scanner = new Scanner("a");
        View view = new View(scanner, System.out);
        view.getInput();
    }

    @Test
    public void displayBoardDisplaysTheBoard() {
        View view = new View(new Scanner("8"), System.out);
        String[] board = new String[]{"X", null, "X", null, "O", "O", null, null, null};
        String expectedResponse = "X| |X\n_____\n |O|O\n_____\n | | \n";
        view.displayBoard(board);
        assertEquals(expectedResponse, outputStream.toString());
    }

    @Test
    public void testDisplayInvalidInput() {
        View view = new View(new Scanner("8"), System.out);
        view.displayInvalidInput();
        assertEquals("Invalid Input!!! Try again:\n", outputStream.toString());
    }

    @Test
    public void testDisplayOver() {
        View view = new View(new Scanner("8"), System.out);
        view.displayGameHasEnded();
        assertEquals("Game has ended!!!\n", outputStream.toString());
    }

    @Test
    public void testDisplayWinner() {
        View view = new View(new Scanner("8"), System.out);
        view.displayWinner("Test");
        assertEquals("The Winner is Test\n", outputStream.toString());
    }

    @Test
    public void testPlayerTurn() {
        View view = new View(new Scanner("8"), System.out);
        view.displayPlayerTurn("Test");
        assertEquals("Test's turn:\n", outputStream.toString());
    }

    @Test
    public void testGetPlayerChoice() {
        View view = new View(new Scanner("1"), System.out);
        view.displayPlayerStart();
        assertEquals("Game Type:\n[1] AI moves first\n[2] AI moves second\n[3] Player vs Player\n", outputStream.toString());
    }
}
