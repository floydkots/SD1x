package Homeworks.SD1x.battleship;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import java.lang.reflect.Method;

public class ShipTest implements Comparator<int[]> {
    private BattleShip battleShip;
    private BattleCruiser battleCruiser;
    private Cruiser cruiser;
    private LightCruiser lightCruiser;
    private Destroyer destroyer;
    private Submarine submarine;
    private EmptySea emptySea;
    private Ocean ocean;
    private SortedSet<int[]> expected;
    private SortedSet<int[]> actual;

    private Random rand;
    private final int MAX = 19;
    private final int MIN = 0;


    @Before
    public void setUp() {
        battleShip = new BattleShip();
        battleCruiser = new BattleCruiser();
        cruiser = new Cruiser();
        lightCruiser = new LightCruiser();
        destroyer = new Destroyer();
        submarine = new Submarine();
        emptySea = new EmptySea();
        ocean = new Ocean();

        rand = new Random();

        expected = new TreeSet<>(Comparator.comparing(Arrays::hashCode));
        actual = new TreeSet<>(Comparator.comparing(Arrays::hashCode));
    }

    @Test
    public void testBattleShip() {
        assertEquals(8, battleShip.getLength());
        assertEquals(8, battleShip.getHit().length);
        for (boolean isHit : battleShip.getHit()) { assertFalse(isHit);}
        assertTrue(battleShip.getShipType().equals("battleship"));
    }

    @Test
    public void testBattleCruiser() {
        assertEquals(7, battleCruiser.getLength());
        assertEquals(7, battleCruiser.getHit().length);
        for (boolean isHit : battleCruiser.getHit()) { assertFalse(isHit);}
        assertTrue(battleCruiser.getShipType().equals("battlecruiser"));
    }

    @Test
    public void testCruiser() {
        assertEquals(6, cruiser.getLength());
        assertEquals(6, cruiser.getHit().length);
        for (boolean isHit : cruiser.getHit()) { assertFalse(isHit);}
        assertTrue(cruiser.getShipType().equals("cruiser"));
    }

    @Test
    public void testLightCruiser() {
        assertEquals(5, lightCruiser.getLength());
        assertEquals(5, lightCruiser.getLength());
        for (boolean isHit : lightCruiser.getHit()) { assertFalse(isHit);}
        assertTrue(lightCruiser.getShipType().equals("light cruiser"));
    }

    @Test
    public void testDestroyer() {
        assertEquals(4, destroyer.getLength());
        assertEquals(4, destroyer.getHit().length);
        for (boolean isHit : destroyer.getHit()) { assertFalse(isHit);}
        assertTrue(destroyer.getShipType().equals("destroyer"));
    }

    @Test
    public void testSubmarine() {
        assertEquals(3, submarine.getLength());
        assertEquals(3, submarine.getHit().length);
        for (boolean isHit : submarine.getHit()) { assertFalse(isHit);}
        assertTrue(submarine.getShipType().equals("submarine"));
    }

    @Test
    public void testEmptySea() {
        assertEquals(1, emptySea.getLength());
        assertEquals(1, emptySea.getHit().length);
        for (boolean isHit : emptySea.getHit()) { assertFalse(isHit);}
        for (int i = 0; i < 5; i++) {
            int row = rand.nextInt(MAX - MIN + 1) + MIN;
            int col = rand.nextInt(MAX - MIN + 1) + MIN;
            assertFalse(emptySea.shootAt(row, col));
            assertTrue(emptySea.getHit()[0]);
        }
        assertFalse(emptySea.isSunk());
        assertTrue(emptySea.toString().equals(" "));
        assertTrue(emptySea.getShipType().equals("empty"));

    }

    @Test
    public void testOverlaps() {
        try {
            Method overlaps = battleShip.getClass().getSuperclass().getDeclaredMethod("overlaps", int[].class, int[].class);
            overlaps.setAccessible(true);
            try {
                int[] ship_1 = new int[] {0, 0};
                int[] ship_2 = new int[] {0, 1};
                int[] ship_3 = new int[] {1, 0};
                int[] ship_4 = new int[] {1, 1};
                int[] point_1 = new int[] {0, 0};
                assertTrue((boolean) overlaps.invoke(battleShip, ship_1, point_1));
                assertFalse((boolean) overlaps.invoke(battleShip, ship_2, point_1));
                assertFalse((boolean) overlaps.invoke(battleShip, ship_3, point_1));
                assertFalse((boolean) overlaps.invoke(battleShip, ship_4, point_1));
            }
            catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                fail(e.getMessage());
            }
        }
        catch (NoSuchMethodException e) {
            fail(e.getMessage());
        }

    }


    @Test
    public void testIsOut() {
        try {
            Method isOut = submarine.getClass().getSuperclass().getDeclaredMethod("isOut", int[].class);
            isOut.setAccessible(true);
            try {
                int[] ship_1 = new int[] {19, 19};
                int[] ship_2 = new int[] {20, 20};
                int[] ship_3 = new int[] {0, 20};
                int[] ship_4 = new int[] {19, 0};
                assertFalse((boolean) isOut.invoke(submarine, ship_1));
                assertTrue((boolean) isOut.invoke(submarine, ship_2));
                assertTrue((boolean) isOut.invoke(submarine, ship_3));
                assertFalse((boolean) isOut.invoke(submarine, ship_4));
            }
            catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                fail(e.getMessage());
            }
        }
        catch (NoSuchMethodException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testTouches() {
        try {
            Method touches = destroyer.getClass().getSuperclass().getDeclaredMethod("touches", int[].class, int[].class);
            touches.setAccessible(true);
            try{
                int[] point = new int[] {1, 1};
                int[] ship_1 = new int[] {0, 0};
                int[] ship_2 = new int[] {0, 1};
                int[] ship_3 = new int[] {0, 2};
                int[] ship_4 = new int[] {1, 0};
                int[] ship_5 = new int[] {1, 2};
                int[] ship_6 = new int[] {2, 0};
                int[] ship_7 = new int[] {2, 1};
                int[] ship_8 = new int[] {2, 2};
                int[] ship_9 = new int[] {2, 3};
                int[] ship_10 = new int[] {10, 10};

                assertTrue((boolean) touches.invoke(destroyer, ship_1, point));
                assertTrue((boolean) touches.invoke(destroyer, ship_2, point));
                assertTrue((boolean) touches.invoke(destroyer, ship_3, point));
                assertTrue((boolean) touches.invoke(destroyer, ship_4, point));
                assertTrue((boolean) touches.invoke(destroyer, ship_5, point));
                assertTrue((boolean) touches.invoke(destroyer, ship_6, point));
                assertTrue((boolean) touches.invoke(destroyer, ship_7, point));
                assertTrue((boolean) touches.invoke(destroyer, ship_8, point));
                assertFalse((boolean) touches.invoke(destroyer, ship_9, point));
                assertFalse((boolean) touches.invoke(destroyer, ship_10, point));
            }
            catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
                fail(e.getMessage());
            }
        }
        catch (NoSuchMethodException e) {
            fail(e.getMessage());
        }
    }

    @Override
    public int compare(int[] a1, int[] a2) {
        return Arrays.hashCode(a1) -Arrays.hashCode(a2);
    }

    @Test
    public void testGettingBodyAndNeighbours() {
        try {
            Method get4Neighbours = cruiser.getClass().getSuperclass().getDeclaredMethod("get4Neighbours", int.class, int.class);
            Method get8Neighbours = cruiser.getClass().getSuperclass().getDeclaredMethod("get8Neighbours", int.class, int.class);
            Method getNeighbours = cruiser.getClass().getSuperclass().getDeclaredMethod("getNeighbours", ArrayList.class);
            Method getBody = cruiser.getClass().getSuperclass().getDeclaredMethod("getBody", int.class, int.class, boolean.class);

            get4Neighbours.setAccessible(true);
            get8Neighbours.setAccessible(true);
            getNeighbours.setAccessible(true);
            getBody.setAccessible(true);

            try {
                int[] point = new int[] {1, 1};
                int[] upLeft = new int[] {0, 0};
                int[] up = new int[] {0, 1};
                int[] upRight = new int[] {0, 2};
                int[] left = new int[] {1, 0};
                int[] right = new int[] {1, 2};
                int[] downLeft = new int[] {2, 0};
                int[] down = new int[] {2, 1};
                int[] downRight = new int[] {2, 2};
                expected.add(up); expected.add(down); expected.add(right); expected.add(left);
                actual.addAll((SortedSet<int[]>)(get4Neighbours.invoke(cruiser, point[0], point[1])));
                assertTrue(expected.equals(actual));

                actual.clear();
                expected.add(upLeft); expected.add(upRight); expected.add(downLeft); expected.add(downRight);
                actual.addAll((SortedSet<int[]>)(get8Neighbours.invoke(cruiser, point[0], point[1])));
                assertTrue(expected.equals(actual));

                expected.clear(); actual.clear();
                expected.add(up); expected.add(left);
                actual.addAll((SortedSet<int[]>)(get4Neighbours.invoke(cruiser, upLeft[0], upLeft[1])));
                assertTrue(expected.equals(actual));

                expected.clear(); actual.clear();
                expected.add(upLeft); expected.add(upRight); expected.add(point);
                actual.addAll((SortedSet<int[]>)(get4Neighbours.invoke(cruiser, up[0], up[1])));
                assertTrue(expected.equals(actual));

                expected.clear(); actual.clear();
                expected.add(new int[] {1, 14});
                expected.add(new int[] {2, 14});
                expected.add(new int[] {3, 14});
                expected.add(new int[] {4, 14});
                actual.addAll((ArrayList<int[]>)(getBody.invoke(destroyer, 1, 14, false)));
                assertTrue(expected.equals(actual));

                expected.clear(); actual.clear();
                ArrayList<int[]> body = (ArrayList<int[]>)(getBody.invoke(submarine, 18, 17, true));

                expected.add(new int[] {17, 16});
                expected.add(new int[] {17, 17});
                expected.add(new int[] {17, 18});
                expected.add(new int[] {17, 19});
                expected.add(new int[] {18, 16});
                expected.add(new int[] {19, 16});
                expected.add(new int[] {19, 17});
                expected.add(new int[] {19, 18});
                expected.add(new int[] {19, 19});
                expected.addAll(body);
                actual.addAll((SortedSet<int[]>)(getNeighbours.invoke(cruiser, body)));
                assertTrue(expected.equals(actual));
            }
            catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
                fail(e.getMessage());
            }
        }
        catch (NoSuchMethodException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPlaceShipAt() {
        expected.add(new int[] {1, 14});
        expected.add(new int[] {2, 14});
        expected.add(new int[] {3, 14});
        expected.add(new int[] {4, 14});
        destroyer.placeShipAt(1, 14, false, ocean);
        for (int[] point: expected) {
            assertTrue(ocean.getShipArray()[point[0]][point[1]].equals(destroyer));
        }
    }

    @Test
    public void testOkToPlaceShipAt() {
        battleShip.placeShipAt(0, 0, false, ocean);
        battleCruiser.placeShipAt(19, 12, true, ocean);


        // sticking out of the ocean's top
        assertFalse(destroyer.okToPlaceShipAt(-2, 14, false, ocean));
        // sticking out of the ocean's right side
        assertFalse(battleCruiser.okToPlaceShipAt(10, 16, true, ocean));
        // overlapping battleShip vertically
        assertFalse(cruiser.okToPlaceShipAt(0, 0, false, ocean));
        // overlapping battleCruiser horizontally
        assertFalse(submarine.okToPlaceShipAt(19, 10, true, ocean));
        // touching battleShip horizontally
        assertFalse(lightCruiser.okToPlaceShipAt(6, 1, true, ocean));
        // touching battleCruiser diagonally
        assertFalse(battleShip.okToPlaceShipAt(18, 4, true, ocean));
        // not overlapping, not touching, placed horizontally
        assertTrue(lightCruiser.okToPlaceShipAt(6, 2, true, ocean));
        // not overlapping, not touching, placed vertically
        assertTrue(submarine.okToPlaceShipAt(0, 2, false, ocean));
    }

    @Test
    public void testIsSunkAndToString() {
        // battleShip is of length 8
        boolean[] battleShipHit = battleShip.getHit();
        for (int i = 0; i < battleShipHit.length; i++) {
            battleShipHit[i] = true;
        }
        assertTrue(battleShip.isSunk());
        assertTrue("x".equals(battleShip.toString()));

        // cruiser is of length 6
        boolean[] cruiserHit = cruiser.getHit();
        cruiserHit[0] = true;
        cruiserHit[5] = true;
        assertFalse(cruiser.isSunk());
        assertTrue("S".equals(cruiser.toString()));
    }

    @Test
    public void testShootAt() {
        // lightCruiser is of length 5
        if (lightCruiser.okToPlaceShipAt(5, 5, true, ocean)) {
            lightCruiser.placeShipAt(5, 5, true, ocean);
        }
        assertTrue(lightCruiser.shootAt(5, 5));
        assertTrue(lightCruiser.shootAt(5, 9));
        assertFalse(lightCruiser.shootAt(5, 10));
        assertFalse(lightCruiser.shootAt(4, 5));

        boolean[] expected = new boolean[] {true, false, false, false, true};
        boolean[] actual = lightCruiser.getHit();
        assertArrayEquals(expected, actual);
    }
}
