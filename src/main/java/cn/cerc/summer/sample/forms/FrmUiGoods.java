package cn.cerc.summer.sample.forms;

import java.util.Map;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.db.core.DataRow;
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
import cn.cerc.summer.sample.services.SampleServices.SvrBills;
import cn.cerc.summer.sample.services.SampleServices.SvrGoods;
import cn.cerc.ui.columns.CustomColumn;
import cn.cerc.ui.columns.ItColumn;
import cn.cerc.ui.columns.StringColumn;
import cn.cerc.ui.columns.UIGrid;
import cn.cerc.ui.columns.UIPhoneLine;
import cn.cerc.ui.core.UrlRecord;
import cn.cerc.ui.panels.UIAppendPanel;
import cn.cerc.ui.panels.UIModifyPanel;
import cn.cerc.ui.panels.UISearchPanel;
import cn.cerc.ui.vcl.UIUrl;

@Webform(module = "", name = "商品管理ui", parent = "")
@Permission("user.base")
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmUiGoods extends CustomForm {
	@Override
	public IPage execute() {
		UICustomPage page = new UICustomPage(this);

		new UIUrl(page.getFrontPanel()).setText("返回").setSite("welcome");
		new UINotice(page.getFrontPanel()).receive(this, "execute");
		new UIUrl(page.getFooter()).setText("新增").setSite("FrmUiGoods.append");

		UISearchPanel search = new UISearchPanel(page.getContent());
		new StringColumn(search, "编号", "code_").setPlaceholder("请输入商品编号");
		new StringColumn(search, "商品", "searchText_").setPlaceholder("请输入商品名称");
		search.readAll();

		ServiceQuery svr = ServiceQuery.open(this, SvrGoods.search, search.getRecord());
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
		UIPhoneLine line5 = grid.addPhoneLine(50, 50);
		UIPhoneLine line6 = grid.addPhoneLine(50, 50);
		UIPhoneLine line7 = grid.addPhoneLine(50, 50);
		UIPhoneLine line8 = grid.addPhoneLine(100);

		new ItColumn(line1.cell(0));
		new StringColumn(line1.cell(1), "账号", "userCode_", 4);
		new StringColumn(line2.cell(0), "商品编号", "code_", 2);
		new StringColumn(line3.cell(1), "品名", "desc_", 2);
		new StringColumn(line4.cell(0), "规格", "spec_", 2);
		new StringColumn(line5.cell(1), "单位", "unit_", 1);
		new StringColumn(line6.cell(0), "库存", "stock_", 2);
		new StringColumn(line7.cell(0), "备注", "remark_", 4);
		CustomColumn defineCell = new CustomColumn(line8.cell(0));
		defineCell.setSpaceWidth(4);
		defineCell.defineCell((column, row) -> {
			new UIUrl(column).setText("修改").setSite("FrmUiGoods.modify").putParam("code", row.getString("code_"));
			new UIUrl(column).setText(" | ");
			new UIUrl(column).setText("删除").setSite("FrmUiGoods.delete").putParam("code", row.getString("code_"));
		});
		return page;
	}

	public IPage append() {
		UICustomPage page = new UICustomPage(this);
		new UIUrl(page.getFrontPanel()).setSite("FrmUiGoods").setText("返回");
		UIAppendPanel form = new UIAppendPanel(page.getContent());
		new StringColumn(form, "商品编号", "code_").setRequired(true);
		new StringColumn(form, "品名", "desc_").setRequired(true);
		new StringColumn(form, "规格", "spec_").setRequired(true);
		new StringColumn(form, "单位", "unit_").setRequired(true);
		new StringColumn(form, "备注", "remark_").setRequired(false);

		if (!Utils.isEmpty(form.readAll())) {
			ServiceQuery svr = ServiceQuery.open(this, SvrGoods.append, form.getRecord());
			if (svr.isFail()) {
				page.setMessage(svr.dataOut().message());
				return page;
			}
			UINotice.sendInfo(getSession(), getClass(), "execute", "新增成功");
			return new RedirectPage(this, "FrmUiGoods");
		}
		return page;
	}

	public IPage modify() {
		UICustomPage page = new UICustomPage(this);
		new UIUrl(page.getFrontPanel()).setSite("FrmUiGoods").setText("返回");

		// 接收传过来的code值(相当于ID)
		String code = getRequest().getParameter("code");

		ServiceQuery svr = ServiceQuery.open(this, SvrGoods.download, Map.of("code_", code));
		if (svr.isFail()) {
			page.setMessage(svr.dataOut().message());
			return page;
		}

		DataRow row = new DataRow();
		row.copyValues(svr.dataOut().current());
		UIModifyPanel form = new UIModifyPanel(page.getContent());
		// 数据拷贝
		form.setRecord(row);
		// 不能直接set
//		form.setRecord(ds.current());
		new StringColumn(form, "商品编号", "code_").setReadonly(true);
		new StringColumn(form, "品名", "desc_").setReadonly(true);
		new StringColumn(form, "规格", "spec_").setRequired(true);
		new StringColumn(form, "单位", "unit_").setRequired(true);
//		new StringColumn(form, "库存", "stock_").setRequired(true);

		if (!Utils.isEmpty(form.readAll())) {
			ServiceQuery modify = ServiceQuery.open(this, SvrGoods.modify, form.getRecord());
			if (modify.isFail()) {
				page.setMessage(svr.dataOut().message());
				return page;
			}

			UINotice.sendInfo(getSession(), getClass(), "execute", "修改成功");
			return new RedirectPage(this, "FrmUiGoods");
		}
		return page;
	}

	public IPage delete() {
		String code = getRequest().getParameter("code");
		UrlRecord url = new UrlRecord();
		url.setSite("FrmUiGoods");
		
        // 查询是否存在单据，如果存在不让删除
		ServiceQuery svr1 = ServiceQuery.open(this, SvrBills.searchBody, Map.of("code_",code));
        if (svr1.isFail()) {
            UINotice.sendInfo(getSession(), getClass(), "execute", svr1.dataOut().message());
            new RedirectPage(this, url.getUrl());
        }
        DataSet dataOut = svr1.dataOut();
        if (dataOut.size()>0) {
            UINotice.sendInfo(getSession(), getClass(), "execute", "请先删除相关单据");
            return new RedirectPage(this, url.getUrl());
        }
        
		ServiceQuery svr = ServiceQuery.open(this, SvrGoods.delete, Map.of("code_",code));
		if (svr.isFail()) {
			UINotice.sendInfo(getSession(), getClass(), "execute", svr.dataOut().message());
		}

		UINotice.sendInfo(getSession(), getClass(), "execute", "删除成功");
		return new RedirectPage(this, url.getUrl());
	}

}
