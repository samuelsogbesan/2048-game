import java.util.Scanner;

class Player {
	private String name;
	private int score;
	private Scanner input = new Scanner(System.in);

	public Player() {
		this.name = "none";
		this.score = 0;
	}

	public Player(String name) {
		this.name = name;
		this.score = 0;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int newScore) {
		this.score = newScore;
	}

	public String getMove() {
		return input.next();
	}

	public void endGame(){
		try {
			input.close();
		  } catch (Exception e) {
			System.out.println("Error closing Scanner.");
			System.exit(-1);
		  }		
	}
}