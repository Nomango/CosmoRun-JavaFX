package game_800x600.pane.option;

import game_800x600.Game;
import game_800x600.baseButton.CloseButton;
import game_800x600.pane.Display;
import game_800x600.pane.background.Background;
import game_800x600.pane.background.MenuBackground;
import game_800x600.pane.howtoplay.HowToPlay;
import game_800x600.pane.howtoplay.HowToPlayButton;
import game_800x600.pane.menu.Menu;
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
		closeButton.setLayoutX(110 * 0.67);
		closeButton.setLayoutY(110 * 0.67);
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
		bkButton.setLayoutY(Game.height / 2 + 70);
		bkButton.setOnMouseClicked(e -> {
			Background.change();
			bkButton.setText(Game.bkMode);
			Display.changeColor();
		});
		pane.getChildren().add(bkButton);
		
		btSound.setLayoutX(Game.width / 2);
		btSound.setLayoutY(Game.height / 2 + 190);
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
		optionTitle.setLayoutX(160);
		optionTitle.setLayoutY(140 * 0.67);
		optionTitle.setFill(Color.hsb(0, 0.0, 1.0, 0.90));
		optionTitle.setFont(Font.font("Gill Sans MT Condensed", FontWeight.BOLD, 53));
		DropShadow dropShadow = new DropShadow(4, 2, 2, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		optionTitle.setEffect(dropShadow);
		pane.getChildren().add(optionTitle);
	}

}
