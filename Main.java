class Main{

	public static void main(String args[]){
		Game2048 game = new Game2048();
		Player player = new Player("James");

		String userInput = player.getMove();

		while((userInput == "RIGHT") && (game.evaluate()) ){
      game.shift(1);
      userInput = "LEFT";
		}
    System.exit(0);
	}
}