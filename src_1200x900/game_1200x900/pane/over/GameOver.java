package game_1200x900.pane.over;

import game_1200x900.Game;
import game_1200x900.pane.Display;
import game_1200x900.pane.background.MenuBackground;
import game_1200x900.pane.menu.Menu;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import util.animation.Fade;

public class GameOver {
	public static Pane pane = new Pane();
	public static boolean status = false;
	private static TryAgainButton btTryAgain = new TryAgainButton();
	private static Text endTitle = new Text("Game Over");
	private static Text scoreText = new Text();
	private static Text bestScoreText = new Text();
	
	public static void load() {
		pane.getChildren().add(new MenuBackground(Color.rgb(40, 40, 40, 0.6)));
		pane.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.SPACE) {
				if (status) {
					status = false;
					Fade.setNode(pane);
					Display.playResetAnimation();
					Display.setOnResetFinished(f -> {
						Game.toFront(Menu.pane);
						Menu.pane.requestFocus();
						Menu.status = true;
					});
				}
			}
		});
		initButton();
		initText();
	}
	
	private static void initButton() {
		btTryAgain.setLayoutX(Game.width / 2);
		btTryAgain.setLayoutY(Game.height / 1.3);
		btTryAgain.setOnMouseClicked(e -> {
			if (status) {
				status = false;
				Fade.setNode(pane);
				Display.playResetAnimation();
				Display.setOnResetFinished(f -> {
					Game.toFront(Menu.pane);
					Menu.pane.requestFocus();
					Menu.status = true;
				});
			}
		});
		pane.getChildren().add(btTryAgain);
	}
	
	private static void initText() {
		DropShadow dropShadow = new DropShadow(5, 3, 3, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		
		endTitle.setLayoutX((Game.width - 390)/ 2);
		endTitle.setLayoutY(Game.height / 5.5);
		endTitle.setFill(Color.WHITE);
		endTitle.setFont(Font.font("Gill Sans MT Condensed", FontWeight.BOLD, 120));
		endTitle.setEffect(dropShadow);
		
		scoreText.setLayoutX((Game.width - 40)/ 2);
		scoreText.setLayoutY(Game.height / 3.5);
		scoreText.setFill(Color.WHITE);
		scoreText.setFont(Font.font("Gill Sans MT Condensed",FontWeight.BOLD, 83));
		scoreText.setEffect(dropShadow);
		
		bestScoreText.setLayoutX((Game.width - 170)/ 2);
		bestScoreText.setLayoutY(Game.height / 2.4);
		bestScoreText.setFill(Color.hsb(0, 0.0, 1.0, 0.90));
		bestScoreText.setFont(Font.font("Gill Sans MT Condensed", FontWeight.BOLD, 65));
		bestScoreText.setEffect(dropShadow);
		
		pane.getChildren().addAll(scoreText, bestScoreText, endTitle);
	}
	
	public static void setScore(int score, int bestScore) {
		scoreText.setText(score + "");
		if (bestScore != 0) {
			bestScoreText.setText(String.format("BEST %3d", bestScore));
		}
	}

}
