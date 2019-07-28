package game.animation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class AndroidLoadingPane extends Pane{
	private AndroidArc androidArc;
	private Timeline loadingAnimation;
	private Rectangle background;
	private double secondTime = -1;
	
	public AndroidLoadingPane(double centerX, double centerY, double lengthOfSide) {
		this.setCache(true);
		this.setCacheHint(CacheHint.QUALITY);
		
		this.setLayoutX(centerX - lengthOfSide / 2);
		this.setLayoutY(centerY - lengthOfSide / 2);
		this.setWidth(lengthOfSide);
		this.setHeight(lengthOfSide);
		
		background = new Rectangle(0, 0, lengthOfSide, lengthOfSide);
		background.setFill(Color.WHITE);
		background.setStroke(null);
		
		androidArc = new AndroidArc(lengthOfSide / 2, lengthOfSide / 2, lengthOfSide / 3, lengthOfSide / 3, 0, 10);
		androidArc.setType(ArcType.OPEN);
		androidArc.setFill(null);
		androidArc.setStroke(Color.GREEN);
		androidArc.setStrokeWidth(lengthOfSide / 20);
		
		this.getChildren().addAll(background, androidArc);
		
		this.setAnimation();
	}
	
	public void setAnimation() {
		this.setCacheHint(CacheHint.SPEED);
		
		EventHandler<ActionEvent> eventHandler = e -> {
			// 旋转基础速度
			androidArc.setStartAngle(androidArc.getStartAngle() - 2);
			
			// 防止起始角度过小
			if (androidArc.getStartAngle() <= -360) {
				androidArc.setStartAngle(androidArc.getStartAngle() + 360);
			}
			
			if (androidArc.isDelay()) {
				androidArc.subDelay();
			}
			else if (androidArc.isEnlarge) {
				// 增长的过程：增加长度，缩小起始角
				androidArc.setStartAngle(androidArc.getStartAngle() - 4);
				androidArc.setLength(androidArc.getLength() + 4);
				if (androidArc.getLength() >= 290) {
					androidArc.resetDelay();
					androidArc.isEnlarge = false;
				}
			}
			else {
				// 缩小的过程：缩小长度
				androidArc.setLength(androidArc.getLength() - 4);
				if (androidArc.getLength() <= 10) {
					androidArc.resetDelay();
					androidArc.isEnlarge = true;
				}
			}
		};

		loadingAnimation = new Timeline(new KeyFrame(Duration.millis(10), eventHandler));
		loadingAnimation.setCycleCount(Timeline.INDEFINITE);
	}
	
	public void setBkStroke(Paint value) {
		background.setStroke(value);
	}
	
	public void setBkColor(Paint value) {
		background.setFill(value);
	}
	
	public void setColor(Paint value) {
		androidArc.setStroke(value);
	}
	
	public void setStrokeWidth(double value) {
		androidArc.setStrokeWidth(value);
	}
	
	public void setTime(double second) {
		this.secondTime = second;
		loadingAnimation.setCycleCount((int)(second * 100 * loadingAnimation.getRate()));
	}
	
	private void setTime() {
		if (this.secondTime == -1) {
			loadingAnimation.setCycleCount(-1);
		} else {
			setTime(this.secondTime);
		}
	}
	
	public void setRate(double rate) {
		loadingAnimation.setRate(rate);
		setTime();
	}
	
	public void play() {
		loadingAnimation.play();
	}
	
	public void pause() {
		loadingAnimation.pause();
	}
	
	public void stop() {
		loadingAnimation.stop();
	}
	
	public void setOnFinished(EventHandler<ActionEvent> e) {
		loadingAnimation.setOnFinished(e);
	}
	
}
