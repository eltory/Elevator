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
	public boolean touchscreen = false;
	public boolean elevator = false;
	public int moveCount = 24;
	public boolean enter = false;
	int elelocation = 400;
	int state =1;
	public char personMove;
	public boolean reach = false;
	public boolean dooropen = false;
	public boolean elereach=false;
	public int whatElev;
	Person() {
		num++;
		startFloor();
		selectFloor();
		createWeight();
		// makeElevatorImg();
	}

	public char getPersonMove() {
		return personMove;
	}

	public void setPersonMove(char personMove) {
		this.personMove = personMove;
	}

	public boolean isDooropen() {
		return dooropen;
	}

	public void setDooropen(boolean dooropen) {
		this.dooropen = dooropen;
	}

	public boolean isReach() {
		return reach;
	}

	public void setReach(boolean reach) {
		this.reach = reach;
	}

	public int getElelocation() {
		return elelocation;
	}

	public void setElelocation(int elelocation) {
		this.elelocation = elelocation;
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

	public boolean isElevator() {
		return elevator;
	}

	public void setElevator(boolean elevator) {
		this.elevator = elevator;
	}

	public boolean isTouchscreen() {
		return touchscreen;
	}

	public void setTouchscreen(boolean touchscreen) {
		this.touchscreen = touchscreen;
	}

	public void startFloor() {
		currFloor = (int) (Math.random() * 100 % 8);
	}

	public void setStartPosition(Point p) {
		position = p;
	}

	public void createWeight() {
		weight = (int) (Math.random() * 100 % 71 + 50);
	}

	public void selectFloor() {
		while (true) {
			wantFloor = (int) (Math.random() * 100 % 8);
			if(wantFloor != currFloor)
				break;
		}
		// direction to stair
		if (wantFloor < currFloor)
			direction = -1;
		else
			direction = 1;
	}

	public int getDirection() {
		return direction;
	}

	public int getMoveCount() {
		return moveCount;
	}

	public void setMoveCount(int moveCount) {
		this.moveCount = moveCount;
	}

	public void moveForward() {
		if(state == 1) {
			this.x += speed;
			moveCount--;
			if(moveCount < 1)
				moveCount = 24;
		}
	}

	public void moveBackward() {
		if(this.x > 250) {
			this.x -= speed;
			moveCount--;
			if(moveCount < 1)
				moveCount = 24;
		}
		if(x==250)
			moveCount=49;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public boolean isEnter() {
		return enter;
	}

	public void setEnter(boolean enter) {
		this.enter = enter;
	}

}