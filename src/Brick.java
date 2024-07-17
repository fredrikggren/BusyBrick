// Klassen hanterar rektanglarna som man försöker träffa med bollen.

public class Brick extends Entity {
	private static int width = 40;
	private static int height = 20;

	public Brick(int x, int y) {
		super(x, y);
		setX(x);
		setY(y);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int w) {
		width = w;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int h) {
		height = h;
	}

}