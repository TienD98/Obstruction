import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Tester {
    enum SearchType {
        MM,
        AB;
    }

    private static final String OUTPUT_FILENAME = "Readme.txt";

    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            String[] parts = scanner.nextLine().split("\\s+");
            int aiPlayer = Integer.parseInt(parts[0]);
            SearchType st = SearchType.valueOf(parts[1]);

            Board board = new Board();
            int currPlayer = 1;
            while(!board.isOver()) {
                System.out.println(board);
                if (currPlayer == aiPlayer) {
                    Board.Location chosen = Solver.findMove(board, currPlayer == 1, st == SearchType.AB);
                    System.out.println("Move " + chosen + " is chosen");
                    board = board.makeMove(chosen, currPlayer == 1);
                }
                else {
                    Board.Location chosen = null;
                    while(chosen == null) {
                        try {
                            System.out.println("Input your move: ");
                            String[] locs = scanner.nextLine().split("\\s+");
                            if (locs.length != 2) {
                                throw new IllegalArgumentException();
                            }
                            int r = Integer.parseInt(locs[0]);
                            int c = Integer.parseInt(locs[1]);
                            Board.Location loc = new Board.Location(r, c);
                            if (board.getPossibleMoves().contains(loc)) {
                                chosen = loc;
                            }
                            else {
                                throw new IllegalArgumentException();
                            }
                        }
                        catch (Exception e) {
                            System.out.println("Invalid input");
                        }
                    }
                    System.out.println("Move " + chosen + " is chosen");
                    board = board.makeMove(chosen, currPlayer == 1);
                }
                currPlayer = 3 - currPlayer;
            }

            System.out.println(board);
            System.out.println("Player " + currPlayer + " lost");
        }
    }
}
