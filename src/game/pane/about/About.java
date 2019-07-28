package game.pane.about;

import game.Game;
import game.animation.Fade;
import game.baseButton.CloseButton;
import game.pane.background.MenuBackground;
import game.pane.option.Option;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class About {
	public static Pane pane = new Pane();
	public static boolean status = false;
	private static CloseButton closeButton = new CloseButton();
	private static Text aboutTitle = new Text("关于");
	private static Text aboutText;
	
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
				});
			}
		});
		pane.getChildren().add(closeButton);
	}
	
	private static void initText() {
		aboutTitle.setLayoutX(240);
		aboutTitle.setLayoutY(130);
		aboutTitle.setFill(Color.hsb(0, 0.0, 1.0, 0.90));
		aboutTitle.setFont(Font.font("微软雅黑",FontWeight.BOLD, 55));
		DropShadow dropShadow = new DropShadow(5, 3, 3, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		aboutTitle.setEffect(dropShadow);
		pane.getChildren().add(aboutTitle);
		
		aboutText = new Text("     本游戏用JavaFX技术，仿照 Windows 应用商店中的同名游戏制作，仅供编程爱好者学习使用。\n\n\n" + 
				"                 Version: 1.4\n" + 
				"            Made by: 宫泽先生\n" + 
				"E-mail: gzip_liu.cd@foxmail.com\n" + 
				"                   2016.6.18");
		aboutText.setLayoutX(300);
		aboutText.setLayoutY(350);
		aboutText.setFill(Color.WHITE);
		aboutText.setFont(Font.font("微软雅黑",FontWeight.SEMI_BOLD, 40));
		aboutText.setEffect(dropShadow);
		aboutText.setWrappingWidth(620);
		pane.getChildren().add(aboutText);
	}

}
