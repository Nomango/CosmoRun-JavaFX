package game.animation;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;

public class Fade {
	private FadeTransition ft;
	
	public Fade(Node root) {
		ft = new FadeTransition(Duration.millis(300), root);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.play();
	}
	
	public void setOnFinished(EventHandler<ActionEvent> value) {
		ft.setOnFinished(value);
	}
	
	public static void setNode(Node root) {
		FadeTransition ft = new FadeTransition(Duration.millis(300), root);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.play();
	}

}
