package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ConsoleView extends View {
	
	public ConsoleView(Model m){
		super(m);
	}

	public void update() {
		this.printBoard();
	}

	public void printBoard() {

		System.out.print("     ");
		for(int i=0;i<model.getCols();i++){
			System.out.print(i+ " ");
		}
		System.out.println();
		System.out.println();
		for (int row = 0; row < model.getRows(); ++row) {

			System.out.print(row + ":   ");
			
			for (int col = 0; col < model.getCols(); ++col) {
				
				if(model.getDisc(col, row) != 0){
					System.out.print(model.getDisc(col, row));
				} else {
					System.out.print("_");
				}
				System.out.print(" ");
			}
			System.out.println();
		}

		System.out.println();

	}
	
	public void getInput() throws IOException{
		System.out.println("Row? " + model.getCurrentPlayer() +": ");
		
		int col=-1;
		boolean invalidInput = true;
		
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		
		if( this.isAIPlayer() ){
//			TODO da den evaluator? ka...
//			Evaluator.4
			col = -1;

		} else {
			
			while (invalidInput) {
				
				String line = console.readLine();
				
				try {
					
					col = Integer.parseInt(line);
					if (col >= 0 && col < model.getCols()){
						invalidInput = false;
					}
					
				} catch (Exception e) {
					invalidInput = true;
				}
			}
		}
		
		for (ViewListener l : listener){
			l.update(col);
		}
	}

	private boolean isAIPlayer() {
		
		for( int id : model.getAIPlayers() ){
			
			// TODO wär's schneller wenn man die vorher in einer variable speichert, weil man dann nicht immer die methode aufrufen muss?
			if(model.getCurrentPlayer() == id){
				return true;
			}
		}
		
		return false;
	}
}
