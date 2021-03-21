package cn.cerc.summer.sample.core.ui;

import cn.cerc.ui.core.UIOriginComponent;
import cn.cerc.ui.parts.UIComponent;
import cn.cerc.ui.vcl.UIDiv;

public class UICopyright extends UIOriginComponent {
    private UIDiv div;

    public UICopyright(UIComponent owner) {
        super(owner);
        this.div = new UIDiv(this);
        div.setText("@Copyright 2004-2021<br/>website: http://www.mimrc.com");
    }

}
