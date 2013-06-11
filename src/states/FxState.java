package states;

import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class FxState implements State {

	private Stage stage;

	public FxState(Stage stage) {
		this.stage = stage;
	}

	public final Stage getStage() {
		return stage;
	}

	public final Scene getScene() {
		return this.stage.getScene();
	}
}
