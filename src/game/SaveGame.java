package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

public class SaveGame {
	private static boolean autoSave = true;
	private static File saveDirectory = new File("D://Program Files//Cosmo Run");
	private static File saveFile = new File("D://Program Files//Cosmo Run//save.bin");
	private static String errorMessage = "      Cannot Save Game!\n        ";
	static {
		if (!saveDirectory.exists() && !saveDirectory.isDirectory()) {
			// �����ļ��в������򴴽�
			if (!saveDirectory.mkdir()) {
				// ����ʧ�ܵ��������
				JOptionPane.showMessageDialog(null, errorMessage + "ERROR:0x000001");
				autoSave = false;
			}
		}
		
		// �����ڴ����ļ��򴴽�
		if (!saveFile.exists()) {
			try {
				if (!saveFile.createNewFile()) {
					JOptionPane.showMessageDialog(null, errorMessage + "ERROR:0x000002");
					autoSave = false;
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, errorMessage + "ERROR:0x000003");
				autoSave = false;
			}
		}
	}

	public static void save(String saveMessage) {
		if (!autoSave) {
			return;
		}
		
		try {
			FileOutputStream fos = new FileOutputStream(saveFile);
			try {
				fos.write(saveMessage.getBytes());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, errorMessage + "ERROR:0x000006");
			} finally {
				try {
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
