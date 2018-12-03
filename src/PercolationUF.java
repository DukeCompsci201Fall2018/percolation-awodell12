
public class PercolationUF implements IPercolate{
	
	private boolean[][] myGrid;
	int myOpenCount = 0;
	int mySize;
	IUnionFind myFinder = new QuickUWPC();
	int VTOP;
	int VBOTTOM;
	
	public PercolationUF(int size, IUnionFind finder) {
		mySize = size;
		VTOP = size*size;
		VBOTTOM = VTOP + 1;
		myGrid = new boolean[size][size];
		myFinder = finder;
		myFinder.initialize(size*size + 2);
	}
	
	@Override
	public void open(int row, int col) {
		// TODO Auto-generated method stub
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		if (myGrid[row][col] != true) {
			myGrid[row][col] = true; 
			myOpenCount ++;
			if (row == 0)
				myFinder.union(col, VTOP);
			if (row == mySize-1)
				myFinder.union(mySize * row + col, VBOTTOM);
			if (inBounds(row + 1, col) && myGrid[row + 1][col])
				myFinder.union(mySize * row + col, mySize * (row + 1) + col);
			if (inBounds(row - 1, col) && myGrid[row - 1][col])
				myFinder.union(mySize * row + col, mySize * (row - 1) + col);
			if (inBounds(row , col + 1) && myGrid[row][col + 1])
				myFinder.union(mySize * row + col, mySize * row + 1 + col);
			if (inBounds(row , col - 1) && myGrid[row][col - 1])
				myFinder.union(mySize * row + col, mySize * row - 1 + col);
		}

	}

	private boolean inBounds(int row, int col) {
		// TODO Auto-generated method stub
		if (row >= mySize  || col >= mySize  || row < 0 || col < 0)
			return false;
		return true;
	}

	@Override
	public boolean isOpen(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myGrid[row][col];
	}
	

	@Override
	public boolean isFull(int row, int col) {
		// TODO Auto-generated method stub
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myFinder.connected(VTOP, row* mySize + col);
	}

	@Override
	public boolean percolates() {
		// TODO Auto-generated method stub
		return myFinder.connected(VTOP, VBOTTOM);
	}

	@Override
	public int numberOfOpenSites() {
		// TODO Auto-generated method stub
		return myOpenCount;
	}

}
