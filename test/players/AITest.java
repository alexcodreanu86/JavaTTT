package players;

import TTT.GameState;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Alex Codreanu on 10/17/14.
 */
public class AITest {

    @Test
    public void getSymbolTest() {
        AI player = new AI("O");
        assertEquals("O", player.getSymbol());
    }

    @Test
    public void blocksOpponentFromWinning() {
        AI player = new AI("O");
        String[] board = new String[]{"X", "O", "O",
                                      "O", "X", null,
                                      "X", null, null};
        GameState state = newGameState(board, "O");
        assertEquals(8, player.nextMove(state));
    }

    @Test
    public void returnsTheWinningMoveWhenAvailable() {
        AI player = new AI("O");
        String[] board = new String[]{"O", "X" , null,
                                      "X", "O" , null,
                                      "X", null, null};
        GameState state = newGameState(board, "O");
        assertEquals(8, player.nextMove(state));
    }

    @Test
    public void nextMoveReturnsOnlyMovesThatAreAvailable() {
        AI player = new AI("O");
        String[] board = new String[]{"X", "O", "O",
                                      "O", "X", "X",
                                      "X", "X", null};
        GameState state = newGameState(board, "O");
        assertEquals(8, player.nextMove(state));
    }


    @Test
    public void nextMovePreventsTraps() {
        AI player = new AI("O");
        String[] board = new String[]{"X", null, null,
                                     null, "O" , null,
                                     null, "X" , null};
        GameState state = newGameState(board, "O");
        assertEquals(3, player.nextMove(state));
    }

    private GameState newGameState(String[] board, String symbol) {
        return new GameState(board, symbol);
    }
}
