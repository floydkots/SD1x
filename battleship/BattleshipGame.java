package Homeworks.SD1x.battleship;
import java.io.IOException;
import java.util.*;

public class BattleshipGame {
    private Ocean ocean;

    public BattleshipGame() {
        ocean = new Ocean();
    }

    public static void main (String[] args) {
        BattleshipGame game = new BattleshipGame();
        System.out.println("Welcome to BattleShip\n\n");
        System.out.println("To shoot, enter the semi-colon-separated coordinates " +
                            "like 1, 1; 0,3; 7,3; 9,11; 12, 17\n" +
                            "Each coordinate must be a pair of Integers");
        System.out.println("Note that only 5 shots are accepted.");
        while (!game.ocean.isGameOver()) {
            game.ocean.print();
            System.out.println("\nEnter the 5 coordinates to shoot.\n");
            Scanner input = new Scanner(System.in);
            String[] points = input.nextLine().split(";");
            ArrayList<int[]> enteredPoints = new ArrayList<>();
            for (String point : points) {
                String[] rowCol = point.split(",");
                try {
                    int row = Integer.parseInt(rowCol[0].trim());
                    int col = Integer.parseInt(rowCol[1].trim());
                    enteredPoints.add(new int[] {row, col});
                }
                catch (NumberFormatException e) {
                    System.out.println(point + " is not a pair of integers. re-enter the 5 points.");
                    enteredPoints.clear();
                }
            }

            for (int[] point: enteredPoints) {
                game.ocean.shootAt(point[0], point[1]);
            }

            System.out.println("\nTotal Hits: " + game.ocean.getHitCount());
            System.out.println("Total Shots Fired: " + game.ocean.getShotsFired());
            System.out.println("Ships Sunk: " + game.ocean.getShipsSunk());
        }

        System.out.println("\n\nCongratulations!! You won!!");
    }
}
