// Hanterar att generera bricksen i våra olika nivåer, dessa ändar beroende på level

import java.util.HashSet;

public class Objects {

	private HashSet<Brick> bricks = new HashSet<>();

	public Objects(int level) {
		generateBricks(level); // Lägg till int, beroende på vilken nivå man är på
	}

	public void generateBricks(int level) {
		for (int i = 1; i <= 0 + level; i++) {
			for (int j = 1; j <= 6; j++) {
				if (j == 0) {
					this.bricks.add(new Brick(50, 30 * i));
				} else {
					this.bricks.add(new Brick(50 * j, 30 * i));
				}
			}
		}
	}

	public void clearBricks() {
		this.bricks.clear();
	}

	public HashSet<Brick> getBricks() {
		return this.bricks;
	}

	public void removeBrick(Brick b) {
		this.bricks.remove(b);
	}

}