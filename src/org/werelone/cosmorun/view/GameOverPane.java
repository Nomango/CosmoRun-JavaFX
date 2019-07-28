package org.werelone.cosmorun.view;

import org.werelone.cosmorun.ui.MenuBackground;
import org.werelone.cosmorun.ui.Text;
import org.werelone.cosmorun.ui.Text.TextFont;
import org.werelone.cosmorun.ui.buttons.TryAgainButton;
import org.werelone.cosmorun.util.GameData;
import org.werelone.cosmorun.util.Language;
import org.werelone.cosmorun.util.Property;
import org.werelone.cosmorun.view.RootLayout.FrontPane;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GameOverPane extends Pane{
	private static GameOverPane pane;
	private TryAgainButton btnTryAgain;
	private Text bestScoreText;
	private Text endTitle;
	private Text endTitle_cn;
	private Text scoreText;
	
	public static GameOverPane create() {
		pane = new GameOverPane();
		pane.init();
		return pane;
	}
	
	public static GameOverPane getInstance() {
		return pane;
	}
	
	private void init() {
		// 游戏结束界面背景
		this.getChildren().add(new MenuBackground(Color.rgb(40, 40, 40, 0.6)));
		// 按空格回到开始菜单
		this.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.SPACE) {
				if(RootLayout.changeFrontPane(FrontPane.cover)) {
					GamePane.getInstance().playResetAnimation();
				}
				
			}
		});
		// 重新开始按钮
		btnTryAgain = new TryAgainButton();
		btnTryAgain.setLayoutX(Property.get("gameover-tryagainbtn-x"));
		btnTryAgain.setLayoutY(Property.get("gameover-tryagainbtn-y"));
		btnTryAgain.setOnMouseClicked(e -> {
			if(RootLayout.changeFrontPane(FrontPane.cover)) {
				GamePane.getInstance().playResetAnimation();
			}
		});
		this.getChildren().add(btnTryAgain);
		// 结束界面文字
		endTitle = new Text(Language.getText("en-gameover-title"), Property.get("gameover-title-x"), Property.get("gameover-title-y"), TextFont.GillSans, 120, true);
		endTitle_cn = new Text(Language.getText("cn-gameover-title"), Property.get("gameover-title-cn-x"), Property.get("gameover-title-cn-y"), TextFont.MsYaHei, 100, false);
		scoreText = new Text("", Property.get("gameover-scoretext-x"), Property.get("gameover-scoretext-y"), TextFont.GillSans, 83, true);
		bestScoreText = new Text("", Property.get("gameover-bestscoretext-x"), Property.get("gameover-bestscoretext-y"), TextFont.GillSans, 65, true);
		this.getChildren().addAll(scoreText, bestScoreText, endTitle, endTitle_cn);
	
		changeLang();
	}
	
	// 刷新最高分
	public void flushScore(boolean newBest) {
		scoreText.setText(GameData.getScore() + "");
		if (newBest) {
			bestScoreText.setText(Language.getAutoText("gameover-newbest"));
		} else if (GameData.getBestScore() != 0) {
			bestScoreText.setText(String.format("%s %3d", Language.getAutoText("gameover-best"), GameData.getBestScore()));
		}
	}
	
	// 切换语言
	public static void changeLang() {
		if (GameData.language == 0) {
			pane.bestScoreText.setFont(TextFont.GillSans, 65, true);
			pane.endTitle_cn.setOpacity(0);
			pane.endTitle.setOpacity(1);
		}
		else {
			pane.bestScoreText.setFont(TextFont.MsYaHei, 40, false);
			pane.endTitle_cn.setOpacity(1);
			pane.endTitle.setOpacity(0);
		}
		pane.btnTryAgain.changeLang();
	}

}
