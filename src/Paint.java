import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Paint extends JFrame{
	
	public Person person ;
	
	Paint(Person p){
		this.person = p;
		//this.setVisible(true);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(person.getImg(), person.getX(), person.getY(), this);	
	}
}



