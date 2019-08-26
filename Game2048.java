class Game2048{

	public int[][] grid = new int[][]{
    {2,2,2,2},
    {3,3,3,3},
    {4,4,4,4},
    {5,5,5,5}
  };
	public int[][] getGrid(){return this.grid;}

	private void setValue(int value, int x, int y){
		this.getGrid()[x][y] = value;
	}
	
	public int getValue(int x, int y){return this.getGrid()[x][y];}

	public void shift(int direction){
		for(int row=0; row<4 ; row++){
        for(int col=0; col<4 ; col++){
          System.out.print(this.getValue(row, col)+ " ");
        }
        System.out.println("");
    }
	}

	public boolean evaluate(){

		return true;
	}

}