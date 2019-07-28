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
		languageMap.put("cn-startmenu-title", "��������");
		languageMap.put("cn-startmenu-best", "��߷�");
		languageMap.put("cn-startmenu-play", "��ʼ");
		languageMap.put("cn-option-title", "����");
		languageMap.put("cn-option-howtoplay", "��Ϸ˵��");
		languageMap.put("cn-option-color", "����ɫ");
		languageMap.put("cn-option-language", "���� - ��������");
		languageMap.put("cn-about-title", "����");
		languageMap.put("cn-about-updatelog", "������־");
		languageMap.put("cn-about-blog", "�ҵĲ���");
		languageMap.put("cn-updatelog-title", "������־");
		languageMap.put("cn-gameover-title", "��Ϸ����");
		languageMap.put("cn-gameover-best", "��߷�");
		languageMap.put("cn-gameover-newbest", "  �¼�¼��");
		languageMap.put("cn-gameover-tryagain", "���¿�ʼ");
		languageMap.put("cn-howtoplay-title", "��Ϸ˵��");
		languageMap.put("cn-howtoplay-tip", "�������ո�ı�С����˶�����");
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
