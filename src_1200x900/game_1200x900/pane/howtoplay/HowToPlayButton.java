package game_1200x900.pane.howtoplay;

import game_1200x900.baseButton.HexagonButton;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class HowToPlayButton extends HexagonButton{
	private Text text = new Text("HOW TO PLAY");
	
	public HowToPlayButton() {
		super.setSize(200, 55);
		
		text.setLayoutX(-270 / 2);
		text.setLayoutY(50 / 2);
		text.setFill(Color.WHITE);
		text.setFont(Font.font("Gill Sans MT Condensed", FontWeight.BOLD, 65));
		DropShadow dropShadow = new DropShadow(5, 3, 3, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		text.setEffect(dropShadow);
		this.getChildren().add(text);
	}

}
