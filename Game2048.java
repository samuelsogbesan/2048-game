class Game2048{
  private final int size=4;

	public int[][] grid = new int[][]{
    {0,2,0,2},
    {0,2,0,0},
    {0,4,0,0},
    {0,2,0,2}
  };
	public int[][] getGrid(){return this.grid;}

	private void setValue(int value, int x, int y){
		this.getGrid()[x][y] = value;
	}
	
	public int getValue(int x, int y){return this.getGrid()[x][y];}

  public void print(){
    int value;
		for(int row=0; row<4 ; row++){
        for(int col=0; col<4 ; col++){
          value = this.getValue(row,col);
          System.out.print(value);
        }
        System.out.println("");
    }
  }

	public void shift(String direction){
    int rowOffset = 0;
    int colOffset = 0;

    switch(direction.toUpperCase()){
      case "UP":rowOffset = -1;break;
      case "DOWN":rowOffset = 1;break;
      case "LEFT":colOffset = -1;break;
      case "RIGHT":colOffset = 1;break;
    }
    
    for(int i =0; i<size-1;i++){
    for(int row=0; row<size;row++){

      for(int col=0; col<size;col++){

          
          if((rowOffset==1) && (row == size-1)){
            continue;
          }
          if((rowOffset==-1)&&(row ==0)){
            continue;
          }
          if((colOffset==1) && (col == size-1)){
            continue;
          }      
          if((colOffset==-1) && (col == 0)){
            continue;
          }

          int value = this.getValue(row,col);
          int nextValue = this.getValue(row+rowOffset,col+colOffset);

          if(nextValue==0){
            this.setValue(value,row+rowOffset,col+colOffset);
            this.setValue(nextValue,row,col);
          }
          else if(nextValue==value){
            this.setValue(value*2,row+rowOffset,col+colOffset);
            this.setValue(0,row,col);
          }             
          
      }

    }}
  }
	

	public boolean evaluate(){

		return true;
	}

}