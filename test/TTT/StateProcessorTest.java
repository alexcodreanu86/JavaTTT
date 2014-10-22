package TTT;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Alex Codreanu on 10/21/14.
 */
public class StateProcessorTest {
    @Test
    public void scoreReturns0WhenStateIsADrawState() {
        StateProcessor processor = new StateProcessor("X");
        String[] board = new String[] { "X", "O", "X",
                                        "O", "X", "O",
                                        "O", "X", "O" };
        GameState state = new GameState(board, "O");
        assertEquals(0, processor.score(0, state));
    }

    @Test
    public void scoreReturns10WhenIsLoosingStateForAi() {
        StateProcessor processor = new StateProcessor("X");
        String[] board = new String[] { "O", null, "X",
                                        "O", "X", "O",
                                        "O", "X", null };
        GameState state = new GameState(board, "O");
        assertEquals(10, processor.score(0, state));
    }

    @Test
    public void scoreReturnsNegative10WhenIsWinningStateForAi() {
        StateProcessor processor = new StateProcessor("X");
        String[] board = new String[] { null, "X", null,
                                        "O" , "X", "O",
                                        "O" , "X", null };
        GameState state = new GameState(board, "O");
        assertEquals(-10, processor.score(0, state));
    }

    @Test
    public void scoreReturnsNegative8IfStateIsAWinningStateNextTurn() {
        StateProcessor processor = new StateProcessor("X");
        String[] board = new String[] { "X" , null , "O",
                                        null, "O" , null,
                                        "X" , null, "X" };
        GameState state = new GameState(board, "O");
        TreeGenerator tGenerator = new TreeGenerator();
        tGenerator.generate(state);
        assertEquals(-8, processor.score(0, state));
    }

    @Test
    public void scoreReturns9WhenStateIsLoosingStateNextTurn() {
        StateProcessor processor = new StateProcessor("O"); //Ai has O symbol
        String[] board = new String[] { "X" , null , "O",
                                        null, "O" , null,
                                        "X" , null, "X" };
        GameState state = new GameState(board, "X");
        TreeGenerator tGenerator = new TreeGenerator();
        tGenerator.generate(state);
        assertEquals(9, processor.score(0, state));
    }
}
