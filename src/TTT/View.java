package TTT;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by Alex Codreanu on 10/13/14.
 */
public class View {
    private Scanner inStream;
    private PrintStream outStream;
    public View(Scanner in, PrintStream out) {
        this.inStream = in;
        this.outStream = out;
    }

    public int getInput() throws NumberFormatException {
        outStream.println("Choose next move: ");
        return Integer.parseInt(inStream.next());
    }

    public void displayBoard(String[] board) {
        outStream.println(Templates.renderBoard(board));
    }

    public void displayInvalidInput() {
        outStream.println("Invalid Input!!! Try again:");
    }

    public void displayGameHasEnded() { outStream.println("Game has ended!!!"); }

    public void displayWinner(String winner) {
        outStream.println("The Winner is " + winner);
    }

    public void displayPlayerTurn(String player) {
        outStream.println(player + "'s turn:");
    }

    public void displayPlayerStart() {
        outStream.println("Game Type:\n" +
                          "[1] AI moves first\n" +
                          "[2] AI moves second\n" +
                          "[3] Player vs Player"); }
}
