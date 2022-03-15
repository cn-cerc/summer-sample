package cn.cerc.summer.sample.forms;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.mis.core.AbstractForm;
import cn.cerc.mis.core.IPage;
import cn.cerc.mis.security.Permission;
import cn.cerc.mis.security.Webform;
import cn.cerc.summer.sample.core.ui.UICustomPage;
import cn.cerc.ui.core.UIComponent;
import cn.cerc.ui.vcl.UIDiv;
import cn.cerc.ui.vcl.UIScript;
import cn.cerc.ui.vcl.UIText;
import cn.cerc.ui.vcl.UIUrl;
import cn.cerc.ui.vcl.ext.UIHtmlFile;
import cn.cerc.ui.vcl.ext.UILine;

@Webform(module = "", name = "欢迎使用 summer-sample", parent = "")
@Permission(Permission.GUEST)
@Component("welcome")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmWelcome extends AbstractForm {

    @Override
    public IPage execute() {
        UICustomPage page = new UICustomPage(this);
        page.addScriptFile("js/autumn-ui.js", "");
        UIComponent content = page.getContent();
        new UILine(content);

        UIText text = new UIText(content);
        text.add("此项目有助于您快速学习summer框架的使用方法");
        text.add("新的项目也可以以此为基础，快速建立");

        new UIUrl(content).setText("进入首页(PC)").setSite("FrmIndex?device=pc");
        new UILine(content);

        new UIUrl(content).setText("进入首页(Phone)").setSite("FrmIndex?device=phone");
        new UILine(content);

        new UIUrl(content).setText("进入首页(免登录)").setSite("FrmIndex?sid=88888888");
        new UILine(content);

        new UIUrl(content).setText("进入范例UI(免登录)").setSite("FrmUiExample?sid=88888888");
        new UILine(content);
        
        new UIUrl(content).setText("进入范例Mysql(免登录)").setSite("FrmUiExampleMysql?sid=88888888");
        new UILine(content);

        /** sci.js 使用范例 */
        new UIDiv(content).setId("page");
        UIScript script = new UIScript(content);
        script.setSrc("js/FrmWelcome.js");
        /** end */

        new UILine(content);
        if (!this.getClient().isPhone()) {
            new UIHtmlFile(page.getFooter()).setFileName("/html/copyright.html");
        }

        return page;
    }

}
