
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
	   private JButton B2, B1, F1, F2, F3, F4, F5, F6;
	   private Image bgImg = Toolkit.getDefaultToolkit().getImage("BG.png");
	   private static LinkedList<Person> personList = new LinkedList<Person>();
	   private Elevator[] elevators = new Elevator[3];

	   private Person p = new Person();
	   private Floor[] floor = new Floor[8];
	   int i = 0;
	   int cnt;
	   int ele;
	   int gg = 0;
	   public static Image[] person = new Image[9];
	   Timer t = new Timer();
	/*
	 * public static void main(String[] arguments) {
	 * 
	 * ControlMain frame = new ControlMain(); //frame.setVisible(true);
	 * 
	 * 
	 * }
	 */

	public ControlMain() {
		// bgImg.setAccelerationPriority(ICONIFIED);
		// setVisible(true);//getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocation(0, 0);
		setBounds(20, 20, 1000, 710);
		setLayout(null);
		// setComponentZOrder(this,0);
		/*
		 * ImageIcon kj = new ImageIcon("BG1.png"); JPanel jp = new JPanel(){
		 * public void paintComponent(Graphics g){ Dimension d = getSize();
		 * g.drawImage(kj.getImage(), 0, 0,d.width,d.height, null);
		 * setOpaque(true); super.paintComponent(g); } }; jp.setBounds(0, 0,
		 * 1000, 700); this.add(jp);
		 */
		for (int i = 0; i < 3; i++) {
			elevators[i] = new Elevator(577 + i * 63, 468, i);
		}
		i = 0;
		for (Floor s : floor) {
			floor[i] = new Floor();
			floor[i].position.x = 250;
			i++;
		}
		floor[0].position.y=72;
		floor[1].position.y=150;
		floor[2].position.y=230;
		floor[3].position.y=310;
		floor[4].position.y=390;
		floor[5].position.y=468;
		floor[6].position.y=548;
		floor[7].position.y=626;

		tk=Toolkit.getDefaultToolkit();
		// create person's image 
		for(int i = 0; i < 5; i++){
			person[i]=tk.createImage("Person"+(4-i)+".png");
		}


		this.setVisible(true);
	}

	@Override
	public void run() {
		while (true) {
			try {
				  if(gg %500 == 0)
			            t.schedule(new TimerTask() {
			               @Override
			               public void run() {
			                  p = new Person();
			                  p.setX(250);
			                  p.setY(floor[p.currFloor].position.y);
			                  personList.add(p);
			                  cnt++;
			               }
			            }, 0, 7000);
			            Thread.sleep(20);
			            gg++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(int a=0;a<cnt;a++){
				if (personList.get(a).getX() == 400 || personList.get(a).getX() == elevators[ele].getX()) {
					if (p.isDisplayKeypad() == true) {
						long k = 3500;
						try {
							TouchScreen sc = new TouchScreen();
							sc.setVisible(true);
							for (int i = 0; i < 3; ++i) {
								if (elevators[i].canTake(personList.get(a).weight) == true) {
									sc.setOption(personList.get(a).wantFloor, i);
									elevators[i].currWeight+=personList.get(a).weight;
									ele = i;
									break;
								}
							}
							personList.get(a).setDisplayKeypad(true);
							Thread.sleep(k);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						if (personList.get(a).getX() == elevators[ele].getX()) {
							for (int i = 0; i < 3; i++) {
								if (i == ele) {
									if (elevators[i].getY() == personList.get(a).getY()) {
										if(personList.get(a).isEnter()==false){
											elevators[i].setY(personList.get(a).getY());
											openElevator(elevators[i]);
											personList.get(a).setCount(10);
											if(personList.get(a).wantFloor<7)
												personList.get(a).setY(floor[6-personList.get(a).wantFloor].position.y);
											else
												personList.get(a).setY(floor[personList.get(a).wantFloor-1].position.y);
											personList.get(a).setEnter(true);
										}
										else{
											openElevator(elevators[i]);
											personList.get(a).moveBackward();
										}
									} else if (elevators[i].getY() < p.getY())
										elevators[i].setY(elevators[i].getY() + 2);
									else {
										elevators[i].setY(elevators[i].getY() - 2);
									}
								}
							}
						} else {
							personList.get(a).setX(personList.get(a).getX() + 1);
						}
					}
				} 
				else {
					if(personList.get(a).isEnter()==false){
						personList.get(a).moveForward();
						personList.get(a).setX(personList.get(a).getX() + 1);
					}
					else{
						personList.get(a).moveBackward();
						personList.get(a).setX(personList.get(a).getX() - 1);
					}
				}
			}
			repaint();
		}
	}

	public void paint(Graphics g) {
		Image buffer = createImage(1000, 710);
		Graphics gContext = buffer.getGraphics();
		gContext.drawImage(bgImg, 0, 20, this);

		for (int i = 0; i < 3; i++)
			gContext.drawImage(elevators[i].getImg(), elevators[i].getX(), elevators[i].getY(), this);
		for(int a=0;a<cnt;a++)
			gContext.drawImage(person[personList.get(a).getMoveCount()/5], personList.get(a).getX(), personList.get(a).getY(), this);		
		//gContext.drawImage(p1.getImg(), p1.getX(), p1.getY(), this);
		g.drawImage(buffer, 0, 0, this);
	}

	public void openElevator(Elevator e) {
		if (e.getOpenCount() < 49)
			e.setOpenCount(e.getOpenCount() + 1);
	}

}
