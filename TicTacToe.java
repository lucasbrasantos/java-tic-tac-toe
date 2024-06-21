import java.util.Scanner;

public class TicTacToe {

    static String bgChar = "-";
    static String[] board = {bgChar, bgChar, bgChar, bgChar, bgChar, bgChar, bgChar, bgChar, bgChar};

    static final Scanner input = new Scanner(System.in);

    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void pressEnterToContinue() {
        System.out.println("Press Enter to continue...");
        input.nextLine();        
    }

    static void printExBoard() {
        System.out.println("Choose a position from 1-9 as shown below:");
        for (int i = 1; i <= 9; i++) {
            System.out.printf(" [%d] ", i);
            if (i % 3 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    static void printBoard() {
        System.out.println("Current board state:");
        for (int i = 1; i <= 9; i++) {
            System.out.printf(" [%s] ", board[i-1]);
            if (i % 3 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    static void inputMove(String player) {        

        while (true) {
            System.out.printf("Player %s, choose a position from 1-9: ", player);
            int pos = input.nextInt() - 1;

            if (pos < 0 || pos >= 9) {
                System.out.println("Invalid position. Try again.");
            } else if (!board[pos].equals(bgChar)) {
                System.out.println("That position is already claimed. Try again.");
            } else {
                board[pos] = player;
                break;
            }
        }
    }

    static int checkGameOver() {
        int[][] winCombinations = {
            {0, 1, 2}, // Row 1
            {3, 4, 5}, // Row 2
            {6, 7, 8}, // Row 3
            {0, 3, 6}, // Column 1
            {1, 4, 7}, // Column 2
            {2, 5, 8}, // Column 3
            {0, 4, 8}, // Diagonal 1
            {2, 4, 6}  // Diagonal 2
        };

        for (int[] combo : winCombinations) {
            if (
                !board[combo[0]].equals(bgChar) &&
                board[combo[0]].equals(board[combo[1]]) &&
                board[combo[1]].equals(board[combo[2]])
            ) {
                return 1; // is a win
            }
        }

        boolean isBoardFull = true;
        for (String cell : board) {
            if (cell.equals(bgChar)) {
                isBoardFull = false;
                break;
            }
        }

        if (isBoardFull) {
            return 2; // is a tie
        }

        return 0; // continue game
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Tic Tac Toe!");
        printExBoard();
        
        String currentPlayer = "X";
        boolean gameover = false;
        pressEnterToContinue();
        while (!gameover) {
            clearScreen();
            printBoard();
            inputMove(currentPlayer);
            clearScreen();
            printBoard();
            int result = checkGameOver();
            
            switch (result) {
                case 0:
                    System.out.println("No winner yet.");
                    currentPlayer = currentPlayer.equals("X") ? "O" : "X";
                    break;
                case 1:
                    System.out.println("Congratulations, Player " + currentPlayer + "! You won!");
                    gameover = true;
                    break;
                case 2:
                    System.out.println("It's a tie! Well played, both players.");
                    gameover = true;
                    break;
            }
        }

        System.out.println("Game over. Thank you for playing!");
    }
}
