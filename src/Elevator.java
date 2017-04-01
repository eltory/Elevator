import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Collections;
import java.util.Iterator;
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
	public int di;
	public int state; // state

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
	public Image[] img = new Image[12];

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

	public int getDi() {
		return di;
	}
	public void setDi(int di) {
		this.di = di;
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
		return img[openCount / 6];
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
		 if (!goFloor.contains(floor)) {
	         goFloor.add(floor);
	         Collections.sort(goFloor);
	      }
		if(direction==1)
			Collections.sort(goFloor);
		else
			Collections.sort(goFloor,Collections.reverseOrder());
	}
	public void popTake(int want) {
	      int k = 0;
	      for (Object i : goFloor) {
	         if (Integer.parseInt(goFloor.elementAt(k).toString()) == want){
	        	 goFloor.remove(k);
	        	 break;
	         }
	     
	         k++;
	      }
	   }

	public void makeElevatorImg(int idx) {
		tk = Toolkit.getDefaultToolkit();


		int j = 0;
		for (int i = 0; i < 12; i++) {
			if (i > 5) {
				j = 11 - i;
			} else {
				j = i;
			}
			img[i] = tk.createImage("Qbox" + j + ".png");
		}
	}
	public boolean hasWork(){
		if(goFloor.isEmpty()==true)
			return false;
		return true;
	}

	public int getFloor() {
	      int i = this.getY();
	      int currFloor;

	      if (i <= 659 && i > 587)
	         currFloor = 0;
	      else if (i <= 587 && i > 491)
	         currFloor = 1;
	      else if (i <= 491 && i > 417)
	         currFloor = 2;
	      else if (i <= 417 && i > 343)
	         currFloor = 3;
	      else if (i <= 343 && i > 270)
	         currFloor = 4;
	      else if (i <= 270 && i > 196)
	         currFloor = 5;
	      else if (i <= 196 && i > 122)
	         currFloor = 6;
	      else
	         currFloor = 7;

	      return currFloor;
	   }
}