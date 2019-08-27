import java.util.Scanner;

class Main {

  public static void main(String args[]) {
    Grid game = new Grid();
    Player player = new Player("James");

    String userInput = player.getMove();
    Scanner input = new Scanner(System.in);

    System.out.println("Welcome to 2048.");
    game.print();
    System.out.println("LEFT/RIGHT/UP/DOWN >>");

    while ((userInput = input.next()) != null) {
      int score = game.shift(userInput);
      player.setScore(player.getScore() + score);
      game.print();
      System.out.println("Score:" + player.getScore());
      System.out.println("LEFT/RIGHT/UP/DOWN >>");
    }
    try {
      input.close();
    } catch (Exception e) {
      System.out.println("Error closing Scanner.");
      System.exit(-1);
    }
    System.exit(0);
  }

}