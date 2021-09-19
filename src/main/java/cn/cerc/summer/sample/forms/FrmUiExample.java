package cn.cerc.summer.sample.forms;

import java.util.LinkedHashMap;
import java.util.Map;

import cn.cerc.core.DataRow;
import cn.cerc.core.DataSet;
import cn.cerc.core.Utils;
import cn.cerc.mis.core.IPage;
import cn.cerc.mis.core.LocalService;
import cn.cerc.mis.core.RedirectPage;
import cn.cerc.summer.sample.core.CustomForm;
import cn.cerc.summer.sample.core.ui.UICustomPage;
import cn.cerc.summer.sample.core.ui.UINotice;
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

        LocalService svr = new LocalService(this, "SvrExample.search");
        // 获取服务的入口，用于外部专递数据给服务
        DataRow headIn = svr.getDataIn().getHead();
        // 设置专递给服务的数据
        headIn.copyValues(search.getRecord());
        // 执行服务，返回结果为boolean类型，失败将失败信息返回到页面给，服务执行正常，服务将数据存放至服务出口
        if (!svr.exec()) {
            page.setMessage(svr.getMessage());
            return page;
        }

        DataSet dataOut = svr.getDataOut();

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
            LocalService svr = new LocalService(this, "SvrExample.append");
            // 传参
            DataRow headIn = svr.getDataIn().getHead();
            headIn.copyValues(uiform.getRecord());
            // 执行
            if (!svr.exec()) {
                page.setMessage(svr.getMessage());
                return page;
            }
            UINotice.sendInfo(getSession(), this.getClass(), "execute", "修改成功");
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

        LocalService svr1 = new LocalService(this, "SvrExample.download");
        DataRow headIn1 = svr1.getDataIn().getHead();
        headIn1.setField("code_", code);
        if (!svr1.exec()) {
            page.setMessage(svr1.getMessage());
            return page;
        }

        DataSet dataOut = svr1.getDataOut();
        UIModifyPanel uiform = new UIModifyPanel(page.getContent());
        uiform.setRecord(dataOut.getHead());

        new StringColumn(uiform, "学号", "code_", 4).setReadonly(true);
        new StringColumn(uiform, "姓名", "name_", 4).setReadonly(true);
        Map<String, String> sexMap = new LinkedHashMap<String, String>();
        sexMap.put("0", "男");
        sexMap.put("1", "女");
        new OptionColumn(uiform, "性别", "sex_", 6).setOptions(sexMap);
        new StringColumn(uiform, "年龄", "age_", 2).setRequired(true);

        if (!Utils.isEmpty(uiform.readAll())) {
            // 调用SvrCorpInfo.modify服务
            LocalService svr = new LocalService(this, "SvrExample.modify");
            // 传参
            svr.getDataIn().getHead().copyValues(uiform.getRecord());
            // 执行
            if (!svr.exec()) {
                page.setMessage(svr.getMessage());
                return page;
            }
            UINotice.sendInfo(getSession(), this.getClass(), "execute", "修改成功");
            return new RedirectPage(this, "FrmUiExample");
        }
        return page;
    }

    public IPage delete() {
        String code = getRequest().getParameter("code");
        LocalService svr = new LocalService(this, "SvrExample.delete");
        DataRow headIn = svr.getDataIn().getHead();
        headIn.setField("code_", code);

        UrlRecord url = new UrlRecord();
        url.setSite("FrmUiExample");
        if (!svr.exec()) {
            UINotice.sendInfo(getSession(), this.getClass(), "execute", svr.getMessage());
            return new RedirectPage(this, url.getUrl());
        }
        UINotice.sendInfo(getSession(), this.getClass(), "execute", String.format("%s 删除成功", code));
        return new RedirectPage(this, url.getUrl());
    }
}
