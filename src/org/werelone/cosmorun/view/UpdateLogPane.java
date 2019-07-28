package org.werelone.cosmorun.view;

import org.werelone.cosmorun.article.UpdateLogArticle;
import org.werelone.cosmorun.ui.MenuBackground;
import org.werelone.cosmorun.ui.Text;
import org.werelone.cosmorun.ui.Text.TextFont;
import org.werelone.cosmorun.ui.buttons.CloseButton;
import org.werelone.cosmorun.util.GameData;
import org.werelone.cosmorun.util.Language;
import org.werelone.cosmorun.util.Property;
import org.werelone.cosmorun.view.RootLayout.FrontPane;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class UpdateLogPane extends Pane{
	// 更新日志界面的单例
	private static UpdateLogPane pane;
	private CloseButton closeButton;
	private Text logTitle;
	private LogText logText;
	
	public static UpdateLogPane create() {
		pane = new UpdateLogPane();
		pane.init();
		return pane;
	}
	
	public static UpdateLogPane getInstance() {
		return pane;
	}
	
	private void init() {
		// 关于界面背景
		this.getChildren().add(new MenuBackground());
		initButton();
		initText();
		// 切换语言
		changeLang();
	}

	private void initButton() {
		closeButton = new CloseButton();
		closeButton.setLayoutX(Property.get("closebtn-x"));
		closeButton.setLayoutY(Property.get("closebtn-y"));
		closeButton.setOnMouseClicked(e -> RootLayout.changeFrontPane(FrontPane.about));
		this.getChildren().add(closeButton);
	}
	
	private void initText() {
		logTitle = new Text("Update Log", Property.get("updatelog-title-x"), Property.get("updatelog-title-y"), TextFont.GillSans, 80, Color.hsb(0, 0.0, 1.0, 0.90), true);
		this.getChildren().add(logTitle);
		
		logText = new LogText();
		logText.setLayoutY(GameData.getHeight() * 0.35);
		this.getChildren().add(logText);
	}
	
	// 切换语言
	public static void changeLang() {
		if (GameData.language == 0) {
			pane.logTitle.setFont(TextFont.GillSans, 80, true);
		}
		else {
			pane.logTitle.setFont(TextFont.MsYaHei, 65, false);
		}
		pane.logTitle.setText(Language.getAutoText("updatelog-title"));
	}

}

class LogText extends Pane {
	private Group logText = new Group();
	private double textHeight = Property.get("updatelog-text-h");
	
	public LogText() {
		this.setClip(new Rectangle(GameData.getWidth(), GameData.getHeight() * 0.55));
		
		this.getChildren().add(logText);
		this.setOnScroll();
		
		Text logText1 = new Text(UpdateLogArticle.text1, Property.get("updatelog-text-x"), Property.get("updatelog-text1-y"), TextFont.MsYaHei, 35, false);
		Text logText2 = new Text(UpdateLogArticle.text2, Property.get("updatelog-text-x"), Property.get("updatelog-text2-y"), TextFont.MsYaHei, 35, false);
		Text logText3 = new Text(UpdateLogArticle.text3, Property.get("updatelog-text-x"), Property.get("updatelog-text3-y"), TextFont.MsYaHei, 35, false);
		Text logText4 = new Text(UpdateLogArticle.text4, Property.get("updatelog-text-x"), Property.get("updatelog-text4-y"), TextFont.MsYaHei, 35, false);
		Text logText5 = new Text(UpdateLogArticle.text5, Property.get("updatelog-text-x"), Property.get("updatelog-text5-y"), TextFont.MsYaHei, 35, false);
		Text logText6 = new Text(UpdateLogArticle.text6, Property.get("updatelog-text-x"), Property.get("updatelog-text6-y"), TextFont.MsYaHei, 35, false);
		Text logText7 = new Text(UpdateLogArticle.text7, Property.get("updatelog-text-x"), Property.get("updatelog-text7-y"), TextFont.MsYaHei, 35, false);

		logText.getChildren().addAll(
				logText1, logText2, logText3, logText4, logText5, logText6, logText7);
		
		for (int i = 0; i < logText.getChildren().size(); i++) {
			Text t = (Text)logText.getChildren().get(i);
			t.setWrappingWidth(Property.get("updatelog-text-wrappingw"));
		}
	}
	
	private void setOnScroll() {
		this.setOnScroll(e -> {
			logText.setLayoutY(logText.getLayoutY() + e.getDeltaY());
			if (logText.getLayoutY() > 0)
				logText.setLayoutY(0);
			if (logText.getLayoutY() < -textHeight)
				logText.setLayoutY(-textHeight);
		});
	}
}