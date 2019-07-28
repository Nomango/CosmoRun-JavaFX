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
	// �����ĵ���
	private static RootLayout mainPane;
	// ǰ�˽����־
	public static enum FrontPane {cover, startMenu, touch, option, howToPlay, about, updateLog, gameOver};
	// ��ǰǰ�˽���
	private FrontPane currentLayer;
	// �������֡���Ϸ���֡�ǰ�˲���
	private Pane bgPane, gamePane, frontPane, logoPane;
	// HashMap����ǰ�˲���
	private Map<FrontPane, Pane> frontPaneMap;
	// ��־�Ƿ������л�����
	private static boolean isChanging;
	
	// ��ȡ�����
	public static Pane create() {
		mainPane = new RootLayout();
		// ���������
		mainPane.clear();
		// ��ʼ�������
		mainPane.init();
		return mainPane;
	}
	
	public void init() {
		// ��ʼ����������
		Property.init();
		// ��ʼ�����԰�
		Language.init();
		// ��ʼ����������
		Music.init();
		// ��ʼ������
		bgPane = BackgroundPane.create();
		gamePane = GamePane.create();
		frontPane = new Pane();
		logoPane = LogoPane.create();
		mainPane.getChildren().addAll(bgPane, gamePane, frontPane, logoPane);
		// �����е�ǰ�˲��ּ���frontPaneMap
		frontPaneMap.put(FrontPane.startMenu, StartMenuPane.create());
		frontPaneMap.put(FrontPane.touch, TouchPane.create());
		frontPaneMap.put(FrontPane.gameOver, GameOverPane.create());
		frontPaneMap.put(FrontPane.howToPlay, HowToPlayPane.create());
		frontPaneMap.put(FrontPane.option, OptionPane.create());
		frontPaneMap.put(FrontPane.about, AboutPane.create());
		frontPaneMap.put(FrontPane.updateLog, UpdateLogPane.create());
		// �����е�ǰ�˲�����Ϊ͸��
		for (FrontPane key: frontPaneMap.keySet()) {
			Pane pane = frontPaneMap.get(key);
			pane.setOpacity(0);
			frontPane.getChildren().add(pane);
		}
		// ����ڵ���
		frontPaneMap.put(FrontPane.cover, CoverPane.create());
		frontPane.getChildren().add(frontPaneMap.get(FrontPane.cover));
		// �����������ռ�õĿռ�
		Property.clear();
	}
	
	// �л�ǰ�˽���
	public static boolean changeFrontPane(FrontPane front) {
		if (isChanging || mainPane.currentLayer == front) {
			return false;
		} else {
			isChanging = true;
			// ���л��Ľ���ŵ���ǰ
			mainPane.frontPaneMap.get(front).toFront();
			// ���������¼�
			mainPane.frontPaneMap.get(front).requestFocus();
			// ���ص�ǰ����
			Fade.setNode(mainPane.frontPaneMap.get(mainPane.currentLayer), f -> {
				// ��ʾ�л��Ľ���
				Show.setNode(mainPane.frontPaneMap.get(front), s -> {
					isChanging = false;
				});
			});
			mainPane.currentLayer = front;
			return true;
		}
	}
	
	// ���������
	private void clear() {
		bgPane = null;
		gamePane = null;
		frontPane = null;
		frontPaneMap = new HashMap<>();
	}
	
	public static void show() {
		// ���ű�������
		if (GameData.musicOn)
			Music.playBgMusic();
		// ���ű�������
		BackgroundPane.getInstance().play();

		// ��Ϸ��߷�Ϊ0ʱ��ʾ��Ϸ˵������
		if (GameData.showTip) {
			changeFrontPane(FrontPane.howToPlay);
		}
		
		Fade.setNode(mainPane.logoPane, e -> {
			mainPane.getChildren().remove(mainPane.logoPane);
			mainPane.logoPane = null;
		});
	}

}
