import java.util.Scanner;

public class GameSelect {
    public static int chooseGame(){
        Scanner kb = new Scanner(System.in);
        int choice;

        System.out.println("Hi welcome, please choose the game you want to play:"
            +"\n1. Tic Tac Toe"
            +"\n2. Order and Chaos" 
            + "\n3. Exit");

        while(true){
            choice = kb.nextInt();
            if(choice == 1 || choice == 2 || choice == 3){
                break;
            }else{
                System.out.println("Invalid choice. Please select again.");
            }
        }

        return choice;
    }
}
