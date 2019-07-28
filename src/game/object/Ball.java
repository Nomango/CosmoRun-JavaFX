package game.object;

import game.Game;
import game.object.FloorManager.MainMode;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;

public class Ball extends Circle{
	public static enum Direct {LEFT_UP, RIGHT_DOWN, LEFT_DOWN, RIGHT_UP, DOWN, UP};
	
	public Direct direct;
	private Floor onFloor;
	private ScaleTransition scaleAnimation = new ScaleTransition(Duration.millis(300), this);
	
	public Ball() {
		super(Game.width / 2.0D, Game.height / 2.0D, 15);
		
		this.setCache(true);
		this.setCacheHint(CacheHint.QUALITY);
		
		this.setFill(Color.WHITE);
		this.setStrokeType(StrokeType.OUTSIDE);
		this.setStroke(Color.web("white", 0.7));
		this.setStrokeWidth(3);
		this.setEffect(new BoxBlur(3, 3, 2));
		
		this.setScaleX(0.0);
		this.setScaleY(0.0);
		this.setCacheHint(CacheHint.SPEED);
	}
	
	public Floor getOn() {
		return onFloor;
	}
	
	public void setOn(Floor f) {
		autoChangeDirect(f);
		onFloor = f;
	}

	public boolean isOn(double dx, double dy) {
		return onFloor.contains(Game.width / 2 - dx, Game.height / 2 - dy);
	}
	
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
	
	public void show() {
		this.setScaleX(0.0);
		this.setScaleY(0.0);
		ScaleTransition scaleAnimation = new ScaleTransition(Duration.millis(400), this);
		scaleAnimation.setByX(1.0f);
		scaleAnimation.setByY(1.0f);
		scaleAnimation.play();
	}
	
	public void setOnHideFinished(EventHandler<ActionEvent> e) {
		this.scaleAnimation.setOnFinished(e);
	}
	
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
	
	public void initDirect(Floor f) {
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
	}

}
