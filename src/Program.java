package bowling;

import bowling.BowlingGame;

public class Program {

    public static void main(String[] args) {
        
        // Normal game play
        BowlingGame game = new BowlingGame();
        game.play();

//        //         Single player debug mode
//        BowlingGame game = new BowlingGame();
//        int[][] scores = {{4, 6}, {2, 6}, {9, 1}, {4, 6}, {5, 5}, {3, 4}, {4, 5}, {1, 3}, {4, 4}, {9, 1, 10}}; // Total score: 110
//        game.play(scores); //             |<-- super chicken -->|

        // Prints Scores
        game.printScores();
    }

}
