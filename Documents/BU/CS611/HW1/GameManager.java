public abstract class GameManager {
    protected int size;
    protected Board board;

    public GameManager(Board board){
        this.board = board;
        this.size = board.getSize();
    }

    public boolean makeMove(int row, int col, char player) {
        int position = row * size + col;
        if (row < 0 || row >= size || col < 0 || 
            col >= size || board.getBoard()[position] != ' ') {
            return false;
        }
        board.getBoard()[position] = player;
        return true;
    }

    public abstract char checkWin();

    public boolean isBoardFull(){
        return board.checkBoardFull();
    }

    public void printBoard(){
        board.printBoard();
    }

}
