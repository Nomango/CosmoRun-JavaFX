package org.werelone.cosmorun.ui.buttons;

import org.werelone.cosmorun.ui.Text;
import org.werelone.cosmorun.ui.Text.TextFont;
import org.werelone.cosmorun.util.GameData;
import org.werelone.cosmorun.util.Language;
import org.werelone.cosmorun.util.Property;

public class LanguageButton extends BaseButton {
	private Text languageText;
	private Text languageText_cn;
	
	public LanguageButton() {
		super.initLarge();
		this.init();
	}
	
	private void init() {
		languageText = new Text(Language.getText("en-option-language"), Property.get("option-langbtn-text-x"), Property.get("option-langbtn-text-y"), TextFont.GillSans, 65, true);
		languageText_cn = new Text(Language.getText("cn-option-language"), Property.get("option-langbtn-text-cn-x"), Property.get("option-langbtn-text-cn-y"), TextFont.MsYaHei, 40, false);
		changeLang();
		this.getChildren().add(languageText);
		this.getChildren().add(languageText_cn);
	}
	
	public void setText(String text) {
		languageText.setText(text);
		languageText_cn.setText(text);
	}
	
	// «–ªª”Ô—‘
	public void changeLang() {
		if (GameData.language == 0) {
			languageText.setOpacity(1);
			languageText_cn.setOpacity(0);
		}
		else {
			languageText.setOpacity(0);
			languageText_cn.setOpacity(1);
		}
	}
}
