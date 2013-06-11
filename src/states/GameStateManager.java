package states;

import eventSystem.Event;
import eventSystem.EventListener;
import eventSystem.EventSystem;
import states.StateType;

public class GameStateManager implements EventListener {

	private State menuState;
	private State singlePlayerState;
	private State twoPlayerState;
	private State creditsState;
	private State splashState;
	private State endState;

	private State currentState;

	public GameStateManager() {
		EventSystem.getInstance().addListener(this);
	}

	@Override
	public void handleEvent(Event event) {
		if (event.getType().equals(StateUpdateEvent.TYPE)) {
			handleStateEvent((StateUpdateEvent) event);
		}
	}

	public void reset() {
		switchState(this.splashState);
	}

	private void handleStateEvent(StateUpdateEvent event) {
		switch (event.getDetail()) {

		case END_OF_SPLASH:
			switchState(this.menuState);
			break;

		case QUIT_SELECTED:
			switchState(this.endState);
			break;

		case SINGLE_PLAYER_SELECTED:
			switchState(this.singlePlayerState);
			break;

		case TWO_PLAYER_SELECTED:
			switchState(this.twoPlayerState);
			break;

		case CREDITS_SELECTED:
			switchState(this.creditsState);
			break;

		default:
			// intentionally left empty
		}
	}

	private void switchState(State newState) {
		if (this.currentState != null) {
			this.currentState.exit();
		}

		this.currentState = newState;
		if (this.currentState != null) {
			this.currentState.enter();
		}
	}

	public State getMenuState() {
		return menuState;
	}

	public void setMenuState(State menuState) {
		this.menuState = menuState;
	}

	public State getSinglePlayerState() {
		return singlePlayerState;
	}

	public void setSinglePlayerState(State singlePlayerState) {
		this.singlePlayerState = singlePlayerState;
	}

	public State getTwoPlayerState() {
		return twoPlayerState;
	}

	public void setTwoPlayerState(State twoPlayerState) {
		this.twoPlayerState = twoPlayerState;
	}

	public State getCreditsState() {
		return creditsState;
	}

	public void setCreditsState(State creditsState) {
		this.creditsState = creditsState;
	}

	public State getSplashState() {
		return splashState;
	}

	public void setSplashState(State splashState) {
		this.splashState = splashState;
	}

	public State getEndState() {
		return endState;
	}

	public void setEndState(State endState) {
		this.endState = endState;
	}

}
