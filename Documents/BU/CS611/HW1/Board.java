public class Board {
    private char[] board;
    private int size;
    private int totalPosition;

    public Board(int size) {
        this.size = size;
        this.board = new char[size*size];
        this.totalPosition = size * size;
        createBoard();
    }

    private void createBoard() {
        for (int i = 0; i < size*size; i++) {
                board[i] = ' ';
        }
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int pos = i * size + j;
                System.out.print(board[pos]);
                if (j < size - 1)
                    System.out.print("|");
            }
            System.out.println();
            if (i < size - 1) {
                for (int x = 0; x < size; x++) {
                    System.out.print("-");
                    if (x<size-1){
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
    }

    public boolean checkBoardFull() {
        return totalPosition == 0;
    }

    public int getSize(){
        return size;
    }

    public char[] getBoard(){
        return board;
    }
}