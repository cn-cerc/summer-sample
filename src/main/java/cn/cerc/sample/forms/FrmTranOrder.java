package cn.cerc.sample.forms;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.db.core.DataRow;
import cn.cerc.db.core.DataSet;
import cn.cerc.db.core.Utils;
import cn.cerc.mis.ado.EntityQuery;
import cn.cerc.mis.client.ServiceExecuteException;
import cn.cerc.mis.core.IPage;
import cn.cerc.mis.core.RedirectPage;
import cn.cerc.mis.core.ServiceQuery;
import cn.cerc.mis.other.MemoryBuffer;
import cn.cerc.mis.security.Permission;
import cn.cerc.mis.security.Webform;
import cn.cerc.sample.SampleServices.SvrTranOrder;
import cn.cerc.sample.core.BufferUser;
import cn.cerc.sample.core.CustomForm;
import cn.cerc.sample.core.ui.UICustomPage;
import cn.cerc.sample.core.ui.UINotice;
import cn.cerc.sample.entity.PartinfoEntity;
import cn.cerc.sample.entity.TranBodyEntity;
import cn.cerc.sample.enums.TBType;
import cn.cerc.ui.columns.CustomColumn;
import cn.cerc.ui.columns.DateColumn;
import cn.cerc.ui.columns.DoubleColumn;
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

@Webform(module = "", name = "订单管理", parent = "")
@Permission("user.base")
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmTranOrder extends CustomForm {

    @Override
    public IPage execute() throws ServiceExecuteException {
        UICustomPage page = new UICustomPage(this);

        new UIUrl(page.getFrontPanel()).setText("返回").setSite("welcome");
        new UINotice(page.getFrontPanel()).receive(this, "execute");
        new UIUrl(page.getFooter()).setText("新增进货单").setSite("FrmTranOrder.appendHead").putParam("tb",
                TBType.AB.name());
        new UIUrl(page.getFooter()).setText(" | ");
        new UIUrl(page.getFooter()).setText("新增出货单").setSite("FrmTranOrder.appendHead").putParam("tb",
                TBType.BC.name());
        new UIUrl(page.getFooter()).setText(" | ");
        new UIUrl(page.getFooter()).setText("新增盘点单").setSite("FrmTranOrder.appendHead").putParam("tb",
                TBType.AE.name());

        UISearchPanel search = new UISearchPanel(page.getContent());
        new DateColumn(search, "起始日期", "dateFrom_");
        new DateColumn(search, "起始日期", "dateTo");
        new StringColumn(search, "订单单号", "code_").setPlaceholder("请输入订单单号");
        search.readAll();

        DataSet dataOut = ServiceQuery.open(this, SvrTranOrder.search, search.current()).getDataOutElseThrow();
        UIGrid grid = new UIGrid(page.getContent()).setDataSet(dataOut);
        UIPhoneLine line1 = grid.addPhoneLine(50, 50);
        UIPhoneLine line2 = grid.addPhoneLine(50, 50);
        UIPhoneLine line3 = grid.addPhoneLine(50, 50);
        UIPhoneLine line4 = grid.addPhoneLine(100);

        new ItColumn(line1.cell(0));
        new StringColumn(line1.cell(1), "单号", "order_sn_", 6);
        new StringColumn(line2.cell(0), "日期", "order_date_", 6);
        new StringColumn(line2.cell(1), "单别", "tb_", 4);
        new StringColumn(line3.cell(1), "备注", "remark_", 6);

        CustomColumn customColumn = new CustomColumn(line4.cell(0));
        customColumn.setSpaceWidth(8);
        customColumn.defineCell((content, record) -> {
            new UIUrl(content).setText("修改").setSite("FrmTranOrder.modify").putParam("orderSN",
                    record.getString("order_sn_"));
            new UISpan(content).setText("|");
            new UIUrl(content).setText("删除").setSite("FrmTranOrder.delete").putParam("orderSN",
                    record.getString("order_sn_"));
        });
        return page;
    }

    public IPage appendHead() {
        String tb = getRequest().getParameter("tb");
        TBType.validateTB(tb);
        ServiceQuery svr = ServiceQuery.open(this, SvrTranOrder.appendHead, Map.of("tb_", tb));
        if (svr.isFail()) {
            UINotice.sendInfo(getSession(), this.getClass(), "execute", svr.dataOut().message());
            return new RedirectPage(this, "FrmTranOrder");
        }
        String orderSN = svr.dataOut().getString("order_sn_");
        UINotice.sendInfo(getSession(), this.getClass(), "modify", String.format("新增订单 %s", orderSN));
        String modifyUrl = UrlRecord.builder("FrmTranOrder.modify").put("orderSN", orderSN).build().getUrl();
        return new RedirectPage(this, modifyUrl);
    }

    public IPage modify() {
        UICustomPage page = new UICustomPage(this);
        new UIUrl(page.getFrontPanel()).setText("返回").setSite("FrmTranOrder");
        new UINotice(page.getFrontPanel()).receive(this, "modify");

        try (MemoryBuffer buff = new MemoryBuffer(BufferUser.Notice_UserCode, this.getUserCode(),
                "FrmTranOrder.modify")) {
            String orderSN = page.getValue(buff, "orderSN");
            if (Utils.isEmpty(orderSN)) {
                page.setMessage("orderSN 不允许为空");
                return page;
            }
            new UIUrl(page.getFooter()).setText("添加单身").setSite("FrmTranOrder.appendBody").putParam("orderSN", orderSN);

            ServiceQuery svr1 = ServiceQuery.open(this, SvrTranOrder.download, Map.of("order_sn_", orderSN));
            if (svr1.isFail()) {
                page.setMessage(svr1.dataOut().message());
                return page;
            }
            DataSet dataOut = svr1.dataOut();
            UIModifyPanel actionForm = new UIModifyPanel(page.getContent());
            actionForm.setRecord(dataOut.head());

            new StringColumn(actionForm, "单别", "tb_").setReadonly(true);
            new StringColumn(actionForm, "单号", "order_sn_").setReadonly(true);
            new DateColumn(actionForm, "日期", "order_date_");
            new DoubleColumn(actionForm, "数量", "total_");
            new StringColumn(actionForm, "备注", "remark_");

            UIGrid grid = new UIGrid(page.getContent()).setDataSet(dataOut);
            UIPhoneLine line1 = grid.addPhoneLine(50, 50);
            UIPhoneLine line2 = grid.addPhoneLine(50, 50);
            UIPhoneLine line3 = grid.addPhoneLine(50, 50);
            UIPhoneLine line4 = grid.addPhoneLine(100);

            new StringColumn(line1.cell(0), "单序", "it_", 3);
            new StringColumn(line1.cell(1), "单号", "order_sn_", 6);

            new StringColumn(line2.cell(0), "品名", "desc_", 6);
            new StringColumn(line2.cell(1), "规格", "spec_", 6);

            new StringColumn(line3.cell(0), "料号", "code_", 6);
            new DoubleColumn(line3.cell(1), "数量", "num_", 4);

            CustomColumn customColumn = new CustomColumn(line4.cell(0));
            customColumn.setSpaceWidth(8);
            customColumn.defineCell((content, record) -> {
                new UIUrl(content).setText("修改").setSite("FrmTranOrder.modifyBody")
                        .putParam("orderSN", record.getString("order_sn_")).putParam("it", record.getString("it_"));
                new UISpan(content).setText("|");
                new UIUrl(content).setText("删除").setSite("FrmTranOrder.deleteBody")
                        .putParam("orderSN", record.getString("order_sn_")).putParam("it", record.getString("it_"));
            });

            if (!Utils.isEmpty(actionForm.readAll())) {
                ServiceQuery svr = ServiceQuery.open(this, SvrTranOrder.modifyHead, actionForm.getRecord());
                if (svr.isFail()) {
                    page.setMessage(svr.dataOut().message());
                    return page;
                }
                UINotice.sendInfo(getSession(), this.getClass(), "modify", "单头修改成功");
                return new RedirectPage(this, "FrmTranOrder.modify");
            }
        }
        return page;
    }

    public IPage appendBody() throws ServiceExecuteException {
        UICustomPage page = new UICustomPage(this);

        try (MemoryBuffer buff = new MemoryBuffer(BufferUser.Notice_UserCode, this.getUserCode(),
                "FrmTranOrder.modify")) {
            String orderSN = page.getValue(buff, "orderSN");
            if (Utils.isEmpty(orderSN)) {
                page.setMessage("orderSN 不允许为空");
                return page;
            }
            new UIUrl(page.getFrontPanel()).setText("返回").setSite("FrmTranOrder.modify").putParam("orderSN", orderSN);

            // 获取系统商品列表
            List<String> bodys = EntityQuery.findMany(this, TranBodyEntity.class, orderSN).stream()
                    .map(item -> item.getCode_()).collect(Collectors.toList());
            Map<String, String> goods = new LinkedHashMap<>();
            EntityQuery.findMany(this, PartinfoEntity.class).stream().filter(item -> !bodys.contains(item.getCode_()))
                    .forEach(item -> goods.put(item.getCode_(), item.getDesc_()));

            UIAppendPanel actionForm = new UIAppendPanel(page.getContent());
            new OptionColumn(actionForm, "商品", "code_").setOptions(goods);
            new DoubleColumn(actionForm, "数量", "num_");

            if (!Utils.isEmpty(actionForm.readAll())) {
                DataRow headIn = new DataRow();
                headIn.copyValues(actionForm.getRecord());
                headIn.setValue("order_sn_", orderSN);
                ServiceQuery svr = ServiceQuery.open(this, SvrTranOrder.appendBody, headIn);
                if (svr.isFail()) {
                    page.setMessage(svr.dataOut().message());
                    return page;
                }
                String code = svr.dataOut().getString("code_");
                UINotice.sendInfo(getSession(), this.getClass(), "modify", String.format("新增商品 %s", code));
                String modifyUrl = UrlRecord.builder("FrmTranOrder.modify").put("orderSN", orderSN).build().getUrl();
                return new RedirectPage(this, modifyUrl);
            }
            return page;
        }
    }

}
