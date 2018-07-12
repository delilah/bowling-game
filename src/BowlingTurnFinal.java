package bowling;

import java.util.ArrayList;

public class BowlingTurnFinal extends BowlingTurn {
    
    protected static final int MAX_ROLLS = 3;

    public boolean isOver() {

        if (!scores.isEmpty()) {

            if (scores.size() == 1) {
                return false; // there ALWAYS is an occurrence of the second roll!
            }

            if (scores.size() == MAX_ROLLS) {
                System.out.println("THE TURN IS OVER");
                return true;
            }

            // We know for sure that we are at the throw > 1, therefore we have at least two score elements
            int lastTwoRollsScores = scores.get(scores.size() - 1).intValue() + scores.get(scores.size() - 2).intValue();
            if (lastTwoRollsScores < MAX_NUMBER_OF_PINS) {
                System.out.println("THE TURN IS OVER");
                return true;
            }
        }
        return false;
    }

    // --- privates

    private int thirdThrowScore() {
        return scores.get(2).intValue();
    }

    // --- protected

    protected int getBonusForTurn(ArrayList<Integer> nextRollsScores) {

        // in this special turn the nextRollsScores is = {} so we will use the special bonus balls awarded
        int result = 0;
        if (isStrike()) {
            result = secondThrowScore() + thirdThrowScore();
        }
        else if (isSpare()) {
            result = thirdThrowScore();
        }
        return result;
    }

}
