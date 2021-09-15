package cn.cerc.summer.sample.core.ui;

import cn.cerc.ui.core.HtmlWriter;
import cn.cerc.ui.core.UIComponent;
import cn.cerc.ui.vcl.UIA;
import cn.cerc.ui.vcl.UILabel;

import java.util.ArrayList;
import java.util.List;

public class UICustomMenuBar extends UIComponent {
    private List<UIComponent> items = new ArrayList<>();
    private UIComponent defaultItem;

    public UICustomMenuBar(UIComponent owner) {
        super(owner);
    }

    @Override
    public void output(HtmlWriter html) {
        html.print("<ul class='%s'>", "menuBar");
        for (UIComponent item : items) {
            if (item == defaultItem) {
                html.print("<li class='selected'>");
            } else {
                html.print("<li>");
            }
            html.print("<span class='iconfont icon-%s'></span>", item.getCssClass());
            item.output(html);
            html.print("</li>");
        }
        html.println("</ul>");
    }

    public UIComponent add(String menuCode, String menuName) {
        UIA label = new UIA(this);
        label.setText(menuName);
        label.setSite(menuCode);
        label.setCssClass(menuCode);
        items.add(label);
        return label;
    }

    public UIComponent getDefaultItem() {
        return defaultItem;
    }

    public void setDefaultItem(UIComponent defaultMenu) {
        this.defaultItem = defaultMenu;
    }

    public List<UIComponent> getItems() {
        return items;
    }
}
