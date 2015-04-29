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

public class PanelTester implements ActionListener, KeyListener {

	private DrawingPanel pane;
	private Graphics g;
	private Timer t;
	public static GJerald gary;
	public static GJerald jerry;
	public static Ammu ni, ne, na, no, nu;
	public static Ammu mi, me, ma, mo, mu;
	public int countAmmu = 0;
	public static Barry a, b, c, d, e, f, h, i, j;
	public Barry[] bushel = {a, b, c, d, e, f, h, i, j};
	private BufferedImage backgroundImg;
	BufferedImage gvictory;
	BufferedImage bvictory;
	public static boolean end = false;
	
	public PanelTester() {
		pane = new DrawingPanel(512, 448);
		g = pane.getGraphics();
		a = new Barry(this, g, 0, 0, 512, 20); bushel[0] = a;
		b = new Barry(this, g, 0, 20, 20, 428); bushel[1] = b;
		c = new Barry(this, g, 20, 428, 492, 20); bushel[2] = c;
		d = new Barry(this, g, 492, 20, 20, 408); bushel[3] = d;
		e = new Barry(this, g, 100, 87, 20, 260); bushel[4] = e;
		f = new Barry(this, g, 392, 87, 20, 260); bushel[5] = f;
		h = new Barry(this, g, 206, 244, 40, 40); bushel[6] = h;
		i = new Barry(this, g, 300, 187, 40, 40); bushel[7] = i;
		j = new Barry(this, g, 180, 121, 40, 40); bushel[8] = j;
		jerry = new GJerald(this, g, 0);
		jerry.setPos(30, 30);
		jerry.diag = true;
		jerry.face = 3;
		gary = new GJerald(this, g, 1);
		gary.setPos(445, 390);
		gary.diag = true;
		gary.face = 0;
		ni = new Ammu(this, g); ne = new Ammu(this, g); na = new Ammu(this, g); no = new Ammu(this, g); nu = new Ammu(this, g);
		mi = new Ammu(this, g); me = new Ammu(this, g); ma = new Ammu(this, g); mo = new Ammu(this, g); mu = new Ammu(this, g);
		try {
			gvictory = ImageIO.read(getClass().getResource("GreenVictory.png"));
			bvictory = ImageIO.read(getClass().getResource("BlueVictory.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		initTimer();
		JPanel panel = pane.getPanel();
		panel.addKeyListener(this);
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		initBackground();
		draw();
	}
	
	public Barry getBarry() {
		return a;
	}
	
	public GJerald getJerry() {
		return jerry;
	}
	
	private void initBackground() {
		BufferedImage[] imgs = new BufferedImage[3];
		for (int i = 1; i < 4; i++) {
			try {
				imgs[i-1] = ImageIO.read(getClass().getResource("backgroundtile" + i + ".png"));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		BufferedImage finalImg = new BufferedImage(512, 448, imgs[0].getType());  
		  
        int num = 0;  
        for (int i = 0; i < 16; i++) {  
            for (int j = 0; j < 16; j++) {  
                finalImg.createGraphics().drawImage(imgs[(int) Math.random()*10], 32 * j, 28 * i, null);  
                num++;  
            }  
        } 
        backgroundImg = finalImg;
		// TODO Auto-generated method stub
		
	}

	private void initTimer() {
		t = new Timer(2, this);
		t.setInitialDelay(190);
		t.start();
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		PanelTester tester = new PanelTester();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		draw();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 37) {	jerry.left = true;	}
		if (e.getKeyCode() == 38) {	jerry.up = true;	}
		if (e.getKeyCode() == 39) {	jerry.right = true;	}
		if (e.getKeyCode() == 40) {	jerry.down = true;	}
		if (e.getKeyCode() == 47) {
			if (ni.checkSelf() == false) {
				ni.setPos(jerry.getBarrelX(), jerry.getBarrelY());
				ni.wreckSelf();
				ni.setFace(jerry.face);
			}
			else if (ne.checkSelf() == false) {
				ne.setPos(jerry.getBarrelX(), jerry.getBarrelY());
				ne.wreckSelf();
				ne.setFace(jerry.face);
			}
			else if (na.checkSelf() == false) {
				na.setPos(jerry.getBarrelX(), jerry.getBarrelY());
				na.wreckSelf();
				na.setFace(jerry.face);
			}
			else if (no.checkSelf() == false) {
				no.setPos(jerry.getBarrelX(), jerry.getBarrelY());
				no.wreckSelf();
				no.setFace(jerry.face);
			}
			else if (nu.checkSelf() == false) {
				nu.setPos(jerry.getBarrelX(), jerry.getBarrelY());
				nu.wreckSelf();
				nu.setFace(jerry.face);
			}
		}
		if (e.getKeyCode() == 65) {	gary.left = true;	}
		if (e.getKeyCode() == 87) {	gary.up = true;	}
		if (e.getKeyCode() == 68) {	gary.right = true;	}
		if (e.getKeyCode() == 83) {	gary.down = true;	}
		if (e.getKeyCode() == 32) {
			if (mi.checkSelf() == false) {
				mi.setPos(gary.getBarrelX(), gary.getBarrelY());
				mi.wreckSelf();
				mi.setFace(gary.face);
			}
			else if (me.checkSelf() == false) {
				me.setPos(gary.getBarrelX(), gary.getBarrelY());
				me.wreckSelf();
				me.setFace(gary.face);
			}
			else if (ma.checkSelf() == false) {
				ma.setPos(gary.getBarrelX(), gary.getBarrelY());
				ma.wreckSelf();
				ma.setFace(gary.face);
			}
			else if (mo.checkSelf() == false) {
				mo.setPos(gary.getBarrelX(), gary.getBarrelY());
				mo.wreckSelf();
				mo.setFace(gary.face);
			}
			else if (mu.checkSelf() == false) {
				mu.setPos(gary.getBarrelX(), gary.getBarrelY());
				mu.wreckSelf();
				mu.setFace(gary.face);
			}
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 37) {	jerry.left = false;	}
		if (e.getKeyCode() == 38) {	jerry.up = false;	}
		if (e.getKeyCode() == 39) {	jerry.right = false;	}
		if (e.getKeyCode() == 40) {	jerry.down = false;	}
		if (e.getKeyCode() == 65) {	gary.left = false;	}
		if (e.getKeyCode() == 87) {	gary.up = false;	}
		if (e.getKeyCode() == 68) {	gary.right = false;	}
		if (e.getKeyCode() == 83) {	gary.down = false;	}
		// TODO Auto-generated method stub
		
	}
	
	public void draw() {
		g.drawImage(backgroundImg, 0, 0, null);
		for (int i=0; i<bushel.length; i++) {
			Barry temp = bushel[i];
			temp.draw();
		}
		if (end == true) {
			String winner;
			if (gary.dieCount != 0) {	g.drawImage(gvictory, 0, 0, null);	}
			else if (jerry.dieCount != 0) {	g.drawImage(bvictory, 0, 0, null);	}
		}
		else {
			jerry.draw();
			gary.draw();
			ni.draw(); ne.draw(); na.draw(); no.draw(); nu.draw();
			mi.draw(); me.draw(); ma.draw(); mo.draw(); mu.draw();
		}
	}
}
