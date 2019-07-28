package game.pane.option;

import game.baseButton.HexagonButton;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class LogButton extends HexagonButton{
	private Circle c = new Circle(25);
	private Circle c2 = new Circle(3.5);
	private Rectangle r = new Rectangle(-3, -3, 6, 20);
	
	public LogButton() {
		c2.setLayoutY(-10);
		
		c.setFill(Color.WHITE);
		c2.setFill(Color.rgb(0, 136, 215));
		r.setFill(Color.rgb(0, 136, 215));
		
		r.setEffect(new BoxBlur(2, 2, 1));
		c2.setEffect(new BoxBlur(2, 2, 1));
		
		DropShadow dropShadow = new DropShadow(5, 3, 3, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		c.setEffect(dropShadow);

		this.getChildren().addAll(c, c2, r);
	}

}
