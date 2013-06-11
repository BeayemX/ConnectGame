package states.game;

import eventSystem.EventSystem;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.GroupBuilder;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.stage.Stage;
import javafx.util.Duration;
import states.State;
import states.StateType;
import states.StateUpdateEvent;

public class MenuState implements State {

	private Stage stage;

	public MenuState(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void enter() {

		Group root = GroupBuilder.create().build();

		String[] menuButtonLabels = { "Singleplayer", "Multiplayer", "Credits", "Quit" };
		final StateType[] menuButtonEvents = { StateType.SINGLE_PLAYER_SELECTED, StateType.TWO_PLAYER_SELECTED, StateType.CREDITS_SELECTED,
				StateType.QUIT_SELECTED };

		for (int i = 0; i < menuButtonLabels.length; ++i) {

			final int crap_i = i;

			final Text text = TextBuilder.create()
					.text(menuButtonLabels[i])
					.font(Font.font("Arial", 40))
					.build();
			text.snapshot(null, null);
			text.setX((stage.getWidth() - text.getLayoutBounds().getWidth()) / 2);
			text.setY((stage.getHeight() - text.getLayoutBounds().getHeight()) / 2 + 70 * (i -  (menuButtonLabels.length - 1) / 2));

			text.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					EventSystem.getInstance().queueEvent(new StateUpdateEvent(menuButtonEvents[crap_i]));
				}

			});

			text.setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {

					FillTransition ft = new FillTransition(Duration.millis(200), text);
					ft.setToValue(Color.WHITE);
					ft.play();

					ScaleTransition st = new ScaleTransition(Duration.millis(200), text);
					st.setToX(1.3d);
					st.setToY(1.15d);
					st.play();

				}

			});

			text.setOnMouseExited(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {

					FillTransition ft = new FillTransition(Duration.millis(200), text);
					ft.setToValue(Color.BLACK);
					ft.play();

					ScaleTransition st = new ScaleTransition(Duration.millis(200), text);
					st.setToX(1f);
					st.setToY(1f);
					st.play();

				}
			});

			root.getChildren().add(text);

		}

		FadeTransition ft = new FadeTransition(Duration.millis(1500), root);
		ft.setFromValue(0f);
		ft.setToValue(1f);

		ft.play();
		
		Scene scene = stage.getScene();
		scene.setRoot(root);

		stage.setScene(scene);
		stage.show();

		// EventSystem.getInstance().queueEvent(new
		// StateUpdateEvent(StateType.TWO_PLAYER_SELECTED));

	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

}
