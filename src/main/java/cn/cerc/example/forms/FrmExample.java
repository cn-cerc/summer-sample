package cn.cerc.example.forms;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jdb.core.Record;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;
import cn.cerc.jpage.core.UrlRecord;

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
        return jspPage;
    }

    public IPage append() {
        JspPage jspPage = new JspPage(this, "common/FrmExample_append.jsp");
        String submit = getRequest().getParameter("submit");
        if (submit == null || "".equals(submit)) {
            return jspPage;
        }

        String name = jspPage.getRequest().getParameter("name");
        if (name == null || "".equals(name)) {
            jspPage.setMessage("姓名不允许为空");
            return jspPage;
        }

        String sex = jspPage.getRequest().getParameter("sex");
        if (sex == null || "".equals(sex)) {
            jspPage.setMessage("性别不允许为空");
            return jspPage;
        }

        String age = jspPage.getRequest().getParameter("age");
        if (age == null || "".equals(age)) {
            jspPage.setMessage("姓名不允许为空");
            return jspPage;
        }

        LocalService svr = new LocalService(this, "SvrExample.append");
        Record headIn = svr.getDataIn().getHead();
        headIn.setField("name_", name);
        headIn.setField("sex_", sex);
        headIn.setField("age_", age);
        if (!svr.exec()) {
            jspPage.setMessage(svr.getMessage());
            return jspPage;
        }

        UrlRecord url = new UrlRecord();
        url.setSite("FrmExample");
        url.addParam("message", "添加成功");
        return new RedirectPage(this, url.getUrl());
    }

    public IPage modify() {
        JspPage jspPage = new JspPage(this, "common/FrmExample_modify.jsp");
        String uid = getRequest().getParameter("uid");
        if (uid == null || "".equals(uid)) {
            jspPage.setMessage("uid 不允许为空");
            return jspPage;
        }

        String message = getRequest().getParameter("message");
        if (message != null) {
            jspPage.setMessage(message);
        }

        LocalService svr1 = new LocalService(this, "SvrExample.download");
        Record headIn1 = svr1.getDataIn().getHead();
        headIn1.setField("UID_", uid);
        if (!svr1.exec()) {
            jspPage.setMessage(svr1.getMessage());
            return jspPage;
        }
        jspPage.add("record", svr1.getDataOut().getHead());

        String submit = getRequest().getParameter("submit");
        if (submit != null && !"".equals(submit)) {
            LocalService svr2 = new LocalService(this, "SvrExample.modify");
            Record headIn2 = svr2.getDataIn().getHead();
            headIn2.setField("UID_", uid);
            headIn2.setField("age_", jspPage.getRequest().getParameter("age"));
            if (!svr2.exec()) {
                jspPage.setMessage(svr2.getMessage());
                return jspPage;
            }
            return new RedirectPage(this, "FrmExample");
        }
        return jspPage;
    }

    public IPage delete() {
        UrlRecord url = new UrlRecord();

        String uid = getRequest().getParameter("uid");
        LocalService svr = new LocalService(this, "SvrExample.delete");
        Record headIn2 = svr.getDataIn().getHead();
        headIn2.setField("UID_", uid);

        if (!svr.exec()) {
            url.setSite("FrmExample.modify");
            url.addParam("uid", uid);
            url.addParam("message", svr.getMessage());
            return new RedirectPage(this, url.getUrl());
        }

        url.setSite("FrmExample");
        url.addParam("message", "删除成功");
        return new RedirectPage(this, url.getUrl());
    }

    @Override
    public boolean logon() {
        return true;
    }
}
