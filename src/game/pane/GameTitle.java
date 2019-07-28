package game.pane;

import game.Game;
import game.animation.Show;
import game.music.Music;
import game.pane.background.Background;
import game.pane.howtoplay.HowToPlay;
import game.pane.menu.Menu;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class GameTitle extends Pane{
	private Timeline tl = new Timeline();
	private Pane pane = new Pane();
	private Circle light = new Circle();
	
	public GameTitle() {
		this.init();
		this.play();
	}
	
	private void init() {
		Rectangle background = new Rectangle(Game.width, Game.height);
		background.setFill(Color.BLACK);
		this.getChildren().add(background);
		
		light.setFill(Color.WHITE);
		light.setCenterX(Game.width / 2);
		light.setCenterY(Game.height / 2);
		light.setRadius(120);
		light.setEffect(new BoxBlur(70, 70, 70));
		light.setOpacity(0.9);
		pane.getChildren().add(light);
		
		Polygon p = new Polygon();
		for (int i = 0; i < 8; i++) {
			double cos = Math.cos(Math.toRadians(i * 45));
			double sin = Math.sin(Math.toRadians(i * 45));
			p.getPoints().addAll(
					108 * cos + 33 * sin, 108 * -sin + 33 * cos,
					150 * cos + 24 * sin, 150 * -sin + 24 * cos,
					150 * cos - 24 * sin, 150 * -sin - 24 * cos,
					108 * cos - 33 * sin, 18 * -sin - 33 * cos
					);
		}
		p.setEffect(new BoxBlur(3, 3, 6));
		p.setFill(Color.WHITE);
		p.setLayoutX(Game.width / 2);
		p.setLayoutY(Game.height / 2);
		p.setOpacity(0.95);
		pane.getChildren().add(p);
		
		Text text = new Text("��������\n   ����");
		text.setFont(Font.font("΢���ź�", FontWeight.BLACK, 40));
		text.setFill(Color.WHITE);
		DropShadow dropShadow = new DropShadow(3, 2, 2, Color.BLACK);
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		text.setEffect(dropShadow);
		text.setLayoutX(Game.width / 2 - 80);
		text.setLayoutY(Game.height / 2 - 10);
		pane.getChildren().add(text);
		pane.setOpacity(0.0);
		
		this.getChildren().add(pane);
	}
	
	private void play() {
		for (int i = 0; i < 50; i++)
			tl.getKeyFrames().add(new KeyFrame(Duration.millis(500 + i * 20), e -> pane.setOpacity(pane.getOpacity() + 0.02)));
		
		for (int i = 0; i < 50; i++)
			tl.getKeyFrames().add(new KeyFrame(Duration.millis(1500 + i * 15) , e -> {
				light.setScaleX(light.getScaleX() - 0.002);
				light.setScaleY(light.getScaleY() - 0.002);
				light.setOpacity(light.getOpacity() - 0.02);
			}));
		
		for (int i = 0; i < 50; i++) 
			tl.getKeyFrames().add(new KeyFrame(Duration.millis(3500 + i * 15), e -> pane.setOpacity(pane.getOpacity() - 0.02)));
		
		tl.getKeyFrames().add(new KeyFrame(Duration.millis(4300)));
		
		tl.setCycleCount(1);
		tl.setOnFinished(e -> {
			// ������ʾ��������Ϸ���
			Show show = new Show(Background.pane);
			Show.setNode(Display.pane);
			// ���ű�������
			if (Game.soundSwitch)
				Music.playBkMusic();
			// ɾ��Ƭͷ��壬��ʼ����Ϸ
			show.setOnFinished(s -> {
				Game.pane.getChildren().remove(this);
				Display.initGame();
			});
			
			// ��һ����Ϸ��ʾ HowToPlay ��壬Ĭ�Ͻ��� Menu ���
			if (Game.firstPlay) {
				Game.showPane(HowToPlay.pane);
				HowToPlay.play();
				HowToPlay.status = true;
			} else {
				Game.showPane(Menu.pane);
				Menu.pane.requestFocus();
				Menu.status = true;
			}
		});
		tl.play();
	}
	
	/*	180 * cos + 55 * sin, 180 * -sin + 55 * cos,
		250 * cos + 40 * sin, 250 * -sin + 40 * cos,
		250 * cos - 40 * sin, 250 * -sin - 40 * cos,
		180 * cos - 55 * sin, 1 * -sin - 55 * cos*/

}
