package game_800x600;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import game_800x600.Game;

public class SaveGame {
	private static boolean autoSave = true;
	private static File saveFile = new File("save.bin");
	private static String errorMessage = "      Cannot Save Game!\n        ";
	static {
		// 创建存档
		if (!saveFile.exists()) {
			try {
				if (!saveFile.createNewFile()) {
					JOptionPane.showMessageDialog(null, errorMessage + "ERROR:0x000002");
					// 创建失败关闭自动存档
					autoSave = false;
				}
			} catch (IOException e) {
				// 处理 I/O 异常
				JOptionPane.showMessageDialog(null, errorMessage + "ERROR:0x000003");
				autoSave = false;
			}
		}
	}

	public static void save() {
		// 自动存档是否打开
		if (!autoSave) {
			return;
		}
		
		// 格式化存档
		String saveMessage = String.format("%010d%1d%1d", Game.getBestScore(), Game.bkMode,
				Game.soundSwitch ? 1 : 0);
		
		try {
			FileOutputStream fos = new FileOutputStream(saveFile);
			try {
				// 写入存档
				fos.write(saveMessage.getBytes());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, errorMessage + "ERROR:0x000006");
			} finally {
				try {
					// 关闭输出流
					fos.close();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, errorMessage + "ERROR:0x000004");
				}
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, errorMessage + "ERROR:0x000005");
		}

	}

}
