package cn.cerc.summer.sample.forms;

import cn.cerc.mis.core.AbstractForm;
import cn.cerc.mis.core.IPage;
import cn.cerc.summer.sample.core.ui.UICopyright;
import cn.cerc.summer.sample.core.ui.UICustomPage;
import cn.cerc.summer.sample.core.ui.UIMenuBar;
import cn.cerc.ui.vcl.UIDiv;
import cn.cerc.ui.vcl.UILabel;
import cn.cerc.ui.vcl.UILine;
import cn.cerc.ui.vcl.UIText;

public class FrmWelcome extends AbstractForm {

    public IPage execute() {
        UICustomPage page = new UICustomPage(this);

        new UILine(page.getContent());

        UIText text = new UIText(page.getContent());
        text.add("此项目有助于您快速学习summer框架的使用方法");
        text.add("新的项目也可以以此为基础，快速建立");

        UILabel label1 = new UILabel(new UIDiv(page.getContent()));
        label1.setUrl("FrmIndex?device=pc").setText("进入首页(PC)");

        UILabel label2 = new UILabel(new UIDiv(page.getContent()));
        label2.setUrl("FrmIndex?device=phone").setText("进入首页(Phone)");

        UILabel label3 = new UILabel(new UIDiv(page.getContent()));
        label3.setUrl("FrmIndex?sid=88888888").setText("进入首页(免登录)");

        new UILine(page.getContent());

        if (!this.getClient().isPhone())
            new UICopyright(page.getFooter());

        return page;
    }

    @Override
    public boolean logon() {
        // true: 此页面不需要登录即可使用
        return true;
    }

}
