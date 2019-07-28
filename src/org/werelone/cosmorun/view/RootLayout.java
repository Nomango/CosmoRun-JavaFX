package org.werelone.cosmorun.view;

import java.util.HashMap;
import java.util.Map;

import org.werelone.cosmorun.animation.Fade;
import org.werelone.cosmorun.animation.Show;
import org.werelone.cosmorun.util.GameData;
import org.werelone.cosmorun.util.Language;
import org.werelone.cosmorun.util.Music;
import org.werelone.cosmorun.util.Property;

import javafx.scene.layout.Pane;

public class RootLayout extends Pane{
	// 主面板的单例
	private static RootLayout mainPane;
	// 前端界面标志
	public static enum FrontPane {cover, startMenu, touch, option, howToPlay, about, updateLog, gameOver};
	// 当前前端界面
	private FrontPane currentLayer;
	// 背景布局、游戏布局、前端布局
	private Pane bgPane, gamePane, frontPane, logoPane;
	// HashMap储存前端布局
	private Map<FrontPane, Pane> frontPaneMap;
	// 标志是否正在切换界面
	private static boolean isChanging;
	
	// 获取主面板
	public static Pane create() {
		mainPane = new RootLayout();
		// 重置主面板
		mainPane.clear();
		// 初始化主面板
		mainPane.init();
		return mainPane;
	}
	
	public void init() {
		// 初始化属性数据
		Property.init();
		// 初始化语言包
		Language.init();
		// 初始化背景音乐
		Music.init();
		// 初始化界面
		bgPane = BackgroundPane.create();
		gamePane = GamePane.create();
		frontPane = new Pane();
		logoPane = LogoPane.create();
		mainPane.getChildren().addAll(bgPane, gamePane, frontPane, logoPane);
		// 将所有的前端布局加入frontPaneMap
		frontPaneMap.put(FrontPane.startMenu, StartMenuPane.create());
		frontPaneMap.put(FrontPane.touch, TouchPane.create());
		frontPaneMap.put(FrontPane.gameOver, GameOverPane.create());
		frontPaneMap.put(FrontPane.howToPlay, HowToPlayPane.create());
		frontPaneMap.put(FrontPane.option, OptionPane.create());
		frontPaneMap.put(FrontPane.about, AboutPane.create());
		frontPaneMap.put(FrontPane.updateLog, UpdateLogPane.create());
		// 将所有的前端布局设为透明
		for (FrontPane key: frontPaneMap.keySet()) {
			Pane pane = frontPaneMap.get(key);
			pane.setOpacity(0);
			frontPane.getChildren().add(pane);
		}
		// 添加遮挡层
		frontPaneMap.put(FrontPane.cover, CoverPane.create());
		frontPane.getChildren().add(frontPaneMap.get(FrontPane.cover));
		// 清除属性数据占用的空间
		Property.clear();
	}
	
	// 切换前端界面
	public static boolean changeFrontPane(FrontPane front) {
		if (isChanging || mainPane.currentLayer == front) {
			return false;
		} else {
			isChanging = true;
			// 将切换的界面放到最前
			mainPane.frontPaneMap.get(front).toFront();
			// 监听焦点事件
			mainPane.frontPaneMap.get(front).requestFocus();
			// 隐藏当前界面
			Fade.setNode(mainPane.frontPaneMap.get(mainPane.currentLayer), f -> {
				// 显示切换的界面
				Show.setNode(mainPane.frontPaneMap.get(front), s -> {
					isChanging = false;
				});
			});
			mainPane.currentLayer = front;
			return true;
		}
	}
	
	// 重置主面板
	private void clear() {
		bgPane = null;
		gamePane = null;
		frontPane = null;
		frontPaneMap = new HashMap<>();
	}
	
	public static void show() {
		// 播放背景音乐
		if (GameData.musicOn)
			Music.playBgMusic();
		// 播放背景动画
		BackgroundPane.getInstance().play();

		// 游戏最高分为0时显示游戏说明界面
		if (GameData.showTip) {
			changeFrontPane(FrontPane.howToPlay);
		}
		
		Fade.setNode(mainPane.logoPane, e -> {
			mainPane.getChildren().remove(mainPane.logoPane);
			mainPane.logoPane = null;
		});
	}

}
