package cn.cerc.sample.forms;

import java.util.Map;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import cn.cerc.db.core.DataSet;
import cn.cerc.db.core.Utils;
import cn.cerc.mis.client.ServiceExecuteException;
import cn.cerc.mis.core.IPage;
import cn.cerc.mis.core.RedirectPage;
import cn.cerc.mis.core.ServiceQuery;
import cn.cerc.mis.other.MemoryBuffer;
import cn.cerc.mis.security.Permission;
import cn.cerc.mis.security.Webform;
import cn.cerc.sample.SampleServicesConfig.SvrEmployeeV2;
import cn.cerc.sample.config.BufferUser;
import cn.cerc.sample.ui.CustomForm;
import cn.cerc.sample.ui.UICustomPage;
import cn.cerc.sample.ui.UINotice;
import cn.cerc.ui.columns.BooleanColumn;
import cn.cerc.ui.columns.CustomColumn;
import cn.cerc.ui.columns.DateColumn;
import cn.cerc.ui.columns.ItColumn;
import cn.cerc.ui.columns.OptionColumn;
import cn.cerc.ui.columns.StringColumn;
import cn.cerc.ui.columns.UIGrid;
import cn.cerc.ui.columns.UIPhoneLine;
import cn.cerc.ui.core.UrlRecord;
import cn.cerc.ui.panels.UIAppendPanel;
import cn.cerc.ui.panels.UIModifyPanel;
import cn.cerc.ui.panels.UISearchPanel;
import cn.cerc.ui.vcl.UIUrl;

@Webform(module = "", name = "员工管理", parent = "")
@Permission("user.base")
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmEmployeeV2 extends CustomForm {

    enum GenderEnum {
        未知, 男, 女;
    }

    @Description("默认的查询页面")
    @Override
    public IPage execute() throws ServiceExecuteException {
        // 创建一个自定义页面
        UICustomPage page = new UICustomPage(this);
        new UIUrl(page.getFrontPanel()).setText("返回").setSite("welcome");
        // 检查是否有需要显示的消息，并予以输出
        new UINotice(page.getFrontPanel()).receive(this, "execute");
        new UIUrl(page.getFooter()).setText("新增").setSite("FrmEmployeeV2.append");

        // 创建搜索面板
        UISearchPanel search = new UISearchPanel(page.getContent());
        new StringColumn(search, "员工工号", "code_").setPlaceholder("请输入员工工号");
        new StringColumn(search, "查询条件", "searchText_").setPlaceholder("请输入查询条件");
        search.readAll();

        // 调用查询服务
        DataSet dataOut = ServiceQuery.open(this, SvrEmployeeV2.search, search.current()).getDataOutElseThrow();

        // 创建数据表格
        UIGrid grid = new UIGrid(page.getContent()).setDataSet(dataOut);

        // 定义在手机上显示的行
        UIPhoneLine line1 = grid.addPhoneLine(50, 50);
        UIPhoneLine line2 = grid.addPhoneLine(50, 50);
        UIPhoneLine line3 = grid.addPhoneLine(50, 50);
        UIPhoneLine line4 = grid.addPhoneLine(100);

        // 定义表格的数据列
        new ItColumn(line1.cell(0));
        new StringColumn(line1.cell(1), "员工工号", "code_", 4);
        new StringColumn(line2.cell(0), "员工姓名", "name_", 4);
        new OptionColumn(line2.cell(1), "员工性别", "gender_", 3).setOptions(GenderEnum.values());
        new StringColumn(line3.cell(0), "入职日期", "entry_date_", 6);
        new BooleanColumn(line3.cell(1), "在职状态", "enable_");

        // 定义表格中特殊的操作列
        CustomColumn customColumn = new CustomColumn(line4.cell(0));
        customColumn.setSpaceWidth(4);
        customColumn.defineCell((content, record) -> new UIUrl(content).setText("修改").setSite("FrmEmployeeV2.modify")
                .putParam("code", record.getString("code_")));

        // 返回页面
        return page;
    }

    @Description("增加页面")
    public IPage append() {
        // 创建一个自定义页面
        UICustomPage page = new UICustomPage(this);
        new UIUrl(page.getFrontPanel()).setText("返回").setSite("FrmEmployeeV2");

        // 创建一个增加专用的面板
        UIAppendPanel actionForm = new UIAppendPanel(page.getContent());
        // 定义每行要显示的内容
        new StringColumn(actionForm, "员工工号", "code_").setRequired(true);
        new StringColumn(actionForm, "员工姓名", "name_").setRequired(true);
        new OptionColumn(actionForm, "员工性别", "gender_").setOptions(GenderEnum.values());
        new DateColumn(actionForm, "入职日期", "entry_date_");

        // 判断是否用户有点击保存按钮，若有则读取所有的提交到actionForm.getRecord()
        if (!Utils.isEmpty(actionForm.readAll())) {
            ServiceQuery svr = ServiceQuery.open(this, SvrEmployeeV2.append, actionForm.getRecord());
            if (svr.isFail())
                return page.setMessage(svr.dataOut().message());
            String code = svr.dataOut().getString("code_");
            // 传递成功的消息到修改页面
            UINotice.sendInfo(getSession(), this.getClass(), "modify", String.format("新增员工 %s", code));
            // 跳转到修改页面
            String modifyUrl = UrlRecord.builder("FrmEmployeeV2.modify").put("code", code).build().getUrl();
            return new RedirectPage(this, modifyUrl);
        }
        return page;
    }

    @Description("修改页面")
    public IPage modify() {
        // 创建一个自定义页面
        UICustomPage page = new UICustomPage(this);
        new UIUrl(page.getFrontPanel()).setText("返回").setSite("FrmEmployeeV2");
        // 检查是否有需要显示的消息，并予以输出
        new UINotice(page.getFrontPanel()).receive(this, "modify");

        // 启用页面缓存
        try (MemoryBuffer buff = new MemoryBuffer(BufferUser.Notice_UserCode, this.getUserCode(),
                "FrmEmployeeV2.modify")) {
            String code = page.getValue(buff, "code");
            if (Utils.isEmpty(code))
                return page.setMessage("code 不允许为空");

            ServiceQuery svr1 = ServiceQuery.open(this, SvrEmployeeV2.download, Map.of("code_", code));
            if (svr1.isFail())
                return page.setMessage(svr1.dataOut().message());

            new UIUrl(page.getFooter()).setText("删除").setSite("FrmEmployeeV2.delete").putParam("code", code);

            // 定义一个修改专用的面板
            UIModifyPanel actionForm = new UIModifyPanel(page.getContent());
            // 绑定数据源
            actionForm.setRecord(svr1.dataOut().current());

            // 显示要修改的内容
            new StringColumn(actionForm, "员工工号", "code_").setReadonly(true);
            new StringColumn(actionForm, "员工姓名", "name_");
            new OptionColumn(actionForm, "员工性别", "gender_").setOptions(GenderEnum.values());
            new DateColumn(actionForm, "入职日期", "entry_date_");
            new BooleanColumn(actionForm, "在职状态", "enable_");

            // 判断是否用户有点击保存按钮，若有则读取所有的提交到actionForm.getRecord()
            if (!Utils.isEmpty(actionForm.readAll())) {
                ServiceQuery svr = ServiceQuery.open(this, SvrEmployeeV2.modify, actionForm.getRecord());
                if (svr.isFail())
                    return page.setMessage(svr.dataOut().message());
                // 传递成功的消息到修改页面
                UINotice.sendInfo(getSession(), this.getClass(), "modify", "修改成功");
                // 跳转到修改页面
                return new RedirectPage(this, "FrmEmployeeV2.modify");
            }
        }

        return page;
    }

    @Description("删除页面")
//    public IPage delete(@PathVariable("code") String code) {
//    public IPage delete(String code) {
    public IPage delete() {
        String code = getRequest().getParameter("code");
        ServiceQuery svr = ServiceQuery.open(this, SvrEmployeeV2.delete, Map.of("code_", code));
        if (svr.isFail()) {
            UINotice.sendInfo(getSession(), this.getClass(), "modify", svr.dataOut().message());
            return new RedirectPage(this, "FrmEmployeeV2.modify");
        }
        UINotice.sendInfo(getSession(), this.getClass(), "execute", String.format("%s 删除成功", code));
        return new RedirectPage(this, "FrmEmployeeV2");
    }
}
