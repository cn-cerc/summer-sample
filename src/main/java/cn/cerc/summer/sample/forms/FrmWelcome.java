package cn.cerc.summer.sample.forms;

import cn.cerc.mis.core.AbstractForm;
import cn.cerc.mis.core.IPage;
import cn.cerc.ui.page.UIPageWelcome;
import cn.cerc.ui.vcl.UIDiv;
import cn.cerc.ui.vcl.UILabel;
import cn.cerc.ui.vcl.UIText;

public class FrmWelcome extends AbstractForm {

    public IPage execute() {
        UIPageWelcome page = new UIPageWelcome(this);

        UIText text = new UIText(page.getContent());
        text.add("欢迎使用summer-sample");
        text.add("此项目有助于您快速学习summer框架的使用方法");
        text.add("新的项目也可以以此为基础，快速建立");

        UILabel label1 = new UILabel(new UIDiv(page.getContent()));
        label1.setText("进入首页");
        label1.setUrl("FrmIndex");

        UILabel label2 = new UILabel(new UIDiv(page.getContent()));
        label2.setText("进入首页(免登录)");
        label2.setUrl("FrmIndex?sid=88888888");

        return page;
    }

    @Override
    public boolean logon() {
        // true: 此页面不需要登录即可使用
        return true;
    }

}
