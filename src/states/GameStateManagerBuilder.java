package states;

public class GameStateManagerBuilder {

	private State splashState = new DefaultState("splash");
	private State menuState = new DefaultState("menu");
	private State creditsState = new DefaultState("credits");
	private State singlePlayerState = new DefaultState("single player");
	private State twoPlayerState = new DefaultState("two player");
	private State endState = new DefaultState("end");

	public static GameStateManagerBuilder create() {
		return new GameStateManagerBuilder();
	}

	public GameStateManager build() {
		GameStateManager stateMngr = new GameStateManager();

		stateMngr.setSplashState(this.splashState);
		stateMngr.setCreditsState(this.creditsState);
		stateMngr.setMenuState(this.menuState);
		stateMngr.setSinglePlayerState(this.singlePlayerState);
		stateMngr.setTwoPlayerState(this.twoPlayerState);
		stateMngr.setEndState(this.endState);

		return stateMngr;
	}

	public GameStateManagerBuilder splashState(State state) {
		this.splashState = state;
		return this;
	}

	public GameStateManagerBuilder endState(State state) {
		this.endState = state;
		return this;
	}

	public GameStateManagerBuilder menuState(State state) {
		this.menuState = state;
		return this;
	}

	public GameStateManagerBuilder singlePlayerState(State state) {
		this.singlePlayerState = state;
		return this;
	}

	public GameStateManagerBuilder twoPlayerState(State state) {
		this.twoPlayerState = state;
		return this;
	}

	public GameStateManagerBuilder creditsState(State state) {
		this.creditsState = state;
		return this;
	}

}