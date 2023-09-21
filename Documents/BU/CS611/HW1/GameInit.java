import java.util.Scanner;

public class GameInit {
    public static void GameInit(int choice){
        GameManager game;
        int boardSize;

        if(choice == 1){
            while(true){
                if (boardSize>= 3){
                    game = new TicTacToe(new Board(boardSize));
                }else{

                }
            }
        }
    }

    private static int getBoardSize(){
        Scanner kb = new Scanner(System.in);
        int boardSize;
        System.out.print("Enter the size of the board you want "
                        +"to play on. (For example 3 for board 3x3)");
        

        return boardSize;
    }
}
