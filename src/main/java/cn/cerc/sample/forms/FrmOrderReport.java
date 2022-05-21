package cn.cerc.sample.forms;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.db.core.DataSet;
import cn.cerc.mis.client.ServiceExecuteException;
import cn.cerc.mis.core.IPage;
import cn.cerc.mis.core.ServiceQuery;
import cn.cerc.mis.security.Permission;
import cn.cerc.mis.security.Webform;
import cn.cerc.sample.SampleServicesConfig.SvrOrderReport;
import cn.cerc.sample.core.CustomForm;
import cn.cerc.sample.core.ui.UICustomPage;
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
        new DateColumn(search, "起始日期", "dateTo");
        search.readAll();

        DataSet dataOut = ServiceQuery.open(this, SvrOrderReport.search, search.current()).getDataOutElseThrow();
        UIGrid grid = new UIGrid(page.getContent()).setDataSet(dataOut);
        UIPhoneLine line1 = grid.addPhoneLine(50, 50);
        UIPhoneLine line2 = grid.addPhoneLine(50, 50);
        UIPhoneLine line3 = grid.addPhoneLine(50, 50);
        UIPhoneLine line4 = grid.addPhoneLine(100);

        new ItColumn(line1.cell(0));
        new StringColumn(line1.cell(1), "日期", "order_date_", 6);

        new StringColumn(line2.cell(0), "入库数量", "order_sn_", 6);
        new StringColumn(line2.cell(1), "出库数量", "tb_", 4);

        new StringColumn(line3.cell(0), "当日库存", "tb_", 4);

        CustomColumn customColumn = new CustomColumn(line4.cell(0));
        customColumn.setSpaceWidth(8);
        customColumn.defineCell((content, record) -> {
            new UIUrl(content).setText("明细").setSite("FrmOrderReport.modify").putParam("orderSN",
                    record.getString("order_sn_"));
        });
        return page;
    }

}
