import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Board {
    public static final int SIZE = 6;
    private static final int[][] DIRS = {{-1,-1}, {-1,0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    private static final int O = 1;
    private static final int X = 2;
    private static final int COVERED = 3;

    private final int[][] grid = new int[SIZE][SIZE];

    public Board makeMove(Location location, boolean firstPlayerToMove) {
        if (grid[location.row][location.col] != 0) {
            return null;
        }
        Board copy = new Board();
        for (int i = 0; i<SIZE; i++) {
            for (int j = 0; j<SIZE; j++) {
                copy.grid[i][j] = grid[i][j];
            }
        }
        copy.grid[location.row][location.col] = firstPlayerToMove ? O : X;
        for (int[] d : DIRS ) {
            if (location.row + d[0] < SIZE && location.row + d[0] >= 0 &&
                    location.col + d[1] < SIZE && location.col + d[1] >= 0) {
                if (copy.grid[location.row + d[0]][location.col + d[1]] == 0) {
                    copy.grid[location.row + d[0]][location.col + d[1]] = COVERED;
                }
            }
        }
        return copy;
    }

    public int evaluate(boolean firstPlayerToMove) {
        if (getPossibleMoves().isEmpty()) {
            return firstPlayerToMove ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
        return 0;
    }

    public Collection<Location> getPossibleMoves() {
        Collection<Location> result = new ArrayList<>();
        for (int i = 0; i<SIZE; i++) {
            for (int j = 0; j<SIZE; j++) {
                if (grid[i][j] == 0) {
                    result.add(new Location(i, j));
                }
            }
        }
        return result;
    }

    public static class Location {
        int row;
        int col;

        Location(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "(" + row + "," + col + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Location location = (Location) o;
            return row == location.row && col == location.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    public boolean isOver() {
        return getPossibleMoves().isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i<SIZE; i++) {
            for(int j = 0; j<SIZE; j++) {
                builder.append("[");
                switch (grid[i][j]) {
                    case 0:
                        builder.append(" ");
                        break;
                    case 1:
                        builder.append("O");
                        break;
                    case 2:
                        builder.append("X");
                        break;
                    case 3:
                        builder.append("/");
                        break;
                    default:
                        throw new IllegalStateException();
                }
                builder.append("]");
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }
}
