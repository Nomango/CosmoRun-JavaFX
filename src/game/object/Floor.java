package game.object;

import game.object.Ball.Direct;
import game.pane.BackgroundPane;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class Floor extends Group {
	public static enum MainMode {F1, F2, F3};
	public static enum Mode {
		F1_LEFT_UP, F1_LEFT_DOWN, F1_RIGHT_UP, F1_RIGHT_DOWN, 
		F2_LEFT_UP, F2_DOWN, F2_UP, F2_RIGHT_DOWN, 
		F3_UP, F3_RIGHT_UP, F3_LEFT_DOWN, F3_DOWN};

	public final static double radiusY = 45;			 // 短半轴长
	public final static double radiusX = radiusY * 1.732;// 长半轴长
	public final static double ex = 3.2;		// 用于判断范围的标准量

	public Mode mode;			// 板块主类型
	public MainMode mainMode;	// 板块类型
	public Ball.Direct direct;	// 板块方向（方便修改小球方向）
	protected Polygon p = new Polygon(); // 板块形状
	protected Polygon range = new Polygon();	// 用于判断范围的稍大板块
	protected boolean seeRange = false;			// range 是否可见
	private ScaleTransition hideAnimation;
	
	protected Floor() {
		
	}

	public Floor(Floor prevFloor) {
		// 从上一块的基础上构造新板块
		initProperty(prevFloor.mode, prevFloor.getCenterX(), prevFloor.getCenterY());
		setCoordinate();
		setColor();
	}

	public boolean rangeContains(double x, double y) {
		// 判断点(x, y)是否在板块内
		return range.contains(x - this.getCenterX(), y - this.getCenterY());
	}
	
	public boolean shapeContains(double x, double y) {
		// 判断点(x, y)是否在板块内
		return p.contains(x - this.getCenterX(), y - this.getCenterY());
	}

	protected void setCenter(double x, double y) {
		// 设置板块中心位置
		this.setLayoutX(x);
		this.setLayoutY(y);
	}

	public double getCenterX() {
		return this.getLayoutX();
	}

	public double getCenterY() {
		return this.getLayoutY();
	}

	// 判断板块位置是否和其他板块冲突
	public boolean isCollisionWith(FloorsGroup floors) {
		for (int i = 0; i < floors.size() - 1; i++) {
			Floor f = floors.get(i);
			for (int j = 0; j < 16; j += 2) {
				if (f.shapeContains(this.getCenterX() + range.getPoints().get(j),
						this.getCenterY() + range.getPoints().get(j + 1))) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void show(FloorsGroup floors) {
		this.setScaleX(0.0);
		this.setScaleY(0.0);
		ScaleTransition st = new ScaleTransition(Duration.millis(300), this);
		st.setByX(1.0f);
		st.setByY(1.0f);
		st.play();
		
		floors.add(this);
	}
	
	public void hide(FloorsGroup floors) {
		hideAnimation = new ScaleTransition(Duration.millis(300), this);
		hideAnimation.setByX(-1.0f);
		hideAnimation.setByY(-1.0f);
		hideAnimation.setOnFinished(e -> floors.remove(this));
		hideAnimation.play();
	}
	
	public void setOnHideFinished(EventHandler<ActionEvent> e) {
		hideAnimation.setOnFinished(e);
	}

	protected void setCoordinate() {
		// 根据板块类型创建形状坐标
		switch (this.mainMode) {
		case F1:
			p.getPoints().addAll(0.0, -radiusY, radiusX, 0.0, 0.0, radiusY, -radiusX, 0.0);
			range.getPoints().addAll(
					0.0,					-radiusY - ex,	radiusX / 2 + 1.732 * ex / 2,	-radiusY / 2 - ex / 2, 
					radiusX + ex * 1.732,	0.0,			radiusX / 2 + 1.732 * ex / 2,	radiusY / 2 + ex / 2, 
					0.0,					radiusY + ex,	-radiusX / 2 - 1.732 * ex / 2,	radiusY / 2 + ex / 2, 
					-radiusX - ex * 1.732,	0.0,			-radiusX / 2 - 1.732 * ex / 2,	-radiusY / 2 - ex / 2);
			break;
		case F2:
			p.getPoints().addAll(-radiusX / 2, -radiusY * 1.5, radiusX / 2, -radiusY * 0.5, radiusX / 2, radiusY * 1.5,
					-radiusX / 2, radiusY * 0.5);
			range.getPoints().addAll(
					-(radiusX + 1.732 * ex) / 2,	-(radiusY + ex) * 1.5,	0.0,							-(radiusY + ex), 
					(radiusX + 1.732 * ex) / 2,		-(radiusY + ex) * 0.5,	(radiusX + 1.732 * ex) / 2,		0.0, 
					(radiusX + 1.732 * ex) / 2,		(radiusY + ex) * 1.5,	0.0,							(radiusY + ex),
					-(radiusX + 1.732 * ex) / 2,	(radiusY + ex) * 0.5,	-(radiusX + 1.732 * ex) / 2,	0.0);
			break;
		case F3:
			p.getPoints().addAll(-radiusX / 2, -radiusY * 0.5, radiusX / 2, -radiusY * 1.5, radiusX / 2, radiusY * 0.5,
					-radiusX / 2, radiusY * 1.5);
			range.getPoints().addAll(
					-(radiusX + 1.732 * ex) / 2,	-(radiusY + ex) * 0.5,	0.0,							-(radiusY + ex), 
					(radiusX + 1.732 * ex) / 2,		-(radiusY + ex) * 1.5,	(radiusX + 1.732 * ex) / 2,		0.0, 
					(radiusX + 1.732 * ex) / 2,		(radiusY + ex) * 0.5,	0.0,							(radiusY + ex),
					-(radiusX + 1.732 * ex) / 2,	(radiusY + ex) * 1.5,	-(radiusX + 1.732 * ex) / 2,	0.0);
			break;
		}
	}

	protected void setColor() {
		this.setCache(true);
		this.setCacheHint(CacheHint.QUALITY);
		
		p.setStrokeWidth(1.5);
		p.setEffect(new BoxBlur(2, 2, 1));

		this.setColor(BackgroundPane.bkColor);
		this.getChildren().add(p);
		
		if (seeRange) {
			range.setStroke(Color.BLACK);
			range.setFill(Color.hsb(0, 0.0, 0.2, 0.3));
			this.getChildren().add(range);
		}
		
	}
	
	public void setColor(BackgroundPane.BkColor bkColor) {
		// 根据板块类型设置颜色
		switch (bkColor) {
		case blue:
			p.setStroke(Color.rgb(0, 114, 181));
			switch (this.mode) {
			case F1_LEFT_UP:
				// 线性梯度渐变色
				p.setFill(new LinearGradient(0.33, 0.0, 0.66, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(0, Color.rgb(0, 160, 254)), new Stop(1, Color.rgb(0, 145, 235))));
				break;
			case F1_LEFT_DOWN:
				p.setFill(new LinearGradient(0.66, 0.0, 0.33, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(1, Color.rgb(0, 145, 235)), new Stop(0, Color.rgb(0, 160, 254))));
				break;
			case F1_RIGHT_DOWN:
				p.setFill(new LinearGradient(0.33, 0.0, 0.66, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(0, Color.rgb(0, 145, 235)), new Stop(1, Color.rgb(0, 160, 254))));
				break;
			case F1_RIGHT_UP:
				p.setFill(new LinearGradient(0.66, 0.0, 0.33, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(1, Color.rgb(0, 160, 254)), new Stop(0, Color.rgb(0, 145, 235))));
				break;
			case F2_DOWN:
			case F2_LEFT_UP:
			case F2_RIGHT_DOWN:
			case F2_UP:
				p.setFill(Color.rgb(56, 134, 195));
				break;
			case F3_DOWN:
			case F3_LEFT_DOWN:
			case F3_RIGHT_UP:
			case F3_UP:
				p.setStroke(Color.rgb(0, 69, 109));
				p.setFill(Color.rgb(0, 91, 144));
				break;
			}
			break;
		case green:
			p.setStroke(Color.rgb(0, 140, 70));
			switch (this.mode) {
			case F1_LEFT_UP:
				// 线性梯度渐变色
				p.setFill(new LinearGradient(0.33, 0.0, 0.66, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(0, Color.rgb(0, 211, 108)), new Stop(1, Color.rgb(0, 199, 101))));
				break;
			case F1_LEFT_DOWN:
				p.setFill(new LinearGradient(0.66, 0.0, 0.33, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(1, Color.rgb(0, 199, 101)), new Stop(0, Color.rgb(0, 211, 108))));
				break;
			case F1_RIGHT_DOWN:
				p.setFill(new LinearGradient(0.33, 0.0, 0.66, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(0, Color.rgb(0, 199, 101)), new Stop(1, Color.rgb(0, 211, 108))));
				break;
			case F1_RIGHT_UP:
				p.setFill(new LinearGradient(0.66, 0.0, 0.33, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(1, Color.rgb(0, 211, 108)), new Stop(0, Color.rgb(0, 199, 101))));
				break;
			case F2_DOWN:
			case F2_LEFT_UP:
			case F2_RIGHT_DOWN:
			case F2_UP:
				p.setFill(Color.rgb(0, 160, 83));
				break;
			case F3_DOWN:
			case F3_LEFT_DOWN:
			case F3_RIGHT_UP:
			case F3_UP:
				p.setStroke(Color.rgb(0, 91, 46));
				p.setFill(Color.rgb(0, 121, 62));
				break;
			}
			break;
		}
	}

	// 初始化板块属性
	private void initProperty(Mode prevMode, double prevX, double prevY) {
		// 随机板块模式
		int rand = (int) (Math.random() * 4);
		switch (prevMode) {
		case F1_LEFT_UP:
			if (rand == 0) {
				this.mode = Mode.F1_LEFT_UP;
				this.setLayoutX(prevX - radiusX);
				this.setLayoutY(prevY - radiusY);
			} else if (rand == 1) {
				this.mode = Mode.F1_LEFT_DOWN;
				this.setLayoutX(prevX - radiusX);
				this.setLayoutY(prevY + radiusY);
			} else if (rand == 2) {
				this.mode = Mode.F1_RIGHT_UP;
				this.setLayoutX(prevX + radiusX);
				this.setLayoutY(prevY - radiusY);
			} else if (rand == 3) {
				this.mode = Mode.F3_UP;
				this.setLayoutX(prevX - radiusX / 2);
				this.setLayoutY(prevY - radiusY * 1.5);
			}
			break;
		case F1_LEFT_DOWN:
			if (rand == 0) {
				this.mode = Mode.F1_RIGHT_DOWN;
				this.setLayoutX(prevX + radiusX);
				this.setLayoutY(prevY + radiusY);
			} else if (rand == 1) {
				this.mode = Mode.F1_LEFT_DOWN;
				this.setLayoutX(prevX - radiusX);
				this.setLayoutY(prevY + radiusY);
			} else if (rand == 2) {
				this.mode = Mode.F1_LEFT_UP;
				this.setLayoutX(prevX - radiusX);
				this.setLayoutY(prevY - radiusY);
			} else if (rand == 3) {
				this.mode = Mode.F2_DOWN;
				this.setLayoutX(prevX - radiusX / 2);
				this.setLayoutY(prevY + radiusY * 1.5);
			}
			break;
		case F1_RIGHT_DOWN:
			if (rand == 0) {
				this.mode = Mode.F1_RIGHT_DOWN;
				this.setLayoutX(prevX + radiusX);
				this.setLayoutY(prevY + radiusY);
			} else if (rand == 1) {
				this.mode = Mode.F1_LEFT_DOWN;
				this.setLayoutX(prevX - radiusX);
				this.setLayoutY(prevY + radiusY);
			} else if (rand == 2) {
				this.mode = Mode.F1_RIGHT_UP;
				this.setLayoutX(prevX + radiusX);
				this.setLayoutY(prevY - radiusY);
			} else if (rand == 3) {
				this.mode = Mode.F3_DOWN;
				this.setLayoutX(prevX + radiusX / 2);
				this.setLayoutY(prevY + radiusY * 1.5);
			}
			break;
		case F1_RIGHT_UP:
			if (rand == 0) {
				this.mode = Mode.F1_RIGHT_DOWN;
				this.setLayoutX(prevX + radiusX);
				this.setLayoutY(prevY + radiusY);
			} else if (rand == 1) {
				this.mode = Mode.F1_RIGHT_UP;
				this.setLayoutX(prevX + radiusX);
				this.setLayoutY(prevY - radiusY);
			} else if (rand == 2) {
				this.mode = Mode.F1_LEFT_UP;
				this.setLayoutX(prevX - radiusX);
				this.setLayoutY(prevY - radiusY);
			} else if (rand == 3) {
				this.mode = Mode.F2_UP;
				this.setLayoutX(prevX + radiusX / 2);
				this.setLayoutY(prevY - radiusY * 1.5);
			}
			break;
		case F2_UP:
			if (rand == 0) {
				this.mode = Mode.F1_RIGHT_UP;
				this.setLayoutX(prevX + radiusX / 2);
				this.setLayoutY(prevY - radiusY * 1.5);
			} else if (rand == 1) {
				this.mode = Mode.F2_UP;
				this.setLayoutX(prevX);
				this.setLayoutY(prevY - radiusY * 2);
			} else if (rand == 2) {
				this.mode = Mode.F2_LEFT_UP;
				this.setLayoutX(prevX - radiusX);
				this.setLayoutY(prevY - radiusY);
			} else if (rand == 3) {
				this.mode = Mode.F2_RIGHT_DOWN;
				this.setLayoutX(prevX + radiusX);
				this.setLayoutY(prevY + radiusY);
			}
			break;
		case F2_DOWN:
			if (rand == 0) {
				this.mode = Mode.F1_LEFT_DOWN;
				this.setLayoutX(prevX - radiusX / 2);
				this.setLayoutY(prevY + radiusY * 1.5);
			} else if (rand == 1) {
				this.mode = Mode.F2_DOWN;
				this.setLayoutX(prevX);
				this.setLayoutY(prevY + radiusY * 2);
			} else if (rand == 2) {
				this.mode = Mode.F2_LEFT_UP;
				this.setLayoutX(prevX - radiusX);
				this.setLayoutY(prevY - radiusY);
			} else if (rand == 3) {
				this.mode = Mode.F2_RIGHT_DOWN;
				this.setLayoutX(prevX + radiusX);
				this.setLayoutY(prevY + radiusY);
			}
			break;
		case F2_LEFT_UP:
			if (rand == 0) {
				this.mode = Mode.F3_LEFT_DOWN;
				this.setLayoutX(prevX - radiusX);
				this.setLayoutY(prevY);
			} else if (rand == 1) {
				this.mode = Mode.F2_UP;
				this.setLayoutX(prevX);
				this.setLayoutY(prevY - radiusY * 2);
			} else if (rand == 2) {
				this.mode = Mode.F2_LEFT_UP;
				this.setLayoutX(prevX - radiusX);
				this.setLayoutY(prevY - radiusY);
			} else if (rand == 3) {
				this.mode = Mode.F2_DOWN;
				this.setLayoutX(prevX);
				this.setLayoutY(prevY + radiusY * 2);
			}
			break;
		case F2_RIGHT_DOWN:
			if (rand == 0) {
				this.mode = Mode.F3_RIGHT_UP;
				this.setLayoutX(prevX + radiusX);
				this.setLayoutY(prevY);
			} else if (rand == 1) {
				this.mode = Mode.F2_UP;
				this.setLayoutX(prevX);
				this.setLayoutY(prevY - radiusY * 2);
			} else if (rand == 2) {
				this.mode = Mode.F2_RIGHT_DOWN;
				this.setLayoutX(prevX + radiusX);
				this.setLayoutY(prevY + radiusY);
			} else if (rand == 3) {
				this.mode = Mode.F2_DOWN;
				this.setLayoutX(prevX);
				this.setLayoutY(prevY + radiusY * 2);
			}
			break;
		case F3_UP:
			if (rand == 0) {
				this.mode = Mode.F1_LEFT_UP;
				this.setLayoutX(prevX - radiusX / 2);
				this.setLayoutY(prevY - radiusY * 1.5);
			} else if (rand == 1) {
				this.mode = Mode.F3_UP;
				this.setLayoutX(prevX);
				this.setLayoutY(prevY - radiusY * 2);
			} else if (rand == 2) {
				this.mode = Mode.F3_LEFT_DOWN;
				this.setLayoutX(prevX - radiusX);
				this.setLayoutY(prevY + radiusY);
			} else if (rand == 3) {
				this.mode = Mode.F3_RIGHT_UP;
				this.setLayoutX(prevX + radiusX);
				this.setLayoutY(prevY - radiusY);
			}
			break;
		case F3_DOWN:
			if (rand == 0) {
				this.mode = Mode.F1_RIGHT_DOWN;
				this.setLayoutX(prevX + radiusX / 2);
				this.setLayoutY(prevY + radiusY * 1.5);
			} else if (rand == 1) {
				this.mode = Mode.F3_DOWN;
				this.setLayoutX(prevX);
				this.setLayoutY(prevY + radiusY * 2);
			} else if (rand == 2) {
				this.mode = Mode.F3_LEFT_DOWN;
				this.setLayoutX(prevX - radiusX);
				this.setLayoutY(prevY + radiusY);
			} else if (rand == 3) {
				this.mode = Mode.F3_RIGHT_UP;
				this.setLayoutX(prevX + radiusX);
				this.setLayoutY(prevY - radiusY);
			}
			break;
		case F3_LEFT_DOWN:
			if (rand == 0) {
				this.mode = Mode.F3_UP;
				this.setLayoutX(prevX);
				this.setLayoutY(prevY - radiusY * 2);
			} else if (rand == 1) {
				this.mode = Mode.F3_DOWN;
				this.setLayoutX(prevX);
				this.setLayoutY(prevY + radiusY * 2);
			} else if (rand == 2) {
				this.mode = Mode.F3_LEFT_DOWN;
				this.setLayoutX(prevX - radiusX);
				this.setLayoutY(prevY + radiusY);
			} else if (rand == 3) {
				this.mode = Mode.F2_LEFT_UP;
				this.setLayoutX(prevX - radiusX);
				this.setLayoutY(prevY);
			}
			break;
		case F3_RIGHT_UP:
			if (rand == 0) {
				this.mode = Mode.F3_UP;
				this.setLayoutX(prevX);
				this.setLayoutY(prevY - radiusY * 2);
			} else if (rand == 1) {
				this.mode = Mode.F3_DOWN;
				this.setLayoutX(prevX);
				this.setLayoutY(prevY + radiusY * 2);
			} else if (rand == 2) {
				this.mode = Mode.F3_RIGHT_UP;
				this.setLayoutX(prevX + radiusX);
				this.setLayoutY(prevY - radiusY);
			} else if (rand == 3) {
				this.mode = Mode.F2_RIGHT_DOWN;
				this.setLayoutX(prevX + radiusX);
				this.setLayoutY(prevY);
			}
			break;
		default:
			System.out.println("Set Property Error!");
		}

		switch (this.mode) {
		case F1_LEFT_DOWN:
		case F1_LEFT_UP:
		case F1_RIGHT_DOWN:
		case F1_RIGHT_UP:
			this.mainMode = MainMode.F1;
			break;
		case F2_DOWN:
		case F2_LEFT_UP:
		case F2_RIGHT_DOWN:
		case F2_UP:
			this.mainMode = MainMode.F2;
			break;
		case F3_DOWN:
		case F3_LEFT_DOWN:
		case F3_RIGHT_UP:
		case F3_UP:
			this.mainMode = MainMode.F3;
			break;
		}

		switch (this.mode) {
		case F1_LEFT_UP:
		case F2_LEFT_UP:
			this.direct = Direct.LEFT_UP;
			break;
		case F1_LEFT_DOWN:
		case F3_LEFT_DOWN:
			this.direct = Direct.LEFT_DOWN;
			break;
		case F1_RIGHT_UP:
		case F3_RIGHT_UP:
			this.direct = Direct.RIGHT_UP;
			break;
		case F1_RIGHT_DOWN:
		case F2_RIGHT_DOWN:
			this.direct = Direct.RIGHT_DOWN;
			break;
		case F2_DOWN:
		case F3_DOWN:
			this.direct = Direct.DOWN;
			break;
		case F2_UP:
		case F3_UP:
			this.direct = Direct.UP;
			break;
		}
	}

}
