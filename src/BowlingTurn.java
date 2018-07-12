package bowling;

import java.util.ArrayList;

public class BowlingTurn {
    protected static final int MAX_ROLLS = 2;
    protected static final int MAX_NUMBER_OF_PINS = 10;

    ArrayList<Integer> scores = new ArrayList<Integer>();

    public void addScore(int inputScore) {

        if (inputScore > MAX_NUMBER_OF_PINS || inputScore < 0) {
            throw new IllegalArgumentException("Pins knocked over every turn can't be over " + MAX_NUMBER_OF_PINS + " or under 0");
        }

        // scores.size() != 2 -> because in the 3rd throw we will insert any valid value
        if (!scores.isEmpty() && scores.size() != 2 && scores.get(scores.size() - 1).intValue() != MAX_NUMBER_OF_PINS) {
            if (inputScore > MAX_NUMBER_OF_PINS - scores.get(scores.size() - 1)) {
                throw new IllegalArgumentException("For this throw the pins knocked over can't be over " + (MAX_NUMBER_OF_PINS - scores.get(scores.size() - 1).intValue()));
            }
        }

        scores.add(inputScore);
        return;
    }

    public boolean isOver() {

        if (!scores.isEmpty()) {
            if (isStrike()){
                System.out.println("THE TURN IS OVER");
                return true;
            } 

            if (scores.size() == MAX_ROLLS)  {
                System.out.println("THE TURN IS OVER");
                return true;
            }
        }
        return false;
    }	

    public int totalScore(ArrayList<Integer> nextTwoRollsScores) {
        return turnScoresSum() + getBonusForTurn(nextTwoRollsScores);
    }

    // --- protected

    protected int firstThrowScore() {
        return scores.get(0).intValue();
    }

    protected int secondThrowScore() {
        return scores.get(1).intValue();
    }

    protected boolean isStrike() {
        return firstThrowScore() == MAX_NUMBER_OF_PINS;
    }

    protected boolean isSpare() {
        return !isStrike() && (firstThrowScore() + secondThrowScore() == MAX_NUMBER_OF_PINS);
    }

    protected int turnScoresSum() {
        if (!isStrike()) {
            return firstThrowScore() + secondThrowScore();
        }
        else {
            return firstThrowScore();
        }
    }

    protected int getBonusForTurn(ArrayList<Integer> nextTwoRollsScores) {
        int result = 0;
        if (isStrike()) {
            result = nextTwoRollsScores.get(0).intValue() + nextTwoRollsScores.get(1).intValue();
        }
        else if (isSpare()) {
            result = nextTwoRollsScores.get(0).intValue();
        }
        return result;
    }

}
