import java.util.Scanner;

class Battleship {
    public static void main(String[] args) {
        System.out.println("Welcome to Battleship!");
        Scanner reader = new Scanner(System.in);

        boolean keepPlaying = true;
        while (keepPlaying) {
            Game game = new Game(reader);
            game.play();

            System.out.print("Would you like to play again? (y/n) ");
            keepPlaying = reader.nextLine().toLowerCase().equals("y");
        }

    }
}
