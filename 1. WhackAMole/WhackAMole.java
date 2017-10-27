import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class WhackAMole {

    // Private instance variables
    int score = 0;
    int molesLeft = 0;
    int attemptsLeft;
    char[][] moleGrid;

    /**The constructor for the game. It specifies the number
     * of whacks allowed, and the grid dimension.*/
    WhackAMole(int numAttempts, int gridDimension) {
        attemptsLeft = numAttempts;
        moleGrid = new char [gridDimension][gridDimension];
        for (char [] aMoleGrid : moleGrid) {
            Arrays.fill(aMoleGrid, '*');
        }
    }

    /** Given a location, we place a mole at that location.
     * We then return true if we can. We also update the
     * number of moles left.*/
    boolean place(int x, int y) {
        try {
            if (moleGrid[x][y] == '*') {
                moleGrid[x][y] = 'M';
                molesLeft += 1;
                return true;
            } else {
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    /** Given a location, take a whack to that location.
     * If that location contains a mole, the score, number
     * of attemptsLeft, and molesLeft is updated. If that
     * location does not contain a mole, only attemptsLeft
     * is updated.*/
    void whack(int x, int y) {
        if (moleGrid[x][y] == 'M') {
            moleGrid[x][y] = 'W';
            System.out.println("A whack at " + x + "," + y + " !");
            printGrid();
            score += 1;
            attemptsLeft -= 1;
            molesLeft -= 1;
            System.out.println("Score: " + score);
            System.out.println("Moles left: " + molesLeft);
        } else {
            System.out.println("Ooops! You missed at " + x + "," + y + ".");
            attemptsLeft -= 1;
        }
        System.out.println("Attempts left: " + attemptsLeft + "\n\n");
    }

    /**Prints the grid without showing where the moles are.
     * For every spot that has recorded a "whacked mole",
     * print out a W, or * otherwise.*/
    void printGridToUser() {
        for (char[] aMoleGrid : moleGrid) {
            for (char anAMoleGrid : aMoleGrid) {
                if (anAMoleGrid == 'W') {
                    System.out.print(anAMoleGrid);
                } else {
                    System.out.print('*');
                }
            }
            System.out.print("\n");
        }
    }

    /**Prints the grid completely. This is effectively
     * dumping the 2d array on the screen. The places where
     * the moles are, are printed as 'M'. The places where
     * the moles have been whacked are printed as 'W'. The
     * places that don't have a mole are printed as '*'.*/
    void printGrid() {
        for (char[] aMoleGrid : moleGrid) {
            for (char anMoleGrid: aMoleGrid) {
                System.out.print(anMoleGrid + " ");
            }
            System.out.print("\n");
        }
    }


    public static void main(String[] args) {
        // Initializing the game by a 10 x 10 grid and
        // 50 attempts.
        WhackAMole moleGame = new WhackAMole(50, 10);

        // Randomly place 10 moles.
        Random rand = new Random();
        while (moleGame.molesLeft < 10) {
            int x = rand.nextInt(10);
            int y = rand.nextInt(10);
            moleGame.place(x, y);

            // To cheat, uncomment the line below.
            // System.out.println("Placed: " + x + "," + y);
        }

        System.out.println("You have a maximum of 50 attempts left.");

        // Start whacking, all the while ensuring attempts
        // left are greater than zero.
        while (moleGame.attemptsLeft > 0 && moleGame.molesLeft > 0) {
            Scanner scan = new Scanner(System.in).useDelimiter(",|\\s+");
            System.out.println("Enter the comma or space separated x and y coordinates of where to whack.");
            // Accept integer input delimited by a comma
            // or one or more spaces
            if (scan.hasNext()) {
                int x = scan.nextInt();
                int y = scan.nextInt();

                if (x < 0 || x > 9 || y < 0 || y > 9) {
                    if (x == -1 && y == -1) {
                        System.out.println("You gave up :-D");
                        moleGame.printGrid();
                    } else {
                        System.out.println("Wrong coordinates, try again.");
                    }
                } else {
                    moleGame.whack(x, y);
                }
            }
        }

        if (moleGame.attemptsLeft == 0) {
            System.out.println("Game over. No attempts left.");
        } else if (moleGame.molesLeft == 0) {
            System.out.println("Congratulations. You won!");
            moleGame.printGrid();
        }
    }
}


