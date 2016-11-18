import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new TouchScreen();
					frame.setVisible(true);
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(null);
	

		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
				aniRun();
	}

	public void aniRun() {
		floor = new String();
		floor = "NF1F2F3F4F5F6FB1B2E1E2E3";

		ImageIcon[] img = new ImageIcon[12];

		for (int i = 0; i < 12; ++i) {
			img[i] = new ImageIcon(floor.substring(2 * i, 2 * (i + 1)) + ".png");
		}

		JLabel jl = new JLabel();
		//jl.setBounds(0, 0, 0, 280);
		//jl.setPreferredSize(getSize());
		jl.setIcon(img[0]);
		JPanel panel = new JPanel();
		panel.add(jl);
		
		Timer loop = new Timer();
		loop.schedule(new TimerTask() {
			@Override
			public void run() {
				jl.setIcon(img[selFloor]);
			}
		}, 1500);

		loop.schedule(new TimerTask() {
			@Override
			public void run() {
				jl.setIcon(img[canElev]);
			}
		}, 3000);
		panel.setBounds(0, 0, 500, 500);
		contentPane.add(panel);
		// Timer loop = new Timer();
		loop.schedule(new TimerTask() {
			@Override
			public void run() {
				setVisible(false);
			}
		}, 4500);
	}

	public void setOption(int f, int e){
		this.selFloor = f;
		this.canElev = e + 9; 
		aniRun();
	}
	

	public static void visible() {
		frame.setVisible(true);
		frame.aniRun();
	}
}
