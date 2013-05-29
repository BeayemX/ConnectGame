package game;

import java.util.ArrayList;

import eventSystem.Event;
import eventSystem.EventListener;
import eventSystem.events.CellUpdateEvent;

public class Model {

	private static final int 			MAX_PLAYER 			= 2;
	private static final int 			ROW_WIN_LENGTH 		= 4;
	
//	private CellState					currentPlayer;
//	private boolean 					currentPlayerIsAI;
	private CellState[][] 				board;
//	private ArrayList<CellState> 		AIPlayers			= new ArrayList<CellState>();
	
	
	Model(int col, int row){
		
		this.board = new CellState[col][row];

		for (int j=0; j<row; ++j){
			for(int i=0; i<col; ++i){
				this.board[i][j] = CellState.EMPTY;
			}
		}
//		nextPlayer();
		
	}
	
	public void setCell(int col, int row, CellState player){
		
//		TODO in view ausgeben
//		System.out.println("Disc set at "+col+"|"+row);
		this.board[col][row] = player;
		
		eventSystem.EventSystem.getInstance().queueEvent( new CellUpdateEvent(this.saveToMemento()));
		
	}
	
	public CellState getCell(int col, int row){
		
		return this.board[col][row];
		
	}	
	
//	public void setCurrentPlayer(CellState id){
//		
//		this.currentPlayer = id;
//		
//		if( this.isAIPlayer() ) {
//			this.currentPlayerIsAI = true;
//		} else {
//			this.currentPlayerIsAI = false;
//		}
//		
//		updateListeners();
//		
//	}
	
//	public boolean isCurrentPlayerAI() {
//		
//		return currentPlayerIsAI;
//		
//	}

//	public CellState getCurrentPlayer(){
//		
//		return this.currentPlayer;
//		
//	}
//	
//	public ArrayList<CellState> getAIPlayers() {
//		
//		return AIPlayers;
//		
//	}

	
	

	
	public int numOfColumns(){ 
		return board.length; 
	}
	
	public int numOfRows(){	
		return board[0].length;	
	}
	
//	public void nextPlayer(){
//		
//		if(getCurrentPlayer() < MAX_PLAYER){
//			this.setCurrentPlayer(getCurrentPlayer()+1);
//		} else {
//			this.setCurrentPlayer(1);
//		}
//		
//	}
	
	public int getWinLength(){
		return this.ROW_WIN_LENGTH;
	}

//	public boolean isGameOver() {
//		return gameOver;
//	}

//	public void setGameOver(boolean gameOver) {
//		this.gameOver = gameOver;		
//	}
	
//	public void setAIPlayer(int PlayerID){
//		
//		if( (PlayerID > 0) && (PlayerID <= this.MAX_PLAYER) ){
//			AIPlayers.add(PlayerID);
//		} else {
//			System.out.println("PlayerID is out of possible Range");
//		}
//		
//	}
	
	public int getMaxPlayers(){
		return this.MAX_PLAYER;
	}
	
//	public int[][] getBoard() {
//		return board;
//	}

//	private boolean isAIPlayer() {
//		
//		for( int id : this.getAIPlayers() ){
//			
//			// TODO waer's schneller wenn man die vorher in einer variable speichert, weil man dann nicht immer die methode aufrufen muss?
//			if(this.getCurrentPlayer() == id){
//				return true;
//			}
//		}
//	
//		return false;
//		
//	}
	
	public Memento saveToMemento(){

		// TODO erstellen
		MementoBoard memento = new MementoBoard(this.numOfColumns(), this.numOfRows());

		// FIXME richtige reihenfolge? oder rows zuerst?
		// FIXME passt das sonst?

		for(int row = 0; row < this.numOfRows(); ++row){
			for(int col = 0; col < this.numOfColumns(); ++col){
				memento.putCell(board[col][row]);
			}	
		}
		memento.seal();
		return memento;
	}
	
	public void restoreFromMemento(MementoBoard memento){

		CellState[][] board = new CellState[this.numOfColumns()][this.numOfRows()];
		for(int row = 0; row < this.numOfRows(); ++row){
			for(int col = 0; col < this.numOfColumns(); ++col){
				board[col][row] = memento.nextCell();;
			}	
		}
		
	}
	
}
