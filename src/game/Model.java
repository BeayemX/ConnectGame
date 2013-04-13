package game;

import java.util.ArrayList;

public class Model {

	private static final int MAX_PLAYER = 2;
	private static final int ROW_WIN_LENGTH = 4;
	
	private int curPlayer;
	private int[][] board;
	private ArrayList<ModelListener> modelListener = new ArrayList<ModelListener>();
	private ArrayList<Integer> AIPlayers= new ArrayList<Integer>();
	private boolean gameOver = false; 
	
	Model(int col, int row){
		this.board = new int[col][row];
		nextPlayer();
//		for(int i=0; i< col; ++i){
//			for(int j=0; j< row; ++j){
//				this.board[i][j] = 0;
//			}
//					
//		}
	}
	
	public void setDisc(int col, int row){
		System.out.println("Disc set at "+col+"|"+row);
		this.board[col][row] = curPlayer;
		updateListeners();
	}
	
	public int getDisc(int col, int row){
		return this.board[col][row];
	}	
	
	public void setPlayer(int id){
		updateListeners();
		this.curPlayer = id;
	}
	
	public int getCurrPlayer(){
		return this.curPlayer;
	}
	
	public ArrayList<Integer> getAIPlayers() {
		return AIPlayers;
	}

	public void setModelListener(ModelListener listener){
		modelListener.add(listener);
	}
	
	public void updateListeners(){
		for (ModelListener mL: modelListener){
			mL.update();
		}
	}
	
	public int getCols(){
		return board.length;
	}
	
	public int getRows(){
		return board[0].length;
	}
	
	public void nextPlayer(){
		if(curPlayer < MAX_PLAYER){
			++curPlayer;
		} else {
			curPlayer=1;
		}
	}
	
	public int getWinLength(){
		return this.ROW_WIN_LENGTH;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	public void setAIPlayer(int PlayerID){
		if( (PlayerID > 0) && (PlayerID <= this.MAX_PLAYER) ){
			AIPlayers.add(PlayerID);
		} else {
			System.out.println("PlayerID is out of possible Range");
		}
	}
	
	public int getMaxPlayers(){
		return this.MAX_PLAYER;
	}
}
