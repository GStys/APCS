import java.applet.*;
import javax.swing.*;
import java.io.*;
import java.net.*;


public class AudioApplet extends JApplet {
	public class Sound {
		private AudioClip song;
		private URL songPath;
		Sound (String filename) {
			try {
				songPath = new URL(getCodeBase(), filename);
				song = Applet.newAudioClip(songPath);
			}catch(Exception e){}
		}
		public void playSound(){
			song.loop();
		}
		public void stopSound(){
			song.stop();
		}
		public void playSoundOnce(){
			song.play();
		}
	}//test change
}
