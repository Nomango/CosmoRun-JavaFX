package game.pane;

import game.Game;
import game.button.CloseButton;
import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class LogPane extends Pane{
	public boolean status = true;
	private CloseButton closeButton = new CloseButton();
	private Rectangle background1 = new Rectangle(0, 0, Game.width, Game.height * 0.25);
	private Rectangle background2 = new Rectangle(0, Game.height * 0.25, Game.width, Game.height);
	private Text logTitle = new Text("更新日志");
	private LogText logText = new LogText();
	
	public LogPane() {
		initBackground();
		initButton();
		initText();
		
		this.setCache(true);
		this.setCacheHint(CacheHint.SPEED);
	}
	
	private void initBackground() {
		background1.setFill(Color.rgb(60, 55, 87, 0.5));
		background2.setFill(Color.rgb(63, 38, 75, 0.6));
		this.getChildren().addAll(background1, background2);
	}

	private void initButton() {
		closeButton.setLayoutX(110);
		closeButton.setLayoutY(110);
		this.getChildren().add(closeButton);
	}
	
	private void initText() {
		logTitle.setLayoutX(240);
		logTitle.setLayoutY(130);
		logTitle.setFill(Color.hsb(0, 0.0, 1.0, 0.90));
		logTitle.setFont(Font.font("微软雅黑",FontWeight.BOLD, 55));
		DropShadow dropShadow = new DropShadow(5, 3, 3, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		logTitle.setEffect(dropShadow);
		this.getChildren().add(logTitle);
		
		logText.setLayoutY(Game.height * 0.35);
		this.getChildren().add(logText);
	}
	
	public void setBtCloseOnMouseClicked(EventHandler<? super MouseEvent> e) {
		this.closeButton.setOnMouseClicked(e);
	}

}

class LogText extends Pane {
	private Group logText = new Group();
	
	public LogText() {
	//	this.setWidth(Game.width);
	//	this.setHeight(Game.height * 0.55);
	//	this.setMaxWidth(Game.width);
	//	this.setMaxHeight(Game.height * 0.55);
		this.setClip(new Rectangle(Game.width, Game.height * 0.55));
		
		this.getChildren().add(logText);
		this.setOnScroll();
		
		Text logText1 = new Text(
				"v1.1  2016.6.1更新\n" + 
				"     优化了游戏流畅性\n" + 
				"     修复了部分BUG\n" + 
				"     增加了游戏动画\n" + 
				"     更新了开始按钮样式\n" + 
				"     关于按钮移至设置界面\n");
		Text logText2 = new Text(
				"v1.2  2016.6.10更新\n" + 
				"     修复了某些情况下游戏无法结束的BUG\n" + 
				"     增加了背景音乐\n" + 
				"     增加了可选的背景颜色\n" + 
				"     增加了死亡动画和开始游戏动画\n");
		Text logText3 = new Text(
				"v1.3  2016.6.11更新\n" + 
				"     增加了自动存档功能\n" + 
				"     增加了更新日志界面\n" + 
				"     增加了游戏说明\n");
		Text logTextProblem = new Text(
				"已知的BUG:\n" + 
				"     相同类型的四个板块会连在一起\n" + 
				"     某些情况下，开始游戏后背景上有主菜单的残影\n");
		
		logText1.setLayoutY(50);
		logText2.setLayoutY(430);
		logText3.setLayoutY(750);
		logTextProblem.setLayoutY(1020);
		logText.getChildren().addAll(logText1, logText2, logText3, logTextProblem);
		
		DropShadow dropShadow = new DropShadow(5, 3, 3, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		for (int i = 0; i < logText.getChildren().size(); i++) {
			Text t = (Text)logText.getChildren().get(i);
			t.setLayoutX(270);
			t.setFill(Color.WHITE);
			t.setFont(Font.font("微软雅黑",FontWeight.SEMI_BOLD, 40));
			t.setEffect(dropShadow);
			t.setWrappingWidth(780);
		}
	}
	
	private void setOnScroll() {
		this.setOnScroll(e -> {
			logText.setLayoutY(logText.getLayoutY() + e.getDeltaY() / 1.5);
			if (logText.getLayoutY() > 0)
				logText.setLayoutY(0);
			if (logText.getLayoutY() < -820)
				logText.setLayoutY(-820);
		});
	}
}
