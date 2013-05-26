package game;

import eventSystem.Event;
import eventSystem.EventListener;

public abstract class View implements EventListener{
	
	@Override
	public void handleEvent(Event event) {

		switch ( event.getType() ){
		
			case "GameStatusUpdateEvent":
	    		this.handleGameStatusUpdateEvent( event );
	    		break;
    		
			case "CellUpdateEvent":
	    		this.handleCellUpdateEvent( event );
	    		break;

		    case "PlayerTurnEvent":
	    		this.handlePlayerTurnEvent( event );
	    		break;
	    		
		    case "PlayerUpdateEvent":
	    		this.handlePlayerUpdateEvent( event );
	    		break;
	    		
    		default: 
    			System.out.println(event.getType() + " hasn't been handled!");
    			break;
		      
		}
		
	}

	private void handlePlayerTurnEvent(Event event) {
		// schreiben player x is dran
	}

	private void handleCellUpdateEvent(Event event) {
		// darstellen
		
	}

	private void handleGameStatusUpdateEvent(Event event) {
		
//		print game over
		
	}
	
	private void handlePlayerUpdateEvent(Event event) {
		
	}
		
}
