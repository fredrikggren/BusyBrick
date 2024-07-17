// Hanterar att skriva ut highscore sidan

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class HighScorePage extends State {
	public HighScorePage() {
	}

	@Override
	public int showMeTheState(int width, int height, int state, GraphicsContext gc, HighScore hs, int commandNum,
			Paddle player, Ball ball, boolean gameStarted) {
		if (state == 2) {
			gc.setFill(Color.BLACK);
			gc.fillRect(0, 0, width, height);
			gc.setStroke(Color.WHITE);
			gc.setTextAlign(TextAlignment.CENTER);
			gc.strokeText("HIGHSCORE", width / 2, height / 10);

			int counter = 100;

			for (HighScore highScore : hs.getListOfScores()) {
				gc.strokeText("Date " + highScore.getPlayerName() + ", Score: " + highScore.getScore(), width / 2,
						counter);
				counter = counter + 50;
			}

		}
		return 2;
	}
}