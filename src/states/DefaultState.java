package states;

public class DefaultState implements State {

	private String name;

	public DefaultState(String name) {
		this.name = name;
	}

	@Override
	public void enter() {
		System.out.println(this.name + " state entered");
	}

	@Override
	public void exit() {
		System.out.println(this.name + " state exited");
	}

}