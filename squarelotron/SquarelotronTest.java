package Homeworks.SD1x.squarelotron;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;



public class SquarelotronTest{
    private Squarelotron square_2;
    private Squarelotron square_4;
    private Squarelotron square_5;

    @Before
    public void setUp() {
        square_2 = new Squarelotron(2);
        square_4 = new Squarelotron(4);
        square_5 = new Squarelotron(5);
    }

    /**
     * Testing the constructor of the Squarelotron class
     */
    @Test
    public void testSquarelotron(){
        assertEquals(2, square_2.size, 0);
        assertEquals(4, square_4.size, 0);

        int [][] expected_2 = new int[][] {
                {1, 2},
                {3, 4}
        };
        assertArrayEquals(expected_2, square_2.squarelotron);

        int [][] expected_4 = new int[][] {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        assertArrayEquals(expected_4, square_4.squarelotron);
    }

    /**
     * Testing the upside-down flip method
     */
    @Test
    public void testUpsideDownFlip(){
        int [][] expected_2 = new int [][] {
                {3, 4},
                {1, 2}
        };
        assertArrayEquals(expected_2, square_2.upsideDownFlip(1).squarelotron);

        int [][] expected_4 = new int [][] {
                {13, 14, 15, 16},
                {9, 6, 7, 12},
                {5, 10, 11, 8},
                {1, 2, 3, 4}
        };

        assertArrayEquals(expected_4, square_4.upsideDownFlip(1).squarelotron);
    }

    /**
     * Testing the mainDiagonalFlip method.
     */
    @Test
    public void testMainDiagonalFlip() {
        int [][] expected_5 = new int [][] {
                {1, 6, 11, 16, 21},
                {2, 7, 8, 9, 22},
                {3, 12, 13, 14, 23},
                {4, 17, 18, 19, 24},
                {5, 10, 15, 20, 25}
        };

        assertArrayEquals(expected_5, square_5.mainDiagonalFlip(1).squarelotron);
    }

    /**
     * Testing the rotateRight method.
     */
    @Test
    public void testRotateRight() {
        int [][] expected_4 = new int [][] {
                {13, 9, 5, 1},
                {14, 10, 6, 2},
                {15, 11, 7, 3},
                {16, 12, 8, 4}
        };
        square_4.rotateRight(1);
        assertArrayEquals(expected_4, square_4.squarelotron);

        int [][] expected_5 = new int [][] {
                {5, 10, 15, 20, 25},
                {4, 9, 14, 19, 24},
                {3, 8, 13, 18, 23},
                {2, 7, 12, 17, 22},
                {1, 6, 11, 16, 21}
        };
        square_5.rotateRight(-1);
        assertArrayEquals(expected_5, square_5.squarelotron);

        int [][] expected_2 = new int [][] {
                {1, 2},
                {3, 4}
        };
        square_2.rotateRight(4);
        assertArrayEquals(expected_2, square_2.squarelotron);
    }
}