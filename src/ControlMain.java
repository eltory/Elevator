
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.Timer;

import javax.swing.*;
import javafx.application.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ControlMain extends JFrame implements Runnable {
	private Toolkit tk;
	private Image bgImg = Toolkit.getDefaultToolkit().getImage("BGB.png");
	private Image bgImg2 = Toolkit.getDefaultToolkit().getImage("BGF.png");
	private Image santa = Toolkit.getDefaultToolkit().createImage("santa.png");
	private Image snow = Toolkit.getDefaultToolkit().createImage("snow.gif"); 
	private static LinkedList<Person> personList = new LinkedList<Person>();
	private Elevator[] elevators = new Elevator[3];
	private Person p = new Person();
	private Floor[] floor = new Floor[8];
	 private BufferedImage bi = null;
	int i = 0;
	int cnt;
	int ele;
	TouchScreen ts;
	int gg = 0;
	public static Image[] person = new Image[10];
	public static Image[] r_person = new Image[10];
	Timer t = new Timer();
	int maxA = 122;
	int minA = 659;
	Santa st = new Santa();
	public ControlMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocation(0, 0);
		setBounds(20, 20, 1000, 710);
		setLayout(null);

		for (int i = 0; i < 3; i++) {
			elevators[i] = new Elevator(573 + i * 54, 488, i);
		}

		i = 0;
		for (Floor s : floor) {
			floor[i] = new Floor();
			floor[i].position.x = 250;
			i++;
		}
		floor[0].position.y = 637+22;
		floor[1].position.y = 565+22;
		floor[2].position.y = 469+22;
		floor[3].position.y = 395+22;
		floor[4].position.y = 321+22;
		floor[5].position.y = 248+22;
		floor[6].position.y = 174+22;
		floor[7].position.y = 100+22;
		i = 0;

		tk = Toolkit.getDefaultToolkit();

		// create person's image
		for (int i = 0; i < 7; i++) {
			person[i] = tk.createImage("Mario" + (1 + i) + ".png");
			r_person[i] = tk.createImage("Mario_r" + (1 + i) + ".png");
		}
		i = 0;
		this.setVisible(true);
	}

	@Override
	public void run() {
		while (true) {
			try {
				st.moveForward();
				if (gg % 3000 == 0)
					t.schedule(new TimerTask() {
						@Override
						public void run() {
							p = new Person();
							p.setX(250);
							p.setY(floor[p.currFloor].position.y +4);
							personList.add(p);
							cnt++;
						}
					}, 0, 15000);
				Thread.sleep(10);
				gg++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			for (int b = 0; b < 3; b++) {
				if (elevators[b].state == 1) {
					if (elevators[b].numofpeople == 0) {
						if (elevators[b].getY() > elevators[b].getTopFloor()) {
							elevators[b].setY(elevators[b].getY() - 1);
						} else if (elevators[b].getY() < elevators[b].getTopFloor()) {
							elevators[b].setY(elevators[b].getY() + 1);
						} else{
							eledoor(b);
						}
					}
					else {
						if (elevators[b].getDirection() == 1 && elevators[b].getY() >= maxA) {
							elevators[b].setY(elevators[b].getY() - 1);
							int k = 0;
							for (Object i : elevators[b].goFloor) {
								int a = Integer.parseInt(elevators[b].goFloor.elementAt(k).toString());
								if (floor[a].position.y == elevators[b].getY()||floor[a].position.y == elevators[b].getY()-1
										||floor[a].position.y == elevators[b].getY()+1) {
									//System.out.println(a+" "+elevators[b].getY());
									InorOut(floor[a].position.y, b);
									break;
								}
								k++;
							}
						} 
						else if (elevators[b].getDirection() == -1&& elevators[b].getY() <= minA) {
							elevators[b].setY(elevators[b].getY() + 1);						
							if(elevators[b].goFloor.size()!=0){
								int h = 0;					
								int a = Integer.parseInt(elevators[b].goFloor.elementAt(h).toString());
								if (floor[a].position.y == elevators[b].getY()||floor[a].position.y == elevators[b].getY()-1
										||floor[a].position.y == elevators[b].getY()+1) {
									//System.out.println(a+" "+elevators[b].getY());
									InorOut(floor[a].position.y, b);
									break;
								}
								h++;
							}
						}
					}
				}
				if(elevators[b].getOpenCount() > 0) {
					elevators[b].setPresent_idx((int)(elevators[b].getOpenCount()/40));
					elevators[b].setOpenCount(elevators[b].getOpenCount() - 1);
				}
			}

			for (int a = 0; a < cnt; a++) {
				personList.get(a).moveForward();
				if (personList.get(a).getX() == 400 && personList.get(a).isTouchscreen() == false) {
					ts = new TouchScreen();
					personList.get(a).setTouchscreen(true);
				}
				if (personList.get(a).isTouchscreen() == true && personList.get(a).isElevator() == false) {
					//System.out.println(a+" "+personList.get(a).currFloor+" "+personList.get(a).wantFloor);
					int min=0;
					int inele=0;
					for (int i = 0; i < 3; i++) {
						if (elevators[i].canTake(personList.get(a).weight) == true) {
							if (elevators[i].getDirection() == 0) {
								if(min==0){
									inele=i;
									min = Math.abs(elevators[i].getY()-personList.get(a).getY());
								}
								else if(min >Math.abs(elevators[i].getY()-personList.get(a).getY())){
									inele=i;
									min = Math.abs(elevators[i].getY()-personList.get(a).getY());
								}
							}

							if(elevators[i].getDirection()==1){
								if(elevators[i].getDirection()==personList.get(a).getDirection()&&elevators[i].getY() > personList.get(a).getY()){
									//elevators[inele].pushTake(personList.get(a).currFloor);
									if(min==0){
										inele=i;
										min = Math.abs(elevators[i].getY()-personList.get(a).getY());
									}
									else if(min >Math.abs(elevators[i].getY()-personList.get(a).getY())){
										inele=i;
										min = Math.abs(elevators[i].getY()-personList.get(a).getY());
									}
								}
							}
							if(elevators[i].getDirection()==-1){
								if(elevators[i].getDirection()==personList.get(a).getDirection()&&elevators[i].getY() < personList.get(a).getY()){
									//elevators[inele].pushTake(personList.get(a).currFloor);
									if(min==0){
										inele=i;
										min = Math.abs(elevators[i].getY()-personList.get(a).getY());
									}
									else if(min >Math.abs(elevators[i].getY()-personList.get(a).getY())){
										inele=i;
										min = Math.abs(elevators[i].getY()-personList.get(a).getY());
									}
								}
							}
						}
					}
					//System.out.println("Ž"+a+" "+inele);
					elevators[inele].setDirection(personList.get(a).getDirection());
					elevators[inele].pushTake(personList.get(a).wantFloor);
					elevators[inele].takeIn(personList.get(a).weight);
					elevators[inele].state = 1;
					elevators[inele].setTopFloor(floor[personList.get(a).currFloor].position.y );
					personList.get(a).setElelocation(elevators[inele].getX());
					personList.get(a).setElevator(true);

					if (personList.get(a).getElelocation() == 573) {
						personList.get(a).setPersonMove('A');
						personList.get(a).whatElev = 0;
					} else if (personList.get(a).getElelocation() == 627) {
						personList.get(a).setPersonMove('B');
						personList.get(a).whatElev = 1;
					} else {
						personList.get(a).setPersonMove('C');
						personList.get(a).whatElev = 2;
					}
					ts.setOption(personList.get(a).wantFloor, personList.get(a).whatElev);
					ts.setVisible(true);
					try {
						Thread.sleep(2000);
						ts.setVisible(false);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if (personList.get(a).isElevator() == true) {
					if (personList.get(a).getX() == personList.get(a).getElelocation()){
						personList.get(a).setState(0);
						personList.get(a).elereach=true;
					}
					if (personList.get(a).isDooropen() == true) {
						personList.get(a).setPersonMove('O');
						personList.get(a).setDooropen(false);
					}
					// when getting off the elevator
					if (personList.get(a).isReach() == true) {
						personList.get(a).setPersonMove('R');
						//personList.get(a).setReach(false);
					}
				}
				if (personList.get(a).getPersonMove() == 'R') {
					personList.get(a).moveBackward();
				}
			}
			repaint();
		}
	}

	public void paint(Graphics g) {
		Image buffer = createImage(1000, 710);
		Graphics gContext = buffer.getGraphics();
		gContext.drawImage(bgImg, 0, -4, this);

		for (int i = 0; i < 3; i++)
			gContext.drawImage(elevators[i].getImg(), elevators[i].getX(), elevators[i].getY(), this);
		
		gContext.drawImage(bgImg2, 0, -4, this);
		gContext.drawImage(santa, st.getX(), st.getY(), this);

		for (int a = 0; a < cnt; a++) {
			if (personList.get(a).getPersonMove() == 'R')
				gContext.drawImage(r_person[personList.get(a).getMoveCount() / 7], personList.get(a).getX(),
						personList.get(a).getY(), this);
			else if (personList.get(a).getPersonMove() == 'O') {

			} else
				gContext.drawImage(person[personList.get(a).getMoveCount() / 7], personList.get(a).getX(),
						personList.get(a).getY(), this);
		}
		
		gContext.drawImage(snow,0,0,1000,710,this);
		g.drawImage(buffer, 0, 0, this);
	}

	public void openElevator(Elevator e) {
		e.open();
		e.setOpenCount(59);
	}

	public void boardElevator(int i) {
		char t;

		if (i == 0)
			t = 'A';
		else if (i == 1)
			t = 'B';
		else
			t = 'C';

		for (int j = 0; j < personList.size(); j++) {
			if (personList.get(j).getPersonMove() == t) {
				personList.get(j).setDooropen(true);
				personList.get(j).setState(0);
			}
		}
	}
	public void eledoor(int b){
		for (int j = 0; j < personList.size(); j++) {
			if( personList.get(j).elereach==true&&personList.get(j).enter==false){
				elevators[b].numofpeople++;
				openElevator(elevators[b]);
				boardElevator(b);
				personList.get(j).enter=true;
			}
		}
	}
	public void InorOut(int a, int b) {
		//System.out.println(a);
		for (int j = 0; j < personList.size(); j++) {
			if (personList.get(j).isElevator() == true
					&& floor[personList.get(j).wantFloor].position.y == a
					&& personList.get(j).isReach()==false
					&& personList.get(j).getElelocation() == elevators[b].getX()) {

				openElevator(elevators[b]);
				boardElevator(b);
				personList.get(j).setReach(true);
				personList.get(j).setY(a);
				personList.get(j).setX(elevators[b].getX());
				elevators[b].popTake(personList.get(j).wantFloor);
				elevators[b].numofpeople--;
				elevators[b].currWeight -= personList.get(j).weight;

				if (elevators[b].numofpeople == 0) {
					elevators[b].setDirection(0);
					elevators[b].setY(personList.get(j).getY()-4);
					elevators[b].state = 0;
				}
			}
		}

	}
}