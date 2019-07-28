package game_800x600.pane.about;

import game_800x600.baseButton.HexagonButton;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class AboutButton extends HexagonButton{
	private Circle c = new Circle(16.6);
	private Circle c2 = new Circle(2.3);
	private Rectangle r = new Rectangle(-2, -2, 4, 13.3);
	
	public AboutButton() {
		c2.setLayoutY(-6.67);
		
		c.setFill(Color.WHITE);
		c2.setFill(Color.rgb(0, 136, 215));
		r.setFill(Color.rgb(0, 136, 215));
		
		r.setEffect(new BoxBlur(2, 2, 1));
		c2.setEffect(new BoxBlur(2, 2, 1));
		
		DropShadow dropShadow = new DropShadow(4, 2, 2, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		c.setEffect(dropShadow);

		this.getChildren().addAll(c, c2, r);
	}

}
