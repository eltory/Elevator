import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

public class Person {
	public int speed = 1;
	public int count = 0;
	private int x, y;
	public int currFloor;
	public int wantFloor;
	public int weight;
	public int direction = 0;
	public static int num = 0;
	public Point position = new Point();
	private Toolkit tk;
	public Image[] img = new Image[11];
	public boolean displayKeypad = false;

	Person() {
		num++;
		startFloor();
		selectFloor();
		createWeight();
		makeElevatorImg();
	}

	public int getX() {
		return x;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Image getImg() {
		return img[count];
	}

	public boolean isDisplayKeypad() {
		return displayKeypad;
	}

	public void setDisplayKeypad(boolean displayKeypad) {
		this.displayKeypad = displayKeypad;
	}

	public void startFloor() {
		currFloor = (int) (Math.random() * 100 % 8 + 1);
	}


	public void setStartPosition(Point p) {
		position = p;
	}

	public void createWeight() {
		weight = (int) (Math.random() * 100 % 71 + 50);
	}

	public void selectFloor() {
		while (wantFloor != currFloor) {
			wantFloor = (int) (Math.random() * 100 % 8 + 1);
		}
		// direction to stair
		if (wantFloor < currFloor)
			direction = -1;
		else
			direction = 1;
	}

	public void makeElevatorImg() {
		tk = Toolkit.getDefaultToolkit();
		int j = 0;
		for (int i = 0; i < 5; i++) {
			img[i] = tk.createImage("Person" + (4 - i) + ".png");
		}
	}
}
