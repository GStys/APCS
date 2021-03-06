import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PanelTester implements ActionListener, KeyListener, MouseListener {

	private DrawingPanel pane;
	private Graphics g;
	private Timer t;
	public static GJerald gary;
	public static GJerald jerry;
	public static Ammu ni, ne, na, no, nu;
	public static Ammu mi, me, ma, mo, mu;
	public static Ammu[] jquiver = {ni, ne, na, no, nu};
	public static Ammu[] gquiver = {mi, me, ma, mo, mu};
	public int countAmmu = 0;
	public static Barry a, b, c, d, e, f, h, i, j, k, l, m, n, o, p, q, r, s, tt, u, v, w, x, y, z, aa;
	public Barry[] bushel = {a, b, c, d, e, f, h, i, j, k, l, m, n, o, p, q, r, s, tt, u, v, w, x, y, z, aa };
	private BufferedImage backgroundImg;
	public BufferedImage gvictory;
	public BufferedImage bvictory;
	public BufferedImage tieGame;
	public static boolean end = false;
	public static Title tim;
	public static int bouncem = 6;
	
	public PanelTester() {
		pane = new DrawingPanel(512, 448);
		g = pane.getGraphics();
		tim = new Title(this, g);
		a = new Barry(this, g, 20, 0, 480, 20, 7); bushel[0] = a;
		b = new Barry(this, g, 0, 20, 20, 428, 6); bushel[1] = b;
		c = new Barry(this, g, 20, 428, 492, 20, 8); bushel[2] = c;
		d = new Barry(this, g, 492, 20, 20, 408, 9); bushel[3] = d;
		e = new Barry(this, g, 100, 87, 20, 260, 10); bushel[4] = e;
		f = new Barry(this, g, 392, 87, 20, 260, 10); bushel[5] = f;
		h = new Barry(this, g, 206, 244, 40, 40, 3); bushel[6] = h;
		i = new Barry(this, g, 300, 187, 40, 40, 3); bushel[7] = i;
		j = new Barry(this, g, 180, 121, 40, 40, 3); bushel[8] = j;
		k = new Barry(this, g, 492, 0, 20, 20, 1); bushel[9] = k;
		l = new Barry(this, g, 0, 0, 20, 20, 1); bushel[10] = l;
		m = new Barry(this, g, 492, 428, 20, 20, 1); bushel[11] = m;
		n = new Barry(this, g, 0, 428, 20, 20, 1); bushel[12] = n;
		o = new Barry(this, g, 100, 87, 20, 20, 15); bushel[13] = o;
		p = new Barry(this, g, 100, 327, 20, 20, 14); bushel[14] = p;
		q = new Barry(this, g, 392, 87, 20, 20, 15); bushel[15] = q;
		r = new Barry(this, g, 392, 327, 20, 20, 14); bushel[16] = r;
		s = new Barry(this, g, 226, 244, 20, 20, 5); bushel[17] = s;
		tt = new Barry(this, g, 200, 121, 20, 20, 5); bushel[18] = tt;
		u = new Barry(this, g, 320, 187, 20, 20, 5); bushel[19] = u;
		v = new Barry(this, g, 206, 264, 20, 20, 4); bushel[20] = v;
		w = new Barry(this, g, 300, 207, 20, 20, 4); bushel[21] = w;
		x = new Barry(this, g, 180, 141, 20, 20, 4); bushel[22] = x;
		y = new Barry(this, g, 226, 264, 20, 20, 2); bushel[23] = y;
		z = new Barry(this, g, 320, 207, 20, 20, 2); bushel[24] = z;
		aa = new Barry(this, g, 200, 141, 20, 20, 2); bushel[25] = aa;
		jerry = new GJerald(this, g, 0);
		jerry.firstx = 450; jerry.firsty = 390;
		jerry.firstd = 0;
		jerry.reset();
		gary = new GJerald(this, g, 1);
		gary.firstx = 30; gary.firsty = 30;
		gary.firstd = 3;
		gary.reset();
		ni = new Ammu(this, g); ne = new Ammu(this, g); na = new Ammu(this, g); no = new Ammu(this, g); nu = new Ammu(this, g);
		mi = new Ammu(this, g); me = new Ammu(this, g); ma = new Ammu(this, g); mo = new Ammu(this, g); mu = new Ammu(this, g);
		gquiver[0] = ni; gquiver[1] = ne; gquiver[2] = na; gquiver[3] = no; gquiver[4] = nu;
		jquiver[0] = mi; jquiver[1] = me; jquiver[2] = ma; jquiver[3] = mo; jquiver[4] = mu;
		
		try {
			gvictory = ImageIO.read(getClass().getResource("GreenVictory.png"));
			bvictory = ImageIO.read(getClass().getResource("BlueVictory.png"));
			tieGame = ImageIO.read(getClass().getResource("TieScreen.png"));
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
	
	public void initTitle() {
		tim.draw();
		JPanel p = pane.getPanel();
		p.addMouseListener(this);
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
                finalImg.createGraphics().drawImage(imgs[(int) Math.random()*10%3], 32 * j, 28 * i, null);  
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
		tester.initTitle();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (tim.on) {
			tim.draw();
		} else {
			draw();
		}
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
		if ((e.getKeyCode() == 47) && (jerry.dieCount == 0)) {
			int p=0;
			while (p<jquiver.length) {
				Ammu temp = jquiver[p];
				if (temp.checkSelf() == false) {
					temp.setPos(jerry.getBarrelX(), jerry.getBarrelY());
					temp.firstx = jerry.getBarrelX(); temp.firsty = jerry.getBarrelY();
					temp.wreckSelf();
					temp.setFace(jerry.face);
					temp.flashCounter = 20;
					break;
				}
				else {	p++;	}
			}
		}
		if (e.getKeyCode() == 65) {	gary.left = true;	}
		if (e.getKeyCode() == 87) {	gary.up = true;	}
		if (e.getKeyCode() == 68) {	gary.right = true;	}
		if (e.getKeyCode() == 83) {	gary.down = true;	}
		if ((e.getKeyCode() == 32) && (gary.dieCount == 0)) {
			int q=0;
			while (q<gquiver.length) {
				Ammu temp = gquiver[q];
				if (temp.checkSelf() == false) {
					temp.setPos(gary.getBarrelX(), gary.getBarrelY());
					temp.firstx = gary.getBarrelX(); temp.firsty = gary.getBarrelY();
					temp.wreckSelf();
					temp.setFace(gary.face);
					temp.flashCounter = 20;
					break;
				}
				else {	q++;	}
			}
		}
		if ((e.getKeyCode() == 82) && (end == true)) {	restart();	}
		if ((e.getKeyCode() == 77) && (end == true)) {	tim.on = true; tim.mode = 0;	}
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

	@Override
	public void mouseClicked(MouseEvent e) {
		if (tim.on) {
			int x = e.getX()/pane.currentZoom;
			int y = e.getY()/pane.currentZoom;
			System.out.println(x + ", " + y);
			if ((y>335) && (y<365)) {
				if ((x>72) && (x<172)) {	tim.mode = 1; tim.startCounter = 30;	}
				else if ((x>200) && (x<295)) {	tim.mode = 2; tim.startCounter = 30;	}
				else if ((x>323) && (x<420)) {	tim.mode = 3; tim.startCounter = 30;	}
			}
			// TODO Auto-generated method stub
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void restart() {
		end = false;
		jerry.reset();
		gary.reset();
		for (int i=0; i<5; i++) {
			jquiver[i].wreckSelf();
			gquiver[i].wreckSelf();
		}
	}
	
	public void draw() {
		g.drawImage(backgroundImg, 0, 0, null);
		if (tim.mode == 2) {
			for (int i=0; i<gquiver.length; i++) {
				gquiver[i].noBounceCount();
				jquiver[i].noBounceCount();
			}
		}
		for (int i=0; i<bushel.length; i++) {
			Barry temp = bushel[i];
			temp.draw();
		}
		if (end == true) {
			if ((gary.dieCount != 0) && (jerry.dieCount != 0)) {	g.drawImage(tieGame, 0, 0, null);	}
			else if (gary.dieCount != 0) {	g.drawImage(gvictory, 0, 0, null);	}
			else if (jerry.dieCount != 0) {	g.drawImage(bvictory, 0, 0, null);	}
		}
		else {
			jerry.draw();
			gary.draw();
			for (int i=0; i<5; i++) {
				gquiver[i].firstx = gary.getBarrelX(); gquiver[i].firsty = gary.getBarrelY();
				jquiver[i].firstx = jerry.getBarrelX(); jquiver[i].firsty = jerry.getBarrelY();
			}
			ni.draw(); ne.draw(); na.draw(); no.draw(); nu.draw();
			mi.draw(); me.draw(); ma.draw(); mo.draw(); mu.draw();
		} 
	}
}
