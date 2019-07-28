package game.music;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class PlayMusic {
	private static Map<String, URL> musicURL = new HashMap<String, URL>();
	private static boolean status;

	static {
		musicURL.put("bkMusic", PlayMusic.class.getClassLoader().getResource("game/sources/bkMusic.wav"));
	}
	public static AudioClip bkMusic;

	public PlayMusic() {
		bkMusic = Applet.newAudioClip(musicURL.get("bkMusic"));
	}

	public void playBkMusic() {
		bkMusic.loop();
		status = true;
	}
	
	public void stopBkMusic() {
		bkMusic.stop();
		status = false;
	}
	
	public static int getStatus() {
		if (status) {
			return 1;
		} else {
			return 0;
		}
	}

}
