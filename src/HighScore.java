// Klassen hanterar sparandet och skapandet av highscore

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

public class HighScore implements Serializable {

	private ArrayList<HighScore> highScores = new ArrayList<>();
	private static final String HIGH_SCORE_FILE = "highscores.txt";

	private static final long serialVersionUID = 1L; // Add a serial version UID
	private String playerName;

	private int score;

	public HighScore() {
	}

	public HighScore(String playerName, int score) {
		this.playerName = playerName;
		this.score = score;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPlayerName() {
		return this.playerName;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return this.score;
	}

	public ArrayList<HighScore> getListOfScores() {
		return this.highScores;
	}

	public void loadHighScores() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(HIGH_SCORE_FILE))) {
			this.highScores = (ArrayList<HighScore>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void saveHighScores() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(HIGH_SCORE_FILE))) {
			oos.writeObject(this.highScores);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateHighScores(int score) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String playerName = dateFormat.format(new java.util.Date());

		HighScore newHighScore = new HighScore(playerName, score);
		this.highScores.add(newHighScore);
		Collections.sort(this.highScores, (hs1, hs2) -> Integer.compare(hs2.getScore(), hs1.getScore()));

		if (this.highScores.size() > 10) {
			this.highScores.remove(10); // Keep only the top 10 high scores
		}
		saveHighScores();
	}

}
