package org.werelone.cosmorun.util;

import java.io.File;
import java.io.FileInputStream;

public class LoadTool {
	private static File loadFile = new File(/*"bin" + File.separator + */"data");

	public static boolean load() {
		// ���浵�Ƿ����
		if (loadFile.exists()) {
			// ���������ַ���
			String loadData = "";
			try {
				// ����������
				FileInputStream fis = new FileInputStream(loadFile);
				// ��ȡ����λ��
				int value = 0;
				// �����ȡ���ݵ�byte����
				byte[] bytes = new byte[10];
				// ��ȡ����
				value = fis.read(bytes);
				if (value == 10) {
					loadData = new String(bytes, 0, value);
				}
				// �ر�������
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			// ��������ʽ���浵�Ƿ���Ч
			if (loadData.matches(".{24}\\d{4}.{24}{1}[0-3].{24}{1}[0-1].{24}{1}[0-1]{1}[0-1]")) {
				GameData.setBestScore(Integer.parseInt(loadData.substring(24, 28)));
				GameData.bgMode = Integer.parseInt(loadData.substring(52, 53));
				GameData.musicOn = (Integer.parseInt(loadData.substring(77, 78)) == 1) ? true : false;
				GameData.setResolution(Integer.parseInt(loadData.substring(102, 103)));
				GameData.language = Integer.parseInt(loadData.substring(103, 104));
				// �Ƿ���ʾ��Ϸ˵��
				if (GameData.getBestScore() != 0)
					GameData.showTip = false;
				return true;
			} else if (loadData.matches("\\d{4}#{1}[0-3]#{1}[0-1]#{1}[0-1]")){
				// �Ͱ汾���ݶ�ȡ
				GameData.setBestScore(Integer.parseInt(loadData.substring(0, 4)));
				GameData.bgMode = Integer.parseInt(loadData.substring(5, 6));
				GameData.musicOn = (Integer.parseInt(loadData.substring(7, 8)) == 1) ? true : false;
				GameData.setResolution(Integer.parseInt(loadData.substring(9, 10)));
				if (GameData.getBestScore() != 0)
					GameData.showTip = false;
				// ɾ���Ͱ汾����
				loadFile.delete();
				return true;
			} else {
				// ����浵��Ч��ɾ���ô浵
				loadFile.delete();
				return false;
			}
		}
		return false;
	}

}
