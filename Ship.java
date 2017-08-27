import java.util.Arrays;


class Ship {
    public final int size;
    public ShipHole[] holes;

    public Ship(int size) {
        this.size = size;
        holes = new ShipHole[this.size];

        for (int i = 0; i < holes.length; i++) {
            holes[i] = new ShipHole();
        }
    }

    public boolean isSunk() {
        for (ShipHole hole: this.holes) {
            if (!hole.isAlreadyShot()) {
                return false;
            }
        }
        return true;
    }
}
