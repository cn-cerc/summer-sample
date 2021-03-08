package cn.cerc.training.forms;

import cn.cerc.core.DataSet;
import cn.cerc.core.Record;
import cn.cerc.core.TDateTime;
import cn.cerc.core.Utils;
import cn.cerc.db.mysql.SqlQuery;
import cn.cerc.mis.core.AbstractForm;
import cn.cerc.mis.core.IPage;
import cn.cerc.mis.core.LocalService;
import cn.cerc.mis.language.R;
import cn.cerc.mis.other.BufferType;
import cn.cerc.mis.other.MemoryBuffer;
import cn.cerc.training.common.AppDB;
import cn.cerc.ui.core.UrlRecord;
import cn.cerc.ui.fields.ButtonField;
import cn.cerc.ui.fields.ItField;
import cn.cerc.ui.fields.OperaField;
import cn.cerc.ui.fields.StringField;
import cn.cerc.ui.grid.AbstractGrid;
import cn.cerc.ui.grid.PhoneGrid;
import cn.cerc.ui.page.JspPage;
import cn.cerc.ui.page.RedirectPage;
import cn.cerc.ui.page.UIPageSearch;
import cn.cerc.ui.parts.UIFormHorizontal;
import cn.cerc.ui.parts.UIHeader;
import cn.cerc.ui.parts.UISheetHelp;
import cn.cerc.ui.parts.UIToolbar;
import cn.cerc.ui.vcl.UIGroupBox;
import cn.cerc.ui.vcl.ext.UISpan;
import lombok.extern.slf4j.Slf4j;

/**
 * 新建的Frm类请重写 logon() 函数（详见底部），否则会被过滤器拦截
 */
@Slf4j
public class FrmExample extends AbstractForm {

    @Override
    public IPage execute() {
        // JspPage用于加载jsp页面
        JspPage jspPage = new JspPage(this, "common/FrmExample.jsp");

        String message = getRequest().getParameter("message");
        if (message != null) {
            // 将消息设置到页面
            jspPage.setMessage(message);
        }

        SqlQuery cdsTmp = new SqlQuery(this);
        cdsTmp.add("select * from %s", AppDB.Table_Example);
        DataSet dataSet = cdsTmp.open();
        log.info("sql指令 {}", cdsTmp.getSqlText().getText());
        log.info("sql数据 {}", dataSet.getJSON());
        while (dataSet.fetch()) {
            // 将性别根据编码转为汉字
            String sex = dataSet.getInt("sex_") == 0 ? "男" : "女";
            dataSet.setField("sex_", sex);
        }
        // 将数据添加到页面对象，提供给页面使用
        jspPage.add("dataSet", dataSet);
        // 返回页面对象，显示页面对象加载的jsp页面
        return jspPage;
    }

    public IPage append() {
        JspPage jspPage = new JspPage(this, "common/FrmExample_append.jsp");
        String submit = getRequest().getParameter("submit");
        if (submit == null || "".equals(submit)) {
            return jspPage;
        }

        String code = getRequest().getParameter("code");
        if (Utils.isEmpty(code)) {
            jspPage.setMessage("学号不允许为空");
            return jspPage;
        }
        String name = getRequest().getParameter("name");
        if (Utils.isEmpty(name)) {
            jspPage.setMessage("姓名不允许为空");
            return jspPage;
        }
        String sex = getRequest().getParameter("sex");
        String age = getRequest().getParameter("age");

        // 打开数据库连接，判断当前学号是否已存在
        SqlQuery cdsTmp = new SqlQuery(this);
        cdsTmp.add("select * from %s", AppDB.Table_Example);
        cdsTmp.add("where code_='%s'", code);
        cdsTmp.open();
        if (!cdsTmp.eof()) {
            jspPage.setMessage("该学号已经存在，不允许重复登记");
            return jspPage;
        }
        // 将数据添加到数据库
        cdsTmp.append();
        cdsTmp.setField("code_", code);
        cdsTmp.setField("name_", name);
        cdsTmp.setField("sex_", sex);
        cdsTmp.setField("age_", age);
        cdsTmp.setField("createTime_", TDateTime.now());
        cdsTmp.setField("updateTime_", TDateTime.now());
        cdsTmp.post();

        // 数据添加成功，重定向到查询页面
        UrlRecord url = new UrlRecord();
        url.setSite("FrmExample");
        url.putParam("message", "添加成功");
        return new RedirectPage(this, url.getUrl());
    }

    public IPage modify() {
        JspPage jspPage = new JspPage(this, "common/FrmExample_modify.jsp");
        String code = getRequest().getParameter("code");
        if (Utils.isEmpty(code)) {
            jspPage.setMessage("code 不允许为空");
            return jspPage;
        }

        String message = getRequest().getParameter("message");
        if (message != null) {
            jspPage.setMessage(message);
        }

        SqlQuery cdsTmp = new SqlQuery(this);
        cdsTmp.add("select * from %s", AppDB.Table_Example);
        cdsTmp.add("where code_='%s'", code);
        cdsTmp.open();
        if (cdsTmp.eof()) {
            jspPage.setMessage("指定的学号数据记录不存在");
            return jspPage;
        }
        Record record = cdsTmp.getCurrent();
        jspPage.add("record", record);

        String submit = getRequest().getParameter("submit");
        if (submit != null && !"".equals(submit)) {
            String sex = getRequest().getParameter("sex");
            if (sex == null || "".equals(sex)) {
                jspPage.setMessage("sex 代码不允许为空");
                return jspPage;
            }

            cdsTmp.edit();
            cdsTmp.setField("age_", getRequest().getParameter("age"));
            cdsTmp.setField("sex_", sex);
            cdsTmp.setField("updateTime_", TDateTime.now());
            cdsTmp.post();

            UrlRecord url = new UrlRecord();
            url.setSite("FrmExample.modify");
            url.putParam("code", code);
            url.putParam("message", "修改成功");
            return new RedirectPage(this, url.getUrl());
        }
        return jspPage;
    }

    public IPage delete() {
        UrlRecord url = new UrlRecord();
        url.setSite("FrmExample");
        String code = getRequest().getParameter("code");
        if (Utils.isEmpty(code)) {
            url.putParam("message", "code 不允许为空");
            return new RedirectPage(this, url.getUrl());
        }

        SqlQuery cdsTmp = new SqlQuery(this);
        cdsTmp.add("select * from %s", AppDB.Table_Example);
        cdsTmp.add("where code_='%s'", code);
        cdsTmp.open();
        if (cdsTmp.eof()) {
            url.putParam("message", String.format("%s 记录不存在", code));
            return new RedirectPage(this, url.getUrl());
        }
        // 删除数据记录
        cdsTmp.delete();

        url.putParam("message", String.format("%s 删除成功", code));
        return new RedirectPage(this, url.getUrl());
    }

    public IPage search() throws Exception {
        UIPageSearch jspPage = new UIPageSearch(this);
        UIHeader menu = jspPage.getHeader();
        menu.setPageTitle("人员维护");

        UIToolbar toolBar = jspPage.getToolBar();
        UISheetHelp section = new UISheetHelp(toolBar);
        section.addLine("人员维护");

        try (MemoryBuffer buff = new MemoryBuffer(BufferType.getUserForm, this.getUserCode(), "FrmExample")) {
            jspPage.addScriptCode((html) -> {
                html.print("clearNearHidden();");
            });

            // 添加搜索框
            UIFormHorizontal search = jspPage.createSearch(buff);
            new StringField(search, "条件", "searchText_").setPlaceholder(R.asString(this, "请输姓名或者年龄"));

            new ButtonField(search.getButtons(), R.asString(this, "查询"), "submit", "search");
            search.readAll();
            // 获取统计列表
            LocalService svr = new LocalService(this);
            svr.setService("SvrExample.search");
            svr.getDataIn().getHead().copyValues(search.getRecord());
            if (!svr.exec()) {
                jspPage.setMessage(svr.getMessage());
                return jspPage;
            }
            DataSet dataOut = svr.getDataOut();
            // 页面显示数据
            AbstractGrid grid = jspPage.createGrid(jspPage.getContent(), dataOut);
            if (getClient().isPhone()) {
                grid.getPages().setPageSize(10000);
            }
            new ItField(grid);
            StringField col1 = new StringField(grid, "姓名", "name_", 5);
            StringField col2 = new StringField(grid, "性别", "sex_", 5);
            col2.createText((ds, html) -> {
                html.print(ds.getInt("sex_") == 0 ? "男" : "女");
            });
            StringField col3 = new StringField(grid, "年龄", "age_", 5);
            OperaField opera = new OperaField(grid);
            opera.setShortName("");
            opera.setName("内容");
            opera.setValue("编辑");
            opera.createUrl((ds, url) -> {
                url.setSite("FrmExample.modify");
                url.putParam("uid", ds.getString("UID_"));
            });

            if (grid instanceof PhoneGrid) {
                PhoneGrid phoneGrid = (PhoneGrid) grid;
                phoneGrid.addLine().addItem(col1, opera);
                phoneGrid.addLine().addItem(col2, col3);
            }

            if (dataOut.eof()) {
                UIGroupBox box = new UIGroupBox(jspPage.getContent());
                box.setCssClass("no-content");
                UISpan span = new UISpan(box);
                span.setText(R.asString(this, "暂无数据"));
            }
            String msg = buff.getString("msg");
            if (!"".equals(msg)) {
                jspPage.setMessage(msg);
                buff.setField("msg", "");
            }
        }
        return jspPage;
    }

    @Override
    public boolean logon() {
        return true;
    }

}
