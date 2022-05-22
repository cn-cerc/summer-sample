package cn.cerc.summer.sample.core.ui;

import cn.cerc.mis.core.Application;
import cn.cerc.ui.core.UIComponent;

public class UIMenuBar extends UICustomMenuBar {

    public UIMenuBar(UIComponent owner) {
        super(owner);
        init();
    }

    private void init() {
        add(Application.getConfig().getDefaultPage(), "首页");
        add("frmMenus", "菜单");
        add("frmReport", "报表");
        add("frmMyProfile", "我的");
    }

    public UIMenuBar switchTo(int index) {
        setDefaultItem(getItems().get(index));
        return this;
    }

}
