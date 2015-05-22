import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Title {
	
	private Graphics g;
	private PanelTester tester;
	public static boolean on = true;
	public int mode = 0;
	public int startCounter = -1;
	public static BufferedImage titleScreenImage;
	public static BufferedImage normUnpressed;
	public static BufferedImage normPressed;
	public static Image norman;
	public static BufferedImage bounceUnpressed;
	public static BufferedImage bouncePressed;
	public static Image bonny;
	public static BufferedImage crazUnpressed;
	public static BufferedImage crazPressed;
	public static Image clayton;
	
	public Title(PanelTester tester, Graphics g) {
		this.g = g;
		this.tester = tester;
		try {
			titleScreenImage = ImageIO.read(getClass().getResource("TitleScreen.png"));
			normUnpressed = ImageIO.read(getClass().getResource("normal_unpressed.png"));
			normPressed = ImageIO.read(getClass().getResource("normal_pressed.png"));
			bounceUnpressed = ImageIO.read(getClass().getResource("bouce_unpressed.png"));
			bouncePressed = ImageIO.read(getClass().getResource("bounce_pressed.png"));
			crazUnpressed = ImageIO.read(getClass().getResource("crazy_unpressed.png"));
			crazPressed = ImageIO.read(getClass().getResource("crazy_pressed.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void resetButtons() {
		norman = normUnpressed;
		bonny = bounceUnpressed;
		clayton = crazUnpressed;
	}
	
	public void draw() {
		g.drawImage(titleScreenImage, 0, 0, null);
		resetButtons();
		if (mode == 1) {	norman = normPressed;	}
		if (mode == 2) {	bonny = bouncePressed;	}
		if (mode == 3) {	clayton = crazPressed;	}
		g.drawImage(norman, 72, 300, 100, 100, null);
		g.drawImage(bonny, 197, 332, 100, 100, null);
		g.drawImage(clayton, 322, 268, 100, 100, null);
		startCounter--;
		if (startCounter == 0) {
			on = false;
			if (mode == 2) {	tester.bouncem = 2;	}
			else {	tester.bouncem = 6;	}
		}
	}

}
