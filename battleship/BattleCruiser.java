package Homeworks.SD1x.battleship;

/**
 * Ship that occupies 7 squares
 */
public class BattleCruiser extends Ship {
    public BattleCruiser() {
        this.setLength(7);
        this.hitInit();
    }

    @Override
    public String getShipType() {
        return "battlecruiser";
    }
}
