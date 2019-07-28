package game_1200x900.pane.option;

import game_1200x900.Game;
import game_1200x900.baseButton.CloseButton;
import game_1200x900.pane.Display;
import game_1200x900.pane.background.Background;
import game_1200x900.pane.background.MenuBackground;
import game_1200x900.pane.howtoplay.HowToPlay;
import game_1200x900.pane.howtoplay.HowToPlayButton;
import game_1200x900.pane.menu.Menu;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import util.Music;
import util.animation.Fade;

public class Option {
	public static Pane pane = new Pane();
	public static boolean status = false;
	private static CloseButton closeButton = new CloseButton();
	private static SoundButton btSound = new SoundButton(Game.soundSwitch);
	private static HowToPlayButton btHowToPlay = new HowToPlayButton();
	private static BkButton bkButton = new BkButton();
	private static Text optionTitle = new Text("OPTION");
	
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
				});
			}
		});
		pane.getChildren().add(closeButton);
		
		btHowToPlay.setLayoutX(Game.width / 2);
		btHowToPlay.setLayoutY(Game.height / 2 -50);
		btHowToPlay.setOnMouseClicked(e -> {
			if (status) {
				status = false;
				Fade fade = new Fade(pane);
				fade.setOnFinished(f -> {
					Game.toFront(HowToPlay.pane);
					HowToPlay.status = true;
				});
				HowToPlay.play();
			}
		});
		pane.getChildren().add(btHowToPlay);
		
		bkButton.setLayoutX(Game.width / 2);
		bkButton.setLayoutY(Game.height / 2 + 125);
		bkButton.setOnMouseClicked(e -> {
			Background.change();
			bkButton.setText(Game.bkMode);
			Display.changeColor();
		});
		pane.getChildren().add(bkButton);
		
		btSound.setLayoutX(Game.width / 2);
		btSound.setLayoutY(Game.height / 2 + 300);
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
	}
	
	private static void initText() {
		optionTitle.setLayoutX(240);
		optionTitle.setLayoutY(140);
		optionTitle.setFill(Color.hsb(0, 0.0, 1.0, 0.90));
		optionTitle.setFont(Font.font("Gill Sans MT Condensed", FontWeight.BOLD, 80));
		DropShadow dropShadow = new DropShadow(5, 3, 3, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		optionTitle.setEffect(dropShadow);
		pane.getChildren().add(optionTitle);
	}

}
