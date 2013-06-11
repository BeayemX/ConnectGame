package states.game;

import eventSystem.EventSystem;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.GroupBuilder;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.stage.Stage;
import javafx.util.Duration;
import states.State;
import states.StateType;
import states.StateUpdateEvent;

public class CreditsState implements State {

	private Stage stage;

	public CreditsState(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void enter() {

		Group root = GroupBuilder.create().build();

		Text text = TextBuilder.create().text("Game by Sifu")
				.font(Font.font("Arial", 20))
				.build();
		text.snapshot(null, null);
		text.setX((stage.getWidth() - text.getLayoutBounds().getWidth()) / 2);
		text.setY((stage.getHeight() - text.getLayoutBounds().getHeight()) / 2);
		
		root.getChildren().add(text);

		FadeTransition ft = new FadeTransition(Duration.millis(1500), text);
		ft.setFromValue(0f);
		ft.setToValue(1f);
		ft.setCycleCount(2);
		ft.setAutoReverse(true);

		ft.play();
		ft.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// FIXME end_of_splash funkt zwar, passt aber nicht so ganz
				EventSystem.getInstance().queueEvent(new StateUpdateEvent(StateType.END_OF_SPLASH));
			}
		});

		Scene scene = stage.getScene();
		scene.setRoot(root);

		stage.setScene(scene);
		stage.show();

	}

	@Override
	public void exit() {

	}

}
