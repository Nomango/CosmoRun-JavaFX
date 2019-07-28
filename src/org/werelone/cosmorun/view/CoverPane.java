package org.werelone.cosmorun.view;

import org.werelone.cosmorun.util.GameData;

import javafx.scene.layout.Pane;

public class CoverPane extends Pane {
	private static CoverPane pane;
	
	public static CoverPane create() {
		pane = new CoverPane();
		pane.setWidth(GameData.getWidth());
		pane.setHeight(GameData.getHeight());
		return pane;
	}
	
	public static CoverPane getInstance() {
		return pane;
	}

}
