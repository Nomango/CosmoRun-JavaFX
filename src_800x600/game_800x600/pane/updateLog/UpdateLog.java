package game_800x600.pane.updateLog;

import game_800x600.Game;
import game_800x600.baseButton.CloseButton;
import game_800x600.pane.about.About;
import game_800x600.pane.background.MenuBackground;
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

public class UpdateLog extends Pane{
	public static Pane pane = new Pane();
	public static boolean status = false;
	private static CloseButton closeButton = new CloseButton();
	private static Text logTitle = new Text("Update Log");
	private static LogText logText = new LogText();
	
	public static void load() {
		pane.getChildren().add(new MenuBackground());
		initButton();
		initText();
	}

	private static void initButton() {
		closeButton.setLayoutX(110 * 0.67);
		closeButton.setLayoutY(110 * 0.67);
		closeButton.setOnMouseClicked(e -> {
			if (status) {
				status = false;
				Fade fade = new Fade(pane);
				fade.setOnFinished(f -> {
					Game.toFront(About.pane);
					About.status = true;
					logText.setTextY(0);
				});
			}
		});
		pane.getChildren().add(closeButton);
	}
	
	private static void initText() {
		logTitle.setLayoutX(240 * 0.67);
		logTitle.setLayoutY(140 * 0.67);
		logTitle.setFill(Color.hsb(0, 0.0, 1.0, 0.90));
		logTitle.setFont(Font.font("Gill Sans MT Condensed", FontWeight.BOLD, 53));
		DropShadow dropShadow = new DropShadow(4, 2, 2, Color.hsb(0, 0.0, 0.2, 0.3));
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
		
		Text logText1 = new Text(text.LogText.text1);
		Text logText2 = new Text(text.LogText.text2);
		Text logText3 = new Text(text.LogText.text3);
		Text logText4 = new Text(text.LogText.text4);
		Text logText5 = new Text(text.LogText.text5);
		Text logText6 = new Text(text.LogText.text6);
		Text logTextProblem = new Text(text.LogText.problemText);
		
		logText1.setLayoutY(50 * 0.67);
		logText2.setLayoutY(430 * 0.67);
		logText3.setLayoutY(750 * 0.67);
		logText4.setLayoutY(1020 * 0.67);
		logText5.setLayoutY(1400 * 0.67);
		logText6.setLayoutY(1880 * 0.67);
		logTextProblem.setLayoutY(2120 * 0.67);
		logText.getChildren().addAll(
				logText1, logText2, logText3, logText4, logText5, logText6, logTextProblem);
		
		DropShadow dropShadow = new DropShadow(4, 2, 2, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		for (int i = 0; i < logText.getChildren().size(); i++) {
			Text t = (Text)logText.getChildren().get(i);
			t.setLayoutX(270 * 0.67);
			t.setFill(Color.WHITE);
			t.setFont(Font.font("Microsoft YaHei",FontWeight.SEMI_BOLD, 26));
			t.setEffect(dropShadow);
			t.setWrappingWidth(520);
		}
	}
	
	private void setOnScroll() {
		this.setOnScroll(e -> {
			logText.setLayoutY(logText.getLayoutY() + e.getDeltaY() / 1.5);
			if (logText.getLayoutY() > 0)
				logText.setLayoutY(0);
			if (logText.getLayoutY() < -1800 * 0.67)
				logText.setLayoutY(-1800 * 0.67);
		});
	}
	
	public void setTextY(double value) {
		logText.setLayoutY(value);
	}
}
