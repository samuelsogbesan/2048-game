class Main {

  public static void main(String args[]) {
    Grid game = new Grid();
    Player player = new Player("James");
    game.play(player);
    System.exit(0);
  }

}