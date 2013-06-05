package game;

import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.GroupBuilder;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.stage.Stage;
import javafx.util.Duration;

import eventSystem.EventSystem;

public class MainGame extends Application {
	
	private static final double STAGE_WIDTH = 800;
	private static final double STAGE_HEIGHT = 600;
	private static final String APP_TITLE = "Connect Four (JavaFX-based)";
	private static final Duration UPDATE_RATE = Duration.seconds(1.0 / 60.0);
	

	public static void main(String[] args)  throws IOException {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {	
				
		initStage(stage);
		initializeUpdater();		
				
		Model 		model			= new Model(7, 5);
		Controller 	controller 		= new Controller(model);
		GameLogic 	logic 			= new GameLogic(model, controller);
//		ConsoleView consoleView		= new ConsoleView();
		GUIView guiView				= new GUIView();
		
//		ConsoleView consoleView		= new ConsoleView(CellState.PLAYER_ONE);
//		ConsoleView consoleView2	= new ConsoleView(CellState.PLAYER_TWO);
//		AiView 		aiView 		= new AiView(m);
//		Evaluator 	evaluator	= new Evaluator(m);

		EventSystem eventSystem = EventSystem.getInstance();
		eventSystem.addListener(logic);
//		eventSystem.addListener(consoleView);
		eventSystem.addListener(guiView);
//		eventSystem.addListener(consoleView2);

		guiView.initialize(stage);
		
		logic.startNewGame();

		
		
//		while (!logic.isEndOfGame()) {
//			
//			eventSystem.update();
//			
//			if (logic.isEndOfGame()) {
//				logic.startNewGame();
//			}
//			
//		}
		
	}
	
	private void initStage(Stage stage) {
		
		
		
		
		
	}

	private void initializeUpdater() {
		KeyFrame oneFrame = new KeyFrame(UPDATE_RATE, new Updater());
		
		TimelineBuilder.create()
				.cycleCount(Animation.INDEFINITE)
				.keyFrames(oneFrame)
				.build()
				.play();
	}
	
	public class Updater implements EventHandler<ActionEvent> {
		
		@Override
		public void handle(ActionEvent event) {
			EventSystem.getInstance().update();
		}

	}

}
