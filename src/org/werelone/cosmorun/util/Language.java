package org.werelone.cosmorun.util;

import java.util.HashMap;
import java.util.Map;

public class Language {
	private static Map<String, String> languageMap;
	
	public static void init() {
		languageMap = new HashMap<>();
		initEN();
		initCN();
	}
	
	private static void initEN() {
		languageMap.put("en-startmenu-title", "COSMO RUN");
		languageMap.put("en-startmenu-best", "BEST");
		languageMap.put("en-startmenu-play", "PLAY");
		languageMap.put("en-option-title", "OPTION");
		languageMap.put("en-option-howtoplay", "HOW TO PLAY");
		languageMap.put("en-option-color", "COLOR");
		languageMap.put("en-option-language", "LANGUAGE - EN");
		languageMap.put("en-about-title", "ABOUT");
		languageMap.put("en-about-updatelog", "LOG");
		languageMap.put("en-about-blog", "MY BLOG");
		languageMap.put("en-updatelog-title", "UPDATE LOG");
		languageMap.put("en-gameover-title", "Game Over");
		languageMap.put("en-gameover-best", "BEST");
		languageMap.put("en-gameover-newbest", "NEW BEST!");
		languageMap.put("en-gameover-tryagain", "TRY AGAIN");
		languageMap.put("en-howtoplay-title", "HOW TO PLAY");
		languageMap.put("en-howtoplay-tip", "Press Space to make a turn");
	}
	
	private static void initCN() {
		languageMap.put("cn-startmenu-title", "宇宙漫步");
		languageMap.put("cn-startmenu-best", "最高分");
		languageMap.put("cn-startmenu-play", "开始");
		languageMap.put("cn-option-title", "设置");
		languageMap.put("cn-option-howtoplay", "游戏说明");
		languageMap.put("cn-option-color", "背景色");
		languageMap.put("cn-option-language", "语言 - 简体中文");
		languageMap.put("cn-about-title", "关于");
		languageMap.put("cn-about-updatelog", "升级日志");
		languageMap.put("cn-about-blog", "我的博客");
		languageMap.put("cn-updatelog-title", "更新日志");
		languageMap.put("cn-gameover-title", "游戏结束");
		languageMap.put("cn-gameover-best", "最高分");
		languageMap.put("cn-gameover-newbest", "  新纪录！");
		languageMap.put("cn-gameover-tryagain", "重新开始");
		languageMap.put("cn-howtoplay-title", "游戏说明");
		languageMap.put("cn-howtoplay-tip", "点击鼠标或空格改变小球的运动方向");
	}
	
	public static String getText(String key) {
		return languageMap.get(key);
	}
	
	public static String getAutoText(String key) {
		if (GameData.language == 0) {
			return languageMap.get("en-" + key);
		}
		else {
			return languageMap.get("cn-" + key);
		}
	}
}
