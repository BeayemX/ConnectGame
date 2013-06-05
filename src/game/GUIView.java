package game;

import eventSystem.Event;
import eventSystem.EventListener;
import eventSystem.EventSystem;
import eventSystem.events.CellUpdateEvent;
import eventSystem.events.GameStatusUpdateEvent;
import eventSystem.events.PlayerUpdateEvent;
import javafx.scene.Group;
import javafx.scene.GroupBuilder;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.stage.Stage;


public class GUIView implements EventListener {
	
	// FIXME magic numbers
	private Circle[][] circles = new Circle[7][5];
	private static int deleteMeCounter = 0;
	
	public GUIView() {
	}
	
	public void initialize(Stage stage) {

		Group root = GroupBuilder.create().build();
		
		
		float radius = 33f;
		// FIXME magic numbers		
		for (int i = 0; i < circles.length; ++i) {
			for (int j = 0; j < circles[i].length; j++) {
				Circle circle = CircleBuilder.create()
						.centerX(i * radius*2 + radius)
						.centerY(j * radius*2 + radius)
						.radius(radius)
						.fill(Color.WHITE)
						.stroke(Color.BLACK)
						.build();
				root.getChildren().add(circle);
			}			
		}
		
		
		Scene scene = SceneBuilder.create()
				.width(400)
				.height(400)
				.root(root)
				.build();
		
		stage.setTitle("hallo");
		stage.setScene(scene);
		stage.setFullScreen(false);
		stage.show();		
	}
	
	public void getInput(){
		
	}
		
	

	public void drawBoard(MementoBoard memento){
		System.out.println("zeichen");
		for(int i = 0; i<memento.getRow(); ++i) {
			for(int j = 0; j < memento.getRow(); ++j) {
				Circle circle = CircleBuilder.create().centerX(i).centerY(j).radius(2d).build();
			}
			
		}
	}
	
	
	
	public void handleEvent(Event event) {

		switch ( event.getType() ){
		
			case "GameStatusUpdate":
//				System.out.println("gamestatusupdate");
	    		this.handleGameStatusUpdateEvent( (GameStatusUpdateEvent) event );
	    		break;
    		
			case "CellUpdate":
//				System.out.println("view: cellupdate");
	    		this.handleCellUpdateEvent( (CellUpdateEvent) event );
	    		break;
	    		
		    case "PlayerUpdate":
//		    	System.out.println("view: playerUpdate");
	    		this.handlePlayerUpdateEvent( (PlayerUpdateEvent) event );
	    		break;
	    		
    		default: 
//    			System.out.println(event.getType() + " hasn't been handled in ConsoleView!");

//    			this.drawBoard();
    			break;
		      
		}
		
	}

	private void handleCellUpdateEvent(CellUpdateEvent event) {
		// neu zeichnen / darstellen
		this.drawBoard( (MementoBoard) event.getMemento());
		
		// XXX kein rekursionsanker
		EventSystem.getInstance().queueEvent(new CellUpdateEvent(event.getMemento()));
		
		for (int i = 0; i < circles.length; ++i) {
			for (int j = 0; j < circles[i].length; ++j) {
				if((i+j) % 2 == 0) {
					// XXX work in progress... kA warum da ein null pointer is
//					wollt nur ausprobiern ob ma so ueberhaupt die circle verändern kann
//					es wird eine endlosschleife an cellupdateds geworfen
//					circles[j][i].setCenterX(0);
					System.out.print(j + " " + i + ";");
				}
			}
		}
		
		
	}

	private void handleGameStatusUpdateEvent(GameStatusUpdateEvent event) {

//		this.drawBoard();
		
////		print game over
//		if(event.getStatus() == Status.OVER){
//			System.out.println("game over");
//			// player x won...
//		}
		
	}
	
	private void handlePlayerUpdateEvent(PlayerUpdateEvent event)  {

//		this.drawBoard();
		// spieler wechseln anzeigen / bzw das ma auf input wartet
//		this.player = event.getPlayer();

	}	

}
