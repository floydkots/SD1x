package Homeworks.SD1x.battleship;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Ocean {
    private Ship[][] ships;
    private int shotsFired;
    private int hitCount;
    private int shipsSunk;
    private static final Ship empty = new EmptySea();
    private static final int MAX = 20;
    public Ocean() {
        ships = new Ship[MAX][MAX];
        for (int row = 0; row < MAX; row++) {
            for (int col = 0; col < MAX; col++) {
                ships[row][col] = new EmptySea();
            }
        }
        shotsFired = 0;
        hitCount = 0;
        shipsSunk = 0;
    }

    /**
     * Place all the 13 ships randomly. Starting with
     * the larger ones.
     * 1 Battleship - 8 squares
     * 1 Battlecruiser - 7 squares
     * 2 Cruisers - 6 squares
     * 2 Light Cruisers - 5 squares
     * 3 Destroyers - 4 squares
     * 4 Submarines -3 squares
     */
    public void placeAllShipsRandomly() {
        LinkedHashMap<Class<?>, Integer> shipsMap = new LinkedHashMap<>();
        Random rand = new Random();
        shipsMap.put(BattleShip.class, 1);
        shipsMap.put(BattleCruiser.class, 1);
        shipsMap.put(Cruiser.class, 2);
        shipsMap.put(LightCruiser.class, 2);
        shipsMap.put(Destroyer.class, 3);
        shipsMap.put(Submarine.class, 4);

        for (Map.Entry<Class<?>, Integer> entry : shipsMap.entrySet()) {
            Class aShip = entry.getKey();
            int num = entry.getValue();
            for (int i = 0; i < num; i++) {
                try {
                    Ship theShip = (Ship) aShip.getConstructors()[0].newInstance();
                    boolean placed = false;
                    while (!placed) {
                        int row = rand.nextInt(MAX);
                        int col = rand.nextInt(MAX);
                        boolean hor = rand.nextBoolean();
                        if (theShip.okToPlaceShipAt(row, col, hor, this)) {
                            theShip.placeShipAt(row, col, hor, this);
                            placed = true;
                        }
                    }
                }
                catch (InstantiationException |
                        IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
    }

    private boolean inRange(int row, int col) {
        return !(row < 0 || col < 0 || row > 19 || col > 19);
    }

    /**
     * Check if the given position contains a ship
     * @param row to check
     * @param column to check
     * @return true if occupied, false otherwise.
     */
    public boolean isOccupied(int row, int column) {
        if (inRange(row, column)) {
            return !ships[row][column].equals(empty);
        }
        return false;
    }

    /**
     * Check if the given location contains a "real" ship
     * that is still afloat. Also updates the number of
     * fired shots
     * @param row to shoot
     * @param column to shoot
     * @return true if 'real', afloat ship, else false
     */
    public boolean shootAt(int row, int column) {
        if (inRange(row, column)) {
            shotsFired++;
            Ship theShip = ships[row][column];
            if (theShip.shootAt(row, column)) {
                hitCount++;
                if (theShip.isSunk()) {
                    shipsSunk++;
                }
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    /**
     * Getter for total number of shots fired in this
     * game.
     * @return total number of shots fired as int.
     */
    public int getShotsFired() {
        return shotsFired;
    }

    /**
     * Get the number of hits recorded in this game. All
     * hits are counted, not just the first time a
     * given square is hit.
     * @return int of the total number of hits counted.
     */
    public int getHitCount() {
        return hitCount;
    }

    /**
     * Get the number of sunk ships in this game.
     * @return int of the total number of ships sunk.
     */
    public int getShipsSunk() {
        return shipsSunk;
    }

    /**
     * Check if all the ships have been sunk.
     * @return true if true, false otherwise.
     */
    public boolean isGameOver() {
        return shipsSunk == 13;
    }

    /**
     * Get the 20x20 array of ships.
     * @return 2d array of ships
     */
    public Ship[][] getShipArray() {
        return ships;
    }

    /**
     * Print the ocean. Row numbers are displayed along
     * the left edge of the array, and column numbers
     * along the top. Numbers are from 00 to 19.
     * 'S' - location fired upon and hit
     * '-' - location fired upon and missed
     * 'x' - location containing sunken ship
     * '.' - location never fired upon
     */
    public void print() {
        for (int row = 0; row < MAX; row++) {
            if (row == 0) {
                StringBuilder colString = new StringBuilder();
                colString.append("  ");
                for (int col = 0; col < MAX; col++) {
                    colString.append(String.format("%3s", col));
                }
                System.out.println(colString);
            }
            StringBuilder rowString = new StringBuilder();
            rowString.append(String.format("%2d%s", row, "|"));
            for (int col = 0; col < MAX; col++) {
                Ship theShip = ships[row][col];
                if (theShip.equals(empty)) {
                    if (theShip.getHit()[0]) {
                        rowString.append(String.format("%2s", "-"));
                    } else {
                        // using space instead of period as in instructions
                        // looks neater, imo
                        rowString.append(String.format("%2s", " "));
                    }
                } else if (theShip.isSunk()) {
                    rowString.append(String.format("%2s", "x"));
                } else if (theShip.getHit()[theShip.getHitIndex(row, col)]) {
                    rowString.append(String.format("%2s", "S"));
                } else {
                    // using space instead of period as in instructions
                    // looks neater, imo
                    rowString.append(String.format("%2s", " "));
                }
                rowString.append("|");
            }
            System.out.println(rowString);
        }
    }
}
