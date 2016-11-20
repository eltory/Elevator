
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

	private JButton B2, B1, F1, F2, F3, F4, F5, F6;
	private Image bgImg = Toolkit.getDefaultToolkit().getImage("BG1.png");

	private Elevator[] elevators = new Elevator[3];
	private Image[] elevatorImg = new Image[3];
	private Person p1;
	private Floor[] floor = new Floor[8];
	int i = 0;
	int sX = 0;
	int sY = 0;
	int ele;

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
			// 1 = 770
			elevators[i] = new Elevator(577 + i * 63, 468, i);
			elevatorImg[i] = elevators[i].getImg();
		}
		i = 0;
		for (Floor s : floor) {
			floor[i] = new Floor();
			floor[i].position.x = sX;
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
		
		elevators[0].currWeight = 800;
		elevators[1].currWeight = 930;
		elevators[2].currWeight = 920;
		p1 = new Person();
		p1.setX(250);
		p1.setY(468);
		// p1.makeElevatorImg();
		this.setVisible(true);// p1.getImg().setAccelerationPriority(priority);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (p1.getX() == 400 || p1.getX() == elevators[ele].getX()) {
				if (p1.isDisplayKeypad() == false) {
					long k = 3500;
					try {
						TouchScreen sc = new TouchScreen();
						sc.setVisible(true);
						for (int i = 0; i < 3; ++i) {
							if (elevators[i].canTake(p1.weight) == true) {
								sc.setOption(p1.wantFloor, i);
								ele = i;
								break;
							}
						}
						p1.setDisplayKeypad(true);
						Thread.sleep(k);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					if (p1.getX() == elevators[ele].getX()) {
						for (int i = 0; i < 3; i++) {
							if (i == ele) {
								if (elevators[i].getY() == p1.getY()) {
									elevators[i].setY(p1.getY());
									openElevator(elevators[i]);
									p1.setCount(10);
									if(p1.wantFloor<7)
										p1.setY(floor[6-p1.wantFloor].position.y);
									else
										p1.setY(floor[p1.wantFloor-1].position.y);
								} else if (elevators[i].getY() < p1.getY())
									elevators[i].setY(elevators[i].getY() + 2);
								else {
									elevators[i].setY(elevators[i].getY() - 2);
								}
							}
						}
					} else {
						p1.setX(p1.getX() + 1);
					}
				}
			} else {
				p1.setX(p1.getX() + 1);
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

		gContext.drawImage(p1.getImg(), p1.getX(), p1.getY(), this);
		g.drawImage(buffer, 0, 0, this);
	}

	public void openElevator(Elevator e) {
		if (e.getOpenCount() < 49)
			e.setOpenCount(e.getOpenCount() + 1);
	}
}
