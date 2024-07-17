// Klassen hanterar bollar och ärver egenskaper av Entity. Tar hand om logiken för när bollar studsar. Tex powerups.
import java.util.Random;
import java.awt.Rectangle;

import javafx.scene.canvas.GraphicsContext;

public class Ball extends Entity {

	private long lastCollisionTime = 0;
	private static final long COLLISION_DELAY = 1000;

	private static final int radius = 15;
	private int xSpeed = 1;
	private int ySpeed = 1;
	private int powerValue;

	public Ball(int canvasWidth, int canvasHeight) {
		super(canvasWidth, canvasHeight);
		setX(canvasWidth / 2);
		setY(canvasHeight / 2);
	}

	@Override
	public void react(GraphicsContext gc) {
		setX(getX() + getXSpeed());
		setY(getY() + getYSpeed());
		gc.fillOval(getX(), getY(), getRadius(), getRadius());
	}

	public void resetBall(int canvasWidth, int canvasHeight) {
		setX(canvasWidth / 2);
		setY(canvasHeight / 2);
		this.xSpeed = new Random().nextInt(2) == 0 ? 1 : -1;
		this.ySpeed = new Random().nextInt(2) == 0 ? 1 : -1;
	}

	public int update(GraphicsContext gc, Paddle player, Objects objects, int canvasWidth, int canvasHeight) {
		if (getY() > canvasHeight) {
			player.setLives(player.getLives() - 1);
			return -1;
		}

		// if ball hits paddle
		if ((getY() == player.getY() - player.getHeight() && getX() >= player.getX()
				&& getX() <= player.getX() + player.getWidth())) {
			setYSpeed(getYSpeed() * -1);
		}

		for (Brick b : objects.getBricks()) {
			if (getY() + radius / 2 >= b.getY() && getY() + radius / 2 <= b.getY() + b.getHeight()
					&& getX() + radius / 2 >= b.getX() && getX() + radius / 2 <= b.getX() + b.getWidth()) {

				if (getY() + radius / 2 == b.getY()) {
					setYSpeed(getYSpeed() * -1);
				}
				if (getY() + radius / 2 == b.getY() + b.getHeight()) {
					setYSpeed(getYSpeed() * -1);
				}

				if (getX() + radius / 2 == b.getX()) {
					setXSpeed(getXSpeed() * -1);
				}

				if (getX() + radius / 2 == b.getX() + b.getWidth()) {
					setXSpeed(getXSpeed() * -1);
				}
				objects.removeBrick(b);

				player.isLucky();

				return 50;

			}
		}

		if (getY() < 0) {
			setYSpeed(getYSpeed() * -1);
		}
		if (getX() > canvasWidth - 15 || getX() < 0) {
			setXSpeed(getXSpeed() * -1);
		}

		return 1;
	}

	public int moveThePower(GraphicsContext gc, Paddle player, Objects objects, int canvasWidth, int canvasHeight) {
		Rectangle entityRect = new Rectangle((int) Math.round(getX()), (int) Math.round(getY()), getRadius(),
				getRadius());
		Rectangle playerRect = new Rectangle((int) Math.round(player.getX()), (int) Math.round(player.getY()),
				player.getWidth(), player.getHeight());
		long currentTime = System.currentTimeMillis();
		// if entity hits paddle

		if (entityRect.intersects(playerRect)) {

			if (currentTime - this.lastCollisionTime > COLLISION_DELAY) {
				player.setLives(player.getLives() + getPowerValue()); // Beroende på ifall det är enemy eller powerBall
																		// så är det +1 eller -1
				this.lastCollisionTime = currentTime;
			}
			return -1; // Annan return ifall PowerBall träffar paddle
		}
		if (getY() < 0) {
			setYSpeed(getYSpeed() * -1);
		}
		if (getX() > canvasWidth - 15 || getX() < 0) {
			setXSpeed(getXSpeed() * -1);
		}
		return 1;
	}

	public int getXSpeed() {
		return this.xSpeed;
	}

	public void setXSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	public int getYSpeed() {
		return this.ySpeed;
	}

	public void setYSpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}

	public static int getRadius() {
		return radius;
	}

	public int getPowerValue() {
		return this.powerValue;
	}

	public void setPowerValue(int value) {
		this.powerValue = value;
	}

}