package org.werelone.cosmorun.object;

import java.util.Vector;

import org.werelone.cosmorun.animation.Fade;
import org.werelone.cosmorun.animation.Show;
import org.werelone.cosmorun.util.GameData;

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
		if (moveX > GameData.getWidth()) moveX = GameData.getWidth();
		if (moveX < -GameData.getWidth()) moveX = -GameData.getWidth();
		if (moveY > GameData.getHeight()) moveY = GameData.getHeight();
		if (moveY < -GameData.getHeight()) moveY = -GameData.getHeight();
	}
	
	public static void setHeadFloor(Floor f) {
		hideFloors.add(f);
		// 创建3个和头板块方向相同的板块，让玩家适应游戏速度
		for (int i = 0; i < 3; i++) {
			hideFloors.add(FloorManager.createBeginFloor(hideFloors.get(hideFloors.size() - 1)));
		}
		for (int i = 0; i < 6; i++) {
			Floors.addHideFloor();
		}
	}
	
	public static void clear() {
		group.getChildren().clear();
		hideFloors.clear();
		floorsShadow.getChildren().clear();
	}
	
	public static void addHideFloor() {
		Floor f = FloorManager.createNewFloor(hideFloors.get(hideFloors.size() - 1));
		if (hideFloors.size() == 5 && Floors.isCollision(f)) {
			f = null;
			hideFloors.remove(hideFloors.size() - 1);
			addHideFloor();
			addHideFloor();
		} else if (hideFloors.size() > 5 && Floors.isCollision(f)) {
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
			if (((Floor)f).contains(GameData.getWidth() / 2, GameData.getHeight() / 2)) {
				return false;
			}
		}
		return true;
	}
	
	public static void changeFloorColor() {
		// 切换隐藏板块的颜色
		for (Floor f: hideFloors) {
			FloorManager.setColor(f, GameData.bgMode);
		}
		for (Node f: group.getChildren()) {
			Fade.setNode(f, e -> {
				FloorManager.setColor((Floor)f, GameData.bgMode);
				Show.setNode(f);
			});
		}
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
