import java.awt.Point;

public class Floor {

	public final Point position = new Point();

	public Point startPosition() {
		Point P = new Point();
		int x, y;
		x = position.x;
		y = position.y + 20;
		P.x = x;
		P.y = y;
		return P;
	}

	public Point selectPosition() {
		Point P = new Point();
		int x, y;
		x = position.x + 300;
		y = position.y + 20;
		P.x = x;
		P.y = y;
		return P;
	}

	public Point takeE1Position() {
		Point P = new Point();
		int x, y;
		x = position.x + 500;
		y = position.y + 20;
		P.x = x;
		P.y = y;
		return P;
	}

	public Point takeE2Position() {
		Point P = new Point();
		int x, y;
		x = position.x + 700;
		y = position.y + 20;
		P.x = x;
		P.y = y;
		return P;
	}

	public Point takeE3Position() {
		Point P = new Point();
		int x, y;
		x = position.x + 900;
		y = position.y + 20;
		P.x = x;
		P.y = y;
		return P;
	}

}
