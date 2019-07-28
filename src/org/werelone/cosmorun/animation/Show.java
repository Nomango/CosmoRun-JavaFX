package org.werelone.cosmorun.animation;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;

public class Show {
	
	public static void setNode(Node root) {
		root.setOpacity(0.0);
		FadeTransition ft = new FadeTransition(Duration.millis(400), root);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
	}
	
	public static void setNode(Node root, EventHandler<ActionEvent> value) {
		root.setOpacity(0.0);
		FadeTransition ft = new FadeTransition(Duration.millis(400), root);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.setOnFinished(value);
		ft.play();
	}
	
	public static void setNode(Node root, EventHandler<ActionEvent> value, double ms) {
		root.setOpacity(0.0);
		FadeTransition ft = new FadeTransition(Duration.millis(ms), root);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.setOnFinished(value);
		ft.play();
	}

}
