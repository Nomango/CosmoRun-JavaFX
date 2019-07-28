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
			// �����浵�ļ���
			if (!saveDirectory.mkdir()) {
				// ����ʧ��
				JOptionPane.showMessageDialog(null, errorMessage + "ERROR:0x000001");
				// �ر��Զ��浵
				autoSave = false;
			}
		}
		
		// �����浵
		if (!saveFile.exists()) {
			try {
				if (!saveFile.createNewFile()) {
					JOptionPane.showMessageDialog(null, errorMessage + "ERROR:0x000002");
					// ����ʧ�ܹر��Զ��浵
					autoSave = false;
				}
			} catch (IOException e) {
				// ���� I/O �쳣
				JOptionPane.showMessageDialog(null, errorMessage + "ERROR:0x000003");
				autoSave = false;
			}
		}
	}

	public static void save() {
		// �Զ��浵�Ƿ��
		if (!autoSave) {
			return;
		}
		
		// ��ʽ���浵
		String saveMessage = String.format("%010d%1d%1d", Game.getBestScore(), Game.bkMode,
				Game.soundSwitch ? 1 : 0);
		
		try {
			FileOutputStream fos = new FileOutputStream(saveFile);
			try {
				// д��浵
				fos.write(saveMessage.getBytes());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, errorMessage + "ERROR:0x000006");
			} finally {
				try {
					// �ر������
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
