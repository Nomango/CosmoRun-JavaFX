package game.pane.menu;

import game.baseButton.HexagonButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PlayButton extends HexagonButton{
	private Text text = new Text("PLAY");
	
	public PlayButton() {
		super(170, 0);
		initProperty();
		initAnimation();
	}

	private void initProperty() {
		text.setFill(Color.WHITE);
		text.setLayoutX(-100 / 2);
		text.setLayoutY(35 / 2);
		text.setFont(Font.font("TimeRomes", FontWeight.BOLD, 40));
		this.getChildren().add(text);
		
		this.setInsideColor(null);
		this.setOutsideColor(Color.rgb(0, 141, 225, 0.4));
	}
	
	private void initAnimation() {
		this.setOnMouseEntered(e -> this.setOutsideColor(Color.rgb(0, 120, 215, 0.4)));
		this.setOnMouseExited(e -> this.setOutsideColor(Color.rgb(0, 161, 255, 0.4)));
		this.setOnMousePressed(e -> this.setOutsideColor(Color.rgb(0, 80, 175, 0.4)));
		this.setOnMouseReleased(e -> this.setOutsideColor(Color.rgb(0, 120, 215, 0.4)));
	}

}
