package game_800x600.pane.menu;

import game_800x600.Game;
import game_800x600.pane.Display;
import game_800x600.pane.about.About;
import game_800x600.pane.about.AboutButton;
import game_800x600.pane.option.Option;
import game_800x600.pane.option.OptionButton;
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
import util.animation.Fade;

public class Menu {
	public static Pane pane = new Pane();
	public static boolean status = false;
	private static ImageView menuTitle = new ImageView("resources/title.png");
	private static OptionButton btOption = new OptionButton();
	private static AboutButton btAbout = new AboutButton();
	private static PlayButton btPlay = new PlayButton();
	private static Text bestScoreText = new Text();
	
	public static void load() {
		initPane();
		initText();
		initButton();
	}
	
	private static void initPane() {
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
		setBestScore(Game.getBestScore());
	}
	
	private static void initText() {
		menuTitle.setScaleX(0.66);
		menuTitle.setScaleY(0.66);
		menuTitle.setLayoutX((Game.width - 556) / 2);
		menuTitle.setLayoutY(80);
		DropShadow dropShadow = new DropShadow(4, 4, 4, Color.hsb(0, 0.0, 0.2, 0.8));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		menuTitle.setEffect(dropShadow);
		
		bestScoreText.setLayoutX((Game.width - 170 * 0.67)/ 2);
		bestScoreText.setLayoutY(Game.height / 2.4);
		bestScoreText.setFill(Color.hsb(0, 0.0, 1.0, 0.90));
		bestScoreText.setFont(Font.font("Gill Sans MT Condensed", FontWeight.BOLD, 43));
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
				fade.setOnFinished(f -> Display.play());
			}
		});
		ScaleTransition st = new ScaleTransition(Duration.millis(1000), btPlay);
		st.setByX(0.15f);
		st.setByY(0.15f);
		st.setAutoReverse(true);
		st.setCycleCount(-1);
		st.play();
		
		btOption.setLayoutX(Game.width / 1.2 + 7);
		btOption.setLayoutY(Game.height / 2 - 20);
		btOption.setOnMouseClicked(e -> {
			if (status) {
				status = false;
				Fade fade = new Fade(pane);
				fade.setOnFinished(f -> {
					Game.toFront(Option.pane);
					Option.status = true;
				});
			}
		});
		pane.getChildren().addAll(btOption, btPlay);
		
		btAbout.setLayoutX(Game.width / 1.2 + 7);
		btAbout.setLayoutY(Game.height / 2 + 100);
		btAbout.setOnMouseClicked(e -> {
			if (status) {
				status = false;
				Fade fade = new Fade(pane);
				fade.setOnFinished(f -> {
					Game.toFront(About.pane);
					About.status = true;
				});
			}
		});
		pane.getChildren().add(btAbout);
	}
	
	public static void setBestScore(int bestScore) {
		if (bestScore != 0) {
			bestScoreText.setText(String.format("BEST %3d", bestScore));
		}
	}

}
