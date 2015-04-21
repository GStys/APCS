import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ammu {
	
	public double firstx, firsty;
	public double width, height;
	public double xval, yval;
	public double rbound, dbound;
	private PanelTester tester; 
	private Graphics g;
	public boolean left = false, up = false, right = false, down = false;
	BufferedImage bullet;
	
	public Ammu(PanelTester tester, Graphics g) {
		this.g = g;
		this.tester = tester;
		width = 10;
		height = 10;
		try {
			bullet = ImageIO.read(getClass().getResource("projectile.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setFace(int direction) {
		if (direction == 37) {	left = true;	}
		if (direction == 38) {	up = true;	}
		if (direction == 39) {	right = true;	}
		if (direction == 40) {	down = true;	}
		if (direction == 0) {	left = true; up = true;	}
		if (direction == 1) {	up = true; right = true;	}
		if (direction == 2) {	left = true; down = true;	}
		if (direction == 3) {	right = true; down = true;	}
	}
	
	public boolean checkSelf() {
		if ((left == true) || (up == true) || (right == true) || (down == true)) {	return true;	}
		return false;
	}

	public void wreckSelf() {
		left = false;
		up = false;
		right = false;
		down = false;
	}
	
	public boolean checkBarry(Barry b, double x, double y) {
		if (((y+height) >= b.yval) && (y <= b.dbound)) {
			if (((x+width) >= b.xval) && (x <= b.rbound)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkGJerald(GJerald a, double x, double y) {
		if (((y+height) >= (a.yval + 7.5)) && (y <= (a.dbound - 7.5))) {
			if (((x+width) >= (a.xval + 7.5)) && (x <= (a.rbound - 7.5))) {
				return true;
			}
		}
		return false;
	}
	
	public void setPos(double x, double y) {
		xval = x;
		yval = y;
		rbound = xval + width;
		dbound = yval + height;
	}
	
	public void place() {
		g.setColor(Color.BLACK);
		double a = xval, b = yval;
		g.drawImage(bullet, (int) a, (int) b, null);
	}
	
	public void moveTo(double x, double y) {
		for (int i=0; i<tester.bushel.length; i++) {
			if (checkBarry(tester.bushel[i], x, y) == true) {
				wreckSelf();
				return;
			}
		}
		if (checkGJerald(tester.jerry, x, y) == true) {
			wreckSelf();
			tester.jerry.kill();
			return;
		}
		if (checkGJerald(tester.gary, x, y) == true) {
			wreckSelf();
			tester.gary.kill();
			return;
		}
		setPos(x, y);
	}
	
	public void moveLine(int direction) {
		if (direction == 39) {	moveTo(xval+0.5, yval);	}
		if (direction == 38) {	moveTo(xval, yval-0.5);	}
		if (direction == 37) {	moveTo(xval-0.5, yval);	}
		if (direction == 40) {	moveTo(xval, yval+0.5);	}
	}
	
	public void draw() {
		if (left == true) {	moveLine(37); place();	}
		if (up == true) {	moveLine(38); place();	}
		if (down == true) {	moveLine(40); place();	}
		if (right == true) {	moveLine(39); place();	}
	}
}