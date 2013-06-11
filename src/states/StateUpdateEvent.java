package states;

import eventSystem.Event;

public class StateUpdateEvent extends Event {

	public static final String TYPE = "StateUpdate";
	private StateType detail;

	public StateUpdateEvent(StateType stateType) {
		super(TYPE);
		this.detail = stateType;
	}

	public StateType getDetail() {

		return this.detail;

	}

}