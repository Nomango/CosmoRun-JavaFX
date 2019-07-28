package game.pane;

import game.Game;
import game.button.CloseButton;
import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class AboutPane extends Pane{
	public boolean status = true;
	private CloseButton closeButton = new CloseButton();
	private Rectangle background1 = new Rectangle(0, 0, Game.width, Game.height * 0.25);
	private Rectangle background2 = new Rectangle(0, Game.height * 0.25, Game.width, Game.height);
	private Text aboutTitle = new Text("关于");
	private Text aboutText;
	
	public AboutPane() {
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
		aboutTitle.setLayoutX(240);
		aboutTitle.setLayoutY(130);
		aboutTitle.setFill(Color.hsb(0, 0.0, 1.0, 0.90));
		aboutTitle.setFont(Font.font("微软雅黑",FontWeight.BOLD, 55));
		DropShadow dropShadow = new DropShadow(5, 3, 3, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		aboutTitle.setEffect(dropShadow);
		this.getChildren().add(aboutTitle);
		
		aboutText = new Text("     本游戏用JavaFX技术，仿照 Windows 应用商店中的同名游戏制作，仅供编程爱好者学习使用。\n\n\n" + 
				"                 Version: 1.3\n" + 
				"            Made by: 宫泽先生\n" + 
				"E-mail: gzip_liu.cd@foxmail.com\n" + 
				"                   2016.6.11");
		aboutText.setLayoutX(300);
		aboutText.setLayoutY(350);
		aboutText.setFill(Color.WHITE);
		aboutText.setFont(Font.font("微软雅黑",FontWeight.SEMI_BOLD, 40));
		aboutText.setEffect(dropShadow);
		aboutText.setWrappingWidth(620);
		this.getChildren().add(aboutText);
	}
	
	public void setBtCloseOnMouseClicked(EventHandler<? super MouseEvent> e) {
		this.closeButton.setOnMouseClicked(e);
	}

}
