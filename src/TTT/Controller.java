package TTT;

import players.Factory;
import players.Player;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by Alex Codreanu on 10/14/14.
 */
public class Controller {
    private String[] board;
    private View view;
    private InputStream in;
    private PrintStream out;
    private Factory factory;
    private Player player1;
    private Player player2;

    public Controller(String[] board) {
        this.in    = System.in;
        this.out   = System.out;
        this.board = board;
    }

    public void startGame() {
        setDependencies();
        initializePlayers();
        newGameRunner(player1, player2, view, board).start();
    }

    private void setDependencies() {
        this.view          = new View(newScanner(in), out);
        this.factory       = new Factory(view);
    }

    public GameRunner newGameRunner(Player player1, Player player2, View view, String[] board) {
        return new GameRunner(player1, player2, view, board);
    }

    private Scanner newScanner(InputStream input) {
        return new Scanner(input);
    }

    private void initializePlayers() {
        int choice = getPlayerInput();
        if (choice > 0 && choice < 4) {
            setupPlayers(choice);
        } else {
            view.displayInvalidInput();
            initializePlayers();
        }
    }

    private int getPlayerInput() {
        view.displayPlayerStart();
        try {
            return view.getInput();
        } catch (NumberFormatException e) {
            view.displayInvalidInput();
            return getPlayerInput();
        }
    }

    private void setupPlayers(int choice) {
        switch(choice) {
            case 1:
                this.player1 = factory.newPlayer("ai", "X");
                this.player2 = factory.newPlayer("human", "O");
                break;
            case 2:
                this.player1 = factory.newPlayer("human", "X");
                this.player2 = factory.newPlayer("ai", "O");
                break;
            case 3:
                this.player1 = factory.newPlayer("human", "X");
                this.player2 = factory.newPlayer("human", "O");
        }
    }
}
