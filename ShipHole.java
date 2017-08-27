
class ShipHole extends GameBoardHole {

    public boolean hasShip() {
        return true;
    }

    public GuessResult getShotResult() {
        return GuessResult.HIT;
    }

    protected String getShotCharacter() {
        return "H";
    }
}
