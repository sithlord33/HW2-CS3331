package sudoku.dialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import sudoku.model.Board;

/**
 * A special panel class to display a Sudoku board modeled by the
 * {@link sudoku.model.Board} class. You need to write code for
 * the paint() method.
 *
 * @see sudoku.model.Board
 * @author Yoonsik Cheon
 */
@SuppressWarnings("serial")
public class BoardPanel extends JPanel {
	
	private int cx = -1;
	private int cy = -1;
    
	public interface ClickListener {
		
		/** Callback to notify clicking of a square. 
		 * 
		 * @param x 0-based column index of the clicked square
		 * @param y 0-based row index of the clicked square
		 */
		void clicked(int x, int y);
	}
	
    /** Background color of the board. */
	private static final Color boardColor = new Color(255, 255, 255);

    /** Board to be displayed. */
    private Board board;

    /** Width and height of a square in pixels. */
    private int squareSize;

    /** Create a new board panel to display the given board. */
    public BoardPanel(Board board, ClickListener listener) {
        this.board = board;
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	int xy = locateSquaree(e.getX(), e.getY());
            	if (xy >= 0) {
            		listener.clicked(xy / 100, xy % 100);
            	}
            }
        });
    }

    /** Set the board to be displayed. */
    public void setBoard(Board board) {
    	this.board = board;
    	repaint();
    }
    
    /**
     * Given a screen coordinate, return the indexes of the corresponding square
     * or -1 if there is no square.
     * The indexes are encoded and returned as x*100 + y, 
     * where x and y are 0-based column/row indexes.
     */
    private int locateSquaree(int x, int y) {
    	if (x < 0 || x > board.size * squareSize
    			|| y < 0 || y > board.size * squareSize) {
    		return -1;
    	}
    	int xx = x / squareSize;
    	int yy = y / squareSize;
    	return xx * 100 + yy;
    }

    /** Draw the associated board. */
    @Override
    public void paint(Graphics g) {
        super.paint(g); 

        // determine the square size
        Dimension dim = getSize();
        squareSize = Math.min(dim.width, dim.height) / board.size;

        // draw background
        final Color oldColor = g.getColor();
        g.setColor(boardColor);
        int size = (squareSize * board.size);
        g.fillRect(0, 0, size, size);

        // WRITE YOUR CODE HERE ...
        // i.e., draw grid and squares.
        g.setColor(Color.GRAY);
        for (int i = 1; i < size / board.size; i++ ){
        	g.drawLine(i * squareSize, 0, i * squareSize, size);
        	g.drawLine(0, i * squareSize, size, i * squareSize);
        }
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, size, size);
        for (int i = 1; i < board.size; i++ ){
        	//g.setStroke(new BasicStroke(3));
        	g.drawLine(i * squareSize * board.root, 0, i * squareSize * board.root, size);
        	g.drawLine(0, i * squareSize * board.root, size, i * squareSize * board.root);
        }
        g.setColor(new Color(135, 206, 250));
        g.fillRect(cx * squareSize + 2, cy * squareSize + 2, squareSize - 3, squareSize - 3);
        
        for (int i = 0; i < board.size; i++){
        	for (int j = 0; j < board.size; j++){
        		if (board.getNum(i, j) != 0){
        			g.setColor(Color.BLACK);
        			if (board.size == 4){
        				g.setFont(new Font("Arial", Font.PLAIN, 48));
        				g.drawString(String.valueOf(board.getNum(i, j)), (i * squareSize) + squareSize / 2 - 12, (j * squareSize) + squareSize / 2 + 17);
        			}
        			else {
        				g.setFont(new Font("Arial", Font.PLAIN, 15));
        				g.drawString(String.valueOf(board.getNum(i, j)), (i * squareSize) + squareSize / 2 - 3, (j * squareSize) + squareSize / 2 + 6);
        			}
        		}
        	}
        }
    }
    
    public void clicked(int cx, int cy){
    	this.cx = cx;
    	this.cy = cy;
    	repaint();
    	//paint(this.getGraphics());
    }
    
    /* Delete number if X is clicked. Place number otherwise and if it's valid */
    public void insert(int num){
    	if (num == 0)
    		board.deleteNum(cx, cy);
    	else
    		board.placeNum(cx, cy, num);
    	repaint();
    }
}
