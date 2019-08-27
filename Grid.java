public class Grid {
  private final int size = 4;

  public int[][] grid = new int[this.size][this.size];

  public Grid() {
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        this.grid[row][col] = 0;
      }

      this.grid[3][0] = 2;
      this.grid[3][1] = 2;
      this.grid[3][2] = 2;
      this.grid[3][3] = 2;
    }
    this.spawn();
  }

  public int[][] getGrid() {
    return this.grid;
  }

  private void setValue(int value, int x, int y) {
    this.getGrid()[x][y] = value;
  }

  public int getValue(int x, int y) {
    return this.getGrid()[x][y];
  }

  public void print() {
    int value;
    for (int row = 0; row < this.size; row++) {
      for (int col = 0; col < this.size; col++) {
        value = this.getValue(row, col);
        System.out.print(value);
      }
      System.out.println("");
    }
  }

  public int shift(String direction, boolean merge) {
    int score = 0;
    int rowOffset = 0;
    int colOffset = 0;

    switch (direction.toUpperCase()) {
    case "UP":
      rowOffset = -1;
      break;
    case "DOWN":
      rowOffset = 1;
      break;
    case "LEFT":
      colOffset = -1;
      break;
    case "RIGHT":
      colOffset = 1;
      break;
    default:
      System.out.println("Your input was invalid");
      return 0;
    }

    for (int i = 0; i < size - 1; i++) {
      for (int row = 0; row < size; row++) {

        for (int col = 0; col < size; col++) {

          if ((rowOffset == 1) && (row == size - 1)) {
            continue;
          }
          if ((rowOffset == -1) && (row == 0)) {
            continue;
          }
          if ((colOffset == 1) && (col == size - 1)) {
            continue;
          }
          if ((colOffset == -1) && (col == 0)) {
            continue;
          }

          int value = this.getValue(row, col);
          int nextValue = this.getValue(row + rowOffset, col + colOffset);

          if (nextValue == 0) {
            this.setValue(value, row + rowOffset, col + colOffset);
            this.setValue(nextValue, row, col);
          }

          if (merge) {
            if (nextValue == value) {
              if (i == size - 2) {
                score += (value * 2);
                this.setValue(value * 2, row + rowOffset, col + colOffset);
                this.setValue(0, row, col);
              }

            }
          }

        }

      }
    }

    return score;
  }

  private void spawn() {
    int value = (Math.random() < 0.9 ? 2 : 4);
    int[] pos = new int[2];
    pos[0] = (int) (Math.random() * (3 - 0) + 1);
    pos[1] = (int) (Math.random() * (3 - 0) + 1);

    while (this.getValue(pos[0], pos[1]) != 0) {
      pos[0] = (int) (Math.random() * (3 - 0) + 1);
      pos[1] = (int) (Math.random() * (3 - 0) + 1);
    }
    this.setValue(value, pos[0], pos[1]);
  }

  public boolean evaluate() {

    return true;
  }

  public void play(Player player) {

    System.out.println("Welcome to 2048.");
    this.print();
    System.out.println("LEFT/RIGHT/UP/ DOWN >>");

    String userInput;
    while ((userInput = player.getMove()) != "") {
      int score = this.shift(userInput, false);
      score = this.shift(userInput, true);
      this.shift(userInput, false);
      this.spawn();
      player.setScore(player.getScore() + score);
      this.print();
      System.out.println("Score:" + player.getScore());
      System.out.println("LEFT/RIGHT/UP/DOWN >>");
    }
    player.endGame();

  }
}