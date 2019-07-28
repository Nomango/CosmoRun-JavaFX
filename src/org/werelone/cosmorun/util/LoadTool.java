package org.werelone.cosmorun.util;

import java.io.File;
import java.io.FileInputStream;

public class LoadTool {
	private static File loadFile = new File(/*"bin" + File.separator + */"data");

	public static boolean load() {
		// 检查存档是否存在
		if (loadFile.exists()) {
			// 创建数据字符串
			String loadData = "";
			try {
				// 创建输入流
				FileInputStream fis = new FileInputStream(loadFile);
				// 读取数据位数
				int value = 0;
				// 储存读取数据的byte数组
				byte[] bytes = new byte[10];
				// 读取数据
				value = fis.read(bytes);
				if (value == 10) {
					loadData = new String(bytes, 0, value);
				}
				// 关闭输入流
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 用正则表达式检查存档是否有效
			if (loadData.matches(".{24}\\d{4}.{24}{1}[0-3].{24}{1}[0-1].{24}{1}[0-1]{1}[0-1]")) {
				GameData.setBestScore(Integer.parseInt(loadData.substring(24, 28)));
				GameData.bgMode = Integer.parseInt(loadData.substring(52, 53));
				GameData.musicOn = (Integer.parseInt(loadData.substring(77, 78)) == 1) ? true : false;
				GameData.setResolution(Integer.parseInt(loadData.substring(102, 103)));
				GameData.language = Integer.parseInt(loadData.substring(103, 104));
				// 是否显示游戏说明
				if (GameData.getBestScore() != 0)
					GameData.showTip = false;
				return true;
			} else if (loadData.matches("\\d{4}#{1}[0-3]#{1}[0-1]#{1}[0-1]")){
				// 低版本数据读取
				GameData.setBestScore(Integer.parseInt(loadData.substring(0, 4)));
				GameData.bgMode = Integer.parseInt(loadData.substring(5, 6));
				GameData.musicOn = (Integer.parseInt(loadData.substring(7, 8)) == 1) ? true : false;
				GameData.setResolution(Integer.parseInt(loadData.substring(9, 10)));
				if (GameData.getBestScore() != 0)
					GameData.showTip = false;
				// 删除低版本数据
				loadFile.delete();
				return true;
			} else {
				// 如果存档无效，删除该存档
				loadFile.delete();
				return false;
			}
		}
		return false;
	}

}
