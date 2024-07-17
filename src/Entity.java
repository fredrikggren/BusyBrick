// Superklass som hanterar position för bollar osv. 

import javafx.scene.canvas.GraphicsContext;

public class Entity {

	private double x;
	private double y;

	public Entity(int canvasWidth, int canvasHeight) {
	}

	public void react(GraphicsContext gc) {
	}

	public double getX() {
		return this.x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return this.y;
	}

	public void setY(double y) {
		this.y = y;
	}

}