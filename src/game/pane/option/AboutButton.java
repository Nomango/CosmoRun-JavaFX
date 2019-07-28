package game.pane.option;

import game.baseButton.HexagonButton;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class AboutButton extends HexagonButton{
	private Text text = new Text("ABOUT");
	
	public AboutButton() {
		super(200, 55);
		
		text.setLayoutX(-80);
		text.setLayoutY(20);
		text.setFill(Color.WHITE);
		text.setFont(Font.font("Î¢ÈíÑÅºÚ",FontWeight.BOLD, 45));
		DropShadow dropShadow = new DropShadow(5, 3, 3, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		text.setEffect(dropShadow);
		this.getChildren().add(text);
	}

}
