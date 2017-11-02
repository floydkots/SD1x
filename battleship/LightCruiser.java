package Homeworks.SD1x.battleship;

/**
 * Represents ship with 5 squares
 */
public class LightCruiser extends Ship {
    public LightCruiser() {
        this.setLength(5);
        this.hitInit();
    }

    @Override
    public String getShipType() {
        return "light cruiser";
    }
}
