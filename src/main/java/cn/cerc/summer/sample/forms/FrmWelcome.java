package cn.cerc.summer.sample.forms;

import cn.cerc.core.DataSet;
import cn.cerc.mis.core.AbstractForm;
import cn.cerc.mis.core.IPage;
import cn.cerc.summer.sample.core.ui.UICustomPage;
import cn.cerc.ui.core.UIComponent;
import cn.cerc.ui.vcl.UIScript;
import cn.cerc.ui.vcl.UIText;
import cn.cerc.ui.vcl.UIUrl;
import cn.cerc.ui.vcl.ext.UIHtmlFile;
import cn.cerc.ui.vcl.ext.UILine;

public class FrmWelcome extends AbstractForm {

    @Override
    public IPage execute() {
        UICustomPage page = new UICustomPage(this);
//        page.addScriptFile("js/DataSet.js");
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
        
        DataSet ds = new DataSet();
        ds.append();
        ds.setField("code", "a");
        ds.setField("name", "jason");
        ds.append();
        ds.setField("code", "b");
        ds.setField("name", "bade");
        
        new UIText(content).setText("summer_ui.js使用范例");
        UIScript script = new UIScript(content);
        script.writeProperty("src", "/static/js/DataSet.js");
        script.add("\nlet ds = new DataSet('%s');", ds.toString());
        script.add("alert(ds.getJson());");
        
        new UILine(content);
        if (!this.getClient().isPhone()) {
            new UIHtmlFile(page.getFooter()).setFileName("/html/copyright.html");
        }
        
        return page;
    }

    @Override
    public boolean logon() {
        // true: 此页面不需要登录即可使用
        return true;
    }

}
