package cn.cerc.summer.sample.core.ui;

import java.util.ArrayList;
import java.util.List;

import cn.cerc.ui.core.HtmlWriter;
import cn.cerc.ui.parts.UIComponent;
import cn.cerc.ui.parts.UICssComponent;
import cn.cerc.ui.vcl.UILabel;

public class UICustomMenuBar extends UIComponent {
    private List<UIComponent> items = new ArrayList<>();
    private UIComponent defaultItem;
    
    public UICustomMenuBar() {
        super();
    }

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
            html.print("<span class='iconfont icon-%s'></span>", ((UICssComponent) item).getCssClass());
            item.output(html);
            html.print("</li>");
        }
        html.println("</ul>");
    }

    public UIComponent add(String menuCode, String menuName) {
        UILabel label = new UILabel();
        label.setText(menuName);
        label.setUrl(menuCode);
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
