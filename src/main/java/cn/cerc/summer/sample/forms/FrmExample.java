package cn.cerc.summer.sample.forms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.cerc.core.DataSet;
import cn.cerc.core.Record;
import cn.cerc.core.Utils;
import cn.cerc.mis.core.IPage;
import cn.cerc.mis.core.LocalService;
import cn.cerc.mis.core.RedirectPage;
import cn.cerc.summer.sample.core.CustomForm;
import cn.cerc.ui.core.JspFile;
import cn.cerc.ui.core.UrlRecord;

/**
 * 新建的Frm类请重写 logon() 函数（详见底部），否则会被过滤器拦截
 */
public class FrmExample extends CustomForm {
    private static final Logger log = LoggerFactory.getLogger(FrmExample.class);

    @Override
    public IPage execute() {
        // JspFile 用于加载jsp页面
        JspFile page = new JspFile(this);
        page.setJspFile("common/FrmExample.jsp");

        String message = getRequest().getParameter("message");
        if (message != null) {
            // 将消息设置到页面
            page.setMessage(message);
        }

        // 本地服务，用于加载指定服务，使用指定服务功能，例如：加载服务ID：SvrExample.search，是查询功能
        LocalService svr = new LocalService(this, "SvrExample.search");
        // 获取服务的入口，用于外部专递数据给服务
        Record headIn = svr.getDataIn().getHead();
        // 设置专递给服务的数据
        headIn.setField("code_", getRequest().getParameter("code"));
        headIn.setField("searchText_", getRequest().getParameter("searchText"));
        // 执行服务，返回结果为boolean类型，失败将失败信息返回到页面给，服务执行正常，服务将数据存放至服务出口
        if (!svr.exec()) {
            page.setMessage(svr.getMessage());
            return page;
        }

        // 获取服务出口数据，DataSet类可存储一对多关系的数据，DataSet可装载二维表数据
        DataSet dataSet = svr.getDataOut();
        log.info("sql数据 {}", dataSet.getJSON());

        while (dataSet.fetch()) {
            // 将性别根据编码转为汉字
            String sex = dataSet.getInt("sex_") == 0 ? "男" : "女";
            dataSet.setField("sex_", sex);
        }
        // 将数据添加到页面对象，提供给页面使用
        page.add("dataSet", dataSet);
        // 返回页面对象，显示页面对象加载的jsp页面
        return page;
    }

    public IPage append() {
        JspFile page = new JspFile(this);
        page.setJspFile("common/FrmExample_append.jsp");
        String submit = getRequest().getParameter("submit");
        if (submit == null || "".equals(submit)) {
            return page;
        }

        String code = getRequest().getParameter("code");
        if (Utils.isEmpty(code)) {
            page.setMessage("学号不允许为空");
            return page;
        }
        String name = getRequest().getParameter("name");
        if (Utils.isEmpty(name)) {
            page.setMessage("姓名不允许为空");
            return page;
        }
        String sex = getRequest().getParameter("sex");
        String age = getRequest().getParameter("age");

        LocalService svr = new LocalService(this, "SvrExample.append");
        Record headIn = svr.getDataIn().getHead();
        headIn.setField("code_", code);
        headIn.setField("name_", name);
        headIn.setField("sex_", sex);
        headIn.setField("age_", age);
        if (!svr.exec()) {
            page.setMessage(svr.getMessage());
            return page;
        }

        // 数据添加成功，重定向到查询页面
        UrlRecord url = new UrlRecord();
        url.setSite("FrmExample");
        url.putParam("message", "添加成功");
        return new RedirectPage(this, url.getUrl());
    }

    public IPage modify() {
        JspFile page = new JspFile(this);
        page.setJspFile("common/FrmExample_modify.jsp");
        String code = getRequest().getParameter("code");
        if (Utils.isEmpty(code)) {
            page.setMessage("code 不允许为空");
            return page;
        }

        String message = getRequest().getParameter("message");
        if (message != null) {
            page.setMessage(message);
        }

        LocalService svr1 = new LocalService(this, "SvrExample.download");
        Record headIn1 = svr1.getDataIn().getHead();
        headIn1.setField("code_", code);
        if (!svr1.exec()) {
            page.setMessage(svr1.getMessage());
            return page;
        }
        Record record = svr1.getDataOut().getHead();
        page.add("record", record);

        String submit = getRequest().getParameter("submit");
        if (submit != null && !"".equals(submit)) {
            String sex = getRequest().getParameter("sex");
            if (sex == null || "".equals(sex)) {
                page.setMessage("sex 代码不允许为空");
                return page;
            }

            LocalService svr2 = new LocalService(this, "SvrExample.modify");
            Record headIn2 = svr2.getDataIn().getHead();
            headIn2.setField("code_", code);
            headIn2.setField("sex_", sex);
            headIn2.setField("age_", getRequest().getParameter("age"));
            if (!svr2.exec()) {
                page.setMessage(svr2.getMessage());
                return page;
            }

            UrlRecord url = new UrlRecord();
            url.setSite("FrmExample.modify");
            url.putParam("code", code);
            url.putParam("message", "修改成功");
            return new RedirectPage(this, url.getUrl());
        }
        return page;
    }

    public IPage delete() {
        UrlRecord url = new UrlRecord();
        url.setSite("FrmExample");
        String code = getRequest().getParameter("code");

        LocalService svr = new LocalService(this, "svrExample.delete");
        Record headIn2 = svr.getDataIn().getHead();
        headIn2.setField("code_", code);
        if (!svr.exec()) {
            url.setSite("FrmExample.modify");
            url.putParam("code_", code);
            url.putParam("message", svr.getMessage());
            return new RedirectPage(this, url.getUrl());
        }

        url.putParam("message", String.format("%s 删除成功", code));
        return new RedirectPage(this, url.getUrl());
    }

}
