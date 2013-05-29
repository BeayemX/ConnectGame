package game;

import java.io.IOException;

import eventSystem.EventSystem;

public class MainGame {

	public static void main(String[] args) throws IOException {
		
		Model 		model		= new Model(7, 5);
		Controller 	controller 	= new Controller(model);
		GameLogic 	logic 		= new GameLogic(model, controller);

		ConsoleView consoleView		= new ConsoleView();
//		ConsoleView consoleView		= new ConsoleView(CellState.PLAYER_ONE);
//		ConsoleView consoleView2	= new ConsoleView(CellState.PLAYER_TWO);
//		AiView 		aiView 		= new AiView(m);
//		Evaluator 	evaluator	= new Evaluator(m);

		EventSystem eventSystem = EventSystem.getInstance();
		eventSystem.addListener(logic);
		eventSystem.addListener(consoleView);
//		eventSystem.addListener(consoleView2);

		logic.startNewGame();

		while (!logic.isEndOfGame()) {
			eventSystem.update();
			if (logic.isEndOfGame()) {
				logic.startNewGame();
			}
		}
		
		
		
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
		
//		while( !model.isGameOver() ){
//			consoleView.getInput();
//// 			c.wait for input
////			v.send input
//		}
	}

}
