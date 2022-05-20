package cn.cerc.sample.forms;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.mis.core.AbstractForm;
import cn.cerc.mis.core.IPage;
import cn.cerc.mis.core.JsonPage;
import cn.cerc.mis.security.Permission;
import cn.cerc.mis.security.Webform;
import cn.cerc.sample.core.ui.UICustomPage;
import cn.cerc.sample.core.ui.UIMenuBar;
import cn.cerc.ui.core.JspFile;
import cn.cerc.ui.core.UIComponent;
import cn.cerc.ui.vcl.UIDiv;
import cn.cerc.ui.vcl.UIUrl;
import cn.cerc.ui.vcl.ext.UIHtmlFile;
import cn.cerc.ui.vcl.ext.UILine;

@Webform(module = "", name = "首页", parent = "")
@Permission("user.base")
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmIndex extends AbstractForm {

    @Override
    public IPage execute() {
        UICustomPage page = new UICustomPage(this);
        UIComponent content = page.getContent();
        new UIDiv(content).setText("请根据需求文档，自行建立相关菜单文件");
        new UILine(content);

        new UIUrl(content).setText("1、增删改查参考编写范例").setSite("FrmExample");
        new UILine(content);

        new UIUrl(content).setText("2、使用 jspfile 编写范例").setSite("FrmIndex.html");
        new UILine(content);

        new UIUrl(content).setText("3、根据表名自动生成服务代码").setSite("FrmBuildCode");

        if (this.getClient().isPhone())
            new UIMenuBar(page.getFooter());
        else
            new UIHtmlFile(page.getFooter()).setFileName("/html/copyright.html");

        return page;
    }

    public IPage html() {
        JspFile page = new JspFile(this);
        page.setJspFile("common/index_html.jsp");
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
