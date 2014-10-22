package TTT;

import players.Player;

/**
 * Created by Alex Codreanu on 10/18/14.
 */
public class GameRunner {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private View view;
    private String[] board;
    public GameRunner(Player player1, Player player2, View view, String[] board) {
        this.player1       = player1;
        this.player2       = player2;
        this.currentPlayer = player1;
        this.view          = view;
        this.board         = board;
    }

    public void start() {
        GameState state = new GameState(board, currentPlayer.getSymbol());
        while (!state.isGameOver()) {
            makePlayerMove(state);
            switchPlayers();
            state = new GameState(state.board, currentPlayer.getSymbol());
        }
        endGame(state);
    }

    private void endGame(GameState state) {
        view.displayGameHasEnded();
        view.displayBoard(state.board);
        if (state.getWinner() != null) {
            view.displayWinner(state.getWinner());
        }
    }

    private void makePlayerMove(GameState state) {
        view.displayPlayerTurn(currentPlayer.getSymbol());
        view.displayBoard(state.board);
        state.setChoice(currentPlayer.nextMove(state));
    }

    private void switchPlayers() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

}
