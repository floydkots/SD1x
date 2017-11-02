package Homeworks.SD1x.battleship;

/**
 * Represents ship with  3 squares
 */
public class Submarine extends Ship {
    public Submarine() {
        this.setLength(3);
        this.hitInit();
    }

    @Override
    public String getShipType() {
        return "submarine";
    }
}
