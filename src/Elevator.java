import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;


public class Elevator {

	public int capOfWeight = 1000;
	public int currWeight = 0;
	public int currStair = 2; // 0 = B2, 8 = 6
	public boolean isMove = false;
	public int speed = 5;
	private int x, y; // location
	private int direction; // direction
	private int state; // state

	// open & close elevator
	private int present_idx = 0;
	private int openCount = 0;
	private int idx;

	// elevator's current floor
	public int currentFloor;

	// work 
	public int from;
	public int to;
	public boolean workState;
	public boolean reachTo;
	public boolean reachFrom;

	// elevator image
	private Toolkit tk;
	public  Image[] img = new Image[10];
	public Elevator(int x, int y, int idx) {
		this.x = x;
		this.y = y;
		this.idx = idx;

		//   this.direction = ElevatorInterface.NO;
		//this.state = ElevatorInterface.STOP;

		//this.workState = ElevatorInterface.RUN;
		this.reachFrom = false;
		this.reachTo = false;

		makeElevatorImg(this.idx); // get elevator images
	}

	public boolean canTake(int personWeight){
		if(currWeight + personWeight > capOfWeight)
			return false;
		else
			return true;
	}
	public Image getImg(){
		return img[openCount/5];
	}
	public void takeIn(int personWeight){
		currWeight += personWeight;
	}
	public void open() {
		this.setOpenCount(0);
	}
	public int getOpenCount(int i) {
		return openCount;
	}

	public void setOpenCount(int openCount) {
		this.openCount = openCount;
	}

	public void takeOut(int personWeight){
		currWeight =- personWeight;
	}

	public int getOpenCount() {
		return openCount;
	}

	public void setMoving(){
		if(direction == 0)
			isMove = false;
		else
			isMove = true;
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

	public boolean isMoving(){
		return isMove;
	}
	
	public void makeElevatorImg(int idx){
		tk = Toolkit.getDefaultToolkit();

		String color;
		int j = 0;

		//if(idx == 0)
			color="A"; // red
		/*else if(idx == 1)
			color="B"; // yellow
		else
			color="C"; // blue
*/
		for(int i = 0; i < 10; i++){
			if(i > 4){
				j = 9 - i;
			}else{
				j = i;
			}
			img[i] = tk.createImage(color + "Elevator" + j + ".png");
		}
	}
}