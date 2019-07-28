package org.werelone.cosmorun.util;

import java.io.File;
import java.io.FileOutputStream;

public class SaveTool {
	private static File saveFile = new File(/*"bin" + File.separator + */"data");
	
	public static void save() {
		try {
			// ����浵�����ڣ������浵
			if (!saveFile.exists()) {
				if (!saveFile.createNewFile()) {
					return;
				}
			}
			// ��ʽ���浵
			String saveMessage = String.format("%s%04d%s%d%s%d%s%d", 
					getRandomStr(), 
					GameData.getBestScore(), 
					getRandomStr(), 
					GameData.bgMode, 
					getRandomStr(), 
					GameData.musicOn ? 1 : 0, 
					getRandomStr(), 
					GameData.getResolution(),
					GameData.language);
			// �����ļ������
			FileOutputStream fos = new FileOutputStream(saveFile);
			// д��浵
			fos.write(saveMessage.getBytes());
			// �ر������
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String getRandomStr() {
		String str = "";
		// �����ȡ24���ַ�
		for (int i = 0; i < 24; i++) {
			str += (char)('0' + (int)(Math.random() * ('z' - '0')));
		}
		return str;
	}
}
