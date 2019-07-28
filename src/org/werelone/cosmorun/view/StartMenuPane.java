package org.werelone.cosmorun.view;

import org.werelone.cosmorun.ui.Shadow;
import org.werelone.cosmorun.ui.Text;
import org.werelone.cosmorun.ui.Text.TextFont;
import org.werelone.cosmorun.ui.buttons.AboutButton;
import org.werelone.cosmorun.ui.buttons.OptionButton;
import org.werelone.cosmorun.ui.buttons.PlayButton;
import org.werelone.cosmorun.util.GameData;
import org.werelone.cosmorun.util.Language;
import org.werelone.cosmorun.util.Property;
import org.werelone.cosmorun.view.RootLayout.FrontPane;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class StartMenuPane extends Pane{
	// 开始菜单的单例
	private static StartMenuPane pane;
	// 游戏标题
	private ImageView menuTitle;
	// 中文标题
	private Text menuTitle_cn;
	// 最高分
	private Text bestScoreText;
	// 中文最高分
	//private Text bestScoreText_cn;
	// 开始按钮
	private PlayButton btnPlay;
	// 关于按钮
	private AboutButton btnAbout;
	// 关于按钮
	private OptionButton btnOption;
	
	public static StartMenuPane create() {
		pane = new StartMenuPane();
		pane.init();
		return pane;
	}
	
	public static StartMenuPane getInstance() {
		return pane;
	}
	
	private void init() {
		this.setMinSize(GameData.getWidth(), GameData.getHeight());
		// 游戏标题
		menuTitle = new ImageView("resources/title.png");
		menuTitle.setLayoutX(Property.get("startmenu-title-x"));
		menuTitle.setLayoutY(Property.get("startmenu-title-y"));
		menuTitle.setEffect(Shadow.create());
		// 标题缩放
		if (GameData.getResolution() == 0) {
			menuTitle.setScaleX(0.66);
			menuTitle.setScaleY(0.66);
		}
		this.getChildren().add(menuTitle);
		// 中文标题
		menuTitle_cn = new Text(Language.getText("cn-startmenu-title"), Property.get("startmenu-title-cn-x"), Property.get("startmenu-title-cn-y"), TextFont.MsYaHei, 100, true);
		this.getChildren().add(menuTitle_cn);
		// 初始化最高分
		bestScoreText = new Text("", Property.get("startmenu-bestscoretext-x"), Property.get("startmenu-bestscoretext-y"), TextFont.GillSans, 65, true);
		this.flushBestScore();
		this.getChildren().add(bestScoreText);
		// 按空格开始游戏
		this.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.SPACE) {
				if(RootLayout.changeFrontPane(FrontPane.touch)) {
					GamePane.getInstance().play();
				}
			}
		});
		// 初始化开始按钮
		btnPlay = new PlayButton();
		btnPlay.setLayoutX(GameData.getWidth() / 2);
		btnPlay.setLayoutY(GameData.getHeight() / 1.3);
		// 点击开始按钮隐藏菜单界面并开始游戏
		btnPlay.setOnMouseClicked(e -> {
			if(RootLayout.changeFrontPane(FrontPane.touch)) {
				GamePane.getInstance().play();
			}
		});
		this.getChildren().add(btnPlay);
		// 初始化关于按钮
		btnAbout = new AboutButton();
		btnAbout.setLayoutX(Property.get("startmenu-aboutbtn-x"));
		btnAbout.setLayoutY(Property.get("startmenu-aboutbtn-y"));
		btnAbout.setOnMouseClicked(e -> RootLayout.changeFrontPane(FrontPane.about));
		this.getChildren().add(btnAbout);
		// 初始化设置按钮
		btnOption = new OptionButton();
		btnOption.setLayoutX(Property.get("startmenu-optionbtn-x"));
		btnOption.setLayoutY(Property.get("startmenu-optionbtn-y"));
		btnOption.setOnMouseClicked(e -> RootLayout.changeFrontPane(FrontPane.option));
		this.getChildren().add(btnOption);
		
		// 切换语言
		changeLang();
	}
	
	// 刷新最高分
	public void flushBestScore() {
		if (GameData.getBestScore() != 0) {
			bestScoreText.setText(String.format("%s %3d", Language.getAutoText("startmenu-best"), GameData.getBestScore()));
		}
	}
	
	// 切换语言
	public static void changeLang() {
		if (GameData.language == 0) {
			pane.bestScoreText.setFont(TextFont.GillSans, 65, true);
			pane.menuTitle_cn.setOpacity(0);
			pane.menuTitle.setOpacity(1);
		}
		else {
			pane.bestScoreText.setFont(TextFont.MsYaHei, 40, false);
			pane.menuTitle_cn.setOpacity(1);
			pane.menuTitle.setOpacity(0);
		}
		pane.flushBestScore();
		pane.btnPlay.changeLang();
	}

}
