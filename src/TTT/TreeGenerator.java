package TTT;

/**
 * Created by Alex Codreanu on 10/20/14.
 */
public class TreeGenerator {
    private String aiSymbol;
    public TreeGenerator(String aiSymbol) {
        this.aiSymbol = aiSymbol;
    }
    public void generate(GameState state) {
        for(int move: state.possibleMoves()) {
            GameState newState = generateNextState(state, move);
            state.nextPossibleStates.add(newState);
            generate(newState);
        }
    }

    public GameState generateNextState(GameState currentState, int move) {
        String[] temporaryBoard = currentState.board.clone();
        temporaryBoard[move] = currentState.playerSymbol;
        return new GameState(temporaryBoard, currentState.nextPlayerSymbol(), aiSymbol);
    }
}
