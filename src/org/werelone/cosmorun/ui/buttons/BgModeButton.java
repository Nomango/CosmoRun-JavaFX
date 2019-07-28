package org.werelone.cosmorun.ui.buttons;

import org.werelone.cosmorun.ui.Text;
import org.werelone.cosmorun.ui.Text.TextFont;
import org.werelone.cosmorun.util.GameData;
import org.werelone.cosmorun.util.Language;
import org.werelone.cosmorun.util.Property;
import org.werelone.cosmorun.view.BackgroundPane;

public class BgModeButton extends BaseButton{
	private Text colorText;
	private Text colorText_cn;
	
	public BgModeButton() {
		super.initLarge();
		this.init();
	}
	
	private void init() {
		colorText = new Text("", Property.get("option-bgmodebtn-text-x"), Property.get("option-bgmodebtn-text-y"), TextFont.GillSans, 65, true);
		colorText_cn = new Text("", Property.get("option-bgmodebtn-text-cn-x"), Property.get("option-bgmodebtn-text-cn-y"), TextFont.MsYaHei, 40, false);
		changeLang();
		this.setText();
		this.getChildren().add(colorText);
		this.getChildren().add(colorText_cn);
	}
	
	public void setText() {
		colorText.setText(Language.getText("en-option-color") + " - " + BackgroundPane.getBgMode(0));
		colorText_cn.setText(Language.getText("cn-option-color") + " - " + BackgroundPane.getBgMode(1));
	}
	
	// «–ªª”Ô—‘
	public void changeLang() {
		if (GameData.language == 0) {
			colorText.setOpacity(1);
			colorText_cn.setOpacity(0);
		}
		else {
			colorText.setOpacity(0);
			colorText_cn.setOpacity(1);
		}
	}

}
