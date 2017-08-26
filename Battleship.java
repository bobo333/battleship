import java.util.Scanner;

// gameboard
// display, holds ships, where has been shot, which were hits

// ships
// size, is sunk, hit locations

// game
// turns, wipes screen and shows new display, gets input



class Battleship {
    public static void main(String[] args) {
        System.out.println("Welcome to Battleship!");
        Scanner reader = new Scanner(System.in);

        boolean keepPlaying = true;
        while (keepPlaying) {
            Game game = new Game(reader);
            game.play();

            
            System.out.print("Would you like to play again? (y/n) ");
            if (!reader.nextLine().toLowerCase().equals("y")) keepPlaying = false;
        }

    }
}
