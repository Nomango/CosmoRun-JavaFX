package org.werelone.cosmorun.util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music {
	private static MediaPlayer mp;
	
	public static void init() {
		String path = Music.class.getResource("/resources/bgMusic.mp3").toString();
		mp = new MediaPlayer(new Media(path));
		mp.setCycleCount(-1);
	}
	
	public static void playBgMusic() {
		mp.play();
	}
	
	public static void stopBgMusic() {
		mp.stop();
	}

}
