package game.pane;

import game.Game;
import game.button.LogButton;
import game.button.AboutButton;
import game.button.BkButton;
import game.button.CloseButton;
import game.button.HowToPlayButton;
import game.button.SoundButton;
import game.pane.BackgroundPane.BkColor;
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

public class OptionPane extends Pane{
	public boolean status = true;
	private CloseButton closeButton = new CloseButton();
	private SoundButton btSound = new SoundButton();
	private LogButton btLog = new LogButton();
	private HowToPlayButton btHowToPlay = new HowToPlayButton();
	private BkButton bkButton = new BkButton();
	private AboutButton btAbout = new AboutButton();
	private Rectangle background1 = new Rectangle(0, 0, Game.width, Game.height * 0.25);
	private Rectangle background2 = new Rectangle(0, Game.height * 0.25, Game.width, Game.height);
	private Text optionTitle = new Text("…Ë÷√");
	
	public OptionPane(int bkColor, boolean soundSwitch) {
		initBackground();
		initButton();
		initText();
		
		this.setBtSound(soundSwitch);
		if (bkColor == 1) {
			bkButton.set(BkColor.blue);
		} else if (bkColor == 2) {
			bkButton.set(BkColor.green);
		}
		
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
		
		btHowToPlay.setLayoutX(Game.width / 2);
		btHowToPlay.setLayoutY(Game.height / 2 -120);
		this.getChildren().add(btHowToPlay);
		
		bkButton.setLayoutX(Game.width / 2);
		bkButton.setLayoutY(Game.height / 2 + 30);
		this.getChildren().add(bkButton);
		
		btAbout.setLayoutX(Game.width / 2);
		btAbout.setLayoutY(Game.height / 2 + 180);
		this.getChildren().add(btAbout);
		
		btSound.setLayoutX(Game.width / 2 - 130);
		btSound.setLayoutY(Game.height / 2 + 330);
		this.getChildren().add(btSound);
		
		btLog.setLayoutX(Game.width / 2 + 130);
		btLog.setLayoutY(Game.height / 2 + 330);
		this.getChildren().add(btLog);
	}
	
	private void initText() {
		optionTitle.setLayoutX(240);
		optionTitle.setLayoutY(130);
		optionTitle.setFill(Color.WHITE);
		optionTitle.setFont(Font.font("Œ¢»Ì—≈∫⁄",FontWeight.BOLD, 55));
		DropShadow dropShadow = new DropShadow(5, 3, 3, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		optionTitle.setEffect(dropShadow);
		this.getChildren().add(optionTitle);
	}
	
	public void setBtCloseOnMouseClicked(EventHandler<? super MouseEvent> e) {
		this.closeButton.setOnMouseClicked(e);
	}
	
	public void setBtHowToPlayOnMouseClicked(EventHandler<? super MouseEvent> e) {
		this.btHowToPlay.setOnMouseClicked(e);
	}
	
	public void setBtAboutOnMouseClicked(EventHandler<? super MouseEvent> e) {
		this.btAbout.setOnMouseClicked(e);
	}
	
	public void setBtLogOnMouseClicked(EventHandler<? super MouseEvent> e) {
		this.btLog.setOnMouseClicked(e);
	}
	
	public void setBtSoundOnMouseClicked(EventHandler<? super MouseEvent> e) {
		this.btSound.setOnMouseClicked(e);
	}
	
	public boolean getBtSoundStatus() {
		return btSound.getStatus();
	}
	
	public void setBtSound(boolean status) {
		this.btSound.set(status);
	}
	
	public void setBtBackgroundOnMouseClicked(EventHandler<? super MouseEvent> e) {
		this.bkButton.setOnMouseClicked(e);
	}
	
	public void setBtBk(BackgroundPane.BkColor bkColor) {
		this.bkButton.set(bkColor);
	}

}
