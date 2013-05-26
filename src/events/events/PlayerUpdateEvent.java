package events.events;

import eventSystem.Event;

public class PlayerUpdateEvent extends Event{

	private int x;
	private int y;
	
	public PlayerUpdateEvent(int x, int y) {
		super("PlayerUpdateEvent");
		this.x = x;
		this.y = y;
	}

}
