import java.util.Scanner;

class Main{

	public static void main(String args[]){
		Game2048 game = new Game2048();
		Player player = new Player("James");

		String userInput = player.getMove();
    Scanner input = new Scanner(System.in);

    System.out.println("Welcome to 2048.");
    game.print();
    System.out.println("LEFT/RIGHT/UP/DOWN >>");


		while((userInput=input.next()) != null){
      game.shift(userInput);  
      game.print();
      System.out.println("LEFT/RIGHT/UP/DOWN >>");
		}
    System.exit(0);
	}
  
}