// Paddle är själva spelaren och styr av musen, här ändars storlek beronde på rand metoden isLucky när en brick blir träffad

import java.util.Random;

public class Paddle {

	private int lives = 2;

	private static int height = 15;
	private static int width = 70;
	private double x;
	private int y;

	public Paddle(int canvasWidth, int canvasHeight) {
		this.x = canvasWidth / 2;
		this.y = canvasHeight - 20;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public static int getHeight() {
		return height;
	}

	public static int getWidth() {
		return width;
	}

	public void setWidth(int w) {
		width = w;
	}

	public void reset() {
		width = 70;
		this.lives = 2;
	}

	public void reSize() {
		int rand = new Random().nextInt(30);
		if (rand < 15) {
			width = width / 2;
		} else if (rand > 15) {
			width = width * 2;
		}
	}

	public void isLucky() {
		int isLucky = new Random().nextInt(10);
		if (isLucky < 2) {
			this.reSize();
		}
	}

}