package cn.cerc.sample.forms;

import java.util.Map;

import cn.cerc.sample.services.SvrTranHead;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.db.core.DataSet;
import cn.cerc.db.core.Utils;
import cn.cerc.mis.client.ServiceExecuteException;
import cn.cerc.mis.core.IPage;
import cn.cerc.mis.core.RedirectPage;
import cn.cerc.mis.core.ServiceQuery;
import cn.cerc.mis.security.Permission;
import cn.cerc.mis.security.Webform;
import cn.cerc.sample.SampleServicesConfig.SvrOrderReport;
import cn.cerc.sample.ui.CustomForm;
import cn.cerc.sample.ui.UICustomPage;
import cn.cerc.sample.ui.UINotice;
import cn.cerc.ui.columns.CustomColumn;
import cn.cerc.ui.columns.DateColumn;
import cn.cerc.ui.columns.ItColumn;
import cn.cerc.ui.columns.StringColumn;
import cn.cerc.ui.columns.UIGrid;
import cn.cerc.ui.columns.UIPhoneLine;
import cn.cerc.ui.panels.UISearchPanel;
import cn.cerc.ui.vcl.UIUrl;

@Webform(module = "", name = "统计报表", parent = "")
@Permission("user.base")
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmOrderReport extends CustomForm {

    @Override
    public IPage execute() throws ServiceExecuteException {
        UICustomPage page = new UICustomPage(this);

        new UIUrl(page.getFrontPanel()).setText("返回").setSite("welcome");

        UISearchPanel search = new UISearchPanel(page.getContent());
        new DateColumn(search, "起始日期", "dateFrom_");
        new DateColumn(search, "起始日期", "dateTo_");
        search.readAll();

        DataSet dataOut = ServiceQuery.open(this, SvrOrderReport.search, search.current()).getDataOutElseThrow();
        UIGrid grid = new UIGrid(page.getContent()).setDataSet(dataOut);
        UIPhoneLine line1 = grid.addPhoneLine(50, 50);
        UIPhoneLine line2 = grid.addPhoneLine(50, 50);
        UIPhoneLine line3 = grid.addPhoneLine(50, 50);
        UIPhoneLine line4 = grid.addPhoneLine(100);

        new ItColumn(line1.cell(0));
        new StringColumn(line1.cell(1), "日期", "order_date_", 6);
        new CustomColumn(line2.cell(0), "入库数量", "in_", 4).defineCell((content, record) -> {
            new UIUrl(content).setText(record.getString("in_")).setSite("FrmOrderReport.detail")
                    .putParam("orderDate", record.getString("order_date_")).putParam("tb", SvrTranHead.TBType.AB.name());
        });
        new CustomColumn(line2.cell(1), "出库数量", "out_", 4).defineCell((content, record) -> {
            new UIUrl(content).setText(record.getString("out_")).setSite("FrmOrderReport.detail")
                    .putParam("orderDate", record.getString("order_date_")).putParam("tb", SvrTranHead.TBType.BC.name());
        });
        new CustomColumn(line3.cell(0), "盘点数量", "reality_", 4).defineCell((content, record) -> {
            new UIUrl(content).setText(record.getString("reality_")).setSite("FrmOrderReport.detail")
                    .putParam("orderDate", record.getString("order_date_")).putParam("tb", SvrTranHead.TBType.AE.name());
        });

        CustomColumn customColumn = new CustomColumn(line4.cell(0));
        customColumn.setSpaceWidth(8);
        customColumn.defineCell((content, record) -> {
            new UIUrl(content).setText("明细").setSite("FrmOrderReport.detail").putParam("orderDate",
                    record.getString("order_date_"));
        });
        return page;
    }

    public IPage detail() throws ServiceExecuteException {
        UICustomPage page = new UICustomPage(this);
        new UIUrl(page.getFrontPanel()).setText("返回").setSite("FrmOrderReport");

        String orderDate = getRequest().getParameter("orderDate");
        if (Utils.isEmpty(orderDate)) {
            UINotice.sendInfo(this.getSession(), getClass(), "execute", "统计日期不允许为空");
            return new RedirectPage(this, "FrmOrderReport");
        }
        String tb = getRequest().getParameter("tb");
        if (Utils.isEmpty(tb))
            tb = "";

        DataSet dataOut = ServiceQuery.open(this, SvrOrderReport.detail, Map.of("order_date_", orderDate, "tb_", tb))
                .getDataOutElseThrow();
        UIGrid grid = new UIGrid(page.getContent()).setDataSet(dataOut);
        UIPhoneLine line1 = grid.addPhoneLine(50, 50);
        UIPhoneLine line2 = grid.addPhoneLine(50, 50);
        UIPhoneLine line3 = grid.addPhoneLine(50, 50);
        UIPhoneLine line4 = grid.addPhoneLine(50, 50);
        UIPhoneLine line5 = grid.addPhoneLine(50, 50);
        UIPhoneLine line6 = grid.addPhoneLine(100);

        new ItColumn(line1.cell(0));
        new StringColumn(line1.cell(1), "日期", "order_date_", 6);

        new StringColumn(line2.cell(0), "单别", "tb_", 4);
        new CustomColumn(line2.cell(0), "单号", "order_sn_", 6).defineCell((content, record) -> {
            new UIUrl(content).setText(record.getString("order_sn_")).setSite("FrmTranOrder.modify")
                    .putParam("orderSN", record.getString("order_sn_")).setTarget("_blank");
        });

        new StringColumn(line3.cell(1), "单序", "it_", 4);
        new StringColumn(line3.cell(1), "料号", "code_", 4);

        new StringColumn(line4.cell(1), "品名", "desc_", 6);
        new StringColumn(line5.cell(1), "规格", "spec_", 7);

        new StringColumn(line5.cell(0), "商品数量", "num_", 3);
        new StringColumn(line6.cell(0), "变化增量", "increment_", 3);
        return page;
    }
}
