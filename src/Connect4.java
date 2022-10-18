import java.util.Scanner;

public class Connect4 {
    private static final int MAX_TURNS = 42;
    public static void printBoard(String[][] board) {
        System.out.println("Board:");
        System.out.println("1 2 3 4 5 6 7");
        System.out.println("-------------");
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void placeChip(String[][] board, int row, String player) {
        for (int i = board.length - 1; i != -1; i--){
            if (board[i][row].equals(" ")){
                board[i][row] = player;
                break;
            }
        }
    }

    public static boolean checkForWinner(String[][] board, String player){
        boolean gameOver = false;
        // horizontal
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length - 3; j++){
                if (board[i][j].equals(player) && board[i][j+1].equals(player) && board[i][j+2].equals(player) && board[i][j+3].equals(player)) {
                    gameOver = true;
                }
            }
        }

        // vertical
        for (int i = 0; i < board.length - 3; i++){
            for (int j = 0; j < board[i].length; j++){
                if (board[i][j].equals(player) && board[i+1][j].equals(player) && board[i+2][j].equals(player) && board[i+3][j].equals(player)) {
                    gameOver = true;
                }
            }
        }

        // down diagonal
        for (int i = 0; i < board.length - 3; i++){
            for (int j = 0; j < board[i].length - 3; j++){
                if(board[i][j].equals(player) && board[i+1][j+1].equals(player) && board[i+2][j+2].equals(player) && board[i+3][j+3].equals(player)) {
                    gameOver = true;
                }
            }
        }

        // up diagonal
        for (int i = 3; i < board.length; i++){
            for (int j = 0; j < board[i].length - 3; j++){
                if(board[i][j].equals(player) && board[i-1][j+1].equals(player) && board[i-2][j+2].equals(player) && board[i-3][j+3].equals(player)) {
                    gameOver = true;
                }
            }
        }

        return gameOver;
    }

    public static void main(String[] args) {
        // Using Scanner for Getting Input from User
        Scanner in = new Scanner(System.in);

        // crate board and initialize each spot to "O" (empty)
        String[][] board = new String[6][7];
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                board[i][j] = " ";
            }
        }

        boolean gameOver = false;
        String player = "1";
        int turns = 1;
        // continues until there is a winner or a tie (full board)
        while (!gameOver && turns <= MAX_TURNS) {
            printBoard(board);

            // current player enters row number to place chip
            int selectedRow = -1;
            do {
                System.out.print("Player " + player + ", enter number between 1-7 to place chip: ");
                String input = in.nextLine();

                // if the input is anything other that 1-7, it is not accepted
                if (input.equals("0") || input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5") || input.equals("6") || input.equals("7")){
                    selectedRow = Integer.parseInt(input) - 1;

                    // if the selected row is already full, set selected row back to -1 (incorrect input flag)
                    if (!board[0][selectedRow].equals(" ")){
                        System.out.println("Row already full");
                        selectedRow = -1;
                    }
                }
                else {
                    System.out.println("Incorrect Input.");
                }

            } while (selectedRow == -1);


            // spacing
            System.out.println();

            // places chip in the board
            placeChip(board, selectedRow, player);

            // checks for a winner only after the
            gameOver = checkForWinner(board, player);

            // changes the turn and adds one to total turns if the game isn't over
            if (!gameOver) {
                if (player.equals("1")) {
                    player = "2";
                }
                else {
                    player = "1";
                }
                turns++;
                System.out.println("Turns: " + turns);
            }
        }

        // prints board in finished state
        printBoard(board);

        // display the winner, or if it's a tie
        if (turns >= MAX_TURNS) {
            System.out.println("It's a tie!");
        }
        else {
            System.out.println("Player " + player + " wins!");
        }
    }
}
