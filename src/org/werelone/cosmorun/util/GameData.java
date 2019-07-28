package org.werelone.cosmorun.util;

public class GameData {
	// 是否显示游戏说明
	public static boolean showTip = true;
	// 分辨率模式：0为 800x600，1为1200x900
	private static int resolution = 0;
	// 屏幕分辨率
	private static int width = 800, height = 600;
	// 游戏最高分
	private static int bestScore = 0;
	// 游戏得分
	private static int score = 0;
	// 游戏速度
	private static float speed;
	// 当前背景颜色
	public static int bgMode = 0;
	// 背景颜色总数
	public final static int MAX_BG_MODE = 3;
	// 背景音乐开关
	public static boolean musicOn = true;
	// 语言
	public static int language = 1;
	
	/*
	 *  初始化游戏数据
	 */
	public static void init() {
		// 读取游戏数据
		boolean ret = LoadTool.load();
		// 第一次游戏时初始化分辨率属性
		if (!ret) {
			initResolution();
		}
	}

	/*
	 *  初始化分辨率属性
	 */
	public static void initResolution() {
		int screenWidth = ((int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
		int screenHeight = ((int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);

		if (screenWidth >= 1280 && screenHeight >= 960) {
			GameData.resolution = 1;
			GameData.width = 1200;
			GameData.height = 900;
		}
	}
	
	/*
	 *  切换分辨率属性
	 */
	public static void changeResolutionData() {
		if (resolution == 0) {
			GameData.resolution = 1;
			GameData.width = 1200;
			GameData.height = 900;
		} else {
			GameData.resolution = 0;
			GameData.width = 800;
			GameData.height = 600;
		}
	}
	
	/*
	 *  获取当前分辨率模式，0为800x600，1为1200x900
	 */
	public static int getResolution() {
		return resolution;
	}
	
	/*
	 *  设置分辨率模式
	 */
	public static void setResolution(int res) {
		resolution = res;
		if (res == 0) {
			width = 800;
			height = 600;
		} else if (res == 1) {
			width = 1200;
			height = 900;
		}
	}
	
	/*
	 * 获取当前游戏界面宽度
	 */
	public static int getWidth() {
		return width;
	}
	
	/*
	 *  获取当前游戏界面高度
	 */
	public static int getHeight() {
		return height;
	}
	
	/*
	 *  获取当前游戏得分
	 */
	public static int getScore() {
		return score;
	}
	
	/*
	 *  得分加一
	 */
	public static void bonusPoint() {
		score++;
	}
	
	/*
	 *  得分清零
	 */
	public static void flushScore() {
		score = 0;
	}
	
	/*
	 *  获取当前最高分
	 */
	public static int getBestScore() {
		return bestScore;
	}
	
	/*
	 *  设置最高分
	 */
	public static void setBestScore(int bestScore) {
		GameData.bestScore = bestScore;
	}
	
	/*
	 *  刷新最高分
	 */
	public static boolean flushBestScore() {
		if (score > bestScore) {
			bestScore = score;
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 *  获取初始游戏速度
	 */
	public static float getInitialSpeed() {
		if (resolution == 0) {
			speed = 0.43f;
		} else {
			speed = 0.58f;
		}
		return speed;
	}
	
	/*
	 * 提升游戏速度
	 */
	public static float speedUp() {
		if (resolution == 0) speed += 0.0006f;
		else speed += 0.0008f;
		return speed;
	}
	
	/*
	 * 切换语言
	 */
	public static void changeLanguage() {
		language = (language == 0) ? 1 : 0;
	}
}
