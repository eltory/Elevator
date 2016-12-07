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

public class TouchScreen extends JFrame {

	private JPanel contentPane;
	private String floor;
	private int fl;
	public static TouchScreen frame;
	public int selFloor, canElev;
	public Image img[] = new Image[12];
	public Image finger = null;
	private Toolkit tk;
	public Point[] point = new Point[8];

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// frame = new TouchScreen();
					// frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		aniRun();
	}

	public void aniRun() {
		floor = new String();
		floor = "NF1F2F3F4F5F6FB1B2E1E2E3";

		point[6] = new Point();
		point[6].x = 140;
		point[6].y = 300;
		point[7] = new Point();
		point[7].x = 140;
		point[7].y = 400;

		for (int i = 0; i < 8; i += 2) {
			point[i] = new Point();
			point[i].x = 140 + (i + 2 / 2) * 120;
			point[i].y = 300;
		}

		for (int i = 1; i < 8; i += 2) {
			point[i] = new Point();
			point[i].x = 140 + (i + 2 / 2) * 120;
			point[i].y = 400;
		}

		tk = Toolkit.getDefaultToolkit();

		for (int i = 0; i < 12; ++i) {
			img[i] = tk.createImage(floor.substring(2 * i, 2 * (i + 1)) + ".png");
		}

		finger = tk.createImage("pointer.png");

		repaint();
	}

	public void paint(Graphics g) {
		Image buffer = createImage(765, 615);
		Graphics gContext = buffer.getGraphics();
		try {
			gContext.drawImage(img[0], 0, 20, this);
			g.drawImage(buffer, 0, 10, this);
			Thread.sleep(500);
			gContext.drawImage(finger, point[selFloor].x, point[selFloor].y, 150, 230, this);
			g.drawImage(buffer, 0, 10, this);
			Thread.sleep(500);
			gContext.drawImage(img[selFloor], 0, 20, this);
			g.drawImage(buffer, 0, 10, this);
			Thread.sleep(500);
			gContext.drawImage(img[canElev], 0, 20, this);
			g.drawImage(buffer, 0, 10, this);
			Thread.sleep(400);
			this.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
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