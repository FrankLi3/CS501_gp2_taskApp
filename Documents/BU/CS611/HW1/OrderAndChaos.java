public class OrderAndChaos extends GameManager {

    public OrderAndChaos(Board board) {
        super(board);
    }

    @Override
    public char checkWin() {
        char[] boardTemp = board.getBoard();

        // Check for Order (O) win
        if (checkPattern(boardTemp, 'O')) {
            return 'O'; // Order (O) wins
        }

        // Check for Chaos (X) win
        if (checkPattern(boardTemp, 'X')) {
            return 'X'; // Chaos (X) wins
        }

        // Check for a draw
        if (isBoardFull()) {
            return 'D'; // It's a draw
        }

        return ' '; // No win or draw yet
    }

    private boolean checkPattern(char[] boardTemp, char player) {
        // Check rows and columns for a winning pattern
        for (int i = 0; i < size; i++) {
            if (checkRow(boardTemp, player, i) || checkColumn(boardTemp, player, i)) {
                return true;
            }
        }

        // Check diagonals for a winning pattern
        return checkDiagonal(boardTemp, player);
    }

    private boolean checkRow(char[] boardTemp, char player, int row) {
        for (int col = 0; col < size; col++) {
            if (boardTemp[row * size + col] != player) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(char[] boardTemp, char player, int col) {
        for (int row = 0; row < size; row++) {
            if (boardTemp[row * size + col] != player) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonal(char[] boardTemp, char player) {
        // Check top-left to bottom-right diagonal
        boolean diagonal1 = true;
        for (int i = 0; i < size; i++) {
            if (boardTemp[i * size + i] != player) {
                diagonal1 = false;
                break;
            }
        }

        // Check top-right to bottom-left diagonal
        boolean diagonal2 = true;
        for (int i = 0; i < size; i++) {
            if (boardTemp[i * size + (size - 1 - i)] != player) {
                diagonal2 = false;
                break;
            }
        }

        return diagonal1 || diagonal2;
    }
}
