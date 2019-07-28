package org.werelone.cosmorun.animation;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;

public class Fade {
	
	public static void setNode(Node root) {
		FadeTransition ft = new FadeTransition(Duration.millis(300), root);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.play();
	}
	
	public static void setNode(Node root, EventHandler<ActionEvent> value) {
		FadeTransition ft = new FadeTransition(Duration.millis(300), root);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.setOnFinished(value);
		ft.play();
	}
	
	public static void setNode(Node root, EventHandler<ActionEvent> value, double ms) {
		FadeTransition ft = new FadeTransition(Duration.millis(ms), root);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.setOnFinished(value);
		ft.play();
	}

}
