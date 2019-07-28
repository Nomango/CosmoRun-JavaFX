package org.werelone.cosmorun.view;

import java.net.URL;

import org.werelone.cosmorun.article.AboutArticle;
import org.werelone.cosmorun.ui.MenuBackground;
import org.werelone.cosmorun.ui.Text;
import org.werelone.cosmorun.ui.Text.TextFont;
import org.werelone.cosmorun.ui.buttons.CloseButton;
import org.werelone.cosmorun.ui.buttons.MyBlogButton;
import org.werelone.cosmorun.ui.buttons.UpdateLogButton;
import org.werelone.cosmorun.util.GameData;
import org.werelone.cosmorun.util.Language;
import org.werelone.cosmorun.util.Property;
import org.werelone.cosmorun.view.RootLayout.FrontPane;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class AboutPane extends Pane{
	// 关于界面的单例
	private static AboutPane pane;
	private CloseButton closeButton;
	private Text aboutTitle;
	private AboutText aboutText;

	public static AboutPane create() {
		pane = new AboutPane();
		pane.init();
		return pane;
	}
	
	public static AboutPane getInstance() {
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
		closeButton.setOnMouseClicked(e -> RootLayout.changeFrontPane(FrontPane.startMenu));
		this.getChildren().add(closeButton);
	}
	
	private void initText() {
		aboutTitle = new Text("", Property.get("about-title-x"), Property.get("about-title-y"), TextFont.GillSans, 80, Color.hsb(0, 0.0, 1.0, 0.90), true);
		this.getChildren().add(aboutTitle);
		
		aboutText = new AboutText();
		aboutText.setLayoutY(GameData.getHeight() * 0.35);
		this.getChildren().add(aboutText);
	}
	
	// 切换语言
	public static void changeLang() {
		if (GameData.language == 0) {
			pane.aboutTitle.setFont(TextFont.GillSans, 80, true);
		}
		else {
			pane.aboutTitle.setFont(TextFont.MsYaHei, 65, false);
		}
		pane.aboutTitle.setText(Language.getAutoText("about-title"));
		pane.aboutText.btnUpdateLog.changeLang();
		pane.aboutText.btnMyBlog.changeLang();
	}
}

class AboutText extends Pane {
	private Group aboutText = new Group();
	private double textHeight = Property.get("about-abouttext-h");
	public UpdateLogButton btnUpdateLog;
	public MyBlogButton btnMyBlog;
	
	public AboutText() {
		this.setClip(new Rectangle(GameData.getWidth(), GameData.getHeight() * 0.55));
		this.initButton();
		this.getChildren().add(aboutText);
		this.setOnScroll();
		
		Text aboutText1 = new Text(AboutArticle.text1, Property.get("about-abouttext1-x"), Property.get("about-abouttext1-y"), TextFont.MsYaHei, 32, Color.hsb(0, 0, 0.8, 0.8), false);
		aboutText1.setWrappingWidth(Property.get("about-abouttext1-wrappingw"));
		
		Text aboutText2 = new Text(AboutArticle.text2, Property.get("about-abouttext2-x"), Property.get("about-abouttext2-y"), TextFont.MsYaHei, 32, false);
		aboutText2.setWrappingWidth(Property.get("about-abouttext2-wrappingw"));
		
		aboutText.getChildren().addAll(aboutText1, aboutText2);
	}
	
	private void initButton() {
		btnUpdateLog = new UpdateLogButton();
		btnUpdateLog.setLayoutX(Property.get("about-updatelogbtn-x"));
		btnUpdateLog.setLayoutY(Property.get("about-updatelogbtn-y"));
		btnUpdateLog.setOnMouseClicked(e -> RootLayout.changeFrontPane(FrontPane.updateLog));
		aboutText.getChildren().add(btnUpdateLog);
		
		btnMyBlog = new MyBlogButton();
		btnMyBlog.setLayoutX(Property.get("about-myblogbtn-x"));
		btnMyBlog.setLayoutY(Property.get("about-myblogbtn-y"));
		btnMyBlog.setOnMouseClicked(a -> {
			try {
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + new URL("http://www.werelone.cn/"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		aboutText.getChildren().add(btnMyBlog);
	}
	
	private void setOnScroll() {
		this.setOnScroll(e -> {
			aboutText.setLayoutY(aboutText.getLayoutY() + e.getDeltaY());
			if (aboutText.getLayoutY() > 0)
				aboutText.setLayoutY(0);
			if (aboutText.getLayoutY() < -textHeight)
				aboutText.setLayoutY(-textHeight);
		});
	}
}