package cn.cerc.summer.sample.core.ui;

import cn.cerc.mis.core.Application;
import cn.cerc.mis.core.IForm;
import cn.cerc.ui.core.UIOriginComponent;
import cn.cerc.ui.parts.UIComponent;
import cn.cerc.ui.vcl.UIImage;
import cn.cerc.ui.vcl.UILabel;

public class UIHeader extends UIOriginComponent {

    private IForm form;

    public UIHeader(UIComponent owner) {
        super(owner);
        if (this.getOrigin() instanceof IForm) {
            this.form = (IForm) this.getOrigin();
        }
        UIImage logo = new UIImage(this);
        logo.setSrc(Application.getStaticPath() + "images/logo.png");

        UILabel label = new UILabel(this);
        label.setText(form.getName());
    }

}
