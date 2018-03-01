package sudoku.model;

/** An abstraction of Sudoku puzzle. */
public class Board {

    /** Size of this board (number of columns/rows). */
    public final int size;
    public int root = 0;
    
    /** Create a new board of the given size. */
    public Board(int size) {
        this.size = size;

        // WRITE YOUR CODE HERE ...
        this.root = (int)Math.sqrt(size);
    }

    /** Return the size of this board. */
    public int size() {
    	return size;
    }

    // WRITE YOUR CODE HERE ..
	private int[][] numA;
	
	public Board(){
		size = 9;
		numA = new int[9][9];
	}
	
	public boolean isValid(int[] place){
		int cx, cy, n;
		cx = place[0];
		cy = place[1];
		n = place[2];
		if (n > size || n < 1)
			return false;
		for (int i = 0; i < size; i++)
			if (n == numA[i][cx - 1])return false;
		for (int i = 0; i < size; i++)
			if (n == numA[cy - 1][i])return false;
	
		int x = (cx - 1)/root, y = (cy - 1)/root;
		for (int i = x*root;i<(x+1)*root;i++)
			for(int j = y*root;j<(y+1)*root;j++)
				if(n == numA[i][j])
					return false;
		return true;
	}
	
	public void placeNum(int[] place){
			if (isValid(place)){
				int x = place[0];
				int y = place[1];
				int num = place[2];
				numA[x - 1][y- 1] = num;
			}
	}

	public boolean isSolved() {
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++){
				if (numA[i][j] == 0)return false;
			}
		return true;
	}

}
