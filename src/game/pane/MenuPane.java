package game.pane;

import game.Game;
import game.button.*;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MenuPane extends Pane{
	public boolean status = true;
	private ImageView menuTitle = new ImageView("game/sources/title.png");
	private OptionButton btOption = new OptionButton();
	private PlayButton btPlay = new PlayButton();
	private Text bestScoreText = new Text();
	private ScaleTransition st = new ScaleTransition(Duration.millis(1000), btPlay);
	
	public MenuPane(int bestScore) {
		init();
		this.setBestScore(bestScore);
		this.setCache(true);
		this.setCacheHint(CacheHint.SPEED);
	}
	
	private void init() {
		this.setCache(true);
		this.setCacheHint(CacheHint.QUALITY);
		
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
		
		btPlay.setLayoutX(Game.width / 2);
		btPlay.setLayoutY(Game.height / 1.3);
		
		btOption.setLayoutX(Game.width / 1.2 + 10);
		btOption.setLayoutY(Game.height / 2 - 20);
		
		this.getChildren().addAll(btOption, btPlay, bestScoreText, menuTitle);
		
		this.setCacheHint(CacheHint.SPEED);
		st.setByX(0.15f);
		st.setByY(0.15f);
		st.setAutoReverse(true);
		st.setCycleCount(-1);
		st.play();
	}
	
	public void setBtPlayOnMouseClicked(EventHandler<MouseEvent> e) {
		btPlay.setOnMouseClicked(e);
	}
	
	public void setBtOptionOnMouseClicked(EventHandler<MouseEvent> e) {
		btOption.setOnMouseClicked(e);
	}
	
	public void setBestScore(int bestScore) {
		if (bestScore != 0) {
			bestScoreText.setText("BEST " + bestScore);
		}
	}

}
