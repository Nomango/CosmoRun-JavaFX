package util.animation;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;

public class Show {
	private FadeTransition ft;
	
	public static void setNode(Node root) {
		root.setOpacity(0.0);
		FadeTransition ft = new FadeTransition(Duration.millis(300), root);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
	}
	
	public Show(Node root) {
		root.setOpacity(0.0);
		ft = new FadeTransition(Duration.millis(300), root);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
	}
	
	public void setOnFinished(EventHandler<ActionEvent> value) {
		ft.setOnFinished(value);
	}

}
