package org.werelone.cosmorun.view;

import org.werelone.cosmorun.animation.Fade;
import org.werelone.cosmorun.animation.Show;
import org.werelone.cosmorun.object.Triangle;
import org.werelone.cosmorun.util.GameData;
import org.werelone.cosmorun.util.Property;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BackgroundPane extends Pane{
	// 背景布局的单例
	private static BackgroundPane pane;
	// 背景矩形
	private Rectangle bgRect;
	// 三角形动画层
	private Pane trianglePane;
	// 三角形坐标
	private double[] points = Property.getArray("background-triangle-points");
	// 线性梯度变化预设值
	private static LinearGradient[] lg = new LinearGradient[]{
			// 蓝色
			new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
					new Stop(0, Color.rgb(8, 39, 110)), new Stop(1, Color.rgb(6, 37, 38))),
			// 紫色
			new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
					new Stop(0, Color.rgb(118, 40, 78)), new Stop(1, Color.rgb(45, 31, 66))),
			// 金色
			new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
					new Stop(0, Color.rgb(7, 35, 82)), new Stop(1, Color.rgb(55, 26, 19)))
	};
	
	// 创建一个新的背景
	public static BackgroundPane create() {
		pane = new BackgroundPane();
		return pane;
	}
	
	public static BackgroundPane getInstance() {
		return pane;
	}
	
	public BackgroundPane() {
		// 初始化背景矩形
		bgRect = new Rectangle(0, 0, GameData.getWidth(), GameData.getHeight());
		bgRect.setFill(lg[GameData.bgMode]);
		this.getChildren().add(bgRect);
		// 初始化三角形层
		trianglePane = new Pane();
		this.getChildren().add(trianglePane);
		// 添加灰度蒙板
		ImageView cover = new ImageView(new Image("resources/cover.png"));
		cover.setFitWidth(GameData.getWidth());
		cover.setFitHeight(GameData.getHeight());
		this.getChildren().add(cover);
	}
	
	public void play() {
		Timeline tl = new Timeline(new KeyFrame(Duration.millis(130), e -> {
			double centerX = GameData.getWidth() * Math.random();
			double centerY = GameData.getHeight() * Math.random();
			trianglePane.getChildren().add(new Triangle(points, centerX, centerY));
		}));
		tl.setCycleCount(-1);
		tl.play();
	}
	
	public void move(double x, double y) {
		for(Node t: trianglePane.getChildren()) {
			t.setLayoutX(t.getLayoutX() + x);
			t.setLayoutY(t.getLayoutY() + y);
		}
	}
	
	public void removeFromTrianglePane(Triangle t) {
		trianglePane.getChildren().remove(t);
	}
	
	// 切换背景颜色
	public static void changeBgColor() {
		// 切换背景
		if (GameData.bgMode == GameData.MAX_BG_MODE - 1) {
			GameData.bgMode = 0;
		} else {
			GameData.bgMode++;
		}
		// 隐藏当前背景
		Fade.setNode(pane.bgRect, e -> {
			// 设置新的颜色
			pane.bgRect.setFill(lg[GameData.bgMode]);
			// 显示新背景
			Show.setNode(pane.bgRect);
		});
	}
	
	// 获取当前背景颜色字符串
	public static String getBgMode(int lang) {
		switch(GameData.bgMode) {
		case 0:
			return (lang == 0) ? "BLU" : "蓝色";
		case 1:
			return (lang == 0) ? "VLT" : "紫色";
		case 2:
			return (lang == 0) ? "GLD" : "金色";
		default:
			return "";
		}
	}

}
