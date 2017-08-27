import java.util.concurrent.ThreadLocalRandom;

class GameBoard {
    private GameBoardHole[][] grid;
    private Ship[] ships;

    public GameBoard() {
        // make grid 10 x 10
        grid = new GameBoardHole[10][10];

        for (GameBoardHole[] row: grid) {
            for (int i = 0; i < row.length; i++) {
                row[i] = new GameBoardHole();
            }
        }

        // make ships
        Ship cruiser = new Ship(2);
        Ship submarine = new Ship(3);
        Ship destroyer = new Ship(3);
        Ship battleship = new Ship(4);
        Ship carrier = new Ship(5);

        this.ships = new Ship[]{cruiser, submarine, destroyer, battleship, carrier};

        // add ships to board
        for (Ship ship: this.ships) {
            addToBoard(ship);
        }
    }

    public void showBoard() {
        // show column numbers at top of board
        System.out.print("  ");
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        int a = (int) 'A';
        for (int i = 0; i < this.grid.length; i++) {
            // show row letter at left of row
            String letter = String.valueOf((char) ((char) a + i));
            System.out.print(letter + " "); 

            for (GameBoardHole hole: this.grid[i]) {
                System.out.print(hole + " ");
            }

            // show row letter on right side of the row
            System.out.print(letter + " "); 
            System.out.println();
        }

        // show numbers at bottom of board
        System.out.print("  ");
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public GuessResult makeShot(Guess guess) {
        GameBoardHole hole = this.grid[guess.row][guess.column];
        if (hole.alreadyShot) {
            return GuessResult.REPEAT;
        }
        hole.shoot();
        return hole.getShotResult();
    }

    public boolean isGameOver() {
        // check if all ships are sunk
        for (Ship ship: this.ships) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    private void addToBoard(Ship ship) {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        Orientation[] orientations = Orientation.values();

        boolean added = false;
        while (!added) {
            // get random row, column and orientation
            int row = rand.nextInt(0, 11);
            int column = rand.nextInt(0, 11);
            Orientation orientation = orientations[rand.nextInt(0, 2)];

            if (canFit(ship, row, column, orientation)) {
                placeShip(ship, row, column, orientation);
                added = true;
            }
        }
    }

    private void placeShip(Ship ship, int row, int column, Orientation orientation) {
        for (int i = 0; i < ship.size; i++) {
            if (orientation == Orientation.HORIZONTAL) {
                this.grid[row][column + i] = ship.holes[i];    
            } else {
                this.grid[row + i][column] = ship.holes[i];
            }
        }
    }

    private boolean hasShip(int row, int column) {
        return this.grid[row][column].hasShip();
    }

    private boolean canFit(Ship ship, int row, int column, Orientation orientation) {
        try {
            // check that none of the holes already have a ship
            for (int i = 0; i < ship.size; i++) {
                if (orientation == Orientation.HORIZONTAL){
                    if (hasShip(row, column + i)) return false;
                } else {
                    if (hasShip(row + i, column)) return false;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // off the board
            return false;
        }
        return true;
    }
}
