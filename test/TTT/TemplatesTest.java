package TTT;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Alex Codreanu on 10/14/14.
 */
public class TemplatesTest {
    @Test
    public void renderReturnsTheBoardDisplay() {
        String[] board = new String[]{"X", null, "X", null, "O", "O", null, null, null};
        String expectedResponse = "X| |X\n_____\n |O|O\n_____\n | | ";
        assertEquals(expectedResponse, Templates.renderBoard(board));
    }
}
