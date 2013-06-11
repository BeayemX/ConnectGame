package game;

import java.util.ArrayList;

public class MementoBoard implements Memento {

	private ArrayList<CellState> states = new ArrayList<CellState>();
	private int pos;
	private boolean sealed;
	private int col;
	private int row;

	// public void setDisc(int col, int row){
	//
	// }
	//
	// public void removeDisc(int col, int row){
	//
	// }

	public MementoBoard(int col, int row) {
		this.col = col;
		this.row = row;

	}

	@Override
	public void reset() {
		this.pos = 0;
	}

	@Override
	public void putCell(CellState state) {
		if (this.sealed) {
			throw new IllegalStateException("sealed memento cannot be altered");
		}
		this.states.add(state);
	}

	@Override
	public CellState nextCell() {
		return this.states.get(this.pos++);
	}

	@Override
	public void seal() {
		this.sealed = true;
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}
}