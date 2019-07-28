package org.werelone.cosmorun.object;

import org.werelone.cosmorun.object.Ball.Direct;
import org.werelone.cosmorun.util.GameData;

import javafx.animation.ScaleTransition;
import javafx.scene.CacheHint;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.util.Duration;

public class FloorManager {
	public static enum MainMode {
		F1, F2, F3
	};

	public static enum Mode {
		F1_LEFT_UP, F1_LEFT_DOWN, F1_RIGHT_UP, F1_RIGHT_DOWN, F2_LEFT_UP, F2_DOWN, F2_UP, F2_RIGHT_DOWN, F3_UP, F3_RIGHT_UP, F3_LEFT_DOWN, F3_DOWN
	};

	private final static double[][] property = new double[][]{{30, 4, 1},{45, 6, 2}};
	public static double radiusY; // 短半轴长
	public static double radiusX;// 长半轴长
	public static double ex; 	// 用于判断范围的标准量

	private static Mode mode;
	private static MainMode mainMode;
	private static Direct direct;
	private static double x, y, strokeWidth;
	
	public static void init() {
		radiusY = property[GameData.getResolution()][0];
		radiusX = radiusY * 1.73;
		ex = property[GameData.getResolution()][1];
		strokeWidth = property[GameData.getResolution()][2];
	}

	public static Floor createHeadFloor() {
		// 随机头板块模式
		randomFirstMode();
		// 创建新板块
		Floor f = new Floor(mode, mainMode, direct, x, y);
		// 设置板块坐标
		setCoordinate(f);
		// 设置板块颜色
		setColor(f);
		return f;
	}
	
	public static Floor createBeginFloor(Floor headFloor) {
		// 初始化板块模式
		initBeginMode(headFloor);
		// 创建新板块
		Floor f = new Floor(mode, mainMode, direct, x, y);
		// 设置板块坐标
		setCoordinate(f);
		// 设置板块颜色
		setColor(f);
		return f;
	}

	public static Floor createNewFloor(Floor prevFloor) {
		// 随机板块模式
		randomMode(prevFloor);
		// 创建新板块
		Floor f = new Floor(mode, mainMode, direct, x, y);
		// 设置板块坐标
		setCoordinate(f);
		// 设置板块颜色
		setColor(f);
		return f;
	}
	
	public static void show(Floor f, int millis) {
		f.shadow.setScaleX(0.0);
		f.shadow.setScaleY(0.0);
		ScaleTransition st = new ScaleTransition(Duration.millis(millis * 2), f.shadow);
		st.setByX(1.0f);
		st.setByY(1.0f);
		st.play();
		
		f.setScaleX(0.0);
		f.setScaleY(0.0);
		st = new ScaleTransition(Duration.millis(millis), f);
		st.setByX(1.0f);
		st.setByY(1.0f);
		st.play();
		
		Floors.add(f);	// 在界面中显示板块
	}
	
	public static void hide(Floor f, int millis) {
		ScaleTransition hide = new ScaleTransition(Duration.millis(millis * 0.5), f.shadow);
		hide.setByX(-1.0f);
		hide.setByY(-1.0f);
		hide.play();
		
		hide = new ScaleTransition(Duration.millis(millis), f);
		hide.setByX(-1.0f);
		hide.setByY(-1.0f);
		hide.setOnFinished(e -> Floors.remove(f));
		hide.play();
	}

	private static void randomFirstMode() {
		// 设置形状中心
		x = GameData.getWidth() / 2 + Floors.moveX;
		y = GameData.getHeight() / 2 + Floors.moveY;
		
		mainMode = MainMode.F1;
		// 随机板块模式
		switch ((int) (Math.random() * 4)) {
		case 0:
			direct = Direct.LEFT_DOWN;
			mode = Mode.F1_LEFT_DOWN;
			break;
		case 1:
			direct = Direct.LEFT_UP;
			mode = Mode.F1_LEFT_UP;
			break;
		case 2:
			direct = Direct.RIGHT_DOWN;
			mode = Mode.F1_RIGHT_DOWN;
			break;
		case 3:
			direct = Direct.RIGHT_UP;
			mode = Mode.F1_RIGHT_UP;
			break;
		}
	}
	
	private static void initBeginMode(Floor headFloor) {
		mainMode = MainMode.F1;
		switch (headFloor.mode) {
		case F1_LEFT_UP:
			mode = Mode.F1_LEFT_UP;
			x = headFloor.getX() - radiusX;
			y = headFloor.getY() - radiusY;
			direct = Direct.LEFT_UP;
			break;
		case F1_LEFT_DOWN:
			mode = Mode.F1_LEFT_DOWN;
			x = headFloor.getX() - radiusX;
			y = headFloor.getY() + radiusY;
			direct = Direct.LEFT_DOWN;
			break;
		case F1_RIGHT_DOWN:
			mode = Mode.F1_RIGHT_DOWN;
			x = headFloor.getX() + radiusX;
			y = headFloor.getY() + radiusY;
			direct = Direct.RIGHT_DOWN;
			break;
		case F1_RIGHT_UP:
			mode = Mode.F1_RIGHT_UP;
			x = headFloor.getX() + radiusX;
			y = headFloor.getY() - radiusY;
			direct = Direct.RIGHT_UP;
			break;
		default:
			System.out.println(1);
		}
	}

	private static void randomMode(Floor prevFloor) {
		// 随机板块模式
		int rand = (int) (Math.random() * 4);

		switch (prevFloor.mode) {
		case F1_LEFT_UP:
			if (rand == 0) {
				mode = Mode.F1_LEFT_UP;
				x = prevFloor.getX() - radiusX;
				y = prevFloor.getY() - radiusY;
			} else if (rand == 1) {
				mode = Mode.F1_LEFT_DOWN;
				x = prevFloor.getX() - radiusX;
				y = prevFloor.getY() + radiusY;
			} else if (rand == 2) {
				mode = Mode.F1_RIGHT_UP;
				x = prevFloor.getX() + radiusX;
				y = prevFloor.getY() - radiusY;
			} else if (rand == 3) {
				mode = Mode.F3_UP;
				x = prevFloor.getX() - radiusX / 2;
				y = prevFloor.getY() - radiusY * 1.5;
			}
			break;
		case F1_LEFT_DOWN:
			if (rand == 0) {
				mode = Mode.F1_RIGHT_DOWN;
				x = prevFloor.getX() + radiusX;
				y = prevFloor.getY() + radiusY;
			} else if (rand == 1) {
				mode = Mode.F1_LEFT_DOWN;
				x = prevFloor.getX() - radiusX;
				y = prevFloor.getY() + radiusY;
			} else if (rand == 2) {
				mode = Mode.F1_LEFT_UP;
				x = prevFloor.getX() - radiusX;
				y = prevFloor.getY() - radiusY;
			} else if (rand == 3) {
				mode = Mode.F2_DOWN;
				x = prevFloor.getX() - radiusX / 2;
				y = prevFloor.getY() + radiusY * 1.5;
			}
			break;
		case F1_RIGHT_DOWN:
			if (rand == 0) {
				mode = Mode.F1_RIGHT_DOWN;
				x = prevFloor.getX() + radiusX;
				y = prevFloor.getY() + radiusY;
			} else if (rand == 1) {
				mode = Mode.F1_LEFT_DOWN;
				x = prevFloor.getX() - radiusX;
				y = prevFloor.getY() + radiusY;
			} else if (rand == 2) {
				mode = Mode.F1_RIGHT_UP;
				x = prevFloor.getX() + radiusX;
				y = prevFloor.getY() - radiusY;
			} else if (rand == 3) {
				mode = Mode.F3_DOWN;
				x = prevFloor.getX() + radiusX / 2;
				y = prevFloor.getY() + radiusY * 1.5;
			}
			break;
		case F1_RIGHT_UP:
			if (rand == 0) {
				mode = Mode.F1_RIGHT_DOWN;
				x = prevFloor.getX() + radiusX;
				y = prevFloor.getY() + radiusY;
			} else if (rand == 1) {
				mode = Mode.F1_RIGHT_UP;
				x = prevFloor.getX() + radiusX;
				y = prevFloor.getY() - radiusY;
			} else if (rand == 2) {
				mode = Mode.F1_LEFT_UP;
				x = prevFloor.getX() - radiusX;
				y = prevFloor.getY() - radiusY;
			} else if (rand == 3) {
				mode = Mode.F2_UP;
				x = prevFloor.getX() + radiusX / 2;
				y = prevFloor.getY() - radiusY * 1.5;
			}
			break;
		case F2_UP:
			if (rand == 0) {
				mode = Mode.F1_RIGHT_UP;
				x = prevFloor.getX() + radiusX / 2;
				y = prevFloor.getY() - radiusY * 1.5;
			} else if (rand == 1) {
				mode = Mode.F2_UP;
				x = prevFloor.getX();
				y = prevFloor.getY() - radiusY * 2;
			} else if (rand == 2) {
				mode = Mode.F2_LEFT_UP;
				x = prevFloor.getX() - radiusX;
				y = prevFloor.getY() - radiusY;
			} else if (rand == 3) {
				mode = Mode.F2_RIGHT_DOWN;
				x = prevFloor.getX() + radiusX;
				y = prevFloor.getY() + radiusY;
			}
			break;
		case F2_DOWN:
			if (rand == 0) {
				mode = Mode.F1_LEFT_DOWN;
				x = prevFloor.getX() - radiusX / 2;
				y = prevFloor.getY() + radiusY * 1.5;
			} else if (rand == 1) {
				mode = Mode.F2_DOWN;
				x = prevFloor.getX();
				y = prevFloor.getY() + radiusY * 2;
			} else if (rand == 2) {
				mode = Mode.F2_LEFT_UP;
				x = prevFloor.getX() - radiusX;
				y = prevFloor.getY() - radiusY;
			} else if (rand == 3) {
				mode = Mode.F2_RIGHT_DOWN;
				x = prevFloor.getX() + radiusX;
				y = prevFloor.getY() + radiusY;
			}
			break;
		case F2_LEFT_UP:
			if (rand == 0) {
				mode = Mode.F3_LEFT_DOWN;
				x = prevFloor.getX() - radiusX;
				y = prevFloor.getY();
			} else if (rand == 1) {
				mode = Mode.F2_UP;
				x = prevFloor.getX();
				y = prevFloor.getY() - radiusY * 2;
			} else if (rand == 2) {
				mode = Mode.F2_LEFT_UP;
				x = prevFloor.getX() - radiusX;
				y = prevFloor.getY() - radiusY;
			} else if (rand == 3) {
				mode = Mode.F2_DOWN;
				x = prevFloor.getX();
				y = prevFloor.getY() + radiusY * 2;
			}
			break;
		case F2_RIGHT_DOWN:
			if (rand == 0) {
				mode = Mode.F3_RIGHT_UP;
				x = prevFloor.getX() + radiusX;
				y = prevFloor.getY();
			} else if (rand == 1) {
				mode = Mode.F2_UP;
				x = prevFloor.getX();
				y = prevFloor.getY() - radiusY * 2;
			} else if (rand == 2) {
				mode = Mode.F2_RIGHT_DOWN;
				x = prevFloor.getX() + radiusX;
				y = prevFloor.getY() + radiusY;
			} else if (rand == 3) {
				mode = Mode.F2_DOWN;
				x = prevFloor.getX();
				y = prevFloor.getY() + radiusY * 2;
			}
			break;
		case F3_UP:
			if (rand == 0) {
				mode = Mode.F1_LEFT_UP;
				x = prevFloor.getX() - radiusX / 2;
				y = prevFloor.getY() - radiusY * 1.5;
			} else if (rand == 1) {
				mode = Mode.F3_UP;
				x = prevFloor.getX();
				y = prevFloor.getY() - radiusY * 2;
			} else if (rand == 2) {
				mode = Mode.F3_LEFT_DOWN;
				x = prevFloor.getX() - radiusX;
				y = prevFloor.getY() + radiusY;
			} else if (rand == 3) {
				mode = Mode.F3_RIGHT_UP;
				x = prevFloor.getX() + radiusX;
				y = prevFloor.getY() - radiusY;
			}
			break;
		case F3_DOWN:
			if (rand == 0) {
				mode = Mode.F1_RIGHT_DOWN;
				x = prevFloor.getX() + radiusX / 2;
				y = prevFloor.getY() + radiusY * 1.5;
			} else if (rand == 1) {
				mode = Mode.F3_DOWN;
				x = prevFloor.getX();
				y = prevFloor.getY() + radiusY * 2;
			} else if (rand == 2) {
				mode = Mode.F3_LEFT_DOWN;
				x = prevFloor.getX() - radiusX;
				y = prevFloor.getY() + radiusY;
			} else if (rand == 3) {
				mode = Mode.F3_RIGHT_UP;
				x = prevFloor.getX() + radiusX;
				y = prevFloor.getY() - radiusY;
			}
			break;
		case F3_LEFT_DOWN:
			if (rand == 0) {
				mode = Mode.F3_UP;
				x = prevFloor.getX();
				y = prevFloor.getY() - radiusY * 2;
			} else if (rand == 1) {
				mode = Mode.F3_DOWN;
				x = prevFloor.getX();
				y = prevFloor.getY() + radiusY * 2;
			} else if (rand == 2) {
				mode = Mode.F3_LEFT_DOWN;
				x = prevFloor.getX() - radiusX;
				y = prevFloor.getY() + radiusY;
			} else if (rand == 3) {
				mode = Mode.F2_LEFT_UP;
				x = prevFloor.getX() - radiusX;
				y = prevFloor.getY();
			}
			break;
		case F3_RIGHT_UP:
			if (rand == 0) {
				mode = Mode.F3_UP;
				x = prevFloor.getX();
				y = prevFloor.getY() - radiusY * 2;
			} else if (rand == 1) {
				mode = Mode.F3_DOWN;
				x = prevFloor.getX();
				y = prevFloor.getY() + radiusY * 2;
			} else if (rand == 2) {
				mode = Mode.F3_RIGHT_UP;
				x = prevFloor.getX() + radiusX;
				y = prevFloor.getY() - radiusY;
			} else if (rand == 3) {
				mode = Mode.F2_RIGHT_DOWN;
				x = prevFloor.getX() + radiusX;
				y = prevFloor.getY();
			}
			break;
		}

		switch (mode) {
		case F1_LEFT_DOWN:
		case F1_LEFT_UP:
		case F1_RIGHT_DOWN:
		case F1_RIGHT_UP:
			mainMode = MainMode.F1;
			break;
		case F2_DOWN:
		case F2_LEFT_UP:
		case F2_RIGHT_DOWN:
		case F2_UP:
			mainMode = MainMode.F2;
			break;
		case F3_DOWN:
		case F3_LEFT_DOWN:
		case F3_RIGHT_UP:
		case F3_UP:
			mainMode = MainMode.F3;
			break;
		}

		switch (mode) {
		case F1_LEFT_UP:
		case F2_LEFT_UP:
			direct = Direct.LEFT_UP;
			break;
		case F1_LEFT_DOWN:
		case F3_LEFT_DOWN:
			direct = Direct.LEFT_DOWN;
			break;
		case F1_RIGHT_UP:
		case F3_RIGHT_UP:
			direct = Direct.RIGHT_UP;
			break;
		case F1_RIGHT_DOWN:
		case F2_RIGHT_DOWN:
			direct = Direct.RIGHT_DOWN;
			break;
		case F2_DOWN:
		case F3_DOWN:
			direct = Direct.DOWN;
			break;
		case F2_UP:
		case F3_UP:
			direct = Direct.UP;
			break;
		}
	}

	private static void setCoordinate(Floor newFloor) {
		// 根据板块类型创建形状坐标
		switch (newFloor.mainMode) {
		case F1:
			newFloor.getPoints().addAll(0.0, -radiusY, radiusX, 0.0, 0.0, radiusY, -radiusX, 0.0);
			newFloor.shadow.getPoints().addAll(0.0, -radiusY, radiusX, 0.0, 0.0, radiusY, -radiusX, 0.0);
			newFloor.range = new double[]{0.0, -radiusY - ex, radiusX / 2 + 1.732 * ex / 2, -radiusY / 2 - ex / 2,
					radiusX + ex * 1.732, 0.0, radiusX / 2 + 1.732 * ex / 2, radiusY / 2 + ex / 2, 0.0, radiusY + ex,
					-radiusX / 2 - 1.732 * ex / 2, radiusY / 2 + ex / 2, -radiusX - ex * 1.732, 0.0,
					-radiusX / 2 - 1.732 * ex / 2, -radiusY / 2 - ex / 2};
			break;
		case F2:
			newFloor.getPoints().addAll(-radiusX / 2, -radiusY * 1.5, radiusX / 2, -radiusY * 0.5, radiusX / 2,
					radiusY * 1.5, -radiusX / 2, radiusY * 0.5);
			newFloor.shadow.getPoints().addAll(-radiusX / 2, -radiusY * 1.5, radiusX / 2, -radiusY * 0.5, radiusX / 2,
					radiusY * 1.5, -radiusX / 2, radiusY * 0.5);
			newFloor.range = new double[]{-(radiusX + 1.732 * ex) / 2, -(radiusY + ex) * 1.5, 0.0, -(radiusY + ex),
					(radiusX + 1.732 * ex) / 2, -(radiusY + ex) * 0.5, (radiusX + 1.732 * ex) / 2, 0.0,
					(radiusX + 1.732 * ex) / 2, (radiusY + ex) * 1.5, 0.0, (radiusY + ex), -(radiusX + 1.732 * ex) / 2,
					(radiusY + ex) * 0.5, -(radiusX + 1.732 * ex) / 2, 0.0};
			break;
		case F3:
			newFloor.getPoints().addAll(-radiusX / 2, -radiusY * 0.5, radiusX / 2, -radiusY * 1.5, radiusX / 2,
					radiusY * 0.5, -radiusX / 2, radiusY * 1.5);
			newFloor.shadow.getPoints().addAll(-radiusX / 2, -radiusY * 0.5, radiusX / 2, -radiusY * 1.5, radiusX / 2,
					radiusY * 0.5, -radiusX / 2, radiusY * 1.5);
			newFloor.range = new double[]{-(radiusX + 1.732 * ex) / 2, -(radiusY + ex) * 0.5, 0.0, -(radiusY + ex),
					(radiusX + 1.732 * ex) / 2, -(radiusY + ex) * 1.5, (radiusX + 1.732 * ex) / 2, 0.0,
					(radiusX + 1.732 * ex) / 2, (radiusY + ex) * 0.5, 0.0, (radiusY + ex), -(radiusX + 1.732 * ex) / 2,
					(radiusY + ex) * 1.5, -(radiusX + 1.732 * ex) / 2, 0.0};
			break;
		}
		
		newFloor.shadow.layoutXProperty().bind(newFloor.layoutXProperty());
		newFloor.shadow.layoutYProperty().bind(newFloor.layoutYProperty().add(10));
	}

	private static void setColor(Floor f) {
		// 质量绘图
		f.setCache(true);
		f.setCacheHint(CacheHint.QUALITY);
		// 形状线宽
		f.setStrokeWidth(strokeWidth);
		// 设置颜色
		FloorManager.setColor(f, GameData.bgMode);
		// 提高动画流畅性
		f.setCacheHint(CacheHint.SPEED);

		f.shadow.setCache(true);
		f.shadow.setCacheHint(CacheHint.QUALITY);
		f.shadow.setFill(Color.hsb(0, 0.0, 0.1, 0.7));
		f.shadow.setEffect(new BoxBlur(4, 4, 4));
		f.shadow.setCacheHint(CacheHint.SPEED);
	}

	public static void setColor(Floor f, int mode) {
		// 根据板块类型设置颜色
		switch (mode) {
		case 0:
			f.setStroke(Color.rgb(0, 140, 70));
			switch (f.mode) {
			case F1_LEFT_UP:
				// 线性梯度渐变色
				f.setFill(new LinearGradient(0.33, 0.0, 0.66, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(0, Color.rgb(0, 241, 128)), new Stop(1, Color.rgb(0, 210, 100))));
				break;
			case F1_LEFT_DOWN:
				f.setFill(new LinearGradient(0.66, 0.0, 0.33, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(1, Color.rgb(0, 210, 100)), new Stop(0, Color.rgb(0, 241, 128))));
				break;
			case F1_RIGHT_DOWN:
				f.setFill(new LinearGradient(0.33, 0.0, 0.66, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(0, Color.rgb(0, 210, 100)), new Stop(1, Color.rgb(0, 241, 128))));
				break;
			case F1_RIGHT_UP:
				f.setFill(new LinearGradient(0.66, 0.0, 0.33, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(1, Color.rgb(0, 241, 128)), new Stop(0, Color.rgb(0, 210, 100))));
				break;
			case F2_DOWN:
			case F2_LEFT_UP:
			case F2_RIGHT_DOWN:
			case F2_UP:
				f.setFill(Color.rgb(0, 160, 83));
				break;
			case F3_DOWN:
			case F3_LEFT_DOWN:
			case F3_RIGHT_UP:
			case F3_UP:
				f.setStroke(Color.rgb(0, 91, 46));
				f.setFill(Color.rgb(0, 121, 62));
				break;
			}
			break;
		case 1:
			f.setStroke(Color.rgb(0, 114, 181));
			switch (f.mode) {
			case F1_LEFT_UP:
				// 线性梯度渐变色
				f.setFill(new LinearGradient(0.33, 0.0, 0.66, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(0, Color.rgb(0, 190, 254)), new Stop(1, Color.rgb(0, 160, 235))));
				break;
			case F1_LEFT_DOWN:
				f.setFill(new LinearGradient(0.66, 0.0, 0.33, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(1, Color.rgb(0, 160, 235)), new Stop(0, Color.rgb(0, 190, 254))));
				break;
			case F1_RIGHT_DOWN:
				f.setFill(new LinearGradient(0.33, 0.0, 0.66, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(0, Color.rgb(0, 160, 235)), new Stop(1, Color.rgb(0, 190, 254))));
				break;
			case F1_RIGHT_UP:
				f.setFill(new LinearGradient(0.66, 0.0, 0.33, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(1, Color.rgb(0, 190, 254)), new Stop(0, Color.rgb(0, 160, 235))));
				break;
			case F2_DOWN:
			case F2_LEFT_UP:
			case F2_RIGHT_DOWN:
			case F2_UP:
				f.setFill(Color.rgb(56, 134, 195));
				break;
			case F3_DOWN:
			case F3_LEFT_DOWN:
			case F3_RIGHT_UP:
			case F3_UP:
				f.setStroke(Color.rgb(0, 69, 109));
				f.setFill(Color.rgb(0, 91, 144));
				break;
			}
			break;
		case 2:
			f.setStroke(Color.rgb(173, 96, 33));
			switch (f.mode) {
			case F1_LEFT_UP:
				// 线性梯度渐变色
				f.setFill(new LinearGradient(0.33, 0.0, 0.66, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(0, Color.rgb(253, 161, 70)), new Stop(1, Color.rgb(220, 133, 50))));
				break;
			case F1_LEFT_DOWN:
				f.setFill(new LinearGradient(0.66, 0.0, 0.33, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(1, Color.rgb(220, 133, 50)), new Stop(0, Color.rgb(253, 161, 70))));
				break;
			case F1_RIGHT_DOWN:
				f.setFill(new LinearGradient(0.33, 0.0, 0.66, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(0, Color.rgb(220, 133, 50)), new Stop(1, Color.rgb(253, 161, 70))));
				break;
			case F1_RIGHT_UP:
				f.setFill(new LinearGradient(0.66, 0.0, 0.33, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(1, Color.rgb(253, 161, 70)), new Stop(0, Color.rgb(220, 133, 50))));
				break;
			case F2_DOWN:
			case F2_LEFT_UP:
			case F2_RIGHT_DOWN:
			case F2_UP:
				f.setFill(Color.rgb(186, 109, 46));
				break;
			case F3_DOWN:
			case F3_LEFT_DOWN:
			case F3_RIGHT_UP:
			case F3_UP:
				f.setStroke(Color.rgb(111, 65, 27));
				f.setFill(Color.rgb(151, 88, 37));
				break;
			}
			break;
		}
	}

}
