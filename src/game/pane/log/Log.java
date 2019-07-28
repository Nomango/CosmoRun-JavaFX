package game.pane.log;

import game.Game;
import game.animation.Fade;
import game.baseButton.CloseButton;
import game.pane.background.MenuBackground;
import game.pane.option.Option;
import javafx.scene.Group;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Log extends Pane{
	public static Pane pane = new Pane();
	public static boolean status = false;
	private static CloseButton closeButton = new CloseButton();
	private static Text logTitle = new Text("更新日志");
	private static LogText logText = new LogText();
	
	public static void load() {
		pane.getChildren().add(new MenuBackground());
		initButton();
		initText();
	}

	private static void initButton() {
		closeButton.setLayoutX(110);
		closeButton.setLayoutY(110);
		closeButton.setOnMouseClicked(e -> {
			if (status) {
				status = false;
				Fade fade = new Fade(pane);
				fade.setOnFinished(f -> {
					Game.showPane(Option.pane);
					Option.status = true;
					logText.setTextY(0);
				});
			}
		});
		pane.getChildren().add(closeButton);
	}
	
	private static void initText() {
		logTitle.setLayoutX(240);
		logTitle.setLayoutY(130);
		logTitle.setFill(Color.hsb(0, 0.0, 1.0, 0.90));
		logTitle.setFont(Font.font("微软雅黑",FontWeight.BOLD, 55));
		DropShadow dropShadow = new DropShadow(5, 3, 3, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		logTitle.setEffect(dropShadow);
		pane.getChildren().add(logTitle);
		
		logText.setLayoutY(Game.height * 0.35);
		pane.getChildren().add(logText);
	}

}

class LogText extends Pane {
	private Group logText = new Group();
	
	public LogText() {
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
		Text logText4 = new Text(
				"v1.4  2016.6.18更新\n" + 
				"     修复了某些情况下开始游戏后背景上有主菜单的残影的BUG\n" + 
				"     优化了内存占用\n" + 
				"     增加了板块阴影\n" + 
				"     增加了新的背景颜色\n");
		Text logTextProblem = new Text(
				"已知的问题:\n" + 
				"     内存和CPU占用依然很大\n" + 
				"     小几率出现新建板块线程崩溃导致游戏卡死\n" + 
				"     小几率出现相同类型的四个板块连在一起\n");
		
		logText1.setLayoutY(50);
		logText2.setLayoutY(430);
		logText3.setLayoutY(750);
		logText4.setLayoutY(1020);
		logTextProblem.setLayoutY(1400);
		logText.getChildren().addAll(logText1, logText2, logText3, logText4, logTextProblem);
		
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
			if (logText.getLayoutY() < -1260)
				logText.setLayoutY(-1260);
		});
	}
	
	public void setTextY(double value) {
		logText.setLayoutY(value);
	}
}
