package org.werelone.cosmorun.util;

public class GameData {
	// �Ƿ���ʾ��Ϸ˵��
	public static boolean showTip = true;
	// �ֱ���ģʽ��0Ϊ 800x600��1Ϊ1200x900
	private static int resolution = 0;
	// ��Ļ�ֱ���
	private static int width = 800, height = 600;
	// ��Ϸ��߷�
	private static int bestScore = 0;
	// ��Ϸ�÷�
	private static int score = 0;
	// ��Ϸ�ٶ�
	private static float speed;
	// ��ǰ������ɫ
	public static int bgMode = 0;
	// ������ɫ����
	public final static int MAX_BG_MODE = 3;
	// �������ֿ���
	public static boolean musicOn = true;
	// ����
	public static int language = 1;
	
	/*
	 *  ��ʼ����Ϸ����
	 */
	public static void init() {
		// ��ȡ��Ϸ����
		boolean ret = LoadTool.load();
		// ��һ����Ϸʱ��ʼ���ֱ�������
		if (!ret) {
			initResolution();
		}
	}

	/*
	 *  ��ʼ���ֱ�������
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
	 *  �л��ֱ�������
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
	 *  ��ȡ��ǰ�ֱ���ģʽ��0Ϊ800x600��1Ϊ1200x900
	 */
	public static int getResolution() {
		return resolution;
	}
	
	/*
	 *  ���÷ֱ���ģʽ
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
	 * ��ȡ��ǰ��Ϸ������
	 */
	public static int getWidth() {
		return width;
	}
	
	/*
	 *  ��ȡ��ǰ��Ϸ����߶�
	 */
	public static int getHeight() {
		return height;
	}
	
	/*
	 *  ��ȡ��ǰ��Ϸ�÷�
	 */
	public static int getScore() {
		return score;
	}
	
	/*
	 *  �÷ּ�һ
	 */
	public static void bonusPoint() {
		score++;
	}
	
	/*
	 *  �÷�����
	 */
	public static void flushScore() {
		score = 0;
	}
	
	/*
	 *  ��ȡ��ǰ��߷�
	 */
	public static int getBestScore() {
		return bestScore;
	}
	
	/*
	 *  ������߷�
	 */
	public static void setBestScore(int bestScore) {
		GameData.bestScore = bestScore;
	}
	
	/*
	 *  ˢ����߷�
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
	 *  ��ȡ��ʼ��Ϸ�ٶ�
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
	 * ������Ϸ�ٶ�
	 */
	public static float speedUp() {
		if (resolution == 0) speed += 0.0006f;
		else speed += 0.0008f;
		return speed;
	}
	
	/*
	 * �л�����
	 */
	public static void changeLanguage() {
		language = (language == 0) ? 1 : 0;
	}
}
