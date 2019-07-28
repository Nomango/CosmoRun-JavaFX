package org.werelone.cosmorun.view;

import org.werelone.cosmorun.Main;
import org.werelone.cosmorun.object.Floors;
import org.werelone.cosmorun.ui.MenuBackground;
import org.werelone.cosmorun.ui.Text;
import org.werelone.cosmorun.ui.Text.TextFont;
import org.werelone.cosmorun.ui.buttons.BgModeButton;
import org.werelone.cosmorun.ui.buttons.ChangeRsltButton;
import org.werelone.cosmorun.ui.buttons.CloseButton;
import org.werelone.cosmorun.ui.buttons.HowToPlayButton;
import org.werelone.cosmorun.ui.buttons.LanguageButton;
import org.werelone.cosmorun.ui.buttons.MusicButton;
import org.werelone.cosmorun.util.GameData;
import org.werelone.cosmorun.util.Language;
import org.werelone.cosmorun.util.Music;
import org.werelone.cosmorun.util.Property;
import org.werelone.cosmorun.view.RootLayout.FrontPane;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class OptionPane extends Pane {
	private static OptionPane pane;
	private CloseButton closeButton;
	private MusicButton btnMusic;
	private HowToPlayButton btHowToPlay;
	private BgModeButton btnBgMode;
	private LanguageButton langBtn;
	private ChangeRsltButton btnChangeRslt;
	private Text optionTitle;
	private Text optionTitle_cn;
	
	public static OptionPane create() {
		pane = new OptionPane();
		pane.init();
		return pane;
	}
	
	public static OptionPane getInstance() {
		return pane;
	}
	
	private void init() {
		// ���ý��汳��
		this.getChildren().add(new MenuBackground());
		// ��Ϸ����
		optionTitle = new Text(Language.getText("en-option-title"), Property.get("option-title-x"), Property.get("option-title-y"), TextFont.GillSans, 80, Color.hsb(0, 0.0, 1.0, 0.90), true);
		this.getChildren().add(optionTitle);
		// ��Ϸ����
		optionTitle_cn = new Text(Language.getText("cn-option-title"), Property.get("option-title-cn-x"), Property.get("option-title-cn-y"), TextFont.MsYaHei, 65, Color.hsb(0, 0.0, 1.0, 0.90), false);
		this.getChildren().add(optionTitle_cn);
		// �رհ�ť
		closeButton = new CloseButton();
		closeButton.setLayoutX(Property.get("closebtn-x"));
		closeButton.setLayoutY(Property.get("closebtn-y"));
		closeButton.setOnMouseClicked(e -> RootLayout.changeFrontPane(FrontPane.startMenu));
		this.getChildren().add(closeButton);
		// ��Ϸ˵����ť
		btHowToPlay = new HowToPlayButton();
		btHowToPlay.setLayoutX(Property.get("option-howtoplaybtn-x"));
		btHowToPlay.setLayoutY(Property.get("option-howtoplaybtn-y"));
		btHowToPlay.setOnMouseClicked(e -> RootLayout.changeFrontPane(FrontPane.howToPlay));
		this.getChildren().add(btHowToPlay);
		// �л�������ť
		btnBgMode = new BgModeButton();
		btnBgMode.setLayoutX(Property.get("option-bgmodebtn-x"));
		btnBgMode.setLayoutY(Property.get("option-bgmodebtn-y"));
		btnBgMode.setOnMouseClicked(e -> {
			BackgroundPane.changeBgColor();
			Floors.changeFloorColor();
			btnBgMode.setText();
		});
		this.getChildren().add(btnBgMode);
		// �л����԰�ť
		langBtn = new LanguageButton();
		langBtn.setLayoutX(Property.get("option-langbtn-x"));
		langBtn.setLayoutY(Property.get("option-langbtn-y"));
		langBtn.setOnMouseClicked(e -> {
			GameData.changeLanguage();
			StartMenuPane.changeLang();
			AboutPane.changeLang();
			UpdateLogPane.changeLang();
			OptionPane.changeLang();
			GameOverPane.changeLang();
			HowToPlayPane.changeLang();
		});
		this.getChildren().add(langBtn);
		// ���ֿ��ذ�ť
		btnMusic = new MusicButton();
		btnMusic.setLayoutX(Property.get("option-musicbtn-x"));
		btnMusic.setLayoutY(Property.get("option-musicbtn-y"));
		btnMusic.setOnMouseClicked(e -> {
			if (GameData.musicOn) {
				GameData.musicOn = false;
				Music.stopBgMusic();
				btnMusic.setOn(false);
			} else {
				GameData.musicOn = true;
				Music.playBgMusic();
				btnMusic.setOn(true);
			}
		});
		this.getChildren().add(btnMusic);
		// �л��ֱ��ʰ�ť
		btnChangeRslt = new ChangeRsltButton();
		btnChangeRslt.setLayoutX(Property.get("option-changersltbtn-x"));
		btnChangeRslt.setLayoutY(Property.get("option-changersltbtn-y"));
		btnChangeRslt.setOnMouseClicked(e -> Main.changeResolution());
		this.getChildren().add(btnChangeRslt);
		
		// �л�����
		changeLang();
	}
	
	// �л�����
	public static void changeLang() {
		if (GameData.language == 0) {
			pane.optionTitle.setOpacity(1);
			pane.optionTitle_cn.setOpacity(0);
		}
		else {
			pane.optionTitle.setOpacity(0);
			pane.optionTitle_cn.setOpacity(1);
		}
		pane.btHowToPlay.changeLang();
		pane.btnBgMode.changeLang();
		pane.langBtn.changeLang();
	}

}
