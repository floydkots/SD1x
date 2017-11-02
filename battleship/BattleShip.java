package Homeworks.SD1x.battleship;

/**
 * Ship that occupies 8 squares
 */
public class BattleShip extends Ship {
    public BattleShip() {
        this.setLength(8);
        this.hitInit();
    }

    @Override
    public String getShipType() {
        return "battleship";
    }
}
