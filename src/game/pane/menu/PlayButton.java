package game.pane.menu;

import game.baseButton.HexagonButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PlayButton extends HexagonButton{
	private Text text = new Text("PLAY");
	
	public PlayButton() {
		super(140, 0);
		initProperty();
		initAnimation();
	}

	private void initProperty() {
		text.setFill(Color.WHITE);
		text.setLayoutX(-90 / 2);
		text.setLayoutY(50 / 2);
		text.setFont(Font.font("Gill Sans MT Condensed", FontWeight.BOLD, 65));
		this.getChildren().add(text);
		
		this.setInsideColor(null);
		this.setOutsideColor(Color.rgb(0, 141, 225, 0.3));
	}
	
	private void initAnimation() {
		this.setOnMouseEntered(e -> this.setOutsideColor(Color.rgb(0, 120, 215, 0.3)));
		this.setOnMouseExited(e -> this.setOutsideColor(Color.rgb(0, 161, 255, 0.3)));
		this.setOnMousePressed(e -> this.setOutsideColor(Color.rgb(0, 80, 175, 0.3)));
		this.setOnMouseReleased(e -> this.setOutsideColor(Color.rgb(0, 120, 215, 0.3)));
	}

}
