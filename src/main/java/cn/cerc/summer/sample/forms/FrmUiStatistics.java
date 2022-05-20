package cn.cerc.summer.sample.forms;

import java.util.Map;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.db.core.DataSet;
import cn.cerc.mis.core.IPage;
import cn.cerc.mis.core.ServiceQuery;
import cn.cerc.mis.security.Permission;
import cn.cerc.mis.security.Webform;
import cn.cerc.summer.sample.core.CustomForm;
import cn.cerc.summer.sample.core.ui.UICustomPage;
import cn.cerc.summer.sample.core.ui.UINotice;
import cn.cerc.summer.sample.services.SampleServices.SvrStatistics;
import cn.cerc.ui.columns.CustomColumn;
import cn.cerc.ui.columns.DateColumn;
import cn.cerc.ui.columns.StringColumn;
import cn.cerc.ui.columns.UIGrid;
import cn.cerc.ui.columns.UIPhoneLine;
import cn.cerc.ui.panels.UISearchPanel;
import cn.cerc.ui.vcl.UIUrl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Webform(module = "", name = "统计管理ui", parent = "")
@Permission("user.base")
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmUiStatistics extends CustomForm {
    @Override
    public IPage execute() {
        UICustomPage page = new UICustomPage(this);

        new UIUrl(page.getFrontPanel()).setText("返回").setSite("welcome");
        new UINotice(page.getFrontPanel()).receive(this, "execute");

        UISearchPanel search = new UISearchPanel(page.getContent());
        new DateColumn(search, "单据日期", "tbDate_");
        search.readAll();
        
        ServiceQuery svr = ServiceQuery.open(this, SvrStatistics.statistics, search.getRecord());
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
        UIPhoneLine line4 = grid.addPhoneLine(50, 50);
        UIPhoneLine line5 = grid.addPhoneLine(100);

        new StringColumn(line1.cell(0), "统计日期", "tbDate_", 1);
        new StringColumn(line2.cell(1), "入库数量", "numIn_", 1);
        new StringColumn(line3.cell(0), "出库数量", "numOut_", 1);
        new StringColumn(line4.cell(1), "盈亏数量", "profitAndLoss_", 1);
        
        CustomColumn defineCell = new CustomColumn(line5.cell(0));
        defineCell.setSpaceWidth(3);
        defineCell.defineCell((column, row) -> {
            new UIUrl(column).setText("明细").setSite("FrmUiStatistics.detail").putParam("tbDate_", dataOut.getString("tbDate_"));
        });
        return page;
    }
    
    public IPage detail() {
        UICustomPage page = new UICustomPage(this);
       
        String tbDate = getRequest().getParameter("tbDate_");
        
        new UIUrl(page.getFrontPanel()).setText("返回").setSite("FrmUiStatistics");
        new UINotice(page.getFrontPanel()).receive(this, "execute");
        
        ServiceQuery svr = ServiceQuery.open(this, SvrStatistics.statisticsDetail, Map.of("tbDate_",tbDate));
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
        UIPhoneLine line4 = grid.addPhoneLine(50, 50);

        new StringColumn(line1.cell(1), "单据日期", "tbDate_", 1);
        new StringColumn(line2.cell(0), "单据编号", "tbNo_", 1);
        new StringColumn(line3.cell(1), "商品名称", "desc_", 1);
        new StringColumn(line4.cell(1), "数量", "num_", 1);

        return page;
    }
}
