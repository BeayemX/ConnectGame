package game;

import java.io.IOException;

public class MVC_Test {

	public static void main(String[] args) throws IOException {
		
		Model m = new Model(7, 5);
		ConsoleView v = new ConsoleView(m);
		Evaluator e = new Evaluator();
		Controller c = new Controller(m, v, e);
		AiView aiV = new AiView(m);
		

		System.out.println(m.getWinLength() + " in a Row");
		
		// register views inside model
		m.setModelListener(v); 		// console view
		m.setModelListener(aiV); 	// ai view
		
		// register controller inside view
		v.setListener(c);
		aiV.setListener(c);
		
		v.printBoard();
		
		while( !m.isGameOver() ){
			v.getInput();
		}
	}

}
