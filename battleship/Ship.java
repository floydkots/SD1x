package Homeworks.SD1x.battleship;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public abstract class Ship {
    private int bowRow; // Row of ship's bow(front)
    private int bowColumn; // Column of ship's bow
    private int length; // Squares occupied by the ship
    private boolean horizontal; // True if ship in 1 row
    private boolean[] hit; // Records hits.
    private static final int MAX = 19;

    public abstract String getShipType();

    public void hitInit() {
        this.hit = new boolean[this.length];
        for (int i = 0; i < this.length; i++) {
            this.hit[i] = false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ship ship = (Ship) o;

        return length == ship.length;
    }

    @Override
    public int hashCode() {
        return length;
    }

    private boolean overlaps(int[] ship, int[] point) {
        return (ship[0] == point[0]) && (ship[1] == point[1]);
    }

    private boolean isOut(int[] ship) {
        return ship[0] < 0 || ship[1] < 0 || ship[0] > 19 || ship[1] > 19;
    }

    private boolean touches(int[] ship, int[] point) {
        boolean up = point[0] - 1 == ship[0] && point[1] == ship[1];
        boolean down = point[0] + 1 == ship[0] && point[1] == ship[1];
        boolean right = point[1] + 1 == ship[1] && point[0] == ship[0];
        boolean left = point[1] - 1 == ship[1] && point[0] == ship[0];
        boolean upLeft = point[0] - 1 == ship[0] && point[1] - 1 == ship[1];
        boolean downLeft = point[0] + 1 == ship[0] && point[1] - 1 == ship[1];
        boolean upRight = point[0] - 1 == ship[0] && point[1] + 1 == ship[1];
        boolean downRight = point[0] + 1 == ship[0] && point[1] + 1 == ship[1];
        boolean vertical = up || down;
        boolean horizontal = right || left;
        boolean diagonal = upLeft || downLeft || upRight || downRight;
        return vertical || horizontal || diagonal;
    }

    private SortedSet<int[]> get4Neighbours(int row, int col) {
        SortedSet <int[]> neighbours = new TreeSet<>(Comparator.comparing(Arrays::hashCode));
        // left neighbour
        if (col > 0) {
            neighbours.add(new int[] {row, col - 1});
        }
        // right neighbour
        if (col < MAX) {
            neighbours.add(new int[] {row, col + 1});
        }
        // up neighbour
        if (row > 0) {
            neighbours.add(new int[] {row - 1, col});
        }
        // down neighbour
        if (row < MAX) {
            neighbours.add(new int[] {row + 1, col});
        }
        return neighbours;
    }

    private SortedSet<int[]> get8Neighbours(int row, int col) {
        SortedSet<int[]> neighbours = new TreeSet<>(Comparator.comparing(Arrays::hashCode));
        // upLeft neighbour
        if (col > 0 && row > 0) {
            neighbours.add(new int[] {row - 1, col - 1});
        }
        // downLeft neighbour
        if (col > 0 && row < MAX) {
            neighbours.add(new int[] {row + 1, col - 1});
        }
        // upRight neighbour
        if (col < MAX && row > 0) {
            neighbours.add(new int[] {row - 1, col + 1});
        }
        // downRight neighbour
        if (col < MAX && row < MAX) {
            neighbours.add(new int[] {row + 1, col + 1});
        }
        neighbours.addAll(get4Neighbours(row, col));
        return neighbours;
    }

    private SortedSet<int[]> getNeighbours(ArrayList<int[]> shipPoints) {
        SortedSet<int[]> neighbours = new TreeSet<>(Comparator.comparing(Arrays::hashCode));
        for (int i = 0; i < shipPoints.size(); i++) {
            int[] point = shipPoints.get(i);
            if (i == 0 || i == shipPoints.size() - 1) {
                neighbours.addAll(get8Neighbours(point[0], point[1]));
            } else {
                neighbours.addAll(get4Neighbours(point[0], point[1]));
            }
        }
        return neighbours;
    }

    private ArrayList<int[]> getBody(int row, int column, boolean horizontal) {
        ArrayList<int[]> body = new ArrayList<>();
        for (int i = 0; i < this.length; i++) {
            int[] point = new int[2];
            if (horizontal) {
                point[0] = row;
                point[1] = column + i;
            } else {
                point[0] = row + i;
                point[1] = column;
            }
            body.add(point);
        }
        return body;
    }

    /**
     * Check if it's okay to place a ship with the bow at
     * the given position, the ship's length and with the
     * given orientation. The ship must not overlap
     * another ship, or touch another ship (vertically,
     * horizontally, or diagonally), and it must not
     * "stick out" beyond the array.
     * @param row: Ship's bow row position
     * @param column: Ship's bow column position
     * @param horizontal: Ship's orientation
     * @param ocean: Well, the Ocean in it's state
     * @return true if it's okay to place the ship, false
     * otherwise.
     */
    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        ArrayList<int[]> body = getBody(row, column, horizontal);
        for (int[] point: body) {
            if (isOut(point)) return false;
        }

        EmptySea empty = new EmptySea();

        for (int[] point: getNeighbours(body)) {
            if (!ocean.getShipArray()[point[0]][point[1]].equals(empty)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Puts the ship in the ocean.
     * @param row
     * @param column
     * @param horizontal
     * @param ocean
     * @see #okToPlaceShipAt for parameter descriptions
     */
    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        // Give values to the bowRow, bowColumn, and
        // horizontal instance variables in the ship.
        this.bowRow = row;
        this.bowColumn = column;
        this.horizontal = horizontal;

        // Put a reference to the ship in each of 1 or
        // more locations (up to 8) in the ship's array
        // in the Ocean object.
        for (int[] point: getBody(bowRow, bowColumn, horizontal)) {
            ocean.getShipArray()[point[0]][point[1]] = this;
        }
    }

    public int getHitIndex(int row, int col) {
        int index;
        if (horizontal){
            index = col - bowColumn;
        } else {
            index = row - bowRow;
        }
        return index;
    }

    /**
     * If the ship occupies the given position and it
     * hasn't been sunk, it marks that part of the ship
     * as "hit". In the hit array, 0 indicates the bow.
     * @param row to hit
     * @param column to hit
     * @return true if ship is hit, false otherwise.
     */
    public boolean shootAt(int row, int column) {
        for (int[] point: getBody(bowRow, bowColumn, horizontal)) {
            if (point[0] == row && point[1] == column && !isSunk()) {
                int index = getHitIndex(point[0], point[1]);
                hit[index] = true;
                return true;
            }
        }
        return false;
    }

    /**
     * Determine if every part of the ship has been hit.
     * @return true if true, false otherwise
     */
    public boolean isSunk() {
        for (boolean shot: hit) {
            if (!shot) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if (this.isSunk()) {
            return "x";
        } else {
            return "S";
        }
    }

    public int getBowRow() {
        return bowRow;
    }

    public void setBowRow(int bowRow) {
        this.bowRow = bowRow;
    }

    public int getBowColumn() {
        return bowColumn;
    }

    public void setBowColumn(int bowColumn) {
        this.bowColumn = bowColumn;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public boolean[] getHit() {
        return hit;
    }

    public void setHit(boolean[] hit) {
        this.hit = hit;
    }

}
