package cn.cerc.example.forms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jdb.core.Record;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;
import cn.cerc.jpage.core.UrlRecord;

/**
 * 注意
 * 
 * 新建的Frm类请重写 logon() 函数，否则会被过滤器拦截
 */
public class FrmPartInfo extends AbstractForm {

    private static final Logger log = LoggerFactory.getLogger(FrmPartInfo.class);

    @Override
    public IPage execute() {
        JspPage jspPage = new JspPage(this, "common/FrmPartInfo.jsp");

        String message = getRequest().getParameter("message");
        if (message != null) {
            jspPage.setMessage(message);
        }

        LocalService svr = new LocalService(this, "SvrPartInfo.search");
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
        log.info("dataSet {}", dataSet);
        return jspPage;
    }

    public IPage append() {
        JspPage jspPage = new JspPage(this, "common/FrmPartInfo_append.jsp");
        String submit = getRequest().getParameter("submit");
        if (submit == null || "".equals(submit)) {
            return jspPage;
        }
        
        String corpNo = getRequest().getParameter("corpNo");
        if (corpNo == null || "".equals(corpNo)) {
            jspPage.setMessage("账套不允许为空");
            return jspPage;
        }
        
        String code = getRequest().getParameter("code");
        if (code == null || "".equals(code)) {
            jspPage.setMessage("编号不允许为空");
            return jspPage;
        }

        String desc = getRequest().getParameter("desc");
        if (desc == null || "".equals(desc)) {
            jspPage.setMessage("名称不允许为空");
            return jspPage;
        }

        String spec = getRequest().getParameter("spec");
        if (spec == null || "".equals(spec)) {
            jspPage.setMessage("规格不允许为空");
            return jspPage;
        }

        String unit = getRequest().getParameter("unit");
        if (unit == null || "".equals(unit)) {
            jspPage.setMessage("单位不允许为空");
            return jspPage;
        }
        
        String remark = getRequest().getParameter("remark");
        if (remark == null ) {
            return jspPage;
        }

        LocalService svr = new LocalService(this, "SvrPartInfo.append");
        Record headIn = svr.getDataIn().getHead();
        headIn.setField("desc_", desc);
        headIn.setField("spec_", spec);
        headIn.setField("unit_", unit);
        headIn.setField("corpNo_",corpNo);
        headIn.setField("code_",code);
        headIn.setField("remark_",remark);
        if (!svr.exec()) {
            jspPage.setMessage(svr.getMessage());
            return jspPage;
        }

        UrlRecord url = new UrlRecord();
        url.setSite("FrmPartInfo");
        url.putParam("message", "添加成功");
        return new RedirectPage(this, url.getUrl());
    }

    public IPage modify() {
        JspPage jspPage = new JspPage(this, "common/FrmPartInfo_modify.jsp");
        String uid = getRequest().getParameter("uid");
        if (uid == null || "".equals(uid)) {
            jspPage.setMessage("uid 不允许为空");
            return jspPage;
        }

        String message = getRequest().getParameter("message");
        if (message != null) {
            jspPage.setMessage(message);
        }

        LocalService svr1 = new LocalService(this, "SvrPartInfo.download");
        Record headIn1 = svr1.getDataIn().getHead();
        headIn1.setField("UID_", uid);
        if (!svr1.exec()) {
            jspPage.setMessage(svr1.getMessage());
            return jspPage;
        }
        jspPage.add("record", svr1.getDataOut().getHead());

        String submit = getRequest().getParameter("submit");
        if (submit != null && !"".equals(submit)) {
            LocalService svr2 = new LocalService(this, "SvrPartInfo.modify");
            Record headIn2 = svr2.getDataIn().getHead();
            headIn2.setField("UID_", uid);
            /*
             * headIn2.setField("corpNo_", getRequest().getParameter("corpNo_"));
             * headIn2.setField("code_", getRequest().getParameter("code_"));
             */
            headIn2.setField("desc_", getRequest().getParameter("desc"));
            headIn2.setField("spec_", getRequest().getParameter("spec"));
            headIn2.setField("unit_", getRequest().getParameter("unit"));
            headIn2.setField("remark_", getRequest().getParameter("remark"));
            if (!svr2.exec()) {
                jspPage.setMessage(svr2.getMessage());
                return jspPage;
            }
            return new RedirectPage(this, "FrmPartInfo");
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
            url.putParam("uid", uid);
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
