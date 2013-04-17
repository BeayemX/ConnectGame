package game;

import java.io.IOException;

public class MVC_Test {

	public static void main(String[] args) throws IOException {
		
		Model m = new Model(7, 5);
		ConsoleView consV = new ConsoleView(m);
		AiView aiV = new AiView(m);
		Evaluator e = new Evaluator(m);
		Controller c = new Controller(m, consV, e);
		
		m.setAIPlayer(2);
		

		System.out.println(m.getWinLength() + " in a Row");
		
		// register views inside model
		m.setModelListener(consV); 		// console view
		m.setModelListener(aiV); 	// ai view
		
		// register controller inside view
		consV.setListener(c);
		aiV.setListener(c);
		
		consV.printBoard();
		
		while( !m.isGameOver() ){
			consV.getInput();
// 			c.wait for input
//			v.send input
		}
	}

}
