package states.game;

import javafx.stage.Stage;
import states.State;

public class EndState implements State {

	private Stage stage;

	public EndState(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void enter() {
		System.exit(0);
	}

	@Override
	public void exit() {

	}

}
