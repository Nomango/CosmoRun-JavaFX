package org.werelone.cosmorun.util;

import java.io.File;
import java.io.FileOutputStream;

public class SaveTool {
	private static File saveFile = new File(/*"bin" + File.separator + */"data");
	
	public static void save() {
		try {
			// 如果存档不存在，创建存档
			if (!saveFile.exists()) {
				if (!saveFile.createNewFile()) {
					return;
				}
			}
			// 格式化存档
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
			// 创建文件输出流
			FileOutputStream fos = new FileOutputStream(saveFile);
			// 写入存档
			fos.write(saveMessage.getBytes());
			// 关闭输出流
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String getRandomStr() {
		String str = "";
		// 随机获取24个字符
		for (int i = 0; i < 24; i++) {
			str += (char)('0' + (int)(Math.random() * ('z' - '0')));
		}
		return str;
	}
}
