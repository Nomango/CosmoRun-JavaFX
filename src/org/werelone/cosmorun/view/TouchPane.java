package org.werelone.cosmorun.view;

import org.werelone.cosmorun.ui.Text;
import org.werelone.cosmorun.ui.Text.TextFont;
import org.werelone.cosmorun.util.GameData;
import org.werelone.cosmorun.util.Property;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class TouchPane extends Pane{
	private static TouchPane pane;
	// 计分板
	private Text scoreText;
	
	public static TouchPane create() {
		pane = new TouchPane();
		pane.init();
		return pane;
	}
	
	public static TouchPane getInstance() {
		return pane;
	}
	
	public void bonusPoint() {
		GameData.bonusPoint();
		scoreText.setText(GameData.getScore() + "");
	}
	
	public void resetScoreText() {
		scoreText.setText("");
	}
	
	private void init() {
		this.setMinSize(GameData.getWidth(), GameData.getHeight());
		// 点击鼠标切换方向
		this.setOnMousePressed(e -> {
			GamePane.getInstance().changeDirect();
		});
		// 按空格切换方向
		this.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.SPACE) {
				GamePane.getInstance().changeDirect();
			}
		});
		// 初始化计分板
		scoreText = new Text("", Property.get("touch-scoretext-x"), Property.get("touch-scoretext-y"), TextFont.GillSans, 80, true);
		// 计分板
		this.getChildren().add(scoreText);
	}

}
