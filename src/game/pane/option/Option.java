package game.pane.option;

import game.Game;
import game.animation.Fade;
import game.baseButton.CloseButton;
import game.music.Music;
import game.pane.background.Background;
import game.pane.background.MenuBackground;
import game.pane.Display;
import game.pane.about.About;
import game.pane.howtoplay.HowToPlay;
import game.pane.howtoplay.HowToPlayButton;
import game.pane.log.Log;
import game.pane.menu.Menu;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Option {
	public static Pane pane = new Pane();
	public static boolean status = false;
	private static CloseButton closeButton = new CloseButton();
	private static SoundButton btSound = new SoundButton(Game.soundSwitch);
	private static LogButton btLog = new LogButton();
	private static HowToPlayButton btHowToPlay = new HowToPlayButton();
	private static BkButton bkButton = new BkButton();
	private static AboutButton btAbout = new AboutButton();
	private static Text optionTitle = new Text("ÉèÖÃ");
	
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
					Game.showPane(Menu.pane);
					Menu.pane.requestFocus();
					Menu.status = true;
				});
			}
		});
		pane.getChildren().add(closeButton);
		
		btHowToPlay.setLayoutX(Game.width / 2);
		btHowToPlay.setLayoutY(Game.height / 2 -120);
		btHowToPlay.setOnMouseClicked(e -> {
			if (status) {
				status = false;
				Fade fade = new Fade(pane);
				fade.setOnFinished(f -> {
					Game.showPane(HowToPlay.pane);
					HowToPlay.status = true;
				});
				HowToPlay.play();
			}
		});
		pane.getChildren().add(btHowToPlay);
		
		bkButton.setLayoutX(Game.width / 2);
		bkButton.setLayoutY(Game.height / 2 + 30);
		bkButton.setOnMouseClicked(e -> {
			Background.change();
			bkButton.setText(Game.bkMode);
			Display.changeColor();
		});
		pane.getChildren().add(bkButton);
		
		btAbout.setLayoutX(Game.width / 2);
		btAbout.setLayoutY(Game.height / 2 + 180);
		btAbout.setOnMouseClicked(e -> {
			if (status) {
				status = false;
				Fade fade = new Fade(pane);
				fade.setOnFinished(f -> {
					Game.showPane(About.pane);
					About.status = true;
				});
			}
		});
		pane.getChildren().add(btAbout);
		
		btSound.setLayoutX(Game.width / 2 - 130);
		btSound.setLayoutY(Game.height / 2 + 330);
		btSound.setOnMouseClicked(e -> {
			if (Game.soundSwitch) {
				Game.soundSwitch = false;
				btSound.set(false);
				Music.stopBkMusic();
			} else {
				Game.soundSwitch = true;
				btSound.set(true);
				Music.playBkMusic();
			}
		});
		pane.getChildren().add(btSound);
		
		btLog.setLayoutX(Game.width / 2 + 130);
		btLog.setLayoutY(Game.height / 2 + 330);
		btLog.setOnMouseClicked(e -> {
			if (status) {
				status = false;
				Fade fade = new Fade(pane);
				fade.setOnFinished(f -> {
					Game.showPane(Log.pane);
					Log.status = true;
				});
			}
		});
		pane.getChildren().add(btLog);
	}
	
	private static void initText() {
		optionTitle.setLayoutX(240);
		optionTitle.setLayoutY(130);
		optionTitle.setFill(Color.WHITE);
		optionTitle.setFont(Font.font("Î¢ÈíÑÅºÚ",FontWeight.BOLD, 55));
		DropShadow dropShadow = new DropShadow(5, 3, 3, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		optionTitle.setEffect(dropShadow);
		pane.getChildren().add(optionTitle);
	}

}
