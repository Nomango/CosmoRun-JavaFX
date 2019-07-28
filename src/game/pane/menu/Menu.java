package game.pane.menu;

import game.Game;
import game.animation.Fade;
import game.pane.Display;
import game.pane.option.Option;
import javafx.animation.ScaleTransition;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Menu {
	public static Pane pane = new Pane();
	public static boolean status = false;
	private static ImageView menuTitle = new ImageView("game/sources/title.png");
	private static OptionButton btOption = new OptionButton();
	private static PlayButton btPlay = new PlayButton();
	private static Text bestScoreText = new Text();
	
	public static void load() {
		pane.setMinWidth(Game.width);
		pane.setMinHeight(Game.height);
		pane.setOnMousePressed(e -> {
			if (Display.status)
				Display.changeDirect();
		});
		pane.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.SPACE) {
				if (status) {
					status = false;
					Fade fade = new Fade(pane);
					fade.setOnFinished(f -> {
						Display.play();
						pane.setOpacity(0.0);
					});
				} else if (Display.status) {
					Display.changeDirect();
				}
			}
		});
		initText();
		initButton();
	}
	
	private static void initText() {
		menuTitle.setLayoutX((Game.width - 556) / 2);
		menuTitle.setLayoutY(120);
		DropShadow dropShadow = new DropShadow(5, 5, 5, Color.hsb(0, 0.0, 0.2, 0.8));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		menuTitle.setEffect(dropShadow);
		
		bestScoreText.setLayoutX((Game.width - 170)/ 2);
		bestScoreText.setLayoutY(Game.height / 2.4);
		bestScoreText.setFill(Color.hsb(0, 0.0, 1.0, 0.90));
		bestScoreText.setFont(Font.font("Î¢ÈíÑÅºÚ",FontWeight.BOLD, 45));
		bestScoreText.setEffect(dropShadow);
		
		pane.getChildren().addAll(bestScoreText, menuTitle);
	}
	
	private static void initButton() {
		btPlay.setLayoutX(Game.width / 2);
		btPlay.setLayoutY(Game.height / 1.3);
		btPlay.setOnMouseClicked(e -> {
			if (status) {
				status = false;
				Fade fade = new Fade(pane);
				fade.setOnFinished(f -> {
					Display.play();
					pane.setOpacity(0.0);
				});
			}
		});
		
		btOption.setLayoutX(Game.width / 1.2 + 10);
		btOption.setLayoutY(Game.height / 2 - 20);
		btOption.setOnMouseClicked(e -> {
			if (status) {
				status = false;
				Fade fade = new Fade(pane);
				fade.setOnFinished(f -> {
					Game.showPane(Option.pane);
					Option.status = true;
				});
			}
		});
		pane.getChildren().addAll(btOption, btPlay);
		
		ScaleTransition st = new ScaleTransition(Duration.millis(1000), btPlay);
		st.setByX(0.15f);
		st.setByY(0.15f);
		st.setAutoReverse(true);
		st.setCycleCount(-1);
		st.play();
	}
	
	public static void setBestScore(int bestScore) {
		if (bestScore != 0) {
			bestScoreText.setText("BEST " + bestScore);
		}
	}

}
