package players;

import TTT.View;

/**
 * Created by Alex Codreanu on 10/17/14.
 */
public class Factory {
    private View view;
    public Factory(View view) {
        this.view = view;
    }

    public Player newPlayer(String playerType, String symbol) {
        if (playerType == "human") {
            return new Human(view, symbol);
        } else {
            return new AI(symbol);
        }
    }
}
