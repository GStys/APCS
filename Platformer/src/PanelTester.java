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
	public static Ammu[] jmmu = {ni, ne, na, no, nu};
	public static Ammu[] gmmu = {mi, me, ma, mo, mu};
	public int countAmmu = 0;
	public static Barry a, b, c, d, e, f, h, i, j, k, l, m, n, o, p, q, r, s, tt, u, v, w, x, y, z, aa;
	public Barry[] bushel = {a, b, c, d, e, f, h, i, j, k, l, m, n, o, p, q, r, s, tt, u, v, w, x, y, z, aa };
	private BufferedImage backgroundImg;
	BufferedImage gvictory;
	BufferedImage bvictory;
	public static boolean end = false;
	
	public PanelTester() {
		pane = new DrawingPanel(512, 448);
		g = pane.getGraphics();
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
		jerry.firstx = 445; jerry.firsty = 390;
		jerry.firstd = 0;
		jerry.reset();
		gary = new GJerald(this, g, 1);
		gary.firstx = 30; gary.firsty = 30;
		gary.firstd = 3;
		gary.reset();
		ni = new Ammu(this, g); ne = new Ammu(this, g); na = new Ammu(this, g); no = new Ammu(this, g); nu = new Ammu(this, g);
		mi = new Ammu(this, g); me = new Ammu(this, g); ma = new Ammu(this, g); mo = new Ammu(this, g); mu = new Ammu(this, g);
		gmmu[0] = ni; gmmu[1] = ne; gmmu[2] = na; gmmu[3] = no; gmmu[4] = nu;
		jmmu[0] = mi; jmmu[1] = me; jmmu[2] = ma; jmmu[3] = mo; jmmu[4] = mu;
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
			int p=0;
			while (p<jmmu.length) {
				Ammu temp = jmmu[p];
				if (temp.checkSelf() == false) {
					temp.setPos(jerry.getBarrelX(), jerry.getBarrelY());
					temp.wreckSelf();
					temp.setFace(jerry.face);
					break;
				}
				else {	p++;	}
			}
		}
		if (e.getKeyCode() == 65) {	gary.left = true;	}
		if (e.getKeyCode() == 87) {	gary.up = true;	}
		if (e.getKeyCode() == 68) {	gary.right = true;	}
		if (e.getKeyCode() == 83) {	gary.down = true;	}
		if (e.getKeyCode() == 32) {
			int q=0;
			while (q<gmmu.length) {
				Ammu temp = gmmu[q];
				if (temp.checkSelf() == false) {
					temp.setPos(gary.getBarrelX(), gary.getBarrelY());
					temp.wreckSelf();
					temp.setFace(gary.face);
					break;
				}
				else {	q++;	}
			}
		}
		if ((e.getKeyCode() == 82) && (end == true)) {	restart();	}
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
	
	public void restart() {
		end = false;
		jerry.reset();
		gary.reset();
		for (int i=0; i<5; i++) {
			jmmu[i].wreckSelf();
			gmmu[i].wreckSelf();
		}
	}
	
	public void draw() {
		g.drawImage(backgroundImg, 0, 0, null);
		for (int i=0; i<bushel.length; i++) {
			Barry temp = bushel[i];
			temp.draw();
		}
		if (end == true) {
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
