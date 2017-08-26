import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Game {
    private GameBoard board = new GameBoard();
    private Scanner reader;
    private Pattern guessPattern = Pattern.compile("([a-jA-J])(\\d\\d?)");
    private int numGuesses = 0;

    public Game(Scanner reader) {
        this.reader = reader;
    }

    public void play() {
        board.showBoard();

        while (!board.isGameOver()) {

            // get next guess
            Guess guess = getGuess();
            GuessResult result = board.makeShot(guess);

            // show board result
            System.out.println();

            // TODO: how to clear console here instead?
            board.showBoard();

            if (result == GuessResult.REPEAT) {
                System.out.println("You already shot there!");
            } else if (result == GuessResult.HIT) {
                System.out.println("Hit!");
                this.numGuesses++;
            } else {
                System.out.println("Miss!");
                this.numGuesses++;
            }
            System.out.println();
        }

        System.out.println("Game over!");
        board.showBoard();
        System.out.println("You took " + this.numGuesses + " tries!");
    }

    private Guess getGuess() {
            
        System.out.print("Next guess (ex: c5): ");
        String rawGuess = this.reader.nextLine();
        Matcher matcher = this.guessPattern.matcher(rawGuess);

        if (matcher.matches()) {

            String rawRow = matcher.group(1);
            String rawColumn = matcher.group(2);

            int column = Integer.parseInt(rawColumn);
            if (column > 10 || column < 1) {
                System.out.println("Invalid guess!");
                return getGuess();
            }

            System.out.println(rawRow + " " + rawColumn);

            int rowInt = letterToInt(rawRow);
            int columnInt = column - 1;
            return new Guess(rowInt, columnInt);
        } else {
            System.out.println("Invalid guess!");
            return getGuess();
        }
    }

    private int letterToInt(String letter) {
        letter = letter.toLowerCase();
        return letter.charAt(0) - 'a';
    }
}

