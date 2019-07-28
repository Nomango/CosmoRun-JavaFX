package game.button;

import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TryAgainButton extends HexagonButton{
	private Text text = new Text("TRY AGAIN");
	private Arc arc = new Arc(140, 0, 20, 20, 0, 270);
	private Rectangle arrow1 = new Rectangle(161, -12, 6.5, 20);
	private Rectangle arrow2 = new Rectangle(150, -10, 6.5, 20);
	
	public TryAgainButton() {
		super(180, 65);
		initProperty();
		
	}

	private void initProperty() {
		text.setFill(Color.WHITE);
		text.setLayoutX(-242 / 2 - 30);
		text.setLayoutY(35 / 2);
		text.setFont(Font.font("TimesRomes", FontWeight.BOLD, 43));
		DropShadow dropShadow = new DropShadow(5, 3, 3, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		text.setEffect(dropShadow);
		this.getChildren().add(text);
		
		arc.setFill(null);
		arc.setStroke(Color.WHITE);
		arc.setStrokeWidth(6.5);
		arc.setType(ArcType.OPEN);
		arc.setEffect(dropShadow);
		this.getChildren().add(arc);
		
		arrow1.setRotate(30);
		arrow1.setFill(Color.WHITE);
		arrow1.setEffect(dropShadow);
		
		arrow2.setRotate(-60);
		arrow2.setFill(Color.WHITE);
		arrow2.setEffect(dropShadow);
		this.getChildren().addAll(arrow2, arrow1);
	}
}
