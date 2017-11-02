package Homeworks.SD1x.battleship;

/**
 * Represents ship with 4 squares
 */
public class Destroyer extends Ship {
    public Destroyer() {
        this.setLength(4);
        this.hitInit();
    }

    @Override
    public String getShipType() {
        return "destroyer";
    }
}
