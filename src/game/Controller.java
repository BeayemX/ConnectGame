package game;

import eventSystem.Event;
import eventSystem.EventListener;

public class Controller implements EventListener {

//	private Model model; // kopier vom aktuellen spielfeld für ki?
//	private Evaluator evaluator = new Evaluator(this.model);
	
	public boolean update(int col) {

		if (isSetPossible(col)) {
			
			int row = getRow(col);
			model.setDisc(col, row);
			if( this.checkVictory( col, row ) ){
				this.gameOver();
			}
			
			this.model.nextPlayer();
			return true;
		} else {
			System.out.println("move isn't possible");
			return false;
		}
	}

	public boolean isSetPossible(int col) {
		return model.getDisc(col,0) == 0;
	}

	public int getRow(int col){
		for (int row = 1; row < model.getRows(); ++row){
			if (model.getDisc(col, row) != 0){
				return row-1;
			}
		}
		return model.getRows()-1; // if whole col is empty, return the lowest position
	}
	
	private boolean checkVictory(int col, int row){
		
		// we dont have to check the (col, row) because there has to be currentPlayer because he set the disc there
		if(
			checkHorizontal(col, row) || 
			checkVertical(col, row) ||
			checkDiagonalAscending(col, row) ||
			checkDiagonalDescending(col, row)	
		){
			gameOver();
		}
		
		return false;
	}

	private boolean checkHorizontal(int col, int row){
		
		int minCheck = col - model.getWinLength()-1;
		int maxCheck = col + model.getWinLength()-1;

		if( minCheck < 0 ){
			minCheck = 0;
		}
		
		if( maxCheck > model.getCols()-1 ){
			maxCheck = model.getCols()-1;
		}
		
		// check winLength-in-a-row
		int winCounter = 0;
		
		for( int i = minCheck; i <= maxCheck; ++i){
			
			if( model.getDisc(i, row) == model.getCurrentPlayer() ){
				++winCounter;
			} else {
				winCounter = 0;
			}
			
			if( winCounter == model.getWinLength() ){
				return true;
			}
			
		}
		return false;
	}
	
	private boolean checkVertical(int col, int row){
		// check downwards
		if(row + ( model.getWinLength()-1 ) < model.getRows()){
			if(
				model.getDisc(col, row+1) == model.getCurrentPlayer() && 
				model.getDisc(col, row+2) == model.getCurrentPlayer() &&
				model.getDisc(col, row+3) == model.getCurrentPlayer()  
			){
			return true;
			}
		}
		return false;
	}
	private boolean checkDiagonalAscending(int col, int row){ // diagonal-/
		
		int minCheck = col - model.getWinLength()-1;
		int maxCheck = col + model.getWinLength()-1;
		
		// check winLength-in-a-row
		int winCounter = 0;
		int x = 0;
		int y = 0;
		
		for( int i = minCheck; i <= maxCheck; ++i){
			x = col - i;
			y = row + i;
			
			if( isInsideBoard(x, y)){
				
				if( model.getDisc(x, y) == model.getCurrentPlayer() ){
					++winCounter;
				} else {
					winCounter = 0;
				}
				
				if( winCounter == model.getWinLength() ){
					return true;
				}
			}			
		}
		return false;
		
	}
	private boolean checkDiagonalDescending(int col, int row){ // diagonal-\
		int minCheck = col - model.getWinLength()-1;
		int maxCheck = col + model.getWinLength()-1;
		
		// check winLength-in-a-row
		int winCounter = 0;
		int x = 0;
		int y = 0;
		
		for( int i = minCheck; i <= maxCheck; ++i){
			x = col - i;
			y = row - i;
			
			if( isInsideBoard(x, y)){
				
				if( model.getDisc(x, y) == model.getCurrentPlayer() ){
					++winCounter;
				} else {
					winCounter = 0;
				}
				
				if( winCounter == model.getWinLength() ){
					return true;
				}
			}
		}
		return false;
	}
	
	private void gameOver(){
		System.out.println("Game over. Player " + model.getCurrentPlayer() + " won.");
		model.setGameOver(true);
	}
	
	private boolean isInsideBoard (int col, int row) {
		
		if (col >= 0 && row >= 0 && col < model.getCols() && row < model.getRows() ){
			return true;
		}
		
		return false;
	}
	
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
		
	}

	private void handleCellUpdateEvent(Event event) {
		
	}

	private void handleGameStatusUpdateEvent(Event event) {
		
	}
	
	private void handlePlayerUpdateEvent(Event event) {
		
	}

}
