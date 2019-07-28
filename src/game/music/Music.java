package game.music;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Music {
	private static Map<String, URL> musicURL = new HashMap<String, URL>();
	private static AudioClip bkMusic;

	public static void load() {
		musicURL.put("bkMusic", Music.class.getClassLoader().getResource("game/sources/bkMusic.wav"));
		bkMusic = Applet.newAudioClip(musicURL.get("bkMusic"));
	}

	public static void playBkMusic() {
		bkMusic.loop();
	}
	
	public static void stopBkMusic() {
		bkMusic.stop();
	}

}
