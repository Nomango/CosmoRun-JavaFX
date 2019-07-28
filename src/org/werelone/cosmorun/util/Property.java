package org.werelone.cosmorun.util;

import java.util.HashMap;
import java.util.Map;

public class Property {
	// 该类中储存了几乎所有界面的属性数据，例如某个按钮的x、y坐标
	public static Map<String, Double> propertyMap;
	public static Map<String, double[]> arrayMap;
	
	// 初始化属性数据
	public static void init() {
		propertyMap = new HashMap<>();
		arrayMap = new HashMap<>();
		
		if (GameData.getResolution() == 0) {
			Property.initSmallButton();
			Property.initSmallLogoPane();
			Property.initSmallTouchPane();
			Property.initSmallStartMenuPane();
			Property.initSmallOptionPane();
			Property.initSmallHowToPlayPane();
			Property.initSmallAboutPane();
			Property.initSmallGameOverPane();
			Property.initSmallUpdateLogPane();
		} else {
			Property.initLargeButton();
			Property.initLargeLogoPane();
			Property.initLargeTouchPane();
			Property.initLargeStartMenuPane();
			Property.initLargeOptionPane();
			Property.initLargeHowToPlayPane();
			Property.initLargeAboutPane();
			Property.initLargeGameOverPane();
			Property.initLargeUpdateLogPane();
		}
	}
	
	// 清空属性数据占用的空间
	public static void clear() {
		propertyMap.clear();
		propertyMap = null;
		arrayMap.clear();
		arrayMap = null;
	}
	
	public static double get(String name) {
		if (GameData.getResolution() == 0) {
			return propertyMap.get("s-" + name);
		} else {
			return propertyMap.get("l-" + name);
		}
	}
	
	public static double[] getArray(String name) {
		if (GameData.getResolution() == 0) {
			return arrayMap.get("s-" + name);
		} else {
			return arrayMap.get("l-" + name);
		}
	}
	
	private static void initLargeTouchPane() {
		propertyMap.put("l-touch-scoretext-x", GameData.getWidth() / 2.0 - 20);
		propertyMap.put("l-touch-scoretext-y", 120.0);
	}
	
	private static void initSmallTouchPane() {
		propertyMap.put("s-touch-scoretext-x", GameData.getWidth() / 2.0 - 15);
		propertyMap.put("s-touch-scoretext-y", 70.0);
	}
	
	private static void initLargeButton() {
		double[] triangleArray = new double[]{0, -30, -26, 15, 26, 15};
		arrayMap.put("l-background-triangle-points", triangleArray);
		double[] littleOutsideArray = new double[]{-47, -57, 47, -57, 92, 0, 47, 57, -47, 57, -92, 0};
		arrayMap.put("l-basebtn-little-outside", littleOutsideArray);
		double[] littleInsideArray = new double[]{-39, -49, 39, -49, 79, 0, 39, 49, -39, 49, -79, 0};
		arrayMap.put("l-basebtn-little-inside", littleInsideArray);
		double[] bigOutsideArray = new double[]{-200, -55, 200, -55, 245, 0, 200, 55, -200, 55, -245, 0};
		arrayMap.put("l-basebtn-big-outside", bigOutsideArray);
		double[] bigInsideArray = new double[]{-192, -47, 192, -47, 237, 0, 192, 47, -192, 47, -237, 0};
		arrayMap.put("l-basebtn-big-inside", bigInsideArray);
		propertyMap.put("l-basebtn-ex", 45.0);
		propertyMap.put("l-basebtn-diff", 8.0);
		propertyMap.put("l-closebtn-x", 110.0);
		propertyMap.put("l-closebtn-y", 110.0);
	}
	
	private static void initSmallButton() {
		double[] triangleArray = new double[]{0, -20, -17, 10, 17, 10};
		arrayMap.put("s-background-triangle-points", triangleArray);
		double[] littleOutsideArray = new double[]{-31.5, -38.2, 31.5, -38.2, 61.7, 0.0, 31.5, 38.2, -31.5, 38.2, -61.7, 0.0};
		arrayMap.put("s-basebtn-little-outside", littleOutsideArray);
		double[] littleInsideArray = new double[]{-26.1, -32.8, 26.1, -32.8, 52.9, 0.0, 26.1, 32.8, -26.1, 32.8, -52.9, 0.0};
		arrayMap.put("s-basebtn-little-inside", littleInsideArray);
		double[] bigOutsideArray = new double[]{-133, -36, 133, -36, 163.2, 0.0, 133, 36, -133, 36, -163.2, 0.0};
		arrayMap.put("s-basebtn-big-outside", bigOutsideArray);
		double[] bigInsideArray = new double[]{-127.6, -30.6, 127.6, -30.6, 157.8, 0.0, 127.6, 30.6, -127.6, 30.6, -157.8, 0.0};
		arrayMap.put("s-basebtn-big-inside", bigInsideArray);
		propertyMap.put("s-basebtn-ex", 30.2);
		propertyMap.put("s-basebtn-diff", 5.4);
		propertyMap.put("s-closebtn-x", 110 * 0.667);
		propertyMap.put("s-closebtn-y", 110 * 0.667);
	}
	
	private static void initLargeStartMenuPane() {
		double[] playbtnArray = new double[]{-140, -57, 140, -57, 185, 0, 140, 57, -140, 57, -185, 0};
		arrayMap.put("l-startmenu-playbtn-points", playbtnArray);
		propertyMap.put("l-startmenu-playbtn-text-x", -90.0 / 2);
		propertyMap.put("l-startmenu-playbtn-text-y", 50.0 / 2);
		propertyMap.put("l-startmenu-playbtn-text-cn-x", -80.0 / 2);
		propertyMap.put("l-startmenu-playbtn-text-cn-y", 35.0 / 2);
		
		propertyMap.put("l-startmenu-bestscoretext-x", (GameData.getWidth() - 170)/ 2.0);
		propertyMap.put("l-startmenu-bestscoretext-y", GameData.getHeight() / 2.4);
		
		propertyMap.put("l-startmenu-title-x", (GameData.getWidth() - 556) / 2.0);
		propertyMap.put("l-startmenu-title-y", 120.0);
		propertyMap.put("l-startmenu-title-cn-x", (GameData.getWidth() - 400) / 2.0);
		propertyMap.put("l-startmenu-title-cn-y", 220.0);
		
		propertyMap.put("l-startmenu-aboutbtn-x", GameData.getWidth() / 1.2 + 10);
		propertyMap.put("l-startmenu-aboutbtn-y", GameData.getHeight() / 2.0 + 140);
		propertyMap.put("l-startmenu-aboutbtn-c-r", 25.0);
		propertyMap.put("l-startmenu-aboutbtn-c2-r", 3.5);
		propertyMap.put("l-startmenu-aboutbtn-c2-y", -10.0);
		propertyMap.put("l-startmenu-aboutbtn-r-x", -3.0);
		propertyMap.put("l-startmenu-aboutbtn-r-y", -3.0);
		propertyMap.put("l-startmenu-aboutbtn-r-w", 6.0);
		propertyMap.put("l-startmenu-aboutbtn-r-h", 20.0);
		
		propertyMap.put("l-startmenu-optionbtn-x", GameData.getWidth() / 1.2 + 10);
		propertyMap.put("l-startmenu-optionbtn-y", GameData.getHeight() / 2.0 - 30);
		propertyMap.put("l-startmenu-optionbtn-c-r", 10.0);
	}
	
	private static void initSmallStartMenuPane() {
		double[] playbtnArray = new double[]{-93.0, -38.0, 93.0, -38.0, 123.0, 0.0, 93.0, 38.0, -93.0, 38.0, -123.0, 0.0};
		arrayMap.put("s-startmenu-playbtn-points", playbtnArray);
		propertyMap.put("s-startmenu-playbtn-text-x", -60.0 / 2);
		propertyMap.put("s-startmenu-playbtn-text-y", 33.0 / 2);
		propertyMap.put("s-startmenu-playbtn-text-cn-x", -80.0 * 0.67 / 2);
		propertyMap.put("s-startmenu-playbtn-text-cn-y", 35.0 * 0.67 / 2);
		
		propertyMap.put("s-startmenu-bestscoretext-x", (GameData.getWidth() - 170 * 0.67)/ 2.0);
		propertyMap.put("s-startmenu-bestscoretext-y", GameData.getHeight() / 2.4);
		
		propertyMap.put("s-startmenu-title-x", (GameData.getWidth() - 556) / 2.0);
		propertyMap.put("s-startmenu-title-y", 80.0 * 0.67);
		propertyMap.put("s-startmenu-title-cn-x", (GameData.getWidth() - 300) / 2.0);
		propertyMap.put("s-startmenu-title-cn-y", 220.0 * 0.67);
		
		propertyMap.put("s-startmenu-aboutbtn-x", GameData.getWidth() / 1.2 + 7);
		propertyMap.put("s-startmenu-aboutbtn-y", GameData.getHeight() / 2.0 + 100);
		propertyMap.put("s-startmenu-aboutbtn-c-r", 16.6);
		propertyMap.put("s-startmenu-aboutbtn-c2-r", 2.3);
		propertyMap.put("s-startmenu-aboutbtn-c2-y", -6.6);
		propertyMap.put("s-startmenu-aboutbtn-r-x", -2.0);
		propertyMap.put("s-startmenu-aboutbtn-r-y", -2.0);
		propertyMap.put("s-startmenu-aboutbtn-r-w", 4.0);
		propertyMap.put("s-startmenu-aboutbtn-r-h", 13.3);
		
		propertyMap.put("s-startmenu-optionbtn-x", GameData.getWidth() / 1.2 + 7);
		propertyMap.put("s-startmenu-optionbtn-y", GameData.getHeight() / 2.0 - 20);
		propertyMap.put("s-startmenu-optionbtn-c-r", 6.6);
	}
	
	private static void initLargeOptionPane() {
		propertyMap.put("l-option-title-x", 240.0);
		propertyMap.put("l-option-title-y", 140.0);
		propertyMap.put("l-option-title-cn-x", 240.0);
		propertyMap.put("l-option-title-cn-y", 130.0);
		
		propertyMap.put("l-option-howtoplaybtn-x", GameData.getWidth() / 2.0);
		propertyMap.put("l-option-howtoplaybtn-y", GameData.getHeight() / 2.0 - 130);
		propertyMap.put("l-option-howtoplaybtn-text-x", -270 / 2.0);
		propertyMap.put("l-option-howtoplaybtn-text-y", 50 / 2.0);
		propertyMap.put("l-option-howtoplaybtn-text-cn-x", -165 / 2.0);
		propertyMap.put("l-option-howtoplaybtn-text-cn-y", 40 / 2.0);
		
		propertyMap.put("l-option-bgmodebtn-x", GameData.getWidth() / 2.0);
		propertyMap.put("l-option-bgmodebtn-y", GameData.getHeight() / 2.0 + 10);
		propertyMap.put("l-option-bgmodebtn-text-x", -255 / 2.0);
		propertyMap.put("l-option-bgmodebtn-text-y", 50 / 2.0);
		propertyMap.put("l-option-bgmodebtn-text-cn-x", -240 / 2.0);
		propertyMap.put("l-option-bgmodebtn-text-cn-y", 40 / 2.0);
		
		propertyMap.put("l-option-langbtn-x", GameData.getWidth() / 2.0);
		propertyMap.put("l-option-langbtn-y", GameData.getHeight() / 2.0 + 150);
		propertyMap.put("l-option-langbtn-text-x", -300 / 2.0);
		propertyMap.put("l-option-langbtn-text-y", 50 / 2.0);
		propertyMap.put("l-option-langbtn-text-cn-x", -280 / 2.0);
		propertyMap.put("l-option-langbtn-text-cn-y", 40 / 2.0);
		
		propertyMap.put("l-option-changersltbtn-x", GameData.getWidth() / 2.0 + 110);
		propertyMap.put("l-option-changersltbtn-y", GameData.getHeight() / 2.0 + 300);
		propertyMap.put("l-option-changersltbtn-r1-x", -25.0);
		propertyMap.put("l-option-changersltbtn-r1-y", -30.0);
		propertyMap.put("l-option-changersltbtn-r1-w", 60.0);
		propertyMap.put("l-option-changersltbtn-r1-h", 40.0);
		propertyMap.put("l-option-changersltbtn-r1-strokew", 6.0);
		propertyMap.put("l-option-changersltbtn-r2-x", -35.0);
		propertyMap.put("l-option-changersltbtn-r2-y", -5.0);
		propertyMap.put("l-option-changersltbtn-r2-w", 50.0);
		propertyMap.put("l-option-changersltbtn-r2-h", 30.0);
		propertyMap.put("l-option-changersltbtn-r2-strokew", 4.5);
		
		propertyMap.put("l-option-musicbtn-x", GameData.getWidth() / 2.0 - 110);
		propertyMap.put("l-option-musicbtn-y", GameData.getHeight() / 2.0 + 300);
		double[] audioShapeArray = new double[]{0.0, 20.0, -17.0, 9.0, -25.0, 9.0, -25.0, -9.0, -17.0, -9.0, 0.0, -20.0};
		arrayMap.put("l-option-musicbtn-audioshape", audioShapeArray);
		propertyMap.put("l-option-musicbtn-wave1-radiusx", 15.0);
		propertyMap.put("l-option-musicbtn-wave1-radiusy", 15.0);
		propertyMap.put("l-option-musicbtn-wave-startangle", -55.0);
		propertyMap.put("l-option-musicbtn-wave-length", 110.0);
		propertyMap.put("l-option-musicbtn-wave-strokew", 4.0);
		propertyMap.put("l-option-musicbtn-wave2-radiusx", 25.0);
		propertyMap.put("l-option-musicbtn-wave2-radiusy", 25.0);
		propertyMap.put("l-option-musicbtn-fork-x", 15.0);
		propertyMap.put("l-option-musicbtn-fork-y", -15.0);
		propertyMap.put("l-option-musicbtn-fork-w", 4.0);
		propertyMap.put("l-option-musicbtn-fork-h", 30.0);
	}
	
	private static void initSmallOptionPane() {
		propertyMap.put("s-option-title-x", 160.0);
		propertyMap.put("s-option-title-y", 140 * 0.67);
		propertyMap.put("s-option-title-cn-x", 160.0);
		propertyMap.put("s-option-title-cn-y", 130 * 0.67);
		
		propertyMap.put("s-option-howtoplaybtn-x", GameData.getWidth() / 2.0);
		propertyMap.put("s-option-howtoplaybtn-y", GameData.getHeight() / 2.0 - 90);
		propertyMap.put("s-option-howtoplaybtn-text-x", -200 / 2.0);
		propertyMap.put("s-option-howtoplaybtn-text-y", 33 / 2.0);
		propertyMap.put("s-option-howtoplaybtn-text-cn-x", -165 * 0.67 / 2.0);
		propertyMap.put("s-option-howtoplaybtn-text-cn-y", 40 * 0.67 / 2.0);
		
		propertyMap.put("s-option-bgmodebtn-x", GameData.getWidth() / 2.0);
		propertyMap.put("s-option-bgmodebtn-y", GameData.getHeight() / 2.0 + 5);
		propertyMap.put("s-option-bgmodebtn-text-x", -255 * 0.67 / 2.0);
		propertyMap.put("s-option-bgmodebtn-text-y", 50 * 0.67 / 2.0);
		propertyMap.put("s-option-bgmodebtn-text-cn-x", -240 * 0.67 / 2.0);
		propertyMap.put("s-option-bgmodebtn-text-cn-y", 40 * 0.67 / 2.0);
		
		propertyMap.put("s-option-langbtn-x", GameData.getWidth() / 2.0);
		propertyMap.put("s-option-langbtn-y", GameData.getHeight() / 2.0 + 100);
		propertyMap.put("s-option-langbtn-text-x", -300 * 0.67 / 2.0);
		propertyMap.put("s-option-langbtn-text-y", 50 * 0.67 / 2.0);
		propertyMap.put("s-option-langbtn-text-cn-x", -280 * 0.67 / 2.0);
		propertyMap.put("s-option-langbtn-text-cn-y", 40 * 0.67 / 2.0);
		
		propertyMap.put("s-option-changersltbtn-x", GameData.getWidth() / 2.0 + 82.5);
		propertyMap.put("s-option-changersltbtn-y", GameData.getHeight() / 2.0 + 190);
		propertyMap.put("s-option-changersltbtn-r1-x", -25.0 * 0.67);
		propertyMap.put("s-option-changersltbtn-r1-y", -30.0 * 0.67);
		propertyMap.put("s-option-changersltbtn-r1-w", 60.0 * 0.67);
		propertyMap.put("s-option-changersltbtn-r1-h", 40.0 * 0.67);
		propertyMap.put("s-option-changersltbtn-r1-strokew", 6.0 * 0.67);
		propertyMap.put("s-option-changersltbtn-r2-x", -35.0 * 0.67);
		propertyMap.put("s-option-changersltbtn-r2-y", -5.0 * 0.67);
		propertyMap.put("s-option-changersltbtn-r2-w", 50.0 * 0.67);
		propertyMap.put("s-option-changersltbtn-r2-h", 30.0 * 0.67);
		propertyMap.put("s-option-changersltbtn-r2-strokew", 4.5 * 0.67);
		
		propertyMap.put("s-option-musicbtn-x", GameData.getWidth() / 2.0 - 82.5);
		propertyMap.put("s-option-musicbtn-y", GameData.getHeight() / 2.0 + 190);
		double[] audioShapeArray = new double[]{0.0, 20.0 * 0.67, -17.0 * 0.67, 9.0 * 0.67, -25.0 * 0.67, 9.0 * 0.67, -25.0 * 0.67, -9.0 * 0.67, -17.0 * 0.67, -9.0 * 0.67, 0.0, -20.0 * 0.67};
		arrayMap.put("s-option-musicbtn-audioshape", audioShapeArray);
		propertyMap.put("s-option-musicbtn-wave1-radiusx", 10.0);
		propertyMap.put("s-option-musicbtn-wave1-radiusy", 10.0);
		propertyMap.put("s-option-musicbtn-wave-startangle", -55.0);
		propertyMap.put("s-option-musicbtn-wave-length", 110.0);
		propertyMap.put("s-option-musicbtn-wave-strokew", 3.0);
		propertyMap.put("s-option-musicbtn-wave2-radiusx", 16.0);
		propertyMap.put("s-option-musicbtn-wave2-radiusy", 16.0);
		propertyMap.put("s-option-musicbtn-fork-x", 10.0);
		propertyMap.put("s-option-musicbtn-fork-y", -10.0);
		propertyMap.put("s-option-musicbtn-fork-w", 2.6);
		propertyMap.put("s-option-musicbtn-fork-h", 20.0);
	}
	
	private static void initLargeLogoPane() {
		propertyMap.put("l-cutscene-text-x", GameData.getWidth() / 2 - 100.0);
		propertyMap.put("l-cutscene-text-y", GameData.getHeight() / 2 - 20.0);
		propertyMap.put("l-cutscene-light-r", 120.0);
		propertyMap.put("l-cutscene-light-blur", 70.0);
		double[] symbolArray = new double[]{-150.0, -25.0, -110.0, -35.0, -120.0, -90.0, -90.0, -120.0, -55.0, -100.0, -33.0, -20.0, -25.0, -150.0, 25.0, -150.0, 55.0, -40.0, 90.0, -120.0, 120.0, -90.0, 100.0, -50.0, 110.0, -33.0, 150.0, -25.0,
				150.0, 25.0, 110.0, 35.0, 120.0, 90.0, 90.0, 120.0, 55.0, 100.0, 33.0, 20.0, 25.0, 150.0, -25.0, 150.0, -55.0, 40.0, -90.0, 120.0, -120.0, 90.0, -100.0, 50.0, -110.0, 33.0, -150.0, 25.0};
		arrayMap.put("l-cutscene-symbol-points", symbolArray);
	}
	
	private static void initSmallLogoPane() {
		propertyMap.put("s-cutscene-text-x", GameData.getWidth() / 2 - 70.0);
		propertyMap.put("s-cutscene-text-y", GameData.getHeight() / 2 - 10.0);
		propertyMap.put("s-cutscene-light-r", 80.0);
		propertyMap.put("s-cutscene-light-blur", 47.0);
		double[] symbolArray = new double[]{-150.0, -25.0, -110.0, -35.0, -120.0, -90.0, -90.0, -120.0, -55.0, -100.0, -33.0, -20.0, -25.0, -150.0, 25.0, -150.0, 55.0, -40.0, 90.0, -120.0, 120.0, -90.0, 100.0, -50.0, 110.0, -33.0, 150.0, -25.0,
				150.0, 25.0, 110.0, 35.0, 120.0, 90.0, 90.0, 120.0, 55.0, 100.0, 33.0, 20.0, 25.0, 150.0, -25.0, 150.0, -55.0, 40.0, -90.0, 120.0, -120.0, 90.0, -100.0, 50.0, -110.0, 33.0, -150.0, 25.0};
		for(int i = 0; i < symbolArray.length; i++) symbolArray[i] *= 0.67;
		arrayMap.put("s-cutscene-symbol-points", symbolArray);
	}
	
	private static void initLargeGameOverPane() {
		propertyMap.put("l-gameover-tryagainbtn-w", 180.0);
		propertyMap.put("l-gameover-tryagainbtn-h", 65.0);
		propertyMap.put("l-gameover-tryagainbtn-x", GameData.getWidth() / 2.0);
		propertyMap.put("l-gameover-tryagainbtn-y", GameData.getHeight() / 1.3);
		propertyMap.put("l-gameover-tryagainbtn-arc-x", 120.0);
		propertyMap.put("l-gameover-tryagainbtn-arc-radiusx", 20.0);
		propertyMap.put("l-gameover-tryagainbtn-arc-radiusy", 20.0);
		propertyMap.put("l-gameover-tryagainbtn-arc-length", 270.0);
		propertyMap.put("l-gameover-tryagainbtn-arc-strokew", 6.5);
		propertyMap.put("l-gameover-tryagainbtn-r1-x", 141.0);
		propertyMap.put("l-gameover-tryagainbtn-r1-y", -12.0);
		propertyMap.put("l-gameover-tryagainbtn-r-w", 6.5);
		propertyMap.put("l-gameover-tryagainbtn-r-h", 20.0);
		propertyMap.put("l-gameover-tryagainbtn-r2-x", 130.0);
		propertyMap.put("l-gameover-tryagainbtn-r2-y", -10.0);
		propertyMap.put("l-gameover-tryagainbtn-text-x", -200 / 2.0 - 30);
		propertyMap.put("l-gameover-tryagainbtn-text-y", 50 / 2.0);
		propertyMap.put("l-gameover-tryagainbtn-text-cn-x", -170 / 2.0 - 30);
		propertyMap.put("l-gameover-tryagainbtn-text-cn-y", 40 / 2.0);
		
		propertyMap.put("l-gameover-title-x", (GameData.getWidth() - 390) / 2.0);
		propertyMap.put("l-gameover-title-y", GameData.getHeight() / 5.5);
		propertyMap.put("l-gameover-title-cn-x", (GameData.getWidth() - 400) / 2.0);
		propertyMap.put("l-gameover-title-cn-y", GameData.getHeight() / 5.5);
		
		propertyMap.put("l-gameover-scoretext-x", (GameData.getWidth() - 40) / 2.0);
		propertyMap.put("l-gameover-scoretext-y", GameData.getHeight() / 3.5);
		
		propertyMap.put("l-gameover-bestscoretext-x", (GameData.getWidth() - 170) / 2.0);
		propertyMap.put("l-gameover-bestscoretext-y", GameData.getHeight() / 2.4);
	}
	
	private static void initSmallGameOverPane() {
		propertyMap.put("s-gameover-tryagainbtn-w", 120.0);
		propertyMap.put("s-gameover-tryagainbtn-h", 43.0);
		propertyMap.put("s-gameover-tryagainbtn-x", GameData.getWidth() / 2.0);
		propertyMap.put("s-gameover-tryagainbtn-y", GameData.getHeight() / 1.3);
		propertyMap.put("s-gameover-tryagainbtn-arc-x", 120.0 * 0.67);
		propertyMap.put("s-gameover-tryagainbtn-arc-radiusx", 20.0 * 0.67);
		propertyMap.put("s-gameover-tryagainbtn-arc-radiusy", 20.0 * 0.67);
		propertyMap.put("s-gameover-tryagainbtn-arc-length", 270.0);
		propertyMap.put("s-gameover-tryagainbtn-arc-strokew", 4.0);
		propertyMap.put("s-gameover-tryagainbtn-r1-x", 141.0 * 0.67);
		propertyMap.put("s-gameover-tryagainbtn-r1-y", -12.0 * 0.67);
		propertyMap.put("s-gameover-tryagainbtn-r-w", 6.5 * 0.67);
		propertyMap.put("s-gameover-tryagainbtn-r-h", 20.0 * 0.67);
		propertyMap.put("s-gameover-tryagainbtn-r2-x", 130.0 * 0.67);
		propertyMap.put("s-gameover-tryagainbtn-r2-y", -10.0 * 0.67);
		propertyMap.put("s-gameover-tryagainbtn-text-x", -200 * 0.67 / 2 - 20);
		propertyMap.put("s-gameover-tryagainbtn-text-y", 50 * 0.67 / 2.0);
		propertyMap.put("s-gameover-tryagainbtn-text-cn-x", -170 * 0.67 / 2 - 20);
		propertyMap.put("s-gameover-tryagainbtn-text-cn-y", 40 * 0.67 / 2.0);
		
		propertyMap.put("s-gameover-title-x", (GameData.getWidth() - 390 * 0.67) / 2.0);
		propertyMap.put("s-gameover-title-y", GameData.getHeight() / 5.5);
		propertyMap.put("s-gameover-title-cn-x", (GameData.getWidth() - 300) / 2.0);
		propertyMap.put("s-gameover-title-cn-y", GameData.getHeight() / 5.5);
		
		propertyMap.put("s-gameover-scoretext-x", (GameData.getWidth() - 40 * 0.67) / 2.0);
		propertyMap.put("s-gameover-scoretext-y", GameData.getHeight() / 3.5);
		
		propertyMap.put("s-gameover-bestscoretext-x", (GameData.getWidth() - 170 * 0.67) / 2.0);
		propertyMap.put("s-gameover-bestscoretext-y", GameData.getHeight() / 2.4);
	}
	
	private static void initLargeAboutPane() {
		propertyMap.put("l-about-title-x", 240.0);
		propertyMap.put("l-about-title-y", 140.0);
		
		propertyMap.put("l-about-updatelogbtn-w", 60.0);
		propertyMap.put("l-about-updatelogbtn-h", 40.0);
		propertyMap.put("l-about-updatelogbtn-x", 400.0);
		propertyMap.put("l-about-updatelogbtn-y", 700.0);
		propertyMap.put("l-about-updatelogbtn-text-x", -40 / 2.0);
		propertyMap.put("l-about-updatelogbtn-text-y", 26 / 2.0);
		propertyMap.put("l-about-updatelogbtn-text-cn-x", -120 / 2.0);
		propertyMap.put("l-about-updatelogbtn-text-cn-y", 26 / 2.0);
		
		propertyMap.put("l-about-myblogbtn-w", 60.0);
		propertyMap.put("l-about-myblogbtn-h", 40.0);
		propertyMap.put("l-about-myblogbtn-text-x", -120 / 2.0);
		propertyMap.put("l-about-myblogbtn-text-y", 26 / 2.0);
		propertyMap.put("l-about-myblogbtn-x", 650.0);
		propertyMap.put("l-about-myblogbtn-y", 700.0);
		
		propertyMap.put("l-about-abouttext1-x", 240.0);
		propertyMap.put("l-about-abouttext1-y", 50.0);
		propertyMap.put("l-about-abouttext1-wrappingw", 750.0);
		
		propertyMap.put("l-about-abouttext2-x", 220.0);
		propertyMap.put("l-about-abouttext2-y", 200.0);
		propertyMap.put("l-about-abouttext2-wrappingw", 800.0);
		
		propertyMap.put("l-about-abouttext-h", 380.0);
	}
	
	private static void initSmallAboutPane() {
		propertyMap.put("s-about-title-x", 240.0 * 0.667);
		propertyMap.put("s-about-title-y", 140.0 * 0.667);
		
		propertyMap.put("s-about-updatelogbtn-w", 45.0);
		propertyMap.put("s-about-updatelogbtn-h", 30.0);
		propertyMap.put("s-about-updatelogbtn-x", 280.0);
		propertyMap.put("s-about-updatelogbtn-y", 525.0);
		propertyMap.put("s-about-updatelogbtn-text-x", -26 / 2.0);
		propertyMap.put("s-about-updatelogbtn-text-y", 19.5 / 2.0);
		propertyMap.put("s-about-updatelogbtn-text-cn-x", -80 / 2.0);
		propertyMap.put("s-about-updatelogbtn-text-cn-y", 19.5 / 2.0);
		
		propertyMap.put("s-about-myblogbtn-w", 45.0);
		propertyMap.put("s-about-myblogbtn-h", 30.0);
		propertyMap.put("s-about-myblogbtn-text-x", -80 / 2.0);
		propertyMap.put("s-about-myblogbtn-text-y", 19.5 / 2.0);
		propertyMap.put("s-about-myblogbtn-x", 450.0);
		propertyMap.put("s-about-myblogbtn-y", 525.0);
		
		propertyMap.put("s-about-abouttext1-x", 240.0 * 0.667);
		propertyMap.put("s-about-abouttext1-y", 50.0 * 0.667);
		propertyMap.put("s-about-abouttext1-wrappingw", 750.0 * 0.667);
		
		propertyMap.put("s-about-abouttext2-x", 220.0 * 0.667);
		propertyMap.put("s-about-abouttext2-y", 200.0 * 0.667);
		propertyMap.put("s-about-abouttext2-wrappingw", 800.0 * 0.667);
		
		propertyMap.put("s-about-abouttext-h", 380.0 * 0.667);
	}
	
	private static void initLargeUpdateLogPane() {
		propertyMap.put("l-updatelog-title-x", 240.0);
		propertyMap.put("l-updatelog-title-y", 140.0);
		
		propertyMap.put("l-updatelog-text-h", 2000.0);
		propertyMap.put("l-updatelog-text-x", 270.0);
		propertyMap.put("l-updatelog-text-wrappingw", 780.0);
		propertyMap.put("l-updatelog-text7-y", 50.0);
		propertyMap.put("l-updatelog-text6-y", 470.0);
		propertyMap.put("l-updatelog-text5-y", 690.0);
		propertyMap.put("l-updatelog-text4-y", 1170.0);
		propertyMap.put("l-updatelog-text3-y", 1550.0);
		propertyMap.put("l-updatelog-text2-y", 1820.0);
		propertyMap.put("l-updatelog-text1-y", 2140.0);
		propertyMap.put("l-updatelog-abouttext1-wrappingw", 750.0);
	}
	
	private static void initSmallUpdateLogPane() {
		propertyMap.put("s-updatelog-title-x", 240.0 * 0.667);
		propertyMap.put("s-updatelog-title-y", 140.0 * 0.667);
		
		propertyMap.put("s-updatelog-text-h", 1800.0 * 0.67);
		propertyMap.put("s-updatelog-text-x", 270.0 * 0.67);
		propertyMap.put("s-updatelog-text-wrappingw", 780.0 * 0.67);
		propertyMap.put("s-updatelog-text7-y", 50.0 * 0.67);
		propertyMap.put("s-updatelog-text6-y", 570.0 * 0.67);
		propertyMap.put("s-updatelog-text5-y", 790.0 * 0.67);
		propertyMap.put("s-updatelog-text4-y", 1270.0 * 0.67);
		propertyMap.put("s-updatelog-text3-y", 1650.0 * 0.67);
		propertyMap.put("s-updatelog-text2-y", 1920.0 * 0.67);
		propertyMap.put("s-updatelog-text1-y", 2240.0 * 0.67);
		propertyMap.put("s-updatelog-abouttext1-wrappingw", 750.0 * 0.67);
	}
	
	private static void initLargeHowToPlayPane() {
		propertyMap.put("l-howtoplay-title-x", GameData.getWidth() / 2 - 180.0);
		propertyMap.put("l-howtoplay-title-y", 140.0);
		propertyMap.put("l-howtoplay-title-cn-x", GameData.getWidth() / 2 - 180.0);
		propertyMap.put("l-howtoplay-title-cn-y", 140.0);
		
		propertyMap.put("l-howtoplay-text-x", 110.0);
		propertyMap.put("l-howtoplay-text-y", 400.0);
		propertyMap.put("l-howtoplay-text-cn-x", 110.0);
		propertyMap.put("l-howtoplay-text-cn-y", 400.0);
		
		propertyMap.put("l-howtoplay-space-x", 140.0);
		propertyMap.put("l-howtoplay-space-y", 550.0);
		propertyMap.put("l-howtoplay-space-w", 500.0);
		propertyMap.put("l-howtoplay-space-h", 125.0);
		propertyMap.put("l-howtoplay-space-arcw", 40.0);
		propertyMap.put("l-howtoplay-space-arch", 40.0);
		propertyMap.put("l-howtoplay-space-strokew", 5.0);
		
		propertyMap.put("l-howtoplay-ball-x", 1000.0);
		propertyMap.put("l-howtoplay-ball-y", 480.0);
		propertyMap.put("l-howtoplay-ball-r", 25.0);
		
		propertyMap.put("l-howtoplay-hand-x", 450.0);
		propertyMap.put("l-howtoplay-hand-y", 600.0);
		propertyMap.put("l-howtoplay-hand-r-x", 10.0);
		propertyMap.put("l-howtoplay-hand-r-y", 140.0);
		propertyMap.put("l-howtoplay-hand-r-w", 80.0);
		propertyMap.put("l-howtoplay-hand-r-h", 20.0);
		double[] handArray = new double[]{0.0, 0.0, 30.0, 0.0, 30.0, 60.0, 100.0, 60.0, 100.0, 130.0, 0.0, 130.0};
		arrayMap.put("l-howtoplay-hand-points", handArray);
		
		propertyMap.put("l-howtoplay-r-w", 15.0);
		propertyMap.put("l-howtoplay-r-h", 50.0);
		propertyMap.put("l-howtoplay-r1-x", 350.0);
		propertyMap.put("l-howtoplay-r1-y", 580.0);
		propertyMap.put("l-howtoplay-r2-x", 420.0);
		propertyMap.put("l-howtoplay-r2-y", 530.0);
		propertyMap.put("l-howtoplay-r3-x", 500.0);
		propertyMap.put("l-howtoplay-r3-y", 530.0);
		
		double[] arrowArray = new double[]{0.0, 0.0, 95.0, -55.0, 77.7, -65.0, 167.5, -82.5, 132.7, -32.2, 119.0, -41.2, 50.0, 0.0, 150.0, 57.7, 125.0, 72.2};
		arrayMap.put("l-howtoplay-arrow-points", arrowArray);
		propertyMap.put("l-howtoplay-arrow-x", 790.0);
		propertyMap.put("l-howtoplay-arrow-y", 580.0);
		
		propertyMap.put("l-howtoplay-floor-radiusy", 55.0);
		propertyMap.put("l-howtoplay-floor1-x", 1000.0);
		propertyMap.put("l-howtoplay-floor1-y", 700.0);
		propertyMap.put("l-howtoplay-floor2-x", 905.0);
		propertyMap.put("l-howtoplay-floor2-y", 645.0);
		propertyMap.put("l-howtoplay-floor3-x", 810.0);
		propertyMap.put("l-howtoplay-floor3-y", 590.0);
		propertyMap.put("l-howtoplay-floor4-x", 905.0);
		propertyMap.put("l-howtoplay-floor4-y", 535.0);
		propertyMap.put("l-howtoplay-floor5-x", 1000.0);
		propertyMap.put("l-howtoplay-floor5-y", 480.0);
		propertyMap.put("l-howtoplay-floor6-x", 1095.0);
		propertyMap.put("l-howtoplay-floor6-y", 425.0);
	}
	
	private static void initSmallHowToPlayPane() {
		propertyMap.put("s-howtoplay-title-x", GameData.getWidth() / 2 - 120.0);
		propertyMap.put("s-howtoplay-title-y", 140.0 * 0.667);
		propertyMap.put("s-howtoplay-title-cn-x", GameData.getWidth() / 2 - 120.0);
		propertyMap.put("s-howtoplay-title-cn-y", 140.0 * 0.667);
		
		propertyMap.put("s-howtoplay-text-x", 110.0 * 0.667);
		propertyMap.put("s-howtoplay-text-y", 400.0 * 0.667);
		propertyMap.put("s-howtoplay-text-cn-x", 110.0 * 0.667);
		propertyMap.put("s-howtoplay-text-cn-y", 400.0 * 0.667);
		
		propertyMap.put("s-howtoplay-space-x", 140.0 * 0.667);
		propertyMap.put("s-howtoplay-space-y", 550.0 * 0.667);
		propertyMap.put("s-howtoplay-space-w", 500.0 * 0.667);
		propertyMap.put("s-howtoplay-space-h", 125.0 * 0.667);
		propertyMap.put("s-howtoplay-space-arcw", 16.0);
		propertyMap.put("s-howtoplay-space-arch", 16.0);
		propertyMap.put("s-howtoplay-space-strokew", 2.0);
		
		propertyMap.put("s-howtoplay-ball-x", 1000.0 * 0.667);
		propertyMap.put("s-howtoplay-ball-y", 480.0 * 0.667);
		propertyMap.put("s-howtoplay-ball-r", 16.0);
		
		propertyMap.put("s-howtoplay-hand-x", 300.0);
		propertyMap.put("s-howtoplay-hand-y", 400.0);
		propertyMap.put("s-howtoplay-hand-r-x", 10.0 * 0.667);
		propertyMap.put("s-howtoplay-hand-r-y", 140.0 * 0.667);
		propertyMap.put("s-howtoplay-hand-r-w", 80.0 * 0.667);
		propertyMap.put("s-howtoplay-hand-r-h", 20.0 * 0.667);
		double[] handArray = new double[]{0.0, 0.0, 20.0, 0.0, 20.0, 40.0, 66.6, 40.0, 66.6, 86.6, 0.0, 86.6};
		arrayMap.put("s-howtoplay-hand-points", handArray);
		
		propertyMap.put("s-howtoplay-r-w", 10.0);
		propertyMap.put("s-howtoplay-r-h", 50.0 * 0.667);
		propertyMap.put("s-howtoplay-r1-x", 350.0 * 0.667);
		propertyMap.put("s-howtoplay-r1-y", 580.0 * 0.667);
		propertyMap.put("s-howtoplay-r2-x", 420.0 * 0.667);
		propertyMap.put("s-howtoplay-r2-y", 530.0 * 0.667);
		propertyMap.put("s-howtoplay-r3-x", 500.0 * 0.667);
		propertyMap.put("s-howtoplay-r3-y", 530.0 * 0.667);
		
		double[] arrowArray = new double[]{0.0, 0.0, 95.0 * 0.667, -55.0 * 0.667, 77.7 * 0.667, -65.0 * 0.667, 167.5 * 0.667, -82.5 * 0.667, 132.7 * 0.667, -32.2 * 0.667, 127.7 * 0.667 - 5 * 1.732 * 0.667, -36.2 * 0.667 - 5 * 0.667, 50.0 * 0.667, 0.0, 150.0 * 0.667, 57.7 * 0.667, 150.0 * 0.667 - 25 * 0.667, 57.7 * 0.667 + 25 / 1.73 * 0.667};
		arrayMap.put("s-howtoplay-arrow-points", arrowArray);
		propertyMap.put("s-howtoplay-arrow-x", 790.0 * 0.667);
		propertyMap.put("s-howtoplay-arrow-y", 580.0 * 0.667);
		
		propertyMap.put("s-howtoplay-floor-radiusy", 36.0);
		propertyMap.put("s-howtoplay-floor1-x", 1000.0 * 0.667);
		propertyMap.put("s-howtoplay-floor1-y", 700.0 * 0.667);
		propertyMap.put("s-howtoplay-floor2-x", 905.0 * 0.667);
		propertyMap.put("s-howtoplay-floor2-y", 645.0 * 0.667);
		propertyMap.put("s-howtoplay-floor3-x", 810.0 * 0.667);
		propertyMap.put("s-howtoplay-floor3-y", 590.0 * 0.667);
		propertyMap.put("s-howtoplay-floor4-x", 905.0 * 0.667);
		propertyMap.put("s-howtoplay-floor4-y", 535.0 * 0.667);
		propertyMap.put("s-howtoplay-floor5-x", 1000.0 * 0.667);
		propertyMap.put("s-howtoplay-floor5-y", 480.0 * 0.667);
		propertyMap.put("s-howtoplay-floor6-x", 1095.0 * 0.667);
		propertyMap.put("s-howtoplay-floor6-y", 425.0 * 0.667);
	}

}
