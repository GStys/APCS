import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Barry {
	private PanelTester tester; 
	private Graphics g;
	public int xval, rbound, yval, dbound;
	public int width, height;
	BufferedImage skin;
	
	public Barry(PanelTester tester, Graphics g, int x, int y, int w, int h) {
		this.tester = tester;
		this.g = g;
		try {
			skin = ImageIO.read(getClass().getResource("barry1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		width = w;
		height = h;
		xval = x;
		rbound = xval + width;
		yval = y;
		dbound = yval + height;
		g.setColor(Color.BLUE);
		g.fillRect(xval, yval, width, height);
	}
	
	public void draw() {
		g.setColor(Color.BLUE);
		g.fillRect(xval, yval, width, height);
		int a = 0;
		int b = 0;
		while (a < width) {
			while (b < height) {
				g.drawImage(skin, xval+a, yval+b, 32+a, 50+b, null);
				b+=50;
			}
			a+=32;
		}
	}
}
