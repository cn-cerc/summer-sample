package cn.cerc.example.forms;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jbean.other.BufferType;
import cn.cerc.jbean.other.MemoryBuffer;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jdb.core.Record;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.language.R;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;
import cn.cerc.jpage.core.UrlRecord;
import cn.cerc.jpage.fields.ButtonField;
import cn.cerc.jpage.fields.ItField;
import cn.cerc.jpage.fields.OperaField;
import cn.cerc.jpage.fields.StringField;
import cn.cerc.jpage.grid.AbstractGrid;
import cn.cerc.jpage.grid.PhoneGrid;
import cn.cerc.jui.page.UIPagePhone;
import cn.cerc.jui.page.UIPageSearch;
import cn.cerc.jui.parts.UIFormHorizontal;
import cn.cerc.jui.parts.UIHeader;
import cn.cerc.jui.parts.UISheetHelp;
import cn.cerc.jui.parts.UISheetUrl;
import cn.cerc.jui.parts.UIToolBar;
import cn.cerc.jui.phone.Block101;
import cn.cerc.jui.vcl.UIGroupBox;
import cn.cerc.jui.vcl.UIText;
import cn.cerc.jui.vcl.ext.UISpan;
import lombok.extern.slf4j.Slf4j;

/**
 * 新建的Frm类请重写 logon() 函数（详见底部），否则会被过滤器拦截
 */
@Slf4j
public class FrmExample extends AbstractForm {

    @Override
    public IPage execute() {
        JspPage jspPage = new JspPage(this, "common/FrmExample.jsp");

        String message = getRequest().getParameter("message");
        if (message != null) {
            jspPage.setMessage(message);
        }

        LocalService svr = new LocalService(this, "SvrExample.search");
        Record headIn = svr.getDataIn().getHead();
        headIn.setField("code_", getRequest().getParameter("code"));
        headIn.setField("searchText_", getRequest().getParameter("searchText"));
        if (!svr.exec()) {
            jspPage.setMessage(svr.getMessage());
            return jspPage;
        }

        DataSet dataSet = svr.getDataOut();
        while (dataSet.fetch()) {
            String sex = dataSet.getInt("sex_") == 0 ? "男" : "女";
            dataSet.setField("sex_", sex);
        }
        jspPage.add("dataSet", dataSet);
        log.info("dataSet {}", dataSet);
        return jspPage;
    }

    public IPage append() {
        JspPage jspPage = new JspPage(this, "common/FrmExample_append.jsp");
        String submit = getRequest().getParameter("submit");
        if (submit == null || "".equals(submit)) {
            return jspPage;
        }

        String code = getRequest().getParameter("code");
        String name = getRequest().getParameter("name");
        String sex = getRequest().getParameter("sex");
        String age = getRequest().getParameter("age");

        LocalService svr = new LocalService(this, "SvrExample.append");
        Record headIn = svr.getDataIn().getHead();
        headIn.setField("code_", code);
        headIn.setField("name_", name);
        headIn.setField("sex_", sex);
        headIn.setField("age_", age);
        if (!svr.exec()) {
            jspPage.setMessage(svr.getMessage());
            return jspPage;
        }

        UrlRecord url = new UrlRecord();
        url.setSite("FrmExample");
        url.putParam("message", "添加成功");
        return new RedirectPage(this, url.getUrl());
    }

    public IPage modify() {
        JspPage jspPage = new JspPage(this, "common/FrmExample_modify.jsp");
        String code = getRequest().getParameter("code");
        if (code == null || "".equals(code)) {
            jspPage.setMessage("code 不允许为空");
            return jspPage;
        }

        String message = getRequest().getParameter("message");
        if (message != null) {
            jspPage.setMessage(message);
        }

        LocalService svr1 = new LocalService(this, "SvrExample.download");
        Record headIn1 = svr1.getDataIn().getHead();
        headIn1.setField("code_", code);
        if (!svr1.exec()) {
            jspPage.setMessage(svr1.getMessage());
            return jspPage;
        }
        Record record = svr1.getDataOut().getHead();
        record.setField("sex_", record.getInt("sex_") == 0 ? "男" : "女");
        jspPage.add("record", record);

        String submit = getRequest().getParameter("submit");
        if (submit != null && !"".equals(submit)) {
            LocalService svr2 = new LocalService(this, "SvrExample.modify");
            Record headIn2 = svr2.getDataIn().getHead();
            headIn2.setField("code_", code);
            headIn2.setField("age_", getRequest().getParameter("age"));
            if (!svr2.exec()) {
                jspPage.setMessage(svr2.getMessage());
                return jspPage;
            }
            jspPage.setMessage("保存成功！");
            return jspPage;
        }
        return jspPage;
    }

    public IPage delete() {
        UrlRecord url = new UrlRecord();
        String code = getRequest().getParameter("code");
        LocalService svr = new LocalService(this, "SvrExample.delete");
        Record headIn2 = svr.getDataIn().getHead();
        headIn2.setField("code_", code);
        if (!svr.exec()) {
            url.setSite("FrmExample.modify");
            url.putParam("code_", code);
            url.putParam("message", svr.getMessage());
            return new RedirectPage(this, url.getUrl());
        }
        url.setSite("FrmExample");
        url.putParam("message", "删除成功");
        return new RedirectPage(this, url.getUrl());
    }

    @Override
    public boolean logon() {
        return true;
    }

}
