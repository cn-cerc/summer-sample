package cn.cerc.sample.ui;

import cn.cerc.mis.core.IForm;
import cn.cerc.ui.core.UIComponent;
import cn.cerc.ui.vcl.UIImage;
import cn.cerc.ui.vcl.UILabel;

public class UIHeader extends UIComponent {

    private IForm form;

    public UIHeader(UIComponent owner) {
        super(owner);
        if (this.getOrigin() instanceof IForm) {
            this.form = (IForm) this.getOrigin();
        }

        UIImage logo = new UIImage(this);
        logo.setSrc("images/home.png");
        logo.setOnclick("javascript:location.href=\"welcome\"");

        UILabel label = new UILabel(this);
        label.setText(form.getName());
    }

}
