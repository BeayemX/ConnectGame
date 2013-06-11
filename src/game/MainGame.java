package game;

import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.GroupBuilder;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.scene.text.Font;
import javafx.scene.text.TextBuilder;
import javafx.stage.Stage;
import javafx.util.Duration;
import states.GameStateManagerBuilder;
import states.game.CreditsState;
import states.game.EndState;
import states.game.MenuState;
import states.game.SinglePlayerState;
import states.game.SplashState;
import states.game.TwoPlayerState;
import eventSystem.EventSystem;
import eventSystem.events.PlayerTurnEvent;

public class MainGame extends Application {

	private static final String APP_TITLE = "Connect Four (JavaFX-based)";
	private static final Duration UPDATE_RATE = Duration.seconds(1.0 / 60.0);
	private static final double MIN_WIDTH = 800;
	private static final double MIN_HEIGHT = 600;

	public static void main(String[] args) throws IOException {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		initStage(stage);
		initializeStates(stage);
		initializeUpdater();

	}

	private void initializeStates(Stage stage) {
		GameStateManagerBuilder.create()
			.splashState(new SplashState(stage))
			.menuState(new MenuState(stage))
			.creditsState(new CreditsState(stage))
			.singlePlayerState(new SinglePlayerState(stage))
			.twoPlayerState(new TwoPlayerState(stage))
			.endState(new EndState(stage))
			.build()
			.reset();
	}

	private void initStage(Stage stage) {
		Group root = new Group();

		Scene scene = SceneBuilder.create().root(root).fill(new Color(0.5, 0.5, 0.5, 1)).build();

		stage.setTitle(APP_TITLE);
		stage.setScene(scene);
		stage.setFullScreen(false);
		stage.setMinWidth(MIN_WIDTH);
		stage.setMinHeight(MIN_HEIGHT);
		stage.setWidth(MIN_WIDTH);
		stage.setHeight(MIN_HEIGHT);
		stage.show();

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
