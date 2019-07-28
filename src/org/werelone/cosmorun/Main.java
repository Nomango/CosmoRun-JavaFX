package org.werelone.cosmorun;

import org.werelone.cosmorun.util.GameData;
import org.werelone.cosmorun.util.Music;
import org.werelone.cosmorun.util.SaveTool;
import org.werelone.cosmorun.view.RootLayout;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application{
	// ��Ϸ���ڵĵ���
	private static Stage stage;
	
	public static void main(String args[]) {
		// ��ʼ����Ϸ����
		GameData.init();
		// ������Ϸ
		Application.launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		// ���洰�ھ��
		Main.stage = stage;
		// ���ó���
		stage.setScene(getScene());
		// ���ô��ڴ�С
		stage.setWidth(GameData.getWidth());
		stage.setHeight(GameData.getHeight());
		// �̶����ڴ�С
		stage.setResizable(false);
		// ���ô���ͼ��
		stage.getIcons().add(new Image("resources/icon.png"));
		// ���ô�������
		stage.setTitle("��������");
		// �رմ���ʱ�Զ���������
		stage.setOnCloseRequest(e -> SaveTool.save());
		// �򿪴���
		stage.show();
	}
	
	@Override
	public void init() {
		// ��������
		if (!Font.getFontNames().contains("Gill Sans MT Condensed")) {
			Font.loadFont(Main.class.getResource("/resources/font/Gill-Sans-MT-Condensed.TTF").toExternalForm(), 10.0);
		}
		if (!Font.getFontNames().contains("Microsoft YaHei")) {
			Font.loadFont(Main.class.getResource("/resources/font/msyh.ttf").toExternalForm(), 10.0);
		}
	}
	
	private static Scene getScene() {
		// ������Ϸ����
		Pane pane = RootLayout.create();
		// ���ó���
		Scene scene = new Scene(pane, GameData.getWidth(), GameData.getHeight());
		scene.setFill(Color.BLACK);
		return scene;
	}
	
	// �л���Ϸ�ֱ���
	public static void changeResolution() {
		// �رյ�ǰ����
		stage.hide();
		// ֹͣ��������
		Music.stopBgMusic();
		// �޸���Ϸ�ֱ�������
		GameData.changeResolutionData();
		// ���ó���
		stage.setScene(getScene());
		// ���ô��ڴ�С
		stage.setWidth(GameData.getWidth());
		stage.setHeight(GameData.getHeight());
		stage.show();
	}

}
