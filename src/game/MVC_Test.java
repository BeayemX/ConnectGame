package game;

import java.io.IOException;

import eventSystem.EventSystem;

public class MVC_Test {

	public static void main(String[] args) throws IOException {
		
		EventSystem eventSystem = EventSystem.getInstance();
		
		Model 		model		= new Model(7, 5);
		ConsoleView consoleView	= new ConsoleView();
//		AiView 		aiView 		= new AiView(m);
//		Evaluator 	evaluator	= new Evaluator(m);
		Controller 	controller 	= new Controller();

		eventSystem.addListener(model);
		eventSystem.addListener(consoleView);
		eventSystem.addListener(controller);
		
		
		
		
		
		
		
		
//		m.setAIPlayer(2);
		

		System.out.println(model.getWinLength() + " in a Row");
		
//		// register views inside model
//		m.setModelListener(consV); 		// console view
//		m.setModelListener(aiV); 	// ai view
//		
//		// register controller inside view
//		consV.setListener(c);
//		aiV.setListener(c);
//		
		consoleView.printBoard();
		
		while( !model.isGameOver() ){
			consoleView.getInput();
// 			c.wait for input
//			v.send input
		}
	}

}
