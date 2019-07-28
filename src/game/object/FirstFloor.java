package game.object;

import game.Game;
import game.object.Ball.Direct;

public class FirstFloor extends Floor {

	public FirstFloor() {
		this.initProperty();
		this.setCoordinate();
		this.setColor();
		
	}

	// 初始化头板块属性
	private void initProperty() {
		// 设置形状中心
		this.setLayoutX(Game.width / 2);
		this.setLayoutY(Game.height / 2);

		// 随机板块模式
		switch ((int) (Math.random() * 4)) {
		case 0:
			this.direct = Direct.LEFT_DOWN;
			this.mode = Mode.F1_LEFT_DOWN;
			break;
		case 1:
			this.direct = Direct.LEFT_UP;
			this.mode = Mode.F1_LEFT_UP;
			break;
		case 2:
			this.direct = Direct.RIGHT_DOWN;
			this.mode = Mode.F1_RIGHT_DOWN;
			break;
		case 3:
			this.direct = Direct.RIGHT_UP;
			this.mode = Mode.F1_RIGHT_UP;
			break;
		}
		this.mainMode = MainMode.F1;

	}

}
