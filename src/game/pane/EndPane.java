package game.pane;

import game.Game;
import game.button.TryAgainButton;
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

public class EndPane extends Pane{
	public boolean status = true;
	private TryAgainButton btTryAgain = new TryAgainButton();
	private Rectangle background = new Rectangle(0, 0, Game.width, Game.height);
	private Text endTitle = new Text("Game Over");
	private Text scoreText = new Text();
	private Text bestScoreText = new Text();
	
	public EndPane() {
		initBackground();
		initButton();
		initText();
		
		this.setCache(true);
		this.setCacheHint(CacheHint.SPEED);
	}
	
	private void initBackground() {
		background.setFill(Color.rgb(40, 40, 40, 0.6));
		this.getChildren().add(background);
	}
	
	private void initButton() {
		btTryAgain.setLayoutX(Game.width / 2);
		btTryAgain.setLayoutY(Game.height / 1.3);
		this.getChildren().add(btTryAgain);
	}
	
	private void initText() {
		DropShadow dropShadow = new DropShadow(5, 3, 3, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		
		endTitle.setLayoutX((Game.width - 500)/ 2);
		endTitle.setLayoutY(Game.height / 5.5);
		endTitle.setFill(Color.WHITE);
		endTitle.setFont(Font.font("Î¢ÈíÑÅºÚ",FontWeight.BOLD, 90));
		endTitle.setEffect(dropShadow);
		
		scoreText.setLayoutX((Game.width - 40)/ 2);
		scoreText.setLayoutY(Game.height / 3.5);
		scoreText.setFill(Color.WHITE);
		scoreText.setFont(Font.font("Î¢ÈíÑÅºÚ",FontWeight.BOLD, 60));
		scoreText.setEffect(dropShadow);
		
		bestScoreText.setLayoutX((Game.width - 170)/ 2);
		bestScoreText.setLayoutY(Game.height / 2.4);
		bestScoreText.setFill(Color.hsb(0, 0.0, 1.0, 0.90));
		bestScoreText.setFont(Font.font("Î¢ÈíÑÅºÚ",FontWeight.BOLD, 45));
		bestScoreText.setEffect(dropShadow);
		
		this.getChildren().addAll(scoreText, bestScoreText, endTitle);
	}
	
	public void setScore(int score, int bestScore) {
		scoreText.setText(score + "");
		if (bestScore != 0) {
			bestScoreText.setText("BEST " + bestScore);
		}
	}
	
	public void setBtTryAgainOnMouseClicked(EventHandler<? super MouseEvent> e) {
		this.btTryAgain.setOnMouseClicked(e);
	}

}
