
class ShipHole extends GameBoardHole {

    public String toString() {
        if (this.alreadyShot) {
            return "H";
        } else {
            return "·";
        }
    }
}