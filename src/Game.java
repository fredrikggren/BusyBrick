//Spelets huvudklass där spelet startas samt objekt skapas

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;

public class Game extends Application {

	private static final int width = 400;
	private static final int height = 600;

	private Ball ball = new Ball(width, height);
	private Paddle player = new Paddle(width, height);

	private int commandNum = 1;

	private State state = new State();
	private Menu menu = new Menu();
	private HighScorePage highScorePage = new HighScorePage();
	private GameRun gameRun = new GameRun();
	private ArrayList<State> states = new ArrayList<State>();

	private int currentState = 0;

	private HighScore highScore = new HighScore();

	private boolean gameStarted;

	private int g = 2; // 1 = game not started
						// 2 = game started
						// 3 = exit game
						// 4 = time for new level

	// RAMAR FÖR SPELET
	public void start(Stage stage) throws Exception {

		stage.setTitle("B U S Y B U S");
		Canvas canvas = new Canvas(width, height);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		Timeline t1 = new Timeline(new KeyFrame(Duration.millis(10), e -> run(gc)));
		t1.setCycleCount(Timeline.INDEFINITE);

		canvas.setOnKeyPressed(e -> handleKeyPress(e.getCode()));
		StackPane root = new StackPane(canvas);
		Scene scene = new Scene(root, width, height);

		// Movement in game
		canvas.setOnMouseMoved(e -> this.player.setX(e.getX()));
		canvas.setOnMouseClicked(e -> this.player.setX(e.getX()));
		canvas.setOnMouseClicked(e -> this.gameStarted = true);
		stage.setScene(scene);
		stage.show();
		canvas.requestFocus();
		t1.play();

		highScore.loadHighScores();
		states.add(this.state);
		states.add(this.menu);
		states.add(this.highScorePage);
		states.add(this.gameRun);

	}

//	SÄTTA IGÅNG SPELET
	private void run(GraphicsContext gc) {

		for (State s : states) {
			this.g = s.showMeTheState(width, height, this.currentState, gc, this.highScore, this.commandNum,
					this.player, this.ball, this.gameStarted);
			if (this.g == 1) {
				this.gameStarted = false;
			} else if (this.g == 3) {
				this.gameRun.getObjects().clearBricks(); // behövs denna?
				System.exit(0);
			}
		}
	}

	private void handleKeyPress(KeyCode keyCode) {
		if (this.currentState == 0) {

			if (keyCode == KeyCode.UP) {
				this.commandNum--;
				if (this.commandNum < 1) {
					this.commandNum = 3;
				}

			} else if (keyCode == KeyCode.DOWN) {
				this.commandNum++;
				if (this.commandNum > 3) {
					this.commandNum = 1;
				}

			} else if (keyCode == KeyCode.ENTER) {
				this.currentState = this.commandNum;

			}
		} else if (keyCode == KeyCode.ESCAPE) {

			this.currentState = 0;

		}
	}
}