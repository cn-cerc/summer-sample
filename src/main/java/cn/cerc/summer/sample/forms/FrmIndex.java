package cn.cerc.summer.sample.forms;

import cn.cerc.mis.core.AbstractForm;
import cn.cerc.mis.core.IPage;
import cn.cerc.mis.core.JsonPage;
import cn.cerc.summer.sample.core.ui.UICustomPage;
import cn.cerc.summer.sample.core.ui.UIMenuBar;
import cn.cerc.ui.core.JspFile;
import cn.cerc.ui.parts.UIComponent;
import cn.cerc.ui.vcl.UIDiv;
import cn.cerc.ui.vcl.UIHtmlFile;
import cn.cerc.ui.vcl.UILabel;
import cn.cerc.ui.vcl.UILine;


public class FrmIndex extends AbstractForm {

    @Override
    public IPage execute() {
        UICustomPage page = new UICustomPage(this);

        UIComponent content = page.getContent();

        new UIDiv(content).setText("请根据需求文档，自行建立相关菜单文件");
        new UILine(content);

        new UILabel(content).setText("1、增删改查参考编写范例").setUrl("FrmExample");
        new UILine(content);

        new UILabel(content).setText("2、使用 jspfile 编写范例").setUrl("FrmIndex.html");
        new UILine(content);

        new UILabel(content).setText("3、根据表名自动生成服务代码").setUrl("frmBuildCode");

        if (this.getClient().isPhone())
            new UIMenuBar(page.getFooter());
        else
            new UIHtmlFile(page.getFooter()).setFileName("/html/copyright.html");

        return page;
    }

    public IPage html() {
        JspFile page = new JspFile(this);
        page.setJspFile("common/FrmIndex.jsp");
        return page;
    }

    public IPage json() {
        JsonPage page = new JsonPage(this);
        page.put("code", "name");
        return page;
    }

    @Override
    public String getName() {
        return "欢迎体验 summer 框架";
    }
}
