import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GJerald {
	
	private Graphics g;
	private PanelTester tester;
	private int player;
	public int face = 39;
	public  double firstx;
	public  double firsty;
	public  double firstd;
	public double xval;
	public double rbound;
	public double yval;
	public double dbound;
	public double width;
	public double height;
	public double barrelx;
	public double barrely;
	BufferedImage[] imgs1;
	BufferedImage[] imgs2;
	BufferedImage[] imgsd1;
	BufferedImage[] imgsd2;
	BufferedImage[] bimgs1;
	BufferedImage[] bimgs2;
	BufferedImage[] bimgsd1;
	BufferedImage[] bimgsd2;
	BufferedImage[] death;
	BufferedImage blacking;
	public boolean diag = false;
	public int anim = 1;
	public boolean left = false, up = false, right = false, down = false;
	public int dieCount = 0;
	
	public GJerald(PanelTester tester, Graphics g, int p) {
		this.g = g;
		this.tester = tester;
		player = p;
		width = 30;
		height = 30;
		imgs1 = new BufferedImage[4];
		imgs2 = new BufferedImage[4];
		bimgs1 = new BufferedImage[4];
		bimgs2 = new BufferedImage[4];
		for (int i=37; i<41; i++) {
			try {
				imgs1[i-37] = ImageIO.read(getClass().getResource("tank" + i + "1.png"));
				imgs2[i-37] = ImageIO.read(getClass().getResource("tank" + i + "2.png"));
				bimgs1[i-37] = ImageIO.read(getClass().getResource("tank2-" + i + "1.png"));
				bimgs2[i-37] = ImageIO.read(getClass().getResource("tank2-" + i + "2.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		imgsd1 = new BufferedImage[4];
		imgsd2 = new BufferedImage[4];
		bimgsd1 = new BufferedImage[4];
		bimgsd2 = new BufferedImage[4];
		try {
			imgsd1[0] = ImageIO.read(getClass().getResource("tank-37381.png"));
			imgsd1[1] = ImageIO.read(getClass().getResource("tank-38391.png"));
			imgsd1[2] = ImageIO.read(getClass().getResource("tank-37401.png"));
			imgsd1[3] = ImageIO.read(getClass().getResource("tank-39401.png"));
			imgsd2[0] = ImageIO.read(getClass().getResource("tank-37382.png"));
			imgsd2[1] = ImageIO.read(getClass().getResource("tank-38392.png"));
			imgsd2[2] = ImageIO.read(getClass().getResource("tank-37402.png"));
			imgsd2[3] = ImageIO.read(getClass().getResource("tank-39402.png"));
			bimgsd1[0] = ImageIO.read(getClass().getResource("tank2-37381.png"));
			bimgsd1[1] = ImageIO.read(getClass().getResource("tank2-38391.png"));
			bimgsd1[2] = ImageIO.read(getClass().getResource("tank2-37401.png"));
			bimgsd1[3] = ImageIO.read(getClass().getResource("tank2-39401.png"));
			bimgsd2[0] = ImageIO.read(getClass().getResource("tank2-37382.png"));
			bimgsd2[1] = ImageIO.read(getClass().getResource("tank2-38392.png"));
			bimgsd2[2] = ImageIO.read(getClass().getResource("tank2-37402.png"));
			bimgsd2[3] = ImageIO.read(getClass().getResource("tank2-39402.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		death = new BufferedImage[5];
		try {
			for (int i=0; i<5; i++) {
				death[i] = ImageIO.read(getClass().getResource("explosion" + (i+1) + ".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			blacking = ImageIO.read(getClass().getResource("BlackKing.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public double getBarrelX() {
		if (face == 37) {	barrelx = xval - 10;	}
		if ((face == 38) || (face == 40)) {	barrelx = xval + 11;	}
		if (face == 39) {	barrelx = rbound;	}
		if ((face == 0) || (face == 2)) {	barrelx = xval-3;	}
		if ((face == 1) || (face == 3)) {	barrelx = rbound - 3;	}
		return barrelx;
	}
	
	public double getBarrelY() {
		if ((face == 37) || (face == 39)) {	barrely = yval + 9.5;	}
		if (face == 38) {	barrely = yval - 10;	}
		if (face == 40) {	barrely = dbound;	}
		if ((face == 0) || (face == 1)) {	barrely = yval-2;	}
		if ((face == 2) || (face == 3)) {	barrely = dbound - 6;	}
		return barrely;
	}
	
	public void wreckSelf() {
		left = false;
		up = false;
		right = false;
		down = false;
	}
	
	public void setPos(double x, double y) {
		xval = x;
		yval = y;
		rbound = xval + width;
		dbound = yval + height;
	}
	
	public void place() {
		double a = xval, b = yval;
		Image temp = blacking;
		if (anim <= 2) {
			if (player == 0) {
				if (diag == true) {	temp = imgsd1[face];	}
				else {	temp = imgs1[face-37];	}
			}
			else {
				if (diag == true) {	temp = bimgsd1[face];	}
				else {	temp = bimgs1[face-37];	}
			}
		}
		if (anim > 2) {
			if (player == 0) {
				if (diag == true) {	temp = imgsd2[face];	}
				else {	temp = imgs2[face-37];	}
			}
			else {
				if (diag == true) {	temp = bimgsd2[face];	}
				else {	temp = bimgs2[face-37];	}
			}
		}
		g.drawImage(temp, (int) a, (int) b, null);
		if (anim == 4) {	anim = 0;	}
		anim++;
	}
	
	public void moveTo(double x, double y) {
		for (int i=0; i<tester.bushel.length; i++) {
			if (checkBarry(tester.bushel[i], x, y) == true) {
				return;
			}
		}
		setPos(x, y);
	}
	
	public void moveLine(int direction) {
		face = direction;
		diag = false;
		if ((left == true) && (up == true)) {	face = 0; diag = true;	}
		if ((up == true) && (right == true)) {	face = 1; diag = true;	}
		if ((left == true) && (down == true)) {	face = 2; diag = true;	}
		if ((down == true) && (right == true)) {	face = 3; diag = true;	}
		if (direction == 39) {	moveTo(xval+0.5, yval);	}
		if (direction == 38) {	moveTo(xval, yval-0.5);	}
		if (direction == 37) {	moveTo(xval-0.5, yval);	}
		if (direction == 40) {	moveTo(xval, yval+0.5);	}
	}
	
	public boolean checkBarry(Barry b, double x, double y) {
		if (((y+height) >= b.yval) && (y <= b.dbound)) {
			if (((x+width) >= b.xval) && (x <= b.rbound)) {
				return true;
			}
		}
		return false;
	}
	
	public void kill() {
		wreckSelf();
		dieCount = 1;
	}
	
	private int deathCoolDown = 100;
	
	public void die() {
		if (dieCount <= 5) {
			double a = xval, b = yval;
			g.drawImage(death[dieCount-1], (int) a, (int) b, null);
			if (deathCoolDown != 0) {	deathCoolDown--;	}
			else {	
				deathCoolDown = 100;
				dieCount++;
			}
		}
		else if (dieCount > 5) {
			tester.end = true;
		}
	}
	
	public void reset() {
		dieCount = 0;
		face = (int) firstd;
		diag = true;
		setPos(firstx, firsty);
		wreckSelf();
	}

	public void draw() {
		if (dieCount == 0) {
			if (left == true) {	moveLine(37);	}
			if (up == true) {	moveLine(38);	}
			if (right == true) {	moveLine(39);	}
			if (down == true) {	moveLine(40);	}
			place();
		}
		else {	die();	}
	}
		
}
