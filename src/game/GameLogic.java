package game;

import eventSystem.Event;
import eventSystem.EventListener;
import eventSystem.EventSystem;
import eventSystem.events.CellUpdateEvent;
import eventSystem.events.GameStatusUpdateEvent;
import eventSystem.events.PlayerTurnEvent;
import eventSystem.events.PlayerUpdateEvent;

public class GameLogic implements EventListener{
	
	private EventSystem eventSystem = EventSystem.getInstance();
	private Model 		model;
	private Controller 	controller;
	private CellState 	currentPlayer;
	private Status 		endOfGame = Status.ONGOING;
	
	
	GameLogic (Model model, Controller controller){
		this.model 		= model;
		this.controller	= controller; 
	}

//	public MementoBoard getMemento(){
//		return new MementoBoard();
//	}

	
	public void startNewGame(){
		fireCellUpdate();
		firePlayerUpdate();
		// TODO erstellen
	}
	
	@Override
	public void handleEvent(Event event) {

		switch ( event.getType() ){
		
		    case "PlayerTurn":
		    	System.out.println("logic: player turn");
	    		this.handlePlayerTurnEvent( (PlayerTurnEvent) event );
	    		break;
	    		
    		default: 
//    			System.out.println(event.getType() + " hasn't been handled in GameLogic!");
    			break;
		      
		}
		
	}

	private void handlePlayerTurnEvent(PlayerTurnEvent event) {
		// zug der gemacht werden will
		controller.insertDisc(event.getTurn(), event.getPlayer() );
		firePlayerUpdate();
	}
	
	private void fireCellUpdate(){		
		Memento tmpMemento = model.saveToMemento();
		this.eventSystem.queueEvent(new CellUpdateEvent(tmpMemento));
	}
	
	private void firePlayerUpdate(){

		this.currentPlayer = (this.currentPlayer == CellState.PLAYER_ONE) ? CellState.PLAYER_TWO : CellState.PLAYER_ONE;
		System.out.println("aaaa"+currentPlayer);
		this.eventSystem.queueEvent(new PlayerUpdateEvent(currentPlayer));
	}
	private void fireWon(int player){
		// TODO welcher spieler? bzw passt das schon so?
		
		// passt das so?
		this.endOfGame = Status.OVER;
		
		this.eventSystem.queueEvent(new GameStatusUpdateEvent(Status.OVER));
	}

	public boolean isEndOfGame() {
		
		return this.endOfGame == Status.OVER;
	}
	
}
