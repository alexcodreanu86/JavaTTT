package TTT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Alex Codreanu on 10/21/14.
 */
public class StateProcessor {
    private String aiSymbol;
    public StateProcessor(String aiSymbol){
        this.aiSymbol = aiSymbol;
    }

    public int score(int depth, GameState state) {
        if (state.hasWinner()){
            return winnerScore(depth, state);
        } else if(state.isBoardFull()){
            return 0;
        } else {
            return nextRoundScore(depth + 1, state.nextPlayerSymbol(), state);
        }
    }

    private int winnerScore(int depth, GameState state) {
        return isAiWinner(state) ? depth - 10 : 10 - depth;
    }

    private boolean isAiWinner(GameState state) {
        return state.getWinner() == aiSymbol;
    }

    private int nextRoundScore(int depth, String player, GameState state) {
        List<Integer> scores = nextRoundSortedScores(depth, state);
        if (player != aiSymbol) {
            return scores.get(0);
        } else {
            return scores.get(scores.size() - 1);
        }
    }

    private List<Integer> nextRoundSortedScores(int depth, GameState state) {
        List<Integer> scores = new ArrayList<Integer>();
        for (GameState possibleState : state.nextPossibleStates) {
            scores.add(score(depth, possibleState));
        }
        Collections.sort(scores);
        return scores;
    }
}
