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
		// ���浵�Ƿ����
		if (loadFile.exists()) {
			// ��ȡ�浵
			String archive = LoadGame.read();
			// ���浵�Ƿ�Ϸ�
			if (!isLegal(archive)) {
				JOptionPane.showMessageDialog(null, errorMessage + "ERROR: ���ݷǷ�");
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
		// �浵�ַ�������Ϊ 12
		if (s.length() != 12) return false;
		// �浵����ȫΪ����
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isDigit(s.charAt(i)))
				return false;
		}
		// ���浵�����Ƿ�Ϸ�
		bestScore = Integer.parseInt(s.substring(0, 10));
		bkMode = Integer.parseInt(s.substring(10, 11));
		soundSwitch = Integer.parseInt(s.substring(11));
		if (bkMode >= Background.MAX_MODE || 
				!(soundSwitch == 0 || soundSwitch == 1))
			return false;
		// �Ϸ����� true
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
