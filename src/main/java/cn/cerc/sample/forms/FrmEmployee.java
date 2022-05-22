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
import cn.cerc.sample.SampleServicesConfig.SvrEmployee;
import cn.cerc.sample.core.BufferUser;
import cn.cerc.sample.core.CustomForm;
import cn.cerc.sample.core.ui.UICustomPage;
import cn.cerc.sample.core.ui.UINotice;
import cn.cerc.ui.columns.BooleanColumn;
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

@Webform(module = "", name = "员工管理", parent = "")
@Permission("user.base")
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmEmployee extends CustomForm {

    enum Gender {
        未知, 男, 女;
    }

    @Override
    public IPage execute() throws ServiceExecuteException {
        UICustomPage page = new UICustomPage(this);

        new UIUrl(page.getFrontPanel()).setText("返回").setSite("welcome");
        new UINotice(page.getFrontPanel()).receive(this, "execute");
        new UIUrl(page.getFooter()).setText("新增").setSite("FrmEmployee.append");

        UISearchPanel search = new UISearchPanel(page.getContent());
        new StringColumn(search, "员工工号", "code_").setPlaceholder("请输入员工工号");
        new StringColumn(search, "查询条件", "searchText_").setPlaceholder("请输入查询条件");
        search.readAll();

        DataSet dataOut = ServiceQuery.open(this, SvrEmployee.search, search.current()).getDataOutElseThrow();
        UIGrid grid = new UIGrid(page.getContent()).setDataSet(dataOut);
        UIPhoneLine line1 = grid.addPhoneLine(50, 50);
        UIPhoneLine line2 = grid.addPhoneLine(50, 50);
        UIPhoneLine line3 = grid.addPhoneLine(50, 50);
        UIPhoneLine line4 = grid.addPhoneLine(100);

        new ItColumn(line1.cell(0));
        new StringColumn(line1.cell(1), "员工工号", "code_", 4);
        new StringColumn(line2.cell(0), "员工姓名", "name_", 4);
        new OptionColumn(line2.cell(1), "员工性别", "gender_", 3).setOptions(Gender.values());
        new StringColumn(line3.cell(0), "入职日期", "entry_date_", 6);
        new BooleanColumn(line3.cell(1), "在职状态", "enable_");

        CustomColumn customColumn = new CustomColumn(line4.cell(0));
        customColumn.setSpaceWidth(8);
        customColumn.defineCell((content, record) -> {
            new UIUrl(content).setText("修改").setSite("FrmEmployee.modify").putParam("code", record.getString("code_"));
            new UISpan(content).setText("|");
            new UIUrl(content).setText("删除").setSite("FrmEmployee.delete").putParam("code", record.getString("code_"));
        });
        return page;
    }

    public IPage append() {
        UICustomPage page = new UICustomPage(this);
        new UIUrl(page.getFrontPanel()).setText("返回").setSite("FrmEmployee");
        UIAppendPanel actionForm = new UIAppendPanel(page.getContent());

        new StringColumn(actionForm, "员工工号", "code_").setRequired(true);
        new StringColumn(actionForm, "员工姓名", "name_").setRequired(true);
        new OptionColumn(actionForm, "员工性别", "gender_").setOptions(Gender.values());
        new StringColumn(actionForm, "入职日期", "entry_date_").setRequired(true).setPlaceholder("格式 2022-05-20");

        if (!Utils.isEmpty(actionForm.readAll())) {
            // 调用SvrCorpInfo.modify服务
            ServiceQuery svr = ServiceQuery.open(this, SvrEmployee.append, actionForm.getRecord());
            if (svr.isFail()) {
                page.setMessage(svr.dataOut().message());
                return page;
            }
            String code = svr.dataOut().getString("code_");
            UINotice.sendInfo(getSession(), this.getClass(), "modify", String.format("新增员工 %s", code));
            String modifyUrl = UrlRecord.builder("FrmEmployee.modify").put("code", code).build().getUrl();
            return new RedirectPage(this, modifyUrl);
        }
        return page;
    }

    public IPage modify() {
        UICustomPage page = new UICustomPage(this);
        new UIUrl(page.getFrontPanel()).setText("返回").setSite("FrmEmployee");
        new UINotice(page.getFrontPanel()).receive(this, "modify");

        try (MemoryBuffer buff = new MemoryBuffer(BufferUser.Notice_UserCode, this.getUserCode(),
                "FrmEmployee.modify")) {
            String code = page.getValue(buff, "code");
            if (Utils.isEmpty(code)) {
                page.setMessage("code 不允许为空");
                return page;
            }

            ServiceQuery svr1 = ServiceQuery.open(this, SvrEmployee.download, Map.of("code_", code));
            if (svr1.isFail()) {
                page.setMessage(svr1.dataOut().message());
                return page;
            }
            DataSet dataOut = svr1.dataOut();
            UIModifyPanel actionForm = new UIModifyPanel(page.getContent());
            actionForm.setRecord(dataOut.current());

            new StringColumn(actionForm, "员工工号", "code_").setReadonly(true);
            new StringColumn(actionForm, "员工姓名", "name_");
            new OptionColumn(actionForm, "员工性别", "gender_").setOptions(Gender.values());
            new StringColumn(actionForm, "入职日期", "entry_date_").setPlaceholder("格式 2022-05-20");
            new BooleanColumn(actionForm, "在职状态", "enable_");

            if (!Utils.isEmpty(actionForm.readAll())) {
                // 调用SvrCorpInfo.modify服务
                ServiceQuery svr = ServiceQuery.open(this, SvrEmployee.modify, actionForm.getRecord());
                if (svr.isFail()) {
                    page.setMessage(svr.dataOut().message());
                    return page;
                }
                UINotice.sendInfo(getSession(), this.getClass(), "modify", "修改成功");
                return new RedirectPage(this, "FrmEmployee.modify");
            }
        }
        return page;
    }

//    public IPage delete(@PathVariable("code") String code) {
    public IPage delete() {
        String code = getRequest().getParameter("code");
        ServiceQuery svr = ServiceQuery.open(this, SvrEmployee.delete, Map.of("code_", code));
        if (svr.isFail()) {
            UINotice.sendInfo(getSession(), this.getClass(), "execute", svr.dataOut().message());
            return new RedirectPage(this, "FrmEmployee");
        }
        UINotice.sendInfo(getSession(), this.getClass(), "execute", String.format("%s 删除成功", code));
        return new RedirectPage(this, "FrmEmployee");
    }
}
