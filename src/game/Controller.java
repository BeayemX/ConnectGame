package game;

import eventSystem.Event;
import eventSystem.EventListener;
import eventSystem.EventSystem;
import eventSystem.events.CellUpdateEvent;
import eventSystem.events.GameStatusUpdateEvent;
import eventSystem.events.PlayerUpdateEvent;

public class Controller  {

	private static final EventSystem eventSystem = EventSystem.getInstance();
	private Model model;
	int cellsToConnect;;
//	private Evaluator evaluator = new Evaluator(this.model);
	
	Controller(Model model){
		this(model, 4);
	}
	Controller(Model model, int cellsToConnect){
		this.model = model;
		this.cellsToConnect = cellsToConnect;
	}
	
//	public boolean update(int col) {
//		if (isSetPossible(col)) {
//			
//			int row = getRowDepth(col);
//
//			model.setCell(col, row);
//			eventSystem.queueEvent( new CellUpdateEvent(model.saveToMemento()));
//
////			if( this.checkVictory( col, row ) ){
////			if( this.isWinning(currentplayer)){ // aber woher?
//				/// XXX throw event
////				this.gameOver();
////			}
//			// XXX throw event
////			this.model.nextPlayer();
//			return true;
//		} else {
//			// XXX throw event?
//			System.out.println("move isn't possible");
//			return false;
//		}
//	}

	public boolean isSetPossible(int col) {
		// XXX ... iwie muss ich da auf die logik zugreifen... ODER  model als member?
		return model.getCell(col,0) == CellState.EMPTY;
	}

	// get lowest possible position in the specific col
	public int getRowDepth(int col){
		// XXX Memento?
		for (int row = 0; row < model.numOfRows(); ++row){
			if (model.getCell(col, row) != CellState.EMPTY){
//				FIXME warum -1? weil bei check horizontal muss ich wieder +1
				return row-1;
			}
		}
//		FIXME warum -1? weil bei check horizontal muss ich wieder +1
		return model.numOfRows()-1; // if whole col is empty, return the lowest position
	}
	
	private boolean isWinning(CellState player, int col, int row){
		// we dont have to check the (col, row) because there has to be currentPlayer because he set the disc there
		if(
			checkHorizontal	(player, col, row)			|| 
			checkVertical	(player, col, row) 			||
			checkDiagonalAscending	(player, col, row) 	||
			checkDiagonalDescending	(player, col, row)	
		){
			eventSystem.queueEvent(new GameStatusUpdateEvent(Status.OVER));
		}
		
		return false;
	}

	private boolean checkHorizontal(CellState player, int col, int row){

		int minCheck = col - (model.getWinLength()-1);
		int maxCheck = col + (model.getWinLength()-1);

		if( minCheck < 0 ){
			minCheck = 0;
		}
		
		if( maxCheck > model.numOfColumns()-1 ){
			maxCheck = model.numOfColumns()-1;
		}
	
		// check winLength-in-a-row
		int winCounter = 0;
		
		for( int i = minCheck; i <= maxCheck; ++i){
			// FIXME warum row+1? problem: er erkentn die ganz oberste reihe nicht mehr... weil -1
			if( model.getCell(i, row+1) == player ){
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
	
	private boolean checkVertical(CellState player, int col, int row){

//		TODO das muesste doch auch funktionieren wenn man das aktuell gesetzen feld nicht ueberprueft, weil da
//		ohnehin der aktuelle spieler drin steht...
//		dann koennte man auch mode.getwinlength()-1 verwenden, oder?
		
		// check downwards
		if(row + ( model.getWinLength() ) < model.numOfRows()){
			
			if(
				model.getCell(col, row+1) == player && 
				model.getCell(col, row+2) == player && 
				model.getCell(col, row+3) == player &&
				model.getCell(col, row+4) == player  
			){
				return true;
			}
		}
		return false;
	}
	private boolean checkDiagonalAscending(CellState player, int col, int row){ // diagonal-/
		
		// check winLength-in-a-row
		int winCounter = 0;
		int x = 0;
		int y = 0;
		
		for( int i = -model.getWinLength()-1; i < model.getWinLength(); ++i){
			
			x = col + i;
			// FIXME wtf?? i+1??
			y = row - i + 1;
		
			if( isInsideBoard(x, y)){
	
				
				if( model.getCell(x, y) == player ){
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
	private boolean checkDiagonalDescending(CellState player, int col, int row){ // diagonal-\

		// check winLength-in-a-row
		int winCounter = 0;
		int x = 0;
		int y = 0;
		
		for( int i = -model.getWinLength()-1; i < model.getWinLength(); ++i){
			
			x = col + i;
			// FIXME wtf?? i+1??
			y = row + i + 1;
	
			if( isInsideBoard(x, y)){

				
				if( model.getCell(x, y) == player ){
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
	
//	private void gameOver(){
//		System.out.println("Game over. Player " + model.getCurrentPlayer() + " won.");
//		model.setGameOver(true);
//	}
	
	private boolean isInsideBoard (int col, int row) {
		
		if (col >= 0 && row >= 0 && col < model.numOfColumns() && row < model.numOfRows() ){
			return true;
		}
		
		return false;
	}
	
	public void insertDisc(int col, CellState player){
		
		if(!isFull(col)){
			// XXX getrowdepth in tmp_var speichern damit nicht 2x aufrufen?
			model.setCell(col, getRowDepth(col), player );
			this.isWinning(player, col, getRowDepth(col));
		} 
		if ( isFull() ){
			EventSystem.getInstance().queueEvent(new GameStatusUpdateEvent(Status.DRAW));
		}
	}
	
	public void removeDisc(int col){
		// TODO die oberste entfernen
	}
	
	public boolean isEmpty(int col){
		return isEmpty(col, model.numOfRows()-1); // check the lowest position of the column
	}
	
	public boolean isEmpty(int col, int row){
		return model.getCell(col, row) == CellState.EMPTY;
	}
	
	public boolean isFull(){
		
		for( int i=0; i<model.numOfColumns(); ++i ) {
			if( !isFull(i) )
				return false;
		}
		return true;
	}
	
	public boolean isFull(int col){
		return !isEmpty(col,0); // topmost position is NOT empty
	}
	
	public int countConnected(CellState state, int col, int row, int dcol, int drow){
		// TODO method not used
		// dcol, drow?...????
		// FIXME return wert is immer -1
		return -1;
		
		
	}
}
