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
		// die methode is ultra crap
		// exception bei eingabe von buchstaben oder NULL
		System.out.println("Row? " + model.getPlayer() +": ");
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		// TODO use numbers >9. ... -48 --> ugly
		int col = input.read() - 48;
		

		for (ViewListener l : listener){
			l.update(col);
		}
	}
}
