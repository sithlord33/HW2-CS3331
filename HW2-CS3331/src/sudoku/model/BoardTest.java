package sudoku.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {
	
	private Board board;
	
	@Before
	public void setUp(){
		board = new Board();
	}

	@Test
	public void testBoardSize() {
		assertEquals(9, board.size()); //default size
	}
	
	@Test
	public void testBoardSize2(){
		board = new Board(4);
		assertEquals(4, board.size());
	}
	
	@Test
	public void testValid(){
		board = new Board(9);
		assertTrue(board.isValid(0, 0, 9));
		assertTrue(board.isValid(1, 0, 8));
		assertTrue(board.isValid(2, 1, 7));
		assertTrue(board.isValid(3, 1, 6));
		assertTrue(board.isValid(4, 2, 5));
		assertTrue(board.isValid(5, 2, 4));
		assertTrue(board.isValid(6, 3, 3));
		assertTrue(board.isValid(7, 3, 2));
		assertTrue(board.isValid(8, 4, 1));
		assertTrue(board.isValid(0, 4, 9));
		assertTrue(board.isValid(1, 5, 8));
		assertTrue(board.isValid(2, 5, 7));
		assertTrue(board.isValid(3, 6, 6));
		assertTrue(board.isValid(4, 6, 5));
		assertTrue(board.isValid(5, 7, 4));
		assertTrue(board.isValid(6, 7, 3));
		assertTrue(board.isValid(7, 8, 2));
		assertTrue(board.isValid(8, 8, 1));
	}
	
	@Test
	public void testValid2(){
		board = new Board(4);
		assertTrue(board.isValid(0, 0, 1));
		assertTrue(board.isValid(0, 0, 2));
		assertTrue(board.isValid(0, 0, 3));
		assertTrue(board.isValid(0, 0, 4));
		assertTrue(board.isValid(0, 1, 1));
		assertTrue(board.isValid(0, 1, 2));
		assertTrue(board.isValid(0, 1, 3));
		assertTrue(board.isValid(0, 1, 4));
		assertTrue(board.isValid(1, 0, 1));
		assertTrue(board.isValid(1, 0, 2));
		assertTrue(board.isValid(1, 0, 3));
		assertTrue(board.isValid(1, 0, 4));
		assertTrue(board.isValid(1, 1, 1));
		assertTrue(board.isValid(1, 1, 2));
		assertTrue(board.isValid(1, 1, 3));
		assertTrue(board.isValid(1, 1, 4));
	}
	
	@Test
	public void testSolved(){
		board = new Board(4);
		assertFalse(board.isSolved());
		board.placeNum(0, 0, 1);
		assertFalse(board.isSolved());
		board.placeNum(0, 1, 2);
		assertFalse(board.isSolved());
		board.placeNum(0, 2, 3);
		assertFalse(board.isSolved());
		board.placeNum(0, 3, 4);
		assertFalse(board.isSolved());
		board.placeNum(1, 0, 4);
		assertFalse(board.isSolved());
		board.placeNum(1, 1, 3);
		assertFalse(board.isSolved());
		board.placeNum(1, 2, 2);
		assertFalse(board.isSolved());
		board.placeNum(1, 3, 1);
		assertFalse(board.isSolved());
		board.placeNum(2, 0, 3);
		assertFalse(board.isSolved());
		board.placeNum(2, 1, 4);
		assertFalse(board.isSolved());
		board.placeNum(2, 2, 1);
		assertFalse(board.isSolved());
		board.placeNum(2, 3, 2);
		assertFalse(board.isSolved());
		board.placeNum(3, 0, 2);
		assertFalse(board.isSolved());
		board.placeNum(3, 1, 1);
		assertFalse(board.isSolved());
		board.placeNum(3, 2, 4);
		assertFalse(board.isSolved());
		board.placeNum(3, 3, 3);
		assertTrue(board.isSolved());
	}

}
