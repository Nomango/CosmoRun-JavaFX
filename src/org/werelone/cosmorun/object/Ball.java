package org.werelone.cosmorun.object;

import org.werelone.cosmorun.object.FloorManager.MainMode;
import org.werelone.cosmorun.util.GameData;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;

public class Ball extends Circle{
	public static enum Direct {LEFT_UP, RIGHT_DOWN, LEFT_DOWN, RIGHT_UP, DOWN, UP};
	
	public Direct direct;		// 小球的运动方向
	private Floor onFloor;		// 小球所在的板块
	private ScaleTransition scaleAnimation;
	
	public Ball() {
		super(GameData.getWidth() / 2, GameData.getHeight() / 2, 10);
		
		if (GameData.getResolution() == 1) this.setRadius(15);
		this.setFill(Color.WHITE);
		this.setStrokeType(StrokeType.OUTSIDE);
		this.setStroke(Color.web("white", 0.7));
		this.setStrokeWidth(3);
		this.setEffect(new BoxBlur(3, 3, 2));
		
		// 设置缩放到 0
		this.setScaleX(0.0);
		this.setScaleY(0.0);
		scaleAnimation = new ScaleTransition(Duration.millis(300), this);
	}
	
	public Floor getOn() {
		return onFloor;
	}
	
	public void setOn(Floor f) {
		autoChangeDirect(f);
		onFloor = f;
	}

	public boolean isOn() {
		return onFloor.contains(GameData.getWidth() / 2, GameData.getHeight() / 2);
	}
	
	/*
	 * 小球显示动画
	 */
	public void show() {
		this.setScaleX(0.0);
		this.setScaleY(0.0);
		this.setFill(Color.hsb(0, 0.0, 1.0));
		ScaleTransition scaleAnimation = new ScaleTransition(Duration.millis(400), this);
		scaleAnimation.setByX(1.0f);
		scaleAnimation.setByY(1.0f);
		scaleAnimation.play();
	}
	
	/*
	 * 小球消失动画
	 */
	public void hide() {
		ScaleTransition st = new ScaleTransition(Duration.millis(300), this);
		st.setByX(0.3f);
		st.setByY(0.3f);
		st.setOnFinished(e -> {
			scaleAnimation.setByX(-1.3f);
			scaleAnimation.setByY(-1.3f);
			scaleAnimation.play();
		});
		st.play();
	}
	
	/*
	 * 设置小球消失后的动作
	 */
	public void setOnHideFinished(EventHandler<ActionEvent> e) {
		this.scaleAnimation.setOnFinished(e);
	}
	
	/*
	 * 设置小球起始运动方向
	 */
	public void setDirect(Direct d) {
		this.direct = d;
	}
/*	public void initDirect(Floor f) {
		switch (f.mode) {
		case F2_UP:
			this.direct = Direct.RIGHT_UP;
			break;
		case F2_DOWN:
			this.direct = Direct.LEFT_DOWN;
			break;
		case F3_UP:
			this.direct = Direct.LEFT_UP;
			break;
		case F3_DOWN:
			this.direct = Direct.RIGHT_DOWN;
			break;
		default:
			this.direct = f.direct;
		}
	}*/
	
	/*
	 * 小球自动转向
	 */
	public void autoChangeDirect(Floor f) {
		if (this.onFloor == null || f == null || this.onFloor.mainMode == f.mainMode)
			return;
		
		if (this.onFloor.mainMode == MainMode.F1) {
			if (f.mainMode == MainMode.F3) {
				switch (this.direct) {
				case LEFT_UP:
					this.direct = Direct.UP;
					break;
				case RIGHT_DOWN:
					this.direct = Direct.DOWN;
					break;
				default:
					break;
				}
			} else if (f.mainMode == MainMode.F2) {
				switch (this.direct) {
				case LEFT_DOWN:
					this.direct = Direct.DOWN;
					break;
				case RIGHT_UP:
					this.direct = Direct.UP;
					break;
				default:
					break;
				}
			}
		} else if (this.onFloor.mainMode == MainMode.F2) {
			if (f.mainMode == MainMode.F1) {
				switch (this.direct) {
				case UP:
					this.direct = Direct.RIGHT_UP;
					break;
				case DOWN:
					this.direct = Direct.LEFT_DOWN;
					break;
				default:
					break;

				}
			} else if (f.mainMode == MainMode.F3) {
				switch (this.direct) {
				case LEFT_UP:
					this.direct = Direct.LEFT_DOWN;
					break;
				case RIGHT_DOWN:
					this.direct = Direct.RIGHT_UP;
					break;
				default:
					break;

				}
			}
		} else if (this.onFloor.mainMode == MainMode.F3) {
			if (f.mainMode == MainMode.F2) {
				switch (this.direct) {
				case LEFT_DOWN:
					this.direct = Direct.LEFT_UP;
					break;
				case RIGHT_UP:
					this.direct = Direct.RIGHT_DOWN;
					break;
				default:
					break;
					
				}
			} else if (f.mainMode == MainMode.F1) {
				switch (this.direct) {
				case DOWN:
					this.direct = Direct.RIGHT_DOWN;
					break;
				case UP:
					this.direct = Direct.LEFT_UP;
					break;
				default:
					break;

				}
			}
		}
	}

}