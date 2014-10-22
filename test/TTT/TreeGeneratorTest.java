package TTT;

import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by Alex Codreanu on 10/20/14.
 */
public class TreeGeneratorTest {
    @Test
    public void treeGeneratorGeneratesOneStateWhenOnlyOneMoveIsLeft() {
        String[] board = new String[] { "O", "X", "O",
                                        "X", "O", "X",
                                        "X", "O", null};
        GameState state = new GameState(board, "X");
        TreeGenerator generator = new TreeGenerator("X");
        generator.generate(state);
        assertEquals(state.nextPossibleStates.size(), 1);
    }

    @Test
    public void generatesStatesForEachPossibleMove() {
        String[] board = new String[] { null, "X", null,
                                        null, null, "X",
                                        null, "O", null};
        GameState state = new GameState(board, "X");
        TreeGenerator generator = new TreeGenerator("X");
        generator.generate(state);
        assertEquals(state.nextPossibleStates.size(), 6);
    }

    @Test
    public void comparingIntegers() {
        assertTrue(new Integer(-3) < new Integer(0));
    }
}
