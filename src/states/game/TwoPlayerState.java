package states.game;

import eventSystem.Event;
import eventSystem.EventListener;
import eventSystem.EventSystem;
import eventSystem.events.CellUpdateEvent;
import eventSystem.events.GameStatusUpdateEvent;
import eventSystem.events.PlayerTurnEvent;
import eventSystem.events.PlayerUpdateEvent;
import game.CellState;
import game.Controller;
import game.GameLogic;
import game.MementoBoard;
import game.Model;
import game.Status;
import states.State;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.GroupBuilder;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.stage.Stage;

public class TwoPlayerState implements State, EventListener {

	private Stage stage;
	// FIXME magic numbers
	private Circle[][] circles = new Circle[7][5];
	private CellState currentPlayer = CellState.PLAYER_ONE;
	// FIXME magic numbers
	private Model model = new Model(7, 5);
	private Text text;

	Color COLOR_EMPTY = Color.WHITE;
	Color COLOR_OUTLINE = Color.BLACK;
	Color COLOR_PLAYER_ONE = Color.RED;
	Color COLOR_PLAYER_TWO = Color.YELLOW;

	public TwoPlayerState(Stage stage) {
		this.stage = stage;
		EventSystem.getInstance().addListener(this);
	}

	@Override
	public void enter() {

		Model model = new Model(7, 5);
		Controller controller = new Controller(model);
		GameLogic logic = new GameLogic(model, controller);

		Group root = GroupBuilder.create().build();
		
		Group disks = GroupBuilder.create().build();

		float radius = 33f;
		float space = 10f;

		for (int i = 0; i < circles.length; ++i) {
			for (int j = 0; j < circles[i].length; j++) {
				final int col = i;
				Circle circle = CircleBuilder.create()
						.centerX(i * (radius * 2 + space) + radius)
						.centerY(j * (radius * 2 + space) + radius / 2)
						.radius(radius)
						.fill(COLOR_EMPTY)
						.stroke(COLOR_OUTLINE).build();
				circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						EventSystem.getInstance().queueEvent(new PlayerTurnEvent(currentPlayer, col));
					}
				});

				circles[i][j] = circle;
				disks.getChildren().add(circle);
			}
		}
		
		root.getChildren().add(disks);
		disks.setTranslateX((stage.getWidth() - disks.getLayoutBounds().getWidth()) / 2);
		disks.setTranslateY((stage.getHeight() - disks.getLayoutBounds().getHeight()) / 2);

		text = TextBuilder.create().text("" + this.currentPlayer)
				.y(50)
				.font(Font.font("Arial", 20))
				.fill((this.currentPlayer == CellState.PLAYER_ONE) ? COLOR_PLAYER_ONE : COLOR_PLAYER_TWO)
				// .stroke(COLOR_OUTLINE)
				.build();
		root.getChildren().add(text);
		
		text.snapshot(null, null);
		text.setX((stage.getWidth() - text.getLayoutBounds().getWidth()) / 2);

		Scene scene = stage.getScene();
		scene.setRoot(root);

		stage.setScene(scene);
		stage.show();

		logic.startNewGame();
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

	public void handleEvent(Event event) {

		switch (event.getType()) {

		case "GameStatusUpdate":
			// System.out.println("gamestatusupdate");
			this.handleGameStatusUpdateEvent((GameStatusUpdateEvent) event);
			break;

		case "CellUpdate":
			// System.out.println("view: cellupdate");
			this.handleCellUpdateEvent((CellUpdateEvent) event);
			break;

		case "PlayerUpdate":
			// System.out.println("view: playerUpdate");
			this.handlePlayerUpdateEvent((PlayerUpdateEvent) event);
			break;

		default:
			// System.out.println(event.getType() +
			// " hasn't been handled in ConsoleView!");

			// this.drawBoard();
			break;

		}

	}

	private void handleCellUpdateEvent(CellUpdateEvent event) {

		this.model.restoreFromMemento((MementoBoard) event.getMemento());
		// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX nur eins
		// ändern, nicht alle?
		for (int row = 0; row < circles.length; ++row) {

			for (int col = 0; col < circles[0].length; ++col) {

				switch (model.getCell(row, col)) {

				case PLAYER_ONE:
					circles[row][col].setFill(COLOR_PLAYER_ONE);
					break;
				case PLAYER_TWO:
					circles[row][col].setFill(COLOR_PLAYER_TWO);
					break;

				}

			}

		}

	}

	private void handleGameStatusUpdateEvent(GameStatusUpdateEvent event) {

		if (event.getStatus() == Status.OVER) {
			System.out.println(currentPlayer + " hat gewonnen");
		} else if (event.getStatus() == Status.DRAW) {
			System.out.println("unentschieden");
		}

		if (event.getStatus() == Status.DRAW || event.getStatus() == Status.OVER) {
			for (int row = 0; row < circles.length; ++row) {

				for (int col = 0; col < circles[0].length; ++col) {

					circles[row][col].setFill(COLOR_EMPTY);

				}

			}

		}

	}

	private void handlePlayerUpdateEvent(PlayerUpdateEvent event) {

		this.currentPlayer = event.getPlayer();
		this.text.setText(this.currentPlayer + "");
		this.text.setFill((this.currentPlayer == CellState.PLAYER_ONE) ? COLOR_PLAYER_ONE : COLOR_PLAYER_TWO);

	}

}
