package states.game;

import javafx.stage.Stage;
import states.State;

public class SinglePlayerState implements State {

	private Stage stage;

	public SinglePlayerState(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void enter() {
		System.out.println("enter single");

	}

	@Override
	public void exit() {
		System.out.println("exit single");

	}

}
