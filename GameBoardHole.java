

class GameBoardHole {
    protected boolean alreadyShot = false;

    public String toString() {
        if (this.alreadyShot) {
            return "x";
        } else {
            return "·";
        }
    }

    public void shoot() {
        this.alreadyShot = true;
    }
}
