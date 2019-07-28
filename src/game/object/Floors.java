package game.object;

import game.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;

public class Floors {
	public static Group group = new Group();
	public static Group floorsShadow = new Group();
	private static Floor hide;
	
	static {
		floorsShadow.translateXProperty().bind(group.translateXProperty());
		floorsShadow.translateYProperty().bind(group.translateYProperty());
	}
	
	public static void move(double dx, double dy) {
		group.setTranslateX(group.getTranslateX() + dx / 3.5);
		group.setTranslateY(group.getTranslateY() + dy / 3.5);
	}
	
	public static void clear() {
		group.getChildren().clear();
		floorsShadow.getChildren().clear();
	}
	
	public static Floor getHide() {
		return hide;
	}
	
	public static void resetHide() {
		hide = FloorManager.getNewFloor(get(size() - 1));
	}
	
	public static void showHide() {
		FloorManager.show(hide);
	}
	
	public static void setHide(Floor f) {
		hide = f;
	}
	
	public static boolean add(Floor f) {
		floorsShadow.getChildren().add(f.shadow);
		return group.getChildren().add(f);
	}
	
	public static Floor get(int i) {
		return (Floor)group.getChildren().get(i);
	}
	
	public static int indexOf(Floor f) {
		return group.getChildren().indexOf(f);
	}
	
	public static void remove(Floor f) {
		floorsShadow.getChildren().remove(f.shadow);
		group.getChildren().remove(f);
	}
	
	public static void remove(int index) {
		floorsShadow.getChildren().remove(index);
		group.getChildren().remove(index);
	}
	
	public static int size() {
		return group.getChildren().size();
	}
	
	public static boolean isOutOfFloors() {
		for (Node f: group.getChildren()) {
			if (((Floor)f).contains(
					Game.width / 2 - group.getTranslateX(), 
					Game.height / 2 - group.getTranslateY())) {
				return false;
			}
		}
		return true;
	}
	
	public static void setFloorColor() {
		for (Node f: group.getChildren()) {
			FloorManager.setColor((Floor)f, Game.bkMode);
		}
		FloorManager.setColor(hide, Game.bkMode);
	}
	
	public static void setOnHideAllFinished(EventHandler<ActionEvent> e) {
		FloorManager.hide.setOnFinished(e);
	}
	
	public static void hideAll() {
		for (Node f: group.getChildren()) {
			FloorManager.hide(((Floor)f));
		}
	}
	
	public static void setTranslateX(double x) {
		group.setTranslateX(x);
	}
	
	public static void setTranslateY(double y) {
		group.setTranslateY(y);
	}
	
	public static double getTranslateX() {
		return group.getTranslateX();
	}
	
	public static double getTranslateY() {
		return group.getTranslateY();
	}
	
	// ÅÐ¶Ï°å¿éÎ»ÖÃÊÇ·ñºÍÆäËû°å¿é³åÍ»
	public static boolean isCollisionWith(Floor floor) {
		for (int i = 0; i < size() - 1; i++) {
			Floor f = get(i);
			for (int j = 0; j < 16; j += 2) {
				if (f.contains(floor.getLayoutX() + floor.range[j], floor.getLayoutY() + floor.range[j + 1])) {
					return true;
				}
			}
		}
		return false;
	}

}
