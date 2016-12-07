
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
	private Image bgImg = Toolkit.getDefaultToolkit().getImage("Back.png");
	private Image bgImg2 = Toolkit.getDefaultToolkit().getImage("Back2.png");
	private static LinkedList<Person> personList = new LinkedList<Person>();
	private Elevator[] elevators = new Elevator[3];

	private Person p = new Person();
	private Floor[] floor = new Floor[8];
	int i = 0;
	int cnt;
	int ele;
	int gg = 0;
	public static Image[] person = new Image[10];
	public static Image[] r_person = new Image[10];
	Timer t = new Timer();

	public ControlMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocation(0, 0);
		setBounds(20, 20, 1000, 710);
		setLayout(null);

		for (int i = 0; i < 3; i++) {
			elevators[i] = new Elevator(663 + i * 49, 467, i);
		}

		i = 0;
		for (Floor s : floor) {
			floor[i] = new Floor();
			floor[i].position.x = 250;
			i++;
		}
		floor[0].position.y = 102;
		floor[1].position.y = 181;
		floor[2].position.y = 261;
		floor[3].position.y = 340;
		floor[4].position.y = 420;
		floor[5].position.y = 499;
		floor[6].position.y = 578;
		floor[7].position.y = 657;
		i = 0;

		tk = Toolkit.getDefaultToolkit();

		// create person's image
		for (int i = 0; i < 5; i++) {
			person[i] = tk.createImage("Person" + (4 - i) + ".png");
			r_person[i] = tk.createImage("Person" + (4 - i) + "_r.png");
		}
		i = 0;
		this.setVisible(true);
	}

	@Override
	public void run() {
		while (true) {
			try {
				if (gg % 1000 == 0)
					t.schedule(new TimerTask() {
						@Override
						public void run() {
							p = new Person();
							p.setX(250);
							p.setY(floor[p.currFloor].position.y - 25);
							personList.add(p);
							cnt++;
						}
					}, 0, 10000);
				Thread.sleep(15);
				gg++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			for(int b=0;b<3;b++){
				if(elevators[b].getY() > elevators[b].getTopFloor()){
					elevators[b].setY(elevators[b].getY()-1);
					if(elevators[b].getY() == elevators[b].getTopFloor()){
						openElevator(elevators[i]);
						boardElevator(b);
					}
				}
				else if(elevators[b].getY() < elevators[b].getTopFloor()){
					elevators[b].setY(elevators[b].getY()+1);
					if(elevators[b].getY() == elevators[b].getTopFloor()){
						openElevator(elevators[i]);
						boardElevator(b);
					}
				}
			}

			for (int a = 0; a < cnt; a++) {
				personList.get(a).moveForward();
				if (personList.get(a).getX() == 400 && personList.get(a).isTouchscreen() == false) {
					//TouchScreen ts = new TouchScreen(); 
					personList.get(a).setTouchscreen(true);
				}
				if(personList.get(a).isTouchscreen() == true && personList.get(a).isElevator()==false){
					for(int i=0;i<3;i++){
						System.out.println(i+" "+elevators[i].getDirection());
						if(elevators[i].canTake(personList.get(a).weight) == true){
							if(elevators[i].getDirection()==0){				
								elevators[i].setDirection(personList.get(a).getDirection());
								elevators[i].pushTake(personList.get(a).wantFloor);
								elevators[i].takeIn(personList.get(a).weight);	
								elevators[i].setTopFloor(floor[personList.get(a).currFloor].position.y-32);
								personList.get(a).setElelocation(elevators[i].getX());
								personList.get(a).setElevator(true);
								
								//System.out.println(a+" "+personList.get(a).getElelocation());
								if(personList.get(a).getElelocation()==663)
									personList.get(a).setPersonMove('A');
								else if(personList.get(a).getElelocation()==712)
									personList.get(a).setPersonMove('B');
								else
									personList.get(a).setPersonMove('C');
								break;
							}
							/*else if(elevators[i].getDirection()==1){
								if(elevators[i].getDirection()==personList.get(a).getDirection()){
									elevators[i].pushTake(personList.get(a).wantFloor);
									elevators[i].takeIn(personList.get(a).weight);	
									elevators[i].setTopFloor(floor[personList.get(a).currFloor].position.y-32);
									personList.get(a).setElelocation(elevators[i].getX());
									personList.get(a).setElevator(true);
									break;
								}
							}
							else{
								if(elevators[i].getDirection()==personList.get(a).getDirection()){
									elevators[i].pushTake(personList.get(a).wantFloor);
									elevators[i].takeIn(personList.get(a).weight);	
									elevators[i].setTopFloor(floor[personList.get(a).currFloor].position.y-32);
									personList.get(a).setElelocation(elevators[i].getX());
									personList.get(a).setElevator(true);
									break;
								}
							}*/
						}
					}
				}
				if(personList.get(a).isElevator()==true){
					if(personList.get(a).getX() == personList.get(a).getElelocation())
						personList.get(a).setState(0);
				}
				if(personList.get(a).isDooropen() == true) {
					personList.get(a).setPersonMove('O'); 
					personList.get(a).setDooropen(false);
				}
				// when getting off the elevator
				if(personList.get(a).isReach() == true) { 
					personList.get(a).setPersonMove('R');
					personList.get(a).setReach(false);
				}
				if(personList.get(a).getPersonMove()=='R'){
					personList.get(a).moveBackward();
				}
			}
			repaint();
		}
	}

	public void paint(Graphics g) {
		Image buffer = createImage(1000, 710);
		Graphics gContext = buffer.getGraphics();
		gContext.drawImage(bgImg, 0, 20, this);
		gContext.drawImage(bgImg2, 0, 20, this);
		for (int i = 0; i < 3; i++)
			gContext.drawImage(elevators[i].getImg(), elevators[i].getX(), elevators[i].getY(), this);

		for (int a = 0; a < cnt; a++){
			//System.out.println(a+" "+personList.get(a).getPersonMove());
			if(personList.get(a).getPersonMove()=='R')
				gContext.drawImage(r_person[personList.get(a).getMoveCount() / 5], personList.get(a).getX(),personList.get(a).getY(), this);
			else if(personList.get(a).getPersonMove()=='O'){

			}
			else
				gContext.drawImage(person[personList.get(a).getMoveCount() / 5], personList.get(a).getX(),personList.get(a).getY(), this);
		}
		g.drawImage(buffer, 0, 0, this);
	}

	public void openElevator(Elevator e) {
		e.open();
		e.setOpenCount(49);
	}
	public void boardElevator(int i) {
		char t;

		if(i == 0) t = 'A';
		else if(i == 1) t = 'B';
		else t = 'C';
		
		for(int j = 0; j < personList.size(); j++) {
			if(personList.get(j).getPersonMove() == t){
				personList.get(j).setDooropen(true);
				personList.get(j).setState(0);
			}
		}  
	}
}