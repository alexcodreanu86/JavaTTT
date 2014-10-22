package TTT;

import java.util.*;

/**
 * Created by Alex Codreanu on 10/13/14.
 */
public class GameState {
    private static final int[][] WINNING_COMBINATIONS = new int[][]{
            new int[] {0, 4, 8},
            new int[] {2, 4, 6},
            new int[] {0, 3, 6},
            new int[] {1, 4, 7},
            new int[] {2, 5, 8},
            new int[] {0, 1, 2},
            new int[] {3, 4, 5},
            new int[] {6, 7, 8}
    };
    private String winner, aiSymbol;
    public String[] board;
    public String playerSymbol;
    public List<GameState> nextPossibleStates =  new ArrayList<GameState>();

    public GameState(String[] board, String playerSymbol) {
        this.board        = board;
        this.playerSymbol = playerSymbol;
    }

    public GameState(String[] board, String playerSymbol, String aiSymbol) {
        this.board          = board;
        this.playerSymbol   = playerSymbol;
        this.aiSymbol       = aiSymbol;
    }

    public List<Integer> possibleMoves() {
        List<Integer> moves = new ArrayList<Integer>();
        for (int i = 0; i < this.board.length; i++) {
            if (isAvailableChoice(i)) {
                moves.add(i);
            }
        }
        return moves;
    }

    public String nextPlayerSymbol() {
        return (playerSymbol == "X") ? "O" : "X";
    }
    public void setChoice(int position) {
        this.board[position] = playerSymbol;
    }

    public boolean isAvailableChoice(int possition) {
        return board[possition] == null;
    }

    public boolean isGameOver() { return hasWinner() || isBoardFull(); }

    public boolean isBoardFull() { return possibleMoves().size() == 0; }

    private boolean hasWinner() {
        boolean hasWinner = false;
        for (int[] combo : WINNING_COMBINATIONS) {
            if (isWinningCombo(combo)) {
                winner = board[combo[0]];
                hasWinner = true;
            }
        }
        return hasWinner;
    }

    public String getWinner() {
        return winner;
    }

    private boolean isWinningCombo(int[] combo) {
        return board[combo[0]] != null && board[combo[0]] == board[combo[1]] && board[combo[0]] == board[combo[2]];
    }

    public int score(int depth) {
        if (hasWinner()){
            if (getWinner() == aiSymbol){
                return depth - 10;
            } else {
                return 10 - depth;
            }
        } else if(isBoardFull()){
            return 0;
        } else {
            return nextRoundScore(depth + 1, this.nextPlayerSymbol());
        }
    }

    public int nextRoundScore(int depth, String player) {
        List<Integer> scores = nextRoundSortedScores(depth);
        if (player != aiSymbol) {
            return scores.get(0);
        } else {
            return scores.get(scores.size() - 1);
        }
    }

    public List<Integer> nextRoundSortedScores(int depth) {
        List<Integer> scores = new ArrayList<Integer>();
        for (GameState state : nextPossibleStates) {
            scores.add(state.score(depth));
        }
        Collections.sort(scores);
        return scores;
    }
}
