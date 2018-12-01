import java.util.LinkedList;
import java.util.Queue;

public class PercolationBFS extends PercolationDFSFast{

	public PercolationBFS(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}
@Override
protected void dfs(int row, int col) {
	Queue<Integer> qu = new LinkedList<Integer>();
	myGrid [row][col] = FULL;
	int size = myGrid.length;
	qu.add(row*myGrid.length + col);
	while (qu.size() != 0) {
		Integer i = qu.remove();
		int rrow = i/size;
		int ccol = i % size;
		if (inBounds(rrow + 1, ccol) && isOpen(rrow +1, ccol) && ! isFull(rrow +1, ccol)) {
			qu.add(i + size);
			myGrid[rrow + 1][ccol] = FULL;
		}
		else if(inBounds(rrow - 1, ccol) && isOpen(rrow -1, ccol) && ! isFull(rrow -1, ccol)) {
			qu.add(i - size);
			myGrid[rrow - 1][ccol] = FULL;
		}
		else if(inBounds(rrow, ccol + 1) && isOpen(rrow, ccol + 1) && ! isFull(rrow, ccol + 1)) {
			qu.add(i + 1);
			myGrid[rrow ][ccol + 1] = FULL;
		}
		else if(inBounds(rrow, ccol - 1) && isOpen(rrow, ccol - 1) && ! isFull(rrow, ccol - 1)) {
			qu.add(i - 1);
			myGrid[rrow ][ccol - 1] = FULL;
		}
	}
}
}
