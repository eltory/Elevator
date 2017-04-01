import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

public class TouchScreen extends JFrame {

   private JPanel contentPane;
   private String floor;
   public static TouchScreen frame;
   public int selFloor = 0, canElev = 0;
   public Image img[] = new Image[12];
   public Image finger = null;
   public Point[] point = new Point[8];
   public int tmp = 0;
   public File in = null;
   
   /**
    * Create the frame.
    */
   public TouchScreen() {
      super("TouchScreen");

      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setBounds(1000, 100, 765, 620);
      getContentPane().setLayout(null);
      setResizable(false);
      contentPane = new JPanel();
      contentPane.setLayout(new BorderLayout(0, 0));
      setContentPane(contentPane);
   }
   
   public void aniRun()
   {
      floor = new String();
      floor = "B2B11F2F3F4F5F6FNFE1E2E3";

      point[0] = new Point();
      point[0].x = 140;
      point[0].y = 400;
      point[1] = new Point();
      point[1].x = 140;
      point[1].y = 300;
      point[2] = new Point();
      point[2].x = 260;
      point[2].y = 300;
      point[3] = new Point();
      point[3].x = 260;
      point[3].y = 400;
      point[4] = new Point();
      point[4].x = 380;
      point[4].y = 300;
      point[5] = new Point();
      point[5].x = 380;
      point[5].y = 400;
      point[6] = new Point();
      point[6].x = 500;
      point[6].y = 300;
      point[7] = new Point();
      point[7].x = 500;
      point[7].y = 400;

      try { img[8] = ImageIO.read(new File(floor.substring(2 * 8, 2 * (8 + 1)) + ".png")); } catch (IOException e) { e.printStackTrace(); }
      try { img[this.selFloor] = ImageIO.read(new File(floor.substring(2 * this.selFloor, 2 * (this.selFloor + 1)) + ".png")); } catch (IOException e) { e.printStackTrace(); }
      try { img[this.canElev] = ImageIO.read(new File(floor.substring(2 * this.canElev, 2 * (this.canElev + 1)) + ".png")); } catch (IOException e) { e.printStackTrace(); }
      try { finger = ImageIO.read(new File("pointer.png")); } catch (IOException e) { e.printStackTrace(); }
   
   }
      
   public void paint(Graphics g)
   {
      Image buffer = createImage(765, 615);
      Graphics gContext = buffer.getGraphics();
      if (tmp == 0) {
         gContext.drawImage(img[8], 0, 20, this);
         g.drawImage(buffer, 0, 10, 765, 615, this);
         try { Thread.sleep(500); }catch(InterruptedException e){ e.printStackTrace(); }
         tmp = 1; 
         repaint(); }
      else if (tmp == 1) {
        gContext.drawImage(img[8], 0, 20, this);
         gContext.drawImage(finger, point[selFloor].x, point[selFloor].y, 150, 230, this);
         g.drawImage(buffer, 0, 10, this);
         try { Thread.sleep(500); }catch(InterruptedException e){ e.printStackTrace(); }
         tmp = 2; 
         repaint();}
      else if (tmp == 2) {
         gContext.drawImage(img[8], 0, 20, this);
         gContext.drawImage(finger, point[selFloor].x, point[selFloor].y, 150, 230, this);
         gContext.drawImage(img[selFloor], 0, 20, this);
         g.drawImage(buffer, 0, 10, this);
         try { Thread.sleep(500); }catch(InterruptedException e){ e.printStackTrace(); }
         tmp = 3; 
         repaint();}
      else {
         gContext.drawImage(img[8], 0, 20, this);
         gContext.drawImage(img[selFloor], 0, 20, this);
         gContext.drawImage(finger, point[selFloor].x, point[selFloor].y, 150, 230, this);
         gContext.drawImage(img[canElev], 0, 20, this);
         g.drawImage(buffer, 0, 10, this);
      }
   }
   
   public void setOption(int f, int e) {
      this.selFloor = f;
      this.canElev = e + 9;
      aniRun();
   }

   public static void visible() {
      frame.setVisible(true);
      frame.aniRun();
   }
   
}