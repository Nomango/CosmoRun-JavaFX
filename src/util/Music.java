package util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Music {
	private static Map<String, URL> musicURL = new HashMap<String, URL>();
	private static AudioClip bkMusic;

	public static void load() {
		musicURL.put("bkMusic", Music.class.getClassLoader().getResource("resources/bkMusic.wav"));
		bkMusic = Applet.newAudioClip(musicURL.get("bkMusic"));
	}

	public static void playBkMusic() {
		bkMusic.loop();
	}
	
	public static void stopBkMusic() {
		bkMusic.stop();
	}

}

/*
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music {
	private static MediaPlayer mp;
	private static Map<String, URL> musicURL = new HashMap<String, URL>();
	
	public static void load() {
		musicURL.put("bkMusic", Music.class.getClassLoader().getResource("resources/bkMusic.wav"));
		try {
			mp = new MediaPlayer(new Media(musicURL.get("bkMusic").toURI().toString()));
		} catch (URISyntaxException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public static void playBkMusic() {
		mp.setCycleCount(Timeline.INDEFINITE);
		mp.play();
	}
	
	public static void stopBkMusic() {
		mp.pause();
	}

}
*/