package TTT;

/**
 * Created by Alex Codreanu on 10/14/14.
 */
public class Templates {
    private static final String rowSeparator    = "\n_____\n";
    private static final String emptySlot       = " ";
    private static final String columnSeparator = "|";

    public static String renderBoard(String[] board) {
        String boardDisplay = "";
        for (int i = 0; i < board.length; i++) {
            if (isEndOfLine(i)) {
                boardDisplay += rowSeparator;
            }

            if (board[i] != null) {
                boardDisplay += board[i];
            } else {
                boardDisplay += emptySlot;
            }

            if (needsDivider(i)) {
                boardDisplay += columnSeparator;
            }
        }
        return boardDisplay;
    }

    private static boolean isEndOfLine(int num) {
        return num != 0 && (num % 3) == 0;
    }

    private static boolean needsDivider(int num) {
        return (num % 3) != 2;
    }
}
