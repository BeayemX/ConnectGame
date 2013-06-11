package eventSystem.events;

import eventSystem.Event;
import game.Memento;
import game.MementoBoard;

public class CellUpdateEvent extends Event {

	public static final String TYPE = "CellUpdate";
	private Memento memento;

	public CellUpdateEvent(Memento memento) {
		super(TYPE);
		this.memento = memento;
	}

	public Memento getMemento() {
		return this.memento;
	}
}
