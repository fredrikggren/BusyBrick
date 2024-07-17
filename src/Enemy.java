// Klassen hanterar våran fiende. Extendar ball då den också är en boll.

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Enemy extends Ball {

	public Enemy(Ball ball, int canvasWidth, int canvasHeight) {
		super(canvasWidth, canvasHeight);
		setY(0);
		setX(new Random().nextInt(canvasWidth));
		setPowerValue(-1);
	}

	@Override
	public void react(GraphicsContext gc) {

		setY(getY() + getYSpeed());
		gc.setFill(Color.RED);
		gc.fillOval(getX(), getY(), getRadius(), getRadius());
		gc.setFill(Color.WHITE);
	}
}