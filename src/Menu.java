// Hanterar att skriva ut menyn samt navigering med piltangenter

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Menu extends State {
	public Menu() {
	}

	@Override
	public int showMeTheState(int width, int height, int state, GraphicsContext gc, HighScore hs, int commandNum,
			Paddle player, Ball ball, boolean gameStarted) {
		if (state == 0) {
			gc.setFill(Color.BLACK);
			gc.fillRect(0, 0, width, height);
			gc.setStroke(Color.WHITE);
			gc.setTextAlign(TextAlignment.CENTER);
			gc.setFont(Font.font(65));

			gc.strokeText("BUSY BUS", width / 2, height / 4);

			gc.setFont(Font.font(10));
			gc.strokeText("USE ARROW KEYS, ENTER AND ESC TO NAVIGATE", width / 2, 550);
			gc.setFont(Font.font(16));
			gc.strokeText("PLAY", width / 2, height / 2);
			gc.strokeText("HIGH SCORE", width / 2, 350);
			gc.strokeText("QUIT", width / 2, 400);

			if (commandNum == 1) {
				gc.strokeText(">", width / 3, height / 2);

			} else if (commandNum == 2) {
				gc.strokeText(">", width / 3, 350);

			} else if (commandNum == 3) {
				gc.strokeText(">", width / 3, 400);

			}

		}
		return 2;
	}
}