package Homeworks.SD1x.squarelotron;

import java.util.Arrays;

/**
 * Squarelotron is a class that implements a physical
 * squarelotron as a two-dimensional array of integers.
 * It also implements methods to modify the squarelotron.
 */
public class Squarelotron {
    public int[][] squarelotron;
    public int size;

    /**
     * This fills the 2-dimensional array with the
     * numbers 1 to n squared in order. It also sets
     * the size instance variable to be n.
     * @param n The size of the 2-dimensional array
     */
    public Squarelotron(int n){
        this.size = n;
        this.squarelotron = new int[size][size];
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                squarelotron[i][j] = i * size + j + 1;
            }
        }
    }

    /**
     * This performs the Upside-Down Flip of the
     * squarelotron. Leaves the original squarelotron
     * unmodified.
     * @param ring An integer representing the ring to
     *            flip upside-down
     * @return A new squarelotron in the flipped state of
     * the original squarelotron
     */
    public Squarelotron upsideDownFlip(int ring) {
        Squarelotron other = new Squarelotron(this.size);
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if(i == ring - 1 || i == size - ring || j == ring - 1 || j == size - ring){
                    other.squarelotron[i][j] = this.squarelotron[size - i - 1][j];
                }
                else{
                    other.squarelotron[i][j] = this.squarelotron[i][j];
                }
            }
        }
        return other;
    }

    /**
     * Thes performs the Main Diagonal Flip of the
     * squarelotron.
     * @param ring An integer representing the ring to
     *             flip through the main diagonal
     * @return A new squarelotron in the flipped state of
     * the original squarelotron.
     */
    public Squarelotron mainDiagonalFlip(int ring) {
        Squarelotron other = new Squarelotron(this.size);

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (i == ring - 1 || i == size - ring || j == ring - 1 || j == size - ring) {
                    other.squarelotron[i][j] = this.squarelotron[j][i];
                }
                else {
                    other.squarelotron[i][j] = this.squarelotron[i][j];
                }
            }
        }
        return other;
    }

    /**
     * This performs a rotation of 90 degrees on the
     * squarelotron. It modifies the original
     * squarelotron.
     * @param numberOfTurns Integer representing the
     *                      number of rotations in
     *                      clockwise (> 0) or
     *                      anticlockwise (< 0)
     */
    public void rotateRight(int numberOfTurns) {
        for (int n = 0; n < Math.abs(numberOfTurns); n++) {
            Squarelotron other = new Squarelotron(this.size);
            for (int i = 0; i < this.size; i++) {
                for (int j = 0; j < this.size; j++) {
                    if (numberOfTurns > 0) {
                        other.squarelotron[j][this.size - 1 - i] = this.squarelotron[i][j];
                    }
                    else {
                        other.squarelotron[i][j] = this.squarelotron[j][this.size - 1 - i];
                    }
                }
            }
            this.squarelotron = Arrays.copyOf(other.squarelotron, other.size);
        }
    }
}
