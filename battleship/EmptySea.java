package Homeworks.SD1x.battleship;

/**
 * Describes part of the ocean that does not have a ship
 * in it
 */
public class EmptySea extends Ship {
    private boolean shot;

    public EmptySea() {
        this.setLength(1);
        this.hitInit();
    }

    @Override
    public boolean shootAt(int row, int column) {
        this.setHit(new boolean[] {true});
        return false;
    }

    @Override
    public boolean isSunk() {
        return false;
    }

    @Override
    public String toString() {
        return ".";
    }

    @Override
    public String getShipType() {
        return "empty";
    }
}
