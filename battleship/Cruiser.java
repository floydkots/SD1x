package Homeworks.SD1x.battleship;

/**
 * Represents ship with 6 squares
 */
public class Cruiser extends Ship {
    public Cruiser() {
        this.setLength(6);
        this.hitInit();
    }

    @Override
    public String getShipType() {
        return "cruiser";
    }
}
