package eventSystem.events;

import eventSystem.Event;
import game.CellState;

public class PlayerUpdateEvent extends Event {

	public static final String TYPE = "PlayerUpdate";
	private CellState player;

	public PlayerUpdateEvent(CellState player) {
		super(TYPE);
		this.player = player;
	}

	public CellState getPlayer() {
		return this.player;
	}

}
