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
        numA = new int[size][size];
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
	
	public boolean isValid(int cx, int cy, int n){
		if (n > size || n < 0)
			return false;
		for (int i = 0; i < size; i++)
			if (n == numA[cx][i])return false;
		for (int i = 0; i < size; i++)
			if (n == numA[i][cy])return false;
	
		int x = cx / root, y = cy / root;
		for (int i = x * root; i < (x + 1) * root; i++)
			for(int j = y * root; j < (y + 1) * root; j++)
				if(n == numA[i][j])
					return false;
		return true;
	}
	
	public void placeNum(int x, int y, int n){
		if (isValid(x, y, n))
			numA[x][y] = n;
	}

	public boolean isSolved() {
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++){
				if (numA[i][j] == 0)return false;
			}
		return true;
	}
	
	public int getNum(int x, int y){
		return numA[x][y];
	}
	
	public void deleteNum(int x, int y){
		numA[x][y] = 0;
	}
}
