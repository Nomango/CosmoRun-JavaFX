package game_800x600.pane.howtoplay;

import game_800x600.baseButton.HexagonButton;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class HowToPlayButton extends HexagonButton{
	private Text text = new Text("HOW TO PLAY");
	
	public HowToPlayButton() {
		super.setSize(133, 36);
		
		text.setLayoutX(-180 / 2);
		text.setLayoutY(33 / 2);
		text.setFill(Color.WHITE);
		text.setFont(Font.font("Gill Sans MT Condensed", FontWeight.BOLD, 43));
		DropShadow dropShadow = new DropShadow(4, 2, 2, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		text.setEffect(dropShadow);
		this.getChildren().add(text);
	}

}
