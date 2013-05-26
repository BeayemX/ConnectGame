package events.events;

import eventSystem.Event;

public class GameStatusUpdateEvent extends Event {

	private boolean gameOver = false;
	
	public GameStatusUpdateEvent(boolean gameOver) {
		super("GameStatusUpdateEvent");
		this.gameOver = gameOver;
	}

}
