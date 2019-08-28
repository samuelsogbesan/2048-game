import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Encapsulates main game logic.
 */
public class Grid {
  private final int size = 4;
  private ArrayList<int[]> available = new ArrayList<int[]>();
  public int[][] grid = new int[this.size][this.size];

  /**
   * Initialises grid with zeros and spawns first number onto grid.
   */
  public Grid() {
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        this.grid[row][col] = 0;
        this.available.add(new int[]{row,col});
      }
      
    }

    /*
    this.grid[0] = new int[]{2,2,2,2};
    this.grid[1] = new int[]{4,4,8,4};
    this.grid[2] = new int[]{2,16,4,2};
    this.grid[3] = new int[]{0,0,2,0};
    */
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

  /**
   * Iterates through grid and prints elements row-by-row
   */
  public void print() {
    int value;
    for (int row = 0; row < this.size; row++) {
      for (int col = 0; col < this.size; col++) {
        value = this.getValue(row, col);
        System.out.print(value+"\t");
      }
      System.out.println("");
    }
  }

  /**
   * Main game logic.
   * Shifts grid values one unit in a given direction (no-wrap)
   * @param direction
   */
  public int shift(String direction, boolean merge) {
    int score = 0; //total score accumulated by player on turn
    int rowOffset = 0; //amount items will be shifted on the x-axis
    int colOffset = 0; //amount items will be shifted on the y-axis

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

    /*
      Performs the shift in the specified direction by:
        For each element in grid (bar the exceptions)
          Shift element into new position if the adjacent element is a 0
          otherwise
          If instructed to merge
            Compare and if qualified merge items
    */
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
          int nextValue = this.getValue(row + rowOffset, col + colOffset); //value of adjacent element

          if (nextValue == 0) {
            this.setValue(value, row + rowOffset, col + colOffset);
            this.setValue(nextValue, row, col);
            this.available.add(new int[]{row,col});
            this.available.remove(new int[]{row+rowOffset,col+colOffset});            

          }

          if (merge) {
            if (nextValue == value) {
              if (i == size - 2) {
                score += (value * 2);
                this.setValue(value * 2, row + rowOffset, col + colOffset);
                this.setValue(0, row, col);
                this.available.add(new int[]{row,col});
                this.available.remove(new int[]{row+rowOffset,col+colOffset}); 
              }

            }
          }

        }

      }
    }

    return score;
  }

  /**
   * Adds a random element of value 2 or 4 to the grid at a random unoccupied position.
   * value = f(x) = {x < 0.9 ? f(x) = 2, otherwise f(x) = 4}
   */
  private void spawn() {
    int value = (Math.random() < 0.9 ? 2 : 4);
    
    
    //int[] pos = new int[2];

    int pos;
    if(this.available.size() > 0){
      pos =(int) (Math.random() * (available.size()-1-0)+1);
    }else{
      System.out.println("no free items");
      return;
    }


    //pos[0] = (int) (Math.random() * (3 - 0) + 1);
    //pos[1] = (int) (Math.random() * (3 - 0) + 1);
    
    /*
    while (this.getValue(pos[0], pos[1]) != 0) {
      pos[0] = (int) (Math.random() * (3 - 0) + 1);
      pos[1] = (int) (Math.random() * (3 - 0) + 1);
    }*/
    this.setValue(value, this.available.get(pos)[0], this.available.get(pos)[1]);
    this.available.add(pos, new int[]{-1,-1});
  }

  public boolean evaluate() {
    if(this.available.size() !=0){
      return true;
    }
    for (int row = 0; row < size; row++) {

      //convert int[] to String
      String c = "";
      for (int j = 0; j < size; j++) {
        c = c.concat(String.valueOf(this.grid[row][j]));
      }

      //System.out.println("string: " + c);

      //If there are repeated non-zero values in row
      String regex = "^(?!.*(.).*\1)[1-9]+$";
      //regex = "([1-9]{1})(?(1)\1|)";
      //regex= "([1-9]{1})(?=\\1{1})";
      //regex="([0-9])(?=\1)";
      //regex="[a-z](?=[0-9])";
      regex="([0-9].*?)(?:\\1)";
      
      
      
      Pattern p = Pattern.compile(regex);
      Matcher m = p.matcher(c);
      if (m.find()){
        //System.out.println("The string matches regex1");
        return true;
      }
     
    }

    //System.out.println("no strings matched regex");
    return false;
    
  }


  /**
   * Main Game Loop
   * @param player The Player Object neaged in the game
   */
  public void play(Player player) {

    System.out.println("Welcome to 2048, a Java clone of 2048 made by Samuel Sogbesan");
    System.out.println("GitHub: https://github.com/samuelsogbesan/2048-game");    
    System.out.println("Linkedin: https://github.com/samuelsogbesan/2048-game"); 

    this.print();
    System.out.println("LEFT/RIGHT/UP/ DOWN >>");
    //this.evaluate();
    
    
    String userInput;
    while ((this.evaluate()))  {
      userInput = player.getMove();
      int score = this.shift(userInput, false);
      System.out.println("good");
      score = this.shift(userInput, true);
      System.out.println("merge good");
      this.shift(userInput, false);
      System.out.println("suffle good");
      this.spawn();
      System.out.println("spawn good");
      player.setScore(player.getScore() + score);
      this.print();
      System.out.println("Score:" + player.getScore());
      System.out.println("LEFT/RIGHT/UP/DOWN >>");
    }
    
    System.out.println("Game over");
    player.endGame();

  }
}