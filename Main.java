class Main{

	public static void main(String args[]){
		Game2048 game = new Game2048();
		Player player = new Player("James");

		String userInput = player.getMove();

		while((userInput == "RIGHT") && (game.evaluate()) ){
      game.print();
      System.out.println("\n");

      game.shift("left");            
      game.print();     
      System.out.println("\n");
      game.shift("down");            
      game.print();
            System.out.println("\n");
      userInput = "LEFT";
		}
    System.exit(0);
	}
}