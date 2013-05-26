package events.events;

import eventSystem.Event;

public class CellUpdateEvent extends Event {

	private int col;
	private int row;
	
	public CellUpdateEvent(int col, int row) {
		super("CellUpdateEvent");
		this.col = col;
		this.row = row;
	}

}
