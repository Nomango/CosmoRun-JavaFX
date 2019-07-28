package org.werelone.cosmorun.view;

import org.werelone.cosmorun.ui.Shadow;
import org.werelone.cosmorun.ui.Text;
import org.werelone.cosmorun.ui.Text.TextFont;
import org.werelone.cosmorun.ui.buttons.AboutButton;
import org.werelone.cosmorun.ui.buttons.OptionButton;
import org.werelone.cosmorun.ui.buttons.PlayButton;
import org.werelone.cosmorun.util.GameData;
import org.werelone.cosmorun.util.Language;
import org.werelone.cosmorun.util.Property;
import org.werelone.cosmorun.view.RootLayout.FrontPane;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class StartMenuPane extends Pane{
	// ��ʼ�˵��ĵ���
	private static StartMenuPane pane;
	// ��Ϸ����
	private ImageView menuTitle;
	// ���ı���
	private Text menuTitle_cn;
	// ��߷�
	private Text bestScoreText;
	// ������߷�
	//private Text bestScoreText_cn;
	// ��ʼ��ť
	private PlayButton btnPlay;
	// ���ڰ�ť
	private AboutButton btnAbout;
	// ���ڰ�ť
	private OptionButton btnOption;
	
	public static StartMenuPane create() {
		pane = new StartMenuPane();
		pane.init();
		return pane;
	}
	
	public static StartMenuPane getInstance() {
		return pane;
	}
	
	private void init() {
		this.setMinSize(GameData.getWidth(), GameData.getHeight());
		// ��Ϸ����
		menuTitle = new ImageView("resources/title.png");
		menuTitle.setLayoutX(Property.get("startmenu-title-x"));
		menuTitle.setLayoutY(Property.get("startmenu-title-y"));
		menuTitle.setEffect(Shadow.create());
		// ��������
		if (GameData.getResolution() == 0) {
			menuTitle.setScaleX(0.66);
			menuTitle.setScaleY(0.66);
		}
		this.getChildren().add(menuTitle);
		// ���ı���
		menuTitle_cn = new Text(Language.getText("cn-startmenu-title"), Property.get("startmenu-title-cn-x"), Property.get("startmenu-title-cn-y"), TextFont.MsYaHei, 100, true);
		this.getChildren().add(menuTitle_cn);
		// ��ʼ����߷�
		bestScoreText = new Text("", Property.get("startmenu-bestscoretext-x"), Property.get("startmenu-bestscoretext-y"), TextFont.GillSans, 65, true);
		this.flushBestScore();
		this.getChildren().add(bestScoreText);
		// ���ո�ʼ��Ϸ
		this.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.SPACE) {
				if(RootLayout.changeFrontPane(FrontPane.touch)) {
					GamePane.getInstance().play();
				}
			}
		});
		// ��ʼ����ʼ��ť
		btnPlay = new PlayButton();
		btnPlay.setLayoutX(GameData.getWidth() / 2);
		btnPlay.setLayoutY(GameData.getHeight() / 1.3);
		// �����ʼ��ť���ز˵����沢��ʼ��Ϸ
		btnPlay.setOnMouseClicked(e -> {
			if(RootLayout.changeFrontPane(FrontPane.touch)) {
				GamePane.getInstance().play();
			}
		});
		this.getChildren().add(btnPlay);
		// ��ʼ�����ڰ�ť
		btnAbout = new AboutButton();
		btnAbout.setLayoutX(Property.get("startmenu-aboutbtn-x"));
		btnAbout.setLayoutY(Property.get("startmenu-aboutbtn-y"));
		btnAbout.setOnMouseClicked(e -> RootLayout.changeFrontPane(FrontPane.about));
		this.getChildren().add(btnAbout);
		// ��ʼ�����ð�ť
		btnOption = new OptionButton();
		btnOption.setLayoutX(Property.get("startmenu-optionbtn-x"));
		btnOption.setLayoutY(Property.get("startmenu-optionbtn-y"));
		btnOption.setOnMouseClicked(e -> RootLayout.changeFrontPane(FrontPane.option));
		this.getChildren().add(btnOption);
		
		// �л�����
		changeLang();
	}
	
	// ˢ����߷�
	public void flushBestScore() {
		if (GameData.getBestScore() != 0) {
			bestScoreText.setText(String.format("%s %3d", Language.getAutoText("startmenu-best"), GameData.getBestScore()));
		}
	}
	
	// �л�����
	public static void changeLang() {
		if (GameData.language == 0) {
			pane.bestScoreText.setFont(TextFont.GillSans, 65, true);
			pane.menuTitle_cn.setOpacity(0);
			pane.menuTitle.setOpacity(1);
		}
		else {
			pane.bestScoreText.setFont(TextFont.MsYaHei, 40, false);
			pane.menuTitle_cn.setOpacity(1);
			pane.menuTitle.setOpacity(0);
		}
		pane.flushBestScore();
		pane.btnPlay.changeLang();
	}

}
