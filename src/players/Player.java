package players;
import TTT.GameState;

/**
 * Created by Alex Codreanu on 10/15/14.
 */
public abstract class Player {
    private String symbol;
    public abstract int nextMove(GameState gameState);

    public String getSymbol() {
        return symbol;
    }

    protected void setSymbol(String value) {
        this.symbol = value;
    }
}
