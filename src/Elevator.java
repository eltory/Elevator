import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Collections;
import java.util.Vector;

public class Elevator {

	public int capOfWeight = 1000;
	public int currWeight = 0;
	public int currFloor = 0; // 0 = B2, 8 = 6
	public Vector goFloor = new Vector();

	public int speed = 5;
	public int btmFloor = 467;
	public int topFloor = 467;

	private int x, y; // location
	private int direction = 0; // direction
	private int state; // state

	// open & close elevator
	private int present_idx = 0;
	private int openCount = 0;
	private int idx;

	// elevator's current floor
	public int currentFloor;

	public int numofpeople = 0;
	public boolean workState;
	public boolean reachTo;
	public boolean reachFrom;
	public boolean ismove;
	// elevator image
	private Toolkit tk;
	public Image[] img = new Image[10];

	public Elevator(int x, int y, int idx) {
		this.x = x;
		this.y = y;
		this.idx = idx;

		this.reachFrom = false;
		this.reachTo = false;

		makeElevatorImg(this.idx);
	}
	public int getPresent_idx() {
		return present_idx;
	}

	public void setPresent_idx(int present_idx) {
		this.present_idx = present_idx;
	}
	public boolean isIsmove() {
		return ismove;
	}

	public void setIsmove(boolean ismove) {
		this.ismove = ismove;
	}

	public int getTopFloor() {
		return topFloor;
	}

	public void setTopFloor(int topFloor) {
		this.topFloor = topFloor;
	}

	public boolean canTake(int personWeight) {
		if (currWeight + personWeight > capOfWeight)
			return false;
		else
			return true;
	}

	public Image getImg() {
		return img[openCount / 5];
	}

	public void takeIn(int personWeight) {
		currWeight += personWeight;
	}

	public void open() {
		this.setOpenCount(100);
	}

	public int getOpenCount(int i) {
		return openCount;
	}

	public void setOpenCount(int openCount) {
		this.openCount = openCount;
	}

	public void takeOut(int personWeight) {
		currWeight = -personWeight;
	}

	public int getOpenCount() {
		return openCount;
	}

	public int getX() {
		return x;
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

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public void pushTake(int floor) {
		goFloor.add(floor);
		Collections.sort(goFloor);
	}

	public void popTake() {
		int k = 0;
		for (Object i : goFloor) {
			if (Integer.parseInt(goFloor.elementAt(k).toString()) == currFloor)
				goFloor.remove(k);
			k++;
		}
	}

	public void makeElevatorImg(int idx) {
		tk = Toolkit.getDefaultToolkit();

		String color;
		int j = 0;
		color = "A";
		for (int i = 0; i < 10; i++) {
			if (i > 4) {
				j = 9 - i;
			} else {
				j = i;
			}
			img[i] = tk.createImage(color + "Elevator" + j + ".png");
		}
	}
	public boolean hasWork(){
		if(goFloor.isEmpty()==true)
			return false;
		return true;
	}

	public int getFloor(int y) {
		int i = 0;

		switch (y) {
		case 102:
			i = 0;
			break;
		case 181:
			i = 1;
			break;
		case 261:
			i = 2;
			break;
		case 340:
			i = 3;
			break;
		case 420:
			i = 4;
			break;
		case 499:
			i = 5;
			break;
		case 578:
			i = 6;
			break;
		case 657:
			i = 7;
			break;
		}
		currFloor = i;
		return i;
	}
}