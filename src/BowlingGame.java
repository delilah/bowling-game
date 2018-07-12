package bowling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


public class BowlingGame {

    private static final int BOWLING_TURNS_NUMBER = 10;
    private ArrayList<Player> players = new ArrayList<Player>();

    public void play() {

        createPlayers();

        for (int currentTurn = 0; currentTurn < BOWLING_TURNS_NUMBER; currentTurn++) {
            for (Player player : players) {

                System.out.println("--------- BEGINNING TURN " + (currentTurn + 1) + " FOR PLAYER " + player.name());

                BowlingTurn turn;					
                if (isIndexOfLastTurn(currentTurn)) {
                    System.out.println("Here comes the special turn!");
                    turn = new BowlingTurnFinal();	
                } else {
                    turn = new BowlingTurn();
                }

                player.playTurn(turn);
            }	
        }

        System.out.println("--------- GAME OVER");
    }

    public void printScores() {
        Player[] playersArray = players.toArray(new Player[players.size()]); 
        Arrays.sort(playersArray);

        for (Player player : playersArray){
            System.out.println("Player: " + player.name() + " Score: " + player.totalScore());
        }
    }	

    // --- privates

    private void createPlayers() {
        int playersNumber = getTotalPlayersFromUserInput();
        for (int i = 0; i < playersNumber; i++) {
            Player player = new Player();
            players.add(player);
        }
    }

    private boolean isIndexOfLastTurn(int turnIndex) {
        return turnIndex == (BOWLING_TURNS_NUMBER - 1);
    }

    private int getTotalPlayersFromUserInput() {

        while (true) {
            try {
                System.out.println("Enter the number of players: ");

                @SuppressWarnings("resource") //can't close the Scanner, otherwise it would close the Input Stream globally
                Scanner in = new Scanner(System.in);
                int inputValue = in.nextInt();
                if (inputValue >= 0){
                    return inputValue;
                } else {
                    throw new IllegalArgumentException("Cannot be a negative value");
                }
            } catch (Exception e) {
                if (e instanceof InputMismatchException) { // this is needed to manage a particular error which has not a standard message
                    System.out.println("OOOPS: You must enter a number");
                }
                else {
                    System.out.println("OOOPS: " + e.getMessage());
                }
            }
        }
    }

    // --- debug

    public void play(int[][] autoplayScores) {

        Player autoplayPlayer = new Player("Autoplay Debug Player");
        players.add(autoplayPlayer);

        for (int i = 0; i < autoplayScores.length; i++) {

            BowlingTurn turn;

            if (isIndexOfLastTurn(i)) {
                turn = new BowlingTurnFinal();
            }
            else {
                turn = new BowlingTurn();
            }

            int[] currentTurnScores = autoplayScores[i];
            for (int j = 0; j < currentTurnScores.length; j++) {
                if (turn.isOver()) {
                    System.out.println("IT WAS PROVIDED A SCORE OF " + currentTurnScores[j] + " FOR THE TURN N° " + (i + 1) + "WHICH IS FINISHED");
                    continue;
                }
                turn.addScore(currentTurnScores[j]);
            }

            autoplayPlayer.autoplayTurn(turn);
        }
    }

}