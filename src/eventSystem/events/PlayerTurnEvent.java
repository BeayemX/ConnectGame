package eventSystem.events;

import eventSystem.Event;
import game.CellState;

public class PlayerTurnEvent extends Event {

	public static final String	TYPE = "PlayerTurn";
	private CellState 	player;
	private int 		turn;
			
	public PlayerTurnEvent(CellState player, int turn) {
		super(TYPE);
		this.player = player;
		this.turn = turn;
	}

//	public CellState getPlayer() {
//		return player;
//	}
	
	public CellState getPlayer() {
		return player;
	}
	
	public int getTurn() {
		return turn;
	}

}
