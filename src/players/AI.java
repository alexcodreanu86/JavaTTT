package players;
import TTT.GameState;
import TTT.StateProcessor;
import TTT.TreeGenerator;

/**
 * Created by Alex Codreanu on 10/15/14.
 */
public class AI extends Player {
    private StateProcessor stateProcessor;
    public AI(String symbol) {
        setSymbol(symbol);
        this.stateProcessor = new StateProcessor(symbol);
    }

    public int nextMove(GameState state) {
        if (state.possibleMoves().size() == 9) {
            return sampleFirstMove();
        } else {
            return calculateBestMove(state);
        }
    }

    private int sampleFirstMove() {
        return (int) (Math.random() * 9);
    }

    private int calculateBestMove(GameState state) {
        generateStatesTree(state);
        GameState nextState = stateWithSmallestScore(state);
        return stateMove(state, nextState);
    }

    private int stateMove(GameState currentState, GameState nextState) {
        int diff = 4;
        for (int i = 0; i < currentState.board.length; i++) {
            if (currentState.board[i] != nextState.board[i]) {
                diff = i;
            }
        }
        return diff;
    }

    private GameState stateWithSmallestScore(GameState state) {
        GameState nextState = state.nextPossibleStates.get(0);
        for (GameState possibleState: state.nextPossibleStates) {
            if (stateProcessor.score(0,possibleState) < stateProcessor.score(0,nextState)){
                nextState = possibleState;
            }
        }
        return nextState;
    }

    private void generateStatesTree(GameState state) {
        new TreeGenerator().generate(state);
    }
}
