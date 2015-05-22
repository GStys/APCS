import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ammu {
	
	public double firstx, firsty;
	public double width, height;
	public double xval, yval;
	public double rbound, dbound;
	public int bounceCount;
	private PanelTester tester; 
	private Graphics g;
	public int face;
	public boolean left = false, up = false, right = false, down = false;
	public int crazy;
	BufferedImage bullet;
	BufferedImage[] flash;
	BufferedImage[] flashd;
	public int flashCounter = 0;
	double a, b;
	Image theFlash;
	
	public Ammu(PanelTester tester, Graphics g) {
		this.g = g;
		this.tester = tester;
		width = 10;
		height = 10;
		bounceCount = tester.bouncem;
		flash = new BufferedImage[4];
		flashd = new BufferedImage[4];
		try {
			bullet = ImageIO.read(getClass().getResource("projectile.png"));
			for (int i=0; i<4; i++) {
				flash[i] = ImageIO.read(getClass().getResource("Muzzleflash-" + (i+37) + ".png"));
				flashd[i] = ImageIO.read(getClass().getResource("Muzzleflash-" + (i) + ".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setFace(int direction) {
		if (direction == 37) {	face = 37; left = true;	}
		if (direction == 38) {	face = 38; up = true;	}
		if (direction == 39) {	face = 39; right = true;	}
		if (direction == 40) {	face = 40; down = true;	}
		if (direction == 0) {	face = 0; left = true; up = true;	}
		if (direction == 1) {	face = 1; up = true; right = true;	}
		if (direction == 2) {	face = 2; left = true; down = true;	}
		if (direction == 3) {	face = 3; right = true; down = true;	}
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
	
	public void muzzFlash() {
		if (flashCounter > 0) {
			if (face == 0) {	a = firstx-27; b = firsty-25;	}
			if (face == 1) {	a = firstx+4; b = firsty-25;	}
			if (face == 2) {	a = firstx-27; b = firsty+3;	}
			if (face == 3) {	a = firstx+4; b = firsty+4;	}
			if (face == 37) {	a = firstx-20; b = firsty-11;	}
			if (face == 38) {	a = firstx-11; b = firsty-17;	}
			if (face == 39) {	a = firstx; b = firsty-9;	}
			if (face == 40) {	a = firstx-11; b = firsty-2;	}
			if (face > 4) {	theFlash = flash[face-37];	}
			else {	theFlash = flashd[face];	}
			g.drawImage(theFlash, (int) a, (int) b, null);
			flashCounter--;
		}
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
	
	public void noBounceCount() {
		bounceCount = 2;
	}
	
	public void moveTo(double x, double y) {
		for (int i=0; i<tester.bushel.length; i++) {
			if (checkBarry(tester.bushel[i], x, y) == true) {
				if (bounceCount == 3) {
					bounceCount = 6;
					wreckSelf();
					return;
				}
				if (((y+5) >= tester.bushel[i].yval) && ((y+height-5) <= (tester.bushel[i].yval + tester.bushel[i].height))) {
					if (left == true) {	left = false; right = true;	}
					else if (right == true) {	right = false; left = true;	}
					bounceCount--;
				}
				else if (((x+5) >= tester.bushel[i].xval) && ((x+width-5) <= (tester.bushel[i].xval + tester.bushel[i].width))) {
					if (up == true) {	up = false; down = true;	}
					else if (down == true) {	down = false; up = true;	}
					bounceCount--;
				}
				else {
					if (up == true) {	up = false; down = true;	}
					else if (down == true) {	down = false; up = true;	}
					if (left == true) {	left = false; right = true;	}
					else if (right == true) {	right = false; left = true;	}
					bounceCount--;
				}
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
		if (direction == 39) {	moveTo(xval+0.7, yval);	}
		if (direction == 38) {	moveTo(xval, yval-0.7);	}
		if (direction == 37) {	moveTo(xval-0.7, yval);	}
		if (direction == 40) {	moveTo(xval, yval+0.7);	}
	}
	
	public void draw() {
		muzzFlash();
		if (left == true) {	moveLine(37); place();	}
		if (up == true) {	moveLine(38); place();	}
		if (down == true) {	moveLine(40); place();	}
		if (right == true) {	moveLine(39); place();	}
	}
}
