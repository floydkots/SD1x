package Homeworks.SD1x.battleship;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class OceanTest {
    private Ocean ocean;
    private EmptySea emptySea;
    private static final int MAX = 20;
    private LinkedHashMap<Class<?>, Integer> lengths;

    @Before
    public void setUp() {
        ocean = new Ocean();
        emptySea = new EmptySea();

        lengths = new LinkedHashMap<>();
        lengths.put(BattleShip.class, 8);
        lengths.put(BattleCruiser.class, 7);
        lengths.put(Cruiser.class, 6);
        lengths.put(LightCruiser.class, 5);
        lengths.put(Destroyer.class, 4);
        lengths.put(Submarine.class, 3);
    }

    @Test
    public void testOceanConstructor() {
        assertEquals(MAX, ocean.getShipArray().length);
        for (Ship[] row: ocean.getShipArray()) {
            assertEquals(MAX, row.length);
            for (Ship ship: row) {
                assertTrue(ship.equals(emptySea));
            }
        }
        assertEquals(0, ocean.getShotsFired());
        assertEquals(0, ocean.getHitCount());
        assertEquals(0, ocean.getShipsSunk());
    }
    @Test
    public void testPlaceAllShipsRandomly() {
        LinkedHashMap<Class<?>, Integer> expected = new LinkedHashMap<>();
        expected.put(BattleShip.class, 1);
        expected.put(BattleCruiser.class, 1);
        expected.put(Cruiser.class, 2);
        expected.put(LightCruiser.class, 2);
        expected.put(Destroyer.class, 3);
        expected.put(Submarine.class, 4);

        LinkedHashMap<Class<?>, Integer> actual = new LinkedHashMap<>();
        actual.put(BattleShip.class, 0);
        actual.put(BattleCruiser.class, 0);
        actual.put(Cruiser.class, 0);
        actual.put(LightCruiser.class, 0);
        actual.put(Destroyer.class, 0);
        actual.put(Submarine.class, 0);

        ocean.placeAllShipsRandomly();
        EmptySea empty = new EmptySea();

        for (Ship[] ships : ocean.getShipArray()) {
            StringBuilder rowString = new StringBuilder(" | ");
            for (Ship ship : ships) {
                rowString.append(ship).append(" | ");
                if (!ship.equals(empty)) {
                    actual.put(ship.getClass(), actual.get(ship.getClass()) + 1);
                }
            }
            System.out.println(rowString);
        }

        for (Map.Entry<Class<?>, Integer> entry : lengths.entrySet()) {
            actual.put(entry.getKey(), actual.get(entry.getKey()) / lengths.get(entry.getKey()));
        }

        assertTrue(expected.equals(actual));
    }

    @Test
    public void testIsOccupied() {
        Submarine sub = new Submarine();
        sub.placeShipAt(0, 0, true, ocean);
        assertTrue(ocean.isOccupied(0, 0));
        assertTrue(ocean.isOccupied(0, 2));
        assertFalse(ocean.isOccupied(0, 3));
    }


    @Test
    public void testShootAtShotsFiredHitCountShipsSunk() {
        Destroyer des = new Destroyer();
        Cruiser cru = new Cruiser();
        des.placeShipAt(5, 5, true, ocean);
        cru.placeShipAt(10, 10, false, ocean);

        // sink des
        for (int i = 5; i < 5 + des.getLength(); i++) {
            assertTrue(ocean.shootAt(5, i));
            assertEquals(i - des.getLength(), ocean.getHitCount());
            assertEquals(i - des.getLength(), ocean.getShotsFired());
        }
        assertEquals(1, ocean.getShipsSunk());
        assertFalse(ocean.shootAt(5, 9));
        assertEquals(4, ocean.getHitCount());
        assertEquals(5, ocean.getShotsFired());
        assertTrue(ocean.getShipArray()[5][9].getHit()[0]);

        // sink cru while checking that firing at the same square
        // twice while the ship is afloat is counted as a Hit.
        for (int i = 10; i < 10 + cru.getLength(); i++) {
            assertTrue(ocean.shootAt(i, 10));
            boolean isSunk = ocean.getShipArray()[i][10].isSunk();
            if (!isSunk) assertTrue(ocean.shootAt(i, 10));
            assertEquals(isSunk ? 4 + (i - 9) * 2 - 1 : 4 + (i - 9) * 2, ocean.getHitCount());
            assertEquals(isSunk ? 5 + (i - 9) * 2 - 1 : 5 + (i - 9) * 2, ocean.getShotsFired());
        }
        assertFalse(ocean.shootAt(16, 10));
        assertEquals(15, ocean.getHitCount());
        assertEquals(17, ocean.getShotsFired());
        assertEquals(2, ocean.getShipsSunk());

        // checking that firing at sunken ship still counts hits.
        assertFalse(ocean.shootAt(5, 5));
        assertFalse(ocean.shootAt(10, 10));
        assertEquals(15, ocean.getHitCount());
        assertEquals(19, ocean.getShotsFired());
        assertEquals(2, ocean.getShipsSunk());
    }

    private void shootAll(Ocean ocean) {
        for (int row = 0; row < MAX; row++) {
            for (int col = 0; col < MAX; col++) {
                Ship theShip = ocean.getShipArray()[row][col];
                if (!theShip.equals(emptySea)) {
                    assertTrue(ocean.shootAt(row, col));
                }
            }
        }
    }

    @Test
    public void testIsGameOver() {
        ocean.placeAllShipsRandomly();
        shootAll(ocean);
        assertTrue(ocean.isGameOver());
    }

    private void shootRandom(Ocean ocean) {
        for (int row = 0; row < MAX; row++) {
            for (int col = 0; col < MAX; col++) {
                Random rand = new Random();
                if (rand.nextBoolean()) {
                    ocean.shootAt(row, col);
                }
            }
        }
    }


    @Test
    public void testPrint() {
        ocean.placeAllShipsRandomly();
        shootAll(ocean);
        ocean.print();
        System.out.println("\n\n\n");
        ocean = new Ocean();
        ocean.placeAllShipsRandomly();
        shootRandom(ocean);
        ocean.print();
    }
}
