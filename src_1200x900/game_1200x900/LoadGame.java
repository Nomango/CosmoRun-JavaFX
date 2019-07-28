package game_1200x900;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

import game_1200x900.pane.background.Background;

public class LoadGame {
	private static File loadFile = new File("save.bin");
	private static String errorMessage = "      Cannot Load Game!\n        ";
	private static int bestScore, bkMode, soundSwitch;

	public static void readArchive() {
		// 检查存档是否存在
		if (loadFile.exists()) {
			// 读取存档
			String archive = LoadGame.read();
			// 检查存档是否合法
			if (!isLegal(archive)) {
				JOptionPane.showMessageDialog(null, errorMessage + "ERROR: 数据非法");
				return;
			} else {
				Game.setBestScore(bestScore);
				Game.bkMode = bkMode;
				Game.soundSwitch = soundSwitch == 1 ? true : false;
				if (Game.getBestScore() != 0)
					Game.firstPlay = false;
			}
		}
	}
	
	private static boolean isLegal(String s) {
		// 存档字符串长度为 12
		if (s.length() != 12) return false;
		// 存档内容全为数字
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isDigit(s.charAt(i)))
				return false;
		}
		// 检查存档数据是否合法
		bestScore = Integer.parseInt(s.substring(0, 10));
		bkMode = Integer.parseInt(s.substring(10, 11));
		soundSwitch = Integer.parseInt(s.substring(11));
		if (bkMode >= Background.MAX_MODE || 
				!(soundSwitch == 0 || soundSwitch == 1))
			return false;
		// 合法返回 true
		return true;
	}
	
	private static String read() {
		String loadMessage = "";
		try {
			FileInputStream fis = new FileInputStream(loadFile);
			try {
				int value = 0;
				byte[] bytes = new byte[13];
				while ((value = fis.read(bytes)) != -1) {
					loadMessage = new String(bytes, 0, value);
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, errorMessage + "ERROR:0x000001");
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, errorMessage + "ERROR:0x000002");
				}
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, errorMessage + "ERROR:0x000003");
		}
		
		return loadMessage;
	}

}
