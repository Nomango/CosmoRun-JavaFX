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
	// �������ֵĵ���
	private static BackgroundPane pane;
	// ��������
	private Rectangle bgRect;
	// �����ζ�����
	private Pane trianglePane;
	// ����������
	private double[] points = Property.getArray("background-triangle-points");
	// �����ݶȱ仯Ԥ��ֵ
	private static LinearGradient[] lg = new LinearGradient[]{
			// ��ɫ
			new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
					new Stop(0, Color.rgb(8, 39, 110)), new Stop(1, Color.rgb(6, 37, 38))),
			// ��ɫ
			new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
					new Stop(0, Color.rgb(118, 40, 78)), new Stop(1, Color.rgb(45, 31, 66))),
			// ��ɫ
			new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
					new Stop(0, Color.rgb(7, 35, 82)), new Stop(1, Color.rgb(55, 26, 19)))
	};
	
	// ����һ���µı���
	public static BackgroundPane create() {
		pane = new BackgroundPane();
		return pane;
	}
	
	public static BackgroundPane getInstance() {
		return pane;
	}
	
	public BackgroundPane() {
		// ��ʼ����������
		bgRect = new Rectangle(0, 0, GameData.getWidth(), GameData.getHeight());
		bgRect.setFill(lg[GameData.bgMode]);
		this.getChildren().add(bgRect);
		// ��ʼ�������β�
		trianglePane = new Pane();
		this.getChildren().add(trianglePane);
		// ��ӻҶ��ɰ�
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
	
	// �л�������ɫ
	public static void changeBgColor() {
		// �л�����
		if (GameData.bgMode == GameData.MAX_BG_MODE - 1) {
			GameData.bgMode = 0;
		} else {
			GameData.bgMode++;
		}
		// ���ص�ǰ����
		Fade.setNode(pane.bgRect, e -> {
			// �����µ���ɫ
			pane.bgRect.setFill(lg[GameData.bgMode]);
			// ��ʾ�±���
			Show.setNode(pane.bgRect);
		});
	}
	
	// ��ȡ��ǰ������ɫ�ַ���
	public static String getBgMode(int lang) {
		switch(GameData.bgMode) {
		case 0:
			return (lang == 0) ? "BLU" : "��ɫ";
		case 1:
			return (lang == 0) ? "VLT" : "��ɫ";
		case 2:
			return (lang == 0) ? "GLD" : "��ɫ";
		default:
			return "";
		}
	}

}
