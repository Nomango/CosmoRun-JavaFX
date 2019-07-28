package game_800x600.object;

import game_800x600.object.Ball.Direct;
import game_800x600.object.FloorManager.MainMode;
import game_800x600.object.FloorManager.Mode;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Polygon;

public class Floor extends Polygon {
	public Mode mode;			// 板块主类型
	public MainMode mainMode;	// 板块类型
	public Direct direct;		// 板块方向（方便修改小球方向）
	public Polygon shadow = new Polygon();	// 阴影
	public double[] range;		// 用于判断板块范围的坐标数组
	private DoubleProperty x = new SimpleDoubleProperty(0);
	private DoubleProperty y = new SimpleDoubleProperty(0);
	

	public Floor(Mode mode, MainMode mainMode, Direct direct, double x, double y) {
		this.mode = mode;
		this.mainMode = mainMode;
		this.direct = direct;
		this.setCenter(x, y);
		
		// 布局中心绑定在 (x, y)
		this.layoutXProperty().bind(this.x);
		this.layoutYProperty().bind(this.y);
	}
	
	/*
	 * 设置板块中心坐标
	 */
	public void setCenter(double x, double y) {
		this.x.set(x);
		this.y.set(y);
	}
	
	/*
	 * 得到板块中心 X 坐标
	 */
	public double getX() {
		return this.x.doubleValue();
	}
	
	/*
	 * 得到板块中心 Y 坐标
	 */
	public double getY() {
		return this.y.doubleValue();
	}
	
	/*
	 * 判断点(x, y)是否在板块内（非 Javadoc）
	 * @see javafx.scene.Node#contains(double, double)
	 */
	@Override
	public boolean contains(double x, double y) {
		return super.contains(x - this.x.get(), y - this.y.get());
	}
	
	/*
	 * 判断板块位置是否和其他板块冲突
	 */
	public boolean isCollisionWith(Floor f) {
		for (int i = 0; i < 16; i += 2) {
			if (this.contains(f.x.get() + f.range[i], f.y.get() + f.range[i + 1])) {
				return true;
			}
		}
		return false;
	}

}
