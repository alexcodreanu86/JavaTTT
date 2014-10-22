package TTT;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import players.AI;
import players.Human;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.mockito.ArgumentCaptor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

/**
 * Created by Alex Codreanu on 10/14/14.
 */
public class ControllerTest {
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
    public void testGameTypeOption1() {
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        ArgumentCaptor<AI> aiArgument = ArgumentCaptor.forClass(AI.class);
        ArgumentCaptor<Human> humanArgument = ArgumentCaptor.forClass(Human.class);
        System.setIn(in);
        Controller controllerSpy = spy(new Controller(fullBoard));
        controllerSpy.startGame();
        verify(controllerSpy).newGameRunner(aiArgument.capture(), humanArgument.capture(), any(View.class), any(String[].class));
        assertEquals(aiArgument.getValue().getClass(), AI.class);
        assertEquals(humanArgument.getValue().getClass(), Human.class);

        System.setIn(System.in);
    }

    @Test
    public void testGameTypeOption2() {
        ByteArrayInputStream in = new ByteArrayInputStream("2".getBytes());
        ArgumentCaptor<AI> aiArgument = ArgumentCaptor.forClass(AI.class);
        ArgumentCaptor<Human> humanArgument = ArgumentCaptor.forClass(Human.class);
        System.setIn(in);
        Controller controllerSpy = spy(new Controller(fullBoard));
        controllerSpy.startGame();
        verify(controllerSpy).newGameRunner(humanArgument.capture(), aiArgument.capture(), any(View.class), any(String[].class));
        assertEquals(aiArgument.getValue().getClass(), AI.class);
        assertEquals(humanArgument.getValue().getClass(), Human.class);

        System.setIn(System.in);
    }

    @Test
    public void testGameTypeOption3() {
        ByteArrayInputStream in = new ByteArrayInputStream("3".getBytes());
        ArgumentCaptor<Human> human1Argument = ArgumentCaptor.forClass(Human.class);
        ArgumentCaptor<Human> human2Argument = ArgumentCaptor.forClass(Human.class);
        System.setIn(in);
        Controller controllerSpy = spy(new Controller(fullBoard));
        controllerSpy.startGame();
        verify(controllerSpy).newGameRunner(human1Argument.capture(), human2Argument.capture(), any(View.class), any(String[].class));
        assertEquals(human1Argument.getValue().getClass(), Human.class);
        assertEquals(human2Argument.getValue().getClass(), Human.class);

        System.setIn(System.in);
    }

    @Test
    public void setsUpTheGame() {
        ByteArrayInputStream in = new ByteArrayInputStream("2".getBytes());
        System.setIn(in);
        Controller controller = new Controller(new String[9]);
        try {
            controller.startGame();
        } catch (NoSuchElementException e) {
            assertTrue(outputStream.toString().contains("Game Type:"));
            assertTrue(outputStream.toString().contains("X's turn:"));
            assertFalse(outputStream.toString().contains("O's turn:"));
        }
    }

    @Test
    public void asksPlayerForInputAgainWhenPreviousInputIsInvalid() {
        ByteArrayInputStream in = new ByteArrayInputStream("4".getBytes());
        System.setIn(in);
        Controller controller = new Controller(new String[9]);
        try {
            controller.startGame();
        } catch (NoSuchElementException e) {
            assertTrue(outputStream.toString().contains("Game Type:"));
            assertTrue(outputStream.toString().contains("Invalid Input!!! Try again:"));
            assertFalse(outputStream.toString().contains("X's turn:"));
            assertFalse(outputStream.toString().contains("O's turn:"));
        }
    }

    public String[] fullBoard = new String[]{ "O", "X", "O",
                                              "X", "O", "X",
                                              "O", "X", "O" };

}

