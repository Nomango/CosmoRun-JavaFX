package game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

public class LoadGame {
	private static File loadDirectory = new File("D://Program Files//Cosmo Run");
	private static File loadFile = new File("D://Program Files//Cosmo Run//save.bin");
	private static String errorMessage = "      Cannot Load Game!\n        ";

	public static boolean canLoad() {
		if (!loadDirectory.exists() && !loadDirectory.isDirectory()) {
			// 储存文件夹不存在返回 false
			return false;
		} else if (!loadFile.exists()) {
			return false;
		} else {
			return true;
		}
	}
	
	public static String load() {
		String loadMessage = null;
		try {
			FileInputStream fis = new FileInputStream(loadFile);
			try {
				int value = 0;
				byte[] bytes = new byte[13];
				while ((value = fis.read(bytes)) != -1) {
					loadMessage = new String(bytes, 0, value);
				}
				return loadMessage;
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
