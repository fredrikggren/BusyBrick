// Klass som tar hand när spelet vär sätts igång, alltså är i sitt game-state.
import java.util.HashSet;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class GameRun extends State {

	private int isStarted;
	private int score;

	private int level = 1;

	private HashSet<Ball> balls = new HashSet<>();

	private Objects objects = new Objects(this.level); // Skapa dubbla konstruktorer, sen ändras beroende på vilken nivå

	public GameRun() {
	}

	@Override
	public int showMeTheState(int width, int height, int state, GraphicsContext gc, HighScore hs, int commandNum,
			Paddle player, Ball ball, boolean gameStarted) {

		if (state == 1) {
			prep(gc, width, height, player, objects);

			if (gameStarted) { // Sätts igång när mus klickas
				this.isStarted = 2;
				// set ball movement
				ball.react(gc);

				gc.setStroke(Color.WHITE);
				gc.setFont(Font.font(10));
				gc.strokeText("Level : " + this.level, 370, 30); // ändrat från cnt till score
				gc.strokeText("Score : " + String.valueOf(this.score), 370, 50); // ändrat från cnt till score
				gc.strokeText("Lives : " + String.valueOf(player.getLives()), 370, 70);
			} else { // If not game started
				// Set the start text
				gc.setStroke(Color.WHITE);
				gc.setTextAlign(TextAlignment.CENTER);
				gc.setFont(Font.font(15));
				gc.strokeText("KLIK MOOSE TO START GEM", width / 2, height / 2);
				gc.setFont(Font.font(10));
				gc.strokeText("PRESS ESC TO PAUSE AND GO MENJO", width / 2, height / 2 + 30);
				gc.setFont(Font.font(25));

				// reset the ball start position
				ball.resetBall(width, height);

			}
			// Makes sure that the ball is still inside canvas
			int temp = ball.update(gc, player, this.objects, width, height); // få in så att detta även gäller enemy
			for (Ball p : this.balls) {
				if (p.moveThePower(gc, player, this.objects, width, height) == -1) {
					balls.remove(p);// om en powerBall träffar player så reagerar den och tas bort från spelet.
				}
			}
			if ((temp == -1) || (player.getLives() == 0)) { // laggt till get.Lives för att kolla när röd boll
															// kolliderar

				// Liven tagit slut
				if (player.getLives() == 0) {
					state = 0;
					hs.updateHighScores(this.score);
					hs.saveHighScores();
					this.level = 1;
					this.score = 0;
					resetObjects(player, this.objects);

					state = 0;
					this.isStarted = 1;
				} else {
					ball.resetBall(width, height); // Om liv ej är slut så restas bara bollen

				}

			} else if (temp == 50) { // när boll slår mot brick
				this.score += temp;
				if (this.objects.getBricks().size() == 0) { // om det inte finns några bricks kvar
					this.level += 1;
					this.isStarted = 1;
					resetObjects(player, this.objects);
					return this.isStarted;
				}
				int r = new Random().nextInt(3);
				if (r == 0) { // skapar enemy
					Ball nEnemy = new Enemy(ball, width, height);
					this.balls.add(nEnemy);
					nEnemy.react(gc);
				} else if (r == 1) { // skapar power up
					Ball pBall = new PowerBall(ball, width, height);
					this.balls.add(pBall);
				}
			}
		}
		return this.isStarted;
	}

	public Objects getObjects() {
		return this.objects;
	}

	public void prep(GraphicsContext gc, int width, int height, Paddle player, Objects objects) {

		// set text color
		gc.setFill(levelColor(this.level, gc, width, height));
		gc.setFont(Font.font(25));
		gc.fillRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());

		for (Brick b : this.objects.getBricks()) {
			gc.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
		}

		for (Ball p : this.balls) {
			p.react(gc);
		}
	}

	// set background color
	public Color levelColor(int level, GraphicsContext gc, int width, int height) {
		if (this.level == 1) {
			gc.setFill(Color.BLACK);
		} else if (this.level == 2) {
			gc.setFill(Color.BROWN);
		} else if (this.level == 3) {
			gc.setFill(Color.DARKBLUE);
		}
		gc.fillRect(0, 0, width, height);
		return Color.WHITE;
	}

	public void resetObjects(Paddle player, Objects objects) {
		player.reset();
		this.balls.clear();
		this.objects.clearBricks();
		this.objects.generateBricks(this.level);
	}

}