package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import eventSystem.Event;
import eventSystem.EventListener;
import eventSystem.EventSystem;
import eventSystem.events.CellUpdateEvent;
import eventSystem.events.GameStatusUpdateEvent;
import eventSystem.events.PlayerTurnEvent;
import eventSystem.events.PlayerUpdateEvent;

public class ConsoleView implements EventListener {
	
	private CellState player;
	
//	public ConsoleView(CellState player) {
//		this.player = player;
//	}
	
	public void printBoard(MementoBoard memento) {
//		
//		System.out.print("     ");
//		for(int i=0;i<.numOfColumns();i++){
//			System.out.print(i+ " ");
//		}
//		System.out.println();
//		System.out.println();
//		for (int row = 0; row < model.numOfRows(); ++row) {
//
//			System.out.print(row + ":   ");
//			
//			for (int col = 0; col < model.numOfColumns(); ++col) {
//				
//				if(model.getCell(col, row) != 0){
//					System.out.print(model.getCell(col, row));
//				} else {
//					System.out.print("_");
//				}
//				System.out.print(" ");
//			}
//			System.out.println();
//		}
//
//		System.out.println();
		for(int i = 0; i < memento.getCol(); ++i){
			System.out.print(i + " ");	
		}
		System.out.println();
		for(int row = 0; row < memento.getRow(); ++row){
			for(int col = 0; col < memento.getCol(); ++col){
				String icon = "";
				switch(memento.nextCell()){
				case EMPTY:
					icon = "_";
					break;
				case PLAYER_ONE:
					icon = "1";
					break;
				case PLAYER_TWO:
					icon = "2";
					break;
				default:
					icon = "x";
					break;
				
				}
				System.out.print( icon + " ");
			}
			System.out.println();
		}
	}
	
	public void getInput() throws IOException{
		// FIXME wenn AI auf voll besetzte col wirft, bleibt der wert und er hängt in einer endlosschleife. 
		int col=0;
		
//		System.out.println("Row? " + model.getCurrentPlayer() +": ");
		System.out.println("Row? ");
		
//		boolean invalidInput = true;
		
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		
//		if( !model.isCurrentPlayerAI() ){			
//			while (invalidInput) {
				
				String line = console.readLine();
				
				try {
					
					col = Integer.parseInt(line);
//					if (col >= 0 && col < model.numOfColumns()){
//						invalidInput = false;
//					}
					
				} catch (Exception e) {
//					invalidInput = true;
					col = 0;
				}
//			}
//		}
		EventSystem.getInstance().queueEvent(new PlayerTurnEvent(this.player, col));
	}
	
	@Override
	public void handleEvent(Event event) {

		switch ( event.getType() ){
		
			case "GameStatusUpdate":
//				System.out.println("gamestatusupdate");
	    		this.handleGameStatusUpdateEvent( (GameStatusUpdateEvent) event );
	    		break;
    		
			case "CellUpdate":
//				System.out.println("view: cellupdate");
	    		this.handleCellUpdateEvent( (CellUpdateEvent) event );
	    		break;
	    		
		    case "PlayerUpdate":
//		    	System.out.println("view: playerUpdate");
	    		this.handlePlayerUpdateEvent( (PlayerUpdateEvent) event );
	    		break;
	    		
    		default: 
//    			System.out.println(event.getType() + " hasn't been handled in ConsoleView!");
    			break;
		      
		}
		
	}

	private void handleCellUpdateEvent(CellUpdateEvent event) {
		// neu zeichnen / darstellen
		this.printBoard((MementoBoard)event.getMemento());
		
	}

	private void handleGameStatusUpdateEvent(GameStatusUpdateEvent event) {
		
//		print game over
		if(event.getStatus() == Status.OVER){
			System.out.println("game over");
			// player x won...
		}
		
	}
	
	private void handlePlayerUpdateEvent(PlayerUpdateEvent event)  {
		// spieler wechseln anzeigen / bzw das ma auf input wartet
		this.player = event.getPlayer();
		// XXX grindig
		try {
			this.getInput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
