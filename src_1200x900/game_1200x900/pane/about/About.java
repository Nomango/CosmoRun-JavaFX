package game_1200x900.pane.about;

import game_1200x900.Game;
import game_1200x900.baseButton.CloseButton;
import game_1200x900.pane.background.MenuBackground;
import game_1200x900.pane.menu.Menu;
import game_1200x900.pane.updateLog.LogButton;
import game_1200x900.pane.updateLog.UpdateLog;
import javafx.scene.Group;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import util.animation.Fade;

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
		
		Text aboutText1 = new Text(text.AboutText.text1);
		aboutText1.setLayoutX(240);
		aboutText1.setLayoutY(50);
		DropShadow dropShadow = new DropShadow(5, 3, 3, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		aboutText1.setFill(Color.hsb(0, 0, 0.8, 0.8));
		aboutText1.setFont(Font.font("Microsoft YaHei", FontWeight.SEMI_BOLD, 32));
		aboutText1.setEffect(dropShadow);
		aboutText1.setWrappingWidth(750);
		
		Text aboutText2 = new Text(text.AboutText.text2);
		
		aboutText2.setLayoutX(220);
		aboutText2.setLayoutY(200);
		
		aboutText2.setFill(Color.WHITE);
		aboutText2.setFont(Font.font("Microsoft YaHei", FontWeight.SEMI_BOLD, 38));
		aboutText2.setEffect(dropShadow);
		aboutText2.setWrappingWidth(800);
		
		aboutText.getChildren().addAll(aboutText1, aboutText2);
	}
	
	private void setOnScroll() {
		this.setOnScroll(e -> {
			aboutText.setLayoutY(aboutText.getLayoutY() + e.getDeltaY() / 1.5);
			if (aboutText.getLayoutY() > 0)
				aboutText.setLayoutY(0);
			if (aboutText.getLayoutY() < -480)
				aboutText.setLayoutY(-480);
		});
	}
	
	public void setTextY(double value) {
		aboutText.setLayoutY(value);
	}
}