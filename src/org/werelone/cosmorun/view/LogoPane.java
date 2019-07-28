package org.werelone.cosmorun.view;

import org.werelone.cosmorun.ui.Text;
import org.werelone.cosmorun.ui.Text.TextFont;
import org.werelone.cosmorun.util.GameData;
import org.werelone.cosmorun.util.Property;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class LogoPane extends Pane{
	private static LogoPane pane;
	private Pane symbolPane;
	private Circle light;
	
	public static LogoPane create() {
		pane = new LogoPane();
		pane.init();
		return pane;
	}
	
	private void init() {
		this.initBackground();
		this.initLight();
		this.initShape();
		this.initText();
		this.initAnimation();
		// 加载游戏
		GamePane.getInstance().initGame();
	}
	
	private void initBackground() {
		symbolPane = new Pane();
		Rectangle background = new Rectangle(GameData.getWidth(), GameData.getHeight());
		background.setFill(Color.BLACK);
		this.getChildren().addAll(background, symbolPane);
	}
	
	private void initText() {
		Text text = new Text(" Made by\n werelone", Property.get("cutscene-text-x"), Property.get("cutscene-text-y"), TextFont.GillSans, 65, true);
		DropShadow dropShadow = new DropShadow(5, 3, 3, Color.BLACK);
		text.setEffect(dropShadow);
		symbolPane.getChildren().add(text);
	}
	
	private void initShape() {
		// 多边形标志
		Polygon p = new Polygon(Property.getArray("cutscene-symbol-points"));
		p.setEffect(new BoxBlur(3, 3, 6));
		p.setFill(Color.WHITE);
		p.setLayoutX(GameData.getWidth() / 2);
		p.setLayoutY(GameData.getHeight() / 2);
		p.setOpacity(0.95);
		symbolPane.getChildren().add(p);
	}
	
	private void initLight() {
		// 光圈
		light = new Circle(GameData.getWidth() / 2, GameData.getHeight() / 2, Property.get("cutscene-light-r"), Color.WHITE);
		light.setScaleX(0.8);
		light.setScaleY(0.8);
		light.setEffect(new BoxBlur(Property.get("cutscene-light-blur"), Property.get("cutscene-light-blur"), (int)Property.get("cutscene-light-blur")));
		light.setOpacity(0.9);
		symbolPane.getChildren().add(light);
	}
	
	private void initAnimation() {
		// 1.5s光圈放大动画
		ScaleTransition stBig = new ScaleTransition(Duration.millis(1500), light);
		stBig.setFromX(0.8);
		stBig.setFromY(0.8);
		stBig.setToX(1);
		stBig.setToY(1);
		// 1.0s光圈缩小动画
		ScaleTransition stLittle = new ScaleTransition(Duration.millis(1000), light);
		stLittle.setFromX(1);
		stLittle.setFromY(1);
		stLittle.setToX(0);
		stLittle.setToY(0);
		// 1.5s等待时间
		Timeline tl = new Timeline(new KeyFrame(Duration.millis(500), e -> {}));
		// 0.5s淡入动画
		FadeTransition ftOut = new FadeTransition(Duration.millis(500), symbolPane);
		ftOut.setFromValue(1);
		ftOut.setToValue(0);
		// 顺序播放动画
		SequentialTransition st = new SequentialTransition(stBig, stLittle, tl, ftOut);
		st.setOnFinished(e -> RootLayout.show());
		st.play();
	}

}
