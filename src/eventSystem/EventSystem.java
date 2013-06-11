package eventSystem;

import java.util.ArrayList;

public class EventSystem {

	private static final EventSystem instance = new EventSystem();

	private ArrayList<Event> activeEventList = new ArrayList<Event>();
	private ArrayList<Event> inactiveEventList = new ArrayList<Event>();

	private ArrayList<EventListener> activeListenerList = new ArrayList<EventListener>(); // every
																							// listener
	private ArrayList<EventListener> inactiveListenerList = new ArrayList<EventListener>();

	// test für jedes event eine eigenen listener list?
	private ArrayList<EventListener> cellUpdateList = new ArrayList<EventListener>();
	private ArrayList<EventListener> gameStatusUpdateList = new ArrayList<EventListener>();
	private ArrayList<EventListener> playerTurnList = new ArrayList<EventListener>();
	private ArrayList<EventListener> playerUpdateList = new ArrayList<EventListener>();

	private EventSystem() {
	}

	public static EventSystem getInstance() {
		return instance;
	}

	public void addListener(EventListener listener) {
		inactiveListenerList.add(listener);
	}

	public void removeListener(EventListener listener) {
		inactiveListenerList.remove(listener);
	}

	public void queueEvent(Event event) {
		inactiveEventList.add(event);
	}

	public void dispatchEvent(Event event) {
		inactiveEventList.remove(event);
	}

	public void update() {

		activeListenerList = (ArrayList<EventListener>) inactiveListenerList.clone();
		// activeListenerList = inactiveListenerList;

		// throw all events
		this.activeEventList = inactiveEventList;
		inactiveEventList = new ArrayList<Event>();

		for (Event e : activeEventList) {

			// switch ( e.getType() ){
			//
			// case "GameStatusEvent":
			// for(EventListener l : gameStatusList ){
			// l.handleEvent( e );
			// }
			// break;
			//
			// case "CellUpdateEvent":
			// for(EventListener l : cellUpdateList ){
			// l.handleEvent( e );
			// }
			// break;
			//
			// case "PlayerTurnEvent":
			// for(EventListener l : playerTurnList){
			// l.handleEvent( e );
			// }
			// break;
			// case "PlayerUpdateEvent":
			// for(EventListener l : playerUpdateList){
			// l.handleEvent( e );
			// }
			// break;
			//
			// default:
			// System.out.println("error, evtl sogar exception thrown...");
			// break;
			// }
			//
			for (EventListener l : activeListenerList) {
				l.handleEvent(e);
			}
		}

	}

}