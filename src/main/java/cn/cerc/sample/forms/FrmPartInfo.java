package cn.cerc.sample.forms;

import java.util.Map;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.db.core.DataSet;
import cn.cerc.db.core.Utils;
import cn.cerc.mis.client.ServiceExecuteException;
import cn.cerc.mis.core.IPage;
import cn.cerc.mis.core.RedirectPage;
import cn.cerc.mis.core.ServiceQuery;
import cn.cerc.mis.other.MemoryBuffer;
import cn.cerc.mis.security.Permission;
import cn.cerc.mis.security.Webform;
import cn.cerc.sample.SampleServicesConfig.SvrPartInfo;
import cn.cerc.sample.config.BufferUser;
import cn.cerc.sample.config.CustomForm;
import cn.cerc.sample.ui.UICustomPage;
import cn.cerc.sample.ui.UINotice;
import cn.cerc.ui.columns.CustomColumn;
import cn.cerc.ui.columns.DoubleColumn;
import cn.cerc.ui.columns.ItColumn;
import cn.cerc.ui.columns.StringColumn;
import cn.cerc.ui.columns.UIGrid;
import cn.cerc.ui.columns.UIPhoneLine;
import cn.cerc.ui.core.UrlRecord;
import cn.cerc.ui.panels.UIAppendPanel;
import cn.cerc.ui.panels.UIModifyPanel;
import cn.cerc.ui.panels.UISearchPanel;
import cn.cerc.ui.vcl.UISpan;
import cn.cerc.ui.vcl.UIUrl;

@Webform(module = "", name = "商品管理", parent = "")
@Permission("user.base")
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmPartInfo extends CustomForm {

    @Override
    public IPage execute() throws ServiceExecuteException {
        UICustomPage page = new UICustomPage(this);

        new UIUrl(page.getFrontPanel()).setText("返回").setSite("welcome");
        new UINotice(page.getFrontPanel()).receive(this, "execute");
        new UIUrl(page.getFooter()).setText("新增").setSite("FrmPartInfo.append");

        UISearchPanel search = new UISearchPanel(page.getContent());
        new StringColumn(search, "商品料号", "code_").setPlaceholder("请输入商品料号");
        new StringColumn(search, "查询条件", "searchText_").setPlaceholder("请输入查询条件");
        search.readAll();

        DataSet dataOut = ServiceQuery.open(this, SvrPartInfo.search, search.current()).getDataOutElseThrow();
        UIGrid grid = new UIGrid(page.getContent()).setDataSet(dataOut);
        UIPhoneLine line1 = grid.addPhoneLine(50, 50);
        UIPhoneLine line2 = grid.addPhoneLine(50, 50);
        UIPhoneLine line3 = grid.addPhoneLine(50, 50);
        UIPhoneLine line4 = grid.addPhoneLine(100);
        UIPhoneLine line5 = grid.addPhoneLine(100);

        new ItColumn(line1.cell(0));
        new StringColumn(line1.cell(1), "料号", "code_", 4);

        new StringColumn(line2.cell(0), "品名", "desc_", 6);
        new StringColumn(line2.cell(1), "规格", "spec_", 6);

        new StringColumn(line3.cell(0), "单位", "unit_", 4);
        new DoubleColumn(line3.cell(1), "库存", "stock_", 4);

        new StringColumn(line4.cell(0), "备注", "remark_", 6);

        CustomColumn customColumn = new CustomColumn(line5.cell(0));
        customColumn.setSpaceWidth(8);
        customColumn.defineCell((content, record) -> {
            new UIUrl(content).setText("修改").setSite("FrmPartInfo.modify").putParam("code", record.getString("code_"));
            new UISpan(content).setText("|");
            new UIUrl(content).setText("删除").setSite("FrmPartInfo.delete").putParam("code", record.getString("code_"));
        });
        return page;
    }

    public IPage append() {
        UICustomPage page = new UICustomPage(this);
        new UIUrl(page.getFrontPanel()).setText("返回").setSite("FrmPartInfo");
        UIAppendPanel actionForm = new UIAppendPanel(page.getContent());

        new StringColumn(actionForm, "品名", "desc_").setRequired(true);
        new StringColumn(actionForm, "规格", "spec_").setRequired(true);
        new StringColumn(actionForm, "单位", "unit_");
        new StringColumn(actionForm, "备注", "remark_");

        if (!Utils.isEmpty(actionForm.readAll())) {
            // 调用SvrCorpInfo.modify服务
            ServiceQuery svr = ServiceQuery.open(this, SvrPartInfo.append, actionForm.getRecord());
            if (svr.isFail()) {
                page.setMessage(svr.dataOut().message());
                return page;
            }
            String code = svr.dataOut().getString("code_");
            UINotice.sendInfo(getSession(), this.getClass(), "modify", String.format("新增商品 %s", code));
            String modifyUrl = UrlRecord.builder("FrmPartInfo.modify").put("code", code).build().getUrl();
            return new RedirectPage(this, modifyUrl);
        }
        return page;
    }

    public IPage modify() {
        UICustomPage page = new UICustomPage(this);
        new UIUrl(page.getFrontPanel()).setText("返回").setSite("FrmPartInfo");
        new UINotice(page.getFrontPanel()).receive(this, "modify");

        try (MemoryBuffer buff = new MemoryBuffer(BufferUser.Notice_UserCode, this.getUserCode(),
                "FrmPartInfo.modify")) {
            String code = page.getValue(buff, "code");
            if (Utils.isEmpty(code)) {
                page.setMessage("code 不允许为空");
                return page;
            }

            ServiceQuery svr1 = ServiceQuery.open(this, SvrPartInfo.download, Map.of("code_", code));
            if (svr1.isFail()) {
                page.setMessage(svr1.dataOut().message());
                return page;
            }
            DataSet dataOut = svr1.dataOut();
            UIModifyPanel actionForm = new UIModifyPanel(page.getContent());
            actionForm.setRecord(dataOut.current());

            new StringColumn(actionForm, "料号", "code_").setReadonly(true);
            new StringColumn(actionForm, "品名", "desc_").setRequired(true);
            new StringColumn(actionForm, "规格", "spec_").setRequired(true);
            new StringColumn(actionForm, "单位", "unit_").setRequired(true);
            new StringColumn(actionForm, "备注", "remark_");

            if (!Utils.isEmpty(actionForm.readAll())) {
                // 调用SvrCorpInfo.modify服务
                ServiceQuery svr = ServiceQuery.open(this, SvrPartInfo.modify, actionForm.getRecord());
                if (svr.isFail()) {
                    page.setMessage(svr.dataOut().message());
                    return page;
                }
                UINotice.sendInfo(getSession(), this.getClass(), "modify", "修改成功");
                return new RedirectPage(this, "FrmPartInfo.modify");
            }
        }
        return page;
    }

    public IPage delete() {
        String code = getRequest().getParameter("code");
        ServiceQuery svr = ServiceQuery.open(this, SvrPartInfo.delete, Map.of("code_", code));
        if (svr.isFail()) {
            UINotice.sendInfo(getSession(), this.getClass(), "execute", svr.dataOut().message());
            return new RedirectPage(this, "FrmPartInfo");
        }
        UINotice.sendInfo(getSession(), this.getClass(), "execute", String.format("%s 删除成功", code));
        return new RedirectPage(this, "FrmPartInfo");
    }
}
