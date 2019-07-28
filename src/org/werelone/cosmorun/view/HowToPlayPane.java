package org.werelone.cosmorun.view;

import org.werelone.cosmorun.ui.MenuBackground;
import org.werelone.cosmorun.ui.Text;
import org.werelone.cosmorun.ui.Text.TextFont;
import org.werelone.cosmorun.ui.buttons.CloseButton;
import org.werelone.cosmorun.util.GameData;
import org.werelone.cosmorun.util.Language;
import org.werelone.cosmorun.util.Property;
import org.werelone.cosmorun.view.RootLayout.FrontPane;

import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class HowToPlayPane extends Pane{
	// 游戏说明面板的单例
	private static HowToPlayPane pane;
	// 关闭按钮
	private CloseButton closeButton = new CloseButton();
	// 游戏说明界面标题
	private Text howToPlayTitle;
	// 游戏说明文字
	private Text howToPlayText;
	// 游戏说明界面标题
	private Text howToPlayTitle_cn;
	// 游戏说明文字
	private Text howToPlayText_cn;
	// 空格图形
	private Rectangle space;
	// 手掌图形
	private Hand hand;
	// 动态闪光图形
	private Group light;
	// 小球图形
	private Circle ball;
	// 箭头图形
	private Polygon arrow;
	// 动态闪光动画
	private Timeline tl;
	
	public static HowToPlayPane create() {
		pane = new HowToPlayPane();
		pane.init();
		return pane;
	}
	
	public static HowToPlayPane getInstance() {
		return pane;
	}
	
	private void init() {
		this.initBackground();
		this.initText();
		this.initShape();
		this.initAnimation();
	}
	
	private void initBackground() {
		this.getChildren().add(new MenuBackground(Color.rgb(30, 56, 113, 0.8), 
				Color.rgb(32, 45, 98, 0.9)));
		
		closeButton.setLayoutX(Property.get("closebtn-x"));
		closeButton.setLayoutY(Property.get("closebtn-y"));
		closeButton.setOnMouseClicked(e -> RootLayout.changeFrontPane(FrontPane.startMenu));
		this.getChildren().add(closeButton);
	}
	
	private void initText() {
		// 游戏说明界面标题
		howToPlayTitle = new Text(Language.getText("en-howtoplay-title"), Property.get("howtoplay-title-x"), Property.get("howtoplay-title-y"), TextFont.GillSans, 80, true);
		this.getChildren().add(howToPlayTitle);
		// 游戏说明文字
		howToPlayText = new Text(Language.getText("en-howtoplay-tip"), Property.get("howtoplay-text-x"), Property.get("howtoplay-text-y"), TextFont.GillSans, 65, true);
		this.getChildren().add(howToPlayText);
		// 游戏说明界面标题
		howToPlayTitle_cn = new Text(Language.getText("cn-howtoplay-title"), Property.get("howtoplay-title-cn-x"), Property.get("howtoplay-title-cn-y"), TextFont.MsYaHei, 65, false);
		this.getChildren().add(howToPlayTitle_cn);
		// 游戏说明文字
		howToPlayText_cn = new Text(Language.getText("cn-howtoplay-tip"), Property.get("howtoplay-text-cn-x"), Property.get("howtoplay-text-cn-y"), TextFont.MsYaHei, 35, false);
		this.getChildren().add(howToPlayText_cn);
		
		changeLang();
	}
	
	private void initShape() {
		// 模糊边特效
		DropShadow dropShadow = new DropShadow(5, 3, 3, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		// 空格图形
		space = new Rectangle(Property.get("howtoplay-space-x"), Property.get("howtoplay-space-y"), Property.get("howtoplay-space-w"), Property.get("howtoplay-space-h"));
		space.setArcWidth(Property.get("howtoplay-space-arcw"));
		space.setArcHeight(Property.get("howtoplay-space-arch"));
		space.setStroke(Color.hsb(0, 0.0, 1.0, 0.90));
		space.setStrokeWidth(Property.get("howtoplay-space-strokew"));
		space.setFill(null);
		space.setEffect(dropShadow);
		this.getChildren().add(space);
		// 手图形
		hand = new Hand();
		hand.setLayoutX(Property.get("howtoplay-hand-x"));
		hand.setLayoutY(Property.get("howtoplay-hand-y"));
		hand.setEffect(dropShadow);
		this.getChildren().add(hand);
		// 手掌的光芒图形
		Rectangle r1 = new Rectangle(Property.get("howtoplay-r1-x"), Property.get("howtoplay-r1-y"), Property.get("howtoplay-r-w"), Property.get("howtoplay-r-h"));
		Rectangle r2 = new Rectangle(Property.get("howtoplay-r2-x"), Property.get("howtoplay-r2-y"), Property.get("howtoplay-r-w"), Property.get("howtoplay-r-h"));
		Rectangle r3 = new Rectangle(Property.get("howtoplay-r3-x"), Property.get("howtoplay-r3-y"), Property.get("howtoplay-r-w"), Property.get("howtoplay-r-h"));
		r1.setFill(Color.WHITE);
		r2.setFill(Color.WHITE);
		r3.setFill(Color.WHITE);
		r1.setRotate(-55);
		r2.setRotate(-20);
		r3.setRotate(20);
		light = new Group();
		light.setEffect(dropShadow);
		light.getChildren().addAll(r1, r2, r3);
		// 加入六个板块
		this.getChildren().add(new Floor(Property.get("howtoplay-floor1-x"), Property.get("howtoplay-floor1-y")));
		this.getChildren().add(new Floor(Property.get("howtoplay-floor2-x"), Property.get("howtoplay-floor2-y")));
		this.getChildren().add(new Floor(Property.get("howtoplay-floor3-x"), Property.get("howtoplay-floor3-y")));
		this.getChildren().add(new Floor(Property.get("howtoplay-floor4-x"), Property.get("howtoplay-floor4-y")));
		this.getChildren().add(new Floor(Property.get("howtoplay-floor5-x"), Property.get("howtoplay-floor5-y")));
		this.getChildren().add(new Floor(Property.get("howtoplay-floor6-x"), Property.get("howtoplay-floor6-y")));
		// 加入小球
		ball = new Circle(Property.get("howtoplay-ball-x"), Property.get("howtoplay-ball-y"), Property.get("howtoplay-ball-r"));
		ball.setFill(Color.WHITE);
		ball.setEffect(new BoxBlur(3, 3, 2));
		this.getChildren().add(ball);
		// 指示小球方向的箭头
		arrow = new Polygon(Property.getArray("howtoplay-arrow-points"));
		arrow.setLayoutX(Property.get("howtoplay-arrow-x"));
		arrow.setLayoutY(Property.get("howtoplay-arrow-y"));
		arrow.setFill(Color.hsb(0, 0.0, 1.0, 0.5));
		arrow.setEffect(dropShadow);
		this.getChildren().add(arrow);
	}
	
	private void initAnimation() {
		tl = new Timeline(new KeyFrame(Duration.millis(500), e -> {
			if (hand.getScaleX() == 1.0) {
				hand.setScaleX(0.8);
				hand.setScaleY(0.8);
				pane.getChildren().add(light);
			} else {
				hand.setScaleX(1.0);
				hand.setScaleY(1.0);
				pane.getChildren().remove(light);
			}
		}));
		tl.setCycleCount(-1);
		// 将动画的播放和界面的透明度绑定
		this.opacityProperty().addListener(e -> {
			if (this.getOpacity() >= 0.9 && tl.getStatus() == Status.STOPPED) {
				tl.play();
			} else if (this.getOpacity() <= 0.2 && tl.getStatus() == Status.RUNNING) {
				tl.stop();
			}
		});
	}
	
	// 假板块类
	private class Floor extends Polygon{
		private final double radiusY = Property.get("howtoplay-floor-radiusy");			 // 短半轴长
		private final double radiusX = radiusY * 1.732;// 长半轴长
		
		public Floor(double x, double y) {
			this.getPoints().addAll(0.0, -radiusY, radiusX, 0.0, 0.0, radiusY, -radiusX, 0.0);
			this.setFill(Color.rgb(65, 89, 173));
			this.setLayoutX(x);
			this.setLayoutY(y);
			this.setEffect(new BoxBlur(3, 3, 2));
		}
	}

	// 手的图形类
	private class Hand extends Group {
		private Polygon hand = new Polygon();
		private Rectangle r;
		
		public Hand() {
			r = new Rectangle(Property.get("howtoplay-hand-r-x"), Property.get("howtoplay-hand-r-y"), Property.get("howtoplay-hand-r-w"), Property.get("howtoplay-hand-r-h"));
			r.setFill(Color.WHITE);
			
			hand = new Polygon(Property.getArray("howtoplay-hand-points"));
			hand.setFill(Color.WHITE);
			
			this.getChildren().addAll(hand, r);
			this.setRotate(-20);
		}
	}
	
	// 切换语言
	public static void changeLang() {
		if (GameData.language == 0) {
			pane.howToPlayTitle.setOpacity(1);
			pane.howToPlayTitle_cn.setOpacity(0);
			pane.howToPlayText.setOpacity(1);
			pane.howToPlayText_cn.setOpacity(0);
		}
		else {
			pane.howToPlayTitle.setOpacity(0);
			pane.howToPlayTitle_cn.setOpacity(1);
			pane.howToPlayText.setOpacity(0);
			pane.howToPlayText_cn.setOpacity(1);
		}
	}

}
