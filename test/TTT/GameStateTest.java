package TTT;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Alex Codreanu on 10/13/14.
 */
public class GameStateTest {
    @Test
    public void allMovesArePossibleMovesOnInitiation() {
        GameState state = new GameState(new String[9], "X");
        assertTrue(state.possibleMoves().size() == 9);
    }

    @Test
    public void setChoiceStoresTheSymbolThatItIsInitializedWith(){
        GameState state = new GameState(new String[9], "X");
        assertEquals(state.playerSymbol, "X");
    }

    @Test
    public void possibleStatesIsAnEmptyListOnInitialization() {
        GameState state = new GameState(new String[9], "X");
        assertTrue(state.nextPossibleStates.size() == 0);
    }

    @Test
    public void nextPlayerSymbolReturnsOWhenCurrentPlayerIsX() {
        GameState state = new GameState(new String[0], "X");
        assertEquals(state.nextPlayerSymbol(), "O");
    }

    @Test
    public void nextPlayerSymbolReturnsXWhenCurrentPlayerIsO() {
        GameState state = new GameState(new String[0], "O");
        assertEquals(state.nextPlayerSymbol(), "X");
    }

    @Test
    public void returnsAllPossibleMoves() {
        String[] board = new String[]{"X", null, "X", null, "O", "O", null, null, null};
        GameState state = new GameState(board, "X");
        assertTrue(Arrays.equals(state.possibleMoves().toArray(), new Integer[]{1,3,6,7,8}));
    }

    @Test
    public void setChoicePlotsTheGivenSymbolAtTheGivenPosition() {
        String[] board = new String[9];
        GameState state = new GameState(board, "X");
        state.setChoice(0);
        assertEquals(board[0], "X");
    }

    @Test
    public void isAvailableChoiceReturnsTrueWhenChoiceIsAvailable() {
        String[] board = new String[9];
        GameState state = new GameState(board, "X");
        assertTrue(state.isAvailableChoice(0));
    }

    @Test
    public void isAvailableChoiceReturnsFalseWhenChoiceIsNotAvailable() {
        String[] board = new String[9];
        GameState state = new GameState(board, "X");
        state.setChoice(0);
        assertFalse(state.isAvailableChoice(0));
    }

    @Test
    public void isGameOverReturnsFalseWhenBoardIsEmpty() {
        String[] board = new String[9];
        GameState state = new GameState(board, "X");
        assertFalse(state.isGameOver());
    }

    @Test
    public void isGameOverReturnsFalseWhenItHasNoWinnerAndBoardIsNotFull() {
        String[] board = new String[]{"X", "O", "X",
                "X", null, "O",
                null, "O", "X"};
        GameState state = new GameState(board, "X");
        assertFalse(state.isGameOver());
    }

    @Test
    public void isGameOverReturnsTrueWhenThereAreNoMoreMovesLeft() {
        String[] board = new String[]{"X", "O", "X",
                "X", "O", "O",
                "O", "X", "X"};
        GameState state = new GameState(board, "X");
        assertTrue(state.isGameOver());
    }

    @Test
    public void hasWinnerStoresWinnerXWhenXWins() {
        GameState state = new GameState(WINNING_BOARDS[0], "X");
        state.isGameOver();
        assertEquals("X", state.getWinner());
    }

    @Test
    public void hasWinnerStoresWinnerOWhenOWins() {
        GameState state = new GameState(WINNING_BOARDS[1], "X");
        state.isGameOver();
        assertEquals("O", state.getWinner());
    }

    @Test
    public void isGameOverReturnsTrueWhenAPlayerWins() {
        for (String[] board : WINNING_BOARDS) {
            GameState state = new GameState(board, "X");
            assertTrue(state.isGameOver());
        }
    }

    private String[][] WINNING_BOARDS = new String[][] {
            new String[] { "X", "O", "X",
                           "X", "X", "O",
                          null, "O", "X" },

            new String[] { "X", "X", "O",
                          null, "O", "O",
                           "O", "X", "X" },

            new String[] { "X", "O", "O",
                           "X", "X", "O",
                           "X", "O", null },

            new String[] { "X", "O", "X",
                          null, "O", "O",
                           "X", "O", null },

            new String[] { "X", "X", "O",
                           "X", "X", "O",
                          null, "O", "O" },

            new String[] { "X", "X", "X",
                           "O", "X", null,
                          null, "O", "X" },

            new String[] { "X", "O", "X",
                           "O", "O", "O",
                           "X", "X", null },

            new String[] { "X", "O", "X",
                           "O", "X", null,
                           "X", "X", "X" },
    };
}
