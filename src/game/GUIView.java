package game;

import eventSystem.Event;
import eventSystem.EventListener;
import eventSystem.EventSystem;
import eventSystem.events.CellUpdateEvent;
import eventSystem.events.GameStatusUpdateEvent;
import eventSystem.events.PlayerTurnEvent;
import eventSystem.events.PlayerUpdateEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.GroupBuilder;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.stage.Stage;


public class GUIView implements EventListener {
	
	// FIXME magic numbers
	private Circle[][] circles = new Circle[7][5];
	private CellState currentPlayer = CellState.PLAYER_ONE;
	// FIXME magic numbers
	private Model model = new Model(7, 5);
	private Text text;
	
	public GUIView() {
	}
	
	public void initialize(Stage stage) {

		Group root = GroupBuilder.create().build();
		
		
		float radius = 33f;
		// FIXME magic numbers		
		for (int i = 0; i < circles.length; ++i) {
			for (int j = 0; j < circles[i].length; j++) {
				final int col=i;
				Circle circle = CircleBuilder.create()
						.centerX(i * radius*2 + radius)
						.centerY(j * radius*2 + radius)
						.radius(radius)
						.fill(Color.WHITE)
						.stroke(Color.BLACK)
						.build();
				circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						EventSystem.getInstance().queueEvent( new PlayerTurnEvent(currentPlayer, col));
					}
				});

				circles[i][j] = circle;
				root.getChildren().add(circle);
			}			
		}
		
		text = TextBuilder.create().text(""+this.currentPlayer)
				.x(50)
				.y(350)
				.build();
		root.getChildren().add(text);
		
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
	
//	public void getInput(){
//		
//	}
		
	
//
//	public void drawBoard(MementoBoard memento){
////		System.out.println("zeichen");
//		for(int i = 0; i<memento.getRow(); ++i) {
//			for(int j = 0; j < memento.getRow(); ++j) {
////				Circle circle = CircleBuilder.create().centerX(i).centerY(j).radius(2d).build();
//			}
//			
//		}
//	}
	
	
	
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
		
		this.model.restoreFromMemento((MementoBoard) event.getMemento());
		
		for (int row = 0; row < circles.length; ++row) {

			for (int col = 0; col < circles[0].length; ++col) {
								
				switch (model.getCell(row, col)){
				
					case PLAYER_ONE:
						circles[row][col].setFill(Color.RED);
						break;
					case PLAYER_TWO:
						circles[row][col].setFill(Color.YELLOW);
						break;
//					case EMPTY:
//						break;
						
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

		this.currentPlayer = event.getPlayer(); 
		this.text.setText(this.currentPlayer+"");
		
	}	

}
