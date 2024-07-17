// State är en superklass osm alla olika spelstates ärver av.

import javafx.scene.canvas.GraphicsContext;

public class State {

	public State() {

	}

	public int showMeTheState(int width, int height, int state, GraphicsContext gc, HighScore hs, int commandNum,
			Paddle player, Ball ball, boolean gameStarted) {
		if (state == 3) {
			hs.updateHighScores(hs.getScore());
			hs.saveHighScores();
			return 3;
		}
		return 2;
	}

}