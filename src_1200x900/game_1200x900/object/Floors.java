package game_1200x900.object;

import java.util.Vector;

import game_1200x900.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;

public class Floors {
	public static Group group = new Group();
	public static Vector<Floor> hideFloors = new Vector<>();
	public static Group floorsShadow = new Group();
	public static double moveX = 0;
	public static double moveY = 0;
	
	static {
		hideFloors.setSize(10);
	}
	
	public static void move(double dx, double dy) {
		for (Node f: group.getChildren()) {
			((Floor)f).setCenter(((Floor)f).getX() + dx, ((Floor)f).getY() + dy);
		}
		for (Floor f: hideFloors) {
			f.setCenter(f.getX() + dx, f.getY() + dy);
		}
		
		// 记录移动值，这个值用于重新开始游戏时的动画
		moveX += dx;
		moveY += dy;
		if (moveX > 1200) moveX = 1200;
		if (moveX < -1200) moveX = -1200;
		if (moveY > 900) moveY = 900;
		if (moveY < -900) moveY = -900;
	}
	
	public static void setHeadFloor(Floor f) {
		hideFloors.add(f);
		for (int i = 0; i < 9; i++) {
			Floors.addHideFloor();
		}
	}
	
	public static void clear() {
		group.getChildren().clear();
		hideFloors.clear();
		floorsShadow.getChildren().clear();
	}
	
	public static void addHideFloor() {
		Floor f = FloorManager.getNewFloor(hideFloors.get(hideFloors.size() - 1));
		if (hideFloors.size() > 2 && Floors.isCollision(f)) {
			f = null;
			hideFloors.remove(hideFloors.size() - 1);
			hideFloors.remove(hideFloors.size() - 1);
			addHideFloor();
			addHideFloor();
			addHideFloor();
		} else {
			hideFloors.add(f);
		}
	}
	
	public static void add(Floor f) {
		floorsShadow.getChildren().add(f.shadow);
		group.getChildren().add(f);
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
			if (((Floor)f).contains(Game.width / 2, Game.height / 2)) {
				return false;
			}
		}
		return true;
	}
	
	public static void setFloorColor() {
		for (Floor f: hideFloors) {
			FloorManager.setColor(f, Game.bkMode);
		}
		for (Node f: group.getChildren()) {
			FloorManager.setColor((Floor)f, Game.bkMode);
		}
	}
	
	public static void setOnHideAllFinished(EventHandler<ActionEvent> e) {
		FloorManager.hide.setOnFinished(e);
	}
	
	public static void hideAll() {
		for (Node f: group.getChildren()) {
			FloorManager.hide(((Floor)f), 200);
		}
	}
	
	// 判断板块位置是否和其他板块冲突
	public static boolean isCollision(Floor floor) {
		for (int i = 0; i < hideFloors.size() - 2; i++) {
			if (hideFloors.get(i).isCollisionWith(floor)) {
				return true;
			}
		}
		for (Node f: group.getChildren()) {
			if (((Floor)f).isCollisionWith(floor)) {
				return true;
			}
		}
		return false;
	}

}
