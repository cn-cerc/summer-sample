package cn.cerc.summer.sample.forms;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.db.core.DataSet;
import cn.cerc.db.core.Utils;
import cn.cerc.mis.core.IPage;
import cn.cerc.mis.core.RedirectPage;
import cn.cerc.mis.core.ServiceQuery;
import cn.cerc.mis.security.Permission;
import cn.cerc.mis.security.Webform;
import cn.cerc.summer.sample.core.CustomForm;
import cn.cerc.summer.sample.core.ui.UICustomPage;
import cn.cerc.summer.sample.core.ui.UINotice;
import cn.cerc.summer.sample.services.SampleServices.SvrExample;
import cn.cerc.ui.columns.CustomColumn;
import cn.cerc.ui.columns.ItColumn;
import cn.cerc.ui.columns.OptionColumn;
import cn.cerc.ui.columns.StringColumn;
import cn.cerc.ui.columns.UIGrid;
import cn.cerc.ui.columns.UIPhoneLine;
import cn.cerc.ui.core.UrlRecord;
import cn.cerc.ui.panels.UIAppendPanel;
import cn.cerc.ui.panels.UIModifyPanel;
import cn.cerc.ui.panels.UISearchPanel;
import cn.cerc.ui.vcl.UISpan;
import cn.cerc.ui.vcl.UIUrl;

@Webform(module = "", name = "范例Ui", parent = "")
@Permission("user.base")
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmUiExample extends CustomForm {
    @Override
    public IPage execute() {
        UICustomPage page = new UICustomPage(this);

        new UIUrl(page.getFrontPanel()).setText("返回").setSite("welcome");
        new UINotice(page.getFrontPanel()).receive(this, "execute");
        new UIUrl(page.getFooter()).setText("新增").setSite("FrmUiExample.append");


        UISearchPanel search = new UISearchPanel(page.getContent());
        new StringColumn(search, "编号", "code_").setPlaceholder("请输入商品编号");
        new StringColumn(search, "条件", "searchText_").setPlaceholder("请输入企业名称");
        search.readAll();

        // 服务查询用于执行服务(本地或者远程)
        ServiceQuery svr = ServiceQuery.open(this, SvrExample.search, search.getRecord());
        // 执行服务，返回结果为boolean类型，失败将失败信息返回到页面给，服务执行正常，服务将数据存放至服务出口
        if (svr.isFail()) {
            page.setMessage(svr.dataOut().message());
            return page;
        }

        DataSet dataOut = svr.dataOut();

        UIGrid grid = new UIGrid(page.getContent());
        grid.setDataSet(dataOut);

        UIPhoneLine line1 = grid.addPhoneLine(50, 50);
        UIPhoneLine line2 = grid.addPhoneLine(50, 50);
        UIPhoneLine line3 = grid.addPhoneLine(50, 50);
        UIPhoneLine line4 = grid.addPhoneLine(100);

        new ItColumn(line1.cell(0));
        new StringColumn(line1.cell(1), "学号", "code_", 4);
        new StringColumn(line2.cell(0), "姓名", "name_", 4);
        Map<String, String> sexMap = new LinkedHashMap<String, String>();
        sexMap.put("0", "男");
        sexMap.put("1", "女");
        new OptionColumn(line2.cell(1), "性别", "sex_", 6).setOptions(sexMap);
        new StringColumn(line3.cell(0), "年龄", "age_", 2);

        CustomColumn customColumn = new CustomColumn(line4.cell(0));
        customColumn.setSpaceWidth(4);
        customColumn.defineCell((content, record) -> {
            new UIUrl(content).setText("修改").setSite("FrmUiExample.modify").putParam("code", record.getString("code_"));
            new UISpan(content).setText("|");
            new UIUrl(content).setText("删除").setSite("FrmUiExample.delete").putParam("code", record.getString("code_"));
        });

        return page;
    }

    public IPage append() {
        UICustomPage page = new UICustomPage(this);
        new UIUrl(page.getFrontPanel()).setText("返回").setSite("FrmUiExample");
        UIAppendPanel uiform = new UIAppendPanel(page.getContent());

        new StringColumn(uiform, "学号", "code_", 4).setRequired(true);
        new StringColumn(uiform, "姓名", "name_", 4).setRequired(true);
        Map<String, String> sexMap = new LinkedHashMap<String, String>();
        sexMap.put("0", "男");
        sexMap.put("1", "女");
        new OptionColumn(uiform, "性别", "sex_", 6).setOptions(sexMap);
        new StringColumn(uiform, "年龄", "age_", 2).setRequired(true);

        if (!Utils.isEmpty(uiform.readAll())) {
            // 调用SvrCorpInfo.modify服务
            ServiceQuery svr = ServiceQuery.open(this, SvrExample.append, uiform.getRecord());
            if (svr.isFail()) {
                page.setMessage(svr.dataOut().message());
                return page;
            }
            UINotice.sendInfo(getSession(), this.getClass(), "execute", "新增成功");
            return new RedirectPage(this, "FrmUiExample");
        }
        return page;
    }

    public IPage modify() {
        UICustomPage page = new UICustomPage(this);
        new UIUrl(page.getFrontPanel()).setText("返回").setSite("FrmUiExample");
        String code = getRequest().getParameter("code");
        if (Utils.isEmpty(code)) {
            page.setMessage("code 不允许为空");
            return page;
        }
        ServiceQuery svr1 = ServiceQuery.open(this, SvrExample.download, Map.of("code_", code));
        if (svr1.isFail()) {
            page.setMessage(svr1.dataOut().message());
            return page;
        }

        DataSet dataOut = svr1.dataOut();
        UIModifyPanel uiform = new UIModifyPanel(page.getContent());
        uiform.setRecord(dataOut.current());

        new StringColumn(uiform, "学号", "code_", 4).setReadonly(true);
        new StringColumn(uiform, "姓名", "name_", 4).setReadonly(true);
        Map<String, String> sexMap = new LinkedHashMap<String, String>();
        sexMap.put("0", "男");
        sexMap.put("1", "女");
        new OptionColumn(uiform, "性别", "sex_", 6).setOptions(sexMap);
        new StringColumn(uiform, "年龄", "age_", 2).setRequired(true);

        if (!Utils.isEmpty(uiform.readAll())) {
            // 调用SvrCorpInfo.modify服务
            ServiceQuery svr = ServiceQuery.open(this, SvrExample.modify, uiform.getRecord());
            if (svr.isFail()) {
                page.setMessage(svr.dataOut().message());
                return page;
            }
            UINotice.sendInfo(getSession(), this.getClass(), "execute", "修改成功");
            return new RedirectPage(this, "FrmUiExample");
        }
        return page;
    }

    public IPage delete() {
        String code = getRequest().getParameter("code");
        UrlRecord url = new UrlRecord();
        url.setSite("FrmUiExample");

        ServiceQuery svr = ServiceQuery.open(this, SvrExample.delete, Map.of("code_", code));
        if (svr.isFail()) {
            UINotice.sendInfo(getSession(), this.getClass(), "execute", svr.dataOut().message());
            return new RedirectPage(this, url.getUrl());
        }
        UINotice.sendInfo(getSession(), this.getClass(), "execute", String.format("%s 删除成功", code));
        return new RedirectPage(this, url.getUrl());
    }
}
