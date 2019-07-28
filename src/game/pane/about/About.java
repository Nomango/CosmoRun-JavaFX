package game.pane.about;

import game.Game;
import game.animation.Fade;
import game.baseButton.CloseButton;
import game.pane.background.MenuBackground;
import game.pane.menu.Menu;
import game.pane.updateLog.LogButton;
import game.pane.updateLog.UpdateLog;
import javafx.scene.Group;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class About {
	public static Pane pane = new Pane();
	public static boolean status = false;
	private static CloseButton closeButton = new CloseButton();
	private static LogButton btLog = new LogButton();
	private static Text aboutTitle = new Text("ABOUT");
	private static AboutText aboutText = new AboutText();
	
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
					Game.toFront(Menu.pane);
					Menu.pane.requestFocus();
					Menu.status = true;
					aboutText.setTextY(0);
				});
			}
		});
		pane.getChildren().add(closeButton);
		
		btLog.setLayoutX(Game.width - 200);
		btLog.setLayoutY(110);
		btLog.setOnMouseClicked(e -> {
			if (status) {
				status = false;
				Fade fade = new Fade(pane);
				fade.setOnFinished(f -> {
					Game.toFront(UpdateLog.pane);
					UpdateLog.status = true;
					aboutText.setTextY(0);
				});
			}
		});
		pane.getChildren().add(btLog);
	}
	
	private static void initText() {
		aboutTitle.setLayoutX(240);
		aboutTitle.setLayoutY(140);
		aboutTitle.setFill(Color.hsb(0, 0.0, 1.0, 0.90));
		aboutTitle.setFont(Font.font("Gill Sans MT Condensed", FontWeight.BOLD, 80));
		DropShadow dropShadow = new DropShadow(5, 3, 3, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		aboutTitle.setEffect(dropShadow);
		pane.getChildren().add(aboutTitle);
		
		
		aboutText.setLayoutY(Game.height * 0.35);
		pane.getChildren().add(aboutText);
	}

}

class AboutText extends Pane {
	private Group aboutText = new Group();
	
	public AboutText() {
		this.setClip(new Rectangle(Game.width, Game.height * 0.55));
		
		this.getChildren().add(aboutText);
		this.setOnScroll();
		
		Text aboutText1 = new Text("    声明：本游戏用JavaFX技术，仿照 Windows 应用商店中的同名游戏制作，仅供编程爱好者学习使用。\n\n");
		aboutText1.setLayoutX(240);
		aboutText1.setLayoutY(50);
		DropShadow dropShadow = new DropShadow(5, 3, 3, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		aboutText1.setFill(Color.hsb(0, 0, 0.8, 0.8));
		aboutText1.setFont(Font.font("微软雅黑",FontWeight.SEMI_BOLD, 32));
		aboutText1.setEffect(dropShadow);
		aboutText1.setWrappingWidth(750);
		
		Text aboutText2 = new Text(
				"       CosmoRun从今天开始停止更新了。从6月1号做出DEMO版到现在，基本上每天都是一有时间就打代码，还经常熬夜通宵..." + 
				"再加上网上JavaFX的资料太少，很多想实现的东西只能自己摸索，好累(눈_눈)\n\n" + 
				"       真的真的很喜欢这个游戏，C++版我也做了一个，想要的可以通过下面的方式联系我。\n\n" + 
				"       马上就要出高考成绩了，祝高中生高考成绩UPUPUP！我的考试过过过！\n\n" + 
				"                     Made by: 宫泽先生\n" + 
				"                       QQ: 569629550\n" + 
				"        E-mail: gzip_liu.cd@foxmail.com\n" + 
				"                      v1.5    2016.6.23");
		
		aboutText2.setLayoutX(220);
		aboutText2.setLayoutY(200);
		
		aboutText2.setFill(Color.WHITE);
		aboutText2.setFont(Font.font("微软雅黑",FontWeight.SEMI_BOLD, 38));
		aboutText2.setEffect(dropShadow);
		aboutText2.setWrappingWidth(800);
		
		aboutText.getChildren().addAll(aboutText1, aboutText2);
	}
	
	private void setOnScroll() {
		this.setOnScroll(e -> {
			aboutText.setLayoutY(aboutText.getLayoutY() + e.getDeltaY() / 1.5);
			if (aboutText.getLayoutY() > 0)
				aboutText.setLayoutY(0);
			if (aboutText.getLayoutY() < -450)
				aboutText.setLayoutY(-450);
		});
	}
	
	public void setTextY(double value) {
		aboutText.setLayoutY(value);
	}
}