package players;
import TTT.*;

/**
 * Created by Alex Codreanu on 10/15/14.
 */
public class Human extends Player {
    private TTT.View view;
    public Human(TTT.View view, String symbol){
        this.view = view;
        setSymbol(symbol);
    }

    public int nextMove(GameState state) {
        try {
            return validatePlayerInput(state);
        } catch (NumberFormatException e) {
            return tryAgain(state);
        }
    }

    private int validatePlayerInput( GameState state) {
        int choice = view.getInput();
        if (state.isAvailableChoice(choice)) {
            return choice;
        } else {
            return tryAgain(state);
        }
    }

    private int tryAgain (GameState state) {
        view.displayInvalidInput();
        return nextMove(state);
    }
}
