public class Main {

	public static void main(String[] args) {
		ControlMain sh = new ControlMain();
		Thread thread = new Thread(sh);
		sh.setVisible(true);
		thread.start();
	}
}