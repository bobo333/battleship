

class GameBoardHole {
    protected boolean alreadyShot = false;

    public String toString() {
        if (this.alreadyShot) {
            return this.getShotCharacter();
        } else {
            return "·";
        }
    }

    public void shoot() {
        this.alreadyShot = true;
    }

    public boolean hasShip() {
        return false;
    }

    public GuessResult getShotResult() {
        return GuessResult.MISS;
    }

    public boolean isAlreadyShot() {
        return alreadyShot;
    }

    protected String getShotCharacter() {
        return "x";
    }
}
