package cn.cerc.summer.sample.core.ui;

import cn.cerc.mis.core.Application;
import cn.cerc.ui.parts.UIComponent;

public class UIMenuBar extends UICustomMenuBar {

    public UIMenuBar() {
        super();
        init();
    }

    public UIMenuBar(UIComponent owner) {
        super(owner);
        init();
    }

    private void init() {
        add(Application.getHomePage(), "首页");
        add("frmMenus", "菜单");
        add("frmReport", "报表");
        add("frmMyProfile", "我的");
    }

    public UIMenuBar switchTo(int index) {
        setDefaultItem(getItems().get(index));
        return this;
    }
    
}
