package connectfour;

import java.util.*;

public class ConnectFour {

    public static void main(String[] args) {
        ConnectFour game = new ConnectFour();
        String t = "    Connect Four";
        Scanner kb = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("Choose colour to go first, RED(1) or YELLOW(2)");
            int x = kb.nextInt();
            if (x == 1) {
                choice = 1;
            } else if (x == 2) {
                choice = 2;
            }
        } while (choice == 0);
        int coordinate;
        do {
            int turn = choice;
            game.displayGrid(t);
            System.out.println(game.playerName(choice) + " choose a column");
            coordinate = kb.nextInt();
            choice = game.addAndFindWinner(turn, coordinate);
        } while (game.determineWinner(choice, coordinate) == NOPLAYER);
        game.displayGrid(t);
        System.out.println(game.playerName(choice) + " is the winner!");
    }

    public static final int NUMCOLUMNS = 7; 
    public static final int NUMROWS = 6; 
    public static final int NOPLAYER = 0;
    public static final int RED = 1;
    public static final int YELLOW = 2;

    private int[][] grid; 
    private int[] columnHeight;
    private int previousPlayer;

    public ConnectFour() {
        previousPlayer = NOPLAYER;
        grid = new int[NUMCOLUMNS][NUMROWS];
        columnHeight = new int[NUMCOLUMNS];

        for (int col = 0; col != NUMCOLUMNS; col++) {
            for (int row = 0; row != NUMROWS; row++) {
                grid[col][row] = NOPLAYER;
            }
        }
        for (int i = 0; i != NUMCOLUMNS; i++) {
            columnHeight[i] = 0;
        }
    } 

    public int addAndFindWinner(int player, int col) {
        System.out.println("Add " + playerName(player) + " to " + col);
        assert (player == RED || player == YELLOW) && player != previousPlayer;
        assert 0 <= col && col < NUMCOLUMNS;
        assert columnHeight[col] != NUMROWS;
        previousPlayer = player;
        int topFreeRow = columnHeight[col];
        grid[col][topFreeRow] = player;
        columnHeight[col]++;
        int winner = determineWinner(player, col);
        if (previousPlayer == RED) {
            player = YELLOW;
        } else {
            player = RED;
        }
        if (winner == NOPLAYER) {
            return player;
        } else {
            return winner;
        }
    } 

    public void displayGrid(String title) {
        System.out.println(title);
        System.out.println("    0 1 2 3 4 5 6");
        int cellValue;
        for (int row = NUMROWS - 1; row != -1; row--) {
            System.out.print(row + " ");
            System.out.print("  ");
            for (int col = 0; col != NUMCOLUMNS; col++) {
                cellValue = grid[col][row];
                if (cellValue == RED) {
                    System.out.print("R ");
                } else if (cellValue == YELLOW) {
                    System.out.print("Y ");
                } else {
                    System.out.print("_ ");
                }
            }
            System.out.println();
        }
    }

    private int numVertical(int player, int col) {
        assert player == RED || player == YELLOW;
        assert 0 <= col && col < NUMCOLUMNS;
        int numMatching = 0;
        int r = columnHeight[col] - 1;     
        while (r >= 0 && grid[col][r] == player) {
            numMatching++;
            r--;
        } 
        return numMatching;
    } 

    private int numForwardDiagonal(int player, int col) {
        assert player == RED || player == YELLOW;
        assert 0 <= col && col < NUMCOLUMNS;
        int numMatching = 0;
        int count = 0;
        int r = columnHeight[col] - 1;
        int c = col;
        while (c >= 0 && r >= 0 && grid[c][r] == player) {
            count++;
            c--;
            r--;
        } 
        c = col;
        r = columnHeight[col] - 1;
        while (c < NUMCOLUMNS && r < NUMROWS && grid[c][r] == player) {
            count++;
            c++;
            r++;
        } 
        numMatching = count - 1; 
        return numMatching;
    }

    private int numBackwardDiagonal(int player, int col) {
        assert player == RED || player == YELLOW;
        assert 0 <= col && col < NUMCOLUMNS;
        int numMatching = 0;
        int count = 0; 
        int r = columnHeight[col] - 1;
        int c = col;
        while (c >= 0 && r < NUMROWS && grid[c][r] == player) {
            count++;
            c--;
            r++;
        } 
        c = col;
        r = columnHeight[col] - 1;
        while (c < NUMCOLUMNS && r < NUMROWS && r >= 0 && grid[c][r] == player) {
            count++;
            c++;
            r--;
        } 
        numMatching = count - 1;
        return numMatching;
    }
    
    private int numHorizontal(int player, int col) {
        assert player == RED || player == YELLOW;
        assert 0 <= col && col < NUMCOLUMNS;
        int numMatching = 0;
        int count = 0; 
        int r = columnHeight[col] - 1;
        int c = col;
        while (c >= 0 && grid[c][r] == player) {
            count++;
            c--;
        }
        c = col;
        while (c < NUMCOLUMNS && grid[c][r] == player) {
            count++;
            c++;
        } 
        numMatching = count - 1;
        return numMatching;
    } 

    private int determineWinner(int player, int col) {
        int winner = NOPLAYER;
        int numVerticalMatching, numHorizontalMatching, numForwardMatching, numBackwardMatching;
        assert player == RED || player == YELLOW;
        assert 0 <= col && col < NUMCOLUMNS;
        numVerticalMatching = this.numVertical(player, col);
        if (numVerticalMatching >= 4) {
            winner = player;
        } else {
            numHorizontalMatching = this.numHorizontal(player, col);
            if (numHorizontalMatching >= 4) {
                winner = player;
            } else {
                numForwardMatching = this.numForwardDiagonal(player, col);
                if (numForwardMatching >= 4) {
                    winner = player;
                } else {
                    numBackwardMatching = this.numBackwardDiagonal(player, col);
                    if (numBackwardMatching >= 4) {
                        winner = player;
                    }
                }
            }
        }
        return winner;
    }

    public String playerName(int player) {
        assert player == NOPLAYER || player == RED || player == YELLOW;
        String name = "";
        if (player == NOPLAYER) {
            name = "No player";
        } else if (player == RED) {
            name = "Red";
        } else if (player == YELLOW) {
            name = "Yellow";
        }
        return name;
    }
}