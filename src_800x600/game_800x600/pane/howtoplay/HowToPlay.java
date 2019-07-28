package game_800x600.pane.howtoplay;

import game_800x600.Game;
import game_800x600.baseButton.CloseButton;
import game_800x600.pane.background.MenuBackground;
import game_800x600.pane.menu.Menu;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import util.animation.Fade;

public class HowToPlay {
	public static Pane pane = new Pane();
	public static boolean status = false;
	private static CloseButton closeButton = new CloseButton();
	private static Text howToPlayTitle = new Text("How To Play");
	private static Rectangle space = new Rectangle(140 * 0.667, 550 * 0.667, 500 * 0.667, 125 * 0.667);
	private static Hand hand = new Hand();
	private static Group light = new Group();
	private static Circle ball = new Circle(666, 466 - 36 * 4, 16);
	private static Polygon arrow = new Polygon();
	private static Text howToPlayText;
	private static Timeline tl;
	
	public static void load() {
		init();
	}
	
	private static void init() {
		pane.getChildren().add(new MenuBackground(Color.rgb(30, 56, 113, 0.8), 
				Color.rgb(32, 45, 98, 0.9)));
		
		closeButton.setLayoutX(110 * 0.667);
		closeButton.setLayoutY(110 * 0.667);
		closeButton.setOnMouseClicked(e -> {
			if (status) {
				status = false;
				Fade fade = new Fade(pane);
				fade.setOnFinished(f -> {
					Game.toFront(Menu.pane);
					Menu.pane.requestFocus();
					Menu.status = true;
				});
				tl.stop();
			}
		});
		pane.getChildren().add(closeButton);
		
		howToPlayTitle.setLayoutX(Game.width / 2 - 120);
		howToPlayTitle.setLayoutY(140 * 0.667);
		howToPlayTitle.setFill(Color.hsb(0, 0.0, 1.0, 0.90));
		howToPlayTitle.setFont(Font.font("Gill Sans MT Condensed", FontWeight.BOLD, 53));
		DropShadow dropShadow = new DropShadow(5, 3, 3, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		howToPlayTitle.setEffect(dropShadow);
		pane.getChildren().add(howToPlayTitle);
		
		howToPlayText = new Text("Press Space to make a turn");
		howToPlayText.setLayoutX(110 * 0.667);
		howToPlayText.setLayoutY(400 * 0.667);
		howToPlayText.setFill(Color.WHITE);
		howToPlayText.setFont(Font.font("Gill Sans MT Condensed", FontWeight.BOLD, 43));
		howToPlayText.setEffect(dropShadow);
		pane.getChildren().add(howToPlayText);
		
		space.setArcWidth(16);
		space.setArcHeight(16);
		space.setStroke(Color.hsb(0, 0.0, 1.0, 0.90));
		space.setStrokeWidth(2);
		space.setFill(null);
		space.setEffect(dropShadow);
		pane.getChildren().add(space);
		
		hand.setLayoutX(300);
		hand.setLayoutY(400);
		hand.setEffect(dropShadow);
		pane.getChildren().add(hand);
		
		Rectangle r1 = new Rectangle(350 * 0.667, 580 * 0.667, 10, 50 * 0.667);
		Rectangle r2 = new Rectangle(420 * 0.667, 530 * 0.667, 10, 50 * 0.667);
		Rectangle r3 = new Rectangle(500 * 0.667, 530 * 0.667, 10, 50 * 0.667);
		r1.setFill(Color.WHITE);
		r2.setFill(Color.WHITE);
		r3.setFill(Color.WHITE);
		r1.setRotate(-55);
		r2.setRotate(-20);
		r3.setRotate(20);
		light.setEffect(dropShadow);
		light.getChildren().addAll(r1, r2, r3);
		
		createSixFloor();
		
		ball.setFill(Color.WHITE);
		ball.setEffect(new BoxBlur(3, 3, 2));
		pane.getChildren().add(ball);
		
		arrow.getPoints().addAll(
				0.0, 0.0, 95.0 * 0.667, -55.0 * 0.667, 
				77.7 * 0.667, -65.0 * 0.667, 167.5 * 0.667, -82.5 * 0.667, 
				132.7 * 0.667, -32.2 * 0.667, 127.7 * 0.667 - 5 * 1.732 * 0.667, -36.2 * 0.667 - 5 * 0.667, 
				50.0 * 0.667, 0.0, 150.0 * 0.667, 57.7 * 0.667, 150.0 * 0.667 - 25 * 0.667, 57.7 * 0.667 + 25 / 1.73 * 0.667);
		arrow.setLayoutX(1000 * 0.667 - 210 * 0.667);
		arrow.setLayoutY(700 * 0.667 - 120 * 0.667);
		arrow.setFill(Color.hsb(0, 0.0, 1.0, 0.5));
		arrow.setEffect(dropShadow);
		pane.getChildren().add(arrow);
		
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
	}
	
	public static void play() {
		tl.play();
	}
	
	private static void createSixFloor() {
		pane.getChildren().add(new Floor(666, 466));
		pane.getChildren().add(new Floor(666 - 63, 466 - 36));
		pane.getChildren().add(new Floor(666 - 63 * 2, 466 - 36 * 2));
		pane.getChildren().add(new Floor(666 - 63, 466 - 36 * 3));
		pane.getChildren().add(new Floor(666, 466 - 36 * 4));
		pane.getChildren().add(new Floor(666 + 63, 466 - 36 * 5));
	}
	
	private static class Floor extends Polygon{
		private final double radiusY = 36;			 // ∂Ã∞Î÷·≥§
		private final double radiusX = radiusY * 1.732;// ≥§∞Î÷·≥§
		
		public Floor(double x, double y) {
			this.getPoints().addAll(0.0, -radiusY, radiusX, 0.0, 0.0, radiusY, -radiusX, 0.0);
			this.setFill(Color.rgb(65, 89, 173));
			this.setLayoutX(x);
			this.setLayoutY(y);
			this.setEffect(new BoxBlur(3, 3, 2));
		}
	}

	private static class Hand extends Group {
		private Polygon hand = new Polygon();
		private Rectangle r = new Rectangle(10 * 0.67, 140 * 0.67, 80 * 0.67, 20 * 0.67);
		
		public Hand() {
			hand.getPoints().addAll(0.0, 0.0, 20.0, 0.0, 20.0, 40.0, 66.6, 40.0, 66.6, 86.6, 0.0, 86.6);
			hand.setFill(Color.WHITE);
			r.setFill(Color.WHITE);
			this.getChildren().addAll(hand, r);
			this.setRotate(-20);
		}
	}

}
