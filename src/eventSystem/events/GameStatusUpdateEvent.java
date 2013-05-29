package eventSystem.events;

import eventSystem.Event;
import game.CellState;
import game.Status;

public class GameStatusUpdateEvent extends Event {

	public static final String TYPE = "GameStatusUpdate";
	private CellState player;
	private Status status;
	
	
	public GameStatusUpdateEvent(Status status) {
		super(TYPE);
		this.status = status;
	}
	
	public Status getStatus() {
		return status;
	}
}
