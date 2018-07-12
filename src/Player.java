package bowling;

import java.util.ArrayList;
import java.util.Scanner;

import bowling.BowlingTurn;

public class Player implements Comparable<Player> {

    private ArrayList<BowlingTurn> turns = new ArrayList<BowlingTurn>(); // frame index
    private String _name;

    public Player() {
        getPlayerName();
    }

    public Player(String playerName) {
        setName(playerName);
    }

    public void playTurn(BowlingTurn turn) {

        turns.add(turn);
        int currentThrowNumber = 1; // used only to print the system out

        while(!turn.isOver()) {
            int score = 0;
            boolean invalidScoreGivenFromInput = true;
            while (invalidScoreGivenFromInput) {
                try {
                    System.out.println("Enter the number of pins knocked down by " + _name + " in the throw number " + currentThrowNumber);
                    score = getScoreFromUserInput();
                    turn.addScore(score);
                    invalidScoreGivenFromInput = false;
                } catch (Exception e) {
                    System.out.println("OOOPS: " + e.getMessage());
                }
            }

            currentThrowNumber++;
        }
    }

    public int totalScore() {

        int result = 0;
        for (int i = 0; i < turns.size(); i++) {

            BowlingTurn currentTurn = turns.get(i);

            ArrayList<Integer> twoRollsAfterTheCurrentTurn = new ArrayList<Integer>();
            if (!(currentTurn instanceof BowlingTurnFinal)) {
                // Retrieving the next two rolls if we are not calculating
                // the score for the last turn; else it has all the information
                // needed to calculate it itself
                twoRollsAfterTheCurrentTurn.addAll(turns.get(i + 1).scores);
                if (twoRollsAfterTheCurrentTurn.size() < 2) {
                    // we can do this without any additional check only because 
                    // we are sure that the last turn will have at least 2 scores
                    twoRollsAfterTheCurrentTurn.addAll(turns.get(i + 2).scores);
                }

                twoRollsAfterTheCurrentTurn = new ArrayList<Integer>(twoRollsAfterTheCurrentTurn.subList(0, 2));
            }

            // Per turn debug print
            // System.out.println("Player: " + _name + ", turn N° " + i + ", score: " + currentTurn.totalScore(twoRollsAfterTheCurrentTurn));

            result += currentTurn.totalScore(twoRollsAfterTheCurrentTurn);
        }
        return result;	
    }
    
    public void setName(String newName) {
        if (newName.isEmpty()) {
            newName = "Unnamed Player";
        }
        _name = newName;
    }

    public String name() {
        return _name;
    }
    
    // --- debug

    public void autoplayTurn(BowlingTurn turn) {
        turns.add(turn);
    }

    // --- implements Comparable<Player>

    public int compareTo(Player otherPlayer) {
        return otherPlayer.totalScore() - this.totalScore();
    }

    // --- privates

    private void getPlayerName() {
        String inputName = getPlayerNameFromUserInput();
        setName(inputName);
    }

    private int getScoreFromUserInput() {
        int inputScore;
        @SuppressWarnings("resource") //can't close the Scanner, otherwise it would close the Input Stream globally
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a score");
        inputScore = in.nextInt();
        return inputScore;
    }

    private String getPlayerNameFromUserInput() {
        String inputName;
        @SuppressWarnings("resource") //can't close the Scanner, otherwise it would close the Input Stream globally
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a name for the player: ");
        inputName = in.nextLine();
        return inputName;
    }
}
