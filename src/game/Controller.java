package game;

public class Controller implements ViewListener {

	private Model model;
	// TODO brauch ich das nicht?
//	private View view;
	private Evaluator evaluator;

	public Controller(Model m, View v, Evaluator e){
		this.model = m;
//		this.view = v;
		this.evaluator = e;
	}
	
	@Override
	public boolean update(int col) {
		if (setIsPossible(col)) {
			
			int row = getRow(col);
			model.setDisc(col, row);
			
			if( this.checkVictory( col, row ) ){
				this.gameOver();
			}
			
			model.nextPlayer();
			return true;
		} else {
			System.out.println("move isn't possible");
			return false;
		}
	}

	public boolean setIsPossible(int col) {
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
			
			if( model.getDisc(i, row) == model.getPlayer() ){
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
				model.getDisc(col, row+1) == model.getPlayer() && 
				model.getDisc(col, row+2) == model.getPlayer() &&
				model.getDisc(col, row+3) == model.getPlayer()  
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
				
				if( model.getDisc(x, y) == model.getPlayer() ){
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
				
				if( model.getDisc(x, y) == model.getPlayer() ){
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
		System.out.println("Game over. Player " + model.getPlayer() + " won.");
		model.setGameOver(true);
	}
	
	private boolean isInsideBoard (int col, int row) {
		
		if (col >= 0 && row >= 0 && col < model.getCols() && row < model.getRows() ){
			return true;
		}
		
		return false;
	}

}
