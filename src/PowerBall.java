// Detta är vår power up som ärver egenskaper från ball

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PowerBall extends Ball {

	public PowerBall(Ball ball, int canvasWidth, int canvasHeight) {
		super(canvasWidth, canvasHeight);
		setY(0);
		setX(new Random().nextInt(canvasWidth));
		setPowerValue(1);
	}

	@Override
	public void react(GraphicsContext gc) {

		setY(getY() + getYSpeed());
		gc.setFill(Color.GREEN);
		gc.fillOval(getX(), getY(), getRadius(), getRadius());
		gc.setFill(Color.WHITE);
	}

}