package org.werelone.cosmorun.object;

import org.werelone.cosmorun.view.BackgroundPane;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class Triangle extends Polygon{
	private ParallelTransition pt = new ParallelTransition(this);
	
	public Triangle(double[] points, double centerX, double centerY) {
		super(points);
		this.setLayoutX(centerX);
		this.setLayoutY(centerY);
		this.setFill(Color.rgb(255, 255, 255, 0.2));
		this.setEffect(new BoxBlur(2, 2, 2));
		this.initAnimation();
	}
	
	private void initAnimation() {
		// �����������ʱ�� 1.5-3.0s
		Duration dt = Duration.millis(Math.random() * 1500 + 1500);
		
		// ��ת����
		RotateTransition rt = new RotateTransition(dt);
		// ��ʼ�Ƕ�0-180��
		rt.setFromAngle((int)Math.random() * 180);
		// ��ת��240-480��
		rt.setToAngle((int)Math.random() * 240 + 240);
		
		// �Ŵ󶯻�
		ScaleTransition st = new ScaleTransition(dt);
		// ��ʼ���Ŵ�С 0.0
		st.setFromX(0);
		st.setFromY(0);
		// �Ŵ��� 1.6-2.2
		st.setToX(Math.random() * 0.6 + 1.6);
		st.setToY(st.getToX());
		
		// ��������
		FadeTransition ft = new FadeTransition(dt);
		ft.setFromValue(1);
		ft.setToValue(0);
		
		TranslateTransition tt = new TranslateTransition(dt);
		tt.setByX((Math.random() - 0.5) * 100);
		tt.setByY((Math.random() - 0.5) * 100);
		
		pt.getChildren().addAll(rt, st, ft, tt);
		pt.setOnFinished(e -> BackgroundPane.getInstance().removeFromTrianglePane(this));
		pt.play();
	}

}
